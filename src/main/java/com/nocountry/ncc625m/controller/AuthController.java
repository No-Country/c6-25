package com.nocountry.ncc625m.controller;

import com.nocountry.ncc625m.auth.utility.AuthenticationErrorEnum;
import com.nocountry.ncc625m.dto.request.AuthenticateRequest;
import com.nocountry.ncc625m.dto.request.RegisterRequest;
import com.nocountry.ncc625m.dto.response.AuthResponse;
import com.nocountry.ncc625m.dto.response.UserResponse;
import com.nocountry.ncc625m.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody AuthenticateRequest authRequest) throws Exception {
        AuthResponse check = authService.login(authRequest.getEmail(), authRequest.getPassword());
        return check.getEmail() != AuthenticationErrorEnum.OK.name() ? ResponseEntity.ok(check) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(check);

    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.userAuth(token));
    }

}
