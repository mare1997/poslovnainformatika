package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
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

import com.pi.PoslovnaInformatika.converters.StavkaFaktureDTOtoStavkaFakture;
import com.pi.PoslovnaInformatika.converters.StavkaFaktureToStavkaFaktureDTO;
import com.pi.PoslovnaInformatika.dto.StavkaFaktureDTO;
import com.pi.PoslovnaInformatika.model.StavkaFakture;
import com.pi.PoslovnaInformatika.service.StavkaFaktureService;

@RestController
@RequestMapping(value="api/fakture/stavkeFakture")
@CrossOrigin("*")
public class StavkaFaktureController {

	@Autowired
	private StavkaFaktureService stavkaFaktureService;
	
	
	@Autowired
	private StavkaFaktureToStavkaFaktureDTO toStavkaFaktureDTO;
	
	@Autowired
	private StavkaFaktureDTOtoStavkaFakture toStavkaFakture;
	
	/* NEPOTREBNO
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StavkaFaktureDTO>> getAllStavkeFakture(){
		List<StavkaFakture> sveStavke = stavkaFaktureService.findAll();
		
		return new ResponseEntity<>(toStavkaFaktureDTO.convert(sveStavke), HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/{idFakture}", method=RequestMethod.GET)
	public ResponseEntity<List<StavkaFaktureDTO>> getStavkeFaktureByFakturaId(@PathVariable Integer idFakture){
		List<StavkaFakture> sveStavke = stavkaFaktureService.findAll();
		List<StavkaFakture> pronadjeneStavke = new ArrayList<StavkaFakture>();
		for(StavkaFakture stavka: sveStavke){
			if(stavka.getFaktura().getId()==idFakture){
				pronadjeneStavke.add(stavka);
			}
		}
		
		return new ResponseEntity<>(toStavkaFaktureDTO.convert(pronadjeneStavke), HttpStatus.OK);
	}
	
	@RequestMapping(value="/stavka/{id}", method=RequestMethod.GET)
	public ResponseEntity<StavkaFaktureDTO> getStavkaFaktureById(@PathVariable Integer id){
		StavkaFakture stavkaFakture = stavkaFaktureService.getOne(id);
		if(stavkaFakture==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toStavkaFaktureDTO.convert(stavkaFakture), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addStavkaFakture", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StavkaFaktureDTO> addStavkaFakture(@RequestBody StavkaFaktureDTO stavkaFaktureDTO){
		
		
		StavkaFakture novaStavkaFakture = stavkaFaktureService.save(toStavkaFakture.convert(stavkaFaktureDTO));
		return new ResponseEntity<>(toStavkaFaktureDTO.convert(novaStavkaFakture), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editStavkaFakture/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StavkaFaktureDTO> editStavkaFakture(@PathVariable Integer id, @RequestBody StavkaFaktureDTO editedStavkaFaktureDTO){
		
		StavkaFakture stavkaFakture = stavkaFaktureService.getOne(id);
		if(stavkaFakture==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		StavkaFakture editedStavkaFakture = toStavkaFakture.convert(editedStavkaFaktureDTO);
		stavkaFaktureService.save(editedStavkaFakture);
		return new ResponseEntity<>(toStavkaFaktureDTO.convert(editedStavkaFakture), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteStavkaFakture/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<StavkaFaktureDTO> hardDeleteStavkaFaktureById(@PathVariable Integer id){
		StavkaFakture stavkaFakture = stavkaFaktureService.getOne(id);
		if(stavkaFakture==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stavkaFaktureService.delete(id);
		return new ResponseEntity<StavkaFaktureDTO>(toStavkaFaktureDTO.convert(stavkaFakture), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteStavkaFakture/{id}", method=RequestMethod.PUT)
	public ResponseEntity<StavkaFaktureDTO> softDeleteStavkaFaktureById(@PathVariable Integer id){
		StavkaFakture stavkaFakture = stavkaFaktureService.getOne(id);
		if(stavkaFakture==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stavkaFakture.setObrisano(true);
		stavkaFaktureService.save(stavkaFakture);
		return new ResponseEntity<StavkaFaktureDTO>(toStavkaFaktureDTO.convert(stavkaFakture), HttpStatus.OK);
	}
}
