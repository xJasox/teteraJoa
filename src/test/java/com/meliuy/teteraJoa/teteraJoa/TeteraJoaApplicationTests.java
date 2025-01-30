package com.meliuy.teteraJoa.teteraJoa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeteraJoaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetStatus() throws Exception {
		mockMvc.perform(get("/status"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.damaged_system").value("navigation"));
	}

	@Test
	void testGetRepairBay() throws Exception {
		mockMvc.perform(get("/repair-bay"))
				.andExpect(status().isOk())
				.andExpect(view().name("html"))
				.andExpect(model().attributeExists("code"))
				.andExpect(model().attribute("code", "NAV-01"));
	}

	@Test
	void testChangeSystem() throws Exception {
		mockMvc.perform(post("/change-default-system").param("system", "engines"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/status"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.damaged_system").value("engines"));

		mockMvc.perform(post("/change-default-system").param("system", "navigation"))
				.andExpect(status().isOk());
	}

	@Test
	void testChangeSystemInvalid() throws Exception {
		mockMvc.perform(post("/change-default-system").param("system", "invalid_system"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testPostTeapot() throws Exception {
		mockMvc.perform(post("/teapot"))
				.andExpect(status().isIAmATeapot());
	}
}