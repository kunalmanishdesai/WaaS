package com.Wario.WaaS.Transaction.Repository;

import com.Wario.WaaS.Transaction.Entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Long>, JpaRepository<Transaction,Long> {

    List<Transaction> findAllByToIdentifierOrFromIdentifier(long toIdentifier, long fromIdentifier, Pageable pageable);

}
