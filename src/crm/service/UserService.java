package crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.UserDao;
import crm.entity.User;
@Transactional
public class UserService {
	@Resource(name="userDao")
	private UserDao userDao;

	public User login(User form) {
		return userDao.login(form);
	}
	//查询所有用户
	public List<User> findAll() {
		return userDao.findAll();
	}
	//根据id查询用户
	public User findById(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.findById(uid);
	}
	//修改密码
	public void update(User form) {
		userDao.update(form);
	}
	//删除用户
	public void delete(User user) {
		userDao.delete(user);
	}

}
