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
@Table(name="Narudzbenica")
public class Narudzbenica implements Serializable{


	private static final long serialVersionUID = -9025140794203656828L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNarudzbenice;
	
	@Column(name="Broj_narudzbenice")
	private Long brojNarudzbenice;
	
	@Column(name="Datum_izrade")
	private Date datumIzrade;

	@Column(name="Datum_isporuke")
	private Date datumIsporuke;
	
	@Column(name="Placena", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean placena;
	
	@JsonIgnore
	@OneToMany(mappedBy="narudzbenica", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<StavkaNarudzbenice> stavkeNarudzbenice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="preduzece")
	private Preduzece preduzece;

	@OneToOne()
	@JoinColumn(name="fakturaRel")
	private Faktura fakturaRel;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kupac_id")
	private Kupac kupac;
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
	private User user;

	
	public Narudzbenica () {}

	public Narudzbenica(int idNarudzbenice, Long brojNarudzbenice, Date datumIzrade, Date datumIsporuke,
			boolean placena, List<StavkaNarudzbenice> stavkeNarudzbenice,Faktura fakturaRel,User user, Preduzece preduzece, Kupac kupac, boolean obrisano) {
		super();
		this.idNarudzbenice = idNarudzbenice;
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumIzrade = datumIzrade;
		this.datumIsporuke = datumIsporuke;
		this.placena = placena;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
		this.fakturaRel = fakturaRel;
		this.user=user;
		this.preduzece=preduzece;
		this.kupac=kupac;
		this.obrisano=obrisano;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
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

	public boolean isPlacena() {
		return placena;
	}

	public void setPlacena(boolean placena) {
		this.placena = placena;
	}

	public List<StavkaNarudzbenice> getStavkeNarudzbenice() {
		return stavkeNarudzbenice;
	}

	public void setStavkeNarudzbenice(List<StavkaNarudzbenice> stavkeNarudzbenice) {
		this.stavkeNarudzbenice = stavkeNarudzbenice;
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

	public void addStavkaNarudzbenice(StavkaNarudzbenice stavkaNarudzbenice){
		if(stavkeNarudzbenice.contains(stavkaNarudzbenice)){
			return;
			
		}
		stavkeNarudzbenice.add(stavkaNarudzbenice);
		stavkaNarudzbenice.setNarudzbenica(this);
	}
	
	public void removeStavkaNarudzbenice(StavkaNarudzbenice stavkaNarudzbenice){
		if(!stavkeNarudzbenice.contains(stavkaNarudzbenice)){
			return;
			
		}
		stavkeNarudzbenice.remove(stavkaNarudzbenice);
		if(stavkaNarudzbenice.getNarudzbenica().equals(this)){
			stavkaNarudzbenice.setNarudzbenica(null);
		}
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

	
}
