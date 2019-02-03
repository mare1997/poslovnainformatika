package com.pi.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.dto.FakturaDTO;
import com.pi.model.Faktura;
import com.pi.model.StavkaFakture;
import com.pi.service.FakturaService;



@Component
public class FakturaDTOtoFaktura implements Converter<FakturaDTO, Faktura>{
	/*
	@Autowired
	private StavkeFakturaService stavkeFaktureService;
	*/
	@Override
	public Faktura convert(FakturaDTO source){
		
		if(source == null) {
			return null;
		}
		
		Faktura faktura = new Faktura();
		
		faktura.setId(source.getIdFakture());
		faktura.setBrojFakture(source.getBrojFakture());
		faktura.setDatumFakture(source.getDatumFakture());
		faktura.setDatumValute(source.getDatumValute());
		faktura.setOsnovica(source.getOsnovica());
		faktura.setUkupanPDV(source.getUkupanPDV());
		faktura.setIznosZaPlacanje(source.getIznosZaPlacanje());
		faktura.setStatusFakture(source.getStatusFakture());
		
		/*List<StavkaFakture> sveStavke = new ArrayList<StavkaFakture>();
		sveStavke = stavkeFaktureService.findAll();
		for(StavkaFakture stavkaFakture: sveStavke){
			if(stavkaFakture.getFaktura().getId()==faktura.getId()){
				sveStavke.add(stavkaFakture);
			}
		}
		faktura.setStavkeFakture(sveStavke);*/
		
		return faktura;
	}
	
	public List<Faktura> convert(List<FakturaDTO> source){
		
		List<Faktura> fakture = new ArrayList<Faktura>();
		for (FakturaDTO fakturaDTO : source) {
			fakture.add(convert(fakturaDTO));
		}
		
		return fakture;
	}
}
