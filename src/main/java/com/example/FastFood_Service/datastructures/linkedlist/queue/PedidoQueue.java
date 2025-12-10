package com.example.FastFood_Service.datastructures.linkedlist.queue;

import com.example.FastFood_Service.model.Pedido;

import java.util.LinkedList;

public class PedidoQueue {

    private final LinkedList<Pedido> queue = new LinkedList<>();

    public void enqueue(Pedido p) {
        queue.addLast(p);
    }

    public Pedido dequeue() {
        return queue.isEmpty() ? null : queue.removeFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean removeById(int id) {
        return queue.removeIf(p -> p.getId() == id);
    }
}
