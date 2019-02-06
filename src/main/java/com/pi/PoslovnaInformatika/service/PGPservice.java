package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.repository.PGPrepository;
import com.pi.PoslovnaInformatika.service.interfaces.PGPserviceInterface;

public class PGPservice implements PGPserviceInterface {

	@Autowired
	PGPrepository pr;
	
	@Override
	public List<PoslovnaGodinaPreduzeca> getAll() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

	@Override
	public PoslovnaGodinaPreduzeca getOne(Integer pgp) {
		// TODO Auto-generated method stub
		return pr.getOne(pgp);
	}

	@Override
	public PoslovnaGodinaPreduzeca save(PoslovnaGodinaPreduzeca pgp) {
		// TODO Auto-generated method stub
		return pr.save(pgp);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		pr.deleteById(id);
	}

}
