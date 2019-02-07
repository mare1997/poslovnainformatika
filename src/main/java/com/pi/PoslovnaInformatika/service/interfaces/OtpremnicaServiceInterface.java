package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.Otpremnica;

public interface OtpremnicaServiceInterface {
	
	public Otpremnica getOne(Integer id);
	
	public List<Otpremnica> findAll();

	public Otpremnica save(Otpremnica otpremnica);

	public List<Otpremnica> save(List<Otpremnica> otpremnice);

	public void delete(Integer id);
	
	public void delete(List<Integer> ids);

}
