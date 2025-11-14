package com.fut7.models;

public class Fila<T> {

    private No<T> tail;
    private int size;

    public Fila() {
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(T value) {
        No<T> novo = new No<>(value);
        if (tail == null) {
            tail = novo;
            tail.setNext(tail);
        } else {
            novo.setNext(tail.getNext());
            tail.setNext(novo);
            tail = novo;
        }
        size++;
    }

    public T dequeue() {
        if (tail == null) {
            return null;
        }
        No<T> head = tail.getNext();
        if (head == tail) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getValue();
    }

    public void print() {
        if (tail == null) {
            System.out.println("Fila vazia");
            return;
        }
        No<T> current = tail.getNext();
        do {
            System.out.println(current.getValue());
            current = current.getNext();
        } while (current != tail.getNext());
    }

    public No<T> getTail() {
        return tail;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setTail(No<T> tail) {
        this.tail = tail;
    }
}
