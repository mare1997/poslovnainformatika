package com.pi.PoslovnaInformatika.converters;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;

@Component
public class OtpremnicaToOtpremnicaDTO implements Converter<Otpremnica, OtpremnicaDTO> {

	@Override
	public OtpremnicaDTO convert(Otpremnica source) {
		
		if(source == null) {
			return null;
		}
		
		OtpremnicaDTO otpremnicaDTO = new OtpremnicaDTO();
		
		otpremnicaDTO.setIdOtpremnice(source.getIdOtpremnice());
		otpremnicaDTO.setBrojOtpremnice(source.getBrojOtpremnice());
		otpremnicaDTO.setDatumOtpremnice(source.getDatumOtpremnice());
		otpremnicaDTO.setDatumIsporuke(source.getDatumIsporuke());
		otpremnicaDTO.setPrimljenaRoba(source.isPrimljenaRoba());
		otpremnicaDTO.setPrevoznikId(source.getPrevoznik().getId());
		otpremnicaDTO.setKupacId(source.getKupac().getId());
		otpremnicaDTO.setUser(source.getUser().getId());
		otpremnicaDTO.setPreduzeceId(source.getPreduzece().getId());
		otpremnicaDTO.setFakturaRel(source.getFakturaRel().getId());
		otpremnicaDTO.setObrisano(source.isObrisano());
		return otpremnicaDTO;
	}
	
	public List<OtpremnicaDTO> convert(List<Otpremnica> source){
		
		ArrayList<OtpremnicaDTO> otpremniceDTO = new ArrayList<OtpremnicaDTO>();
		for (Otpremnica otpremnica: source) {
			otpremniceDTO.add(convert(otpremnica));
		}
		
		return otpremniceDTO;
	}

}

