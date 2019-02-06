package com.pi.PoslovnaInformatika.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity                 
@Table(name="preduzece") 
public class Preduzece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="preduzece_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@Column(name="adresa", unique=false, nullable=false, length = 50)
	private String adresa;
	
	@Column(name="email", unique=false, nullable=false, length = 50)
	private String email;
	
	@Column(name="telefon", unique=false, nullable=false, length = 50)
	private String telefon;
	
	@Column(name="pib", unique=true, nullable=false, length = 20)
	private long pib;
	
	@ManyToOne
	@JoinColumn(name = "mesto_id", referencedColumnName = "mesto_id", nullable = true)
	private Mesto mesto;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<Kupac> kupci=new HashSet<Kupac>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<GrupaRobe> grupe=new HashSet<GrupaRobe>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<Cenovnik> cenovnik=new HashSet<Cenovnik>();
	

	@OneToMany(mappedBy="preduzece",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Otpremnica> otpremnice;
	
	@OneToMany(mappedBy="preduzece",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Faktura> fakture;
	
	@OneToMany(mappedBy="preduzece",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Narudzbenica> narudzbenice;
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<User> users=new HashSet<User>();

	public Preduzece() {}
	


	public Preduzece(Integer id, String name, String adresa, String email, String telefon, long pib, Mesto mesto,
			Set<Kupac> kupci, Set<GrupaRobe> grupe, Set<Cenovnik> cenovnik, List<Otpremnica> otpremnice,
			List<Faktura> fakture, List<Narudzbenica> narudzbenice, boolean obrisano, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.adresa = adresa;
		this.email = email;
		this.telefon = telefon;
		this.pib = pib;
		this.mesto = mesto;
		this.kupci = kupci;
		this.grupe = grupe;
		this.cenovnik = cenovnik;
		this.otpremnice = otpremnice;
		this.fakture = fakture;
		this.narudzbenice = narudzbenice;
		this.obrisano = obrisano;
		this.users = users;
	}





	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public long getPib() {
		return pib;
	}


	public void setPib(long pib) {
		this.pib = pib;
	}


	public Mesto getMesto() {
		return mesto;
	}


	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}


	public Set<Kupac> getKupci() {
		return kupci;
	}


	public void setKupci(Set<Kupac> kupci) {
		this.kupci = kupci;
	}


	public Set<GrupaRobe> getGrupe() {
		return grupe;
	}


	public void setGrupe(Set<GrupaRobe> grupe) {
		this.grupe = grupe;
	}


	public Set<Cenovnik> getCenovnik() {
		return cenovnik;
	}


	public void setCenovnik(Set<Cenovnik> cenovnik) {
		this.cenovnik = cenovnik;
	}


	public List<Otpremnica> getOtpremnice() {
		return otpremnice;
	}


	public void setOtpremnice(List<Otpremnica> otpremnice) {
		this.otpremnice = otpremnice;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}



	public List<Faktura> getFakture() {
		return fakture;
	}



	public void setFakture(List<Faktura> fakture) {
		this.fakture = fakture;
	}



	public List<Narudzbenica> getNarudzbenice() {
		return narudzbenice;
	}



	public void setNarudzbenice(List<Narudzbenica> narudzbenice) {
		this.narudzbenice = narudzbenice;
	}



	public boolean isObrisano() {
		return obrisano;
	}



	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	
	
}
