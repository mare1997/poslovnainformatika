package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
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

import com.pi.PoslovnaInformatika.converters.NarudzbenicaDTOtoNarudzbenica;
import com.pi.PoslovnaInformatika.converters.NarudzbenicaToNarudzbenicaDTO;
import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;
import com.pi.PoslovnaInformatika.repository.StavkeNarudzbeniceRepository;
import com.pi.PoslovnaInformatika.service.NarudzbenicaService;
import com.pi.PoslovnaInformatika.service.PGPservice;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.OtpremnicaServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkaNarudzbeniceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.StavkeCenovnikaServiceInterface;

@RestController
@RequestMapping(value="api/narudzbenice")
@CrossOrigin("*")
public class NarudzbenicaController {

	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Autowired
	private PGPservice pgpService;
	
	@Autowired
	private NarudzbenicaToNarudzbenicaDTO toNarudzbenicaDTO;
	
	@Autowired
	private NarudzbenicaDTOtoNarudzbenica toNarudzbenica;
	
	@Autowired
	private StavkaNarudzbeniceServiceInterface snsi;
	
	@Autowired
	private FakturaServiceInterface fsi;
	
	@Autowired
	private OtpremnicaServiceInterface osi;
	
	
	@RequestMapping(value="/all",method=RequestMethod.GET,params={"page","size","posGodId","preduzeceId"})
	public ResponseEntity<List<NarudzbenicaDTO>> getNarudzbenice(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("posGodId") int posGodId, @RequestParam("preduzeceId") int preduzeceId){
		Page<Narudzbenica> sveNarudzbenicePage = narudzbenicaService.findAll(PageRequest.of(page, size,Sort.by("datumIzrade").descending()));

		PoslovnaGodinaPreduzeca posGod = pgpService.getOne(posGodId);
		if (page > sveNarudzbenicePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
		List<Narudzbenica> activeNarudzbenice = new ArrayList<>();
		List<Narudzbenica> tempNarudzbenice = sveNarudzbenicePage.getContent();

		for (Narudzbenica narudzbenica : tempNarudzbenice){
			if (narudzbenica.getDatumIzrade().after(posGod.getDatumPocetak()) 
				&& narudzbenica.getPreduzece().getId() == preduzeceId
				&& narudzbenica.isAktivna() == false)
			{	
				if(posGod.getZavrsena() == true) {
					if(narudzbenica.getDatumIzrade().before(posGod.getDatumKraj())) {
						activeNarudzbenice.add(narudzbenica);
					}
				}else {
					activeNarudzbenice.add(narudzbenica);
				}
					
			}
		}
		Page<Narudzbenica> activeNarudzbenicePage = new PageImpl<>(activeNarudzbenice);
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(sveNarudzbenicePage.getTotalPages()));

		return new ResponseEntity<>(toNarudzbenicaDTO.convert(activeNarudzbenicePage.getContent()),headers, HttpStatus.OK);
	}
	@RequestMapping(value="/all/finished",method=RequestMethod.GET,params={"page","size","posGodId","preduzeceId"})
	public ResponseEntity<List<NarudzbenicaDTO>> getNarudzbenicee(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("posGodId") int posGodId, @RequestParam("preduzeceId") int preduzeceId){
		Page<Narudzbenica> sveNarudzbenicePage = narudzbenicaService.findAll(PageRequest.of(page, size,Sort.by("datumIzrade").descending()));

		PoslovnaGodinaPreduzeca posGod = pgpService.getOne(posGodId);
		if (page > sveNarudzbenicePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
		List<Narudzbenica> activeNarudzbenice = new ArrayList<>();
		List<Narudzbenica> tempNarudzbenice = sveNarudzbenicePage.getContent();

		for (Narudzbenica narudzbenica : tempNarudzbenice){
			if (narudzbenica.isObrisano()==false
				&&	narudzbenica.getDatumIzrade().after(posGod.getDatumPocetak()) 
				&& narudzbenica.getPreduzece().getId() == preduzeceId
				&& narudzbenica.isAktivna() == false)
			{	
				if(posGod.getZavrsena() == true) {
					if(narudzbenica.getDatumIzrade().before(posGod.getDatumKraj())) {
						activeNarudzbenice.add(narudzbenica);
					}
				}else {
					activeNarudzbenice.add(narudzbenica);
				}
					
			}
		}
		Page<Narudzbenica> activeNarudzbenicePage = new PageImpl<>(activeNarudzbenice);
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(sveNarudzbenicePage.getTotalPages()));

		return new ResponseEntity<>(toNarudzbenicaDTO.convert(activeNarudzbenicePage.getContent()),headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/all",method=RequestMethod.GET,params={"page","size","posGodId","preduzeceId"})
	public ResponseEntity<List<NarudzbenicaDTO>> getActiveNarudzbenica(@RequestParam("page") int page, @RequestParam("size") int size,
						@RequestParam("posGodId") int posGodId, @RequestParam("preduzeceId") int preduzeceId){
		Page<Narudzbenica> narudzbenicePage = narudzbenicaService.findAll(PageRequest.of(page, size,Sort.by("datumIzrade").descending()));
		
		PoslovnaGodinaPreduzeca posGod = pgpService.getOne(posGodId);
		if (page > narudzbenicePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
		List<Narudzbenica> activeNarudzbenice = new ArrayList<>();
		List<Narudzbenica> tempNarudzbenice = narudzbenicePage.getContent();

		for (Narudzbenica narudzbenica : tempNarudzbenice){
			if (narudzbenica.isObrisano()==false
					&&	narudzbenica.getDatumIzrade().after(posGod.getDatumPocetak()) 
					&& narudzbenica.getPreduzece().getId() == preduzeceId
					&& narudzbenica.isAktivna() == true)
				{	
					if(posGod.getZavrsena() == true) {
						if(narudzbenica.getDatumIzrade().before(posGod.getDatumKraj())) {
							activeNarudzbenice.add(narudzbenica);
						}
					}else {
						activeNarudzbenice.add(narudzbenica);
					}
						
				}
		}
		Page<Narudzbenica> activeNarudzbenicePage = new PageImpl<>(activeNarudzbenice);
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(narudzbenicePage.getTotalPages()));
		
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(activeNarudzbenicePage.getContent()),headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<NarudzbenicaDTO> getNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/{id}", method=RequestMethod.GET)
	public ResponseEntity<NarudzbenicaDTO> getActiveNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null || narudzbenica.isObrisano()== true || narudzbenica.isAktivna() == false){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addNarudzbenica", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addNarudzbenica(@Validated @RequestBody NarudzbenicaDTO narudzbenicaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		Narudzbenica novaNarudzbenica = narudzbenicaService.save(toNarudzbenica.convert(narudzbenicaDTO));
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(novaNarudzbenica), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/editNarudzbenica/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> editNarudzbenica(@PathVariable Integer id,@Validated  @RequestBody NarudzbenicaDTO editedNarudzbenicaDTO, Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		/*
		 * System.out.println("Narudzbenica je :" + editedNarudzbenicaDTO.isAktivna());
		editedNarudzbenicaDTO.setIdNarudzbenice(id);
		Narudzbenica editedNarudzbenica = toNarudzbenica.convert(editedNarudzbenicaDTO);
		narudzbenicaService.save(editedNarudzbenica);
		*/
		Narudzbenica editedNarudzbenica = narudzbenicaService.edit(editedNarudzbenicaDTO,id);
		return new ResponseEntity<>(toNarudzbenicaDTO.convert(editedNarudzbenica), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteNarudzbenica/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<NarudzbenicaDTO> hardDeleteNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		/*List<StavkaNarudzbenice> s = snsi.findAll();
		for(StavkaNarudzbenice ss:s) {
			if(ss.getNarudzbenica().getIdNarudzbenice() == id) {
				snsi.delete(ss.getIdStavkeNarudzbenice());
			}
		}*/
		narudzbenicaService.delete(id);
		return new ResponseEntity<NarudzbenicaDTO>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteNarudzbenica/{id}", method=RequestMethod.PUT)
	public ResponseEntity<NarudzbenicaDTO> softDeleteNarudzbenicaById(@PathVariable Integer id){
		Narudzbenica narudzbenica = narudzbenicaService.getOne(id);
		if(narudzbenica==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		/*Faktura f= fsi.getOne(narudzbenica.getFakturaRel().getId());
		Otpremnica o = osi.getOne(f.getOtpremnicaRel().getIdOtpremnice());
		narudzbenica.setObrisano(true);
		f.setObrisano(true);
		o.setObrisano(true);
		narudzbenicaService.save(narudzbenica);
		fsi.save(f);
		osi.save(o);*/
		narudzbenicaService.softDelete(id);
		return new ResponseEntity<NarudzbenicaDTO>(toNarudzbenicaDTO.convert(narudzbenica), HttpStatus.OK);
	}
	
}
