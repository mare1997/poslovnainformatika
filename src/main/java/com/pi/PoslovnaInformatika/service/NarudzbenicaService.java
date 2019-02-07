package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO Auto-generated method stub
		return narudzbenicaRepository.getOne(id);
	}

	@Override
	public List<Narudzbenica> findAll() {
		// TODO Auto-generated method stub
		return narudzbenicaRepository.findAll();
	}

	@Override
	public Narudzbenica save(Narudzbenica narudzbenica) {
		// TODO Auto-generated method stub
		return narudzbenicaRepository.save(narudzbenica);
	}

	@Override
	public List<Narudzbenica> save(List<Narudzbenica> narudzbenice) {
		// TODO Auto-generated method stub
		return narudzbenicaRepository.saveAll(narudzbenice);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		narudzbenicaRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		// TODO Auto-generated method stub
		narudzbenicaRepository.delete((Narudzbenica) ids);
	}

	
}
