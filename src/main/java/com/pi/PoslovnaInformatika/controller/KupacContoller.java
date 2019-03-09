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

import com.pi.PoslovnaInformatika.dto.GrupaRobeDTO;
import com.pi.PoslovnaInformatika.dto.KupacDTO;

import com.pi.PoslovnaInformatika.model.Kupac;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.service.interfaces.KupacServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.MestoServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PGPserviceInterface;
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
	
	@Autowired
	private PGPserviceInterface pgsi;
	
	@GetMapping(value = "/getActive/{id}/{idPreduzeca}/{idPG}")
    public ResponseEntity<KupacDTO> getKupac(@PathVariable("id") int id,@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	Kupac kupac = ksi.getOne(id);
        if(kupac == null || kupac.isObrisano() == true || kupac.getPreduzece().getId() != idPreduzeca || kupac.getDatum_kreiranja().before(p.getDatumPocetak()))
            return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<KupacDTO>(new KupacDTO(kupac),HttpStatus.OK);
    }
	@GetMapping(value = "/getActive/all/{idPreduzeca}/{idPG}")
	public ResponseEntity<List<KupacDTO>> get(@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
		/*List<Kupac> k = ksi.getAll();
		List<KupacDTO> kdto=new ArrayList<>();
		for(Kupac kk :  k) {
			if(kk.isObrisano() ==false && kk.getPreduzece().getId() == idPreduzeca && p.getDatumPocetak().before(kk.getDatum_kreiranja())) {
				if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(kk.getDatum_kreiranja()))
        				kdto.add(new KupacDTO(kk));
        		}else {
        			kdto.add(new KupacDTO(kk));
        		}
				
				
			}
		}*/
		
		Preduzece pr =psi.getOne(idPreduzeca);
		List<KupacDTO> sviKupci = ksi.getAllActiveDTO(p, pr);
		return new ResponseEntity<List<KupacDTO>>(sviKupci,HttpStatus.OK);
	}
	@GetMapping(value = "/getInactive/{id}/{idPreduzeca}/{idPG}")
    public ResponseEntity<KupacDTO> getKupa(@PathVariable("id") int id,@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
    	Kupac kupac = ksi.getOne(id);
        if(kupac == null ||  kupac.getPreduzece().getId() != idPreduzeca || kupac.getDatum_kreiranja().before(p.getDatumPocetak()))
            return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<KupacDTO>(new KupacDTO(kupac),HttpStatus.OK);
    }
	@GetMapping(value = "/getInactive/all/{idPreduzeca}/{idPG}")
	public ResponseEntity<List<KupacDTO>> getK(@PathVariable("idPreduzeca") int idPreduzeca,@PathVariable("idPG") int idPG){
		PoslovnaGodinaPreduzeca p = pgsi.getOne(idPG); 
		/*List<Kupac> k = ksi.getAll();
		List<KupacDTO> kdto=new ArrayList<>();
		for(Kupac kk :  k) {
			if(kk.getPreduzece().getId() == idPreduzeca && p.getDatumPocetak().before(kk.getDatum_kreiranja())) {
				if(p.getZavrsena() == true) {
        			if(p.getDatumKraj().after(kk.getDatum_kreiranja()))
        				kdto.add(new KupacDTO(kk));
        		}else {
        			kdto.add(new KupacDTO(kk));
        		}
				
				
			}
		}*/
		Preduzece pr =psi.getOne(idPreduzeca);
		List<KupacDTO> sviKupci = ksi.getAllInactiveDTO(p, pr);
		return new ResponseEntity<List<KupacDTO>>(sviKupci,HttpStatus.OK);
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
