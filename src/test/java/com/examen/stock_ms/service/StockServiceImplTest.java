package com.examen.stock_ms.service;

import com.examen.stock_ms.exception.BadRequestException;
import com.examen.stock_ms.exception.InternalServerErrorException;
import com.examen.stock_ms.exception.ResourceNotFoundException;
import com.examen.stock_ms.mapper.StockMapper;
import com.examen.stock_ms.model.dto.FindByProductIdDto;
import com.examen.stock_ms.model.dto.SaveStockRequestDto;
import com.examen.stock_ms.model.dto.SaveStockResponseDto;
import com.examen.stock_ms.model.entity.Stock;
import com.examen.stock_ms.repository.StockRepository;
import com.examen.stock_ms.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {
    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockMapper stockMapper;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock;
    private FindByProductIdDto findByProductIdDto;
    private SaveStockResponseDto saveStockResponseDto;

    @BeforeEach
    void setUp() {
        stock = new Stock(1,3,4,6);
        findByProductIdDto = new FindByProductIdDto(3,6);
        saveStockResponseDto = new SaveStockResponseDto(1,3,4,6);
    }

    @Test
    void testCantidadDeUnidadesPorProducto_TotalQuantityExists() {
        Integer productId = 1;
        when(stockRepository.getTotalQuantityByProductId(productId)).thenReturn(6);

        FindByProductIdDto result = stockService.cantidadDeUnidadesPorProducto(productId);

        assertNotNull(result);
        assertEquals(productId, result.productId());
        assertEquals(6, result.total());

    }

    @Test
    void testCantidadDeUnidadesPorProducto_ProductIdIsZero() {
        Integer productId = 0;
        RuntimeException exception = assertThrows(
                BadRequestException.class,
                () -> stockService.cantidadDeUnidadesPorProducto(0)
        );

        assertEquals("El producto id no puede ser cero", exception.getMessage());
    }

    @Test
    void testCantidadDeUnidadesPorProducto_ProductIdNotFound() {
        Integer productId = 99;
        when(stockRepository.getTotalQuantityByProductId(productId)).thenReturn(null);
        RuntimeException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> stockService.cantidadDeUnidadesPorProducto(productId)
        );

        assertEquals("No se encontró el identificador del producto: " + productId, exception.getMessage());
    }

    @Test
    void testSaveStocks_Success() {
        List<SaveStockRequestDto> saveStocksRequestDto = List.of(
                new SaveStockRequestDto(3,4,6)
        );
        List<SaveStockResponseDto> saveStocksResponseDto = List.of(
                new SaveStockResponseDto(1,3,4,6)
        );
        when(stockMapper.toStocks(saveStocksRequestDto)).thenReturn(List.of(stock));
        when(stockRepository.saveAllAndFlush(List.of(stock))).thenReturn(List.of(stock));
        when(stockMapper.toSaveStocksResponseDto(List.of(stock))).thenReturn(saveStocksResponseDto);

        List<SaveStockResponseDto> result = stockService.saveStocks(saveStocksRequestDto);

        assertNotNull(result);
        assertEquals(saveStocksResponseDto.size(), result.size());
        assertEquals(saveStocksResponseDto.getFirst().productId(), result.getFirst().productId());
        assertEquals(saveStocksResponseDto.getFirst().wareHouseId(), result.getFirst().wareHouseId());
        assertEquals(saveStocksResponseDto.getFirst().quantity(), result.getFirst().quantity());
    }

    @Test
    void testSaveStocks_InternalError() {
        List<SaveStockRequestDto> saveStocksRequestDtoFail = List.of(
                new SaveStockRequestDto(3, 4, 6)
        );

        // Simulamos que el repositorio falla
        when(stockMapper.toStocks(any())).thenThrow(new RuntimeException("DB error"));

        InternalServerErrorException exception = assertThrows(
                InternalServerErrorException.class,
                () -> stockService.saveStocks(saveStocksRequestDtoFail)
        );

        assertEquals("Ocurrió un error al guardar el stock", exception.getMessage());
    }

}
