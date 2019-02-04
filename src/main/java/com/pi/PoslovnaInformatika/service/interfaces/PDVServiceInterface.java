package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;


import com.pi.PoslovnaInformatika.model.PDV;

public interface PDVServiceInterface {
	List<PDV> getAll();
	PDV getOne(Integer PDV);
	PDV save(PDV PDV);
	void remove(Integer id);
}
