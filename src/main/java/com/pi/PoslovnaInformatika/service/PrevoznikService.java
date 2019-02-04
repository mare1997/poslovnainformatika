package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.model.Prevoznik;
import com.pi.PoslovnaInformatika.repository.PreduzeceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PrevoznikServiceInterface;
@Service
public class PrevoznikService implements PrevoznikServiceInterface {

	@Override
	public List<Prevoznik> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prevoznik getOne(Integer Prevoznik) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prevoznik save(Prevoznik Prevoznik) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		
	}

	

}
