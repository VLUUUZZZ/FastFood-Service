package com.example.FastFood_Service.datastructures.linkedlist.stack;

import com.example.FastFood_Service.model.HistorialOperacion;

import java.util.Stack;

public class OperationStack {

    private final Stack<HistorialOperacion> stack = new Stack<>();

    public void push(HistorialOperacion op) {
        stack.push(op);
    }

    public HistorialOperacion pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
