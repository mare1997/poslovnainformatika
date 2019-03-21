package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.dto.MestoDTO;
import com.pi.PoslovnaInformatika.model.Mesto;
import com.pi.PoslovnaInformatika.repository.MestoRepository;
import com.pi.PoslovnaInformatika.service.interfaces.MestoServiceInterface;
@Service
public class MestoService implements MestoServiceInterface{

	@Autowired
	MestoRepository mr;
	@Override
	public List<Mesto> getAll() {
		// TODO Auto-generated method stub
		return mr.findAll();
	}

	@Override
	public Mesto getOne(Integer Mesto) {
		// TODO Auto-generated method stub
		return mr.getOne(Mesto);
	}

	@Override
	public Mesto save(MestoDTO mestoDTO) {
		Mesto mesto = new Mesto();
		mesto.setGrad(mestoDTO.getGrad());
		mesto.setPostanski_broj(mestoDTO.getPostanski_broj());
		return mesto;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		mr.deleteById(id);
	}

	@Override
	public Mesto edit(int id,MestoDTO mestoDTO) {
		Mesto mesto  = mr.getOne(id);
		if(mesto == null) {
			return null;
		}
		
		mesto.setGrad(mestoDTO.getGrad());
		mesto.setPostanski_broj(mestoDTO.getPostanski_broj());
		
		
		mr.save(mesto);
		return mesto;
	}

	@Override
	public void removeL(Mesto mesto) {
		mesto.setObrisano(true);
		mr.save(mesto);
		
	}
	
	
}
