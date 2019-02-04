package com.pi.PoslovnaInformatika.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.service.KupacService;
import com.pi.PoslovnaInformatika.service.NarudzbenicaService;
import com.pi.PoslovnaInformatika.service.OtpremnicaService;
import com.pi.PoslovnaInformatika.service.StavkaFaktureService;
import com.pi.PoslovnaInformatika.service.UserService;





@Component
public class FakturaDTOtoFaktura implements Converter<FakturaDTO, Faktura>{
	
	@Autowired
	private StavkaFaktureService stavkeFaktureService;
	
	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Autowired
	private OtpremnicaService otpremnicaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KupacService kupacService;
	
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
