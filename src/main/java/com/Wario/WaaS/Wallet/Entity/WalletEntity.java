package com.Wario.WaaS.Wallet.Entity;

import com.Wario.WaaS.Wallet.Enum.WalletType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WalletEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    private String owner;

    @Column(unique = true)
    private String userId;

    private String companyId;

    private BigDecimal balance;
}
