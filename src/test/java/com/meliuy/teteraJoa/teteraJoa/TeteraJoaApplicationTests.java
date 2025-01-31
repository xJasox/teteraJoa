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
		mockMvc.perform(get("/api/status"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.damagedSystem").value("navigation"));
	}

	@Test
	void testChangeSystem() throws Exception {
		mockMvc.perform(post("/api/change-default-system").param("system", "engines"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/api/status"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.damagedSystem").value("engines"));

		mockMvc.perform(post("/api/change-default-system").param("system", "navigation"))
				.andExpect(status().isOk());
	}

	@Test
	void testChangeSystemInvalid() throws Exception {
		mockMvc.perform(post("/api/change-default-system").param("system", "invalid_system"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testPostTeapot() throws Exception {
		mockMvc.perform(post("/api/teapot"))
				.andExpect(status().isIAmATeapot());
	}
}