package com.nocountry.ncc625m.exception;

import lombok.Data;

@Data
public class IdNotFoundException extends RuntimeException{

    private Long id;

    public IdNotFoundException(Long id) {
        super(String.format("Id '%s' no existe.", id));
        this.setId(id);
    }
}
