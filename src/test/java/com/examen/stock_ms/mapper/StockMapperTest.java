package com.examen.stock_ms.mapper;

import com.examen.stock_ms.model.dto.SaveStockRequestDto;
import com.examen.stock_ms.model.dto.SaveStockResponseDto;
import com.examen.stock_ms.model.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StockMapperTest {

    @Autowired
    private StockMapper stockMapper;

    @Test
    void testToStock() {
        SaveStockRequestDto saveStockRequestDto = new SaveStockRequestDto(5,4,3);

        Stock stock = stockMapper.toStock(saveStockRequestDto);

        assertNotNull(stock);
        assertEquals(saveStockRequestDto.productId(), stock.getProductId());
        assertEquals(saveStockRequestDto.wareHouseId(), stock.getWareHouseId());
        assertEquals(saveStockRequestDto.quantity(), stock.getQuantity());
    }

    @Test
    void testToStocks() {

        List<SaveStockRequestDto> saveStocksRequestDto = List.of(
                new SaveStockRequestDto(5,3,3),
                new SaveStockRequestDto(3,9,8),
                new SaveStockRequestDto(4,5,2)
        );

        List<Stock> stocks = stockMapper.toStocks(saveStocksRequestDto);

        assertNotNull(stocks);
        assertEquals(saveStocksRequestDto.size(), stocks.size());
        assertEquals(saveStocksRequestDto.getFirst().productId(), stocks.getFirst().getProductId());
        assertEquals(saveStocksRequestDto.get(1).wareHouseId(), stocks.get(1).getWareHouseId());
        assertEquals(saveStocksRequestDto.get(2).quantity(), stocks.get(2).getQuantity());
    }

    @Test
    void testToSaveStockResponseDto() {
        Stock stock = new Stock(3,5,4,3);

        SaveStockResponseDto saveStockResponseDto = stockMapper.toSaveStockResponseDto(stock);

        assertNotNull(saveStockResponseDto);
        assertEquals(stock.getProductId(), saveStockResponseDto.productId());
        assertEquals(stock.getWareHouseId(), saveStockResponseDto.wareHouseId());
        assertEquals(stock.getQuantity(), saveStockResponseDto.quantity());
    }

    @Test
    void testToSaveStocksResponseDto() {
        List<Stock> stocks = List.of(
                new Stock(1,2,4,3),
                new Stock(4,6,3,2),
                new Stock(5,6,3,6)
        );

        List<SaveStockResponseDto> saveStocksResponseDto = stockMapper.toSaveStocksResponseDto(stocks);

        assertNotNull(saveStocksResponseDto);
        assertEquals(stocks.size(), saveStocksResponseDto.size());
        assertEquals(stocks.getFirst().getProductId(), saveStocksResponseDto.getFirst().productId());
        assertEquals(stocks.get(1).getWareHouseId(), saveStocksResponseDto.get(1).wareHouseId());
        assertEquals(stocks.get(2).getQuantity(), saveStocksResponseDto.get(2).quantity());
    }


}
