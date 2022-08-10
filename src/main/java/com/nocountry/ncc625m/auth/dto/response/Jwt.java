package com.nocountry.ncc625m.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jwt {

    private String email;
    private String token;

}
