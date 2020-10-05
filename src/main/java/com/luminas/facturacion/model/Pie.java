package com.luminas.facturacion.model;

public class Pie {
    private double total;
    private double totalIva;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }

    @Override
    public String toString() {
        return "Pie{" +
                "total=" + total +
                ", totalIva=" + totalIva +
                '}';
    }
}
