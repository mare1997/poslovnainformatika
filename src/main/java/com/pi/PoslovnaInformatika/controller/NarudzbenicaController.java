package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.pi.PoslovnaInformatika.converters.NarudzbenicaDTOtoNarudzbenica;
import com.pi.PoslovnaInformatika.converters.NarudzbenicaToNarudzbenicaDTO;
import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.service.NarudzbenicaService;

@RestController
@RequestMapping(value="api/narudzbenice")
@CrossOrigin("*")
public class NarudzbenicaController {

	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	
	@Autowired
	private NarudzbenicaToNarudzbenicaDTO toNarudzbenicaDTO;
	
	@Autowired
	private NarudzbenicaDTOtoNarudzbenica toNarudzbenica;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<NarudzbenicaDTO>> getNarudzbenice(){
		List<Narudzbenica> narudzbenice = narudzbenicaService.findAll();
		Collections.sort(narudzbenice);
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(narudzbenice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active",method=RequestMethod.GET)
	public ResponseEntity<List<NarudzbenicaDTO>> getActiveNarudzbenica(){
		List<Narudzbenica> narudzbenice = narudzbenicaService.findAll();
		List<Narudzbenica> activeNarudzbenice = new ArrayList<>();
		for (Narudzbenica narudzbenica : narudzbenice){
			if (narudzbenica.isObrisano()==false)
					activeNarudzbenice.add(narudzbenica);
		}
		Collections.sort(activeNarudzbenice);
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(activeNarudzbenice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<NarudzbenicaDTO> getNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/{id}", method=RequestMethod.GET)
	public ResponseEntity<NarudzbenicaDTO> getActiveNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null || narudzbenica.isObrisano()== true){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addNarudzbenica", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addNarudzbenica(@Validated @RequestBody NarudzbenicaDTO narudzbenicaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		Narudzbenica novaNarudzbenica = narudzbenicaService.save(toNarudzbenica.convert(narudzbenicaDTO));
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(novaNarudzbenica), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editNarudzbenica/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> editNarudzbenica(@Validated @PathVariable Integer id, @RequestBody NarudzbenicaDTO editedNarudzbenicaDTO, Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Narudzbenica editedNarudzbenica = toNarudzbenica.convert(editedNarudzbenicaDTO);
		narudzbenicaService.save(editedNarudzbenica);
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(editedNarudzbenica), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteNarudzbenica/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<NarudzbenicaDTO> hardDeleteNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		narudzbenicaService.delete(id);
		return new ResponseEntity<NarudzbenicaDTO>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteNarudzbenica/{id}", method=RequestMethod.PUT)
	public ResponseEntity<NarudzbenicaDTO> softDeleteNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		narudzbenica.setObrisano(true);
		narudzbenicaService.save(narudzbenica);
		return new ResponseEntity<NarudzbenicaDTO>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
}
