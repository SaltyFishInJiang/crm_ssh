package crm.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.CustomerDao;
import crm.entity.Customer;
import crm.entity.DictCustLevel;
import crm.entity.DictCustSource;
import crm.entity.PageBean;

@Transactional
public class CustomerService {
	@Resource(name = "customerDao")
	private CustomerDao customerDao;

	// 添加客户
	public void add(Customer form) {
		customerDao.add(form);
	}

	// 分页查询所有
	public PageBean<Customer> list(int pc) {
		PageBean<Customer> pb = new PageBean<Customer>();
		// 查询并封装总记录数
		pb.setTr(customerDao.findCount());
		// 设置每页记录数
		int ps = 3;
		// 查询并封装每页记录数以及客户信息集合
		pb.setBeanList(customerDao.findByPage(pc, ps));
		pb.setPs(ps);
		return pb;
	}

	// 根据id查询客户
	public Customer findById(Integer cid) {
		// TODO Auto-generated method stub
		return customerDao.get(cid);
	}

	// 删除客户
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	// 保存修改，更新客户信息
	public void update(Customer form) {
		customerDao.update(form);
	}

	// 根据条件进行，分页查询
	public PageBean<Customer> listCriteria(Customer customer, int pc) {
		PageBean<Customer> pb = new PageBean<Customer>();
		// 查询并封装总记录数
		pb.setTr(customerDao.findCountByCriteria(customer));
		// 设置每页记录数
		int ps = 10;
		// 查询并封装每页记录数以及客户信息集合
		pb.setBeanList(customerDao.findByPageAndCriteria(customer, pc, ps));
		pb.setPs(ps);
		return pb;
	}
	//查询所有用户
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	//综合条件查询
	public List<Customer> findByMoreCondition(Customer form) {
		return customerDao.findByMoreCondition(form);
	}
	//查找客户级别数据字典
	public List<DictCustLevel> findAllLevel() {
		// TODO Auto-generated method stub
		return customerDao.findAllLevel();
	}
	//查找客户来源数据字典
	public List<DictCustSource> findAllSource() {
		// TODO Auto-generated method stub
		return customerDao.findAllSource();
	}
	//根据客户级别统计
	public List<Map<String, Object>> countLevel() {
		// TODO Auto-generated method stub
		return customerDao.countLevel();
	}
	//根据客户来源统计
	public List<Map<String, Object>> countSource() {
		// TODO Auto-generated method stub
		return customerDao.countSource();
	}


}
