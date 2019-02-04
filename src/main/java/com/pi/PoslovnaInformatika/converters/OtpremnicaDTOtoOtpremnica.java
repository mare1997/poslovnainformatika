package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;
import com.pi.PoslovnaInformatika.service.KupacService;
import com.pi.PoslovnaInformatika.service.PreduzeceService;
import com.pi.PoslovnaInformatika.service.PrevoznikService;
import com.pi.PoslovnaInformatika.service.StavkaOtpremniceService;
import com.pi.PoslovnaInformatika.service.UserService;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;



@Component
public class OtpremnicaDTOtoOtpremnica implements Converter<OtpremnicaDTO, Otpremnica>{
	
	@Autowired
	private StavkaOtpremniceService stavkeOtpremniceService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private KupacService kupacService;
	@Autowired
	private UserService userService;
	@Autowired
	private PreduzeceService preduzeceService;
	@Autowired
	
	private FakturaServiceInterface fakturaService;
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
		
		otpremnica.setPrevoznik(prevoznikService.getOne(source.getPrevoznikId()));
		otpremnica.setKupac(kupacService.getOne(source.getKupacId()));
		otpremnica.setUser(userService.getOne(source.getUser()));
		otpremnica.setPreduzece(preduzeceService.getOne(source.getPreduzeceId()));
		
		otpremnica.setFakturaRel(fakturaService.findOne(Long.valueOf(source.getFakturaRel())));

		
		
		List<StavkaOtpremnice> sveStavke = new ArrayList<StavkaOtpremnice>();
		sveStavke = stavkeOtpremniceService.findAll();
		for(StavkaOtpremnice stavkaOtpremnice: sveStavke){
			if(stavkaOtpremnice.getOtpremnica().getIdOtpremnice()==otpremnica.getIdOtpremnice()){
				sveStavke.add(stavkaOtpremnice);
			}
		}
		
		otpremnica.setStavkeOtpremnice(sveStavke);
		
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

