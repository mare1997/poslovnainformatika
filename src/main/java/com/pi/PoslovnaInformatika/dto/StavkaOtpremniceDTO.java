package com.pi.PoslovnaInformatika.dto;

public class StavkaOtpremniceDTO {

	private int idStavkeOtpremnice;
	private Long redniBroj;
	private String naziv;
	private Long cena;
	private Long isporucenaKolicina;
	private String napomena;
	private int idOtpremnice;
	private String jedinicaMere;
	private int robaUslugaId;
	private boolean obrisano;

	public StavkaOtpremniceDTO(int idStavkeOtpremnice, Long redniBroj, String naziv, Long cena, Long isporucenaKolicina,
			String napomena, int idOtpremnice, String jedinicaMere,int robaUslugaId, boolean obrisano) {
		super();
		this.idStavkeOtpremnice = idStavkeOtpremnice;
		this.redniBroj = redniBroj;
		this.naziv = naziv;
		this.cena = cena;
		this.isporucenaKolicina = isporucenaKolicina;
		this.napomena = napomena;
		this.idOtpremnice = idOtpremnice;
		this.robaUslugaId=robaUslugaId;
		this.jedinicaMere=jedinicaMere;
		this.obrisano=obrisano;
	}

	public StavkaOtpremniceDTO() {}
	
	
	
	public int getRobaUslugaId() {
		return robaUslugaId;
	}

	public void setRobaUslugaId(int robaUslugaId) {
		this.robaUslugaId = robaUslugaId;
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

	public int getIdOtpremnice() {
		return idOtpremnice;
	}

	public void setIdOtpremnice(int idOtpremnice) {
		this.idOtpremnice = idOtpremnice;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	
	
}
