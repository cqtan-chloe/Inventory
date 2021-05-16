package team5.service;

import javax.transaction.Transactional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.model.RoleType;
import team5.model.User;
import team5.repo.UserRepo;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

	@Autowired
	UserRepo urepo;
	
	@Autowired
	HttpSession session;	
	
	public boolean authenticate(User user) {
		User dbuser = urepo.findByUserName(user.getUserName());
		if (dbuser==null) {
			return false;
		}else if(dbuser.getUserName().equals(user.getUserName()) && dbuser.getPassword().equals(user.getPassword())){
			return true;
		}else {
			return false;
		}
	}
	
	public void logout() {
		session.invalidate();
	}
	
	public void setUser(User user) {
		User u = urepo.findByUserName(user.getUserName());
		session.setAttribute("user", u);
	}
	
	public boolean isNotLoggedIn() {
		//User user = (User) session.getAttribute("user");
		User user = urepo.findByUserName("admin1");
		if (user == null)
			return true;
		else 
			return false;
	}
	
	public boolean hasNoPermission() {
//		User user = (User) session.getAttribute("user");
//		if (user.getRole() != RoleType.ADMIN)
//			return true;
//		else 
			return false;
	}
	
	public boolean hasPermission() {
//		User user = (User) session.getAttribute("user");
//		if (user.getRole() == RoleType.ADMIN)
			return true;
//		else 
//			return false;
	}
}

