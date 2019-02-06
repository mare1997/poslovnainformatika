package com.pi.PoslovnaInformatika.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="Faktura")
@Table(name="Faktura")
public class Faktura implements Serializable{

	
	private static final long serialVersionUID = 4156764521915772558L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFakture;
	
	@Column(name="Broj_fakture", columnDefinition="NUMBER")
	private Long brojFakture;
	
	@Column(name="Datum_valute", columnDefinition="DATE")
	private Date datumValute;
	
	@Column(name="Datum_fakture", columnDefinition="DATE")
	private Date datumFakture;
	
	@Column(name="Osnovica", columnDefinition="NUMBER")
	private Long osnovica;
	
	@Column(name="Ukupan_PDV", columnDefinition="NUMBER")
	private Long ukupanPDV;
	
	@Column(name="Iznos_za_placanje", columnDefinition="NUMBER")
	private Long iznosZaPlacanje;
	
	@Column(name="Status_fakture", columnDefinition="CHAR(2)")
	private String statusFakture;

	@JsonIgnore
	@OneToMany(mappedBy="faktura", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<StavkaFakture> stavkeFakture;
	
	@OneToOne
	@JoinColumn(name="narudzbenicaRel")
	private Narudzbenica narudzbenicaRel;
	
	@OneToOne(mappedBy="fakturaRel")
	private Otpremnica otpremnicaRel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kupac_id")
	private Kupac kupac;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="preduzece")
	private Preduzece preduzece;
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
	private User user;
	
	public Faktura() {}

	
	
	public Faktura(int id, Long brojFakture, Date datumValute,
			Date datumFakture, Long osnovica, Long ukupanPDV,
			Long iznosZaPlacanje, String statusFakture,
			List<StavkaFakture> stavkeFakture, Narudzbenica narudzbenicaRel,
			Otpremnica otpremnicaRel,Kupac kupac, User user,Preduzece preduzece,boolean obrisano) {
		super();
		this.idFakture = id;
		this.brojFakture = brojFakture;
		this.datumValute = datumValute;
		this.datumFakture = datumFakture;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.statusFakture = statusFakture;
		this.stavkeFakture = stavkeFakture;
		this.narudzbenicaRel=narudzbenicaRel;
		this.otpremnicaRel=otpremnicaRel;
		this.kupac=kupac;
		this.user=user;
		this.preduzece=preduzece;
		this.obrisano=obrisano;
	}



	
	public int getId() {
		return idFakture;
	}

	public void setId(int id) {
		this.idFakture = id;
	}

	public Long getBrojFakture() {
		return brojFakture;
	}

	public void setBrojFakture(Long brojFakture) {
		this.brojFakture = brojFakture;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public Date getDatumFakture() {
		return datumFakture;
	}

	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}

	public Long getOsnovica() {
		return osnovica;
	}

	public void setOsnovica(Long osnovica) {
		this.osnovica = osnovica;
	}

	public Long getUkupanPDV() {
		return ukupanPDV;
	}

	public void setUkupanPDV(Long ukupanPDV) {
		this.ukupanPDV = ukupanPDV;
	}

	public Long getIznosZaPlacanje() {
		return iznosZaPlacanje;
	}

	public void setIznosZaPlacanje(Long iznosZaPlacanje) {
		this.iznosZaPlacanje = iznosZaPlacanje;
	}

	public Narudzbenica getNarudzbenicaRel() {
		return narudzbenicaRel;
	}

	public void setNarudzbenicaRel(Narudzbenica narudzbenicaRel) {
		this.narudzbenicaRel = narudzbenicaRel;
	}
	
	

	public Otpremnica getOtpremnicaRel() {
		return otpremnicaRel;
	}



	public void setOtpremnicaRel(Otpremnica otpremnicaRel) {
		this.otpremnicaRel = otpremnicaRel;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Kupac getKupac() {
		return kupac;
	}



	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}



	public Preduzece getPreduzece() {
		return preduzece;
	}



	public boolean isObrisano() {
		return obrisano;
	}



	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}



	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}



	public String getStatusFakture() {
		return statusFakture;
	}

	public void setStatusFakture(String statusFakture) {
		this.statusFakture = statusFakture;
	}

	public List<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}

	public void setStavkeFakture(List<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}
	
	public void addStavkaFakture(StavkaFakture stavkaFakture){
		if(stavkeFakture.contains(stavkaFakture)){
			return;
			
		}
		stavkeFakture.add(stavkaFakture);
		stavkaFakture.setFaktura(this);
	}
	
	public void removeStavkaFakture(StavkaFakture stavkaFakture){
		if(!stavkeFakture.contains(stavkaFakture)){
			return;
			
		}
		stavkeFakture.remove(stavkaFakture);
		if(stavkaFakture.getFaktura().equals(this)){
			stavkaFakture.setFaktura(null);
		}
	}
	
}
