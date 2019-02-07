package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;
import com.pi.PoslovnaInformatika.repository.StavkeOtpremniceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeOtpremniceServiceInterface;

@Service
public class StavkaOtpremniceService implements StavkeOtpremniceServiceInterface {

	@Autowired
	StavkeOtpremniceRepository stavkeRepository;
	@Override
	public StavkaOtpremnice getOne(Integer id) {
		// TODO Auto-generated method stub
		return stavkeRepository.getOne(id);
	}

	@Override
	public List<StavkaOtpremnice> findAll() {
		// TODO Auto-generated method stub
		return stavkeRepository.findAll();
	}

	@Override
	public StavkaOtpremnice save(StavkaOtpremnice stavkaOtpremnice) {
		// TODO Auto-generated method stub
		return stavkeRepository.save(stavkaOtpremnice);
	}

	@Override
	public List<StavkaOtpremnice> save(List<StavkaOtpremnice> otpremnice) {
		// TODO Auto-generated method stub
		return stavkeRepository.saveAll(otpremnice);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		stavkeRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		// TODO Auto-generated method stub
		stavkeRepository.delete((StavkaOtpremnice) ids);
	}

	

}
