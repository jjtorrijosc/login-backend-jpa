package com.jjtorri.login.backend.control;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jjtorri.login.backend.model.Sesion;
import com.jjtorri.login.backend.model.Usuario;
import com.jjtorri.login.backend.repo.SesionRepository;
import com.jjtorri.login.backend.repo.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private SesionRepository sesionRepo;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody(required = true) Usuario user) {
		//boolean login = false;
		Usuario userAux = null;

		if (user != null && user.getEmail() != null) {
			userAux = usuarioRepo.findByEmail(user.getEmail());

			// si el usuario se ha logado por alguna red social lo almacenamos si
			// es su primer login
			if (user.getProvider() != null && !user.getProvider().equals("")) {
				if (userAux == null) {
					this.signUp(user);
					userAux = usuarioRepo.findByEmail(user.getEmail());
				}
				//login = true;
				grabarSesion(userAux.getUserId());
			}

			// sino validamos contra la pass de BD
			else if (user.getEmail()!= null && userAux != null && userAux.getEmail() != null 
					&& user.getEmail() != null && user.getEmail().equals(userAux.getEmail())
						&& user.getPassword() != null && user.getPassword().equals(userAux.getPassword())) {
					//login = true;
					grabarSesion(userAux.getUserId());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid login, check your email and password");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid login, check your email and password");
		}

		return ResponseEntity.ok(userAux);
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public ResponseEntity<Object> signUp(@RequestBody(required = true) Usuario user) {

		if (user != null && user.getEmail()!=null) {
			Usuario userAux = usuarioRepo.findByEmail(user.getEmail());
			if (userAux!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid sigup, user alredy exists");
			} else {
				user = usuarioRepo.save(user);
				grabarSesion(user.getUserId());
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid sigup, email must be specified");
		}
		return ResponseEntity.ok(user);
	}

	@RequestMapping("/sessions")
	public List<Sesion> userSessions(@RequestParam(value = "user_id") long userId) {
		return this.sesionRepo.findByUserId(userId);
	}

	private void grabarSesion(long user_id) {
		Sesion sesion = new Sesion();
		sesion.setUserId(user_id);
		sesion.setAcceso(new GregorianCalendar());
		this.sesionRepo.save(sesion);
	}
}