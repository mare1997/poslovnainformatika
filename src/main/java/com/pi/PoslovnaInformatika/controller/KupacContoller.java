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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pi.PoslovnaInformatika.dto.KupacDTO;

import com.pi.PoslovnaInformatika.model.Kupac;

import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.MestoServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/kupac")
@CrossOrigin("*")
public class KupacContoller {
	
	@Autowired
	private KupacServiceInterface ksi;
	
	@Autowired
	private PreduzeceServiceInterface psi;
	
	@Autowired
	private MestoServiceInterface msi;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<KupacDTO> getKupac(@PathVariable("id") int id){
    	
    	Kupac kupac = ksi.getOne(id);
        if(kupac == null)
            return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<KupacDTO>(new KupacDTO(kupac),HttpStatus.OK);
    }
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addKupac(@Validated @RequestBody KupacDTO kupacDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Kupac kupac = new Kupac();
		kupac.setName(kupacDTO.getName());
		kupac.setAdresa(kupacDTO.getAdresa());
		kupac.setPib_jmbg(kupacDTO.getPib_jmbg());
		kupac.setMesto(msi.getOne(kupacDTO.getMesto().getId()));
		kupac.setPreduzece(psi.getOne(kupac.getPreduzece().getId()));
		
		
		
		ksi.save(kupac);
		
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac),HttpStatus.CREATED);
	}
	@PutMapping(value = "/edit/{id}")
	public ResponseEntity<?> editCategoy(@Validated @RequestBody KupacDTO kupacDTO,Errors errors, @PathVariable("id")int id){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Kupac kupac = ksi.getOne(id);
		if(kupac == null) {
			return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
		}
		kupac.setName(kupacDTO.getName());
		kupac.setAdresa(kupacDTO.getAdresa());
		kupac.setPib_jmbg(kupacDTO.getPib_jmbg());
		kupac.setMesto(msi.getOne(kupacDTO.getMesto().getId()));
		kupac.setPreduzece(psi.getOne(kupac.getPreduzece().getId()));
		
		
		ksi.save(kupac);
		
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac),HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteKupac(@PathVariable("id") int id){
		
		Kupac kupac= ksi.getOne(id);
		if(kupac == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		kupac.setObrisano(true);
		ksi.save(kupac);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
