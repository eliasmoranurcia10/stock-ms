package com.examen.stock_ms.model.dto;

import java.time.LocalDateTime;

public record ErrorDetailDto(
        String message,
        LocalDateTime dateTime
) {
}
