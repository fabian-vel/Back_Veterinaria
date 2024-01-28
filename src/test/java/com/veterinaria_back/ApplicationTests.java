package com.veterinaria_back;

import com.veterinaria_back.controller.PacienteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

	@Mock
	private PacienteController controller;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

	@Test
	void  maid() {
		Application.main(new String[]{});
	}

}
