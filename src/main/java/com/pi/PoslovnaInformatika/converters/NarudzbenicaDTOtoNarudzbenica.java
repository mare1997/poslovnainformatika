package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaNarudzbeniceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.UserServiceInterface;


@Component
public class NarudzbenicaDTOtoNarudzbenica implements Converter<NarudzbenicaDTO, Narudzbenica>{
	
	@Autowired
	private StavkaNarudzbeniceServiceInterface stavkeNarudzbeniceService;
	
	
	@Autowired
	private FakturaServiceInterface fakturaService;
	
	@Autowired
	private NarudzbenicaServiceInterface narudzbenicaService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private PreduzeceServiceInterface preduzeceService;
	
	@Autowired
	private KupacServiceInterface kupacService;
	
	@Override
	public Narudzbenica convert(NarudzbenicaDTO source){
		
		if(source == null) {
			return null;
		}
		
			Narudzbenica narudzbenica = new Narudzbenica();
			/*System.out.println(source.getIdNarudzbenice());
			System.out.println(narudzbenicaService.getOne(source.getIdNarudzbenice()));*/
			
			if(source.getIdNarudzbenice() != 0) {
				narudzbenica = narudzbenicaService.getOne(source.getIdNarudzbenice());
			}
			
			narudzbenica.setAktivna(source.isAktivna());
			
			/*if(narudzbenica.isAktivna()==true){
				narudzbenica.setFakturaRel(null);
			}else{
				narudzbenica.setFakturaRel(fakturaService.getOne(source.getFakturaRel()));
			}*/
			if(source.getBrojNarudzbenice()!=null){
				narudzbenica.setBrojNarudzbenice(source.getBrojNarudzbenice());
			}
			
			if(source.getDatumIzrade()!=null){
				narudzbenica.setDatumIzrade(source.getDatumIzrade());
			}
		
			if(source.getDatumIsporuke()!=null){
				narudzbenica.setDatumIsporuke(source.getDatumIsporuke());
			}
			
			if(source.getFakturaRel()!=0){
				narudzbenica.setFakturaRel(fakturaService.getOne(source.getFakturaRel()));
			}
			if(source.getUser()!=0){
				narudzbenica.setUser(userService.getOne(source.getUser()));
					
			}
			if(source.getPreduzece()!=0){
				narudzbenica.setPreduzece(preduzeceService.getOne(source.getPreduzece()));
					
			}
			if(source.getKupac()!=0){
				narudzbenica.setKupac(kupacService.getOne(source.getKupac()));
			}
			
			narudzbenica.setObrisano(source.isObrisano());
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

