package com.neoris.neoris.enums;

public enum ValorMovimiento {
    CREDITO(0), DEBITO(1);

    private int valor;

    ValorMovimiento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
