package com.pi.dto;

import java.sql.Date;

public class NarudzbenicaDTO {

	
private int idStavkeNarudzbenice;
	
	private Long brojNarudzbenice;
	private Date datumIzrade;
	private Date datumIsporuke;
	private boolean placena;
	
	public NarudzbenicaDTO() {}
	
	public NarudzbenicaDTO(int idStavkeNarudzbenice, Long brojNarudzbenice, Date datumIzrade, Date datumIsporuke,
			boolean placena) {
		super();
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumIzrade = datumIzrade;
		this.datumIsporuke = datumIsporuke;
		this.placena = placena;

	}
	public int getIdStavkeNarudzbenice() {
		return idStavkeNarudzbenice;
	}
	public void setIdStavkeNarudzbenice(int idStavkeNarudzbenice) {
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
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

	
	
}
