package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.dto.StopaPDVDTO;
import com.pi.PoslovnaInformatika.model.PDV;
import com.pi.PoslovnaInformatika.model.StopaPDV;


public interface StopaPDVServiceInterface {
	List<StopaPDV> getAll();
	List<StopaPDVDTO> getSPByPDV(PDV pdv);
	StopaPDV getOne(Integer StopaPDV);
	StopaPDV save(StopaPDVDTO stopaDTO);
	void remove(Integer id);
	void removeL(StopaPDV sp);
}
