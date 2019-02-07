package com.pi.PoslovnaInformatika.common;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247173819432571404L;

	public Date now() {
        return new Date();
    }
}
