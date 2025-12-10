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
}
