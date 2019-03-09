package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.converters.NarudzbenicaDTOtoNarudzbenica;
import com.pi.PoslovnaInformatika.converters.NarudzbenicaToNarudzbenicaDTO;
import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.repository.NarudzbenicaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.NarudzbenicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.OtpremnicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaNarudzbeniceServiceInterface;

@Service
public class NarudzbenicaService implements NarudzbenicaServiceInterface {

	@Autowired
	NarudzbenicaRepository narudzbenicaRepository;
	
	@Autowired
	private NarudzbenicaToNarudzbenicaDTO toNarudzbenicaDTO;
	
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
		Faktura f= fsi.getOne(narudzbenica.getFakturaRel().getId());
		Otpremnica o = osi.getOne(f.getOtpremnicaRel().getIdOtpremnice());
		narudzbenica.setObrisano(true);
		f.setObrisano(true);
		o.setObrisano(true);
		save(narudzbenica);
		fsi.save(f);
		osi.save(o);
	}


	


	
}
