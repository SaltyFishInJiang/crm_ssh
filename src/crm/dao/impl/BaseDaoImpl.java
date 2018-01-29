package crm.dao.impl;

import java.lang.reflect.*;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import crm.dao.BaseDao;
/**
 * dao接口实现类继承该类与dao接口，明确泛型参数。
 * 类中使用this.getHibernateTemplate()方法
 * 接口实现类配置bean时，必须注入sessionFactory ,而省去注入hibernateTemplate
 * @author user
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	@SuppressWarnings("rawtypes")
	private Class clazzType;

	//构造函数
	@SuppressWarnings("rawtypes")
	public BaseDaoImpl() {
		//1 获取当前运行对象的class
		//比如运行customerDao实现类，得到customerDao实现类class
		Class clazz = this.getClass();
		
		//2 获取运行类的父类的参数化类型
		Type type = clazz.getGenericSuperclass();
		
		//3 转换成子接口ParameterizedType
		ParameterizedType ptype = (ParameterizedType) type;
		
		//4 获取实际类型参数
		//比如 Map<key,value>
		Type[] types = ptype.getActualTypeArguments();
		
		//5 把Type变成class
		Class clazzParameter =  (Class) types[0];
		this.clazzType = clazzParameter;
	}

	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@SuppressWarnings("unchecked")
	public T get(Integer id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().get(clazzType, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) this.getHibernateTemplate().find("from "+clazzType.getSimpleName());
	}

}
