package com.jjtorri.login.backend.model;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private BigInteger user_id;
	
	private String username;
	private String password;
	private String email;
	private String provider;
	private String id_provider;
	private List<Sesion> sesiones;

	public Usuario () {
		
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
	
	public BigInteger getUser_id() {
		return user_id;
	}

	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getId_provider() {
		return id_provider;
	}

	public void setId_provider(String id_provider) {
		this.id_provider = id_provider;
	}

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

	@Override
	public String toString() {
		return String.format("Usuario[username='%s']", this.username);
	}
	
	
}
