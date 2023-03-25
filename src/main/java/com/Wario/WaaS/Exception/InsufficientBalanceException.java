package com.Wario.WaaS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends RuntimeException {

    private static final String insufficientException = "Insufficient Balance in Wallet";

    public InsufficientBalanceException() {
        super(insufficientException);
    }
}
