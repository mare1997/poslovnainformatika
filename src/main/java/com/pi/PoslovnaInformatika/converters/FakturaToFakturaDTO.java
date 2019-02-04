package com.pi.PoslovnaInformatika.converters;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;


public class FakturaToFakturaDTO implements Converter<Faktura, FakturaDTO> {

	@Override
	public FakturaDTO convert(Faktura source) {
		
		if(source == null) {
			return null;
		}
		
		FakturaDTO fakturaDTO = new FakturaDTO();
		
		fakturaDTO.setIdFakture(source.getId());
		fakturaDTO.setBrojFakture(source.getBrojFakture());
		fakturaDTO.setDatumValute(source.getDatumValute());
		fakturaDTO.setDatumFakture(source.getDatumFakture());
		fakturaDTO.setOsnovica(source.getOsnovica());
		fakturaDTO.setUkupanPDV(source.getUkupanPDV());
		fakturaDTO.setIznosZaPlacanje(source.getIznosZaPlacanje());
		fakturaDTO.setStatusFakture(source.getStatusFakture());
		fakturaDTO.setNarudzbeniceRel(source.getNarudzbenicaRel().getIdNarudzbenice());
		fakturaDTO.setOtpremnicaRel(source.getOtpremnicaRel().getIdOtpremnice());
		fakturaDTO.setKupac(source.getKupac().getId());
		
		return fakturaDTO;
	}
	
	public List<FakturaDTO> convert(List<Faktura> source){
		
		ArrayList<FakturaDTO> faktureDTO = new ArrayList<FakturaDTO>();
		for (Faktura faktura: source) {
			faktureDTO.add(convert(faktura));
		}
		
		return faktureDTO;
	}

}
