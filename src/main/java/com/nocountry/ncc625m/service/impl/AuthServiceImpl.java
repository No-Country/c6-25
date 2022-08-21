package com.nocountry.ncc625m.service.impl;

import com.nocountry.ncc625m.auth.security.JwtTokenProvider;
import com.nocountry.ncc625m.auth.utility.AuthenticationErrorEnum;
import com.nocountry.ncc625m.auth.utility.RoleEnum;
import com.nocountry.ncc625m.entity.RoleEntity;
import com.nocountry.ncc625m.entity.UserEntity;
import com.nocountry.ncc625m.exception.EmailAlreadyExistException;
import com.nocountry.ncc625m.exception.InvalidCredentialsException;
import com.nocountry.ncc625m.exception.NotEnoughAgeException;
import com.nocountry.ncc625m.mapper.MovimientoMapper;
import com.nocountry.ncc625m.mapper.UserMapper;
import com.nocountry.ncc625m.dto.request.RegisterRequest;
import com.nocountry.ncc625m.dto.response.AuthResponse;
import com.nocountry.ncc625m.dto.response.UserResponse;
import com.nocountry.ncc625m.repository.RoleRepository;
import com.nocountry.ncc625m.repository.UserRepository;
import com.nocountry.ncc625m.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse register(RegisterRequest userRegister) {
        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new EmailAlreadyExistException(userRegister.getEmail());
        }

        if(userRegister.getAge() < 18){
            throw new NotEnoughAgeException();
        }

        Set<RoleEntity> roleEntity = roleRepository.findByName(RoleEnum.USER.getFullRoleName());

        if (roleEntity.isEmpty()) {
            throw new NullPointerException();
        }

        UserEntity userEntity = userMapper.requestToEntity(userRegister, roleEntity);
        userEntity = userRepository.save(userEntity);

        AuthResponse authResponse = userMapper.toUserRegisterResponde(
                userEntity,
                jwtTokenProvider.generateToken(authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userRegister.getEmail(), userRegister.getPassword()))),
                movimientoMapper.entityListToResponse(userEntity.getMovimientos()));

        return authResponse;
    }

    @Override
    public AuthResponse login(String email, String password) throws Exception {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserEntity userEntity = userRepository.findByEmail(email).get();

            return userMapper.toUserRegisterResponde(
                    userEntity,
                    jwtTokenProvider.generateToken(authentication),
                    movimientoMapper.entityListToResponse(userEntity.getMovimientos()));

        } catch (Exception e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse userAuth(String token) {
        token = token.replace("Bearer ", "");
        String email = jwtTokenProvider.getJWTUsername(token);

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the searched user does not exist"));
        return userMapper.convertTo(userEntity, movimientoMapper.entityListToResponse(userEntity.getMovimientos()));
    }

}
