package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi.PoslovnaInformatika.converters.OtpremnicaDTOtoOtpremnica;
import com.pi.PoslovnaInformatika.converters.OtpremnicaToOtpremnicaDTO;
import com.pi.PoslovnaInformatika.dto.OtpremnicaDTO;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.StavkaOtpremnice;
import com.pi.PoslovnaInformatika.service.OtpremnicaService;
import com.pi.PoslovnaInformatika.service.PGPservice;
import com.pi.PoslovnaInformatika.service.StavkaOtpremniceService;

@RestController
@RequestMapping(value="api/otpremnice")
@CrossOrigin("*")
public class OtpremnicaController {

	@Autowired
	private OtpremnicaService otpremnicaService;


	@Autowired
	private StavkaOtpremniceService soService;
	
	
	@Autowired
	private PGPservice pgpService;
	
	@Autowired
	private OtpremnicaToOtpremnicaDTO toOtpremnicaDTO;
	
	@Autowired
	private OtpremnicaDTOtoOtpremnica toOtpremnica;
	
	@RequestMapping(value="/all",method=RequestMethod.GET,params={"page","size","posGodId","preduzeceId"})
	public ResponseEntity<List<OtpremnicaDTO>> getOtpremnice(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("posGodId") int posGodId, @RequestParam("preduzeceId") int preduzeceId){
		Page<Otpremnica> otpremnicePage = otpremnicaService.findAll(PageRequest.of(page, size,Sort.by("datumOtpremnice").descending()));

		PoslovnaGodinaPreduzeca posGod = pgpService.getOne(posGodId);
		if (page > otpremnicePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		List<Otpremnica> tempOtpremnice = otpremnicePage.getContent();
		List<Otpremnica> activeOtpremnice = new ArrayList<>();
		for (Otpremnica otpremnica : tempOtpremnice){
			if (otpremnica.isObrisano()==false 
					&& otpremnica.getDatumOtpremnice().after(posGod.getDatumPocetak()) 
			//		&& otpremnica.getDatumOtpremnice().before(posGod.getDatumKraj())
					&& otpremnica.getPreduzece().getId() == preduzeceId)
				
					activeOtpremnice.add(otpremnica);
		}
		
		Page<Otpremnica> activeOtpremnicePage = new PageImpl<>(activeOtpremnice);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(otpremnicePage.getTotalPages()));
		return new ResponseEntity<>(toOtpremnicaDTO.convert(activeOtpremnicePage.getContent()),headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/all",method=RequestMethod.GET,params={"page","size","posGodId","preduzeceId"})
	public ResponseEntity<List<OtpremnicaDTO>> getActiveFakture(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("posGodId") int posGodId, @RequestParam("preduzeceId") int preduzeceId){
		
		Page<Otpremnica> otpremnicePage = otpremnicaService.findAll(PageRequest.of(page, size,Sort.by("datumOtpremnice").descending()));
		
		PoslovnaGodinaPreduzeca posGod = pgpService.getOne(posGodId);
		if (page > otpremnicePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		List<Otpremnica> tempOtpremnice = otpremnicePage.getContent();
		List<Otpremnica> activeOtpremnice = new ArrayList<>();
		for (Otpremnica otpremnica : tempOtpremnice){
			if (otpremnica.isObrisano()==false 
					&& otpremnica.getDatumOtpremnice().after(posGod.getDatumPocetak()) 
					&& otpremnica.getDatumOtpremnice().before(posGod.getDatumKraj())
					&& otpremnica.getPreduzece().getId() == preduzeceId)
				
					activeOtpremnice.add(otpremnica);
		}
		
		Page<Otpremnica> activeOtpremnicePage = new PageImpl<>(activeOtpremnice);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(otpremnicePage.getTotalPages()));
		return new ResponseEntity<>(toOtpremnicaDTO.convert(activeOtpremnicePage.getContent()),headers, HttpStatus.OK);
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
		System.out.println(otpremnicaDTO.toString());
		System.out.println(toOtpremnica.convert(otpremnicaDTO).toString());
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
		}/*
		editedOtpremnicaDTO.setIdOtpremnice(id);
		Otpremnica editedOtpremnica = toOtpremnica.convert(editedOtpremnicaDTO);
		otpremnicaService.save(editedOtpremnica);*/
		Otpremnica editedOtpremnica = otpremnicaService.edit(editedOtpremnicaDTO,id);
		return new ResponseEntity<>(toOtpremnicaDTO.convert(editedOtpremnica), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteOtpremnica/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<OtpremnicaDTO> hardDeleteOtpremnicaById(@PathVariable Integer id){
		Otpremnica otpremnica = otpremnicaService.getOne(id);
		if(otpremnica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		/*List<StavkaOtpremnice> lso = soService.findAll();
		for(StavkaOtpremnice so:lso){
			if(so.getOtpremnica().getIdOtpremnice()==id){
				soService.delete(so.getIdStavkeOtpremnice());
			}
		}*/
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


