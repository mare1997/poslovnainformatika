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


@Entity(name="Stavka_fakture")
@Table(name="Stavka_fakture")
public class StavkaFakture implements Serializable{


	private static final long serialVersionUID = 4408445101814871459L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStavkeFakture;
	
	@Column(name="Kolicina", columnDefinition="NUMBER")
	private Long kolicina;
	
	@Column(name="Jedinicna_cena", columnDefinition="NUMBER")
	private Long jedinicnaCena;

	@Column(name="Rabat", columnDefinition="NUMBER")
	private Long rabat;
	
	@Column(name="Osnovica_za_PDV", columnDefinition="NUMBER")
	private Long osnovicaZaPDV;
	
	@Column(name="Procenat_PDV", columnDefinition="NUMBER")
	private Long procenatPDV;
	
	@Column(name="Iznos_PDV", columnDefinition="NUMBER")
	private Long iznosPDV;
	
	@Column(name="Iznos_stavke", columnDefinition="NUMBER")
	private Long iznosStavke;
	
	@Column(name="Jedinica_mere",columnDefinition="VARCHAR(20)")
	private String jedinicaMere;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idFakture")
	private Faktura faktura;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="roba_usluga_id")
	private Roba roba_usluga_id;
	
	public StavkaFakture () {}

	

	public StavkaFakture(int idStavkeFakture, Long kolicina, Long jedinicnaCena, Long rabat, Long osnovicaZaPDV,
			Long procenatPDV, Long iznosPDV, Long iznosStavke, String jedinicaMere, Faktura faktura,
			Roba roba_usluga_id) {
		super();
		this.idStavkeFakture = idStavkeFakture;
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovicaZaPDV = osnovicaZaPDV;
		this.procenatPDV = procenatPDV;
		this.iznosPDV = iznosPDV;
		this.iznosStavke = iznosStavke;
		this.jedinicaMere = jedinicaMere;
		this.faktura = faktura;
		this.roba_usluga_id = roba_usluga_id;
	}



	public int getIdStavkeFakture() {
		return idStavkeFakture;
	}

	public void setIdStavkeFakture(int idStavkeFakture) {
		this.idStavkeFakture = idStavkeFakture;
	}

	public Long getKolicina() {
		return kolicina;
	}

	public void setKolicina(Long kolicina) {
		this.kolicina = kolicina;
	}

	public Long getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(Long jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public Long getRabat() {
		return rabat;
	}

	public void setRabat(Long rabat) {
		this.rabat = rabat;
	}

	public Long getOsnovicaZaPDV() {
		return osnovicaZaPDV;
	}

	public void setOsnovicaZaPDV(Long osnovicaZaPDV) {
		this.osnovicaZaPDV = osnovicaZaPDV;
	}

	public Long getProcenatPDV() {
		return procenatPDV;
	}

	public void setProcenatPDV(Long procenatPDV) {
		this.procenatPDV = procenatPDV;
	}

	public Long getIznosPDV() {
		return iznosPDV;
	}

	public void setIznosPDV(Long iznosPDV) {
		this.iznosPDV = iznosPDV;
	}
	

	public String getJedinicaMere() {
		return jedinicaMere;
	}



	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	
	public Roba getRoba() {
		return roba_usluga_id;
	}

	public void setRoba(Roba roba) {
		this.roba_usluga_id = roba;
	}

	public Long getIznosStavke() {
		return iznosStavke;
	}

	public void setIznosStavke(Long iznosStavke) {
		this.iznosStavke = iznosStavke;
	}

	public Faktura getFaktura() {
		return faktura;
	}

	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
		
		if(faktura != null && !faktura.getStavkeFakture().contains(this)){
			faktura.addStavkaFakture(this);
		}
	}
	
	
}
