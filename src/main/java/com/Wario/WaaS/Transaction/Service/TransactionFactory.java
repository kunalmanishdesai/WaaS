package com.Wario.WaaS.Transaction.Service;

import com.Wario.WaaS.Transaction.Entity.Transaction;
import com.Wario.WaaS.Transaction.Repository.TransactionRepository;
import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class TransactionFactory {

    TransactionRepository repository;
    public Transaction createTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public List<Transaction> getTransactionsForWallet(WalletEntity walletEntity, int page, int limit, String sortBy) {
        return repository.findAllByToIdentifierOrFromIdentifier(walletEntity.getId(),walletEntity.getId(),
                PageRequest.of(page,limit, Sort.by(sortBy)));
    };
}
