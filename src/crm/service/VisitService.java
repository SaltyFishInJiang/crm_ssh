package crm.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.VisitDao;
import crm.entity.Customer;
import crm.entity.PageBean;
import crm.entity.Visit;

@Transactional
public class VisitService {
	@Resource(name="visitDao")
	private VisitDao visitDao;
	// 添加拜访记录
	public void add(Visit form) {
		visitDao.add(form);
	}	
	// 分页查询所有
	public PageBean<Visit> list(int pc) {
		PageBean<Visit> pb = new PageBean<Visit>();
		// 查询并封装总记录数
		pb.setTr(visitDao.findCount());
		// 设置每页记录数
		int ps = 10;
		// 查询并封装每页记录数以及客户信息集合
		pb.setBeanList(visitDao.findByPage(pc, ps));
		pb.setPs(ps);
		return pb;
	}
	//综合查询
	public List<Visit> findByMoreCondition(Visit form, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return visitDao.findByMoreCondition(form,startDate,endDate);
	}	
}
