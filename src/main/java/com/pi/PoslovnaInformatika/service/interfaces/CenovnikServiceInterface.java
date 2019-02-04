package com.pi.PoslovnaInformatika.service.interfaces;



import java.util.List;


import com.pi.PoslovnaInformatika.model.Cenovnik;

public interface CenovnikServiceInterface{
	List<Cenovnik> getAll();
	Cenovnik getOne(Integer cenovnikid);
	Cenovnik save(Cenovnik cenovnik);
	void remove(Integer id);
}
