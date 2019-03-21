package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.PoslovnaGodinaDTO;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.repository.PGPrepository;
import com.pi.PoslovnaInformatika.service.interfaces.PGPserviceInterface;
@Service
public class PGPservice implements PGPserviceInterface {

	@Autowired
	PGPrepository pr;
	
	@Override
	public List<PoslovnaGodinaPreduzeca> getAll() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

	@Override
	public PoslovnaGodinaPreduzeca getOne(Integer pgp) {
		// TODO Auto-generated method stub
		return pr.getOne(pgp);
	}

	
	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		pr.deleteById(id);
	}

	@Override
	public List<PoslovnaGodinaDTO> getAllByPreduzece(int id) {
		List<PoslovnaGodinaDTO> pd = new ArrayList<>();
		List<PoslovnaGodinaPreduzeca> pgp = pr.findAll();
		
		for(PoslovnaGodinaPreduzeca p:pgp) {
			if(p.getPreduzece().getId() == id)
			pd.add(new PoslovnaGodinaDTO(p));
		}
		return pd;
	}

	@Override
	public PoslovnaGodinaPreduzeca save(PoslovnaGodinaDTO pgDTO) {
		PoslovnaGodinaPreduzeca pg = new PoslovnaGodinaPreduzeca();
		pg.setDatumPocetak(pgDTO.getDatumPocetak());
		pg.setGodina(pgDTO.getGodina());
		pr.save(pg);
		return pg;
	}

	@Override
	public PoslovnaGodinaPreduzeca zakljucana(int id, PoslovnaGodinaDTO poslovnaGodinaDTO) {
		PoslovnaGodinaPreduzeca godina = pr.getOne(id);
		godina.setZavrsena(true);
		godina.setDatumKraj(poslovnaGodinaDTO.getDatumKraj());
		pr.save(godina);
		return godina;
	}

}
