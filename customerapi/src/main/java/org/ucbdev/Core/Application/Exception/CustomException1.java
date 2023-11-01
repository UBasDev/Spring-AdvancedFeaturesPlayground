package org.ucbdev.Core.Application.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException1 extends RuntimeException{
    private final String errorText;
    private final HttpStatus httpStatus;
    public CustomException1(String errorText, HttpStatus httpStatus){
        super(errorText);
        this.errorText=errorText;
        this.httpStatus=httpStatus;
    }
}
