package com.pi.PoslovnaInformatika.model;

public class UserTokenState {
	
	private String access_token;

    public UserTokenState() {
        this.access_token = null;
    }

    public UserTokenState(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


}
