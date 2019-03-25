package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.StavkaCenovnikaDTO;
import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.model.StavkaCenovnika;
import com.pi.PoslovnaInformatika.repository.CenovnikRepository;
import com.pi.PoslovnaInformatika.repository.StavkeCenovnikaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeCenovnikaServiceInterface;
@Service
public class StavkeCenovnikaService implements StavkeCenovnikaServiceInterface {

	
	@Autowired
	StavkeCenovnikaRepository scr;
	@Autowired
	CenovnikRepository cr;
	
	@Override
	public List<StavkaCenovnika> getAll() {
		// TODO Auto-generated method stub
		return scr.findAll();
	}

	@Override
	public StavkaCenovnika getOne(Integer StavkaCenovnika) {
		// TODO Auto-generated method stub
		return scr.getOne(StavkaCenovnika);
	}

	

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		scr.deleteById(id);
	}

	@Override
	public StavkaCenovnika save(StavkaCenovnikaDTO scDTO) {
		StavkaCenovnika sc = new StavkaCenovnika();
		sc.setCena(scDTO.getCena());
		sc.setCenovnik(cr.getOne(scDTO.getCenovnik().getId()));
		return sc;
	}

	@Override
	public void removeL(StavkaCenovnika sc) {
		sc.setObrisano(true);
		scr.save(sc);
		
	}

	@Override
	public List<StavkaCenovnikaDTO> getAllByCenovnik(Cenovnik c) {
		List<StavkaCenovnika> ss=scr.findAll();
        List<StavkaCenovnikaDTO> scDTo=new ArrayList<>();
        for (StavkaCenovnika s:ss) {
        	if(s.getCenovnik().getId() == c.getId() && s.isObrisano() == false) {
        		scDTo.add(new StavkaCenovnikaDTO(s));
        	}
        }
		return scDTo;
	}

	@Override
	public StavkaCenovnika getAllByCenovnikAndRoba(Cenovnik c, Roba r) {
		List<StavkaCenovnika> sc= scr.findAll();
		
		for(StavkaCenovnika s:sc) {
			if(s.getCenovnik().getId() == c.getId() && s.getRoba().getId() == r.getId()) {
				return s;
			}
		}
		return null;
	}

	@Override
	public StavkaCenovnika saveR(long cena,Cenovnik cenovnik,Roba roba) {
		StavkaCenovnika sc = new StavkaCenovnika();
		sc.setCena(cena);
		sc.setCenovnik(cenovnik);
		sc.setRoba(roba);
		return sc;
	}

}
