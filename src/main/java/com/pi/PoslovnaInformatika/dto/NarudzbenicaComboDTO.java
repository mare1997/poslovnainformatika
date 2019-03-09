package com.pi.PoslovnaInformatika.dto;

import java.sql.Date;
import java.util.List;

import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;

public class NarudzbenicaComboDTO {

	
	/*brNarudzbenice,User,
	Datum izrade,
	Datum isporuke
	Datum Valute,
	listarobe (gruparobe,naziv i kolicina),
	Kupac (ime,adresa mesto, jmbg)
	Rabat,
	Prevoznik
	Napomena,
	isActive
	*/
	private int idKombo;
	private Long brojNarudzbenice;
	private String userIme;
	private Date datumIzrade;
	private Date datumIsporuke;
	private Date datumValute;
	private List<StavkaNarudzbenice> stavkeNarudzbenice;
	private String kupacIme;
	private String kupacAdresa;
	private String kupacMesto;
	private String kupacJMBG;
	private String prevoznikIme;
	private String napomena;
	private boolean aktivna;
	
	public NarudzbenicaComboDTO(){}
	
	

	public NarudzbenicaComboDTO(int idKombo, Long brojNarudzbenice,String userIme, Date datumIzrade, Date datumIsporuke,
			Date datumValute, List<StavkaNarudzbenice> stavkeNarudzbenice, String kupacIme, String kupacAdresa,
			String kupacMesto, String kupacJMBG, String prevoznikIme, String napomena, boolean aktivna) {
		super();
		this.idKombo = idKombo;
		this.brojNarudzbenice = brojNarudzbenice;
		this.userIme=userIme;
		this.datumIzrade = datumIzrade;
		this.datumIsporuke = datumIsporuke;
		this.datumValute = datumValute;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
		this.kupacIme = kupacIme;
		this.kupacAdresa = kupacAdresa;
		this.kupacMesto = kupacMesto;
		this.kupacJMBG = kupacJMBG;
		this.prevoznikIme = prevoznikIme;
		this.napomena = napomena;
		this.aktivna = aktivna;
	}



	public String getUserIme() {
		return userIme;
	}



	public void setUserIme(String userIme) {
		this.userIme = userIme;
	}



	public int getIdKombo() {
		return idKombo;
	}

	public void setIdKombo(int idKombo) {
		this.idKombo = idKombo;
	}

	public Long getBrojNarudzbenice() {
		return brojNarudzbenice;
	}

	public void setBrojNarudzbenice(Long brojNarudzbenice) {
		this.brojNarudzbenice = brojNarudzbenice;
	}

	public Date getDatumIzrade() {
		return datumIzrade;
	}

	public void setDatumIzrade(Date datumIzrade) {
		this.datumIzrade = datumIzrade;
	}

	public Date getDatumIsporuke() {
		return datumIsporuke;
	}

	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public List<StavkaNarudzbenice> getStavkeNarudzbenice() {
		return stavkeNarudzbenice;
	}

	public void setStavkeNarudzbenice(List<StavkaNarudzbenice> stavkeNarudzbenice) {
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}

	public String getKupacIme() {
		return kupacIme;
	}

	public void setKupacIme(String kupacIme) {
		this.kupacIme = kupacIme;
	}

	public String getKupacAdresa() {
		return kupacAdresa;
	}

	public void setKupacAdresa(String kupacAdresa) {
		this.kupacAdresa = kupacAdresa;
	}

	public String getKupacMesto() {
		return kupacMesto;
	}

	public void setKupacMesto(String kupacMesto) {
		this.kupacMesto = kupacMesto;
	}

	public String getKupacJMBG() {
		return kupacJMBG;
	}

	public void setKupacJMBG(String kupacJMBG) {
		this.kupacJMBG = kupacJMBG;
	}

	public String getPrevoznikIme() {
		return prevoznikIme;
	}

	public void setPrevoznikIme(String prevoznikIme) {
		this.prevoznikIme = prevoznikIme;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public boolean isAktivna() {
		return aktivna;
	}

	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}



	@Override
	public String toString() {
		return "NarudzbenicaComboDTO [idKombo=" + idKombo + ", brojNarudzbenice=" + brojNarudzbenice + ", userIme="
				+ userIme + ", datumIzrade=" + datumIzrade + ", datumIsporuke=" + datumIsporuke + ", datumValute="
				+ datumValute + ", stavkeNarudzbenice=" + stavkeNarudzbenice + ", kupacIme=" + kupacIme
				+ ", kupacAdresa=" + kupacAdresa + ", kupacMesto=" + kupacMesto + ", kupacJMBG=" + kupacJMBG
				+ ", prevoznikIme=" + prevoznikIme + ", napomena=" + napomena + ", aktivna=" + aktivna + "]";
	}



	
	
}
