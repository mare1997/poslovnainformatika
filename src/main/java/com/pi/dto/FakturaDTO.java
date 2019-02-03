package com.pi.dto;

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
	
	public FakturaDTO () {}

	public FakturaDTO(int idFakture, Long brojFakture, Date datumValute, Date datumFakture, Long osnovica,
			Long ukupanPDV, Long iznosZaPlacanje, String statusFakture) {
		super();
		this.idFakture = idFakture;
		this.brojFakture = brojFakture;
		this.datumValute = datumValute;
		this.datumFakture = datumFakture;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.statusFakture = statusFakture;
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
	
	
}
