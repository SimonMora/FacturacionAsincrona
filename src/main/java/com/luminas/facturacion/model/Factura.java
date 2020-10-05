package com.luminas.facturacion.model;

import java.util.HashMap;

public class Factura {
    private Cabecera cabeceraFactura;
    private HashMap<String, Double> productoPrecioUnitario;
    private float iva;
    private long cantidad;
    private double precioNeto;
    private double precioVenta;
    private double montoIva;
    private Pie pieFactura;

    public Factura() {
    }

    public Cabecera getCabeceraFactura() {
        return cabeceraFactura;
    }

    public void setCabeceraFactura(Cabecera cabeceraFactura) {
        this.cabeceraFactura = cabeceraFactura;
    }

    public HashMap<String, Double> getProductoPrecioUnitario() {
        return productoPrecioUnitario;
    }

    public void setProductoPrecioUnitario(HashMap<String, Double> productoPrecioUnitario) {
        this.productoPrecioUnitario = productoPrecioUnitario;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioNeto() {
        return precioNeto;
    }

    public void setPrecioNeto(double precioNeto) {
        this.precioNeto = precioNeto;
    }

    public double getMontoIva() {
        return montoIva;
    }

    public void setMontoIva(double montoIva) {
        this.montoIva = montoIva;
    }

    public Pie getPieFactura() {
        return pieFactura;
    }

    public void setPieFactura(Pie pieFactura) {
        this.pieFactura = pieFactura;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "cabeceraFactura=" + cabeceraFactura +
                ", productoPrecioUnitario=" + productoPrecioUnitario +
                ", iva=" + iva +
                ", cantidad=" + cantidad +
                ", precioNeto=" + precioNeto +
                ", precioVenta=" + precioVenta +
                ", montoIva=" + montoIva +
                ", pieFactura=" + pieFactura +
                '}';
    }
}
