package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;



import com.pi.PoslovnaInformatika.model.User;



public interface UserServiceInterface {

	User getByUsername(String username);
	User getByUsernameAndPassword(String username,String password);
	List<User> getAll();
	User getOne(Integer userId);
	User save(User user);
	void remove(Integer id);
	
}
