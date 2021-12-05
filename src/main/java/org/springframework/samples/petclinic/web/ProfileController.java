package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ConductorService;
import org.springframework.samples.petclinic.service.EmpresaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

	private final ClienteService clienteService;

	private final ConductorService conductorService;

	private final EmpresaService empresaService;

	@Autowired
	public ProfileController(ClienteService clienteService, ConductorService conductorService,
			EmpresaService empresaService) {
		this.conductorService = conductorService;
		this.empresaService = empresaService;
		this.clienteService = clienteService;
	}

	@GetMapping("/profile")
	public String currentProfile(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String autorizacion = "";
		if (authorities.size() <= 1) {
			for (GrantedAuthority auth : authorities) {
				autorizacion = auth.toString();
			}
		}
		if (autorizacion.equals("cliente")) {
			int id = clienteService.findClienteByUsername(authentication.getName()).getId();
			return "redirect:/cliente/" + id;
		} else if (autorizacion.equals("empresa")) {
			int id = empresaService.findEmpresaByUsername(authentication.getName()).getId();
			return "redirect:/empresa/" + id;
		} else if (autorizacion.equals("conductor")) {
			int id = conductorService.findConductorByUsername(authentication.getName()).getId();
			return "redirect:/conductor/" + id;
		} else {
			return "redirect:/error";
		}

	}

	@GetMapping("/reserva")
	public String currentReserva(Authentication authentication) {
		int id = clienteService.findClienteByUsername(authentication.getName()).getId();
		return "redirect:/cliente/" + id + "/reserva/new";
	}

	@GetMapping("/reservas")
	public String currentReservas(Authentication authentication) {
		int id = clienteService.findClienteByUsername(authentication.getName()).getId();
		return "redirect:/cliente/" + id + "/reservas";
	}
}
