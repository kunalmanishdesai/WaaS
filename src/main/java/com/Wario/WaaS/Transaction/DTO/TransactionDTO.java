package com.Wario.WaaS.Transaction.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class TransactionDTO {

    @NotNull
    private BigDecimal amount;

    private String comment;
}
