package com.example.FastFood_Service.datastructures.linkedlist;

import com.example.FastFood_Service.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList {

    public static class Node {
        public Pedido data;
        public Node next;

        public Node(Pedido data) {
            this.data = data;
        }
    }

    private Node head;

    public Node getHead() {
        return head;
    }

    public void add(Pedido data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            return;
        }
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = node;
    }

    public Pedido findById(int id) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.getId() == id) return temp.data;
            temp = temp.next;
        }
        return null;
    }

    public boolean removeById(int id) {
        if (head == null) return false;
        if (head.data.getId() == id) {
            head = head.next;
            return true;
        }
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data.getId() == id) {
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public List<Pedido> toList() {
        List<Pedido> list = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }
        return list;
    }
}
