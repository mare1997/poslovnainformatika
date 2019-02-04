package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.repository.GrupaRobeRepository;
import com.pi.PoslovnaInformatika.service.interfaces.GrupaRobeServiceInterface;

public class GrupaRobeService implements  GrupaRobeServiceInterface {

	@Autowired
	GrupaRobeRepository crr;
	
	@Override
	public List<GrupaRobe> getAll() {
		// TODO Auto-generated method stub
		return crr.findAll();
	}

	@Override
	public GrupaRobe getOne(Integer GrupaRobeid) {
		// TODO Auto-generated method stub
		return crr.getOne(GrupaRobeid);
	}

	@Override
	public GrupaRobe save(GrupaRobe GrupaRobe) {
		// TODO Auto-generated method stub
		return crr.save(GrupaRobe);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		crr.deleteById(id);
	}

}