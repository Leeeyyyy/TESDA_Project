package com.digitalwallet.wallet.controller;

import com.digitalwallet.wallet.dto.LoginRequest;
import com.digitalwallet.wallet.dto.RegisterRequest;
import com.digitalwallet.wallet.entity.User;
import com.digitalwallet.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return userService.register(request);

    }

    @PostMapping("/login")
    public User login(
            @RequestBody LoginRequest request) {

        return userService.login(request);

    }

}