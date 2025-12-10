package com.example.FastFood_Service.model;

public class HistorialOperacion {

    private String tipoOperacion;
    private Pedido pedidoAntes;
    private Pedido pedidoDespues;

    public HistorialOperacion(String tipoOperacion, Pedido antes, Pedido despues) {
        this.tipoOperacion = tipoOperacion;
        this.pedidoAntes = antes;
        this.pedidoDespues = despues;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public Pedido getPedidoAntes() {
        return pedidoAntes;
    }

    public Pedido getPedidoDespues() {
        return pedidoDespues;
    }
}
