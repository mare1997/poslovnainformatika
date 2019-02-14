package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.pi.PoslovnaInformatika.dto.PrevoznikDTO;

import com.pi.PoslovnaInformatika.model.Prevoznik;
import com.pi.PoslovnaInformatika.service.interfaces.PrevoznikServiceInterface;

@RestController
@RequestMapping(value = "api/prevoznik")
@CrossOrigin("*")
public class PrevoznikContoller {
	
	@Autowired
	private PrevoznikServiceInterface psi;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<PrevoznikDTO> getPrevoznik(@PathVariable("id") int id){
    	
		Prevoznik p = psi.getOne(id);
        if(p == null)
            return new ResponseEntity<PrevoznikDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<PrevoznikDTO>(new PrevoznikDTO(p),HttpStatus.OK);
    }
	@RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<PrevoznikDTO>> getPRevoznike(){
    	
    	List<Prevoznik> pr=psi.getAll();
        List<PrevoznikDTO> prevoznikDTO=new ArrayList<>();
        for (Prevoznik p:pr) {
        	prevoznikDTO.add(new PrevoznikDTO(p));
            
        }
        return new ResponseEntity<List<PrevoznikDTO>>(prevoznikDTO,HttpStatus.OK);
    }
	
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addPrevoznik(@Validated @RequestBody PrevoznikDTO prevoznikDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Prevoznik p = new Prevoznik();
		p.setName(prevoznikDTO.getName());
		
		psi.save(p);
		
		return new ResponseEntity<PrevoznikDTO>(new PrevoznikDTO(p),HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteKupac(@PathVariable("id") int id){
		
		Prevoznik p= psi.getOne(id);
		if(p == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		p.setObrisano(true);
		psi.save(p);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
