package team5.service;

import team5.model.User;

public interface SessionService {

	public boolean authenticate(User user);
	public void logout();
	public void setUser(User user);
	
	public boolean isNotLoggedIn();
	public boolean hasNoPermission();
	public boolean hasPermission();
}