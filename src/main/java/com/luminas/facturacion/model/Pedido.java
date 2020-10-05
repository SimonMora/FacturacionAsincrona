package com.luminas.facturacion.model;

public class Pedido {
    private long numeroPedido;
    private Cliente cliente;
    private Producto[] detalles;

    public Pedido() {
    }

    public long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto[] getDetalles() {
        return detalles;
    }

    public void setDetalles(Producto[] detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente=" + cliente +
                ", detalles=" + detalles +
                '}';
    }
}
