package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/all",method=RequestMethod.GET,params={"page","size"})
	public ResponseEntity<List<FakturaDTO>> getFakture(@RequestParam("page") int page, @RequestParam("size") int size){
		Page<Faktura> fakturePage = fakturaService.findAll(PageRequest.of(page, size,Sort.by("datumFakture").descending()));
		if (page > fakturePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(fakturePage.getTotalPages()));
		
		return new ResponseEntity<>(toFakturaDTO.convert(fakturePage.getContent()),headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/all",method=RequestMethod.GET,params={"page","size"})
	public ResponseEntity<List<FakturaDTO>> getActiveFakture(@RequestParam("page") int page, @RequestParam("size") int size){
		Page<Faktura> fakturePage = fakturaService.findAll(PageRequest.of(page, size,Sort.by("datumFakture").descending()));
		if (page > fakturePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		List<Faktura> tempFakture = fakturePage.getContent(); 
		List<Faktura> activeFakture = new ArrayList<>();
		for (Faktura faktura : tempFakture){
			if (faktura.isObrisano()==false)
					activeFakture.add(faktura);
		}
		Page<Faktura> activeFakturePage = new PageImpl<>(activeFakture);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(fakturePage.getTotalPages()));
		
		return new ResponseEntity<>(toFakturaDTO.convert(activeFakturePage.getContent()), headers,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<FakturaDTO> getFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/{id}", method=RequestMethod.GET)
	public ResponseEntity<FakturaDTO> getActiveFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null || faktura.isObrisano()==true){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	@RequestMapping(value="/addFaktura", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addFaktura(@Validated @RequestBody FakturaDTO fakturaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		Faktura novaFaktura = fakturaService.save(toFaktura.convert(fakturaDTO));
		return new ResponseEntity<>(toFakturaDTO.convert(novaFaktura), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/editFaktura/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> editFaktura(@Validated @PathVariable Integer id, @RequestBody FakturaDTO editedFakturaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
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
