package com.pi.PoslovnaInformatika.dto;

import java.sql.Date;

public class NarudzbenicaDTO {

	

	private int idNarudzbenice;	
	private Long brojNarudzbenice;
	private Date datumIzrade;
	private Date datumIsporuke;
	private boolean placena;
	private int fakturaRel;
	private int user;
	
	public NarudzbenicaDTO() {}
	
	public NarudzbenicaDTO(int idNarudzbenice, Long brojNarudzbenice, Date datumIzrade, Date datumIsporuke,
			boolean placena, int fakturaRel, int user) {
		super();
		this.idNarudzbenice = idNarudzbenice;
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumIzrade = datumIzrade;
		this.datumIsporuke = datumIsporuke;
		this.placena = placena;
		this.fakturaRel=fakturaRel;
		this.user=user;

	}
	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getIdNarudzbenice() {
		return idNarudzbenice;
	}
	public void setIdNarudzbenice(int idNarudzbenice) {
		this.idNarudzbenice = idNarudzbenice;
	}
	public Long getBrojNarudzbenice() {
		return brojNarudzbenice;
	}
	public void setBrojNarudzbenice(Long brojNarudzbenice) {
		this.brojNarudzbenice = brojNarudzbenice;
	}
	public Date getDatumIzrade() {
		return datumIzrade;
	}
	public void setDatumIzrade(Date datumIzrade) {
		this.datumIzrade = datumIzrade;
	}
	public Date getDatumIsporuke() {
		return datumIsporuke;
	}
	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}
	public boolean isPlacena() {
		return placena;
	}
	public void setPlacena(boolean placena) {
		this.placena = placena;
	}

	public int getFakturaRel() {
		return fakturaRel;
	}

	public void setFakturaRel(int fakturaRel) {
		this.fakturaRel = fakturaRel;
	}

	
	
}
