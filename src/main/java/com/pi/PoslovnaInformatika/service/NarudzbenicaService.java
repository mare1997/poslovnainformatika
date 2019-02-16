package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.repository.NarudzbenicaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;

@Service
public class NarudzbenicaService implements NarudzbenicaServiceInterface {

	@Autowired
	NarudzbenicaRepository narudzbenicaRepository;
	@Override
	public Narudzbenica getOne(Integer id) {
		return narudzbenicaRepository.getOne(id);
	}


	@Override
	public Page<Narudzbenica> findAll(Pageable pageRequest) {
		return narudzbenicaRepository.findAll(pageRequest);
	}

	@Override
	public Narudzbenica save(Narudzbenica narudzbenica) {
		return narudzbenicaRepository.save(narudzbenica);
	}

	@Override
	public List<Narudzbenica> save(List<Narudzbenica> narudzbenice) {
		return narudzbenicaRepository.saveAll(narudzbenice);
	}

	@Override
	public void delete(Integer id) {
		narudzbenicaRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		narudzbenicaRepository.delete((Narudzbenica) ids);
	}




	
}
