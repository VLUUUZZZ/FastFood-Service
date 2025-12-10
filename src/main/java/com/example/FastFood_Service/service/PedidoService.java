package com.example.FastFood_Service.service;

import com.example.FastFood_Service.model.*;

import java.util.List;

public interface PedidoService {

    List<Pedido> listarPedidos();
    Pedido crearPedido(PedidoRequest request);
    Pedido obtenerPedidoPorId(int id);

    Pedido actualizarPedido(int id, PedidoRequest request);

    boolean eliminarPedido(int id);

    boolean cancelarPedido(int id);
    Pedido despacharPedido();
    double calcularTotalRecursivo();
    EstadisticasResponse obtenerEstadisticas();
    Pedido rollback();
}
