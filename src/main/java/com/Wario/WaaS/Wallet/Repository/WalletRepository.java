package com.Wario.WaaS.Wallet.Repository;

import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity,Long> {

   Optional<WalletEntity> findByCompanyIdAndUserId(String companyId, String userId);
}
