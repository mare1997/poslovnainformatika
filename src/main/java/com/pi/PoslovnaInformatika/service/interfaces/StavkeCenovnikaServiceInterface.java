package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;


import com.pi.PoslovnaInformatika.model.StavkaCenovnika;

public interface StavkeCenovnikaServiceInterface {
	List<StavkaCenovnika> getAll();
	StavkaCenovnika getOne(Integer StavkaCenovnika);
	StavkaCenovnika save(StavkaCenovnika StavkaCenovnika);
	void remove(Integer id);
}
