package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;
import java.sql.Date;

import com.pi.PoslovnaInformatika.model.Prevoznik;

public class PrevoznikDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	
	private boolean obrisano;
	
	public PrevoznikDTO() {
		super();
	}

	public PrevoznikDTO(Integer id, String name,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		
		this.obrisano = obrisano;
	}

	public PrevoznikDTO(Prevoznik p) {
		this(p.getId(),p.getName(),p.isObrisano());
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

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	
	
	
	
	
}
