package org.springframework.samples.petclinic.web;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.PreReserva;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ConductorService;
import org.springframework.samples.petclinic.service.PreReservaService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente/{clienteid}")
public class ReservaController {

	private static final String VIEWS_RESERVA_CREATE = "reserva/createReservaForm";

	private final ReservaService reservaService;

	private final ClienteService clienteService;

	private final VehiculoService vehiculoService;

	private final ConductorService conductorService;

	private final PreReservaService preReservaService;

	@Autowired
	ReservaController(ReservaService reservaService, VehiculoService vehiculoService, ConductorService conductorService,
			ClienteService clienteService, PreReservaService preReservaService) {
		this.reservaService = reservaService;
		this.vehiculoService = vehiculoService;
		this.clienteService = clienteService;
		this.conductorService = conductorService;
		this.preReservaService = preReservaService;
	}

	@ModelAttribute("cliente")
	public Cliente findCliente(@PathVariable("clienteid") int clienteId) {
		return this.clienteService.findClienteById(clienteId);
	}

	@InitBinder("cliente")
	public void initClienteBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(dateFormat, false));
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

	@GetMapping(value = "/preReserva/new")
	public String initPreReservaForm(Map<String, Object> model, Cliente cliente)
			throws DataAccessException, OverStockedVehicleException {
		Collection<TipoVehiculo> tiposVehiculo = vehiculoService.findTipoVehiculo();
		preReservaService.deleteAll();
		PreReserva preReserva = new PreReserva();
		model.put("preReserva", preReserva);
		model.put("tiposVehiculo", tiposVehiculo);
		return "reserva/createPreReservaForm";
	}

	@PostMapping(value = "/preReserva/new")
	public String processPreReservaFrom(@Valid PreReserva preReserva, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("preReserva", preReserva);
			return "reserva/createPreReservaForm";

		} else {
			if (preReserva.getFechaInicio().isAfter(preReserva.getFechaFin())) {
				model.put("preReserva", preReserva);
				model.addAttribute("Message", "La fecha de fin debe ser mas tarde que la de inicio");
				return "reserva/createPreReservaForm";
			} else if (preReserva.getFechaInicio().isBefore(LocalDate.now())) {
				model.put("preReserva", preReserva);
				model.addAttribute("Message", "La fecha de inicio no puede estar en el pasado");
				return "reserva/createPreReservaForm";
			} else {
				this.preReservaService.save(preReserva);
				model.put("preReserva", preReserva);

			}
			return "redirect:/cliente/{clienteid}/reserva/new";
		}
	}

	@GetMapping(value = "/reserva/new")
	public String initReservaForm(Map<String, Object> model, Cliente cliente) {
		Collection<PreReserva> preReservas = this.preReservaService.findAllPreReservas();
		PreReserva preReserva = (PreReserva) preReservas.toArray()[0];
		Collection<Vehiculo> vehiculos = vehiculoService.findVehiculosPorCiudadYFechaDisponibles(preReserva.getCiudad(),
				preReserva.getFechaInicio(), preReserva.getFechaFin(), preReserva.getTipoVehiculo());
		Collection<Conductor> conductores = conductorService.findConductoresPorCiudadPermisoYFecha(
				preReserva.getCiudad(), preReserva.getTipoVehiculo(), preReserva.getFechaInicio(),
				preReserva.getFechaFin());
		Reserva reserva = new Reserva();
		cliente.addReserva(reserva);
		model.put("preReserva", preReserva);
		model.put("reserva", reserva);
		model.put("vehiculos", vehiculos);
		model.put("conductores", conductores);
		return VIEWS_RESERVA_CREATE;
	}

	@PostMapping(value = "/reserva/new")
	public String processCreationForm(Cliente cliente, @Valid Reserva reserva, BindingResult result, ModelMap model)
			throws DataAccessException, OverStockedVehicleException {
		if (result.hasErrors()) {
			model.addAttribute("Message", "Salta esto");
			model.put("reserva", reserva);
			return VIEWS_RESERVA_CREATE;
		} else {
			Collection<PreReserva> preReservas = this.preReservaService.findAllPreReservas();
			PreReserva preReserva = (PreReserva) preReservas.toArray()[0];
			reserva.setFechaInicio(preReserva.getFechaInicio());
			reserva.setFechaFin(preReserva.getFechaFin());
			if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
				model.put("reserva", reserva);
				model.addAttribute("Message", "La fecha de fin debe ser mas tarde que la de inicio");
				return VIEWS_RESERVA_CREATE;
			} else if (reserva.getFechaInicio().isBefore(LocalDate.now())) {
				model.put("reserva", reserva);
				model.addAttribute("Message", "La fecha de inicio no puede estar en el pasado");
				return VIEWS_RESERVA_CREATE;
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
					result.rejectValue("stock", "ErrorAqui", "ErrorAqui");
					model.put("reserva", reserva);
					model.addAttribute("message", "No queda ningun vehiculo de ese modelo y marca");
					return VIEWS_RESERVA_CREATE;
				}

			}
			return "redirect:/cliente/{clienteid}/reserva/" + reserva.getId();
		}
	}

	@GetMapping("/reserva/{reservaId}")
	public ModelAndView showReserva(@PathVariable("reservaId") int reservaId) {
		ModelAndView mav = new ModelAndView("reserva/reservaDetails");
		mav.addObject(this.reservaService.findReservaById(reservaId));
		return mav;
	}

	@GetMapping("/reserva/{reservaId}/delete")
	public String deleteOficina(@PathVariable("reservaId") int reservaId, @PathVariable("clienteid") int clienteId,
			ModelMap model) throws CloseDateBookingException {
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
				return "redirect:/reservas";
			} catch (CloseDateBookingException ex) {
				model.addAttribute("message", "El periodo de cancelacion ya ha acabado");
				return "redirect:/reservas";
			}
		} else {
			model.addAttribute("message", "No se ha encontrado la reserva que quiere eliminar.");
			return "redirect:/reservas";
		}
	}

	@GetMapping(value = "/reservas")
	public String allReservas(Cliente cliente, BindingResult result, Map<String, Object> model) {
		Collection<Reserva> results = this.reservaService.findReservasByCliente(cliente);
		model.put("selections", results);
		return "cliente/reservasList";
	}

}
