package com.examen.stock_ms.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaveStockResponseDtoTest {
    private SaveStockResponseDto saveStockResponseDto;

    @BeforeEach
    void setUp() {
        saveStockResponseDto = new SaveStockResponseDto(1,3,4,45);
    }

    @Test
    void testSaveStockResponseDtoGetter() {
        assertNotNull(saveStockResponseDto);
        assertEquals(1, saveStockResponseDto.id());
        assertEquals(3, saveStockResponseDto.productId());
        assertEquals(4, saveStockResponseDto.wareHouseId());
        assertEquals(45, saveStockResponseDto.quantity());
    }
}
