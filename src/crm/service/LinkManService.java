package crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.LinkManDao;
import crm.entity.LinkMan;
import crm.entity.PageBean;

@Transactional
public class LinkManService {
	@Resource(name="linkManDao")
	private LinkManDao linkManDao;
	
	//添加联系人
	public void add(LinkMan form) {
		linkManDao.add(form);
	}
	// 分页查询所有
	public PageBean<LinkMan> list(int pc) {
		PageBean<LinkMan> pb = new PageBean<LinkMan>();
		// 查询并封装总记录数
		pb.setTr(linkManDao.findCount());
		// 设置每页记录数
		int ps = 3;
		// 查询并封装每页记录数以及客户信息集合
		pb.setBeanList(linkManDao.findByPage(pc, ps));
		pb.setPs(ps);
		return pb;
	}
	// 根据id查询联系人
	public LinkMan findById(Integer lkm_id) {
		// TODO Auto-generated method stub
		return linkManDao.findById(lkm_id);
	}		
	// 保存修改，更新客户信息
	public void update(LinkMan form) {
		linkManDao.update(form);
	}	
	// 删除客户
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}	
	// 根据条件进行，分页查询
	public PageBean<LinkMan> listCriteria(LinkMan linkMan, int pc) {
		PageBean<LinkMan> pb = new PageBean<LinkMan>();
		// 查询并封装总记录数
		pb.setTr(linkManDao.findCountByCriteria(linkMan));
		// 设置每页记录数
		int ps = 10;
		// 查询并封装每页记录数以及联系人信息集合
		pb.setBeanList(linkManDao.findByPageAndCriteria(linkMan, pc, ps));
		pb.setPs(ps);
		return pb;
	}
	public List<LinkMan> findByMoreCondition(LinkMan form) {
		// TODO Auto-generated method stub
		return linkManDao.findByMoreCondition(form);
	}	
}
