package com.pi.PoslovnaInformatika.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.PDVDTO;
import com.pi.PoslovnaInformatika.model.PDV;
import com.pi.PoslovnaInformatika.repository.PDVRepository;
import com.pi.PoslovnaInformatika.service.interfaces.PDVServiceInterface;
@Service
public class PDVService implements PDVServiceInterface{

	@Autowired
	PDVRepository pdvr;
	
	@Override
	public List<PDV> getAll() {
		// TODO Auto-generated method stub
		return pdvr.findAll();
	}

	@Override
	public PDV getOne(Integer PDV) {
		// TODO Auto-generated method stub
		return pdvr.getOne(PDV);
	}

	

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		pdvr.deleteById(id);
	}

	@Override
	public PDV save(PDVDTO pdvDto) {
		PDV pdv = new PDV();
		pdv.setName(pdvDto.getName());
		pdvr.save(pdv);
		return pdv;
	}

	@Override
	public void removeL(PDV pdv) {
		pdv.setObrisano(true);
		pdvr.save(pdv);
		
	}

	@Override
	public List<PDVDTO> getAllWithDeleted() {
		List<PDV> pdv=pdvr.findAll();
        List<PDVDTO> pdvDto=new ArrayList<>();
        for (PDV p:pdv) {
            pdvDto.add(new PDVDTO(p));
            
        }
		return pdvDto;
	}

	@Override
	public List<PDVDTO> getAllWithOutDeleted() {
		List<PDV> pdv=pdvr.findAll();
        List<PDVDTO> pdvDto=new ArrayList<>();
        for (PDV p:pdv) {
        	if(p.isObrisano() == false) {
        		pdvDto.add(new PDVDTO(p));
        	}
        }
		return pdvDto;
	}

}
