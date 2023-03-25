package com.Wario.WaaS.Wallet.Factory;

import com.Wario.WaaS.Exception.WalletNotFound;
import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import com.Wario.WaaS.Wallet.Enum.WalletType;
import com.Wario.WaaS.Wallet.Repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletFactory {

    private final WalletRepository walletRepository;

    public WalletEntity createWallet(String companyId,
                                     String userId) {
        return walletRepository.save(
                WalletEntity.builder()
                        .userId(userId)
                        .companyId(companyId)
                        .walletType(WalletType.Closed)
                        .build()
        );
    }

    public WalletEntity getWalletByCompanyIdAndUserId(String companyId,
                                                      String userId) {
        return walletRepository.findByCompanyIdAndUserId(companyId,userId)
                .orElseThrow(() -> new WalletNotFound(companyId,userId));
    }

}
