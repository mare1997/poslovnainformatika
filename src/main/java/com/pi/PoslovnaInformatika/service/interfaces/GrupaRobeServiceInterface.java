package com.pi.PoslovnaInformatika.service.interfaces;



import java.util.List;

import com.pi.PoslovnaInformatika.dto.GrupaRobeDTO;
import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;

public interface GrupaRobeServiceInterface{

	List<GrupaRobe> getAll();
	List<GrupaRobeDTO> getAllWithDeleted(PoslovnaGodinaPreduzeca p,Preduzece pr);
	List<GrupaRobeDTO> getAllWithOutDeleted(PoslovnaGodinaPreduzeca p,Preduzece pr);
	GrupaRobe getOne(Integer GrupaRobeid);
	GrupaRobe save(GrupaRobeDTO grupaDTO);
	void remove(Integer id);
	void removeL(GrupaRobe gr);
	GrupaRobe getByName(String name);
}
