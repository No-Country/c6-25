package com.nocountry.ncc625m.auth.service;

import com.nocountry.ncc625m.auth.dto.UserDTO;
import com.nocountry.ncc625m.auth.dto.request.AuthenticationRequest;
import com.nocountry.ncc625m.auth.dto.response.Jwt;
import com.nocountry.ncc625m.auth.service.mapper.UserAuthMapper;
import com.nocountry.ncc625m.auth.util.JwUtils;
import com.nocountry.ncc625m.exception.AlreadyExistsException;
import com.nocountry.ncc625m.model.UserEntity;
import com.nocountry.ncc625m.repository.RoleRepository;
import com.nocountry.ncc625m.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserAuthMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private JwUtils jwUtils;

    @Transactional
    public Jwt save (UserDTO userDTO) throws AlreadyExistsException {
        String encryptPass = passwordEncoder.encode(userDTO.getPassword());
        if (userRepository.findByEmail(userDTO.getEmail()) != null) throw new AlreadyExistsException("Email is already in use");
        UserEntity user = userMapper.userDTOtoEntity(userDTO);
        user.setPassword(encryptPass);
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);

        String jwt = jwUtils.generateToken(userDetailsCustomService.loadUserByUsername(user.getEmail()));

        return Jwt.builder()
                .email(userDTO.getEmail())
                .token(jwt)
                .build();
    }

    public Jwt authentication (AuthenticationRequest authenticationRequest){
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email or password incorrect", e);
        }

        final String jwt =  jwUtils.generateToken(userDetails);

        return Jwt.builder()
                .email(authenticationRequest.getEmail())
                .token(jwt)
                .build();
    }


}
