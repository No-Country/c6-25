package com.nocountry.ncc625m.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String photo;
    private Double balance;
    private Set<MovimientoResponse> movimientos;
}
