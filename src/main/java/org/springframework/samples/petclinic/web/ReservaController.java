package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ConductorService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.OverStockedVehicleException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservaController {

	private static final String VIEWS_RESERVA_CREATE = "reserva/createReservaForm";

	private final ReservaService reservaService;
	
	private final ClienteService clienteService;

	private final VehiculoService vehiculoService;

	private final ConductorService conductorService;

	@Autowired
	ReservaController(ReservaService reservaService, VehiculoService vehiculoService,
			ConductorService conductorService) {
		this.reservaService = reservaService;
		this.vehiculoService = vehiculoService;
		this.conductorService = conductorService;
	}
	
	@ModelAttribute("vehiculos")
	public Collection<Vehiculo> populateVehiculos(){
		return this.vehiculoService.find;
	}
	@ModelAttribute("cliente")
	public Cliente findCliente(@PathVariable("clienteid") int clienteId) {
		return this.clienteService.findClienteById(clienteId);
	}

	@InitBinder("cliente")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/reserva/new")
	public String initCreationForm(Map<String, Object> model) {
		Reserva reserva = new Reserva();
		model.put("reserva", reserva);
		return VIEWS_RESERVA_CREATE;
	}
	
	@PostMapping(value = "/reserva/new")
	public String processCreationForm(@Valid Reserva reserva, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.put("reserva", reserva);
			return VIEWS_RESERVA_CREATE;
		}else{
			if(reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
				model.put("reserva", reserva);
				model.addAttribute("Message","La fecha de fin debe ser mas tarde que la de inicio");
				return VIEWS_RESERVA_CREATE;
			}else if(reserva.getFechaInicio().isBefore(LocalDate.now())) {
				model.put("reserva", reserva);
				model.addAttribute("Message","La fecha de inicio no puede estar en el pasado");
			}else{
				try {
					this.reservaService.saveReserva(reserva);
				}catch(OverStockedVehicleException ex) {
					result.rejectValue("","","");
					model.put("reserva", reserva);
					return VIEWS_RESERVA_CREATE;
				}
			
		}
			
			model.put("reserva", reserva);
			return "redirect:/reserva/" + reserva.getId();

		}
	}

}
