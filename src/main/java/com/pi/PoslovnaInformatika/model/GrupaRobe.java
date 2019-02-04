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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                 
@Table(name="grupa_robe") 
public class GrupaRobe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="grupa_robe_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "pdv_id", referencedColumnName = "pdv_id", nullable = true)
	private PDV pdv;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "grupa")
	private Set<Roba> robe=new HashSet<Roba>();
	
	@ManyToOne
	@JoinColumn(name = "preduzece_id", referencedColumnName = "preduzece_id", nullable = true)
	private Preduzece preduzece;

	
	
	public GrupaRobe() {
		super();
	}

	public GrupaRobe(Integer id, String name, PDV pdv, Set<Roba> robe, Preduzece preduzece) {
		super();
		this.id = id;
		this.name = name;
		this.pdv = pdv;
		this.robe = robe;
		this.preduzece = preduzece;
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

	public PDV getPdv() {
		return pdv;
	}

	public void setPdv(PDV pdv) {
		this.pdv = pdv;
	}

	public Set<Roba> getRobe() {
		return robe;
	}

	public void setRobe(Set<Roba> robe) {
		this.robe = robe;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}
	
	
}
