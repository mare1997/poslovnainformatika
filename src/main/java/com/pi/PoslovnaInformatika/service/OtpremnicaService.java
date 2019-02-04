package com.pi.PoslovnaInformatika.service;

import java.util.List;

import com.pi.PoslovnaInformatika.model.Otpremnica;

public interface OtpremnicaService {
	
	public Otpremnica findOne(Long id);
	
	public List<Otpremnica> findAll();

	public Otpremnica save(Otpremnica otpremnica);

	public List<Otpremnica> save(List<Otpremnica> otpremnice);

	public Otpremnica delete(Long id);
	
	public void delete(List<Long> ids);

}
