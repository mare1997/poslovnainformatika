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
	private Date datum_vazenja;
	private PreduzeceDTO preduzece;
	
	
	
	public CenovnikDTO() {
		super();
	}
	public CenovnikDTO(Integer id, Date datum_vazenja, PreduzeceDTO preduzece) {
		super();
		this.id = id;
		this.datum_vazenja = datum_vazenja;
		this.preduzece = preduzece;
	}
	public CenovnikDTO(Cenovnik c) {
		this(c.getId(),c.getDatum_vazenja(),new PreduzeceDTO(c.getPreduzece()));
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	

}
