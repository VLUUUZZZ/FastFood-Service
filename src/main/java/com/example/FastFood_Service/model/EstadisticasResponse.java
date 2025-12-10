package com.example.FastFood_Service.model;

public class EstadisticasResponse {

    private int totalPedidos;
    private double montoTotal;
    private int registrados;
    private int despachados;
    private int cancelados;

    public EstadisticasResponse(
            int totalPedidos,
            double montoTotal,
            int registrados,
            int despachados,
            int cancelados
    ) {
        this.totalPedidos = totalPedidos;
        this.montoTotal = montoTotal;
        this.registrados = registrados;
        this.despachados = despachados;
        this.cancelados = cancelados;
    }

    public int getTotalPedidos() {
        return totalPedidos;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public int getRegistrados() {
        return registrados;
    }

    public int getDespachados() {
        return despachados;
    }

    public int getCancelados() {
        return cancelados;
    }
}
