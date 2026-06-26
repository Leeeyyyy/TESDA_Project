package com.digitalwallet.wallet.dto;

import lombok.Data;

@Data
public class CashInRequest {

    private Long userId;
    private Double amount;

}