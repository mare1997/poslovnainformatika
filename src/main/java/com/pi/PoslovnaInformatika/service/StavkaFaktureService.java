package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.repository.StavkeFakturaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaFaktureServiceInterface;

@Service
public class StavkaFaktureService implements StavkaFaktureServiceInterface {

	@Autowired
	StavkeFakturaRepository stavkeRepository;
	@Override
	public StavkaFakture getOne(Integer id) {
		// TODO Auto-generated method stub
		return stavkeRepository.getOne(id);
	}

	@Override
	public List<StavkaFakture> findAll() {
		// TODO Auto-generated method stub
		return stavkeRepository.findAll();
	}

	@Override
	public StavkaFakture save(StavkaFakture stavkaFakture) {
		// TODO Auto-generated method stub
		return stavkeRepository.save(stavkaFakture);
	}

	@Override
	public List<StavkaFakture> save(List<StavkaFakture> stavke) {
		// TODO Auto-generated method stub
		return stavkeRepository.saveAll(stavke);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		stavkeRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		// TODO Auto-generated method stub
		stavkeRepository.delete((StavkaFakture) ids);
	}

	
}
