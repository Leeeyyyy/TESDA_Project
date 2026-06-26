package com.digitalwallet.wallet.controller;

import com.digitalwallet.wallet.dto.ChangePinRequest;
import com.digitalwallet.wallet.dto.CashInRequest;
import com.digitalwallet.wallet.dto.TransferRequest;
import com.digitalwallet.wallet.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digitalwallet.wallet.entity.User;
import java.util.List;
import com.digitalwallet.wallet.entity.Transaction;

@RestController
@RequestMapping("/api/wallet")
@CrossOrigin(origins = "*")
public class WalletController {

    private final UserService userService;

    public WalletController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cashin")
    public String cashIn(@RequestBody CashInRequest request) {

        return userService.cashIn(request);

    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {

        return userService.getUser(id);

    }
    @GetMapping("/transactions/{id}")
    public List<Transaction> getTransactions(
            @PathVariable Long id) {

        return userService.getTransactions(id);

    }
        @PostMapping("/transfer")
        public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {

            String result = userService.transfer(request);

            if (!result.equals("Transfer Successful")) {
                return ResponseEntity.badRequest().body(result);
            }

            return ResponseEntity.ok(result);
        }   
    @PostMapping("/change-pin")
    public String changePin(@RequestBody ChangePinRequest request) {

        return userService.changePin(request);

    }
}