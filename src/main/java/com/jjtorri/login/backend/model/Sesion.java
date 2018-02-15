package com.jjtorri.login.backend.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sesion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sesionId;

	private long userId;
	private Calendar acceso;

	public Sesion() {

	}

	
	public long getSesionId() {
		return sesionId;
	}

	public void setSesionId(long sesionId) {
		this.sesionId = sesionId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Calendar getAcceso() {
		return acceso;
	}

	public void setAcceso(Calendar acceso) {
		this.acceso = acceso;
	}

}
