package com.luminas.facturacion.model;

public class Cliente {
    private long id;
    private String direccion;
    private String condicionImpositiva;
    private String tipoDocumento;
    private int numeroDocumento;

    public Cliente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCondicionImpositiva() {
        return condicionImpositiva;
    }

    public void setCondicionImpositiva(String condicionImpositiva) {
        this.condicionImpositiva = condicionImpositiva;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", condicionImpositiva=" + condicionImpositiva +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento=" + numeroDocumento +
                '}';
    }
}
