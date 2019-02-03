package com.pi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="Stavka_otpremnice")
public class StavkaOtpremnice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStavkeOtpremnice;
	
	@Column(name="Redni_broj", columnDefinition="INTEGER")
	private Long redniBroj;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(30)")
	private String naziv;
	
	@Column(name="Cena", columnDefinition="NUMBER")
	private Long cena;

	@Column(name="Isporucena_kolicina", columnDefinition="NUMBER")
	private Long isporucenaKolicina;
	
	@Column(name="Napomena", columnDefinition="VARCHAR(150)")
	private String napomena;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idOtpremnice")
	private Otpremnica otpremnica;
	
	public StavkaOtpremnice() {}

	public StavkaOtpremnice(int idStavkeOtpremnice, Long redniBroj, String naziv, Long cena, Long isporucenaKolicina,
			String napomena, Otpremnica otpremnica) {
		super();
		this.idStavkeOtpremnice = idStavkeOtpremnice;
		this.redniBroj = redniBroj;
		this.naziv = naziv;
		this.cena = cena;
		this.isporucenaKolicina = isporucenaKolicina;
		this.napomena = napomena;
		this.otpremnica = otpremnica;
	}

	public int getIdStavkeOtpremnice() {
		return idStavkeOtpremnice;
	}

	public void setIdStavkeOtpremnice(int idStavkeOtpremnice) {
		this.idStavkeOtpremnice = idStavkeOtpremnice;
	}

	public Long getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(Long redniBroj) {
		this.redniBroj = redniBroj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Long getCena() {
		return cena;
	}

	public void setCena(Long cena) {
		this.cena = cena;
	}

	public Long getIsporucenaKolicina() {
		return isporucenaKolicina;
	}

	public void setIsporucenaKolicina(Long isporucenaKolicina) {
		this.isporucenaKolicina = isporucenaKolicina;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public Otpremnica getOtpremnica() {
		return otpremnica;
	}

	public void setOtpremnica(Otpremnica otpremnica) {
		this.otpremnica = otpremnica;
		
		if(otpremnica != null && !otpremnica.getStavkeOtpremnice().contains(this)){
			otpremnica.addStavkaOtpremnice(this);
		}
	}
	
	
}
