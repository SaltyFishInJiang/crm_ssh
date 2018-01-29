package crm.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.action.verfiy.VerfiyCode;
import crm.entity.User;
import crm.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	//模型驱动封装表单数据
	private User form = new User();
	//属性封装方式封装验证码
	private String verfiyCode;
	public String login() {
		User user = userService.login(form);
		HttpServletRequest request = ServletActionContext.getRequest();
		//验证码校验
		if(!verfiyCode.equalsIgnoreCase((String)request.getSession().getAttribute("verfiyCode"))) {
			request.setAttribute("error", "验证码错误");
			request.setAttribute("user", form);
			return "login";
		}
		if (user != null) {
			request.getSession().setAttribute("user", user);
			return "loginSuccess";
		} else {
			request.setAttribute("error", "用户名或者密码错误");
			request.setAttribute("user", form);
			return "login";
		}
	}
	//验证码
	public String verfiy() throws IOException {
		VerfiyCode vc = new VerfiyCode();
		BufferedImage image = vc.getImage();
		ServletActionContext.getRequest().getSession().setAttribute("verfiyCode", vc.getText());
		VerfiyCode.output(image, ServletActionContext.getResponse().getOutputStream());
		return NONE;
	}
	//跳转到修改密码
	public String toEdit() {
		return "toEdit";
	}
	//保存修改
	public String edit() {
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String password=user.getPassword();
		if (form.getPassword().equals(password)) {
			ServletActionContext.getRequest().setAttribute("error", "新密码与原密码一致，请重新修改");
			return "editPswFail";
		}
		userService.update(form);
		ServletActionContext.getRequest().getSession().setAttribute("user", form);
		return "editPswSuccess";
	}
	//查询所有
	public String list() {
		List<User> list=userService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	//删除用户
	public String delete() {
		User user=userService.findById(form.getUid());
		if (user!=null) {
			userService.delete(user);
		}
		return "deleteSuccess";
	}
	//初始化用户
	public String userInitialize() {
		User user=userService.findById(form.getUid());
		if (user!=null) {
			user.setPassword("1234");
			userService.update(user);
		}
		return "initialize";
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return form;
	}

	public String getVerfiyCode() {
		return verfiyCode;
	}

	public void setVerfiyCode(String verfiyCode) {
		this.verfiyCode = verfiyCode;
	}
}
