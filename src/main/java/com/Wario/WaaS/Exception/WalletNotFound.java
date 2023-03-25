package com.Wario.WaaS.Exception;

public class WalletNotFound extends RuntimeException {

    private static final String errorMessageTemplate = "Wallet not found for user Id: %s" +
            "for companyId: %s";
    public WalletNotFound(String companyId,String userId) {
        super(String.format(errorMessageTemplate,userId,companyId));
    }
}
