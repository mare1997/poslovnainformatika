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


import com.pi.PoslovnaInformatika.dto.PDVDTO;


import com.pi.PoslovnaInformatika.model.PDV;

import com.pi.PoslovnaInformatika.service.interfaces.PDVServiceInterface;

@RestController
@RequestMapping(value = "api/pdv")
@CrossOrigin("*")
public class PDVContoller {

	@Autowired
	private PDVServiceInterface psi;
	
	@GetMapping(value = "/getPDVdeleteYes/{id}")
    public ResponseEntity<PDVDTO> getKupac(@PathVariable("id") int id){
    	
    	PDV pdv = psi.getOne(id);
        if(pdv == null)
            return new ResponseEntity<PDVDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<PDVDTO>(new PDVDTO(pdv),HttpStatus.OK);
    }
	@GetMapping(value = "/getPDVdeleteNo/{id}")
    public ResponseEntity<PDVDTO> get(@PathVariable("id") int id){
    	
    	PDV pdv = psi.getOne(id);
        if(pdv == null || pdv.isObrisano() == true)
            return new ResponseEntity<PDVDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<PDVDTO>(new PDVDTO(pdv),HttpStatus.OK);
    }
	@RequestMapping(value="/getPDVdeleteYes/all", method = RequestMethod.GET)
    public ResponseEntity<List<PDVDTO>> getPDVs(){
    	List<PDVDTO> pdvDto=psi.getAllWithDeleted();
        return new ResponseEntity<List<PDVDTO>>(pdvDto,HttpStatus.OK);
    }
	
	
	@RequestMapping(value="/getPDVdeleteNo/all", method = RequestMethod.GET)
    public ResponseEntity<List<PDVDTO>> getPDVsc(){
    	List<PDVDTO> pdvDto=psi.getAllWithOutDeleted();
        return new ResponseEntity<List<PDVDTO>>(pdvDto,HttpStatus.OK);
    }
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addPDV(@Validated @RequestBody PDVDTO pdvDto,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		PDV pdv = psi.save(pdvDto);
		return new ResponseEntity<PDVDTO>(new PDVDTO(pdv),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deletePDV(@PathVariable("id") int id){
		
		PDV pdv= psi.getOne(id);
		if(pdv == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		psi.removeL(pdv);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
