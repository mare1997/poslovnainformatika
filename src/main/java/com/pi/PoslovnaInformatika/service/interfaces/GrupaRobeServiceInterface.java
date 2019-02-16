package com.pi.PoslovnaInformatika.service.interfaces;



import java.util.List;


import com.pi.PoslovnaInformatika.model.GrupaRobe;

public interface GrupaRobeServiceInterface{

	List<GrupaRobe> getAll();
	GrupaRobe getOne(Integer GrupaRobeid);
	GrupaRobe save(GrupaRobe GrupaRobe);
	void remove(Integer id);
	GrupaRobe getByName(String name);
}
