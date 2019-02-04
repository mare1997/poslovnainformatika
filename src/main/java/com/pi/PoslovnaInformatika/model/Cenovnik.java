package com.pi.PoslovnaInformatika.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                 
@Table(name="cenovnik") 
public class Cenovnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="cenovnik_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="datum_vazenja", unique=false, nullable=false)
	private Date datum_vazenja;
	
	@ManyToOne
	@JoinColumn(name = "preduzece_id", referencedColumnName = "preduzece_id", nullable = true)
	private Preduzece preduzece;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "cenovnik")
	private Set<StavkaCenovnika> cene=new HashSet<StavkaCenovnika>();

	
	
	public Cenovnik() {
		super();
	}



	public Cenovnik(Integer id, Date datum_vazenja, Preduzece preduzece, Set<StavkaCenovnika> cene) {
		super();
		this.id = id;
		this.datum_vazenja = datum_vazenja;
		this.preduzece = preduzece;
		this.cene = cene;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Date getDatum_vazenja() {
		return datum_vazenja;
	}



	public void setDatum_vazenja(Date datum_vazenja) {
		this.datum_vazenja = datum_vazenja;
	}



	public Preduzece getPreduzece() {
		return preduzece;
	}



	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}



	public Set<StavkaCenovnika> getCene() {
		return cene;
	}



	public void setCene(Set<StavkaCenovnika> cene) {
		this.cene = cene;
	}
	
	
	
	
}
