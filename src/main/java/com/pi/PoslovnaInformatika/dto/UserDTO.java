package com.pi.PoslovnaInformatika.dto;

import java.io.Serializable;


import com.pi.PoslovnaInformatika.model.User;
import com.pi.PoslovnaInformatika.model.User.UserType;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private UserType userType;
    private PreduzeceDTO preduzece;
	private boolean obrisano;
	
	
	
	public UserDTO() {
		super();
	}
	
	
	public UserDTO(Integer id, String firstname, String lastname, String username, String password, UserType userType,
			PreduzeceDTO preduzece,boolean obrisano) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.preduzece = preduzece;
		this.obrisano=  obrisano;
	}


	public UserDTO(User user) {
        this(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),user.getUser_password(),user.getUserType(),new PreduzeceDTO(user.getPreduzece()),user.isObrisano());
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
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
