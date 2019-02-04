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
@Table(name="mesto") 
public class Mesto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="mesto_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="grad", unique=false, nullable=false, length = 50)
	private String grad;
	
	@Column(name="postanski_broj", unique=true, nullable=false, length = 20)
	private long postanski_broj;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "mesto")
	private Set<Preduzece> preduzeca=new HashSet<Preduzece>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "mesto")
	private Set<Kupac> kupci=new HashSet<Kupac>();

	public Mesto(Integer id, String grad, long postanski_broj, Set<Preduzece> preduzeca, Set<Kupac> kupci) {
		super();
		this.id = id;
		this.grad = grad;
		this.postanski_broj = postanski_broj;
		this.preduzeca = preduzeca;
		this.kupci = kupci;
	}

	public Mesto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public long getPostanski_broj() {
		return postanski_broj;
	}

	public void setPostanski_broj(long postanski_broj) {
		this.postanski_broj = postanski_broj;
	}

	public Set<Preduzece> getPreduzeca() {
		return preduzeca;
	}

	public void setPreduzeca(Set<Preduzece> preduzeca) {
		this.preduzeca = preduzeca;
	}

	public Set<Kupac> getKupci() {
		return kupci;
	}

	public void setKupci(Set<Kupac> kupci) {
		this.kupci = kupci;
	}
	
	
}
