package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;


import com.pi.PoslovnaInformatika.model.Roba;

public class RobaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String jedninica_mere;
	private GrupaRobeDTO grupa;
	private boolean obrisano;
	
	public RobaDTO() {
		super();
	}
	public RobaDTO(Integer id, String name, String jedninica_mere, GrupaRobeDTO grupa, boolean obrisano) {
		super();
		this.id = id;
		this.name = name;
		this.jedninica_mere = jedninica_mere;
		this.grupa = grupa;
		this.obrisano = obrisano;
	}
	public RobaDTO(Roba r) {
		this(r.getId(),r.getName(),r.getJedninica_mere(),new GrupaRobeDTO(r.getGrupa()),r.isObrisano());
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
	public String getJedninica_mere() {
		return jedninica_mere;
	}
	public void setJedninica_mere(String jedninica_mere) {
		this.jedninica_mere = jedninica_mere;
	}
	public GrupaRobeDTO getGrupa() {
		return grupa;
	}
	public void setGrupa(GrupaRobeDTO grupa) {
		this.grupa = grupa;
	}
	public boolean isObrisano() {
		return obrisano;
	}
	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
	
	
	

}
