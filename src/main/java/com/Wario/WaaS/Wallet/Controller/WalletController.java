package com.Wario.WaaS.Wallet.Controller;

import com.Wario.WaaS.Wallet.DTO.CreateWalletRequest;
import com.Wario.WaaS.Transaction.DTO.TransactionDTO;
import com.Wario.WaaS.Transaction.Entity.Transaction;
import com.Wario.WaaS.Wallet.DTO.AddBalanceRequest;
import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import com.Wario.WaaS.Wallet.Factory.WalletFactory;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/companies/{companyId}")
@RequiredArgsConstructor
public class WalletController {

    private final WalletFactory walletFactory;

    @PostMapping(value = "/wallet")
    public ResponseEntity<WalletEntity> createWallet(@PathVariable String companyId,
                                                    @RequestBody CreateWalletRequest createWalletRequest) {
        return new ResponseEntity<>(walletFactory.createWallet(companyId, createWalletRequest),
                HttpStatus.CREATED);
    }
    @GetMapping(value = "/users/{userId}/wallet", produces = "application/json")
    public  ResponseEntity<WalletEntity> getWallet(@PathVariable String companyId,
                                  @PathVariable String userId) {
        return new ResponseEntity<>(
                walletFactory.getWalletByCompanyIdAndUserId(companyId, userId),
                HttpStatus.OK);
    }

    @PostMapping(value = "/users/{userId}/wallet/addBalance")
    public ResponseEntity<Transaction> addBalance(@PathVariable String companyId,
                                   @PathVariable String userId,
                                   @RequestBody AddBalanceRequest addBalanceRequest) {

        return new ResponseEntity<>(
                walletFactory.addBalance(companyId, userId, addBalanceRequest),
                HttpStatus.OK);
    }

    @PostMapping(value = "/users/{userId}/wallet/buy", produces = "application/json")
    public ResponseEntity<Transaction> buy(@PathVariable String companyId,
                           @PathVariable String userId,
                           @RequestBody TransactionDTO transactionDTO) {

        return new ResponseEntity<>(
                walletFactory.buy(companyId, userId, transactionDTO),
                HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/wallet/transactions", produces = "application/json")
    public ResponseEntity<List<Transaction>> getTransactionsByWallet(@PathVariable String companyId,
                                                     @PathVariable String userId,
                                                     @RequestParam int page,
                                                     @RequestParam int limit,
                                                     @RequestParam @Nullable String sortBy) {

        Pageable pageable = PageRequest.of(page,limit);

        if (sortBy != null) {
            pageable = PageRequest.of(page,limit, Sort.by(sortBy));
        }

        return new ResponseEntity<>(
                walletFactory.getTransactions(companyId, userId, pageable),
                HttpStatus.OK
        );
    }
}
