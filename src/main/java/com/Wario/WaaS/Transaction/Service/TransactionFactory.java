package com.Wario.WaaS.Transaction.Service;

import com.Wario.WaaS.Transaction.DTO.TransactionDTO;
import com.Wario.WaaS.Transaction.Entity.Transaction;
import com.Wario.WaaS.Transaction.Repository.TransactionRepository;
import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionFactory {
    private final TransactionRepository repository;
    public Transaction createTransaction(WalletEntity walletEntity, TransactionDTO transactionDTO) {
        return repository.save(Transaction.builder()
                        .amount(transactionDTO.getAmount())
                        .toIdentifier(String.valueOf(walletEntity.getId()))
                        .remark(transactionDTO.getComment())
                        .build());
    }

    public List<Transaction> getTransactionsForWallet(WalletEntity walletEntity, Pageable pageable) {
        String walletIdentifier = String.valueOf(walletEntity.getId());

        return repository.findAllByToIdentifierOrFromIdentifier(walletIdentifier,
                walletIdentifier, pageable);
    };
}
