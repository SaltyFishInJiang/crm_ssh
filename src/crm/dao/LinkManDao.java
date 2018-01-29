package crm.dao;

import java.util.List;

import crm.entity.LinkMan;

public interface LinkManDao {

	void add(LinkMan form);
	
	int findCount();

	List<LinkMan> findByPage(int pc, int ps);

	LinkMan findById(Integer lkm_id);

	void update(LinkMan form);

	void delete(LinkMan linkMan);

	int findCountByCriteria(LinkMan linkMan);

	List<LinkMan> findByPageAndCriteria(LinkMan linkMan, int pc, int ps);

	List<LinkMan> findByMoreCondition(LinkMan form);
}
