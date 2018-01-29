package crm.dao;

import java.util.List;
import java.util.Map;

import crm.entity.Customer;
import crm.entity.DictCustLevel;
import crm.entity.DictCustSource;

public interface CustomerDao extends BaseDao<Customer>{

//	void add(Customer form);

//	Customer findById(Integer cid);

//	void delete(Customer customer);

//	void update(Customer form);

	int findCount();

	List<Customer> findByPage(int pc, int ps);

	int findCountByCriteria(Customer customer);

	List<Customer> findByPageAndCriteria(Customer customer, int pc, int ps);

	List<Customer> findByMoreCondition(Customer form);

	List<DictCustLevel> findAllLevel();

	List<DictCustSource> findAllSource();

	List<Map<String, Object>> countLevel();

	List<Map<String, Object>> countSource();

//	List<Customer> findAll();

}
