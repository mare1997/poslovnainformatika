package com.pi.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.pi.dto.NarudzbenicaDTO;
import com.pi.model.Narudzbenica;


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
		narudzbenicaDTO.setPlacena(source.isPlacena());
				
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
