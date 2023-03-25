package com.Wario.WaaS.Wallet.Controller;

import com.Wario.WaaS.Wallet.Entity.WalletEntity;
import com.Wario.WaaS.Wallet.Factory.WalletFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class Controller {

    private final WalletFactory walletFactory;

    @GetMapping(value = "company/{companyId}/user/{userId}/wallet", produces = "application/json")
    public WalletEntity getWallet(@PathVariable String companyId,
                                  @PathVariable String identifier) {
        return walletFactory.getWalletByCompanyIdAndUserId(companyId, identifier);
    }

    @PostMapping(value = "companies/{companyId}/user/{userId}/wallet", produces = "application/json")
    public WalletEntity createWallet(@PathVariable String companyId,
                                     @PathVariable String userId) {
        return walletFactory.createWallet(companyId,userId);
    }
}
