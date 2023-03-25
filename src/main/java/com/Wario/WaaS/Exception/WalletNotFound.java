package com.Wario.WaaS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WalletNotFound extends RuntimeException {

    private static final String walletNotFound = "Wallet not found";

    public WalletNotFound() {
        super(walletNotFound);
    }
}
