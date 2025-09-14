package com.examen.stock_ms.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaveStockRequestDtoTest {

    private SaveStockRequestDto saveStockRequestDto;

    @BeforeEach
    void setUp() {
        saveStockRequestDto = new SaveStockRequestDto(1, 2,5);
    }

    @Test
    void testSaveStockRequestDtoGetter(){
        assertNotNull(saveStockRequestDto);
        assertEquals(1, saveStockRequestDto.productId());
        assertEquals(2, saveStockRequestDto.wareHouseId());
        assertEquals(5, saveStockRequestDto.quantity());
    }
}
