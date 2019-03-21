package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.KupacDTO;
import com.pi.PoslovnaInformatika.model.Kupac;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.repository.KupacRepository;
import com.pi.PoslovnaInformatika.repository.MestoRepository;
import com.pi.PoslovnaInformatika.repository.PreduzeceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
@Service
public class KupacService  implements KupacServiceInterface{

	@Autowired
	KupacRepository kp;
	@Autowired
	PreduzeceRepository pr;
	@Autowired
	MestoRepository mr;
	
	@Override
	public List<Kupac> getAll() {
		// TODO Auto-generated method stub
		return kp.findAll();
	}
	
	@Override
	public List<KupacDTO> getAllActiveDTO(PoslovnaGodinaPreduzeca p,Preduzece pr){
		List<Kupac> listaKupaca = kp.findAll();
		List<KupacDTO> listaKupacaDTO = new ArrayList<>();
		for (Kupac k:listaKupaca) {
        	if(k.isObrisano() == false && k.getPreduzece().getId() == pr.getId() && p.getDatumPocetak().before(k.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(k.getDatum_kreiranja()))
        				listaKupacaDTO.add(new KupacDTO(k));
        				
        		}else {
        			listaKupacaDTO.add(new KupacDTO(k));
        		}
        		
        	}
        }
		return listaKupacaDTO;
        
	}
	
	@Override
	public List<KupacDTO> getAllInactiveDTO(PoslovnaGodinaPreduzeca p,Preduzece pr){
		List<Kupac> listaKupaca = kp.findAll();
		List<KupacDTO> listaKupacaDTO = new ArrayList<>();
		for (Kupac k:listaKupaca) {
        	if(k.getPreduzece().getId() == pr.getId() && p.getDatumPocetak().before(k.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(k.getDatum_kreiranja()))
        				listaKupacaDTO.add(new KupacDTO(k));
        				
        		}else {
        			listaKupacaDTO.add(new KupacDTO(k));
        		}
        		
        	}
        }
		return listaKupacaDTO;
        
	}

	@Override
	public Kupac getOne(Integer Kupac) {
		// TODO Auto-generated method stub
		return kp.getOne(Kupac);
	}

	

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		kp.deleteById(id);
	}

	@Override
	public Kupac save(KupacDTO kupacDTO) {
		Kupac kupac = new Kupac();
		kupac.setName(kupacDTO.getName());
		kupac.setAdresa(kupacDTO.getAdresa());
		kupac.setPib_jmbg(kupacDTO.getPib_jmbg());
		kupac.setMesto(mr.getOne(kupacDTO.getMesto().getId()));
		kupac.setPreduzece(pr.getOne(kupac.getPreduzece().getId()));
		
		
		
		kp.save(kupac);
		return kupac;
	}
	

	@Override
	public Kupac edit(int id, KupacDTO kupacDTO) {
		Kupac kupac = kp.getOne(id);
		kupac.setName(kupacDTO.getName());
		kupac.setAdresa(kupacDTO.getAdresa());
		kupac.setPib_jmbg(kupacDTO.getPib_jmbg());
		kupac.setMesto(mr.getOne(kupacDTO.getMesto().getId()));
		kupac.setPreduzece(pr.getOne(kupac.getPreduzece().getId()));
		
		
		kp.save(kupac);
		return kupac;
	}

	@Override
	public void removeL(Kupac kupac) {
		kupac.setObrisano(true);
		kp.save(kupac);
		
	}

	
}
