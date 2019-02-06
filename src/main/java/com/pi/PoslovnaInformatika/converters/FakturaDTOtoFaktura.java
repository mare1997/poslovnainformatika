package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.OtpremnicaServiceInterface;
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
		faktura.setNarudzbenicaRel(narudzbenicaService.findOne(Long.valueOf(source.getNarudzbeniceRel())));
		faktura.setOtpremnicaRel(otpremnicaService.findOne(Long.valueOf(source.getOtpremnicaRel())));
		faktura.setUser(userService.getOne(source.getUser()));
		
		faktura.setKupac(kupacService.getOne(source.getKupac()));
		List<StavkaFakture> sveStavke = new ArrayList<StavkaFakture>();
		sveStavke = stavkeFaktureService.findAll();
		for(StavkaFakture stavkaFakture: sveStavke){
			if(stavkaFakture.getFaktura().getId()==faktura.getId()){
				sveStavke.add(stavkaFakture);
			}
		}
		faktura.setStavkeFakture(sveStavke);
		
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
