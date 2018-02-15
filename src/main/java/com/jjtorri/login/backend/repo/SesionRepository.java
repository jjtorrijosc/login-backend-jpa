package com.jjtorri.login.backend.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jjtorri.login.backend.model.Sesion;

public interface SesionRepository extends CrudRepository<Sesion, String> {

	public List<Sesion> findByUserId(long user_id);

	@SuppressWarnings("unchecked")
	public Sesion save(Sesion sesion);

}