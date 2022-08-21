package com.nocountry.ncc625m.exception;

import lombok.Data;

@Data
public class InsufficientBalanceException extends RuntimeException{

    private Double initialBalance;
    private Double newAmount;

    public InsufficientBalanceException(Double initialBalance, Double newAmount){

        super(String.format("El monto total de $'%s' es insuficiente para debitar $'%s' ", initialBalance, newAmount));

        this.initialBalance = initialBalance;
        this.newAmount = newAmount;

    }
}
