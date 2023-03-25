package com.Wario.WaaS.Wallet.Factory;

import com.Wario.WaaS.Exception.InsufficientBalanceException;
import com.Wario.WaaS.Exception.WalletNotFound;
import com.Wario.WaaS.Wallet.DTO.CreateWalletRequest;
import com.Wario.WaaS.Transaction.DTO.TransactionDTO;
import com.Wario.WaaS.Transaction.Entity.Transaction;
import com.Wario.WaaS.Transaction.Service.TransactionFactory;
import com.Wario.WaaS.Wallet.DTO.AddBalanceRequest;
import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import com.Wario.WaaS.Wallet.Enum.WalletType;
import com.Wario.WaaS.Wallet.Repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class WalletFactory {

    private final WalletRepository walletRepository;

    private final TransactionFactory transactionFactory;

    public WalletEntity createWallet(String companyId,
                                     CreateWalletRequest createWalletRequest) {

        WalletEntity wallet = walletRepository.save(WalletEntity.builder()
                .userId(createWalletRequest.getUserId())
                .companyId(companyId)
                .balance(BigDecimal.ZERO)
                .walletType(WalletType.Closed)
                .build()
        );


        if (createWalletRequest.getBalance().equals(BigDecimal.ZERO)) {
            return wallet;
        }

        addBalance(wallet,createWalletRequest.getBalance());
        return wallet;
    }

    public WalletEntity getWalletByCompanyIdAndUserId(String companyId,
                                                      String userId) {
        return walletRepository.findByCompanyIdAndUserId(companyId,userId)
                .orElseThrow(WalletNotFound::new);
    }


    private Transaction addBalance(WalletEntity wallet,BigDecimal amount) {
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        return transactionFactory.addMoney(wallet, TransactionDTO.builder()
                .amount(amount)
                .build());
    }

    public Transaction addBalance(String companyId, String userId, AddBalanceRequest addBalanceRequest) {
        return addBalance(getWalletByCompanyIdAndUserId(companyId,userId),addBalanceRequest.getAmount());
    }

    public Transaction buy(String companyId, String userId, TransactionDTO transactionDTO) {
        WalletEntity wallet = getWalletByCompanyIdAndUserId(companyId,userId);

        if (wallet.getBalance().compareTo(transactionDTO.getAmount()) < 0) {
            throw new InsufficientBalanceException();
        }

        wallet.setBalance(wallet.getBalance().subtract(transactionDTO.getAmount()));
        walletRepository.save(wallet);

        return transactionFactory.spendMoney(wallet,transactionDTO);
    }

    public List<Transaction> getTransactions(String companyId, String userId, Pageable pageable) {
        WalletEntity wallet = getWalletByCompanyIdAndUserId(companyId,userId);
        return transactionFactory.getTransactionsForWallet(wallet,pageable);
    }
}
