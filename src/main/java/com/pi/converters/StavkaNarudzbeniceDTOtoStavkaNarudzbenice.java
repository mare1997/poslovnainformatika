package com.pi.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.pi.dto.StavkaNarudzbeniceDTO;
import com.pi.model.Narudzbenica;
import com.pi.model.StavkaNarudzbenice;
import com.pi.service.NarudzbenicaService;

public class StavkaNarudzbeniceDTOtoStavkaNarudzbenice implements Converter<StavkaNarudzbeniceDTO, StavkaNarudzbenice>{

	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Override
	public StavkaNarudzbenice convert(StavkaNarudzbeniceDTO source){
		
		if(source == null) {
			return null;
		}
		
		StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
		
		stavkaNarudzbenice.setIdStavkeNarudzbenice(source.getIdStavkeNarudzbenice());
		stavkaNarudzbenice.setKolicina(source.getKolicina());
		stavkaNarudzbenice.setNaziv(source.getNaziv());
		
		
		if(source.getIdNarudzbenice() != 0){
			Narudzbenica narudzbenica = narudzbenicaService.findOne(Long.valueOf(source.getIdNarudzbenice()));
			stavkaNarudzbenice.setNarudzbenica(narudzbenica);
		}
		
		return stavkaNarudzbenice;
	}
	
	public List<StavkaNarudzbenice> convert(List<StavkaNarudzbeniceDTO> source){
		
		List<StavkaNarudzbenice> sveStavke = new ArrayList<StavkaNarudzbenice>();
		for (StavkaNarudzbeniceDTO StavkaNarudzbeniceDTO : source) {
			sveStavke.add(convert(StavkaNarudzbeniceDTO));
		}
		
		return sveStavke;
	}
}

