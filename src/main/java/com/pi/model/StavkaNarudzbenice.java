package com.pi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="Stavka_narudzbenice")
public class StavkaNarudzbenice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStavkeNarudzbenice;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(30)")
	private String naziv;
	
	@Column(name="Kolicina", columnDefinition="NUMBER")
	private Long kolicina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idNarudzbenice")
	private Narudzbenica narudzbenica;
	
	public StavkaNarudzbenice () {}

	

	public StavkaNarudzbenice(int idStavkeNarudzbenice, String naziv, Long kolicina, Narudzbenica narudzbenica) {
		super();
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.narudzbenica = narudzbenica;
	}



	public int getIdStavkeNarudzbenice() {
		return idStavkeNarudzbenice;
	}



	public void setIdStavkeNarudzbenice(int idStavkeNarudzbenice) {
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
	}



	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}



	public Long getKolicina() {
		return kolicina;
	}



	public void setKolicina(Long kolicina) {
		this.kolicina = kolicina;
	}



	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}



	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
		
		if(narudzbenica !=null && !narudzbenica.getStavkeNarudzbenice().contains(this)){
			narudzbenica.addStavkaNarudzbenice(this);
		}
	}
	
	
}
