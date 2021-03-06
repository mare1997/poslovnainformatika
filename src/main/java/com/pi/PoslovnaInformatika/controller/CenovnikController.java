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
import com.pi.PoslovnaInformatika.dto.GrupaRobeDTO;
import com.pi.PoslovnaInformatika.model.Cenovnik;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.service.interfaces.CenovnikServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PGPserviceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/cenovnik")
@CrossOrigin("*")
public class CenovnikController {
	
	@Autowired
	private CenovnikServiceInterface csi;
	
	@Autowired
	private PreduzeceServiceInterface psi;
	
	@Autowired
	private PGPserviceInterface pgsi;
	
	@GetMapping(value = "/getCenovnikdeleteYes/{id}/{idPreduzeca}/{idPG}")
    public ResponseEntity<CenovnikDTO> getC(@PathVariable("id") int id,@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
    	PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	Cenovnik c = csi.getOne(id);
        if(c == null || c.getPreduzece().getId() != idPreduzeca || c.getDatum_kreiranja().before(p.getDatumPocetak()))
            return new ResponseEntity<CenovnikDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getCenovnikdeleteYes/all/{idPreduzeca}/{idPG}", method = RequestMethod.GET)
    public ResponseEntity<List<CenovnikDTO>> getCenoviks(@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	Preduzece pr = psi.getOne(idPreduzeca);
        List<CenovnikDTO> cDTo= csi.getAllWithDeleted(p, pr);
        
        return new ResponseEntity<List<CenovnikDTO>>(cDTo,HttpStatus.OK);
    }
	@GetMapping(value = "/getCenovnikdeleteNo/{id}/{idPreduzeca}/{idPG}")
    public ResponseEntity<CenovnikDTO> getCc(@PathVariable("id") int id,@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	Cenovnik c = csi.getOne(id);
        if(c == null || c.isObrisano() == true  || c.getPreduzece().getId() != idPreduzeca || c.getDatum_kreiranja().before(p.getDatumPocetak()))
            return new ResponseEntity<CenovnikDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c),HttpStatus.OK);
    }
	
	@GetMapping(value = "/getActiveCenovnik/{idPreduzeca}/{idPG}")
    public ResponseEntity<CenovnikDTO> getCc(@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
		
    	Cenovnik c = csi.getAktivan(idPreduzeca);
        if(c == null || c.isObrisano() == true  || c.getPreduzece().getId() != idPreduzeca || c.getDatum_kreiranja().before(p.getDatumPocetak()))
            return new ResponseEntity<CenovnikDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getCenovnikdeleteNo/all/{idPreduzeca}/{idPG}", method = RequestMethod.GET)
    public ResponseEntity<List<CenovnikDTO>> getCenoviksc(@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	Preduzece pr =psi.getOne(idPreduzeca);
        List<CenovnikDTO> cDTo= csi.getAllWithOutDeleted(p, pr);
        
        return new ResponseEntity<List<CenovnikDTO>>(cDTo,HttpStatus.OK);
    }
	@PostMapping(value = "/add")
	public ResponseEntity<?> addCenovnik(@Validated @RequestBody CenovnikDTO cDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Cenovnik c = csi.save(cDTO);
		return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteC(@PathVariable("id") int id){
		Cenovnik c= csi.getOne(id);
		if(c == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		csi.removeL(c);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

