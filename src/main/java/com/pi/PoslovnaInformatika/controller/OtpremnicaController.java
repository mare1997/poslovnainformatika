package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi.PoslovnaInformatika.converters.OtpremnicaDTOtoOtpremnica;
import com.pi.PoslovnaInformatika.converters.OtpremnicaToOtpremnicaDTO;
import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.service.OtpremnicaService;

@RestController
@RequestMapping(value="api/otpremnice")
@CrossOrigin("*")
public class OtpremnicaController {

	@Autowired
	private OtpremnicaService otpremnicaService;
	
	
	@Autowired
	private OtpremnicaToOtpremnicaDTO toOtpremnicaDTO;
	
	@Autowired
	private OtpremnicaDTOtoOtpremnica toOtpremnica;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<OtpremnicaDTO>> getOtpremnice(){
		List<Otpremnica> otpremnice = otpremnicaService.findAll();
		Collections.sort(otpremnice);
		return new ResponseEntity<>(toOtpremnicaDTO.convert(otpremnice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/all",method=RequestMethod.GET)
	public ResponseEntity<List<OtpremnicaDTO>> getActiveFakture(){
		List<Otpremnica> otpremnice = otpremnicaService.findAll();
		List<Otpremnica> activeOtpremnice = new ArrayList<>();
		for (Otpremnica otpremnica : otpremnice){
			if (otpremnica.isObrisano()==false)
					activeOtpremnice.add(otpremnica);
		}
		Collections.sort(activeOtpremnice);
		return new ResponseEntity<>(toOtpremnicaDTO.convert(activeOtpremnice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OtpremnicaDTO> getOtpremnicaById(@PathVariable Integer id){
		Otpremnica otpremnica = otpremnicaService.getOne(id);
		if(otpremnica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toOtpremnicaDTO.convert(otpremnica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/{id}", method=RequestMethod.GET)
	public ResponseEntity<OtpremnicaDTO> getActiveOtpremnicaById(@PathVariable Integer id){
		Otpremnica otpremnica = otpremnicaService.getOne(id);
		if(otpremnica==null || otpremnica.isObrisano()==true){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toOtpremnicaDTO.convert(otpremnica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addOtpremnica", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addOtpremnica(@Validated @RequestBody OtpremnicaDTO otpremnicaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		Otpremnica novaOtpremnica = otpremnicaService.save(toOtpremnica.convert(otpremnicaDTO));
		return new ResponseEntity<>(toOtpremnicaDTO.convert(novaOtpremnica), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editOtpremnica/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> editOtpremnica(@Validated @PathVariable Integer id, @RequestBody OtpremnicaDTO editedOtpremnicaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		Otpremnica otpremnica = otpremnicaService.getOne(id);
		if(otpremnica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Otpremnica editedOtpremnica = toOtpremnica.convert(editedOtpremnicaDTO);
		otpremnicaService.save(editedOtpremnica);
		return new ResponseEntity<>(toOtpremnicaDTO.convert(editedOtpremnica), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteOtpremnica/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<OtpremnicaDTO> hardDeleteOtpremnicaById(@PathVariable Integer id){
		Otpremnica otpremnica = otpremnicaService.getOne(id);
		if(otpremnica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		otpremnicaService.delete(id);
		return new ResponseEntity<OtpremnicaDTO>(toOtpremnicaDTO.convert(otpremnica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteOtpremnica/{id}", method=RequestMethod.PUT)
	public ResponseEntity<OtpremnicaDTO> softDeleteOtpremnicaById(@PathVariable Integer id){
		Otpremnica otpremnica = otpremnicaService.getOne(id);
		if(otpremnica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		otpremnica.setObrisano(true);
		otpremnicaService.save(otpremnica);
		return new ResponseEntity<OtpremnicaDTO>(toOtpremnicaDTO.convert(otpremnica), HttpStatus.OK);
	}
	
}


