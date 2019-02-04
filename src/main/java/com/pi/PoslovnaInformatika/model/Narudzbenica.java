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

@Entity(name="Narudzbenica")
@Table(name="Narudzbenica")
public class Narudzbenica implements Serializable{


	private static final long serialVersionUID = -9025140794203656828L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNarudzbenice;
	
	@Column(name="Broj_narudzbenice", columnDefinition="NUMBER")
	private Long brojNarudzbenice;
	
	@Column(name="Datum_izrade", columnDefinition="DATE")
	private Date datumIzrade;

	@Column(name="Datum_isporuke", columnDefinition="DATE")
	private Date datumIsporuke;
	
	@Column(name="Placena", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean placena;
	
	@JsonIgnore
	@OneToMany(mappedBy="narudzbenica", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<StavkaNarudzbenice> stavkeNarudzbenice;
	

	@OneToOne(mappedBy="narudzbenicaRel")
	private Faktura fakturaRel;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
	private User user;

	
	public Narudzbenica () {}

	public Narudzbenica(int idNarudzbenice, Long brojNarudzbenice, Date datumIzrade, Date datumIsporuke,
			boolean placena, List<StavkaNarudzbenice> stavkeNarudzbenice,Faktura fakturaRel,User user) {
		super();
		this.idNarudzbenice = idNarudzbenice;
		this.brojNarudzbenice = brojNarudzbenice;
		this.datumIzrade = datumIzrade;
		this.datumIsporuke = datumIsporuke;
		this.placena = placena;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
		this.fakturaRel = fakturaRel;
		this.user=user;
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

	
}
