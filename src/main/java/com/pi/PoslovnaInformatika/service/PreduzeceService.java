package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.PreduzeceDTO;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.repository.PreduzeceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;
@Service
public class PreduzeceService implements PreduzeceServiceInterface {
	
	@Autowired 
	PreduzeceRepository pr;
	@Override
	public List<Preduzece> getAll() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}
	
	@Override
	public List<PreduzeceDTO> getAllDTO(){
		List<Preduzece> listPreduzeca = pr.findAll();
		List<PreduzeceDTO> listaPreduzecaDTO = new ArrayList<>();
		for (Preduzece p:listPreduzeca) {
        	if(p.isObrisano() == false) {
        		listaPreduzecaDTO.add(new PreduzeceDTO(p));
        	}
        }
		return listaPreduzecaDTO;
        
	}

	@Override
	public Preduzece getOne(Integer Preduzece) {
		// TODO Auto-generated method stub
		return pr.getOne(Preduzece);
	}

	@Override
	public Preduzece save(Preduzece Preduzece) {
		// TODO Auto-generated method stub
		return pr.save(Preduzece);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		pr.deleteById(id);
	}

	@Override
	public Preduzece getByName(String name) {
		// TODO Auto-generated method stub
		return pr.getByName(name);
	}

}
