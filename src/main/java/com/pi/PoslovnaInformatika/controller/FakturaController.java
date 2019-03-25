package com.pi.PoslovnaInformatika.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.pi.PoslovnaInformatika.converters.FakturaDTOtoFaktura;
import com.pi.PoslovnaInformatika.converters.FakturaToFakturaDTO;
import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;
import com.pi.PoslovnaInformatika.service.FakturaService;
import com.pi.PoslovnaInformatika.service.PGPservice;
import com.pi.PoslovnaInformatika.service.StavkaFaktureService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(value="api/fakture")
@CrossOrigin("*")
public class FakturaController {

	@Autowired
	private FakturaService fakturaService;
	
	@Autowired
	private PGPservice pgpService;
	
	@Autowired
	private StavkaFaktureService sfService;
	
	@Autowired
	private FakturaToFakturaDTO toFakturaDTO;
	
	@Autowired
	private FakturaDTOtoFaktura toFaktura;
	
	
	@RequestMapping(value="/all",method=RequestMethod.GET,params={"page","size","posGodId","preduzeceId"})
	public ResponseEntity<List<FakturaDTO>> getFakture(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("posGodId") int posGodId, @RequestParam("preduzeceId") int preduzeceId){
		
		Page<Faktura> fakturePage = fakturaService.findAll(PageRequest.of(page, size,Sort.by("datumFakture").descending()));

		PoslovnaGodinaPreduzeca posGod = pgpService.getOne(posGodId);
		
		if (page > fakturePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		List<Faktura> tempFakture = fakturePage.getContent(); 
		List<Faktura> activeFakture = new ArrayList<>();
		for (Faktura faktura : tempFakture){
			if (faktura.isObrisano() == false &&
					faktura.getDatumFakture().after(posGod.getDatumPocetak()) 
				//	&& faktura.getDatumFakture().before(posGod.getDatumKraj())
					&& faktura.getPreduzece().getId() == preduzeceId)
				
					activeFakture.add(faktura);
		}
		Page<Faktura> activeFakturePage = new PageImpl<>(activeFakture);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(fakturePage.getTotalPages()));
		
		return new ResponseEntity<>(toFakturaDTO.convert(activeFakturePage.getContent()),headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/all",method=RequestMethod.GET,params={"page","size","posGodId","preduzeceId"})
	public ResponseEntity<List<FakturaDTO>> getActiveFakture(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("posGodId") int posGodId, @RequestParam("preduzeceId") int preduzeceId){
		
		Page<Faktura> fakturePage = fakturaService.findAll(PageRequest.of(page, size,Sort.by("datumFakture").descending()));
		
		PoslovnaGodinaPreduzeca posGod = pgpService.getOne(posGodId);
		
		if (page > fakturePage.getTotalPages()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		List<Faktura> tempFakture = fakturePage.getContent(); 
		List<Faktura> activeFakture = new ArrayList<>();
		for (Faktura faktura : tempFakture){
			if (faktura.isObrisano()==false 
					&& faktura.getDatumFakture().after(posGod.getDatumPocetak()) 
					&& faktura.getDatumFakture().before(posGod.getDatumKraj())
					&& faktura.getPreduzece().getId() == preduzeceId)
				
					activeFakture.add(faktura);
		}
		Page<Faktura> activeFakturePage = new PageImpl<>(activeFakture);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(fakturePage.getTotalPages()));
		
		return new ResponseEntity<>(toFakturaDTO.convert(activeFakturePage.getContent()), headers,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<FakturaDTO> getFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	
	@RequestMapping(value="/active/{id}", method=RequestMethod.GET)
	public ResponseEntity<FakturaDTO> getActiveFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null || faktura.isObrisano()==true){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	@RequestMapping(value="/addFaktura", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addFaktura(@Validated @RequestBody FakturaDTO fakturaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		
		Faktura novaFaktura = fakturaService.save(toFaktura.convert(fakturaDTO));
		return new ResponseEntity<>(toFakturaDTO.convert(novaFaktura), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/editFaktura/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> editFaktura(@Validated @PathVariable Integer id, @RequestBody FakturaDTO editedFakturaDTO,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		/*editedFakturaDTO.setIdFakture(id);
		Faktura editedFaktura = toFaktura.convert(editedFakturaDTO);
		fakturaService.save(editedFaktura);*/
		Faktura editedFaktura = fakturaService.edit(editedFakturaDTO,id);
		return new ResponseEntity<>(toFakturaDTO.convert(editedFaktura), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/hardDeleteFaktura/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<FakturaDTO> hardDeleteFakturaById(@PathVariable Integer id){
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		/*List<StavkaFakture> lsf = sfService.findAll();
		for(StavkaFakture sf: lsf){
			if(sf.getFaktura().getId()==id){
				sfService.delete(sf.getIdStavkeFakture());
			}
		}*/
		fakturaService.delete(id);
		return new ResponseEntity<FakturaDTO>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	
	@RequestMapping(value="/softDeleteFaktura/{id}", method=RequestMethod.PUT)
	public ResponseEntity<FakturaDTO> softDeleteFakturaById(@PathVariable Integer id){
		
		Faktura faktura = fakturaService.getOne(id);
		if(faktura==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}/*
		faktura.setObrisano(true);*/
		fakturaService.softDelete(id);
		return new ResponseEntity<FakturaDTO>(toFakturaDTO.convert(faktura), HttpStatus.OK);
	}
	
	@RequestMapping(value="/generateReport/{id}")
	public ResponseEntity<InputStreamResource> generateReport(@PathVariable("id") int id) throws JRException, IOException, SQLException{
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poslovnainformatika?useSSL=false", "root", "root");
		
		/*ReportGenerator gen = new ReportGenerator();
		gen.exportToPdf();*/
		System.out.println(System.getProperty("user.dir"));
		JasperCompileManager.compileReportToFile(
                getClass().getResource("/reports/finalpiv6.jrxml").getPath(), // the path to the jrxml file to compile
                getClass().getResource("/reports/finalpiv6.jasper").getPath());
		Map<String, Object> mapIdFakture = new HashMap<String, Object>();
		mapIdFakture.put("id_fakture", id);
		JasperPrint jp = JasperFillManager.fillReport(getClass().getResource("/reports/finalpiv6.jasper").openStream(),mapIdFakture, dbConnection);
		ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));//ExportReportToPdf vraca byte[]
		
		System.out.println(System.getProperty("user.dir"));
		File pdf = new File("reports/report.pdf");
		/*File pdf = new File(System.getProperty("user.dir")+"\\reports\\report.pdf");*/
		
		JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
		System.out.println(pdf.getAbsolutePath());
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report.pdf");

		
		return ResponseEntity
	       		.ok()
	       		.headers(headers)
	       		.contentType(MediaType.APPLICATION_PDF)
	       		.body(new InputStreamResource(bis));
	}
}
