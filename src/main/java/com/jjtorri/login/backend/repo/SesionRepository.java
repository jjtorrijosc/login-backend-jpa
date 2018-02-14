package com.jjtorri.login.backend.repo;


import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jjtorri.login.backend.model.Sesion;


public interface SesionRepository extends CrudRepository<Sesion, String> {

    public List<Sesion> findByUserId(BigInteger userId);
     
    @SuppressWarnings("unchecked")
	public Sesion save(Sesion sesion);

}