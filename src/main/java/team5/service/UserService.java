package team5.service;

import team5.model.User;


public interface UserService extends IService<User>{

	public User findByUsername(String userName);
	boolean updateUser(User user);
}