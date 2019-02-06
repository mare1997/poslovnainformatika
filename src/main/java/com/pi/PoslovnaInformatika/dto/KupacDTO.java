package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;

import com.pi.PoslovnaInformatika.model.Kupac;

public class KupacDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String adresa;
	private long pib_jmbg;
	private MestoDTO mesto;
	private PreduzeceDTO preduzece;
	private boolean obrisano;
	public KupacDTO() {
		super();
	}
	public KupacDTO(Integer id, String name, String adresa, long pib_jmbg, MestoDTO mesto, PreduzeceDTO preduzece,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.adresa = adresa;
		this.pib_jmbg = pib_jmbg;
		this.mesto = mesto;
		this.preduzece = preduzece;
		this.obrisano=obrisano;
	}
	public KupacDTO(Kupac k) {
		this(k.getId(),k.getName(),k.getAdresa(),k.getPib_jmbg(),new MestoDTO(k.getMesto()),new PreduzeceDTO(k.getPreduzece()),k.isObrisano());
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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public long getPib_jmbg() {
		return pib_jmbg;
	}
	public void setPib_jmbg(long pib_jmbg) {
		this.pib_jmbg = pib_jmbg;
	}
	public MestoDTO getMesto() {
		return mesto;
	}
	public void setMesto(MestoDTO mesto) {
		this.mesto = mesto;
	}
	public PreduzeceDTO getPreduzece() {
		return preduzece;
	}
	public void setPreduzece(PreduzeceDTO preduzece) {
		this.preduzece = preduzece;
	}
	public boolean isObrisano() {
		return obrisano;
	}
	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

	
	
	
}
