package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pi.PoslovnaInformatika.dto.PoslovnaGodinaDTO;


import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.service.interfaces.PGPserviceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/poslovnagodina")
@CrossOrigin("*")
public class PoslovnaGodinaController {

	@Autowired
	private PGPserviceInterface psi;
	
	@Autowired
	private PreduzeceServiceInterface prsi;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<PoslovnaGodinaDTO> get(@PathVariable("id") int id){
    	
    	PoslovnaGodinaPreduzeca godina = psi.getOne(id);
        if(godina == null)
            return new ResponseEntity<PoslovnaGodinaDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(godina),HttpStatus.OK);
    }
	@GetMapping(value = "/all/{idPreduzeca}")
	public ResponseEntity<List<PoslovnaGodinaDTO>> getAll(@PathVariable("idPreduzeca") int id){
		List<PoslovnaGodinaDTO> pd = psi.getAllByPreduzece(id);
		
		
		return new ResponseEntity<List<PoslovnaGodinaDTO>>(pd,HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@Validated @RequestBody PoslovnaGodinaDTO pgDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		PoslovnaGodinaPreduzeca pg = psi.save(pgDTO);
		
		return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(pg),HttpStatus.CREATED);
	}
	@PutMapping(value = "/zakljucena/{id}")
	public ResponseEntity<?> edit(@Validated @RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO,Errors errors, @PathVariable("id")int id){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		PoslovnaGodinaPreduzeca godina = psi.zakljucana(id, poslovnaGodinaDTO);
		if(godina == null) {
			return new ResponseEntity<PoslovnaGodinaDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(godina),HttpStatus.OK);
	}
	
}
