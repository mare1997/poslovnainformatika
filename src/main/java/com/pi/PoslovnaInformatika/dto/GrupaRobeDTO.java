package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;

import com.pi.PoslovnaInformatika.model.GrupaRobe;

public class GrupaRobeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private PDVDTO pdv;
	private PreduzeceDTO preduzece;
	private boolean obrisano;
	public GrupaRobeDTO() {
		super();
	}
	public GrupaRobeDTO(Integer id, String name, PDVDTO pdv, PreduzeceDTO preduzece,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.pdv = pdv;
		this.preduzece = preduzece;
		this.obrisano=obrisano;
	}
	public GrupaRobeDTO(GrupaRobe g) {
		this(g.getId(),g.getName(),new PDVDTO(g.getPdv()),new PreduzeceDTO(g.getPreduzece()),g.isObrisano());
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
	public PDVDTO getPdv() {
		return pdv;
	}
	public void setPdv(PDVDTO pdv) {
		this.pdv = pdv;
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
