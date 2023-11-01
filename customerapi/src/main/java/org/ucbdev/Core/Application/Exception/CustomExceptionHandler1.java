package org.ucbdev.Core.Application.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.ucbdev.Core.Application.Model.CustomErrorResponse1;

import java.time.Instant;

@ControllerAdvice
public class CustomExceptionHandler1 extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException1.class)
    public ResponseEntity<CustomErrorResponse1> handleException1(CustomException1 exception){
        var customErrorResponse1 = CustomErrorResponse1.builder().error(exception.getErrorText()).message(exception.getMessage()).status(exception.getHttpStatus().value()).timestamp(Instant.now()).build();
        return ResponseEntity.status(exception.getHttpStatus()).body(customErrorResponse1);
    }
}
