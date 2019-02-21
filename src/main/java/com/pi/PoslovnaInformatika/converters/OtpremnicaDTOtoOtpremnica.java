package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.OtpremnicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PrevoznikServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeOtpremniceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.UserServiceInterface;



@Component
public class OtpremnicaDTOtoOtpremnica implements Converter<OtpremnicaDTO, Otpremnica>{
	
	@Autowired
	private StavkeOtpremniceServiceInterface stavkeOtpremniceService;
	
	@Autowired
	private OtpremnicaServiceInterface otpremnicaService;
	@Autowired
	private PrevoznikServiceInterface prevoznikService;
	@Autowired
	private KupacServiceInterface kupacService;
	@Autowired
	private UserServiceInterface userService;
	@Autowired
	private PreduzeceServiceInterface preduzeceService;
	@Autowired
	
	private FakturaServiceInterface fakturaService;
	@Override
	public Otpremnica convert(OtpremnicaDTO source){
		
		if(source == null) {
			return null;
		}
		
		
		Otpremnica otpremnica = new Otpremnica();
		
		if(source.getIdOtpremnice() != 0) {
			otpremnica= otpremnicaService.getOne(source.getIdOtpremnice());
		}
		
		/*if(source.getFakturaRel()!=0) {
			otpremnica=otpremnicaService.getOne(source.getFakturaRel());
		}*/
		if(source.getBrojOtpremnice()!=null){
			otpremnica.setBrojOtpremnice(source.getBrojOtpremnice());
				
		}
		if(source.getDatumOtpremnice() != null){
			otpremnica.setDatumOtpremnice(source.getDatumOtpremnice());
				
		}
		if(source.getDatumIsporuke()!=null){
			otpremnica.setDatumIsporuke(source.getDatumIsporuke());
				
		}
		if(source.isPrimljenaRoba()){
			otpremnica.setPrimljenaRoba(source.isPrimljenaRoba());
				
		}
		if(source.getPrevoznikId()!=0){
			otpremnica.setPrevoznik(prevoznikService.getOne(source.getPrevoznikId()));
			
		}
		if(source.getKupacId()!=0){
			otpremnica.setKupac(kupacService.getOne(source.getKupacId()));
				
		}
		if(source.getUser()!=0){
			otpremnica.setUser(userService.getOne(source.getUser()));
				
		}
		if(source.getPreduzeceId()!=0){
			otpremnica.setPreduzece(preduzeceService.getOne(source.getPreduzeceId()));
				
		}
		if(source.getFakturaRel()!=0){
			otpremnica.setFakturaRel(fakturaService.getOne(source.getFakturaRel()));
				
		}
		otpremnica.setObrisano(source.isObrisano());
		
		
		
		
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

