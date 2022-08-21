package com.nocountry.ncc625m.exception;

import lombok.Data;

@Data
public class MaxExtractionPerDayException extends RuntimeException{

    public MaxExtractionPerDayException() {
        super("Límite máximo de débito diario superado. Por favor, ingrese un monto menor.");
    }
}
