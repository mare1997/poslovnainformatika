package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.KupacDTO;
import com.pi.PoslovnaInformatika.model.Kupac;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;


public interface KupacServiceInterface{

	List<Kupac> getAll();
	Kupac getOne(Integer Kupac);
	Kupac save(Kupac Kupac);
	void remove(Integer id);
	List<KupacDTO> getAllActiveDTO(PoslovnaGodinaPreduzeca p,Preduzece pr);
	List<KupacDTO> getAllInactiveDTO(PoslovnaGodinaPreduzeca p,Preduzece pr);
}
