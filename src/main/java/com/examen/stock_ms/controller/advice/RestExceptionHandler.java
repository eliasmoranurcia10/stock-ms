package com.examen.stock_ms.controller.advice;

import com.examen.stock_ms.exception.BadRequestException;
import com.examen.stock_ms.exception.InternalServerErrorException;
import com.examen.stock_ms.exception.ResourceNotFoundException;
import com.examen.stock_ms.model.dto.ErrorDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class RestExceptionHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailDto> handleExceptionNotExists(ResourceNotFoundException ex){
        ErrorDetailDto error = new ErrorDetailDto(ex.getMessage(), LocalDateTime.now().format(formatter) );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetailDto> handleBadRequest(BadRequestException ex){
        ErrorDetailDto error = new ErrorDetailDto(ex.getMessage(), LocalDateTime.now().format(formatter) );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorDetailDto> handleInternalServer(InternalServerErrorException ex){
        ErrorDetailDto error = new ErrorDetailDto(ex.getMessage(), LocalDateTime.now().format(formatter) );
        return ResponseEntity.badRequest().body(error);
    }

}
