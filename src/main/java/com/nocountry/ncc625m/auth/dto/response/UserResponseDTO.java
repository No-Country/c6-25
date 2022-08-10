package com.nocountry.ncc625m.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    private String name;
    private String lastName;
    private String email;
    private String photo;

}
