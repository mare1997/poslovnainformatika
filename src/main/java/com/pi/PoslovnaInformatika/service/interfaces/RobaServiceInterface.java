package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.RobaDTO;
import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.model.Roba;

public interface RobaServiceInterface{
	List<Roba> getAll();
	List<RobaDTO> getAllWithOutDeleted(GrupaRobe gr);
	List<RobaDTO> getAllByNameGR(GrupaRobe gr);
	List<RobaDTO> getAllSearch(String name,Integer grupaRobeId );
	Roba getOne(Integer Roba);
	Roba save(RobaDTO robaDTO);
	void remove(Integer id);
	void removeL(Roba roba);
	Roba getByName(String roba);
}
