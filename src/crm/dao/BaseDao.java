package crm.dao;

import java.util.List;
/**
 * 让dao接口继承该接口，实现具体的泛型参数
 * @author user
 *
 * @param <T>
 */
public interface BaseDao<T> {
	//添加
	void add(T t);
	//修改
	void update(T t);
	//删除
	void delete(T t);
	//根据id查询
	T get(Integer id);
	//查询所有
	List<T> findAll();
}
