package com.Wario.WaaS.Transaction.Repository;

import com.Wario.WaaS.Transaction.Entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Long>, JpaRepository<Transaction,Long> {

    List<Transaction> findAllByToIdentifierOrFromIdentifier(String toIdentifier, String fromIdentifier, Pageable pageable);

}
