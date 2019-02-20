package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;
import java.sql.Date;

import com.pi.PoslovnaInformatika.model.Cenovnik;

public class CenovnikDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Date datum_vazenja;
	private Date datum_kreiranja;
	private PreduzeceDTO preduzece;
	
	
	
	public CenovnikDTO() {
		super();
	}
	public CenovnikDTO(Integer id,String name, Date datum_vazenja,Date datum_kreiranja, PreduzeceDTO preduzece) {
		super();
		this.id = id;
		this.name = name;
		this.datum_vazenja = datum_vazenja;
		this.datum_kreiranja  =  datum_kreiranja;
		this.preduzece = preduzece;
	}
	public CenovnikDTO(Cenovnik c) {
		this(c.getId(),c.getName(),c.getDatum_vazenja(),c.getDatum_kreiranja(),new PreduzeceDTO(c.getPreduzece()));
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
	public Date getDatum_vazenja() {
		return datum_vazenja;
	}
	public void setDatum_vazenja(Date datum_vazenja) {
		this.datum_vazenja = datum_vazenja;
	}
	public PreduzeceDTO getPreduzece() {
		return preduzece;
	}
	public void setPreduzece(PreduzeceDTO preduzece) {
		this.preduzece = preduzece;
	}
	public Date getDatum_kreiranja() {
		return datum_kreiranja;
	}
	public void setDatum_kreiranja(Date datum_kreiranja) {
		this.datum_kreiranja = datum_kreiranja;
	}
	
	

}
