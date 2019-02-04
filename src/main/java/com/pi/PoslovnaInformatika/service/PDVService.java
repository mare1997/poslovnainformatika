package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.PDV;
import com.pi.PoslovnaInformatika.repository.PDVRepository;
import com.pi.PoslovnaInformatika.service.interfaces.PDVServiceInterface;
@Service
public class PDVService implements PDVServiceInterface{

	@Autowired
	PDVRepository pdvr;
	
	@Override
	public List<PDV> getAll() {
		// TODO Auto-generated method stub
		return pdvr.findAll();
	}

	@Override
	public PDV getOne(Integer PDV) {
		// TODO Auto-generated method stub
		return pdvr.getOne(PDV);
	}

	@Override
	public PDV save(PDV PDV) {
		// TODO Auto-generated method stub
		return pdvr.save(PDV);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		pdvr.deleteById(id);
	}

}
