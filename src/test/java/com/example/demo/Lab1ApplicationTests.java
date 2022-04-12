package com.example.demo;

import com.example.demo.controller.SimpleCalculationsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Autowired
    private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testCalculateSuccessful_correctResult() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/calculate?operation=minus_one&value=233"))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.value").value("232"))
                .andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

    @Test
    public void testCalculateBadRequest_requiredParameterIsNotPresent() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/calculate?operation=minus_one"))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$.message")
                        .value("Required request parameter 'value' for method parameter type int is not present"))
                .andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

	@Test
	public void testCalculateBadRequest_incorrectParameterType() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/calculate?operation=minus_one&value=fdfd"))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$.message")
                        .value("Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"fdfd\""))
                .andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
	}

    @Test
    public void testCalculateBadRequest_incorrectOperation() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/calculate?operation=not_exist&value=10"))
                .andDo(print()).andExpect(status().isInternalServerError()).andExpect(jsonPath("$.message")
                        .value("Operation is incorrect!"))
                .andReturn();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
    }

	@Test
	void testCalculateMinusOne() {
		int value = simpleCalculationsController.calculateByOperation("minus_one",2).getValue();
		assertEquals(1, value);
	}

	@Test
	void testCalculatePlusOne() {
		int value = simpleCalculationsController.calculateByOperation("plus_one",2).getValue();
		assertEquals(3, value);
	}

	@Test
	void testCalculatePlusTen() {
		int value = simpleCalculationsController.calculateByOperation("plus_ten",2).getValue();
		assertEquals(12, value);
	}

	@Test
	void testCalculateMinusTen() {
		int value = simpleCalculationsController.calculateByOperation("minus_ten",2).getValue();
		assertEquals(-8, value);
	}
}
