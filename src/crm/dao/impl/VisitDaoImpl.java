package crm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import crm.dao.VisitDao;
import crm.entity.Customer;
import crm.entity.Visit;

public class VisitDaoImpl implements VisitDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//添加客户
	@Override
	public void add(Visit form) {
		hibernateTemplate.save(form);
	}	
	//查询总记录数
	@Override
	public int findCount() {
		@SuppressWarnings("unchecked")
		List<Long> listCount=(List<Long>) hibernateTemplate.find("select count(*) from Visit");
		int tr=0;
		if (listCount!=null&&listCount.size()>0) {
			tr=listCount.get(0).intValue();
		}
		return tr;
	}
	//分页查询所有
	@Override
	public List<Visit> findByPage(int pc, int ps) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Visit.class);
		@SuppressWarnings("unchecked")
		List<Visit> list=(List<Visit>) hibernateTemplate.findByCriteria(criteria, (pc-1)*ps, ps);
		return list;
	}
	//综合查询
	@Override
	public List<Visit> findByMoreCondition(Visit form, Date startDate, Date endDate) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Visit.class);
		if (form.getUser().getUid()!=null&&form.getUser().getUid()>0) {
			detachedCriteria.add(Restrictions.eq("user.uid", form.getUser().getUid()));
		}
		if (form.getCustomer().getCid()!=null&&form.getCustomer().getCid()>0) {
			detachedCriteria.add(Restrictions.eq("customer.cid", form.getCustomer().getCid()));
		}
		if (startDate!=null) {
			detachedCriteria.add(Restrictions.ge("visitDate", startDate));
		}
		if (endDate!=null) {
			detachedCriteria.add(Restrictions.le("visitDate", endDate));
		}
		@SuppressWarnings("unchecked")
		List<Visit> list=(List<Visit>) hibernateTemplate.findByCriteria(detachedCriteria);
		if (list!=null&list.size()>0) {
			return list;
		}
		return null;
	}
}
