package com.pi.PoslovnaInformatika.service.interfaces;



import java.util.List;

import com.pi.PoslovnaInformatika.dto.CenovnikDTO;
import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;

public interface CenovnikServiceInterface{
	List<Cenovnik> getAll();
	List<CenovnikDTO> getAllWithDeleted(PoslovnaGodinaPreduzeca p,Preduzece pr);
	List<CenovnikDTO> getAllWithOutDeleted(PoslovnaGodinaPreduzeca p,Preduzece pr);
	Cenovnik getOne(Integer cenovnikid);
	Cenovnik save(CenovnikDTO cDTO);
	void remove(Integer id);
	void removeL(Cenovnik c);
	
}
