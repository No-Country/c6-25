package com.nocountry.ncc625m.auth.service.mapper;

import com.nocountry.ncc625m.auth.dto.UserDTO;
import com.nocountry.ncc625m.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserAuthMapper {

    public UserDTO userEntityToDTO(UserEntity user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .photo(user.getPhoto())
                .build();
    }

    public UserEntity userDTOtoEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .lastname(userDTO.getLastname())
                .photo(userDTO.getPhoto())
                .build();
    }


}
