package crm.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.entity.Customer;
import crm.entity.PageBean;
import crm.entity.User;
import crm.entity.Visit;
import crm.service.CustomerService;
import crm.service.UserService;
import crm.service.VisitService;

@SuppressWarnings("serial")
public class VisitAction extends ActionSupport implements ModelDriven<Visit> {
	@Autowired
	private VisitService visitService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	//通过模型驱动，封装表单数据 
	private Visit form=new Visit();
	//通过属性封装，封装参数
	//当前页码
	int pc;
	//开始日期
	Date startDate;
	//结束日期
	Date endDate;
	@Override
	public Visit getModel() {
		// TODO Auto-generated method stub
		return form;
	}
	
	//跳转到添加页面
	public String  toAdd() {
		//查询所有用户
		List<User> listUser=userService.findAll();
		//查询所有客户
		List<Customer> listCustomer=customerService.findAll();
		//保存到request域
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		return "toAdd";
	}
	//添加拜访记录
	public String add() {
		//根据用户id查找到用户信息
		User user=userService.findById(form.getUser().getUid());
		//根据客户id查找到客户信息
		Customer customer=customerService.findById(form.getCustomer().getCid());
		//将用户与客户信息存到拜访记录中
		form.setUser(user);
		form.setCustomer(customer);
		//保存拜访记录
		visitService.add(form);
		return "addSuccess"; 
	}
	//客户拜访列表，返回所有
	public String list() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//保存项目路径+操作路径
		String url=request.getRequestURI()+"?";
		//如果没指定当前页面，默认为第一页
		if (pc==0) {
			pc=1;
		}
		//返回页面
		PageBean<Visit> pb=visitService.list(pc);
		pb.setUrl(url);
		pb.setPc(pc);
		request.setAttribute("pb", pb);
		return "list";
	}
	//跳转到条件查询页面
	public String toSelect() {
		//查询所有用户
		List<User> listUser=userService.findAll();
		//查询所有客户
		List<Customer> listCustomer=customerService.findAll();
		//保存到request域
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);		
		return "toSelect";
	}
	//条件查询 
	public String select() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//返回页面
		List<Visit> list=visitService.findByMoreCondition(form,startDate,endDate);
		request.setAttribute("list", list);
		return "selectList";
	}
	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}	
	
}
