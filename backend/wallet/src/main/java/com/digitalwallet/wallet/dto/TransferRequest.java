package com.digitalwallet.wallet.dto;

import lombok.Data;

@Data
public class TransferRequest {

    private Long senderId;
    private String receiverEmail;
    private Double amount;

}