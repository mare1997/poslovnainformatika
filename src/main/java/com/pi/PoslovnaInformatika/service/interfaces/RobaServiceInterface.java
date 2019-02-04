package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;


import com.pi.PoslovnaInformatika.model.Roba;

public interface RobaServiceInterface{
	List<Roba> getAll();
	Roba getOne(Integer Roba);
	Roba save(Roba Roba);
	void remove(Integer id);
}
