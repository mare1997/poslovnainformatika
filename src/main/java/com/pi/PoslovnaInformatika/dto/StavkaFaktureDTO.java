package com.pi.PoslovnaInformatika.dto;

public class StavkaFaktureDTO {

	private int idStavkeFakture;
	private Long kolicina;
	private Long jedinicnaCena;
	private Long rabat;
	private Long osnovicaZaPDV;
	private Long procenatPDV;
	private Long iznosPDV;
	private Long iznosStavke;
	private int idFakture;
	private int robaUslugaId;
	private String jedinicaMere;
	private boolean obrisano;
	
	public StavkaFaktureDTO() {}

	public StavkaFaktureDTO(int idStavkeFakture, Long kolicina, Long jedinicnaCena, Long rabat, Long osnovicaZaPDV,
			Long procenatPDV, Long iznosPDV, Long iznosStavke, int idFakture, int robaUslugaId, String jedinicaMere, boolean obrisano) {
		super();
		this.idStavkeFakture = idStavkeFakture;
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovicaZaPDV = osnovicaZaPDV;
		this.procenatPDV = procenatPDV;
		this.iznosPDV = iznosPDV;
		this.iznosStavke = iznosStavke;
		this.idFakture = idFakture;
		this.robaUslugaId=robaUslugaId;
		this.jedinicaMere=jedinicaMere;
		this.obrisano=obrisano;
	}

	public int getIdStavkeFakture() {
		return idStavkeFakture;
	}

	public void setIdStavkeFakture(int idStavkeFakture) {
		this.idStavkeFakture = idStavkeFakture;
	}

	public Long getKolicina() {
		return kolicina;
	}

	public void setKolicina(Long kolicina) {
		this.kolicina = kolicina;
	}

	public Long getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(Long jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public Long getRabat() {
		return rabat;
	}

	public void setRabat(Long rabat) {
		this.rabat = rabat;
	}

	public Long getOsnovicaZaPDV() {
		return osnovicaZaPDV;
	}

	public void setOsnovicaZaPDV(Long osnovicaZaPDV) {
		this.osnovicaZaPDV = osnovicaZaPDV;
	}

	public Long getProcenatPDV() {
		return procenatPDV;
	}

	public void setProcenatPDV(Long procenatPDV) {
		this.procenatPDV = procenatPDV;
	}

	public Long getIznosPDV() {
		return iznosPDV;
	}

	public void setIznosPDV(Long iznosPDV) {
		this.iznosPDV = iznosPDV;
	}

	public Long getIznosStavke() {
		return iznosStavke;
	}

	public void setIznosStavke(Long iznosStavke) {
		this.iznosStavke = iznosStavke;
	}

	public int getIdFakture() {
		return idFakture;
	}

	public void setIdFakture(int idFakture) {
		this.idFakture = idFakture;
	}

	public int getRobaUslugaId() {
		return robaUslugaId;
	}

	public void setRobaUslugaId(int robaUslugaId) {
		this.robaUslugaId = robaUslugaId;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	
}
