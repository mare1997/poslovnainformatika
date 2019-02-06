package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;


import com.pi.PoslovnaInformatika.model.Preduzece;

public class PreduzeceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String adresa;
	private String email;
	private String telefon;
	private long pib;
	private MestoDTO mesto;
	private boolean obrisano;
	public PreduzeceDTO() {
		super();
	}
	public PreduzeceDTO(Integer id, String name, String adresa, String email, String telefon, long pib,
			MestoDTO mesto,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.adresa = adresa;
		this.email = email;
		this.telefon = telefon;
		this.pib = pib;
		this.mesto = mesto;
		this.obrisano = obrisano;
	}
	
	
	public PreduzeceDTO(Preduzece p) {
		this(p.getId(),p.getName(),p.getAdresa(),p.getEmail(),p.getTelefon(),p.getPib(),new MestoDTO(p.getMesto()),p.isObrisano());
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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public long getPib() {
		return pib;
	}
	public void setPib(long pib) {
		this.pib = pib;
	}
	public MestoDTO getMesto() {
		return mesto;
	}
	public void setMesto(MestoDTO mesto) {
		this.mesto = mesto;
	}
	public boolean isObrisano() {
		return obrisano;
	}
	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
}
