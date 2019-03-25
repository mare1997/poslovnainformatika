package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.RobaDTO;
import com.pi.PoslovnaInformatika.dto.StavkaCenovnikaDTO;
import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.model.StavkaCenovnika;
import com.pi.PoslovnaInformatika.repository.GrupaRobeRepository;
import com.pi.PoslovnaInformatika.repository.RobaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.CenovnikServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.RobaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeCenovnikaServiceInterface;
@Service
public class RobaService implements RobaServiceInterface {

	@Autowired
	RobaRepository rr;
	
	@Autowired
	GrupaRobeRepository grr;
	
	@Autowired
	StavkeCenovnikaServiceInterface scsi;
	
	@Autowired
	CenovnikServiceInterface csi;
	
	@Override
	public List<Roba> getAll() {
		// TODO Auto-generated method stub
		return rr.findAll();
	}

	@Override
	public Roba getOne(Integer Roba) {
		// TODO Auto-generated method stub
		return rr.getOne(Roba);
	}

	@Override
	public Roba save(RobaDTO robaDTO) {
		Roba roba = new Roba();
		roba.setName(robaDTO.getName());
		roba.setJedninica_mere(robaDTO.getJedninica_mere());
		roba.setGrupa(grr.getOne(robaDTO.getGrupa().getId()));
		//treba da se doda da se dodaju cene ,pozovi servis nekako dobaci dto sc
		//promeni  stavke cenovnila dto
		Cenovnik cen =csi.getAktivan(robaDTO.getGrupa().getPreduzece().getId());
		rr.save(roba);
		StavkaCenovnika sc = scsi.saveR(robaDTO.getCena(), cen,roba);
		System.out.println("Cena "+ sc);
		rr.save(roba);
		return roba;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		rr.deleteById(id);
	}
	

	@Override
	public Roba getByName(String roba) {
		// TODO Auto-generated method stub
		return rr.getByName(roba);
	}

	@Override
	public List<RobaDTO> getAllWithOutDeleted(GrupaRobe gr) {
		List<Roba> roba = rr.findAll();  
		List<RobaDTO> robaDTo=new ArrayList<>();
	        for (Roba r:roba) {
	        	if(r.isObrisano() == false) {
	        		if(gr.getId() == r.getGrupa().getId()) {
	        			robaDTo.add(new RobaDTO(r));
	        		}
	        	}
	        }
		return robaDTo;
	}

	@Override
	public List<RobaDTO> getAllByNameGR(GrupaRobe gr) {
		List<Roba> roba= rr.findAll();
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
        	if(r.isObrisano() == false) {
        		if(gr.getId() == r.getGrupa().getId()) {
        			robaDTo.add(new RobaDTO(r));
        		}
        	}
        }
		return robaDTo;
	}

	@Override
	public List<RobaDTO> getAllSearch(String name,Integer grupaRobeId ) {
		List<Roba> roba = rr.findAll();  
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
        	if(r.isObrisano() == false && r.getName().contains(name) && r.getGrupa().getId()==grupaRobeId) {
        		
        			robaDTo.add(new RobaDTO(r));
        		
        	}
        }
		return robaDTo;
	}

	@Override
	public void removeL(Roba roba) {
		roba.setObrisano(true);
		rr.save(roba);
		
	}

}
