package com.ufcg.psoft.mercadofacil.model;

import java.io.Serializable;


/**
 * Model responsável por armazenar tokens que são enviados aos clients
 *
 */
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}