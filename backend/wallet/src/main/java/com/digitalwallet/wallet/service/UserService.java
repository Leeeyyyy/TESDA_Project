package com.digitalwallet.wallet.service;

import com.digitalwallet.wallet.dto.CashInRequest;
import com.digitalwallet.wallet.dto.LoginRequest;
import com.digitalwallet.wallet.dto.RegisterRequest;
import com.digitalwallet.wallet.entity.Transaction;
import com.digitalwallet.wallet.entity.User;
import com.digitalwallet.wallet.repository.TransactionRepository;
import com.digitalwallet.wallet.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.digitalwallet.wallet.dto.TransferRequest;
import com.digitalwallet.wallet.dto.ChangePinRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public UserService(UserRepository userRepository,
                       TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public String register(RegisterRequest request) {

        Optional<User> existingUser =
                userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            return "Email already exists.";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPin(request.getPin());
        user.setBalance(0.0);

        userRepository.save(user);

        return "Registration Successful";
    }

    public User login(LoginRequest request) {

        Optional<User> user =
                userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            return null;
        }

        if (!user.get().getPin().equals(request.getPin())) {
            return null;
        }

        return user.get();
    }

    public String cashIn(CashInRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElse(null);

        if (user == null) {
            return "User not found.";
        }

        if (request.getAmount() == null || request.getAmount() <= 0) {
            return "Invalid amount.";
        }

        user.setBalance(user.getBalance() + request.getAmount());

        userRepository.save(user);

        Transaction transaction = new Transaction();

        transaction.setType("Cash In");
        transaction.setAmount(request.getAmount());
        transaction.setDescription("Cash In");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setUser(user);

        transactionRepository.save(transaction);

        return "Cash In Successful";
    }
        public User getUser(Long id) {

        return userRepository.findById(id).orElse(null);

    }
        public List<Transaction> getTransactions(Long id) {

        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null) {
            return List.of();
        }

        return transactionRepository
                .findByUserOrderByTransactionDateDesc(user);

        }
         public String transfer(TransferRequest request) {

        User sender = userRepository.findById(request.getSenderId())
                .orElse(null);

        if (sender == null) {
            return "Sender not found.";
        }

        User receiver = userRepository.findByEmail(request.getReceiverEmail())
                .orElse(null);

        if (receiver == null) {
            return "Receiver not found.";
        }

        if (sender.getId().equals(receiver.getId())) {
            return "You cannot transfer to your own account.";
        }

        if (request.getAmount() == null || request.getAmount() <= 0) {
            return "Invalid amount.";
        }

        if (sender.getBalance() < request.getAmount()) {
            return "Insufficient balance.";
        }

        sender.setBalance(sender.getBalance() - request.getAmount());
        receiver.setBalance(receiver.getBalance() + request.getAmount());

        userRepository.save(sender);
        userRepository.save(receiver);

        Transaction sent = new Transaction();
        sent.setUser(sender);
        sent.setType("Transfer Sent");
        sent.setAmount(request.getAmount());
        sent.setDescription("Sent to " + receiver.getEmail());
        sent.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(sent);

        Transaction received = new Transaction();
        received.setUser(receiver);
        received.setType("Transfer Received");
        received.setAmount(request.getAmount());
        received.setDescription("Received from " + sender.getEmail());
        received.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(received);

        return "Transfer Successful";
    }
        public String changePin(ChangePinRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElse(null);

        if (user == null) {
            return "User not found.";
        }

        if (!user.getPin().equals(request.getOldPin())) {
            return "Current PIN is incorrect.";
        }

        if (request.getNewPin() == null ||
                request.getNewPin().length() != 4) {

            return "PIN must be exactly 4 digits.";
        }

        user.setPin(request.getNewPin());

        userRepository.save(user);

        return "PIN updated successfully.";

    }
}