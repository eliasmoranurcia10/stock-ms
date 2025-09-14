package com.examen.stock_ms.controller.advice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DummyController.class)
public class RestExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testResourceNotFoundException() throws Exception {
        mockMvc.perform(get("/test/notfound"))
                .andExpect(status().isBadRequest()) // tu handler devuelve 400
                .andExpect(jsonPath("$.message").value("Recurso no encontrado"))
                .andExpect(jsonPath("$.dateTime").exists());
    }

    @Test
    void testBadRequestException() throws Exception {
        mockMvc.perform(get("/test/badrequest"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Petición inválida"))
                .andExpect(jsonPath("$.dateTime").exists());
    }

    @Test
    void testInternalServerErrorException() throws Exception {
        mockMvc.perform(get("/test/internal"))
                .andExpect(status().isBadRequest()) // también devuelve 400
                .andExpect(jsonPath("$.message").value("Error interno"))
                .andExpect(jsonPath("$.dateTime").exists());
    }
}
