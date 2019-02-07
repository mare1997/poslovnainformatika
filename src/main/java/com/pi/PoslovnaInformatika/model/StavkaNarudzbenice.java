package com.pi.PoslovnaInformatika.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Stavka_narudzbenice")
public class StavkaNarudzbenice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1229066187100033139L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStavkeNarudzbenice;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(30)")
	private String naziv;
	
	@Column(name="Kolicina")
	private Long kolicina;
	
	@Column(name="Jedinica_mere",columnDefinition="VARCHAR(20)")
	private String jedinicaMere;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idNarudzbenice")
	private Narudzbenica narudzbenica;
	
	@Column(name="Obrisano", columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean obrisano;
	
	public StavkaNarudzbenice () {}

	

	public StavkaNarudzbenice(int idStavkeNarudzbenice, String naziv, Long kolicina, Narudzbenica narudzbenica,String jedinicaMere,boolean obrisano) {
		super();
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.narudzbenica = narudzbenica;
		this.jedinicaMere=jedinicaMere;
		this.obrisano=obrisano;
	}



	public String getJedinicaMere() {
		return jedinicaMere;
	}



	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}



	public int getIdStavkeNarudzbenice() {
		return idStavkeNarudzbenice;
	}



	public void setIdStavkeNarudzbenice(int idStavkeNarudzbenice) {
		this.idStavkeNarudzbenice = idStavkeNarudzbenice;
	}



	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}



	public boolean isObrisano() {
		return obrisano;
	}



	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}



	public Long getKolicina() {
		return kolicina;
	}



	public void setKolicina(Long kolicina) {
		this.kolicina = kolicina;
	}



	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}



	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
		
		if(narudzbenica !=null && !narudzbenica.getStavkeNarudzbenice().contains(this)){
			narudzbenica.addStavkaNarudzbenice(this);
		}
	}
	
	
}
