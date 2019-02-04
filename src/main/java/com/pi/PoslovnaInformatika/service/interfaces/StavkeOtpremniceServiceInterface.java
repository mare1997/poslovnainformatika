package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;

public interface StavkeOtpremniceServiceInterface {

	public StavkaOtpremnice findOne(Long id);
	
	public List<StavkaOtpremnice> findAll();

	public StavkaOtpremnice save(StavkaOtpremnice stavkaOtpremnice);

	public List<StavkaOtpremnice> save(List<StavkaOtpremnice> otpremnice);

	public StavkaOtpremnice delete(Long id);
	
	public void delete(List<Long> ids);
}
