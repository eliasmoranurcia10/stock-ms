package com.examen.stock_ms.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindByProductIdDtoTest {

    private FindByProductIdDto findByProductIdDto;

    @BeforeEach
    void setUp() {
        findByProductIdDto = new FindByProductIdDto(1, 60);
    }

    @Test
    void testFindByProductIdDtoGetter() {
        assertNotNull(findByProductIdDto);
        assertEquals(1, findByProductIdDto.productId());
        assertEquals(60, findByProductIdDto.total());
    }
}
