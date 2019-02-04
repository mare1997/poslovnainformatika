package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;

import com.pi.PoslovnaInformatika.model.Mesto;

public class MestoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String grad;
	private long postanski_broj;
	
	public MestoDTO() {
		super();
	}
	public MestoDTO(Integer id, String grad, long postanski_broj) {
		super();
		this.id = id;
		this.grad = grad;
		this.postanski_broj = postanski_broj;
	}
	
	public MestoDTO(Mesto m) {
		this(m.getId(),m.getGrad(),m.getPostanski_broj());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public long getPostanski_broj() {
		return postanski_broj;
	}
	public void setPostanski_broj(long postanski_broj) {
		this.postanski_broj = postanski_broj;
	}
	
	
	

}
