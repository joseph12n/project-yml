package com.mycompany.projectyml.domain.enumeration;

public enum Estado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private final String Valor;

    Estado(String Valor) {
        this.Valor = Valor;
    }

    public String getValor() {
        return Valor;
    }
}