package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.OtpremnicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaFaktureServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.UserServiceInterface;





@Component
public class FakturaDTOtoFaktura implements Converter<FakturaDTO, Faktura>{
	
	@Autowired
	private StavkaFaktureServiceInterface stavkeFaktureService;
	
	@Autowired
	private NarudzbenicaServiceInterface narudzbenicaService;
	
	@Autowired
	private OtpremnicaServiceInterface otpremnicaService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private KupacServiceInterface kupacService;
	
	@Autowired
	private PreduzeceServiceInterface preduzeceService;
	@Autowired
	private FakturaServiceInterface FakturaService;
	
	@Override
	public Faktura convert(FakturaDTO source){
		
		if(source == null) {
			return null;
		}
		Faktura faktura=new Faktura();
		
		
		/*
		if(FakturaService.getOne(source.getIdFakture()) != null) {
			 faktura = FakturaService.getOne(source.getIdFakture());
		}else {
			 faktura = new Faktura();
		}*/
		System.out.println(faktura.toString());
		
		if(source.getIdFakture() != 0) {
			faktura = FakturaService.getOne(source.getIdFakture());
		}
		System.out.println(source.getBrojFakture());
		if(source.getBrojFakture()!=null ){
		faktura.setBrojFakture(source.getBrojFakture());
				
		}
		if(source.getDatumFakture()!=null){
			faktura.setDatumFakture(source.getDatumFakture());
				
		}
		if(source.getDatumValute()!=null){
			faktura.setDatumValute(source.getDatumValute());
				
		}
		System.out.println(source.getOsnovica());
		if(source.getOsnovica()!=null ){
			faktura.setOsnovica(source.getOsnovica());

		}
		if(source.getUkupanPDV()!=null){
			faktura.setUkupanPDV(source.getUkupanPDV());
				
		}
		if(source.getIznosZaPlacanje()!=null){
			faktura.setIznosZaPlacanje(source.getIznosZaPlacanje());
				
		}
		if(source.getStatusFakture()!=null){
			faktura.setStatusFakture(source.getStatusFakture());
				
		}
		if(source.getNarudzbeniceRel()!=0){
			faktura.setNarudzbenicaRel(narudzbenicaService.getOne(source.getNarudzbeniceRel()));
				
		}
		if(source.getOtpremnicaRel() != 0) {
			faktura.setOtpremnicaRel(otpremnicaService.getOne(source.getOtpremnicaRel()));
		}
		
		if(source.getUser()!=0){
			faktura.setUser(userService.getOne(source.getUser()));
				
		}
		faktura.setObrisano(source.isObrisano());
		if(source.getPreduzece()!=0){
			faktura.setPreduzece(preduzeceService.getOne(source.getPreduzece()));
				
		}
		
		if(source.getKupac()!=0){
			faktura.setKupac(kupacService.getOne(source.getKupac()));
			
		}
		
		
		
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
