package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.service.FakturaService;

@Component
public class OtpremnicaDTOtoOtpremnica implements Converter<OtpremnicaDTO, Otpremnica>{
	
	/*@Autowired
	private StavkeOtpremnicaService stavkeOtpremnicaService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private KupacService kupacService;
	@Autowired
	private radnikService radnikService;
	@Autowired
	private preduzeceService preduzeceService;
	@Autowired*/
	
	private FakturaService fakturaService;
	@Override
	public Otpremnica convert(OtpremnicaDTO source){
		
		if(source == null) {
			return null;
		}
		
		Otpremnica otpremnica = new Otpremnica();
		
		otpremnica.setIdOtpremnice(source.getIdOtpremnice());
		otpremnica.setBrojOtpremnice(source.getBrojOtpremnice());
		otpremnica.setDatumOtpremnice(source.getDatumOtpremnice());
		otpremnica.setDatumIsporuke(source.getDatumIsporuke());
		otpremnica.setPrimljenaRoba(source.isPrimljenaRoba());
		
		/*otpremnica.setPrevoznik(prevoznikService.findOne(Long.valueOf(source.getPrevoznikId())));
		otpremnica.setKupac(kupacService.findOne(Long.valueOf(source.getKupacId())));
		otpremnica.setRadnik(radnikService.findOne(Long.valueOf(source.getRadnikId())));
		otpremnica.setPreduzece(preduzeceService.findOne(Long.valueOf(source.getPreduzeceId())));
		*/
		otpremnica.setFakturaRel(fakturaService.findOne(Long.valueOf(source.getFakturaRel())));

		
		
		/*List<StavkaOtpremnice> sveStavke = new ArrayList<StavkaOtpremnice>();
		sveStavke = stavkeOtpremniceService.findAll();
		for(StavkaOtpremnice stavkaOtpremnice: sveStavke){
			if(stavkaOtpremnice.getOtpremnica().getIdOtpremnice()==otpremnica.getIdOtpremnice()){
				sveStavke.add(stavkaOtpremnice);
			}
		}
		
		otpremnica.setStavkeOtpremnice(sveStavke);*/
		
		return otpremnica;
	}
	
	public List<Otpremnica> convert(List<OtpremnicaDTO> source){
		
		List<Otpremnica> otpremnice = new ArrayList<Otpremnica>();
		for (OtpremnicaDTO otpremnicaDTO : source) {
			otpremnice.add(convert(otpremnicaDTO));
		}
		
		return otpremnice;
	}
}

