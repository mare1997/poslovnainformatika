package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.PDVDTO;
import com.pi.PoslovnaInformatika.model.PDV;

public interface PDVServiceInterface {
	List<PDV> getAll();
	List<PDVDTO> getAllWithDeleted();
	List<PDVDTO> getAllWithOutDeleted();
	PDV getOne(Integer PDV);
	PDV save(PDVDTO pdvDto);
	void remove(Integer id);
	void removeL(PDV pdv);
}
