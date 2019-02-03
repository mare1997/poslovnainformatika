package com.pi.converters;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.pi.dto.OtpremnicaDTO;
import com.pi.model.Otpremnica;

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

