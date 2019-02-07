package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.repository.StavkeNarudzbeniceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaNarudzbeniceServiceInterface;

@Service
public class StavkaNarudzbeniceService implements StavkaNarudzbeniceServiceInterface {

	@Autowired
	StavkeNarudzbeniceRepository stavkeRepository;
	@Override
	public StavkaNarudzbenice getOne(Integer id) {
		// TODO Auto-generated method stub
		return stavkeRepository.getOne(id);
	}

	@Override
	public List<StavkaNarudzbenice> findAll() {
		// TODO Auto-generated method stub
		return stavkeRepository.findAll();
	}

	@Override
	public StavkaNarudzbenice save(StavkaNarudzbenice stavkaNarudzbenice) {
		// TODO Auto-generated method stub
		return stavkeRepository.save(stavkaNarudzbenice);
	}

	@Override
	public List<StavkaNarudzbenice> save(List<StavkaNarudzbenice> stavke) {
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
		stavkeRepository.delete((StavkaNarudzbenice) ids);
	}

	

}
