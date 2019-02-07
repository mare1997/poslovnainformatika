package com.pi.PoslovnaInformatika.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Otpremnica")
public class Otpremnica implements Serializable{

	
	private static final long serialVersionUID = -3516694711224808432L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOtpremnice;
	
	@Column(name="Broj_otpremnice")
	private Long brojOtpremnice;
	
	@Column(name="Datum_otpremnice")
	private Date datumOtpremnice;
	
	@Column(name="Primljena_roba", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean primljenaRoba;
	
	@Column(name="Datum_isporuke")
	private Date datumIsporuke;
	
	@JsonIgnore
	@OneToMany(mappedBy="otpremnica", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<StavkaOtpremnice> stavkeOtpremnice;
	
	@ManyToOne
	@JoinColumn(name = "prevoznik_id", referencedColumnName = "prevoznik_id", nullable = true)
	private Prevoznik prevoznik;
	
	@ManyToOne
	@JoinColumn(name = "kupac_id", referencedColumnName = "kupac_id", nullable = true)
	private Kupac kupac;
	
	@ManyToOne
	@JoinColumn(name = "preduzece_id", referencedColumnName = "preduzece_id", nullable = true)
	private Preduzece preduzece;
	
	@OneToOne
	@JoinColumn(name="fakturaRel")
	private Faktura fakturaRel;
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
	private User user;
	
	public Otpremnica() {}
	

	public Otpremnica(int idOtpremnice, Long brojOtpremnice, Date datumOtpremnice, boolean primljenaRoba,
			Date datumIsporuke, List<StavkaOtpremnice> stavkeOtpremnice, Prevoznik prevoznik, Kupac kupac,
			User user, Preduzece preduzece, Faktura fakturaRel,boolean obrisano) {
		super();
		this.idOtpremnice = idOtpremnice;
		this.brojOtpremnice = brojOtpremnice;
		this.datumOtpremnice = datumOtpremnice;
		this.primljenaRoba = primljenaRoba;
		this.datumIsporuke = datumIsporuke;
		this.stavkeOtpremnice = stavkeOtpremnice;
		this.prevoznik = prevoznik;
		this.kupac = kupac;
		this.user = user;
		this.preduzece = preduzece;
		this.fakturaRel = fakturaRel;
		this.obrisano=obrisano;
	}


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

	

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}


	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
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


	public List<StavkaOtpremnice> getStavkeOtpremnice() {
		return stavkeOtpremnice;
	}


	public void setStavkeOtpremnice(List<StavkaOtpremnice> stavkaOtpremnice) {
		this.stavkeOtpremnice = stavkaOtpremnice;
	}
	
	public void addStavkaOtpremnice(StavkaOtpremnice stavkaOtpremnice){
		if(stavkeOtpremnice.contains(stavkaOtpremnice)){
			return;
			
		}
		stavkeOtpremnice.add(stavkaOtpremnice);
		stavkaOtpremnice.setOtpremnica(this);
	}
	
	public void removeStavkaOtpremnice(StavkaOtpremnice stavkaOtpremnice){
		if(!stavkeOtpremnice.contains(stavkaOtpremnice)){
			return;
			
		}
		stavkeOtpremnice.remove(stavkaOtpremnice);
		if(stavkaOtpremnice.getOtpremnica().equals(this)){
			stavkaOtpremnice.setOtpremnica(null);
		}
	}
}
