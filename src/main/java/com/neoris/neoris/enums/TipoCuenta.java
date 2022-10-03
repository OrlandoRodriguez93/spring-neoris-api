package com.neoris.neoris.enums;

public enum TipoCuenta {
    AHORRO(0), CORRIENTE(1);

    private int tipo;

    TipoCuenta(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
