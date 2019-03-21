package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;



import com.pi.PoslovnaInformatika.dto.PoslovnaGodinaDTO;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;



public interface PGPserviceInterface {
	List<PoslovnaGodinaPreduzeca> getAll();
	List<PoslovnaGodinaDTO> getAllByPreduzece(int id);
	PoslovnaGodinaPreduzeca getOne(Integer pgp);
	PoslovnaGodinaPreduzeca save(PoslovnaGodinaDTO pgp);
	PoslovnaGodinaPreduzeca zakljucana(int id,PoslovnaGodinaDTO pgp);
	void remove(Integer id);
}
