package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;
import java.sql.Date;

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
	private Date datum_kreiranja;
	private boolean obrisano;
	public GrupaRobeDTO() {
		super();
	}
	public GrupaRobeDTO(Integer id, String name, PDVDTO pdv, PreduzeceDTO preduzece,Date datum_kreiranja,boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.pdv = pdv;
		this.preduzece = preduzece;
		this.datum_kreiranja = datum_kreiranja;
		this.obrisano=obrisano;
	}
	public GrupaRobeDTO(GrupaRobe g) {
		this(g.getId(),g.getName(),new PDVDTO(g.getPdv()),new PreduzeceDTO(g.getPreduzece()),g.getDatum_kreiranja(),g.isObrisano());
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
	public Date getDatum_kreiranja() {
		return datum_kreiranja;
	}
	public void setDatum_kreiranja(Date datum_kreiranja) {
		this.datum_kreiranja = datum_kreiranja;
	}
	
	
	
	
	
}
