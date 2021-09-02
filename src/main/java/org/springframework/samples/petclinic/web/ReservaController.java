package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ConductorService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.CloseDateBookingException;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservaController {

	private static final String VIEWS_RESERVA_CREATE = "reserva/createReservaForm";

	private final ReservaService reservaService;

	private final ClienteService clienteService;

	private final VehiculoService vehiculoService;

	private final ConductorService conductorService;

	@Autowired
	ReservaController(ReservaService reservaService, VehiculoService vehiculoService, ConductorService conductorService,
			ClienteService clienteService) {
		this.reservaService = reservaService;
		this.vehiculoService = vehiculoService;
		this.clienteService = clienteService;
		this.conductorService = conductorService;
	}

	@ModelAttribute("cliente")
	public Cliente findCliente(@PathVariable("clienteid") int clienteId) {
		return this.clienteService.findClienteById(clienteId);
	}

	@InitBinder("cliente")
	public void initClienteBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("reserva")
	public void initReservaBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("conductor")
	public void initConductorBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("vehiculo")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/reserva/new")
	public String initCreationForm(Map<String, Object> model, Cliente cliente) {
		Collection<TipoVehiculo> tiposVehiculo = vehiculoService.findTipoVehiculo();
		Reserva reserva = new Reserva();
		cliente.addReserva(reserva);
		model.put("reserva", reserva);
		model.put("tiposVehiculo", tiposVehiculo);
		return "reserva/createPreReservaForm";
	}

	@GetMapping(value = "/reserva/init")
	public String initReservaForm(Map<String, Object> model, Reserva reserva, String ciudad,
			TipoVehiculo tipoVehiculo) {
		model.put("reserva", reserva);
		Collection<Vehiculo> vehiculos = vehiculoService.findVehiculosPorCiudadYFechaDisponibles(ciudad,
				reserva.getFechaInicio(), reserva.getFechaFin());
		Collection<Conductor> conductores = conductorService.findConductoresPorCiudadPermisoYFecha(ciudad, tipoVehiculo,
				reserva.getFechaInicio(), reserva.getFechaFin());
		model.put("vehiculos", vehiculos);
		model.put("conductores", conductores);
		return VIEWS_RESERVA_CREATE;
	}

	@PostMapping(value = "/reserva/new")
	public String processCreationForm(Cliente cliente, @Valid Reserva reserva, BindingResult result, ModelMap model)
			throws DataAccessException, OverStockedVehicleException {
		if (result.hasErrors()) {
			model.put("reserva", reserva);
			return VIEWS_RESERVA_CREATE;
		} else {
			if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
				model.put("reserva", reserva);
				model.addAttribute("Message", "La fecha de fin debe ser mas tarde que la de inicio");
				return VIEWS_RESERVA_CREATE;
			} else if (reserva.getFechaInicio().isBefore(LocalDate.now())) {
				model.put("reserva", reserva);
				model.addAttribute("Message", "La fecha de inicio no puede estar en el pasado");
			} else {
				try {
					if (reserva.getConductor() != null) {
						reserva.getConductor().addReserva(reserva);
					}
					reserva.getVehiculo().addReserva(reserva);
					cliente.addReserva(reserva);
					this.reservaService.saveReserva(reserva);
					model.addAttribute("message", "La reserva se ha realizado con exito");
				} catch (OverStockedVehicleException ex) {
					result.rejectValue("stock", "", "");
					model.put("reserva", reserva);
					model.addAttribute("message", "No queda ningun vehiculo de ese modelo y marca");
					return VIEWS_RESERVA_CREATE;
				}

			}
			return "redirect:/reserva/" + reserva.getId();
		}
	}

	@GetMapping("/reserva/{reservaId}")
	public ModelAndView showReserva(@PathVariable("reservasId") int reservaId) {
		ModelAndView mav = new ModelAndView("reserva/reservasDetails");
		mav.addObject(this.reservaService.findReservaById(reservaId));
		return mav;
	}

	@GetMapping("/reserva/{reservaId}/delete")
	public String deleteOficina(@PathVariable("reservaId") int reservaId, @PathVariable("clinteId") int clienteId,
			ModelMap model) throws CloseDateBookingException{
		Reserva r = reservaService.findReservaById(reservaId);
		Cliente c = clienteService.findClienteById(clienteId);
		if (r != null) {
			try {
				c.removeReserva(r);
				r.getVehiculo().removeReserva(r);
				if (r.getConductor() != null) {
					r.getConductor().removeReserva(r);
				}
				reservaService.deleteReservaById(reservaId);
				model.addAttribute("message", "La reserva se ha eliminado correctamente.");
				return "redirect:/cliente/{clienteId}";
			} catch (CloseDateBookingException ex) {
				model.addAttribute("message", "El periodo de cancelacion ya ha acabado");
				return "redirect:/cliente/{clienteId}";
			}
		} else {
			model.addAttribute("message", "No se ha encontrado la reserva que quiere eliminar.");
			return "redirect:/cliente/{clienteId}";
		}
	}

}
