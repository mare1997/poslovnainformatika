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
@Table(name="preduzece") 
public class Preduzece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="preduzece_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false, length = 50)
	private String name;
	
	@Column(name="adresa", unique=false, nullable=false, length = 50)
	private String adresa;
	
	@Column(name="email", unique=false, nullable=false, length = 50)
	private String email;
	
	@Column(name="telefon", unique=false, nullable=false, length = 50)
	private String telefon;
	
	@Column(name="pib", unique=true, nullable=false, length = 20)
	private long pib;
	
	@ManyToOne
	@JoinColumn(name = "mesto_id", referencedColumnName = "mesto_id", nullable = true)
	private Mesto mesto;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<Kupac> kupci=new HashSet<Kupac>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<GrupaRobe> grupe=new HashSet<GrupaRobe>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<Cenovnik> cenovnik=new HashSet<Cenovnik>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "preduzece")
	private Set<User> users=new HashSet<User>();
	
	
}
