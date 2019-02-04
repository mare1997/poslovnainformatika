package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.repository.CenovnikRepository;
import com.pi.PoslovnaInformatika.service.interfaces.CenovnikServiceInterface;
@Service
public class CenovnikService implements CenovnikServiceInterface {
	
	@Autowired
	CenovnikRepository cr;
	@Override
	public List<Cenovnik> getAll() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Cenovnik getOne(Integer cenovnikid) {
		// TODO Auto-generated method stub
		return cr.getOne(cenovnikid);
	}

	@Override
	public Cenovnik save(Cenovnik cenovnik) {
		// TODO Auto-generated method stub
		return cr.save(cenovnik);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		cr.deleteById(id);
	}

}
