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

import com.pi.PoslovnaInformatika.dto.CenovnikDTO;
import com.pi.PoslovnaInformatika.dto.PreduzeceDTO;

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
	
	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@Column(name="datum_vazenja", unique=false, nullable=false)
	private Date datum_vazenja;
	
	@Column(name="datum_kreiranja", unique=false, nullable=false)
	private Date datum_kreiranja;
	
	@Column(name="Aktivan", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean aktivan;
	
	@ManyToOne
	@JoinColumn(name = "preduzece_id", referencedColumnName = "preduzece_id", nullable = true)
	private Preduzece preduzece;
	
	
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "cenovnik")
	private Set<StavkaCenovnika> cene=new HashSet<StavkaCenovnika>();

	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	
	public Cenovnik() {
		super();
	}
	


	public Cenovnik(Integer id,String name, Date datum_vazenja,Date datum_kreiranja, Preduzece preduzece, Set<StavkaCenovnika> cene,boolean aktivan,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.datum_vazenja = datum_vazenja;
		this.datum_kreiranja = datum_kreiranja;
		this.preduzece = preduzece;
		this.cene = cene;
		this.aktivan = aktivan;
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



	public Date getDatum_vazenja() {
		return datum_vazenja;
	}



	public void setDatum_vazenja(Date datum_vazenja) {
		this.datum_vazenja = datum_vazenja;
	}

	

	public boolean isAktivan() {
		return aktivan;
	}



	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
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



	public boolean isObrisano() {
		return obrisano;
	}



	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}



	public Date getDatum_kreiranja() {
		return datum_kreiranja;
	}



	public void setDatum_kreiranja(Date datum_kreiranja) {
		this.datum_kreiranja = datum_kreiranja;
	}
	
	
	
	
}
