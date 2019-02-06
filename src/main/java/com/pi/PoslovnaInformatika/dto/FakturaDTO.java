package com.pi.PoslovnaInformatika.dto;

import java.sql.Date;

public class FakturaDTO {


	private int idFakture;
	private Long brojFakture;
	private Date datumValute;
	private Date datumFakture;
	private Long osnovica;
	private Long ukupanPDV;
	private Long iznosZaPlacanje;
	private String statusFakture;
	private int narudzbeniceRel;
	private int otpremnicaRel;
	private int kupac;
	private int user;
	private boolean obrisano;
	private int preduzece;
	
	
	public FakturaDTO () {}

	

	public FakturaDTO(int idFakture, Long brojFakture, Date datumValute, Date datumFakture, Long osnovica,
			Long ukupanPDV, Long iznosZaPlacanje, String statusFakture, int narudzbeniceRel, int otpremnicaRel,
			int kupac,int user,boolean obrisano,int preduzece) {
		super();
		this.idFakture = idFakture;
		this.brojFakture = brojFakture;
		this.datumValute = datumValute;
		this.datumFakture = datumFakture;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.statusFakture = statusFakture;
		this.narudzbeniceRel = narudzbeniceRel;
		this.otpremnicaRel = otpremnicaRel;
		this.kupac = kupac;
		this.user=user;
		this.obrisano=obrisano;
		this.preduzece=preduzece;
	}



	public int getUser() {
		return user;
	}



	public void setUser(int user) {
		this.user = user;
	}



	public int getIdFakture() {
		return idFakture;
	}

	public void setIdFakture(int idFakture) {
		this.idFakture = idFakture;
	}

	public Long getBrojFakture() {
		return brojFakture;
	}

	public void setBrojFakture(Long brojFakture) {
		this.brojFakture = brojFakture;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public Date getDatumFakture() {
		return datumFakture;
	}

	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}

	
	public boolean isObrisano() {
		return obrisano;
	}



	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}



	public int getPreduzece() {
		return preduzece;
	}



	public void setPreduzece(int preduzece) {
		this.preduzece = preduzece;
	}



	public Long getOsnovica() {
		return osnovica;
	}

	public void setOsnovica(Long osnovica) {
		this.osnovica = osnovica;
	}

	public Long getUkupanPDV() {
		return ukupanPDV;
	}

	public void setUkupanPDV(Long ukupanPDV) {
		this.ukupanPDV = ukupanPDV;
	}

	public Long getIznosZaPlacanje() {
		return iznosZaPlacanje;
	}

	public void setIznosZaPlacanje(Long iznosZaPlacanje) {
		this.iznosZaPlacanje = iznosZaPlacanje;
	}

	public String getStatusFakture() {
		return statusFakture;
	}

	public void setStatusFakture(String statusFakture) {
		this.statusFakture = statusFakture;
	}



	public int getNarudzbeniceRel() {
		return narudzbeniceRel;
	}



	public void setNarudzbeniceRel(int narudzbeniceRel) {
		this.narudzbeniceRel = narudzbeniceRel;
	}



	public int getOtpremnicaRel() {
		return otpremnicaRel;
	}



	public void setOtpremnicaRel(int otpremnicaRel) {
		this.otpremnicaRel = otpremnicaRel;
	}



	public int getKupac() {
		return kupac;
	}



	public void setKupac(int kupac) {
		this.kupac = kupac;
	}
	
	
}
