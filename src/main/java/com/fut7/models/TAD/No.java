package com.fut7.models.TAD;

public class No<T> {
    
    private T value;
    private No<T> next;
    private No<T> prev;

    public No(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public T getValue() {
        return value;
    }
    public No<T> getNext() {
        return next;
    }
    public No<T> getPrev() {
        return prev;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public void setNext(No<T> next) {
        this.next = next;
    }
    public void setPrev(No<T> prev) {
        this.prev = prev;
    }
}
