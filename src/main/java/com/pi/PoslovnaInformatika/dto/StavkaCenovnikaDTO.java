package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;


import com.pi.PoslovnaInformatika.model.StavkaCenovnika;

public class StavkaCenovnikaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private long cena;
	private int roba;
	private CenovnikDTO cenovnik;
	private boolean obrisano;
	public StavkaCenovnikaDTO() {
		super();
	}
	public StavkaCenovnikaDTO(Integer id, long cena, int roba, CenovnikDTO cenovnik,boolean obrisano) {
		super();
		this.id = id;
		this.cena = cena;
		this.roba = roba;
		this.cenovnik = cenovnik;
		this.obrisano = obrisano;
	}
	public StavkaCenovnikaDTO(StavkaCenovnika s) {
		
		this(s.getId(),s.getCena(),s.getRoba().getId(), new CenovnikDTO(s.getCenovnik()),s.isObrisano());
		System.out.println(s);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getCena() {
		return cena;
	}
	public void setCena(long cena) {
		this.cena = cena;
	}
	public int getRoba() {
		return roba;
	}
	public void setRoba(int roba) {
		this.roba = roba;
	}
	public CenovnikDTO getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(CenovnikDTO cenovnik) {
		this.cenovnik = cenovnik;
	}
	public boolean isObrisano() {
		return obrisano;
	}
	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	

}
