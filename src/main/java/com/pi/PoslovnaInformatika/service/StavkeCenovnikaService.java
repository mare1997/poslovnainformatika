package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.StavkaCenovnika;
import com.pi.PoslovnaInformatika.repository.StavkeCenovnikaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeCenovnikaServiceInterface;
@Service
public class StavkeCenovnikaService implements StavkeCenovnikaServiceInterface {

	
	@Autowired
	StavkeCenovnikaRepository scr;
	
	@Override
	public List<StavkaCenovnika> getAll() {
		// TODO Auto-generated method stub
		return scr.findAll();
	}

	@Override
	public StavkaCenovnika getOne(Integer StavkaCenovnika) {
		// TODO Auto-generated method stub
		return scr.getOne(StavkaCenovnika);
	}

	@Override
	public StavkaCenovnika save(StavkaCenovnika StavkaCenovnika) {
		// TODO Auto-generated method stub
		return scr.save(StavkaCenovnika);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		scr.deleteById(id);
	}

}
