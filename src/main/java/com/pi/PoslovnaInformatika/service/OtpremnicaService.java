package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.repository.OtpremnicaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.OtpremnicaServiceInterface;

@Service
public class OtpremnicaService implements OtpremnicaServiceInterface {

	@Autowired
	OtpremnicaRepository otpremniceRepository;
	@Override
	public Otpremnica getOne(Integer id) {
		// TODO Auto-generated method stub
		return otpremniceRepository.getOne(id);
	}

	@Override
	public List<Otpremnica> findAll() {
		// TODO Auto-generated method stub
		return otpremniceRepository.findAll();
	}

	@Override
	public Otpremnica save(Otpremnica otpremnica) {
		// TODO Auto-generated method stub
		return otpremniceRepository.save(otpremnica);
	}

	@Override
	public List<Otpremnica> save(List<Otpremnica> otpremnice) {
		// TODO Auto-generated method stub
		return otpremniceRepository.saveAll(otpremnice);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		otpremniceRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		// TODO Auto-generated method stub
		otpremniceRepository.delete((Otpremnica) ids);
	}

	

}
