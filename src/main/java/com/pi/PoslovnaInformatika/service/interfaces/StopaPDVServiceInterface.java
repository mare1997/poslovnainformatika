package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;




import com.pi.PoslovnaInformatika.model.StopaPDV;


public interface StopaPDVServiceInterface {
	List<StopaPDV> getAll();
	StopaPDV getOne(Integer StopaPDV);
	StopaPDV save(StopaPDV StopaPDV);
	void remove(Integer id);
}
