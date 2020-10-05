package com.luminas.facturacion.model;

public enum IvaValues {
    A (10.05f),

    B ( 21f),

    X (70f);

    public final float value;

    private IvaValues(float value) {
        this.value = value;
    }

}
