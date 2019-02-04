package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;

import com.pi.PoslovnaInformatika.model.Prevoznik;

public class PrevoznikDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	
	public PrevoznikDTO() {
		super();
	}

	public PrevoznikDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PrevoznikDTO(Prevoznik p) {
		this(p.getId(),p.getName());
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
	
	
	
}
