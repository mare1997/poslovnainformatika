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
import com.pi.PoslovnaInformatika.repository.PreduzeceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
@Service
public class KupacService  implements KupacServiceInterface{

	@Autowired
	KupacRepository kp;
	@Autowired
	PreduzeceRepository pr;
	
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
	public Kupac save(Kupac Kupac) {
		// TODO Auto-generated method stub
		return kp.save(Kupac);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		kp.deleteById(id);
	}

	
}
