package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.pi.PoslovnaInformatika.dto.StavkaOtpremniceDTO;
import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;

public class StavkaOtpremniceToStavkaOtpremniceDTO implements Converter<StavkaOtpremnice, StavkaOtpremniceDTO>{
	
	@Override
	public StavkaOtpremniceDTO convert(StavkaOtpremnice source){
		
		if(source == null) {
			return null;
		}
		
		StavkaOtpremniceDTO stavkaOtpremniceDTO = new StavkaOtpremniceDTO();
		
		stavkaOtpremniceDTO.setIdStavkeOtpremnice(source.getIdStavkeOtpremnice());
		stavkaOtpremniceDTO.setRedniBroj(source.getRedniBroj());
		stavkaOtpremniceDTO.setNaziv(source.getNaziv());
		stavkaOtpremniceDTO.setCena(source.getCena());
		stavkaOtpremniceDTO.setIsporucenaKolicina(source.getIsporucenaKolicina());
		stavkaOtpremniceDTO.setNapomena(source.getNapomena());
		stavkaOtpremniceDTO.setJedinicaMere(source.getJedinicaMere());
	
		if(source.getOtpremnica() !=null){
			stavkaOtpremniceDTO.setIdOtpremnice(source.getOtpremnica().getIdOtpremnice());
		}
		
		return stavkaOtpremniceDTO;
	}
	
	public List<StavkaOtpremniceDTO> convert(List<StavkaOtpremnice> source){
		
		ArrayList<StavkaOtpremniceDTO> stavkeDTO = new ArrayList<StavkaOtpremniceDTO>();
		for (StavkaOtpremnice stavkaOtpremnice : source) {
			stavkeDTO.add(convert(stavkaOtpremnice));
		}
		
		return stavkeDTO;
	}

}
