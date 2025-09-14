package com.examen.stock_ms.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorDetailDtoTest {

    private ErrorDetailDto errorDetailDto;

    @BeforeEach
    void setUp() {
        errorDetailDto = new ErrorDetailDto("Quantity of product cannot be 0", "2020-09-18 14:05:23");
    }

    @Test
    void testErrorDetailDtoGetter() {
        assertNotNull(errorDetailDto);
        assertEquals("Quantity of product cannot be 0", errorDetailDto.message());
        assertEquals("2020-09-18 14:05:23", errorDetailDto.dateTime());
    }
}
