package crm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import crm.dao.UserDao;
import crm.entity.User;

public class UserDaoImpl implements UserDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("all")
	@Override
	public User login(User form) {
		String hql="from User where username=? and password=?";
		List<User> list=(List<User>) hibernateTemplate.find(hql, form.getUsername(),form.getPassword());
		if (list!=null&&list.size()!=0) {
			User user=list.get(0);
			return user;
		}
		return  null;
	}
	//查询所有用户
	@Override
	public List<User> findAll() {
		@SuppressWarnings("unchecked")
		List<User> list=(List<User>) hibernateTemplate.find("from User");
		if (list!=null&&list.size()!=0) {
			return list;
		}
		return null;
	}
	//根据id查询用户信息
	@Override
	public User findById(Integer uid) {
		return hibernateTemplate.get(User.class, uid);
	}
	//修改密码
	@Override
	public void update(User form) {
		hibernateTemplate.update(form);
	}
	//删除用户
	@Override
	public void delete(User user) {
		hibernateTemplate.delete(user);
	}
	
}
