package com.examen.stock_ms.model.dto;

public record SaveStockRequestDto(
        Integer productId,
        Integer wareHouseId,
        Integer quantity
) {
}
