package com.pi.PoslovnaInformatika.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity                 
@Table(name="users") 
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public enum UserType {ADMIN,USER}
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="user_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="firstname", unique=false, nullable=false, length = 30)
	private String firstname;
	
	@Column(name="lastname", unique=false, nullable=false, length = 30)
	private String lastname;
	
	@Column(name="username", unique=true, nullable=false, length = 30)
	private String username;
	  
	@Column(name="user_pasword", unique=false, nullable=false, length = 10)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_type", unique=false, nullable=false)
	private UserType userType;

	@ManyToOne
	@JoinColumn(name = "preduzece_id", referencedColumnName = "preduzece_id", nullable = true)
	private Preduzece preduzece;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "user")
	private Set<Narudzbenica> narudzbenice=new HashSet<Narudzbenica>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "user")
	private Set<Faktura> fakture=new HashSet<Faktura>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "user")
	private Set<Otpremnica> otpremnica=new HashSet<Otpremnica>();
	
	public User() {
		super();
	}
	
	public User(Integer id, String firstname, String lastname, String username, String password, UserType userType,
			Preduzece preduzece, Set<Narudzbenica> narudzbenice, Set<Faktura> fakture, Set<Otpremnica> otpremnica) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.preduzece = preduzece;
		this.narudzbenice = narudzbenice;
		this.fakture = fakture;
		this.otpremnica = otpremnica;
	}

	





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_password() {
		return password;
	}

	public void setUser_password(String user_password) {
		this.password = user_password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public Set<Narudzbenica> getNarudzbenice() {
		return narudzbenice;
	}

	public void setNarudzbenice(Set<Narudzbenica> narudzbenice) {
		this.narudzbenice = narudzbenice;
	}

	public Set<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
	}

	public Set<Otpremnica> getOtpremnica() {
		return otpremnica;
	}

	public void setOtpremnica(Set<Otpremnica> otpremnica) {
		this.otpremnica = otpremnica;
	}


	
	
	
	
	
	
}
