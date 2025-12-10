package com.example.FastFood_Service.model;

public class PedidoRequest {

    private String nombreCliente;
    private String descripcion;
    private double monto;

    public PedidoRequest() {}

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }
}
