package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return otpremniceRepository.getOne(id);
	}

	@Override
	public Page<Otpremnica> findAll(Pageable pageRequest) {
		return otpremniceRepository.findAll(pageRequest);
	}

	@Override
	public Otpremnica save(Otpremnica otpremnica) {
		return otpremniceRepository.save(otpremnica);
	}

	@Override
	public List<Otpremnica> save(List<Otpremnica> otpremnice) {
		return otpremniceRepository.saveAll(otpremnice);
	}

	@Override
	public void delete(Integer id) {
		otpremniceRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		otpremniceRepository.delete((Otpremnica) ids);
	}

	

}
