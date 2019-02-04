package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;




import com.pi.PoslovnaInformatika.model.Prevoznik;

public interface PrevoznikServiceInterface{
	List<Prevoznik> getAll();
	Prevoznik getOne(Integer Prevoznik);
	Prevoznik save(Prevoznik Prevoznik);
	void remove(Integer id);
	
}
