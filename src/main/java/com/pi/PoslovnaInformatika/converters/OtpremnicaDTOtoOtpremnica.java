package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;
import com.pi.PoslovnaInformatika.service.OtpremnicaService;

@Component
public class OtpremnicaDTOtoOtpremnica implements Converter<OtpremnicaDTO, Otpremnica>{
	
	/*@Autowired
	private StavkeOtpremnicaService stavkeOtpremnicaService;
	*/
	@Override
	public Otpremnica convert(OtpremnicaDTO source){
		
		if(source == null) {
			return null;
		}
		
		Otpremnica otpremnica = new Otpremnica();
		
		otpremnica.setIdOtpremnice(source.getIdOtpremnice());
		otpremnica.setBrojOtpremnice(source.getBrojOtpremnice());
		otpremnica.setDatumOtpremnice(source.getDatumOtpremnice());
		otpremnica.setDatumIsporuke(source.getDatumIsporuke());
		otpremnica.setPrimljenaRoba(source.isPrimljenaRoba());
		
		
		
		/*List<StavkaOtpremnice> sveStavke = new ArrayList<StavkaOtpremnice>();
		sveStavke = stavkeOtpremniceService.findAll();
		for(StavkaOtpremnice stavkaOtpremnice: sveStavke){
			if(stavkaOtpremnice.getOtpremnica().getIdOtpremnice()==otpremnica.getIdOtpremnice()){
				sveStavke.add(stavkaOtpremnice);
			}
		}
		
		otpremnica.setStavkeOtpremnice(sveStavke);*/
		
		return otpremnica;
	}
	
	public List<Otpremnica> convert(List<OtpremnicaDTO> source){
		
		List<Otpremnica> otpremnice = new ArrayList<Otpremnica>();
		for (OtpremnicaDTO otpremnicaDTO : source) {
			otpremnice.add(convert(otpremnicaDTO));
		}
		
		return otpremnice;
	}
}

