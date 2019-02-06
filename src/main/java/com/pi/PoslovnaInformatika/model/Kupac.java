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
@Table(name="kupac") 
public class Kupac implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="kupac_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@Column(name="adresa", unique=false, nullable=false, length = 50)
	private String adresa;
	
	@Column(name="pib_jmbg", unique=true, nullable=false, length = 20)
	private long pib_jmbg;
	
	@ManyToOne
	@JoinColumn(name = "mesto_id", referencedColumnName = "mesto_id", nullable = true)
	private Mesto mesto;
	
	@ManyToOne
	@JoinColumn(name = "preduzece_id", referencedColumnName = "preduzece_id", nullable = true)
	private Preduzece preduzece;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "kupac")
	private Set<Otpremnica> otpremnice=new HashSet<Otpremnica>();
	
	@OneToMany(mappedBy="kupac",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Narudzbenica> narudzbenice;

	@OneToMany(mappedBy="kupac",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Faktura> fakture;
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	public Kupac() {
		super();
	}

	public Kupac(Integer id, String name, String adresa, long pib_jmbg, Mesto mesto, Preduzece preduzece,
			Set<Otpremnica> otpremnice,List<Faktura> fakture,List<Narudzbenica> narudzbenice,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.adresa = adresa;
		this.pib_jmbg = pib_jmbg;
		this.mesto = mesto;
		this.preduzece = preduzece;
		this.otpremnice = otpremnice;
		this.fakture=fakture;
		this.narudzbenice=narudzbenice;
		this.obrisano=obrisano;
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

	public long getPib_jmbg() {
		return pib_jmbg;
	}

	public void setPib_jmbg(long pib_jmbg) {
		this.pib_jmbg = pib_jmbg;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public Set<Otpremnica> getOtpremnice() {
		return otpremnice;
	}

	public void setOtpremnice(Set<Otpremnica> otpremnice) {
		this.otpremnice = otpremnice;
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
