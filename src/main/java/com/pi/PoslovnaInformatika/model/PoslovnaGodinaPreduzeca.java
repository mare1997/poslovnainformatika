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
@Table(name="poslovna_godina")
public class PoslovnaGodinaPreduzeca implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="poslovna_godina_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="godina", unique=true, nullable=false, length = 4)
	private String godina;
	
	@ManyToOne
	@JoinColumn(name = "preduzece_id", referencedColumnName = "preduzece_id", nullable = true)
	private Preduzece preduzece;
	
	@Column(name="zavrsena", columnDefinition="BOOLEAN DEFAULT FALSE")
	private Boolean zavrsena;

	public PoslovnaGodinaPreduzeca() {
		super();
		
	}
	
	public PoslovnaGodinaPreduzeca(Integer id, String godina, Boolean zavrsena) {
		super();
		this.id = id;
		this.godina = godina;
		this.zavrsena = zavrsena;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGodina() {
		return godina;
	}

	public void setGodina(String godina) {
		this.godina = godina;
	}

	public Boolean getZavrsena() {
		return zavrsena;
	}

	public void setZavrsena(Boolean zavrsena) {
		this.zavrsena = zavrsena;
	}
	
	
}
