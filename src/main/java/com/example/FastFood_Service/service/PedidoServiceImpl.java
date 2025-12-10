package com.example.FastFood_Service.service;

import com.example.FastFood_Service.model.Pedido;
import com.example.FastFood_Service.model.PedidoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final List<Pedido> pedidos = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    @Override
    public Pedido crearPedido(PedidoRequest request) {
        Pedido pedido = new Pedido(
                nextId++,
                request.getNombreCliente(),
                request.getDescripcion(),
                request.getMonto(),
                "PENDIENTE"
        );
        pedidos.add(pedido);
        return pedido;
    }

    @Override
    public Pedido obtenerPedidoPorId(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Pedido actualizarPedido(int id, PedidoRequest request) {
        Pedido pedido = obtenerPedidoPorId(id);
        if (pedido != null) {
            pedido.setNombreCliente(request.getNombreCliente());
            pedido.setDescripcion(request.getDescripcion());
            pedido.setMonto(request.getMonto());
        }
        return pedido;
    }

    @Override
    public boolean eliminarPedido(int id) {
        return pedidos.removeIf(p -> p.getId() == id);
    }
}
