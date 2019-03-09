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


import com.pi.PoslovnaInformatika.dto.StavkaCenovnikaDTO;
import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.model.StavkaCenovnika;
import com.pi.PoslovnaInformatika.service.interfaces.CenovnikServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.RobaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeCenovnikaServiceInterface;

@RestController
@RequestMapping(value = "api/stavkacenovnika")
@CrossOrigin("*")
public class StavkaCenovnikaController {
	
	@Autowired
	private StavkeCenovnikaServiceInterface scsi;
	
	@Autowired
	private RobaServiceInterface rsi;
	
	@Autowired
	private CenovnikServiceInterface csi;
	
	@GetMapping(value = "/getSCdeleteYes/{id}")
    public ResponseEntity<StavkaCenovnikaDTO> getSC(@PathVariable("id") int id){
    	
    	StavkaCenovnika s = scsi.getOne(id);
        if(s == null)
            return new ResponseEntity<StavkaCenovnikaDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<StavkaCenovnikaDTO>(new StavkaCenovnikaDTO(s),HttpStatus.OK);
    }
	@GetMapping(value = "/getSCdeleteNo/{id}")
    public ResponseEntity<StavkaCenovnikaDTO> getSCc(@PathVariable("id") int id){
    	
    	StavkaCenovnika s = scsi.getOne(id);
        if(s == null || s.isObrisano() == true)
            return new ResponseEntity<StavkaCenovnikaDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<StavkaCenovnikaDTO>(new StavkaCenovnikaDTO(s),HttpStatus.OK);
    }
	//dodaj metodu gde daje cenu od Robe i Cenovnika
	@GetMapping(value = "/getCenaByRoba/{idRobe}/{idCenovnik}")
    public ResponseEntity<StavkaCenovnikaDTO> get(@PathVariable("idRobe") int idRobe,@PathVariable("idCenovnik") int idCenovnik){
    	Cenovnik c =csi.getOne(idCenovnik);
    	Roba r = rsi.getOne(idRobe);
    	StavkaCenovnika s = scsi.getAllByCenovnikAndRoba(c, r);
        if(s == null || s.isObrisano() == true)
            return new ResponseEntity<StavkaCenovnikaDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<StavkaCenovnikaDTO>(new StavkaCenovnikaDTO(s),HttpStatus.OK);
    }
	
	@RequestMapping(value="/all/{idcenovnika}", method = RequestMethod.GET)
    public ResponseEntity<List<StavkaCenovnikaDTO>> getSCs(@PathVariable("idcenovnika") int id){
    	Cenovnik c = csi.getOne(id);
    	if(c == null || c.isObrisano() == true) {
            return new ResponseEntity<List<StavkaCenovnikaDTO>>(HttpStatus.NOT_FOUND);
    	}
    	List<StavkaCenovnikaDTO> scDTo=scsi.getAllByCenovnik(c);
    	return new ResponseEntity<List<StavkaCenovnikaDTO>>(scDTo,HttpStatus.OK);
    }
	@PostMapping(value = "/add")
	public ResponseEntity<?> addSC(@Validated @RequestBody StavkaCenovnikaDTO scDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		StavkaCenovnika sc = scsi.save(scDTO);
		return new ResponseEntity<StavkaCenovnikaDTO>(new StavkaCenovnikaDTO(sc),HttpStatus.CREATED);
	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteRoba(@PathVariable("id") int id){
		
		StavkaCenovnika sc= scsi.getOne(id);
		if(sc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		scsi.removeL(sc);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
