package com.pi.PoslovnaInformatika.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                 
@Table(name="prevoznik") 
public class Prevoznik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="prevoznik_id", unique=true, nullable=false) 
	private Integer id;

	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "prevoznik")
	private Set<Otpremnica> otpremnice=new HashSet<Otpremnica>();
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;

	public Prevoznik() {
		super();
	}

	public Prevoznik(Integer id, String name, Set<Otpremnica> otpremnice,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.otpremnice = otpremnice;
		this.obrisano = obrisano;
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

	public Set<Otpremnica> getOtpremnice() {
		return otpremnice;
	}

	public void setOtpremnice(Set<Otpremnica> otpremnice) {
		this.otpremnice = otpremnice;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
}
