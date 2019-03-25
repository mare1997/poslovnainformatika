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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pi.PoslovnaInformatika.dto.MestoDTO;

import com.pi.PoslovnaInformatika.model.Mesto;
import com.pi.PoslovnaInformatika.service.interfaces.MestoServiceInterface;

@RestController
@RequestMapping(value = "api/mesto")
@CrossOrigin("*")
public class MestoController {

	@Autowired
	private MestoServiceInterface msi;
	
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<MestoDTO> getMesto(@PathVariable("id") int id){
    	
    	Mesto mesto = msi.getOne(id);
        if(mesto == null || mesto.isObrisano() == true)
            return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<MestoDTO>(new MestoDTO(mesto),HttpStatus.OK);
    }
	@GetMapping(value="/all")
	public ResponseEntity<List<MestoDTO>> getAll(){
		
		List<Mesto> mesta = msi.getAll();
		List<MestoDTO> m  = new ArrayList<>();
		for(Mesto mm: mesta) {
			if(mm.isObrisano() == false) {
				m.add(new MestoDTO(mm));
			}
		}
		
		return new ResponseEntity<List<MestoDTO>>(m,HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addMesto(@Validated @RequestBody MestoDTO mestoDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		Mesto mesto = msi.save(mestoDTO);
		
		return new ResponseEntity<MestoDTO>(new MestoDTO(mesto),HttpStatus.CREATED);
	}
	@PutMapping(value = "/edit/{id}")
	public ResponseEntity<?> editCategoy(@Validated @RequestBody MestoDTO mestoDTO,Errors errors, @PathVariable("id")int id){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Mesto mesto = msi.edit(id, mestoDTO);
		
		return new ResponseEntity<MestoDTO>(new MestoDTO(mesto),HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteMesto(@PathVariable("id") int id){
		
		Mesto mesto= msi.getOne(id);
		if(mesto == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		msi.removeL(mesto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
