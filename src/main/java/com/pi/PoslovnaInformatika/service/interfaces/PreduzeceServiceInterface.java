package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.PreduzeceDTO;
import com.pi.PoslovnaInformatika.model.Preduzece;

public interface PreduzeceServiceInterface  {
	List<Preduzece> getAll();
	Preduzece getOne(Integer Preduzece);
	Preduzece save(Preduzece Preduzece);
	void remove(Integer id);
	List<PreduzeceDTO> getAllDTO();
	Preduzece getByName(String name);	
}
