package com.nocountry.ncc625m.mapper;

import com.nocountry.ncc625m.dto.request.RegisterRequest;
import com.nocountry.ncc625m.dto.response.MovimientoResponse;
import com.nocountry.ncc625m.dto.response.AuthResponse;
import com.nocountry.ncc625m.dto.response.UserResponse;
import com.nocountry.ncc625m.entity.RoleEntity;
import com.nocountry.ncc625m.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder encoder;

    public AuthResponse toUserRegisterResponde(UserEntity userEntity, String token, Set<MovimientoResponse> movimientos) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(userEntity.getId());
        authResponse.setEmail(userEntity.getEmail());
        authResponse.setFirstName(userEntity.getFirstName());
        authResponse.setLastName(userEntity.getLastName());
        authResponse.setPhoto(userEntity.getPhoto());
        authResponse.setBalance(userEntity.getBalance());
        authResponse.setMovimientos(movimientos);
        authResponse.setToken(token);
        return authResponse;
    }

    public UserResponse convertTo(UserEntity user, Set<MovimientoResponse> movimientos){
        return UserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .photo(user.getPhoto())
                .id(user.getId())
                .movimientos(movimientos)
                .balance(user.getBalance())
                .build();
    }

    public UserEntity requestToEntity(RegisterRequest request, Set<RoleEntity> roles) {

        UserEntity entity = new UserEntity();
        entity.setBalance(0.00);
        entity.setEmail(request.getEmail());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setRol(roles);
        entity.setPassword(encoder.encode(request.getPassword()));

        return entity;
    }
}
