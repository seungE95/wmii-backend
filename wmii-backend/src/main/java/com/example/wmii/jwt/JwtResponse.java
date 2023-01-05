package com.example.wmii.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable{

	private static final long serialVersionUID = -3077949996790845537L;
	private final String jwttoken;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public String getToken() {
		return this.jwttoken;
	}
	
}
