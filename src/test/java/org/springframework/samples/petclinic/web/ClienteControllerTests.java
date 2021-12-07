package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Test class for {@link OwnerController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers=ClienteController.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
class ClienteControllerTests {

	private static final int TEST_CLIENTE_ID = 1;

	@Autowired
	private ClienteController clienteController;

	@MockBean
	private ClienteService clienteService;
        
        @MockBean
	private UserService userService;
        
        @MockBean
        private AuthoritiesService authoritiesService; 

	@Autowired
	private MockMvc mockMvc;

	private Cliente prueba;

	@BeforeEach
	void setup() {

		prueba = new Cliente();
		prueba.setId(TEST_CLIENTE_ID);
		prueba.setNombre("Prueba");
		prueba.setDni("12345678A");
		prueba.setEmail("example@example.com");
		prueba.setDireccion("110 W. Liberty St.");
		prueba.setTelefono("608555102");
		given(this.clienteService.findClienteById(TEST_CLIENTE_ID)).willReturn(prueba);

	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/cliente/new")).andExpect(status().isOk()).andExpect(model().attributeExists("cliente"))
				.andExpect(view().name("cliente/createOrUpdateClienteForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/cliente/new").param("nombre", "Joe").param("dni", "12345678B")
							.with(csrf())
							.param("email", "joe@joe.com")
							.param("direccion", "Calle Londres")
							.param("telefono", "622007775")
							.param("username", "joe")
							.param("password", "joe"))
				.andExpect(view().name("/welcome"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/cliente/new")
							.with(csrf())
							.param("nombre", "Joe")
							.param("dni", "12345678B")
							.param("email", "joe@joe.com"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("cliente"))
				.andExpect(model().attributeHasFieldErrors("cliente", "direccion"))
				.andExpect(view().name("cliente/createOrUpdateClienteForm"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testInitUpdateOwnerForm() throws Exception {
		mockMvc.perform(get("/cliente/{clienteId}/edit", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("cliente"))
				.andExpect(model().attribute("cliente", hasProperty("nombre", is("Prueba"))))
				.andExpect(model().attribute("cliente", hasProperty("telefono", is("608555102"))))
				.andExpect(model().attribute("cliente", hasProperty("email", is("example@example.com"))))
				.andExpect(model().attribute("cliente", hasProperty("direccion", is("110 W. Liberty St."))))
				.andExpect(model().attribute("cliente", hasProperty("dni", is("12345678A"))))
				.andExpect(view().name("cliente/createOrUpdateClienteForm"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateOwnerFormSuccess() throws Exception {
		mockMvc.perform(post("/cliente/{clienteId}/edit", TEST_CLIENTE_ID)
							.with(csrf())
							.param("nombre", "Joe")
							.param("telefono", "608555102")
							.param("email", "example@example.com")
							.param("direccion", "London")
							.param("dni", "12345678A"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/cliente/{clienteId}"));
	}
	

        @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateOwnerFormHasErrors() throws Exception {
		mockMvc.perform(post("/cliente/{clienteId}/edit", TEST_CLIENTE_ID)
							.with(csrf())
							.param("nombre", "Joe")
							.param("dni", "12345678A"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("cliente"))
				.andExpect(model().attributeHasFieldErrors("cliente", "direccion"))
				.andExpect(view().name("cliente/createOrUpdateClienteForm"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testShowOwner() throws Exception {
		mockMvc.perform(get("/cliente/{clienteId}", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("cliente", hasProperty("nombre", is("Prueba"))))
				.andExpect(model().attribute("cliente", hasProperty("telefono", is("608555102"))))
				.andExpect(model().attribute("cliente", hasProperty("email", is("example@example.com"))))
				.andExpect(model().attribute("cliente", hasProperty("direccion", is("110 W. Liberty St."))))
				.andExpect(model().attribute("cliente", hasProperty("dni", is("12345678A"))))
				.andExpect(view().name("cliente/clienteDetails"));
	}

}
