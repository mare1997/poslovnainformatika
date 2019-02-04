package com.pi.PoslovnaInformatika.dto;

import java.sql.Date;

public class OtpremnicaDTO {

	private int idOtpremnice;
	private Long brojOtpremnice;
	private Date datumOtpremnice;
	private boolean primljenaRoba;
	private Date datumIsporuke;
	private int prevoznikId;
	private int kupacId;
	private int radnikId;
	private int preduzeceId;
	private int fakturaRel;
	
	
	
	
	public OtpremnicaDTO(int idOtpremnice, Long brojOtpremnice, Date datumOtpremnice, boolean primljenaRoba,
			Date datumIsporuke, int prevoznikId, int kupacId, int radnikId, int preduzeceId, int fakturaRel) {
		super();
		this.idOtpremnice = idOtpremnice;
		this.brojOtpremnice = brojOtpremnice;
		this.datumOtpremnice = datumOtpremnice;
		this.primljenaRoba = primljenaRoba;
		this.datumIsporuke = datumIsporuke;
		this.prevoznikId = prevoznikId;
		this.kupacId = kupacId;
		this.radnikId = radnikId;
		this.preduzeceId = preduzeceId;
		this.fakturaRel = fakturaRel;
	}


	public OtpremnicaDTO() {}


	public int getIdOtpremnice() {
		return idOtpremnice;
	}


	public void setIdOtpremnice(int idOtpremnice) {
		this.idOtpremnice = idOtpremnice;
	}


	public Long getBrojOtpremnice() {
		return brojOtpremnice;
	}


	public void setBrojOtpremnice(Long brojOtpremnice) {
		this.brojOtpremnice = brojOtpremnice;
	}


	public Date getDatumOtpremnice() {
		return datumOtpremnice;
	}


	public void setDatumOtpremnice(Date datumOtpremnice) {
		this.datumOtpremnice = datumOtpremnice;
	}


	public boolean isPrimljenaRoba() {
		return primljenaRoba;
	}


	public void setPrimljenaRoba(boolean primljenaRoba) {
		this.primljenaRoba = primljenaRoba;
	}


	public Date getDatumIsporuke() {
		return datumIsporuke;
	}


	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}


	public int getPrevoznikId() {
		return prevoznikId;
	}


	public void setPrevoznikId(int prevoznikId) {
		this.prevoznikId = prevoznikId;
	}


	public int getKupacId() {
		return kupacId;
	}


	public void setKupacId(int kupacId) {
		this.kupacId = kupacId;
	}


	public int getRadnikId() {
		return radnikId;
	}


	public void setRadnikId(int radnikId) {
		this.radnikId = radnikId;
	}


	public int getPreduzeceId() {
		return preduzeceId;
	}


	public void setPreduzeceId(int preduzeceId) {
		this.preduzeceId = preduzeceId;
	}


	public int getFakturaRel() {
		return fakturaRel;
	}


	public void setFakturaRel(int fakturaRel) {
		this.fakturaRel = fakturaRel;
	}
	
	
}
