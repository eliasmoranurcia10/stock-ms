package com.examen.stock_ms.controller.advice;

import com.examen.stock_ms.exception.BadRequestException;
import com.examen.stock_ms.exception.InternalServerErrorException;
import com.examen.stock_ms.exception.ResourceNotFoundException;
import com.examen.stock_ms.model.dto.ErrorDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHander {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailDto> handleExceptionNotExists(ResourceNotFoundException ex){
        ErrorDetailDto error = new ErrorDetailDto("not-exists", LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetailDto> handleBadRequest(BadRequestException ex){
        ErrorDetailDto error = new ErrorDetailDto("bad-request", LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorDetailDto> handleInternalServer(InternalServerErrorException ex){
        ErrorDetailDto error = new ErrorDetailDto("internal-server-error", LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

}
