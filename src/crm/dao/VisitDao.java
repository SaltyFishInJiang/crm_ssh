package crm.dao;

import java.util.Date;
import java.util.List;

import crm.entity.Customer;
import crm.entity.Visit;

public interface VisitDao {

	void add(Visit form);

	int findCount();

	List<Visit> findByPage(int pc, int ps);

	List<Visit> findByMoreCondition(Visit form, Date startDate, Date endDate);

}
