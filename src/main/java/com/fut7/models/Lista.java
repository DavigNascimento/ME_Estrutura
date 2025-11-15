package com.fut7.models;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> {

    private No<T> head;
    private No<T> tail;
    private int size;

    public Lista() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T value) {
        No<T> novo = new No<>(value);
        if (head == null) {
            head = novo;
            tail = novo;
        } else {
            tail.setNext(novo);
            novo.setPrev(tail);
            tail = novo;
        }
        size++;
    }

    public void remove(T value) {
        No<T> current = head;
        while (current != null) {
            if (current.getValue().equals(value)) {
                // nao head
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                // nao tail
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }
                size--;
                return;
            }
            current = current.getNext();
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void print() {
        No<T> current = head;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private No<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }

    public No<T> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public No<T> getTail() {
        return tail;
    }

    public void setHead(No<T> head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTail(No<T> tail) {
        this.tail = tail;
    }
}
