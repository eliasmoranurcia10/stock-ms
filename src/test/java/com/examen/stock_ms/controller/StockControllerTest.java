package com.examen.stock_ms.controller;

import com.examen.stock_ms.model.dto.FindByProductIdDto;
import com.examen.stock_ms.model.dto.SaveStockRequestDto;
import com.examen.stock_ms.model.dto.SaveStockResponseDto;
import com.examen.stock_ms.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StockService stockService;

    @Test
    void testUnitsQuantityByProductId_Success() throws Exception {
        FindByProductIdDto findByProductIdDto = new FindByProductIdDto(1,32);

        when(stockService.cantidadDeUnidadesPorProducto(1)).thenReturn(findByProductIdDto);

        mockMvc.perform( get("/stock/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$.productId").value(1) )
                .andExpect( jsonPath("$.total").value(32) );

        verify(stockService).cantidadDeUnidadesPorProducto(1);
        verifyNoMoreInteractions(stockService);

    }

    @Test
    void testSaveStocks_Success() throws Exception {
        List<SaveStockRequestDto> saveStocksRequestDto = List.of( new SaveStockRequestDto(10,3,22) );
        List<SaveStockResponseDto> saveStocksResponseDto = List.of( new SaveStockResponseDto(2,10,3,22) );

        when(stockService.saveStocks(saveStocksRequestDto)).thenReturn(saveStocksResponseDto);

        mockMvc.perform( post("/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saveStocksRequestDto)) )
                .andDo( print() )
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.size()").value(1) )
                .andExpect( jsonPath("$[0].id").value(2) )
                .andExpect( jsonPath("$[0].productId").value(10) )
                .andExpect( jsonPath("$[0].wareHouseId").value(3) )
                .andExpect( jsonPath("$[0].quantity").value(22) );

        verify(stockService).saveStocks(anyList());
    }

}
