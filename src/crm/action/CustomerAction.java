package crm.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.entity.Customer;
import crm.entity.DictCustLevel;
import crm.entity.DictCustSource;
import crm.entity.PageBean;
import crm.service.CustomerService;

@SuppressWarnings("serial")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	@Autowired
	private CustomerService customerService;
	//模型驱动，封装表单数据
	private Customer form=new Customer();
	//属性封装，封装当前页码
	int pc;
	@Override
	public Customer getModel() {
		return form;
	}
	//跳转到客户添加页面
	public String toAdd() {
		List<DictCustLevel> listLevel=customerService.findAllLevel();
		List<DictCustSource> listSource=customerService.findAllSource();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("listLevel", listLevel);
		request.setAttribute("listSource", listSource);
 		return "toAdd";
	}
	//添加客户
	public String  add() {
		customerService.add(form);
		return "addSuccess";
	}
	//客户列表，返回所有
	public String list() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//保存项目路径+操作路径
		String url=request.getRequestURI()+"?";
		//如果没指定当前页面，默认为第一页
		if (pc==0) {
			pc=1;
		}
		//返回页面
		PageBean<Customer> pb=customerService.list(pc);
		pb.setUrl(url);
		pb.setPc(pc);
		request.setAttribute("pb", pb);
		return "list";
	}
	//根据客户id删除
	public String delete() {
		//先根据模型驱动获得客户id，查询出客户信息
		Customer customer=customerService.findById(form.getCid());
		//如果查询结果不为空，执行删除
		if (customer!=null) {
			customerService.delete(customer);
		}
		return "deleteSuccess";
	}
	//跳转到修改页面，返回要修改的客户信息到页面上
	public String  toEdit() {
		Customer customer=customerService.findById(form.getCid());
		List<DictCustLevel> listLevel=customerService.findAllLevel();
		List<DictCustSource> listSource=customerService.findAllSource();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("customer", customer);
		request.setAttribute("listLevel", listLevel);
		request.setAttribute("listSource", listSource);
		return "toEdit";
	}
	//保存修改
	public String edit() {
		customerService.update(form);
		return "editSuccess";
	}
	//根据条件查询
	public String listCriteria() {
		if (form.getCustName()!=null&&!"".equalsIgnoreCase(form.getCustName().trim())) {
			HttpServletRequest request=ServletActionContext.getRequest();
			//保存项目路径+操作路径
			String queryString=request.getQueryString();
			//如果路径中存在页码，则将页码部分去除
			if (queryString.contains("pc")) {
				queryString=queryString.substring(0,request.getQueryString().indexOf("&pc"));
			}
			String url=request.getRequestURI()+"?"+queryString+"&";
			//如果没指定当前页面，默认为第一页
			if (pc==0) {
				pc=1;
			}
			//返回页面
			PageBean<Customer> pb=customerService.listCriteria(form,pc);
			pb.setUrl(url);
			pb.setPc(pc);
			request.setAttribute("pb", pb);
		}else {
			list();
		}
		return "listCriteria";
	}
	//跳转到条件查询页面
	public String toSelect() {
		List<DictCustLevel> listLevel=customerService.findAllLevel();
		List<DictCustSource> listSource=customerService.findAllSource();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("listLevel", listLevel);
		request.setAttribute("listSource", listSource);
		return "toSelect";
	}
	//条件查询 
	public String select() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//返回页面
		List<Customer> list=customerService.findByMoreCondition(form);
		request.setAttribute("list", list);
		return "selectList";
	}
	//根据客户来源统计
	public String countLevel() {
		List<Map<String, Object>> list=customerService.countLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
	//根据客户级别统计
	public String countSource() {
		List<Map<String, Object>> list=customerService.countSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
}
