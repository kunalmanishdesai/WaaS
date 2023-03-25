package com.Wario.WaaS.Wallet.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AddBalanceRequest {

    @Min(1)
    @NotNull
    private BigDecimal amount;

}
