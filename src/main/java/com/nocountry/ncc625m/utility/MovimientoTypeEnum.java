package com.nocountry.ncc625m.utility;

public enum MovimientoTypeEnum {

    DEBITO, DEPOSITO, PAGO_EXTERNO;

    public String get(){
        return this.name();
    }
}
