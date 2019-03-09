package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;

public interface OtpremnicaServiceInterface {
	
	public Otpremnica getOne(Integer id);
	
	public Page<Otpremnica> findAll(Pageable pageRequest);

	public Otpremnica save(Otpremnica otpremnica);

	public List<Otpremnica> save(List<Otpremnica> otpremnice);

	public void delete(Integer id);
	
	public void delete(List<Integer> ids);

	Otpremnica edit(OtpremnicaDTO editedOtpremnicaDTO, Integer id);

	void softDelete(Integer id);

}
