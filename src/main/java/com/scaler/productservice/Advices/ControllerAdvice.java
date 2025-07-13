package com.scaler.productservice.Advices;

import com.scaler.productservice.Exceptions.ProductNotFoundException;
import com.scaler.productservice.Exceptions.UnauthorizedUserException;
import com.scaler.productservice.Exceptions.UserNotFoundException;
import com.scaler.productservice.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@org.springframework.web.bind.annotation.ControllerAdvice

@RestControllerAdvice
public class ControllerAdvice
{
    @ExceptionHandler(ProductNotFoundException.class) // ExceptionHandler purpose - if from any place of entire project, if ProductNotFoundException is thrown , then bellow method will catch it and beautify it with object ErrorDTO and return back to client
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException)
    {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(productNotFoundException.getMessage());
        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND); // HTTP Status can be set anything , its upto us to follow REST API guidelines
        return responseEntity;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException userNotFoundException)
    {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(userNotFoundException.getMessage());
        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<ErrorDTO> handleUnauthorizedUserException(UnauthorizedUserException unauthorizedUserException)
    {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(unauthorizedUserException.getMessage());
        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }


}
