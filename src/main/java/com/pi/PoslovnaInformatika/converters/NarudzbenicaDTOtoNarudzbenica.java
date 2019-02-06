package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaNarudzbeniceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.UserServiceInterface;


@Component
public class NarudzbenicaDTOtoNarudzbenica implements Converter<NarudzbenicaDTO, Narudzbenica>{
	
	@Autowired
	private StavkaNarudzbeniceServiceInterface stavkeNarudzbeniceService;
	
	
	@Autowired
	private FakturaServiceInterface fakturaService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Override
	public Narudzbenica convert(NarudzbenicaDTO source){
		
		if(source == null) {
			return null;
		}
		
		Narudzbenica narudzbenica = new Narudzbenica();
		
		narudzbenica.setIdNarudzbenice(source.getIdNarudzbenice());
		narudzbenica.setBrojNarudzbenice(source.getBrojNarudzbenice());
		narudzbenica.setDatumIzrade(source.getDatumIzrade());
		narudzbenica.setDatumIsporuke(source.getDatumIsporuke());
		narudzbenica.setPlacena(source.isPlacena());
		narudzbenica.setFakturaRel(fakturaService.findOne(Long.valueOf(source.getFakturaRel())));
		narudzbenica.setUser(userService.getOne(source.getUser()));
		
			
		List<StavkaNarudzbenice> sveStavke = new ArrayList<StavkaNarudzbenice>();
		sveStavke = stavkeNarudzbeniceService.findAll();
		for(StavkaNarudzbenice stavkaNarudzbenice: sveStavke){
			if(stavkaNarudzbenice.getNarudzbenica().getIdNarudzbenice()==narudzbenica.getIdNarudzbenice()){
				sveStavke.add(stavkaNarudzbenice);
			}
		}
		narudzbenica.setStavkeNarudzbenice(sveStavke);
		
		return narudzbenica;
	}
	
	public List<Narudzbenica> convert(List<NarudzbenicaDTO> source){
		
		List<Narudzbenica> narudzbenice = new ArrayList<Narudzbenica>();
		for (NarudzbenicaDTO narudzbenicaDTO : source) {
			narudzbenice.add(convert(narudzbenicaDTO));
		}
		
		return narudzbenice;
	}
}

