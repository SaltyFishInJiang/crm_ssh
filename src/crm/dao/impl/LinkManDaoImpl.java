package crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import crm.dao.LinkManDao;
import crm.entity.LinkMan;

public class LinkManDaoImpl implements LinkManDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//添加联系人
	@Override
	public void add(LinkMan form) {
		hibernateTemplate.save(form);
	}
	//查询总记录数
	@Override
	public int findCount() {
		@SuppressWarnings("unchecked")
		List<Long> listCount=(List<Long>) hibernateTemplate.find("select count(*) from LinkMan");
		int tr=0;
		if (listCount!=null&&listCount.size()>0) {
			tr=listCount.get(0).intValue();
		}
		return tr;
	}
	//分页查询所有
	@Override
	public List<LinkMan> findByPage(int pc, int ps) {
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		@SuppressWarnings("unchecked")
		List<LinkMan> list=(List<LinkMan>) hibernateTemplate.findByCriteria(criteria, (pc-1)*ps, ps);
		return list;
	}	
	//根据id查询联系人信息
	@Override
	public LinkMan findById(Integer lkm_id) {
		LinkMan linkMan=hibernateTemplate.get(LinkMan.class, lkm_id);
		return linkMan;
	}	
	//保存修改，更新客户信息
	@Override
	public void update(LinkMan form) {
		hibernateTemplate.update(form);
	}
	//删除联系人
	@Override
	public void delete(LinkMan linkMan) {
		hibernateTemplate.delete(linkMan);
	}	
	//根据条件查询总记录数
	@Override
	public int findCountByCriteria(LinkMan linkMan) {
		@SuppressWarnings("unchecked")
		List<Long> listCount=(List<Long>) hibernateTemplate.find("select count(*) from LinkMan where lkm_name like ?",
					"%"+linkMan.getLkm_name().trim()+"%");
		int tr=0;
		if (listCount!=null&&listCount.size()>0) {
			tr=listCount.get(0).intValue();
		}
		return tr;
	}
	//根据条件分页查询所有
	@Override
	public List<LinkMan> findByPageAndCriteria(LinkMan linkMan, int pc, int ps) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LinkMan.class);
		Criteria criteria=detachedCriteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().getCurrentSession());
		criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name().trim()+"%"))
		.setFirstResult((pc-1)*ps).setMaxResults(ps);
		@SuppressWarnings("unchecked")
		List<LinkMan> listCriteria=(List<LinkMan>) hibernateTemplate.findByCriteria(detachedCriteria);
		if (listCriteria!=null&&listCriteria.size()>0) {
			return listCriteria;
		}
		return null;
	}
	//综合条件查询
	@Override
	public List<LinkMan> findByMoreCondition(LinkMan form) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LinkMan.class);
		if (form.getLkm_name()!=null&&!"".equalsIgnoreCase(form.getLkm_name().trim())) {
			detachedCriteria.add(Restrictions.eq("lkm_name", form.getLkm_name()));
		}
		if (form.getLkm_gender()!=null&&!"".equalsIgnoreCase(form.getLkm_gender().trim())) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", form.getLkm_gender()));
		}
		if (form.getCustomer().getCid()!=null&&form.getCustomer().getCid()>0) {
			detachedCriteria.add(Restrictions.eq("customer.cid", form.getCustomer().getCid()));
		}
		@SuppressWarnings("unchecked")
		List<LinkMan> list=(List<LinkMan>) hibernateTemplate.findByCriteria(detachedCriteria);
		if (list!=null&list.size()>0) {
			return list;
		}
		return null;
	}	
}
