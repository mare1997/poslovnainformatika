package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.StopaPDV;
import com.pi.PoslovnaInformatika.repository.StopaPDVRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StopaPDVServiceInterface;
@Service
public class StopaPDVService implements StopaPDVServiceInterface {
	
	@Autowired
	StopaPDVRepository sr;
	@Override
	public List<StopaPDV> getAll() {
		// TODO Auto-generated method stub
		return sr.findAll();
	}

	@Override
	public StopaPDV getOne(Integer StopaPDV) {
		// TODO Auto-generated method stub
		return sr.getOne(StopaPDV);
	}

	@Override
	public StopaPDV save(StopaPDV StopaPDV) {
		// TODO Auto-generated method stub
		return sr.save(StopaPDV);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		sr.deleteById(id);
	}

}
