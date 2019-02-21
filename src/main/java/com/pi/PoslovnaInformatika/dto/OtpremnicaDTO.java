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
	private int user;
	private int preduzeceId;
	private int fakturaRel;
	private boolean obrisano;
	
	
	
	
	public OtpremnicaDTO(int idOtpremnice, Long brojOtpremnice, Date datumOtpremnice, boolean primljenaRoba,
			Date datumIsporuke, int prevoznikId, int kupacId, int user, int preduzeceId, int fakturaRel, boolean obrisano) {
		super();
		this.idOtpremnice = idOtpremnice;
		this.brojOtpremnice = brojOtpremnice;
		this.datumOtpremnice = datumOtpremnice;
		this.primljenaRoba = primljenaRoba;
		this.datumIsporuke = datumIsporuke;
		this.prevoznikId = prevoznikId;
		this.kupacId = kupacId;
		this.user = user;
		this.preduzeceId = preduzeceId;
		this.fakturaRel = fakturaRel;
		this.obrisano=obrisano;
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


	public boolean isObrisano() {
		return obrisano;
	}


	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}


	public int getKupacId() {
		return kupacId;
	}


	public void setKupacId(int kupacId) {
		this.kupacId = kupacId;
	}


	public int getUser() {
		return user;
	}


	public void setUser(int user) {
		this.user = user;
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


	@Override
	public String toString() {
		return "OtpremnicaDTO [idOtpremnice=" + idOtpremnice + ", brojOtpremnice=" + brojOtpremnice
				+ ", datumOtpremnice=" + datumOtpremnice + ", primljenaRoba=" + primljenaRoba + ", datumIsporuke="
				+ datumIsporuke + ", prevoznikId=" + prevoznikId + ", kupacId=" + kupacId + ", user=" + user
				+ ", preduzeceId=" + preduzeceId + ", fakturaRel=" + fakturaRel + ", obrisano=" + obrisano + "]";
	}
	
	
}
