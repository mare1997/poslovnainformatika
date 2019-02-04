package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.Kupac;

public interface KupacServiceInterface{

	List<Kupac> getAll();
	Kupac getOne(Integer Kupac);
	Kupac save(Kupac Kupac);
	void remove(Integer id);
}
