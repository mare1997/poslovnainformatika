package com.pi.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.pi.dto.StavkaNarudzbeniceDTO;
import com.pi.model.StavkaNarudzbenice;

public class StavkaNarudzbeniceToStavkaNarudzbeniceDTO implements Converter<StavkaNarudzbenice, StavkaNarudzbeniceDTO>{
	
	@Override
	public StavkaNarudzbeniceDTO convert(StavkaNarudzbenice source){
		
		if(source == null) {
			return null;
		}
		
		StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO = new StavkaNarudzbeniceDTO();
		
		stavkaNarudzbeniceDTO.setIdStavkeNarudzbenice(source.getIdStavkeNarudzbenice());
		stavkaNarudzbeniceDTO.setKolicina(source.getKolicina());
		stavkaNarudzbeniceDTO.setNaziv(source.getNaziv());
		
		if(source.getNarudzbenica() !=null){
			stavkaNarudzbeniceDTO.setIdNarudzbenice(source.getNarudzbenica().getIdNarudzbenice());
		}
		
		return stavkaNarudzbeniceDTO;
	}
	
	public List<StavkaNarudzbeniceDTO> convert(List<StavkaNarudzbenice> source){
		
		ArrayList<StavkaNarudzbeniceDTO> stavkeDTO = new ArrayList<StavkaNarudzbeniceDTO>();
		for (StavkaNarudzbenice stavkaNarudzbenice : source) {
			stavkeDTO.add(convert(stavkaNarudzbenice));
		}
		
		return stavkeDTO;
	}

}
