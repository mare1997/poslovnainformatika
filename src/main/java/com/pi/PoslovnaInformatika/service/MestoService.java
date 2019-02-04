package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.Mesto;
import com.pi.PoslovnaInformatika.repository.MestoRepository;
import com.pi.PoslovnaInformatika.service.interfaces.MestoServiceInterface;
@Service
public class MestoService implements MestoServiceInterface{

	@Autowired
	MestoRepository mr;
	@Override
	public List<Mesto> getAll() {
		// TODO Auto-generated method stub
		return mr.findAll();
	}

	@Override
	public Mesto getOne(Integer Mesto) {
		// TODO Auto-generated method stub
		return mr.getOne(Mesto);
	}

	@Override
	public Mesto save(Mesto Mesto) {
		// TODO Auto-generated method stub
		return mr.save(Mesto);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		mr.deleteById(id);
	}
	
	
}
