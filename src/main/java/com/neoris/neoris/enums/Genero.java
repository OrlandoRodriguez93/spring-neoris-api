package com.neoris.neoris.enums;

public enum Genero {
    FEMENINO(0), MASCULINO(1);

    private int genero;

    Genero(int genero) {
        this.genero = genero;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}
