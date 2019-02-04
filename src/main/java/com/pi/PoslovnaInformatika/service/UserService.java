package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.User;
import com.pi.PoslovnaInformatika.repository.UserRepository;
import com.pi.PoslovnaInformatika.service.interfaces.UserServiceInterface;
@Service
public class UserService implements UserServiceInterface {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.getByUsername(username);
	}

	@Override
	public User getByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.getByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getOne(Integer userId) {
		// TODO Auto-generated method stub
		return userRepository.getOne(userId);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

}
