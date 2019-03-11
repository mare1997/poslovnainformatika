package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.StopaPDVDTO;
import com.pi.PoslovnaInformatika.model.PDV;
import com.pi.PoslovnaInformatika.model.StopaPDV;
import com.pi.PoslovnaInformatika.repository.PDVRepository;
import com.pi.PoslovnaInformatika.repository.StopaPDVRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StopaPDVServiceInterface;
@Service
public class StopaPDVService implements StopaPDVServiceInterface {
	
	@Autowired
	StopaPDVRepository sr;
	@Autowired
	PDVRepository pr;
	
	@Override
	public List<StopaPDV> getAll() {
		// TODO Auto-generated method stub
		return sr.findAll();
	}

	@Override
	public StopaPDV getOne(Integer StopaPDV) {
		// TODO Auto-generated method stub
		return sr.getOne(StopaPDV);
	}

	@Override
	public StopaPDV save(StopaPDVDTO stopaDTO) {
		StopaPDV stopa = new StopaPDV();
		stopa.setProcenat(stopaDTO.getProcenat());
		stopa.setDatum_vazenja(stopaDTO.getDatum_vazenja());
		stopa.setPdv(pr.getOne(stopaDTO.getPdv().getId()));
		sr.save(stopa);
		return stopa;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		sr.deleteById(id);
	}

	@Override
	public List<StopaPDVDTO> getSPByPDV(PDV pdv) {
		List<StopaPDV> s= sr.findAll();
		List<StopaPDVDTO> ss=new ArrayList<>();
		for(StopaPDV st:s) {
			if(st.isObrisano() == false && pdv.getId() == st.getPdv().getId()) {
				ss.add(new StopaPDVDTO(st));
			}
		}
		
		return ss;
	}

	@Override
	public void removeL(StopaPDV stopa) {
		// TODO Auto-generated method stub
		stopa.setObrisano(true);
		sr.save(stopa);
	}

}
