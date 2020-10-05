package com.luminas.facturacion.model;

public class Factura {
    private Cabecera cabeceraFactura;
    private Producto producto;
    private double precioUnitario;
    private float iva;
    private long cantidad;
    private double precioVenta;
    private double precioNeto;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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
                ", producto=" + producto +
                ", precioUnitario=" + precioUnitario +
                ", iva=" + iva +
                ", cantidad=" + cantidad +
                ", precioVenta=" + precioVenta +
                ", precioNeto=" + precioNeto +
                ", montoIva=" + montoIva +
                ", pieFactura=" + pieFactura +
                '}';
    }
}
