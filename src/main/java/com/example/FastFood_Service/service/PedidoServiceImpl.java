package com.example.FastFood_Service.service;

import com.example.FastFood_Service.datastructures.linkedlist.SinglyLinkedList;
import com.example.FastFood_Service.datastructures.linkedlist.queue.PedidoQueue;
import com.example.FastFood_Service.datastructures.linkedlist.stack.OperationStack;
import com.example.FastFood_Service.model.EstadisticasResponse;
import com.example.FastFood_Service.model.HistorialOperacion;
import com.example.FastFood_Service.model.Pedido;
import com.example.FastFood_Service.model.PedidoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final SinglyLinkedList listaPedidos = new SinglyLinkedList();
    private final PedidoQueue colaPendientes = new PedidoQueue();
    private final OperationStack historial = new OperationStack();

    private int nextId = 1;

    @Override
    public List<Pedido> listarPedidos() {
        return listaPedidos.toList();
    }

    @Override
    public Pedido crearPedido(PedidoRequest request) {

        if (request.getNombreCliente() == null || request.getNombreCliente().isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (request.getDescripcion() == null || request.getDescripcion().isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        if (request.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }

        Pedido nuevo = new Pedido(
                nextId++,
                request.getNombreCliente(),
                request.getDescripcion(),
                request.getMonto(),
                "REGISTRADO"
        );

        listaPedidos.add(nuevo);
        colaPendientes.enqueue(nuevo);

        historial.push(new HistorialOperacion("CREAR", null, clon(nuevo)));

        return nuevo;
    }

    @Override
    public Pedido obtenerPedidoPorId(int id) {
        return listaPedidos.findById(id);
    }

    @Override
    public Pedido actualizarPedido(int id, PedidoRequest request) {
        return null;
    }

    @Override
    public boolean eliminarPedido(int id) {
        return false;
    }

    @Override
    public boolean cancelarPedido(int id) {

        Pedido pedido = listaPedidos.findById(id);
        if (pedido == null) return false;

        Pedido antes = clon(pedido);

        pedido.setEstado("CANCELADO");

        colaPendientes.removeById(id);

        Pedido despues = clon(pedido);

        historial.push(new HistorialOperacion("CANCELAR", antes, despues));

        return true;
    }

    @Override
    public Pedido despacharPedido() {

        if (colaPendientes.isEmpty()) return null;

        Pedido pedido = colaPendientes.dequeue();

        Pedido antes = clon(pedido);

        pedido.setEstado("DESPACHADO");

        Pedido despues = clon(pedido);

        historial.push(new HistorialOperacion("DESPACHAR", antes, despues));

        return pedido;
    }

    @Override
    public double calcularTotalRecursivo() {
        return calcularRecursivo(listaPedidos.getHead());
    }

    private double calcularRecursivo(SinglyLinkedList.Node nodo) {
        if (nodo == null) return 0;
        return nodo.data.getMonto() + calcularRecursivo(nodo.next);
    }

    @Override
    public EstadisticasResponse obtenerEstadisticas() {

        List<Pedido> pedidos = listaPedidos.toList();
        int registrados = 0;
        int despachados = 0;
        int cancelados = 0;
        double total = 0;

        for (Pedido p : pedidos) {
            total += p.getMonto();
            switch (p.getEstado()) {
                case "REGISTRADO" -> registrados++;
                case "DESPACHADO" -> despachados++;
                case "CANCELADO" -> cancelados++;
            }
        }

        return new EstadisticasResponse(
                pedidos.size(),
                total,
                registrados,
                despachados,
                cancelados
        );
    }

    @Override
    public Pedido rollback() {

        if (historial.isEmpty()) {
            return null;
        }

        HistorialOperacion op = historial.pop();

        switch (op.getTipoOperacion()) {

            case "CREAR" -> {
                listaPedidos.removeById(op.getPedidoDespues().getId());
                colaPendientes.removeById(op.getPedidoDespues().getId());
                return op.getPedidoAntes();
            }

            case "CANCELAR" -> {
                Pedido p = listaPedidos.findById(op.getPedidoAntes().getId());
                if (p != null) {
                    p.setEstado(op.getPedidoAntes().getEstado());
                    if (p.getEstado().equals("REGISTRADO") || p.getEstado().equals("EN_PREPARACION")) {
                        colaPendientes.enqueue(p);
                    }
                }
                return p;
            }

            case "DESPACHAR" -> {
                Pedido p = listaPedidos.findById(op.getPedidoAntes().getId());
                if (p != null) {
                    p.setEstado(op.getPedidoAntes().getEstado());
                    colaPendientes.enqueue(p);
                }
                return p;
            }
        }

        return null;
    }

    private Pedido clon(Pedido p) {
        return new Pedido(
                p.getId(),
                p.getNombreCliente(),
                p.getDescripcion(),
                p.getMonto(),
                p.getEstado()
        );
    }
}
