package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.pi.PoslovnaInformatika.dto.StavkaNarudzbeniceDTO;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;


public class StavkaNarudzbeniceDTOtoStavkaNarudzbenice implements Converter<StavkaNarudzbeniceDTO, StavkaNarudzbenice>{

	@Autowired
	private NarudzbenicaServiceInterface narudzbenicaService;
	
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

