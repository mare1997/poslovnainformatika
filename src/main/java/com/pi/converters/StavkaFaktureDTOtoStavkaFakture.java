package com.pi.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.pi.dto.StavkaFaktureDTO;
import com.pi.model.Faktura;
import com.pi.model.StavkaFakture;
import com.pi.service.FakturaService;

public class StavkaFaktureDTOtoStavkaFakture implements Converter<StavkaFaktureDTO, StavkaFakture>{

	@Autowired
	private FakturaService fakturaService;
	
	@Override
	public StavkaFakture convert(StavkaFaktureDTO source){
		
		if(source == null) {
			return null;
		}
		
		StavkaFakture stavkaFakture = new StavkaFakture();
		
		stavkaFakture.setIdStavkeFakture(source.getIdStavkeFakture());
		stavkaFakture.setKolicina(source.getKolicina());
		stavkaFakture.setJedinicnaCena(source.getJedinicnaCena());
		stavkaFakture.setRabat(source.getRabat());
		stavkaFakture.setOsnovicaZaPDV(source.getOsnovicaZaPDV());
		stavkaFakture.setProcenatPDV(source.getProcenatPDV());
		stavkaFakture.setIznosPDV(source.getIznosPDV());
		stavkaFakture.setIznosStavke(source.getIznosStavke());
		
		if(source.getIdFakture() != 0){
			Faktura faktura = fakturaService.findOne(Long.valueOf(source.getIdFakture()));
			stavkaFakture.setFaktura(faktura);
		}
		
		return stavkaFakture;
	}
	
	public List<StavkaFakture> convert(List<StavkaFaktureDTO> source){
		
		List<StavkaFakture> sveStavke = new ArrayList<StavkaFakture>();
		for (StavkaFaktureDTO StavkaFaktureDTO : source) {
			sveStavke.add(convert(StavkaFaktureDTO));
		}
		
		return sveStavke;
	}
}
