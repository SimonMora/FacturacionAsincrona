package com.luminas.facturacion.model;

public class NotaCredito {
    private Cabecera cabeceraNotaCredito;
    private Pie pieNotaCredito;

    public NotaCredito() {
    }

    public Cabecera getCabeceraNotaCredito() {
        return cabeceraNotaCredito;
    }

    public void setCabeceraNotaCredito(Cabecera cabeceraNotaCredito) {
        this.cabeceraNotaCredito = cabeceraNotaCredito;
    }

    public Pie getPieNotaCredito() {
        return pieNotaCredito;
    }

    public void setPieNotaCredito(Pie pieNotaCredito) {
        this.pieNotaCredito = pieNotaCredito;
    }

    @Override
    public String toString() {
        return "NotaCredito{" +
                "cabeceraNotaCredito=" + cabeceraNotaCredito +
                ", pieNotaCredito=" + pieNotaCredito +
                '}';
    }
}
