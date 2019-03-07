package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.PrevoznikDTO;
import com.pi.PoslovnaInformatika.model.Prevoznik;

public interface PrevoznikServiceInterface{
	List<Prevoznik> getAll();
	Prevoznik getOne(Integer Prevoznik);
	Prevoznik save(PrevoznikDTO Prevoznik);
	void remove(Integer id);
	void removeLogical(Prevoznik p);
	List<PrevoznikDTO> getAllDTO();
	
}
