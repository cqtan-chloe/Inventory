package team5.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.model.User;
import team5.repo.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	public void save(User user) {
		userRepo.save(user);
	}
	
	@Override
	public User findById(Long id) {
		return userRepo.findById(id).get();
	}
	
	@Override
	public User findByUsername(String userName) {
		return userRepo.findByUserName(userName);
	}

	@Override
	public List<User> find() {
		List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public void deleteById(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User create() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
