package com.luminas.facturacion.model;

import java.time.LocalDate;

public class Cabecera {
    private LocalDate fecha;
    private long numero;
    private long talon;
    private String letra;
    private Cliente cliente;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public long getTalon() {
        return talon;
    }

    public void setTalon(long talon) {
        this.talon = talon;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Cabecera{" +
                "fecha=" + fecha +
                ", numero=" + numero +
                ", talon=" + talon +
                ", letra='" + letra + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
