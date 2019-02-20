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

import com.pi.PoslovnaInformatika.dto.GrupaRobeDTO;

import com.pi.PoslovnaInformatika.model.GrupaRobe;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.service.interfaces.GrupaRobeServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PDVServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PGPserviceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/gruparobe")
@CrossOrigin("*")
public class GrupaRobeController {

	@Autowired
	private GrupaRobeServiceInterface grsi;
	
	@Autowired
	private PDVServiceInterface psi;
	
	@Autowired
	private PreduzeceServiceInterface prsi;
	
	@Autowired
	private PGPserviceInterface pgsi;
	
	@GetMapping(value = "/getGRdeliteYes/{id}/{idPreduzeca}/{idPG}")
    public ResponseEntity<GrupaRobeDTO> getGrupaRoba(@PathVariable("id") int id,@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	GrupaRobe grupa=grsi.getOne(id);
    	
        if(grupa == null || grupa.getPreduzece().getId() != idPreduzeca || grupa.getDatum_kreiranja().before(p.getDatumPocetak()) )
            return new ResponseEntity<GrupaRobeDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<GrupaRobeDTO>(new GrupaRobeDTO(grupa),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getGRdeliteYes/all/{idPreduzeca}/{idPG}", method = RequestMethod.GET)
    public ResponseEntity<List<GrupaRobeDTO>> getGrupa(@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	List<GrupaRobe> grupa=grsi.getAll();
        List<GrupaRobeDTO> grupaDto=new ArrayList<>();
        for (GrupaRobe r:grupa) {
        	if(r.getPreduzece().getId() == idPreduzeca && p.getDatumPocetak().before(r.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(r.getDatum_kreiranja()))
        				grupaDto.add(new GrupaRobeDTO(r));
        		}else {
        			grupaDto.add(new GrupaRobeDTO(r));
        		}
        		
        	}
            
        }
        return new ResponseEntity<List<GrupaRobeDTO>>(grupaDto,HttpStatus.OK);
    }
	@GetMapping(value = "/getGRdeliteNo/{id}/{idPreduzeca}/{idPG}")
    public ResponseEntity<GrupaRobeDTO> getGR(@PathVariable("id") int id,@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	GrupaRobe grupa=grsi.getOne(id);
        if(grupa == null || grupa.isObrisano() == true || grupa.getPreduzece().getId() != idPreduzeca || grupa.getDatum_kreiranja().before(p.getDatumPocetak()))
            return new ResponseEntity<GrupaRobeDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<GrupaRobeDTO>(new GrupaRobeDTO(grupa),HttpStatus.OK);
    }
	
	@RequestMapping(value="/getGRdeliteNo/all/{idPreduzeca}/{idPG}", method = RequestMethod.GET)
    public ResponseEntity<List<GrupaRobeDTO>> getGR(@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	List<GrupaRobe> grupa=grsi.getAll();
        List<GrupaRobeDTO> grupaDto=new ArrayList<>();
        for (GrupaRobe r:grupa) {
        	if(r.isObrisano() == false && r.getPreduzece().getId() == idPreduzeca && p.getDatumPocetak().before(r.getDatum_kreiranja())) {
        		if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(r.getDatum_kreiranja()))
        				grupaDto.add(new GrupaRobeDTO(r));
        		}else {
        			grupaDto.add(new GrupaRobeDTO(r));
        		}
        		
        	}
        }
        return new ResponseEntity<List<GrupaRobeDTO>>(grupaDto,HttpStatus.OK);
    }
	@PostMapping(value = "/add")
	public ResponseEntity<?> addRoba(@Validated @RequestBody GrupaRobeDTO grupaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		GrupaRobe grupa = new GrupaRobe();
		
		grupa.setName(grupaDTO.getName());
		grupa.setPdv(psi.getOne(grupaDTO.getPdv().getId()));
		grupa.setPreduzece(prsi.getOne(grupaDTO.getPreduzece().getId()));
		
		
		grsi.save(grupa);
		
		return new ResponseEntity<GrupaRobeDTO>(new GrupaRobeDTO(grupa),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteGrupa(@PathVariable("id") int id){
		
		GrupaRobe grupa= grsi.getOne(id);
		if(grupa == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		grupa.setObrisano(true);
		grsi.save(grupa);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
