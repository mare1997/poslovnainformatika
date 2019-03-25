package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.GrupaRobeDTO;
import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.repository.GrupaRobeRepository;
import com.pi.PoslovnaInformatika.repository.PDVRepository;
import com.pi.PoslovnaInformatika.repository.PreduzeceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.GrupaRobeServiceInterface;
@Service
public class GrupaRobeService implements  GrupaRobeServiceInterface {

	@Autowired
	GrupaRobeRepository crr;
	
	@Autowired
	PDVRepository pdvr;
	@Autowired
	PreduzeceRepository pr;
	
	@Override
	public List<GrupaRobe> getAll() {
		// TODO Auto-generated method stub
		return crr.findAll();
	}

	@Override
	public GrupaRobe getOne(Integer GrupaRobeid) {
		// TODO Auto-generated method stub
		return crr.getOne(GrupaRobeid);
	}

	@Override
	public GrupaRobe save(GrupaRobeDTO grupaDTO) {
		GrupaRobe grupa = new GrupaRobe();
		
		grupa.setName(grupaDTO.getName());
		grupa.setPdv(pdvr.getOne(grupaDTO.getPdv().getId()));
		grupa.setPreduzece(pr.getOne(grupaDTO.getPreduzece().getId()));
		grupa.setDatum_kreiranja(grupaDTO.getDatum_kreiranja());
		
		
		crr.save(grupa);
		return grupa;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		crr.deleteById(id);
	}

	@Override
	public GrupaRobe getByName(String name) {
		// TODO Auto-generated method stub
		return crr.getByName(name);
	}

	@Override
	public void removeL(GrupaRobe gr) {
		// TODO Auto-generated method stub
		gr.setObrisano(true);
		crr.save(gr);
	}

	@Override
	public List<GrupaRobeDTO> getAllWithDeleted(PoslovnaGodinaPreduzeca p, Preduzece pr) {
		List<GrupaRobe> grupa = crr.findAll();
		List<GrupaRobeDTO> grupaDto=new ArrayList<>();
        for (GrupaRobe r:grupa) {
        	if(r.getPreduzece().getId() == pr.getId() && p.getDatumPocetak().before(r.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(r.getDatum_kreiranja()))
        				grupaDto.add(new GrupaRobeDTO(r));
        		}else {
        			grupaDto.add(new GrupaRobeDTO(r));
        		}
        		
        	}
            
        }
		return grupaDto;
	}

	@Override
	public List<GrupaRobeDTO> getAllWithOutDeleted(PoslovnaGodinaPreduzeca p, Preduzece pr) {
		List<GrupaRobe> grupa = crr.findAll();
		List<GrupaRobeDTO> grupaDto=new ArrayList<>();
        for (GrupaRobe r:grupa) {
        	if(r.isObrisano() == false && r.getPreduzece().getId() == pr.getId() && p.getDatumPocetak().before(r.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(r.getDatum_kreiranja()))
        				grupaDto.add(new GrupaRobeDTO(r));
        		}else {
        			grupaDto.add(new GrupaRobeDTO(r));
        		}
        		
        	}
        }
		return grupaDto;
	}

}
