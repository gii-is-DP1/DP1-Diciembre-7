package org.springframework.samples.petclinic.web;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ConductorService;
import org.springframework.samples.petclinic.service.EmpresaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {
	
	private static final String VIEWS_USER_PROFILE = "users/profile";
	
	private final ClienteService clienteService;
	
	private final ConductorService conductorService;
	
	private final EmpresaService empresaService;

	private final UserService userService;
	
	@Autowired
	ProfileController(UserService userService, ConductorService conductorService, ClienteService clienteService, EmpresaService empresaService){
		this.clienteService = clienteService;
		this.conductorService = conductorService;
		this.empresaService = empresaService;
		this.userService = userService;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@ResponseBody
    public int currentUserName(Authentication authentication) {
	    String currentUserName = authentication.getName();
	    int currentId = 0;
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    if(authorities.contains("cliente")) {
	    	 currentId = clienteService.findClienteByUsername(currentUserName).getId();
	    }else if(authorities.contains("conductor")) {
	    	 currentId = conductorService.findConductorByUsername(currentUserName).getId();
	    }else if(authorities.contains("empresa")) {
	    	 currentId = empresaService.findEmpresaByUsername(currentUserName).getId();
	    }
	    return currentId;
	}
	
	public String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

}
