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
@Table(name="pdv") 
public class PDV implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="pdv_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "pdv")
	private Set<StopaPDV> stope=new HashSet<StopaPDV>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "pdv")
	private Set<GrupaRobe> grupe=new HashSet<GrupaRobe>();

	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	public PDV() {
		super();
	}



	public PDV(Integer id, String name, Set<StopaPDV> stope, Set<GrupaRobe> grupe,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.stope = stope;
		this.grupe = grupe;
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



	public Set<StopaPDV> getStope() {
		return stope;
	}



	public void setStope(Set<StopaPDV> stope) {
		this.stope = stope;
	}



	public Set<GrupaRobe> getGrupe() {
		return grupe;
	}



	public void setGrupe(Set<GrupaRobe> grupe) {
		this.grupe = grupe;
	}
	
	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
}
