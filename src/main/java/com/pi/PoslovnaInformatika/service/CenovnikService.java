package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.CenovnikDTO;
import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.repository.CenovnikRepository;
import com.pi.PoslovnaInformatika.repository.PreduzeceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.CenovnikServiceInterface;
@Service
public class CenovnikService implements CenovnikServiceInterface {
	
	@Autowired
	CenovnikRepository cr;
	@Autowired
	PreduzeceRepository pr;
	@Override
	public List<Cenovnik> getAll() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Cenovnik getOne(Integer cenovnikid) {
		// TODO Auto-generated method stub
		return cr.getOne(cenovnikid);
	}

	

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		cr.deleteById(id);
	}

	@Override
	public List<CenovnikDTO> getAllWithDeleted(PoslovnaGodinaPreduzeca p, Preduzece pr) {
		List<Cenovnik> lc=cr.findAll();
		List<CenovnikDTO> cDTo=new ArrayList<>();
        for (Cenovnik cc:lc) {
        	if(cc.getPreduzece().getId() == pr.getId() && p.getDatumPocetak().before(cc.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(cc.getDatum_kreiranja()))
        				cDTo.add(new CenovnikDTO(cc));
        		}else {
        			cDTo.add(new CenovnikDTO(cc));
        		}
        		
        	}
        	
        }
		return cDTo;
	}

	@Override
	public List<CenovnikDTO> getAllWithOutDeleted(PoslovnaGodinaPreduzeca p, Preduzece pr) {
		List<Cenovnik> lc=cr.findAll();
		List<CenovnikDTO> cDTo=new ArrayList<>();
        for (Cenovnik cc:lc) {
        	if(cc.isObrisano() == false && cc.getPreduzece().getId() == pr.getId() && p.getDatumPocetak().before(cc.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(cc.getDatum_kreiranja()))
        				cDTo.add(new CenovnikDTO(cc));
        		}else {
        			cDTo.add(new CenovnikDTO(cc));
        		}
        		
        	}
        	
        }
		
		return cDTo;
	}

	@Override
	public void removeL(Cenovnik c) {
		c.setObrisano(true);
		cr.save(c);
		
	}

	@Override
	public Cenovnik save(CenovnikDTO cDTO) {
		Cenovnik c = new Cenovnik();
		c.setName(cDTO.getName());
		c.setDatum_vazenja(cDTO.getDatum_vazenja());
		c.setDatum_kreiranja(cDTO.getDatum_kreiranja());
		c.setPreduzece(pr.getOne(cDTO.getPreduzece().getId()));
		c.setAktivan(cDTO.isAktivan());
		return c;
	}

	

	
}
