package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.PrevoznikDTO;
import com.pi.PoslovnaInformatika.model.Prevoznik;
import com.pi.PoslovnaInformatika.repository.PrevoznikRepository;
import com.pi.PoslovnaInformatika.service.interfaces.PrevoznikServiceInterface;
@Service
public class PrevoznikService implements PrevoznikServiceInterface {

	@Autowired
	PrevoznikRepository pr;
	
	@Override
	public List<Prevoznik> getAll() {
		// TODO Auto-generated method stub
		return pr.findAll() ;
	}
	
	@Override
	public List<PrevoznikDTO> getAllDTO(){
		List<Prevoznik> listPrevoznika = pr.findAll();
		List<PrevoznikDTO> listaPrevoznikaDTO = new ArrayList<>();
		for (Prevoznik p:listPrevoznika) {
        	if(p.isObrisano() == false) {
        		listaPrevoznikaDTO.add(new PrevoznikDTO(p));
        	}
        }
		return listaPrevoznikaDTO;
        
	}
	@Override
	public Prevoznik getOne(Integer Prevoznik) {
		// TODO Auto-generated method stub
		return pr.getOne(Prevoznik);
	}

	@Override
	public Prevoznik save(PrevoznikDTO prevoznik) {
		Prevoznik p = new Prevoznik();
		p.setName(prevoznik.getName());
		return pr.save(p);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		pr.deleteById(id);
	}

	@Override
	public void removeLogical(Prevoznik p){
		
		p.setObrisano(true);
		pr.save(p);
	}
	

}
