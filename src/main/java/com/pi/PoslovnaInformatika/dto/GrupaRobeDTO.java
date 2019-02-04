package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;

import com.pi.PoslovnaInformatika.model.PDV;
import com.pi.PoslovnaInformatika.model.Preduzece;

public class GrupaRobeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private PDVDTO pdv;
	private PreduzeceDTO preduzece;
	public GrupaRobeDTO() {
		super();
	}
	public GrupaRobeDTO(Integer id, String name, PDVDTO pdv, PreduzeceDTO preduzece) {
		super();
		this.id = id;
		this.name = name;
		this.pdv = pdv;
		this.preduzece = preduzece;
	}
	public GrupaRobeDTO(GrupaRobeDTO g) {
		this();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PDVDTO getPdv() {
		return pdv;
	}
	public void setPdv(PDVDTO pdv) {
		this.pdv = pdv;
	}
	public PreduzeceDTO getPreduzece() {
		return preduzece;
	}
	public void setPreduzece(PreduzeceDTO preduzece) {
		this.preduzece = preduzece;
	}
	
	
	
	
	
}
