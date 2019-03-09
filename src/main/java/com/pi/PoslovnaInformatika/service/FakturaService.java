package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.converters.FakturaDTOtoFaktura;
import com.pi.PoslovnaInformatika.converters.FakturaToFakturaDTO;
import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.repository.FakturaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;

@Service
public class FakturaService implements FakturaServiceInterface {

	@Autowired
	FakturaRepository fakturaRepository;
	
	@Autowired
	private StavkaFaktureService sfService;
	
	@Autowired
	private FakturaService fakturaService;
	
	@Autowired
	private FakturaToFakturaDTO toFakturaDTO;
	
	@Autowired
	private FakturaDTOtoFaktura toFaktura;

	@Override
	public Faktura getOne(Integer id) {
		return fakturaRepository.getOne(id);
	}

	@Override
	public Page<Faktura> findAll(Pageable pageRequest) {
		
		return fakturaRepository.findAll(pageRequest);
	}

	@Override
	public Faktura save(Faktura faktura) {
		return fakturaRepository.save(faktura);
	}

	@Override
	public List<Faktura> save(List<Faktura> fakture) {
		return fakturaRepository.saveAll(fakture);
	}

	@Override
	public void delete(Integer id) {
		List<StavkaFakture> lsf = sfService.findAll();
		for(StavkaFakture sf: lsf){
			if(sf.getFaktura().getId()==id){
				sfService.delete(sf.getIdStavkeFakture());
			}
		}
		fakturaRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		fakturaRepository.delete((Faktura) ids);
	}

	@Override
	public Faktura edit(FakturaDTO editedFakturaDTO, Integer id) {
		editedFakturaDTO.setIdFakture(id);
		Faktura editedFaktura = toFaktura.convert(editedFakturaDTO);
		fakturaService.save(editedFaktura);
		return fakturaService.save(editedFaktura);
	}

	@Override
	public void softDelete(Integer id) {
		Faktura faktura = getOne(id);
		faktura.setObrisano(true);
		save(faktura);
		
	}
	

	

}
