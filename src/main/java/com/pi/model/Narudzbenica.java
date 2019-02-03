package com.pi.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Narudzbenica {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStavkeNarudzbenice;
	
	@Column(name="Broj_narudzbenice", columnDefinition="NUMBER")
	private Long brojNarudzbenice;
	
	@Column(name="Datum_izrade", columnDefinition="DATE")
	private Date datumIzrade;

	@Column(name="Datum_isporuke", columnDefinition="DATE")
	private Date datumIsporuke;
	
	@Column(name="Placena", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean placena;
	
	@JsonIgnore
	@OneToMany(mappedBy="narudzbenica", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<StavkaNarudzbenice> stavkeNarudzbenice;
	
	public Narudzbenica () {}

	public Narudzbenica(int idStavkeNarudzbenice, Long brojNarudzbenice, Date datumIzrade, Date datumIsporuke,
			boolean placena, List<StavkaNarudzbenice> stavkeNarudzbenice) {
		super();
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumIzrade = datumIzrade;
		this.datumIsporuke = datumIsporuke;
		this.placena = placena;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
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

	public List<StavkaNarudzbenice> getStavkeNarudzbenice() {
		return stavkeNarudzbenice;
	}

	public void setStavkeNarudzbenice(List<StavkaNarudzbenice> stavkeNarudzbenice) {
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}
	
	
	public void addStavkaNarudzbenice(StavkaNarudzbenice stavkaNarudzbenice){
		if(stavkeNarudzbenice.contains(stavkaNarudzbenice)){
			return;
			
		}
		stavkeNarudzbenice.add(stavkaNarudzbenice);
		stavkaNarudzbenice.setNarudzbenica(this);
	}
	
	public void removeStavkaNarudzbenice(StavkaNarudzbenice stavkaNarudzbenice){
		if(!stavkeNarudzbenice.contains(stavkaNarudzbenice)){
			return;
			
		}
		stavkeNarudzbenice.remove(stavkaNarudzbenice);
		if(stavkaNarudzbenice.getNarudzbenica().equals(this)){
			stavkaNarudzbenice.setNarudzbenica(null);
		}
	}
}
