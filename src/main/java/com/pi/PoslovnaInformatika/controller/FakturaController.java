package com.pi.PoslovnaInformatika.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi.PoslovnaInformatika.converters.FakturaDTOtoFaktura;
import com.pi.PoslovnaInformatika.converters.FakturaToFakturaDTO;
import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.service.FakturaService;

@RestController
@RequestMapping(value="api/fakture")
@CrossOrigin("*")
public class FakturaController {

	@Autowired
	private FakturaService fakturaService;
	
	
	@Autowired
	private FakturaToFakturaDTO toFakturaDTO;
	
	@Autowired
	private FakturaDTOtoFaktura toFaktura;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<FakturaDTO>> getFakture(){
		List<Faktura> fakture = fakturaService.findAll();
		
		return new ResponseEntity<>(toFakturaDTO.convert(fakture), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<FakturaDTO> getFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFaktura", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<FakturaDTO> addFaktura(@RequestBody FakturaDTO fakturaDTO){
		
		
		Faktura novaFaktura = fakturaService.save(toFaktura.convert(fakturaDTO));
		return new ResponseEntity<>(toFakturaDTO.convert(novaFaktura), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editFaktura/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<FakturaDTO> editFaktura(@PathVariable Integer id, @RequestBody FakturaDTO editedFakturaDTO){
		
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Faktura editedFaktura = toFaktura.convert(editedFakturaDTO);
		fakturaService.save(editedFaktura);
		return new ResponseEntity<>(toFakturaDTO.convert(editedFaktura), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteFaktura/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<FakturaDTO> hardDeleteFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		fakturaService.delete(id);
		return new ResponseEntity<FakturaDTO>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteFaktura/{id}", method=RequestMethod.PUT)
	public ResponseEntity<FakturaDTO> softDeleteFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		faktura.setObrisano(true);
		fakturaService.save(faktura);
		return new ResponseEntity<FakturaDTO>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	
}
