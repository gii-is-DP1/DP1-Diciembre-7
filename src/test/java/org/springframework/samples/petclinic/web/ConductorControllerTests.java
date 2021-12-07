package org.springframework.samples.petclinic.web;

import java.util.HashSet;


import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ConductorService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@WebMvcTest(controllers=ConductorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ConductorControllerTests {
	
	private static final int TEST_CONDUCTOR_ID = 1;
	
	@Autowired
	private ConductorController conductorController;
	
	@MockBean
	private ReservaService reservaService;
	
	@MockBean
	private ConductorService conductorService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Conductor ortega;
	
	@BeforeEach
	void setup() {
		ortega = new Conductor();
		ortega.setId(TEST_CONDUCTOR_ID);
		ortega.setNombre("Jose Manuel");
		ortega.setCiudad("Ecija");
		ortega.setDni("84380218A");
		ortega.setEmail("ego@ego.com");
		ortega.setExperiencia(14);
		ortega.setPermisoCoche(true);
		ortega.setPermisoBarco(true);
		ortega.setSalarioBase(120.0);
		ortega.setSalarioPorDia(3.0);
		ortega.setTelefono("136942000");
		given(this.conductorService.findConductorById(TEST_CONDUCTOR_ID)).willReturn(ortega);
	}
	
	@WithMockUser(value = "spring")
		@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/conductor/new")).andExpect(status().isOk()).andExpect(model().attributeExists("conductor"))
				.andExpect(view().name("conductor/createOrUpdateConductorForm"));
	}
	
	@WithMockUser(value = "spring")
		@Test
	void testProcessCreationFormSucces() throws Exception {
		mockMvc.perform(post("/conductor/new").param("nombre", "Abel").param("ciudad", "Sevilla").
				with(csrf())
				.param("dni", "81473182F")
				.param("email", "jeje@jeje.com")
				.param("experiencia", "3")
				.param("permisoCoche","true")
				.param("permisoBarco", "false")
				.param("salarioBase", "200.0")
				.param("salarioPorDia", "10.0")
				.param("telefono", "387142931")
				.param("username", "clake")
				.param("password", "clake"))
		.andExpect(view().name("/welcome"));
	}
	
	

}
