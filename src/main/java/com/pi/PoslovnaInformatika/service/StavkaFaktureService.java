package com.pi.PoslovnaInformatika.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.model.PDV;
import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.model.StopaPDV;
import com.pi.PoslovnaInformatika.repository.GrupaRobeRepository;
import com.pi.PoslovnaInformatika.repository.PDVRepository;
import com.pi.PoslovnaInformatika.repository.RobaRepository;
import com.pi.PoslovnaInformatika.repository.StavkeFakturaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaFaktureServiceInterface;

@Service
public class StavkaFaktureService implements StavkaFaktureServiceInterface {

	@Autowired
	StavkeFakturaRepository stavkeRepository;
	
	@Autowired
	PDVRepository pdvRepository;
	
	@Autowired
	RobaRepository robaR;
	
	@Autowired
	GrupaRobeRepository grR;
	
	@Override
	public StavkaFakture getOne(Integer id) {
		// TODO Auto-generated method stub
		return stavkeRepository.getOne(id);
	}

	@Override
	public List<StavkaFakture> findAll() {
		// TODO Auto-generated method stub
		return stavkeRepository.findAll();
	}

	
	@Override
	public List<StavkaFakture> findByFakturaId(Integer idFakture) {
		List<StavkaFakture> sveStavke = this.findAll();
		List<StavkaFakture> pronadjeneStavke = new ArrayList<StavkaFakture>();
		for(StavkaFakture stavka: sveStavke){
			if(stavka.getFaktura().getId()==idFakture){
				pronadjeneStavke.add(stavka);
			}
		}
		
		return pronadjeneStavke;
	}
	
	public List<StavkaFakture> findByFakturaIdAndActive(Integer idFakture) {
		List<StavkaFakture> sveStavke = this.findAll();
		List<StavkaFakture> pronadjeneStavke = new ArrayList<StavkaFakture>();
		for(StavkaFakture stavka: sveStavke){
			if(stavka.getFaktura().getId()==idFakture && stavka.isObrisano()==false){
				pronadjeneStavke.add(stavka);
			}
		}
		
		return pronadjeneStavke;
	}
	
	@Override
	public StavkaFakture save(StavkaFakture stavkaFakture) {
		
		/*Roba roba = robaR.getOne(stavkaFakture.getRoba().getId());
		GrupaRobe gRobe = grR.getOne(roba.getGrupa().getId());
		PDV pdv = pdvRepository.getOne(gRobe.getPdv().getId());*/
		int sfStopaPDV = 1;
		Set<StopaPDV> stope =stavkaFakture.getRoba().getGrupa().getPdv().getStope();
		for(StopaPDV s: stope){
			if(s.getDatum_vazenja().before(new Date(System.currentTimeMillis()))){
				sfStopaPDV=s.getProcenat();
				
			}
		}
		
		Long sfVrednost = stavkaFakture.getJedinicnaCena()*stavkaFakture.getKolicina();
		Long sfRabat = sfVrednost*stavkaFakture.getRabat()/100;
		Long sfOsnovicaPDV = stavkaFakture.getJedinicnaCena()-sfRabat;
		Long sfIznosPDV = sfOsnovicaPDV*sfStopaPDV/100;
		Long sfIznosStavke = sfVrednost-sfRabat+sfIznosPDV;
		stavkaFakture.setIznosPDV(sfIznosPDV);
		stavkaFakture.setIznosStavke(sfIznosStavke);
		stavkaFakture.setOsnovicaZaPDV(sfOsnovicaPDV);
		stavkaFakture.setProcenatPDV((long) sfStopaPDV);
		return stavkeRepository.save(stavkaFakture);
	}

	@Override
	public List<StavkaFakture> save(List<StavkaFakture> stavke) {
		// TODO Auto-generated method stub
		return stavkeRepository.saveAll(stavke);
	}
	
	@Override
	public void delete(StavkaFakture stavkaFakture) {
		stavkaFakture.setObrisano(true);
		this.save(stavkaFakture);
		
		return;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		stavkeRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		// TODO Auto-generated method stub
		stavkeRepository.delete((StavkaFakture) ids);
	}

	
}
