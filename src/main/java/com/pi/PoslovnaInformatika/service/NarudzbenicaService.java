package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.converters.NarudzbenicaDTOtoNarudzbenica;
import com.pi.PoslovnaInformatika.converters.NarudzbenicaToNarudzbenicaDTO;
import com.pi.PoslovnaInformatika.dto.NarudzbenicaComboDTO;
import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.repository.FakturaRepository;
import com.pi.PoslovnaInformatika.repository.NarudzbenicaRepository;
import com.pi.PoslovnaInformatika.repository.OtpremnicaRepository;
import com.pi.PoslovnaInformatika.repository.StavkeNarudzbeniceRepository;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.OtpremnicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PrevoznikServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaNarudzbeniceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.UserServiceInterface;

@Service
public class NarudzbenicaService implements NarudzbenicaServiceInterface {

	@Autowired
	NarudzbenicaRepository narudzbenicaRepository;
	
	@Autowired
	FakturaRepository fakturaRepository;
	
	@Autowired
	OtpremnicaRepository otpremnicaRepository;
	
	@Autowired
	private NarudzbenicaToNarudzbenicaDTO toNarudzbenicaDTO;
	
	@Autowired
	private UserServiceInterface usi;
	
	@Autowired
	private PrevoznikServiceInterface psi;
	
	@Autowired
	private KupacServiceInterface ksi;
	
	@Autowired
	private NarudzbenicaDTOtoNarudzbenica toNarudzbenica;
	
	@Autowired
	private FakturaServiceInterface fsi;
	
	@Autowired
	private OtpremnicaServiceInterface osi;
	
	@Autowired
	private StavkaNarudzbeniceServiceInterface snsi;
	
	
	@Override
	public Narudzbenica getOne(Integer id) {
		return narudzbenicaRepository.getOne(id);
	}


	@Override
	public Page<Narudzbenica> findAll(Pageable pageRequest) {
		return narudzbenicaRepository.findAll(pageRequest);
	}

	@Override
	public Narudzbenica save(Narudzbenica narudzbenica) {
		return narudzbenicaRepository.save(narudzbenica);
	}

	
	@Override
	public List<Narudzbenica> save(List<Narudzbenica> narudzbenice) {
		return narudzbenicaRepository.saveAll(narudzbenice);
	}


	@Override
	public Narudzbenica saveCombo(NarudzbenicaComboDTO comboDTO) {
		Narudzbenica narudzbenica = new Narudzbenica();
		Faktura faktura = new Faktura();
		/*Kupac kupac = new Kupac();
		Prevoznik prevoznik = new Prevoznik();*/
		Otpremnica otpremnica = new Otpremnica();
		narudzbenica.setBrojNarudzbenice(comboDTO.getBrojNarudzbenice());
		narudzbenica.setUser(usi.getOne(comboDTO.getUserId()));
		narudzbenica.setDatumIzrade(comboDTO.getDatumIzrade());
		narudzbenica.setDatumIsporuke(comboDTO.getDatumIsporuke());
		narudzbenica.setStavkeNarudzbenice(comboDTO.getStavkeNarudzbenice());
		faktura.setDatumValute(comboDTO.getDatumValute());
		narudzbenica.setKupac(ksi.getOne(comboDTO.getKupacId()));
		otpremnica.setPrevoznik(psi.getOne(comboDTO.getPrevoznikId()));
		narudzbenicaRepository.save(narudzbenica);
		fakturaRepository.save(faktura);
		otpremnicaRepository.save(otpremnica);
		/*for(StavkaNarudzbenice stavka : comboDTO.getStavkeNarudzbenice()){
			snRepository.save(stavka);
		}*/
		snsi.save(comboDTO.getStavkeNarudzbenice());
		return narudzbenica;
	}
	
	@Override
	public void delete(Integer id) {
		List<StavkaNarudzbenice> s = snsi.findAll();
		for(StavkaNarudzbenice ss:s) {
			if(ss.getNarudzbenica().getIdNarudzbenice() == id) {
				snsi.delete(ss.getIdStavkeNarudzbenice());
			}
		}
		narudzbenicaRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		narudzbenicaRepository.delete((Narudzbenica) ids);
	}

	@Override
	public Narudzbenica edit(NarudzbenicaDTO editedNarudzbenicaDTO,Integer id) {
		System.out.println("Narudzbenica je :" + editedNarudzbenicaDTO.isAktivna());
		editedNarudzbenicaDTO.setIdNarudzbenice(id);
		Narudzbenica editedNarudzbenica = toNarudzbenica.convert(editedNarudzbenicaDTO);
		
		return narudzbenicaRepository.save(editedNarudzbenica);
		
		
	}


	@Override
	public void softDelete(Integer id) {
		Narudzbenica narudzbenica = getOne(id);
		Faktura f = new Faktura();
		Otpremnica o = new Otpremnica();
		if(fsi.getOne(narudzbenica.getFakturaRel().getId())!=null){
			f.setObrisano(true);
			fsi.save(f);
		}
		
		if(osi.getOne(f.getOtpremnicaRel().getIdOtpremnice())!=null){
			o.setObrisano(true);
			osi.save(o);
		}
		narudzbenica.setObrisano(true);
		
		
		save(narudzbenica);
		
	}




	


	
}
