package com.nocountry.ncc625m.dto.response;

import com.nocountry.ncc625m.entity.RoleEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String photo;

    private Double balance;

    private Set<MovimientoResponse> movimientos;

    private Set<RoleEntity> rol;
}
