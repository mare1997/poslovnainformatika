package com.pi.PoslovnaInformatika.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Stavka_Otpremnice")
public class StavkaOtpremnice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8918560754970461476L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStavkeOtpremnice;
	
	@Column(name="Redni_broj", columnDefinition="INTEGER")
	private Long redniBroj;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(30)")
	private String naziv;
	
	@Column(name="Cena")
	private Long cena;

	@Column(name="Isporucena_kolicina")
	private Long isporucenaKolicina;
	
	@Column(name="Napomena", columnDefinition="VARCHAR(150)")
	private String napomena;
	
	@Column(name="Jedinica_mere",columnDefinition="VARCHAR(20)")
	private String jedinicaMere;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="roba_usluga_id")
	private Roba roba_usluga_id;
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idOtpremnice")
	private Otpremnica otpremnica;
	
	public StavkaOtpremnice() {}

	public StavkaOtpremnice(int idStavkeOtpremnice, Long redniBroj, String naziv, Long cena, Long isporucenaKolicina,
			String napomena, Otpremnica otpremnica,String jedinicaMere,Roba roba_usluga_id,boolean obrisano) {
		super();
		this.idStavkeOtpremnice = idStavkeOtpremnice;
		this.redniBroj = redniBroj;
		this.naziv = naziv;
		this.cena = cena;
		this.isporucenaKolicina = isporucenaKolicina;
		this.napomena = napomena;
		this.otpremnica = otpremnica;
		this.roba_usluga_id=roba_usluga_id;
		this.jedinicaMere=jedinicaMere;
		this.obrisano=obrisano;
	}

	
	
	public Roba getRoba_usluga_id() {
		return roba_usluga_id;
	}

	public void setRoba_usluga_id(Roba roba_usluga_id) {
		this.roba_usluga_id = roba_usluga_id;
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

	public String getJedinicaMere() {
		return jedinicaMere;
	}



	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
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
