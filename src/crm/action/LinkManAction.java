package crm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.entity.Customer;
import crm.entity.LinkMan;
import crm.entity.PageBean;
import crm.service.CustomerService;
import crm.service.LinkManService;

@SuppressWarnings("serial")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	@Autowired
	private LinkManService linkManService;
	@Autowired
	private CustomerService customerService;
	// 模型驱动，封装linkMan数据
	private LinkMan form = new LinkMan();
// 通过属性封装，获取参数
	//获取当前页码
	private int pc;

	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return form;
	}

	// 跳转到添加页面
	public String toAdd() {
		// 通过customerService获得客户信息的集合，保存到域对象中
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		// 通过转发，将客户信息显示在添加页面上
		return "toAdd";
	}

	// 添加联系人
	public String add() {
		// 通过所属客户id查询客户信息
		Customer customer = customerService.findById(form.getCustomer().getCid());
		// 将客户信息设置到联系人中
		form.setCustomer(customer);
		// 保存联系人信息
		linkManService.add(form);
		return "addSuccess";
	}
	//联系人列表，返回所有
	public String list() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//保存项目路径+操作路径
		String url=request.getRequestURI()+"?";
		//如果没指定当前页面，默认为第一页
		if (pc==0) {
			pc=1;
		}
		//返回页面
		PageBean<LinkMan> pb=linkManService.list(pc);
		pb.setUrl(url);
		pb.setPc(pc);
		request.setAttribute("pb", pb);
		return "list";
	}	
	//跳转到修改页面，返回要修改的联系人信息到页面上
	public String  toEdit() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//得到所有的客户信息，并返回到修改页面
		List<Customer> listCustomer = customerService.findAll();
		request.setAttribute("listCustomer", listCustomer);
		LinkMan linkMan=linkManService.findById(form.getLkm_id());
		request.setAttribute("linkman", linkMan);
		return "toEdit";
	}	
	
	//保存修改
	public String edit() {
		// 根据所属客户的id查询到客户信息
		Customer customer = customerService.findById(form.getCustomer().getCid());
		//保存客户信息到联系人
		form.setCustomer(customer);
		//更新操作
		linkManService.update(form);
		return "editSuccess";
	}	
	
	//删除联系人
	public String delete() {
		//先根据id查询出联系人
		LinkMan linkMan=linkManService.findById(form.getLkm_id());
		//查询结果不为空，则执行删除
		if (linkMan!=null) {
			linkManService.delete(linkMan);
		}
		return "deleteSuccess";
	}	
	//根据条件查询
	public String listCriteria() {
		//如果查询条件不为空，执行条件查询，否则直接返回到列表页面
		if (form.getLkm_name()!=null&&!"".equalsIgnoreCase(form.getLkm_name().trim())) {
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
			PageBean<LinkMan> pb=linkManService.listCriteria(form,pc);
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
		// 通过customerService获得客户信息的集合，保存到域对象中
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);		
		return "toSelect";
	}
	//条件查询 
	public String select() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//返回页面
		List<LinkMan> list=linkManService.findByMoreCondition(form);
		request.setAttribute("list", list);
		return "selectList";
	}	
	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}
		
}
