package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.StavkaFaktureDTO;
import com.pi.PoslovnaInformatika.model.StavkaFakture;

@Component
public class StavkaFaktureToStavkaFaktureDTO implements Converter<StavkaFakture, StavkaFaktureDTO>{
	
	@Override
	public StavkaFaktureDTO convert(StavkaFakture source){
		
		if(source == null) {
			return null;
		}
		
		StavkaFaktureDTO stavkaFaktureDTO = new StavkaFaktureDTO();
		
		stavkaFaktureDTO.setIdStavkeFakture(source.getIdStavkeFakture());
		stavkaFaktureDTO.setKolicina(source.getKolicina());
		stavkaFaktureDTO.setJedinicnaCena(source.getJedinicnaCena());
		stavkaFaktureDTO.setRabat(source.getRabat());
		stavkaFaktureDTO.setOsnovicaZaPDV(source.getOsnovicaZaPDV());
		stavkaFaktureDTO.setProcenatPDV(source.getProcenatPDV());
		stavkaFaktureDTO.setIznosPDV(source.getIznosPDV());
		stavkaFaktureDTO.setIznosStavke(source.getIznosStavke());
		stavkaFaktureDTO.setJedinicaMere(source.getJedinicaMere());
		stavkaFaktureDTO.setRobaUslugaId(source.getRoba().getId());
		stavkaFaktureDTO.setObrisano(source.isObrisano());
		
		if(source.getFaktura() !=null){
			stavkaFaktureDTO.setIdFakture(source.getFaktura().getId());
		}
		
		return stavkaFaktureDTO;
	}
	
	public List<StavkaFaktureDTO> convert(List<StavkaFakture> source){
		
		ArrayList<StavkaFaktureDTO> stavkeDTO = new ArrayList<StavkaFaktureDTO>();
		for (StavkaFakture stavkaFakture : source) {
			stavkeDTO.add(convert(stavkaFakture));
		}
		
		return stavkeDTO;
	}

}
