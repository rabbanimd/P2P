package com.cortes.p2p.Resolve.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserDataException extends RuntimeException {
    private String fieldName;
    private String fieldValue;


    public InvalidUserDataException(String fieldName, String fieldValue) {
        super(fieldName + " is invalid : " + fieldValue);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
