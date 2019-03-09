package com.pi.PoslovnaInformatika.dto;

import java.sql.Date;

import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.model.Kupac;
import com.pi.PoslovnaInformatika.model.Preduzece;
import com.pi.PoslovnaInformatika.model.User;

public class NarudzbenicaDTO {

	

	private int idNarudzbenice;	
	private Long brojNarudzbenice;
	private Date datumIzrade;
	private Date datumIsporuke;
	private boolean aktivna;
	private Faktura fakturaRel;
	private User user;
	private Preduzece preduzece;
	private boolean obrisano;
	private Kupac kupac;
	
	public NarudzbenicaDTO() {}
	
	public NarudzbenicaDTO(int idNarudzbenice, Long brojNarudzbenice, Date datumIzrade, Date datumIsporuke,
			boolean aktivna, Faktura fakturaRel, User user, Preduzece preduzece,Kupac kupac, boolean obrisano) {
		super();
		this.idNarudzbenice = idNarudzbenice;
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumIzrade = datumIzrade;
		this.datumIsporuke = datumIsporuke;
		this.aktivna = aktivna;
		this.fakturaRel=fakturaRel;
		this.user=user;
		this.obrisano=obrisano;
		this.preduzece=preduzece;
		this.kupac=kupac;

	}
	

	public int getIdNarudzbenice() {
		return idNarudzbenice;
	}
	public void setIdNarudzbenice(int idNarudzbenice) {
		this.idNarudzbenice = idNarudzbenice;
	}
	public Long getBrojNarudzbenice() {
		return brojNarudzbenice;
	}
	public void setBrojNarudzbenice(Long brojNarudzbenice) {
		this.brojNarudzbenice = brojNarudzbenice;
	}
	public Date getDatumIzrade() {
		return datumIzrade;
	}
	public void setDatumIzrade(Date datumIzrade) {
		this.datumIzrade = datumIzrade;
	}
	public Date getDatumIsporuke() {
		return datumIsporuke;
	}
	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}
	public boolean isAktivna() {
		return aktivna;
	}
	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}

	

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}



	public Faktura getFakturaRel() {
		return fakturaRel;
	}

	public void setFakturaRel(Faktura fakturaRel) {
		this.fakturaRel = fakturaRel;
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

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	@Override
	public String toString() {
		return "NarudzbenicaDTO [idNarudzbenice=" + idNarudzbenice + ", brojNarudzbenice=" + brojNarudzbenice
				+ ", datumIzrade=" + datumIzrade + ", datumIsporuke=" + datumIsporuke + ", aktivna=" + aktivna
				+ ", fakturaRel=" + fakturaRel + ", user=" + user + ", preduzece=" + preduzece + ", obrisano="
				+ obrisano + ", kupac=" + kupac + "]";
	}
	
	
	
}
