package com.jjtorri.login.backend.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sesion {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private BigInteger sesion_id;
	
	private BigInteger userId;	
	private Calendar acceso;

	
	public Sesion () {
		
	}

	
	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public Calendar getAcceso() {
		return acceso;
	}

	public void setAcceso(Calendar acceso) {
		this.acceso = acceso;
	}
}
