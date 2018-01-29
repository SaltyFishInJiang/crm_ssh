package crm.dao;

import java.util.List;

import crm.entity.User;

public interface UserDao {

	public User login(User form);

	public List<User> findAll();

	public User findById(Integer uid);

	public void update(User form);

	public void delete(User user);

}
