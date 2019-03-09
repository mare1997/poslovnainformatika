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
		if(source.getBrojOtpremnice()!=null) {
			otpremnicaDTO.setBrojOtpremnice(source.getBrojOtpremnice());
				
		}
		if(source.getDatumOtpremnice()!=null) {
			otpremnicaDTO.setDatumOtpremnice(source.getDatumOtpremnice());
				
		}
		if(source.getDatumIsporuke()!=null) {
			otpremnicaDTO.setDatumIsporuke(source.getDatumIsporuke());
				
		}
		otpremnicaDTO.setPrimljenaRoba(source.isPrimljenaRoba());
		if(source.getPrevoznik()!=null) {
			otpremnicaDTO.setPrevoznikId(source.getPrevoznik());
				
		}
		if(source.getKupac()!=null) {
			otpremnicaDTO.setKupacId(source.getKupac());
				
		}
		if(source.getUser()!=null) {
			otpremnicaDTO.setUser(source.getUser());
				
		}
		if(source.getPreduzece()!=null) {
			otpremnicaDTO.setPreduzeceId(source.getPreduzece());
				
		}
		if(source.getFakturaRel()!=null) {
			otpremnicaDTO.setFakturaRel(source.getFakturaRel());
				
		}
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

