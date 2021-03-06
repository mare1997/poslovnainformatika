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

import com.pi.PoslovnaInformatika.converters.StavkaOtpremniceDTOtoStavkaOtpremnice;
import com.pi.PoslovnaInformatika.converters.StavkaOtpremniceToStavkaOtpremniceDTO;
import com.pi.PoslovnaInformatika.dto.StavkaOtpremniceDTO;
import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;
import com.pi.PoslovnaInformatika.service.StavkaOtpremniceService;

@RestController
@RequestMapping(value="api/otpremnice/stavkeOtpremnice")
@CrossOrigin("*")
public class StavkaOtpremniceController {
	
	@Autowired
	private StavkaOtpremniceService stavkaOtpremniceService;
	
	
	@Autowired
	private StavkaOtpremniceToStavkaOtpremniceDTO toStavkaOtpremniceDTO;
	
	@Autowired
	private StavkaOtpremniceDTOtoStavkaOtpremnice toStavkaOtpremnice;
	
	/* NEPOTREBNO
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StavkaOtpremniceDTO>> getAllStavkeOtpremnice(){
		List<StavkaOtpremnice> sveStavke = stavkaOtpremniceService.findAll();
		
		return new ResponseEntity<>(toStavkaOtpremniceDTO.convert(sveStavke), HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/{idOtpremnice}", method=RequestMethod.GET)
	public ResponseEntity<List<StavkaOtpremniceDTO>> getStavkeOtpremniceByOtpremnicaId(@PathVariable Integer idOtpremnice){
		List<StavkaOtpremnice> pronadjeneStavke = stavkaOtpremniceService.findByOtpremnicaId(idOtpremnice);
		
		return new ResponseEntity<>(toStavkaOtpremniceDTO.convert(pronadjeneStavke), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/{idOtpremnice}", method=RequestMethod.GET)
	public ResponseEntity<List<StavkaOtpremniceDTO>> getActiveStavkeOtpremniceByOtpremnicaId(@PathVariable Integer idOtpremnice){
		List<StavkaOtpremnice> pronadjeneStavke = stavkaOtpremniceService.findByOtpremnicaIdAndActive(idOtpremnice);
		
		return new ResponseEntity<>(toStavkaOtpremniceDTO.convert(pronadjeneStavke), HttpStatus.OK);
	}
	
	@RequestMapping(value="/stavka/{id}", method=RequestMethod.GET)
	public ResponseEntity<StavkaOtpremniceDTO> getStavkaOtpremniceById(@PathVariable Integer id){
		StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceService.getOne(id);
		if(stavkaOtpremnice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toStavkaOtpremniceDTO.convert(stavkaOtpremnice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/stavka/{id}", method=RequestMethod.GET)
	public ResponseEntity<StavkaOtpremniceDTO> getActiveStavkaOtpremniceById(@PathVariable Integer id){
		StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceService.getOne(id);
		if(stavkaOtpremnice==null || stavkaOtpremnice.isObrisano()==true){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toStavkaOtpremniceDTO.convert(stavkaOtpremnice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addStavkaOtpremnice", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addStavkaOtpremnice(@Validated @RequestBody StavkaOtpremniceDTO stavkaOtpremniceDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		StavkaOtpremnice novaStavkaOtpremnice = stavkaOtpremniceService.save(toStavkaOtpremnice.convert(stavkaOtpremniceDTO));
		return new ResponseEntity<>(toStavkaOtpremniceDTO.convert(novaStavkaOtpremnice), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editStavkaOtpremnice/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> editStavkaOtpremnice(@Validated @PathVariable Integer id, @RequestBody StavkaOtpremniceDTO editedStavkaOtpremniceDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceService.getOne(id);
		if(stavkaOtpremnice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		StavkaOtpremnice editedStavkaOtpremnice = toStavkaOtpremnice.convert(editedStavkaOtpremniceDTO);
		stavkaOtpremniceService.save(editedStavkaOtpremnice);
		return new ResponseEntity<>(toStavkaOtpremniceDTO.convert(editedStavkaOtpremnice), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteStavkaOtpremnice/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<StavkaOtpremniceDTO> hardDeleteStavkaOtpremniceById(@PathVariable Integer id){
		StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceService.getOne(id);
		if(stavkaOtpremnice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stavkaOtpremniceService.delete(id);
		return new ResponseEntity<StavkaOtpremniceDTO>(toStavkaOtpremniceDTO.convert(stavkaOtpremnice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteStavkaOtpremnice/{id}", method=RequestMethod.PUT)
	public ResponseEntity<StavkaOtpremniceDTO> softDeleteStavkaOtpremniceById(@PathVariable Integer id){
		StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceService.getOne(id);
		if(stavkaOtpremnice==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stavkaOtpremniceService.delete(stavkaOtpremnice);
		return new ResponseEntity<StavkaOtpremniceDTO>(toStavkaOtpremniceDTO.convert(stavkaOtpremnice), HttpStatus.OK);
	}
	
}

