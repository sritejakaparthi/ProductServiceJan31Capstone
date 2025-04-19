package com.scaler.productservicejan31capstone.Exceptions;

import com.scaler.productservicejan31capstone.Dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionalHandler
{
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException()
    {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage("NullPointer exception is occurred");

        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException)
    {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage(productNotFoundException.getMessage());

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}
