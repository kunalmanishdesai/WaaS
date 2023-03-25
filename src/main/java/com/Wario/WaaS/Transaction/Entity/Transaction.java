package com.Wario.WaaS.Transaction.Entity;

import com.Wario.WaaS.Exception.InvalidDataException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TransactionSeqGenerator")
    @SequenceGenerator(name = "TransactionSeqGenerator", sequenceName = "transaction_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fromIdentifier;

    private String toIdentifier;

    private LocalDateTime dateTime;
    private String remark;

    @NotNull
    private BigDecimal amount;

    @PrePersist
    private void prePersist() {
        if ( fromIdentifier == null && toIdentifier == null)
            throw new InvalidDataException("Either toIdentifier or fromIdentifier should be present");

        dateTime = LocalDateTime.now();
    }
}
