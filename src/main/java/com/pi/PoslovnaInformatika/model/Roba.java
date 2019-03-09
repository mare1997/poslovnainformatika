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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity                 
@Table(name="roba") 
public class Roba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="roba_id", unique=true, nullable=false) 
	private Integer id;

	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@Column(name="jedninica_mere", unique=false, nullable=false, length = 50)
	private String jedninica_mere;
	
	@ManyToOne
	@JoinColumn(name = "grupa_robe_id", referencedColumnName = "grupa_robe_id", nullable = true)
	private GrupaRobe grupa;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "roba")
	private Set<StavkaCenovnika> cene = new HashSet<StavkaCenovnika>();
	
	@OneToMany(mappedBy="roba_usluga_id", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<StavkaFakture> stavkeFakture;

	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	public Roba() {}
	
	
	public Roba(Integer id, String name, String jedninica_mere, GrupaRobe grupa, Set<StavkaCenovnika> cene,
			List<StavkaFakture> stavkeFakture,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.jedninica_mere = jedninica_mere;
		this.grupa = grupa;
		this.cene = cene;
		this.stavkeFakture = stavkeFakture;
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


	public String getJedninica_mere() {
		return jedninica_mere;
	}


	public void setJedninica_mere(String jedninica_mere) {
		this.jedninica_mere = jedninica_mere;
	}


	public GrupaRobe getGrupa() {
		return grupa;
	}


	public void setGrupa(GrupaRobe grupa) {
		this.grupa = grupa;
	}


	


	public Set<StavkaCenovnika> getCene() {
		return cene;
	}


	public void setCene(Set<StavkaCenovnika> cene) {
		this.cene = cene;
	}


	public List<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}


	public void setStavkeFakture(List<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}


	public boolean isObrisano() {
		return obrisano;
	}


	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
}
