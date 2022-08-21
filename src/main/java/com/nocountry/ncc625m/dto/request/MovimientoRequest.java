package com.nocountry.ncc625m.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MovimientoRequest {

    @NotNull(message = "amount can't be null")
    private Double amount;

    @NotBlank(message = "'from' can't be null")
    private String from;

    @NotBlank(message = "'to' can't be null")
    private String to;
}
