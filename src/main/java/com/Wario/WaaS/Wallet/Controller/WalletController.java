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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/companies/{companyId}")
@RequiredArgsConstructor
public class WalletController {

    private final WalletFactory walletFactory;

    @PostMapping(value = "/wallet")
    public WalletEntity createWallet(@PathVariable String companyId,
                                     @RequestBody CreateWalletRequest createWalletRequest) {
        return walletFactory.createWallet(companyId,createWalletRequest);
    }
    @GetMapping(value = "/users/{userId}/wallet", produces = "application/json")
    public WalletEntity getWallet(@PathVariable String companyId,
                                  @PathVariable String userId) {
        return walletFactory.getWalletByCompanyIdAndUserId(companyId, userId);
    }

    @PostMapping(value = "/users/{userId}/wallet/addBalance")
    public WalletEntity addBalance(@PathVariable String companyId,
                                   @PathVariable String userId,
                                   @RequestBody AddBalanceRequest addBalanceRequest) {
        return walletFactory.addBalance(companyId,userId,addBalanceRequest);
    }

    @PostMapping(value = "/users/{userId}/wallet/buy", produces = "application/json")
    public Transaction buy(@PathVariable String companyId,
                           @PathVariable String userId,
                           @RequestBody TransactionDTO transactionDTO) {

        return walletFactory.buy(companyId,userId,transactionDTO);
    }

    @GetMapping(value = "/users/{userId}/wallet/transactions", produces = "application/json")
    public List<Transaction> getTransactionsByWallet(@PathVariable String companyId,
                                                     @PathVariable String userId,
                                                     @RequestParam int page,
                                                     @RequestParam int limit,
                                                     @RequestParam @Nullable String sortBy) {

        Pageable pageable = PageRequest.of(page,limit);
        if (sortBy != null) {
            pageable = PageRequest.of(page,limit, Sort.by(sortBy));
        }

        return walletFactory.getTransactions(companyId,userId,pageable);
    }
}
