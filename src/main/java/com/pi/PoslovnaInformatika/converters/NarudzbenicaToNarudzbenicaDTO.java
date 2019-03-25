package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.Narudzbenica;

@Component
public class NarudzbenicaToNarudzbenicaDTO implements Converter<Narudzbenica, NarudzbenicaDTO>{

	@Override
	public NarudzbenicaDTO convert(Narudzbenica source) {
		
		if(source == null) {
			return null;
		}
		
		NarudzbenicaDTO narudzbenicaDTO = new NarudzbenicaDTO();
		
		narudzbenicaDTO.setIdNarudzbenice(source.getIdNarudzbenice());
		narudzbenicaDTO.setBrojNarudzbenice(source.getBrojNarudzbenice());
		narudzbenicaDTO.setDatumIzrade(source.getDatumIzrade());
		narudzbenicaDTO.setDatumIsporuke(source.getDatumIsporuke());
		narudzbenicaDTO.setAktivna(source.isAktivna());
		System.out.println(narudzbenicaDTO.isAktivna()==true);
		
		if(narudzbenicaDTO.isAktivna()==true){
			/*Faktura faktura = new Faktura();
			faktura.setId(0);
			narudzbenicaDTO.setFakturaRel(faktura);*/
			narudzbenicaDTO.setFakturaRel(0);
		}else{
			if(source.getFakturaRel()!=null){
				narudzbenicaDTO.setFakturaRel(source.getFakturaRel().getId());
						
			}
		}
		
		if(source.getUser()!=null) {
			narudzbenicaDTO.setUser(source.getUser().getId());
				
		}
		if(source.getPreduzece()!=null) {
			narudzbenicaDTO.setPreduzece(source.getPreduzece().getId());
				
		}
		if(source.getKupac()!=null){
			narudzbenicaDTO.setKupac(source.getKupac().getId());
				
		}
		narudzbenicaDTO.setObrisano(source.isObrisano());
		
				
		return narudzbenicaDTO;
	}
	
	public List<NarudzbenicaDTO> convert(List<Narudzbenica> source){
		
		ArrayList<NarudzbenicaDTO> narudzbeniceDTO = new ArrayList<NarudzbenicaDTO>();
		for (Narudzbenica narudzbenica: source) {
			narudzbeniceDTO.add(convert(narudzbenica));
		}
		
		return narudzbeniceDTO;
	}
}
