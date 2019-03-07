package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;
import java.sql.Date;

import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;

public class PoslovnaGodinaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String godina;
	private Date datumPocetak;
	private Date datumKraj;
	private Boolean zavrsena;
	public PoslovnaGodinaDTO() {
		super();
	}
	
	public PoslovnaGodinaDTO(Integer id, String godina, Date datumPocetak, Date datumKraj, Boolean zavrsena) {
		super();
		this.id = id;
		this.godina = godina;
		this.datumPocetak = datumPocetak;
		this.datumKraj = datumKraj;
		this.zavrsena = zavrsena;
	}

	public PoslovnaGodinaDTO(PoslovnaGodinaPreduzeca p) {
		this(p.getId(),p.getGodina(),p.getDatumPocetak(),p.getDatumKraj(),p.getZavrsena());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGodina() {
		return godina;
	}
	public void setGodina(String godina) {
		this.godina = godina;
	}
	public Boolean getZavrsena() {
		return zavrsena;
	}
	public void setZavrsena(Boolean zavrsena) {
		this.zavrsena = zavrsena;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getDatumPocetak() {
		return datumPocetak;
	}
	public void setDatumPocetak(Date datumPocetak) {
		this.datumPocetak = datumPocetak;
	}
	public Date getDatumKraj() {
		return datumKraj;
	}
	public void setDatumKraj(Date datumKraj) {
		this.datumKraj = datumKraj;
	}
	
	

}
