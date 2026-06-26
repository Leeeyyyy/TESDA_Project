package com.digitalwallet.wallet.repository;

import com.digitalwallet.wallet.entity.Transaction;
import com.digitalwallet.wallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserOrderByTransactionDateDesc(User user);

}