package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;


import com.pi.PoslovnaInformatika.model.Mesto;

public interface MestoServiceInterface{
	List<Mesto> getAll();
	Mesto getOne(Integer Mesto);
	Mesto save(Mesto Mesto);
	void remove(Integer id);
}
