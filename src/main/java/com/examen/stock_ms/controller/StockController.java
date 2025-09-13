package com.examen.stock_ms.controller.advice;

import com.examen.stock_ms.model.dto.FindByProductIdDto;
import com.examen.stock_ms.model.dto.SaveStockRequestDto;
import com.examen.stock_ms.model.dto.SaveStockResponseDto;
import com.examen.stock_ms.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{productId}")
    public FindByProductIdDto unitsQuantityByProductId(@PathVariable Integer productId) {
        return stockService.cantidadDeUnidadesPorProducto(productId);
    }

    @PostMapping
    public SaveStockResponseDto saveStock(@RequestBody SaveStockRequestDto saveStockRequestDto) {
        return stockService.saveStock(saveStockRequestDto);
    }
}
