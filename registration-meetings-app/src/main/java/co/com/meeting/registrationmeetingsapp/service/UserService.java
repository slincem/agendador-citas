package co.com.meeting.registrationmeetingsapp.service;

import java.util.List;

import co.com.meeting.registrationmeetingsapp.model.entity.User;

public interface UserService {
	
	public void registerUser(User user);
	
	public List<User> findAllUsers();
	
	public User findUser(String identification);
	
	public void update(User user);
	
	public String findUserType(String identification);
	
}
