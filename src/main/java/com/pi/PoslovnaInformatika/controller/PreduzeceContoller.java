package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi.PoslovnaInformatika.dto.PreduzeceDTO;

import com.pi.PoslovnaInformatika.model.Preduzece;

import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;



@RestController
@RequestMapping(value = "api/preduzece")
@CrossOrigin("*")
public class PreduzeceContoller {

	@Autowired
	private PreduzeceServiceInterface psi;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<PreduzeceDTO> getpreduzece(@PathVariable("id") int id){
    	
    	Preduzece preduzece = psi.getOne(id);
        if(preduzece == null)
            return new ResponseEntity<PreduzeceDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<PreduzeceDTO>(new PreduzeceDTO(preduzece),HttpStatus.OK);
    }
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<PreduzeceDTO>> getAll(){
    	
    	List<Preduzece> p=psi.getAll();
        List<PreduzeceDTO> pDTo=new ArrayList<>();
        for (Preduzece  pp:p) {
            pDTo.add(new PreduzeceDTO(pp));
            
        }
        return new ResponseEntity<List<PreduzeceDTO>>(pDTo,HttpStatus.OK);
    }
	
}
