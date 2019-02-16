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

import com.pi.PoslovnaInformatika.dto.CenovnikDTO;

import com.pi.PoslovnaInformatika.model.Cenovnik;

import com.pi.PoslovnaInformatika.service.interfaces.CenovnikServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/cenovnik")
@CrossOrigin("*")
public class CenovnikController {
	
	@Autowired
	private CenovnikServiceInterface csi;
	
	@Autowired
	private PreduzeceServiceInterface psi;
	
	@GetMapping(value = "/getCenovnikdeleteYes/{id}")
    public ResponseEntity<CenovnikDTO> getC(@PathVariable("id") int id){
    	
    	Cenovnik c = csi.getOne(id);
        if(c == null)
            return new ResponseEntity<CenovnikDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getCenovnikdeleteYes/all", method = RequestMethod.GET)
    public ResponseEntity<List<CenovnikDTO>> getCenoviks(){
    	
    	List<Cenovnik> c=csi.getAll();
        List<CenovnikDTO> cDTo=new ArrayList<>();
        for (Cenovnik cc:c) {
        	cDTo.add(new CenovnikDTO(cc));
        	
        }
        return new ResponseEntity<List<CenovnikDTO>>(cDTo,HttpStatus.OK);
    }
	@GetMapping(value = "/getCenovnikdeleteNo/{id}")
    public ResponseEntity<CenovnikDTO> getCc(@PathVariable("id") int id){
    	
    	Cenovnik c = csi.getOne(id);
        if(c == null || c.isObrisano() == true)
            return new ResponseEntity<CenovnikDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getCenovnikdeleteNo/all", method = RequestMethod.GET)
    public ResponseEntity<List<CenovnikDTO>> getCenoviksc(){
    	
    	List<Cenovnik> c=csi.getAll();
        List<CenovnikDTO> cDTo=new ArrayList<>();
        for (Cenovnik cc:c) {
        	if(cc.isObrisano() == false) {
        		cDTo.add(new CenovnikDTO(cc));
        	}
        }
        return new ResponseEntity<List<CenovnikDTO>>(cDTo,HttpStatus.OK);
    }
	@PostMapping(value = "/add")
	public ResponseEntity<?> addCenovnik(@Validated @RequestBody CenovnikDTO cDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Cenovnik c = new Cenovnik();
		c.setName(cDTO.getName());
		c.setDatum_vazenja(cDTO.getDatum_vazenja());
		c.setPreduzece(psi.getOne(cDTO.getPreduzece().getId()));
		
		
		csi.save(c);
		
		return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteC(@PathVariable("id") int id){
		
		Cenovnik c= csi.getOne(id);
		if(c == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		c.setObrisano(true);
		csi.save(c);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
