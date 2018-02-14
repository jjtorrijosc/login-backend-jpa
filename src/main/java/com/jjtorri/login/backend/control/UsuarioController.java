package com.jjtorri.login.backend.control;

import java.math.BigInteger;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jjtorri.login.backend.model.Sesion;
import com.jjtorri.login.backend.model.Usuario;
import com.jjtorri.login.backend.repo.SesionRepository;
import com.jjtorri.login.backend.repo.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private SesionRepository sesionRepo;

    @RequestMapping(value="/usuario/login", method = RequestMethod.POST)
    public boolean login (@RequestBody(required=true) Usuario user) {
        boolean login = false;
        Usuario userAux = null;
        
        if (user!=null) {
        	userAux = usuarioRepo.findByUsername(user.getUsername());
        	
        	//si el usuario se ha logado por alguna red social lo almacenamos si
        	// es su primer login
        	if (user.getProvider()!=null && !user.getProvider().equals("")) {
        		if (userAux == null) {
        			this.signUp(user);
        			userAux = usuarioRepo.findByUsername(user.getUsername());
        		}
        		login = true;
        		grabarSesion(user.getUser_id());
	        }
        	
        	// sino validamos contra la pass de BD
	        else if (user.getUsername()!=null && userAux.getUsername()!=null) {
		        if (user.getUsername()!=null && user.getUsername().equals(userAux.getUsername()) 
		       			&& user.getPassword() != null && user.getPassword().equals(userAux.getPassword())) {
		        	login = true;
		        	grabarSesion(user.getUser_id());
		        }
	        }
        }
    	return login;
    }
    
    @RequestMapping(value="/usuario/sign-up", method = RequestMethod.POST)
    public Usuario signUp(@RequestBody(required=true) Usuario user) {

        if (user!=null) {
        	user = usuarioRepo.save(user);
        	grabarSesion(user.getUser_id());
        }
        return user;
    }
    
    
    //TODO: crear metodo para consultar sesiones de usuario paramRequest 
    
    
    private void grabarSesion (BigInteger user_id) {
    	Sesion sesion = new Sesion();
    	sesion.setUserId(user_id);
    	sesion.setAcceso(new GregorianCalendar());
    	this.sesionRepo.save(sesion);
    }
}