package com.neoris.neoris.enums;

public enum TipoMovimiento {
    RETIRO(0), DEPOSITO(1);

    private int tipoMovimiento;

    TipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
