package com.example.FastFood_Service.model;

public class Pedido {

    private int id;
    private String nombreCliente;
    private String descripcion;
    private double monto;
    private String estado;

    public Pedido(int id, String nombreCliente, String descripcion, double monto, String estado) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
    }

    public Pedido() {}

    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
