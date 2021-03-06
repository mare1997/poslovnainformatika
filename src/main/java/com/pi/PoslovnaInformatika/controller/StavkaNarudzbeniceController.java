package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
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
	
	@RequestMapping(value="/{idNarudzbenice}", method=RequestMethod.GET)
	public ResponseEntity<List<StavkaNarudzbeniceDTO>> getStavkeNarudzbeniceByNarudzbenicaId(@PathVariable Integer idNarudzbenice){
		List<StavkaNarudzbenice> pronadjeneStavke = stavkaNarudzbeniceService.findByNarudzbenicaId(idNarudzbenice);
		
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(pronadjeneStavke), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/{idNarudzbenice}", method=RequestMethod.GET)
	public ResponseEntity<List<StavkaNarudzbeniceDTO>> getActiveStavkeNarudzbeniceByNarudzbenicaId(@PathVariable Integer idNarudzbenice){
		List<StavkaNarudzbenice> pronadjeneStavke = stavkaNarudzbeniceService.findByNarudzbenicaIdAndActive(idNarudzbenice);
		
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(pronadjeneStavke), HttpStatus.OK);
	}
	
	@RequestMapping(value="/stavka/{id}", method=RequestMethod.GET)
	public ResponseEntity<StavkaNarudzbeniceDTO> getActiveStavkaNarudzbeniceById(@PathVariable Integer id){
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(stavkaNarudzbenice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/stavka/{id}", method=RequestMethod.GET)
	public ResponseEntity<StavkaNarudzbeniceDTO> getStavkaNarudzbeniceById(@PathVariable Integer id){
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null || stavkaNarudzbenice.isObrisano()==true){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(stavkaNarudzbenice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addStavkaNarudzbenice", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addStavkaNarudzbenice(@Validated @RequestBody StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		StavkaNarudzbenice novaStavkaNarudzbenice = stavkaNarudzbeniceService.save(toStavkaNarudzbenice.convert(stavkaNarudzbeniceDTO));
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(novaStavkaNarudzbenice), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editStavkaNarudzbenice/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> editStavkaNarudzbenice(@Validated @PathVariable Integer id, @RequestBody StavkaNarudzbeniceDTO editedStavkaNarudzbeniceDTO,Errors errors){
		
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		StavkaNarudzbenice editedStavkaNarudzbenice = toStavkaNarudzbenice.convert(editedStavkaNarudzbeniceDTO);
		stavkaNarudzbeniceService.save(editedStavkaNarudzbenice);
		return new ResponseEntity<>(toStavkaNarudzbeniceDTO.convert(editedStavkaNarudzbenice), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteStavkaNarudzbenice/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> hardDeleteStavkaNarudzbeniceById(@PathVariable Integer id){
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		stavkaNarudzbeniceService.delete(id);
		return new ResponseEntity<StavkaNarudzbeniceDTO>(toStavkaNarudzbeniceDTO.convert(stavkaNarudzbenice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteStavkaNarudzbenice/{id}", method=RequestMethod.PUT)
	public ResponseEntity<StavkaNarudzbeniceDTO> softDeleteStavkaNarudzbeniceById(@PathVariable Integer id){
		System.out.println("Brisanjesss: "+ id);
		StavkaNarudzbenice stavkaNarudzbenice = stavkaNarudzbeniceService.getOne(id);
		if(stavkaNarudzbenice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stavkaNarudzbeniceService.delete(stavkaNarudzbenice);
	
		return new ResponseEntity<StavkaNarudzbeniceDTO>(toStavkaNarudzbeniceDTO.convert(stavkaNarudzbenice), HttpStatus.OK);
	}
	
}
