package com.Wario.WaaS.Wallet.Repository;

import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity,Long> {

   Optional<WalletEntity> findByCompanyIdAndUserId(String companyId, String userId);
}
