package com.pi.PoslovnaInformatika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pi.PoslovnaInformatika.dto.StopaPDVDTO;

import com.pi.PoslovnaInformatika.model.StopaPDV;
import com.pi.PoslovnaInformatika.service.interfaces.PDVServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StopaPDVServiceInterface;

@RestController
@RequestMapping(value = "api/stopapdv")
@CrossOrigin("*")
public class StopaPDVController {

	@Autowired
	private StopaPDVServiceInterface spsi;
	
	@Autowired
	private PDVServiceInterface psi;
	
	@GetMapping(value = "/getSpdvdeleteYes/{id}")
    public ResponseEntity<StopaPDVDTO> getS(@PathVariable("id") int id){
    	
    	StopaPDV stopa = spsi.getOne(id);
        if(stopa == null)
            return new ResponseEntity<StopaPDVDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<StopaPDVDTO>(new StopaPDVDTO(stopa),HttpStatus.OK);
    }
	@GetMapping(value = "/getSpdvdeleteNo/{id}")
    public ResponseEntity<StopaPDVDTO> get(@PathVariable("id") int id){
    	
    	StopaPDV stopa = spsi.getOne(id);
        if(stopa == null || stopa.isObrisano() == true)
            return new ResponseEntity<StopaPDVDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<StopaPDVDTO>(new StopaPDVDTO(stopa),HttpStatus.OK);
    }
	@PostMapping(value = "/add")
	public ResponseEntity<?> addStopa (@Validated @RequestBody StopaPDVDTO stopaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		StopaPDV stopa = new StopaPDV();
		stopa.setProcenat(stopaDTO.getProcenat());
		stopa.setDatum_vazenja(stopaDTO.getDatum_vazenja());
		stopa.setPdv(psi.getOne(stopaDTO.getPdv().getId()));
		spsi.save(stopa);
		
		return new ResponseEntity<StopaPDVDTO>(new StopaPDVDTO(stopa),HttpStatus.CREATED);
	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteStopa(@PathVariable("id") int id){
		
		StopaPDV stopa= spsi.getOne(id);
		if(stopa == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		stopa.setObrisano(true);
		spsi.save(stopa);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
