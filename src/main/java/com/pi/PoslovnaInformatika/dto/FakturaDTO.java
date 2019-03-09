package com.pi.PoslovnaInformatika.dto;

import java.sql.Date;

import com.pi.PoslovnaInformatika.model.Kupac;
import com.pi.PoslovnaInformatika.model.Narudzbenica;
import com.pi.PoslovnaInformatika.model.Otpremnica;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.model.User;

public class FakturaDTO {


	private int idFakture;
	private Long brojFakture;
	private Date datumValute;
	private Date datumFakture;
	private Long osnovica;
	private Long ukupanPDV;
	private Long iznosZaPlacanje;
	private String statusFakture;
	private Narudzbenica narudzbeniceRel;
	private Otpremnica otpremnicaRel;
	private Kupac kupac;
	private User user;
	private boolean obrisano;
	private Preduzece preduzece;
	
	
	public FakturaDTO () {}

	

	public FakturaDTO(int idFakture, Long brojFakture, Date datumValute, Date datumFakture, Long osnovica,
			Long ukupanPDV, Long iznosZaPlacanje, String statusFakture, Narudzbenica narudzbeniceRel, Otpremnica otpremnicaRel,
			Kupac kupac,User user,boolean obrisano,Preduzece preduzece) {
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



	public Narudzbenica getNarudzbeniceRel() {
		return narudzbeniceRel;
	}



	public void setNarudzbeniceRel(Narudzbenica narudzbeniceRel) {
		this.narudzbeniceRel = narudzbeniceRel;
	}



	public Otpremnica getOtpremnicaRel() {
		return otpremnicaRel;
	}



	public void setOtpremnicaRel(Otpremnica otpremnicaRel) {
		this.otpremnicaRel = otpremnicaRel;
	}



	public Kupac getKupac() {
		return kupac;
	}



	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Preduzece getPreduzece() {
		return preduzece;
	}



	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}



	
	
}
