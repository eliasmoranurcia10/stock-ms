package com.examen.stock_ms.service;

import com.examen.stock_ms.model.dto.FindByProductIdDto;
import com.examen.stock_ms.model.dto.SaveStockRequestDto;
import com.examen.stock_ms.model.dto.SaveStockResponseDto;

import java.util.List;

public interface StockService {

    FindByProductIdDto cantidadDeUnidadesPorProducto(Integer productId);

    List<SaveStockResponseDto> saveStocks(List<SaveStockRequestDto> saveStocksRequestDto);

}
