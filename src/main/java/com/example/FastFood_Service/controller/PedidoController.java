package com.example.FastFood_Service.controller;

import com.example.FastFood_Service.model.*;
import com.example.FastFood_Service.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listarPedidos();
    }

    @PostMapping
    public Pedido crear(@RequestBody PedidoRequest request) {
        return service.crearPedido(request);
    }

    @GetMapping("/{id}")
    public Pedido obtener(@PathVariable int id) {
        return service.obtenerPedidoPorId(id);
    }

    @PutMapping("/cancelar/{id}")
    public boolean cancelar(@PathVariable int id) {
        return service.cancelarPedido(id);
    }

    @PutMapping("/despachar")
    public Pedido despachar() {
        return service.despacharPedido();
    }

    @GetMapping("/estadisticas")
    public EstadisticasResponse estadisticas() {
        return service.obtenerEstadisticas();
    }

    @GetMapping("/total")
    public double total() {
        return service.calcularTotalRecursivo();
    }

    @PutMapping("/rollback")
    public Pedido rollback() {
        return service.rollback();
    }
}
