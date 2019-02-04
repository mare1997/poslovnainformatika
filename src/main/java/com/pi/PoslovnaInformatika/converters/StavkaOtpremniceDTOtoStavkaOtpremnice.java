package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.pi.PoslovnaInformatika.dto.StavkaOtpremniceDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;
import com.pi.PoslovnaInformatika.service.OtpremnicaService;

public class StavkaOtpremniceDTOtoStavkaOtpremnice implements Converter<StavkaOtpremniceDTO, StavkaOtpremnice>{

	@Autowired
	private OtpremnicaService otpremnicaService;
	
	@Override
	public StavkaOtpremnice convert(StavkaOtpremniceDTO source){
		
		if(source == null) {
			return null;
		}
		
		StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
		
		stavkaOtpremnice.setIdStavkeOtpremnice(source.getIdStavkeOtpremnice());
		stavkaOtpremnice.setRedniBroj(source.getRedniBroj());
		stavkaOtpremnice.setNaziv(source.getNaziv());
		stavkaOtpremnice.setCena(source.getCena());
		stavkaOtpremnice.setIsporucenaKolicina(source.getIsporucenaKolicina());
		stavkaOtpremnice.setNapomena(source.getNapomena());
		stavkaOtpremnice.setJedinicaMere(source.getJedinicaMere());
	
		if(source.getIdOtpremnice() != 0){
			Otpremnica otpremnica = otpremnicaService.findOne(Long.valueOf(source.getIdOtpremnice()));
			stavkaOtpremnice.setOtpremnica(otpremnica);
		}
		
		return stavkaOtpremnice;
	}
	
	public List<StavkaOtpremnice> convert(List<StavkaOtpremniceDTO> source){
		
		List<StavkaOtpremnice> sveStavke = new ArrayList<StavkaOtpremnice>();
		for (StavkaOtpremniceDTO StavkaOtpremniceDTO : source) {
			sveStavke.add(convert(StavkaOtpremniceDTO));
		}
		
		return sveStavke;
	}
}
