package crm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import crm.dao.CustomerDao;
import crm.entity.Customer;
import crm.entity.DictCustLevel;
import crm.entity.DictCustSource;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
//	//添加客户
//	@Override
//	public void add(Customer form) {
//		hibernateTemplate.save(form);
//	}
	//查询总记录数
	@Override
	public int findCount() {
		@SuppressWarnings("unchecked")
		List<Long> listCount=(List<Long>) this.getHibernateTemplate().find("select count(*) from Customer");
		int tr=0;
		if (listCount!=null&&listCount.size()>0) {
			tr=listCount.get(0).intValue();
		}
		return tr;
	}
	//分页查询所有
	@Override
	public List<Customer> findByPage(int pc, int ps) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		@SuppressWarnings("unchecked")
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pc-1)*ps, ps);
		return list;
	}
	//根据id查询客户
//	@Override
//	public Customer get(Integer cid) {
//		Customer customer=hibernateTemplate.get(Customer.class, cid);
//		return customer;
//	}
	//删除客户
//	@Override
//	public void delete(Customer customer) {
//		hibernateTemplate.delete(customer);
//	}
	//保存修改，更新客户信息
//	@Override
//	public void update(Customer form) {
//		hibernateTemplate.update(form);
//	}
	//根据条件查询总记录数
	@Override
	public int findCountByCriteria(Customer customer) {
		@SuppressWarnings("unchecked")
		List<Long> listCount=(List<Long>) this.getHibernateTemplate().find("select count(*) from Customer where custName like ?",
					"%"+customer.getCustName().trim()+"%");
		int tr=0;
		if (listCount!=null&&listCount.size()>0) {
			tr=listCount.get(0).intValue();
		}
		return tr;
	}
	//根据条件分页查询所有
	@Override
	public List<Customer> findByPageAndCriteria(Customer customer, int pc, int ps) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
		Criteria criteria=detachedCriteria.getExecutableCriteria(this.getHibernateTemplate().getSessionFactory().getCurrentSession());
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName().trim()+"%"))
		.setFirstResult((pc-1)*ps).setMaxResults(ps);
		@SuppressWarnings("unchecked")
		List<Customer> listCriteria=(List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (listCriteria!=null&&listCriteria.size()>0) {
			return listCriteria;
		}
		return null;
	}
//	//查询所有用户
//	@Override
//	public List<Customer> findAll() {
//		@SuppressWarnings("unchecked")
//		List<Customer> listCustomer=(List<Customer>) hibernateTemplate.find("from Customer");
//		return listCustomer;
//	}
	@Override
	public List<Customer> findByMoreCondition(Customer form) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
		if (form.getCustName()!=null&&!"".equalsIgnoreCase(form.getCustName().trim())) {
			detachedCriteria.add(Restrictions.eq("custName", form.getCustName()));
		}
		if (form.getDictCustLevel().getDclid()!=null&&form.getDictCustLevel().getDclid()>0) {
			detachedCriteria.add(Restrictions.eq("dictCustLevel.dclid",form.getDictCustLevel().getDclid()));
		}
		if (form.getDictCustSource().getDcsid()!=null&&form.getDictCustSource().getDcsid()>0) {
			detachedCriteria.add(Restrictions.eq("dictCustSource.dcsid", form.getDictCustSource().getDcsid()));
		}
		@SuppressWarnings("unchecked")
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list!=null&list.size()>0) {
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DictCustLevel> findAllLevel() {
		return (List<DictCustLevel>) this.getHibernateTemplate().find("from DictCustLevel");
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DictCustSource> findAllSource() {
		// TODO Auto-generated method stub
		return (List<DictCustSource>) this.getHibernateTemplate().find("from DictCustSource");
	}
	//根据客户级别统计
	@SuppressWarnings("deprecation")
	@Override
	public List<Map<String, Object>> countLevel() {
		String sql="SELECT COUNT(c.custLevel) AS level_num,d.dclname AS level_name "
				+ "FROM  customer  c RIGHT  OUTER JOIN dictCustLevel d "
				+ "ON c.custLevel=d.dclid GROUP BY d.dclname" ;
		//获取session
		Session session=this.getSessionFactory().getCurrentSession();
		//创建sqlquery
		@SuppressWarnings("rawtypes")
		SQLQuery sqlQuery=session.createSQLQuery(sql);
		//处理返回结果
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		//得到list
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list=sqlQuery.getResultList();
		return list;
	}
	//根据客户来源统计
		@SuppressWarnings("deprecation")
		@Override
		public List<Map<String, Object>> countSource() {
			String sql="SELECT COUNT(c.custSource) AS source_num,d.dcsname AS source_name "
					+ "FROM  customer  c RIGHT  OUTER JOIN dictCustSource d "
					+ "ON c.custSource=d.dcsid GROUP BY d.dcsname" ;
			//获取session
			Session session=this.getSessionFactory().getCurrentSession();
			//创建sqlquery
			@SuppressWarnings("rawtypes")
			SQLQuery sqlQuery=session.createSQLQuery(sql);
			//处理返回结果
			sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
			//得到list
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> list=sqlQuery.getResultList();
			return list;
		}
}
