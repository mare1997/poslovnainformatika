package com.pi.PoslovnaInformatika.dto;

import com.pi.PoslovnaInformatika.model.Narudzbenica;

public class StavkaNarudzbeniceDTO {

	private int idStavkeNarudzbenice;
	private String naziv;
	private Long kolicina;
	private int idNarudzbenice;
	private String jedinicaMere;
	
	public StavkaNarudzbeniceDTO() {}

	public StavkaNarudzbeniceDTO(int idStavkeNarudzbenice, String naziv, Long kolicina, int idNarudzbenice, String jedinicaMere) {
		super();
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.idNarudzbenice = idNarudzbenice;
		this.jedinicaMere=jedinicaMere;
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

	public int getIdNarudzbenice() {
		return idNarudzbenice;
	}

	public void setIdNarudzbenice(int idNarudzbenice) {
		this.idNarudzbenice = idNarudzbenice;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	
	
	
	
}
