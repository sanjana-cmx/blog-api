package com.app.blog.exceptions;

import com.app.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler (ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(msg,false);
        return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    //MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidExceptions (MethodArgumentNotValidException ex){
        Map<String,String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((objectError ->
        {
          String field=  ((FieldError)objectError).getField();
          String msg=  objectError.getDefaultMessage();
          response.put(field,msg);
        }));

        return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
    }
}
