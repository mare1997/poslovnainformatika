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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.pi.PoslovnaInformatika.dto.RobaDTO;
import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.service.interfaces.GrupaRobeServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.RobaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeCenovnikaServiceInterface;

@RestController
@RequestMapping(value = "api/roba")
@CrossOrigin("*")
public class RobaController {
	
	@Autowired
	private RobaServiceInterface rsi;
	
	@Autowired
	private GrupaRobeServiceInterface grsi;
	
	@Autowired
	private StavkeCenovnikaServiceInterface scsi;
	
	@GetMapping(value = "/getRobadeliteYes/{id}")
    public ResponseEntity<RobaDTO> getRoba(@PathVariable("id") int id){
    	
    	Roba roba=rsi.getOne(id);
        if(roba == null)
            return new ResponseEntity<RobaDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<RobaDTO>(new RobaDTO(roba),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getAllRobadeliteYes/all", method = RequestMethod.GET)
    public ResponseEntity<List<RobaDTO>> getRobas(){
    	
    	List<Roba> roba=rsi.getAll();
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
            robaDTo.add(new RobaDTO(r));
            
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTo,HttpStatus.OK);
    }
	@GetMapping(value = "/getRobadeliteNo/{id}")
    public ResponseEntity<RobaDTO> getR(@PathVariable("id") int id){
    	
    	Roba roba=rsi.getOne(id);
        if(roba == null || roba.isObrisano() == true)
            return new ResponseEntity<RobaDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<RobaDTO>(new RobaDTO(roba),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getAllRobadeliteNo/all", method = RequestMethod.GET)
    public ResponseEntity<List<RobaDTO>> getR(){
    	
    	List<Roba> roba=rsi.getAll();
    	
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
        	if(r.isObrisano() == false) {
        		System.out.println(r.getName());
        		robaDTo.add(new RobaDTO(r));
        	}
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTo,HttpStatus.OK);
    }
	@RequestMapping(value="/getAllRobadeliteNo/{idgr}", method = RequestMethod.GET)
    public ResponseEntity<List<RobaDTO>> getRr(@PathVariable("idgr") int id){
    	GrupaRobe gr = grsi.getOne(id);
    	List<Roba> roba=rsi.getAll();
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
        	if(r.isObrisano() == false) {
        		if(gr.getId() == r.getGrupa().getId()) {
        			robaDTo.add(new RobaDTO(r));
        		}
        	}
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTo,HttpStatus.OK);
    }
	@RequestMapping(value="/getAllRobadeliteNoName/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<RobaDTO>> getRr(@PathVariable("name") String name){
    	GrupaRobe gr = grsi.getByName(name);
    	List<Roba> roba=rsi.getAll();
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
        	if(r.isObrisano() == false) {
        		if(gr.getId() == r.getGrupa().getId()) {
        			robaDTo.add(new RobaDTO(r));
        		}
        	}
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTo,HttpStatus.OK);
    }
	
	@RequestMapping(value="/getAllActiveRobaByName", method = RequestMethod.GET,params={"name"})
    public ResponseEntity<List<RobaDTO>> getActiveRobaByName(@RequestParam("name") String name){
    	
    	List<Roba> roba=rsi.getAll();
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
        	if(r.isObrisano() == false && r.getName().contains(name)) {
        		
        			robaDTo.add(new RobaDTO(r));
        		
        	}
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTo,HttpStatus.OK);
    }
	
	@RequestMapping(value="/getAllRobaByName", method = RequestMethod.GET,params={"name"})
    public ResponseEntity<List<RobaDTO>> getRobaByName(@RequestParam("name") String name){
    	
    	List<Roba> roba=rsi.getAll();
        List<RobaDTO> robaDTo=new ArrayList<>();
        for (Roba r:roba) {
        	if(r.getName().contains(name)) {
        		
        			robaDTo.add(new RobaDTO(r));
        		
        	}
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTo,HttpStatus.OK);
    }
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addRoba(@Validated @RequestBody RobaDTO robaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Roba roba = new Roba();
		
		roba.setName(robaDTO.getName());
		roba.setJedninica_mere(robaDTO.getJedninica_mere());
		roba.setGrupa(grsi.getOne(robaDTO.getGrupa().getId()));
		roba.setCene(scsi.getOne(robaDTO.getCena()));
		
		
		rsi.save(roba);
		
		return new ResponseEntity<RobaDTO>(new RobaDTO(roba),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteRoba(@PathVariable("id") int id){
		
		Roba roba= rsi.getOne(id);
		if(roba == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		roba.setObrisano(true);
		rsi.save(roba);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
