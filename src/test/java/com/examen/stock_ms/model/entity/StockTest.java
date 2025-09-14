package com.examen.stock_ms.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
    }
    @Test
    void testStockGettersAndSetters() {

        stock.setId(1);
        stock.setProductId(2);
        stock.setWareHouseId(2);
        stock.setQuantity(30);

        assertNotNull(stock);
        assertEquals(1, stock.getId());
        assertEquals(2, stock.getProductId());
        assertEquals(2, stock.getWareHouseId());
        assertEquals(30, stock.getQuantity());
    }

}
