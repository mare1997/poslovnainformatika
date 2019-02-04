package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.repository.RobaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.RobaServiceInterface;

public class RobaService implements RobaServiceInterface {

	@Autowired
	RobaRepository rr;
	
	@Override
	public List<Roba> getAll() {
		// TODO Auto-generated method stub
		return rr.findAll();
	}

	@Override
	public Roba getOne(Integer Roba) {
		// TODO Auto-generated method stub
		return rr.getOne(Roba);
	}

	@Override
	public Roba save(Roba Roba) {
		// TODO Auto-generated method stub
		return rr.save(Roba);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		rr.deleteById(id);
	}

}
