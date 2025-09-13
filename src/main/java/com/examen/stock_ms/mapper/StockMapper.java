package com.examen.stock_ms.mapper;

import com.examen.stock_ms.model.dto.SaveStockRequestDto;
import com.examen.stock_ms.model.dto.SaveStockResponseDto;
import com.examen.stock_ms.model.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock toStock(SaveStockRequestDto saveStockRequestDto);
    List<Stock> toStocks(List<SaveStockRequestDto> saveStocksRequestDto);


    SaveStockResponseDto toSaveStockResponseDto(Stock stock);
    List<SaveStockResponseDto> toSaveStocksResponseDto(List<Stock> stocks);
}
