package com.nocountry.ncc625m.auth.controller;

import com.nocountry.ncc625m.auth.dto.UserDTO;
import com.nocountry.ncc625m.auth.dto.request.AuthenticationRequest;
import com.nocountry.ncc625m.auth.dto.response.Jwt;
import com.nocountry.ncc625m.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;


    @PostMapping("/register")
    public ResponseEntity<Jwt> register(@Valid @RequestBody UserDTO user) throws Exception {


        Jwt jwt = userDetailsCustomService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
    }


    @PostMapping("/login")
    public ResponseEntity<Jwt> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        Jwt jwt = userDetailsCustomService.authentication(authenticationRequest);

        return ResponseEntity.ok(jwt);

    }

}
