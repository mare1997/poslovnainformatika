package com.pi.PoslovnaInformatika.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity                 
@Table(name="stopa_pdv") 
public class StopaPDV implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="stopa_pdv_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="procenat", unique=false, nullable=false)
	private int procenat;

	@Column(name="datum_vazenja", unique=false, nullable=false)
	private Date datum_vazenja;
	
	@ManyToOne
	@JoinColumn(name = "pdv_id", referencedColumnName = "pdv_id", nullable = true)
	private PDV pdv;

	
	
	public StopaPDV() {
		super();
	}



	public StopaPDV(Integer id, int procenat, Date datum_vazenja, PDV pdv) {
		super();
		this.id = id;
		this.procenat = procenat;
		this.datum_vazenja = datum_vazenja;
		this.pdv = pdv;
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



	public PDV getPdv() {
		return pdv;
	}



	public void setPdv(PDV pdv) {
		this.pdv = pdv;
	}
	
	
	
}
