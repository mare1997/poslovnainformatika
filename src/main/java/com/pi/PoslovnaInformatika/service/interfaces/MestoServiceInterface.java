package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.MestoDTO;
import com.pi.PoslovnaInformatika.model.Mesto;

public interface MestoServiceInterface{
	List<Mesto> getAll();
	Mesto getOne(Integer Mesto);
	Mesto save(MestoDTO mestoDTO);
	Mesto edit(int id,MestoDTO mestoDTO);
	void remove(Integer id);
	void removeL(Mesto m);
}
