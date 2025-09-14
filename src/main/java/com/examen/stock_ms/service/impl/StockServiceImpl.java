package com.examen.stock_ms.service.impl;

import com.examen.stock_ms.exception.BadRequestException;
import com.examen.stock_ms.exception.InternalServerErrorException;
import com.examen.stock_ms.exception.ResourceNotFoundException;
import com.examen.stock_ms.mapper.StockMapper;
import com.examen.stock_ms.model.dto.FindByProductIdDto;
import com.examen.stock_ms.model.dto.SaveStockRequestDto;
import com.examen.stock_ms.model.dto.SaveStockResponseDto;
import com.examen.stock_ms.model.entity.Stock;
import com.examen.stock_ms.repository.StockRepository;
import com.examen.stock_ms.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public FindByProductIdDto cantidadDeUnidadesPorProducto(Integer productId) {
        if(productId==0) throw new BadRequestException("El producto id no puede ser cero");

        Integer total = stockRepository.getTotalQuantityByProductId(productId);
        if(total==null) throw new ResourceNotFoundException("No se encontró el identificador del producto: " + productId);

        return new FindByProductIdDto(
                productId,
                total
        );
    }

    @Override
    public List<SaveStockResponseDto> saveStocks(List<SaveStockRequestDto> saveStocksRequestDto) {
        try {
            List<Stock> stocks = stockMapper.toStocks(saveStocksRequestDto);
            return stockMapper.toSaveStocksResponseDto( stockRepository.saveAllAndFlush(stocks) );
        } catch (Exception ex) {
            throw new InternalServerErrorException("Ocurrió un error al guardar el stock");
        }
    }
}
