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
	
		@WithMockUser(value = "spring")
	    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/conductor/new")
							.with(csrf())
							.param("nombre", "Abel")
							.param("dni", "81473182F")
							.param("email", "jeje@jeje.com")
							.param("experiencia", "3")
							.param("permisoCoche","true")
							.param("permisoBarco", "false")
							.param("salarioBase", "200.0")
							.param("salarioPorDia", "10.0")
							.param("telefono", "387142931"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("conductor"))
				.andExpect(model().attributeHasFieldErrors("conductor", "ciudad"))
				.andExpect(view().name("conductor/createOrUpdateConductorForm"));
	}
		
		   @WithMockUser(value = "spring")
			@Test
			void testInitUpdateConductorForm() throws Exception {
				mockMvc.perform(get("/conductor/{conductorId}/edit", TEST_CONDUCTOR_ID)).andExpect(status().isOk())
						.andExpect(model().attributeExists("conductor"))
						.andExpect(model().attribute("conductor", hasProperty("nombre", is("Jose Manuel"))))
						.andExpect(model().attribute("conductor", hasProperty("ciudad", is("Ecija"))))
						.andExpect(model().attribute("conductor", hasProperty("dni", is("84380218A"))))
						.andExpect(model().attribute("conductor", hasProperty("email", is("ego@ego.com"))))
						.andExpect(model().attribute("conductor", hasProperty("experiencia", is(14))))
						.andExpect(model().attribute("conductor", hasProperty("permisoCoche", is(true))))
						.andExpect(model().attribute("conductor", hasProperty("permisoBarco", is(true))))
						.andExpect(model().attribute("conductor", hasProperty("salarioBase", is(120.0))))
						.andExpect(model().attribute("conductor", hasProperty("salarioPorDia", is(3.0))))
						.andExpect(model().attribute("conductor", hasProperty("telefono", is("136942000"))))
						.andExpect(view().name("conductor/createOrUpdateConductorForm"));
			}

		        @WithMockUser(value = "spring")
			@Test
			void testProcessUpdateConductorFormSuccess() throws Exception {
				mockMvc.perform(post("/conductor/{conductorId}/edit", TEST_CONDUCTOR_ID)
									.with(csrf())
									.param("nombre", "Jose Manuel")
									.param("ciudad", "Sevilla")
									.param("dni", "84380218A")
									.param("email", "ego@ego.com")
									.param("experiencia", "14")
									.param("permisoCoche", "true")
									.param("permisoBarco", "true")
									.param("salarioBase", "120.0")
									.param("salarioPorDia", "3.0")
									.param("telefono", "136942000"))
						.andExpect(status().is3xxRedirection())
						.andExpect(view().name("redirect:/conductor/{conductorId}"));
			}
			

		        @WithMockUser(value = "spring")
			@Test
			void testProcessUpdateConductorFormHasErrors() throws Exception {
				mockMvc.perform(post("/conductor/{conductorId}/edit", TEST_CONDUCTOR_ID)
									.with(csrf())
									.param("nombre", "Jose Manuel")
									.param("dni", "84380218A")
									.param("email", "ego@ego.com")
									.param("permisoCoche", "true")
									.param("permisoBarco", "true")
									.param("salarioBase", "120.0")
									.param("salarioPorDia", "3.0")
									.param("telefono", "136942000"))
						.andExpect(status().isOk())
						.andExpect(model().attributeHasErrors("conductor"))
						.andExpect(model().attributeHasFieldErrors("conductor", "ciudad"))
						.andExpect(view().name("conductor/createOrUpdateConductorForm"));
			}

		        @WithMockUser(value = "spring")
			@Test
			void testShowConductor() throws Exception {
				mockMvc.perform(get("/conductor/{conductorId}", TEST_CONDUCTOR_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("conductor", hasProperty("nombre", is("Jose Manuel"))))
				.andExpect(model().attribute("conductor", hasProperty("ciudad", is("Ecija"))))
				.andExpect(model().attribute("conductor", hasProperty("dni", is("84380218A"))))
				.andExpect(model().attribute("conductor", hasProperty("email", is("ego@ego.com"))))
				.andExpect(model().attribute("conductor", hasProperty("experiencia", is(14))))
				.andExpect(model().attribute("conductor", hasProperty("permisoCoche", is(true))))
				.andExpect(model().attribute("conductor", hasProperty("permisoBarco", is(true))))
				.andExpect(model().attribute("conductor", hasProperty("salarioBase", is(120.0))))
				.andExpect(model().attribute("conductor", hasProperty("salarioPorDia", is(3.0))))
				.andExpect(model().attribute("conductor", hasProperty("telefono", is("136942000"))))
						.andExpect(view().name("conductor/conductorDetails"));
			}
	
	

}
