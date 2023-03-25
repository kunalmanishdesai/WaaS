package com.Wario.WaaS.Exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String error) {
        super(error);
    }
}
