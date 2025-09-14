package com.examen.stock_ms.controller.advice;

import com.examen.stock_ms.exception.BadRequestException;
import com.examen.stock_ms.exception.InternalServerErrorException;
import com.examen.stock_ms.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DummyController {

    @GetMapping("/notfound")
    public String notFound() {
        throw new ResourceNotFoundException("Recurso no encontrado");
    }

    @GetMapping("/badrequest")
    public String badRequest() {
        throw new BadRequestException("Petición inválida");
    }

    @GetMapping("/internal")
    public String internal() {
        throw new InternalServerErrorException("Error interno");
    }
}
