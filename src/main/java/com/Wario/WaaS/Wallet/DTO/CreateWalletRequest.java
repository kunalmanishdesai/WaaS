package com.Wario.WaaS.Wallet.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateWalletRequest {

    @NotNull
    private String userId;

    @NotNull

    private BigDecimal balance;
}
