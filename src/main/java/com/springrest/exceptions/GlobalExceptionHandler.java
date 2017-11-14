package com.springrest.exceptions;

/*
created by PopoPenguin on 11/13/17
*/

import com.springrest.model.CustomResponseObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity APINotFoundExceptionHandler (InvalidRequestException ae){

        CustomResponseObject response = new CustomResponseObject();

        response.setError(ae);
        response.setStatus_code(ae.getStatus_code());
        response.setMessage(ae.getMessage());

        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus_code()));
    }


}
