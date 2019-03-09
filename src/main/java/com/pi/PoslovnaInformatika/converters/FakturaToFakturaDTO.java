package com.pi.PoslovnaInformatika.converters;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;

@Component
public class FakturaToFakturaDTO implements Converter<Faktura, FakturaDTO> {

	@Override
	public FakturaDTO convert(Faktura source) {
		
		if(source == null) {
			return null;
		}
		
		FakturaDTO fakturaDTO = new FakturaDTO();
		
		fakturaDTO.setIdFakture(source.getId());
		if(source.getBrojFakture()!=null) {
			fakturaDTO.setBrojFakture(source.getBrojFakture());
				
		}
		if(source.getDatumValute()!=null) {
			fakturaDTO.setDatumValute(source.getDatumValute());
				
		}
		if(source.getDatumFakture()!=null) {
			fakturaDTO.setDatumFakture(source.getDatumFakture());
				
		}
		if(source.getOsnovica()!=null) {
			fakturaDTO.setOsnovica(source.getOsnovica());
				
		}
		if(source.getUkupanPDV()!=null) {
			fakturaDTO.setUkupanPDV(source.getUkupanPDV());
				
		}
		if(source.getIznosZaPlacanje()!=null) {
			fakturaDTO.setIznosZaPlacanje(source.getIznosZaPlacanje());
				
		}
		if(source.getStatusFakture()!=null) {
			fakturaDTO.setStatusFakture(source.getStatusFakture());
				
		}
		if(source.getNarudzbenicaRel()!=null) {
			fakturaDTO.setNarudzbeniceRel(source.getNarudzbenicaRel());
				
		}
		if(source.getOtpremnicaRel() != null) {
			fakturaDTO.setOtpremnicaRel(source.getOtpremnicaRel());
		}
		
		if(source.getKupac()!=null) {
			fakturaDTO.setKupac(source.getKupac());
				
		}
		if(source.getUser()!=null) {
			fakturaDTO.setUser(source.getUser());
				
		}
		if(source.getPreduzece()!=null) {
			fakturaDTO.setPreduzece(source.getPreduzece());
				
		}
		
		fakturaDTO.setObrisano(source.isObrisano());
		
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
