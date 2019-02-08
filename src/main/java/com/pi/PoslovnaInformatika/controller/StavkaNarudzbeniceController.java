package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi.PoslovnaInformatika.converters.StavkaNarudzbeniceDTOtoStavkaNarudzbenice;
import com.pi.PoslovnaInformatika.converters.StavkaNarudzbeniceToStavkaNarudzbeniceDTO;
import com.pi.PoslovnaInformatika.dto.StavkaNarudzbeniceDTO;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.service.StavkaNarudzbeniceService;

@RestController
@RequestMapping(value="api/narudzbenice/stavkeNarudzbenice")
public class StavkaNarudzbeniceController {

	@Autowired
	private StavkaNarudzbeniceService stavkaNarudzbeniceService;
	
	
	@Autowired
	private StavkaNarudzbeniceToStavkaNarudzbeniceDTO toStavkaNarudzbeniceDTO;
	
	@Autowired
	private StavkaNarudzbeniceDTOtoStavkaNarudzbenice toStavkaNarudzbenice;
	
	/* NEPOTREBNO
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StavkaNarudzbeniceDTO>> getAllStavkeNarudzbenice(){
		List<StavkaNarudzbenice> sveStavke = stavkaNarudzbeniceService.findAll();
		
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(sveStavke), HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/{narudzbenicaId}", method=RequestMethod.GET)
	public ResponseEntity<List<StavkaNarudzbeniceDTO>> getStavkeNarudzbeniceByNarudzbenicaId(@PathVariable Integer idNarudzbenice){
		List<StavkaNarudzbenice> sveStavke = stavkaNarudzbeniceService.findAll();
		List<StavkaNarudzbenice> pronadjeneStavke = new ArrayList<StavkaNarudzbenice>();
		for(StavkaNarudzbenice stavka: sveStavke){
			if(stavka.getNarudzbenica().getIdNarudzbenice()==idNarudzbenice){
				pronadjeneStavke.add(stavka);
			}
		}
		
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(pronadjeneStavke), HttpStatus.OK);
	}
	
	@RequestMapping(value="/stavka/{id}", method=RequestMethod.GET)
	public ResponseEntity<StavkaNarudzbeniceDTO> getStavkaNarudzbeniceById(@PathVariable Integer id){
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(stavkaNarudzbenice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addStavkaNarudzbenice", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StavkaNarudzbeniceDTO> addStavkaNarudzbenice(@RequestBody StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO){
		
		
		StavkaNarudzbenice novaStavkaNarudzbenice = stavkaNarudzbeniceService.save(toStavkaNarudzbenice.convert(stavkaNarudzbeniceDTO));
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(novaStavkaNarudzbenice), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editStavkaNarudzbenice/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StavkaNarudzbeniceDTO> editStavkaNarudzbenice(@PathVariable Integer id, @RequestBody StavkaNarudzbeniceDTO editedStavkaNarudzbeniceDTO){
		
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		StavkaNarudzbenice editedStavkaNarudzbenice = toStavkaNarudzbenice.convert(editedStavkaNarudzbeniceDTO);
		stavkaNarudzbeniceService.save(editedStavkaNarudzbenice);
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(editedStavkaNarudzbenice), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteStavkaNarudzbenice/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<StavkaNarudzbeniceDTO> hardDeleteStavkaNarudzbeniceById(@PathVariable Integer id){
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stavkaNarudzbeniceService.delete(id);
		return new ResponseEntity<StavkaNarudzbeniceDTO>(toStavkaNarudzbeniceDTO.convert(stavkaNarudzbenice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteStavkaNarudzbenice/{id}", method=RequestMethod.PUT)
	public ResponseEntity<StavkaNarudzbeniceDTO> softDeleteStavkaNarudzbeniceById(@PathVariable Integer id){
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stavkaNarudzbenice.setObrisano(true);
		stavkaNarudzbeniceService.save(stavkaNarudzbenice);
		return new ResponseEntity<StavkaNarudzbeniceDTO>(toStavkaNarudzbeniceDTO.convert(stavkaNarudzbenice), HttpStatus.OK);
	}
	
}
