package pe.com.ccasani.api.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import pe.com.ccasani.api.exception.ServiceException;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorValid(BindException ex){

        return ErrorResponse.builder()
                .codigo("ERROR-REQUEST")
                .mensage(ex.getBindingResult().getFieldError().getField())
                .build();
    }
    @ExceptionHandler({HttpServerErrorException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse serverError(Exception e){
        return ErrorResponse.builder().codigo("ERROR-API").mensage("Estimado cliente en estos momentos estamos trabajando para mejorar la atención").build();
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse errorService(Exception e){
        return ErrorResponse.builder().codigo("ERROR-API").mensage("Recurso no encontrado").build();
    }

    @Data
    @AllArgsConstructor
    @Builder
    private static class ErrorResponse{
        private String codigo;
        private String mensage;


    }


}
