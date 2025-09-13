package com.examen.stock_ms.model.dto;

public record SaveStockResponseDto(
        Integer id,
        Integer productId,
        Integer wareHouseId,
        Integer quantity
) {
}
