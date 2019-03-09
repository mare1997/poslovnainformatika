package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.StavkaCenovnikaDTO;
import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.model.StavkaCenovnika;

public interface StavkeCenovnikaServiceInterface {
	List<StavkaCenovnika> getAll();
	List<StavkaCenovnikaDTO> getAllByCenovnik(Cenovnik c);
	StavkaCenovnika getAllByCenovnikAndRoba(Cenovnik c,Roba r);
	StavkaCenovnika getOne(Integer StavkaCenovnika);
	StavkaCenovnika save(StavkaCenovnikaDTO scDTO);
	void remove(Integer id);
	void removeL(StavkaCenovnika sc);
}
