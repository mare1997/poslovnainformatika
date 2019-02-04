package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi.PoslovnaInformatika.model.Kupac;
import com.pi.PoslovnaInformatika.repository.KupacRepository;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;

public class KupacService  implements KupacServiceInterface{

	@Autowired
	KupacRepository kp;
	
	@Override
	public List<Kupac> getAll() {
		// TODO Auto-generated method stub
		return kp.findAll();
	}

	@Override
	public Kupac getOne(Integer Kupac) {
		// TODO Auto-generated method stub
		return kp.getOne(Kupac);
	}

	@Override
	public Kupac save(Kupac Kupac) {
		// TODO Auto-generated method stub
		return kp.save(Kupac);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		kp.deleteById(id);
	}

	
}
