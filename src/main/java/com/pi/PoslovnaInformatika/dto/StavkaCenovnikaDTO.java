package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;

import com.pi.PoslovnaInformatika.model.Roba;
import com.pi.PoslovnaInformatika.model.StavkaCenovnika;

public class StavkaCenovnikaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private long cena;
	private RobaDTO roba;
	private CenovnikDTO cenovnik;
	private boolean obrisano;
	public StavkaCenovnikaDTO() {
		super();
	}
	public StavkaCenovnikaDTO(Integer id, long cena, RobaDTO roba, CenovnikDTO cenovnik,boolean obrisano) {
		super();
		this.id = id;
		this.cena = cena;
		this.roba = roba;
		this.cenovnik = cenovnik;
		this.obrisano = obrisano;
	}
	public StavkaCenovnikaDTO( long cena, RobaDTO roba, CenovnikDTO cenovnik,boolean obrisano) {
		super();
		
		this.cena = cena;
		this.roba = roba;
		this.cenovnik = cenovnik;
		this.obrisano = obrisano;
	}
	public StavkaCenovnikaDTO(StavkaCenovnika s) {
		this(s.getId(),s.getCena(),new RobaDTO(s.getRoba()), new CenovnikDTO(s.getCenovnik()),s.isObrisano());
		
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
	public RobaDTO getRoba() {
		return roba;
	}
	public void setRoba(RobaDTO roba) {
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
