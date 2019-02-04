package com.pi.PoslovnaInformatika.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity                 
@Table(name="stavka_cenovnika") 
public class StavkaCenovnika implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="stavka_cenovnika_id", unique=true, nullable=false) 
	private Integer id;
	

	@Column(name="cena", unique=false, nullable=false)
	private long cena;
	
	@ManyToOne
	@JoinColumn(name = "roba_id", referencedColumnName = "roba_id", nullable = true)
	private Roba roba;
	
	@ManyToOne
	@JoinColumn(name = "cenovnik_id", referencedColumnName = "cenovnik_id", nullable = true)
	private Cenovnik cenovnik;

	public StavkaCenovnika() {
		super();
	}

	public StavkaCenovnika(Integer id, long cena, Roba roba, Cenovnik cenovnik) {
		super();
		this.id = id;
		this.cena = cena;
		this.roba = roba;
		this.cenovnik = cenovnik;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getCena() {
		return cena;
	}

	public void setCena(long cena) {
		this.cena = cena;
	}

	public Roba getRoba() {
		return roba;
	}

	public void setRoba(Roba roba) {
		this.roba = roba;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}
	
	
	
}
