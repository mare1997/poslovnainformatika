package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.StavkaNarudzbeniceDTO;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;

@Component
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
		stavkaNarudzbenice.setJedinicaMere(source.getJedinicaMere());
		stavkaNarudzbenice.setObrisano(source.isObrisano());
		
		if(source.getIdNarudzbenice() != 0){
			Narudzbenica narudzbenica = narudzbenicaService.getOne(source.getIdNarudzbenice());
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

