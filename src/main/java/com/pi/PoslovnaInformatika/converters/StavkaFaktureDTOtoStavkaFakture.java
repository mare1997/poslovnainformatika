package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.pi.PoslovnaInformatika.dto.StavkaFaktureDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.RobaServiceInterface;


public class StavkaFaktureDTOtoStavkaFakture implements Converter<StavkaFaktureDTO, StavkaFakture>{

	@Autowired
	private FakturaServiceInterface fakturaService;
	
	@Autowired
	private RobaServiceInterface robaService;
	
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
		stavkaFakture.setRoba(robaService.getOne(source.getRobaUslugaId()));
		stavkaFakture.setJedinicaMere(source.getJedinicaMere());
		stavkaFakture.setObrisano(source.isObrisano());
		
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
