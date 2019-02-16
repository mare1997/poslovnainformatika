package com.pi.PoslovnaInformatika.model;

public class UserTokenState {
	
	private String access_token;
	private int id;
	private String autority;

    public UserTokenState() {
        this.access_token = null;
    }

    public UserTokenState(String access_token,int id,String autority) {
        this.access_token = access_token;
        this.id =  id;
        this.autority = autority;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutority() {
		return autority;
	}

	public void setAutority(String autority) {
		this.autority = autority;
	}
    

}
