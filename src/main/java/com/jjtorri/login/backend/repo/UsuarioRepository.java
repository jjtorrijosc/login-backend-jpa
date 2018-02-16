package com.jjtorri.login.backend.repo;

import org.springframework.data.repository.CrudRepository;

import com.jjtorri.login.backend.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	public Usuario findByEmail(String email);

	@SuppressWarnings("unchecked")
	public Usuario save(Usuario user);

}