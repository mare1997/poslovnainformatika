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
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<StavkaCenovnika> cene=new HashSet<StavkaCenovnika>();
	
	
	
	
	
}
