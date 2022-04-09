package com.example.demo;

import com.example.demo.controller.SimpleCalculationsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Lab1ApplicationTests {
  	@Autowired
	SimpleCalculationsController simpleCalculationsController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp(){
		this.mockMvc = MockMvcBuilders.standaloneSetup(simpleCalculationsController).build();
	}

	@Test
	public void testCalculateSuccessful_correctResult() throws Exception{
		MvcResult mvcResult = this.mockMvc.perform(get("/calculate?operation=MINUS_ONE&value=233")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.value").value("232")).andReturn();
		assertEquals(200,mvcResult.getResponse().getStatus());
	}

	@Test
	public void testCalculateBad_incorrectResult() throws Exception{
		MvcResult mvcResult = this.mockMvc.perform(get("/calculate?operation=MINUS_ONE&value=fdfd")).andDo(print()).andExpect(status().isBadRequest()).andReturn();
		assertEquals(400,mvcResult.getResponse().getStatus());
	}

	@Test
	void testCalculateMinusOne() {
		int value = simpleCalculationsController.calculateByOperation("minus_one",2).getValue();
		assertEquals(1,value);
	}

	@Test
	void testCalculatePlusOne() {
		int value = simpleCalculationsController.calculateByOperation("plus_one",2).getValue();
		assertEquals(3,value);
	}

	@Test
	void testCalculatePlusTen() {
		int value = simpleCalculationsController.calculateByOperation("plus_ten",2).getValue();
		assertEquals(12,value);
	}

	@Test
	void testCalculateMinusTen() {
		int value = simpleCalculationsController.calculateByOperation("minus_ten",2).getValue();
		assertEquals(-8,value);
	}

}
