package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;



public interface PGPserviceInterface {
	List<PoslovnaGodinaPreduzeca> getAll();
	PoslovnaGodinaPreduzeca getOne(Integer pgp);
	PoslovnaGodinaPreduzeca save(PoslovnaGodinaPreduzeca pgp);
	void remove(Integer id);
}
