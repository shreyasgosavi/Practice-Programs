package com.tdd.customexception;

public class InvalidDelimiterException extends RuntimeException{

    public InvalidDelimiterException(String exceptionMessage){
        super(exceptionMessage);
    }
}
