package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;
import java.sql.Date;

import com.pi.PoslovnaInformatika.model.PDV;
import com.pi.PoslovnaInformatika.model.StopaPDV;

public class StopaPDVDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int procenat;
	private Date datum_vazenja;
	private PDVDTO pdv;
	public StopaPDVDTO() {
		super();
	}
	public StopaPDVDTO(Integer id, int procenat, Date datum_vazenja, PDVDTO pdv) {
		super();
		this.id = id;
		this.procenat = procenat;
		this.datum_vazenja = datum_vazenja;
		this.pdv = pdv;
	}
	public StopaPDVDTO(StopaPDV s) {
		this(s.getId(),s.getProcenat(),s.getDatum_vazenja(),new PDVDTO(s.getPdv()));
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getProcenat() {
		return procenat;
	}
	public void setProcenat(int procenat) {
		this.procenat = procenat;
	}
	public Date getDatum_vazenja() {
		return datum_vazenja;
	}
	public void setDatum_vazenja(Date datum_vazenja) {
		this.datum_vazenja = datum_vazenja;
	}
	public PDVDTO getPdv() {
		return pdv;
	}
	public void setPdv(PDVDTO pdv) {
		this.pdv = pdv;
	}
	
	
	
}
