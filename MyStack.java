package Main;

import java.util.Arrays;

public class MyStack<E> {
    private int capacity;
    private int size;
    private E[] array;

    public MyStack() {
        this(64);
    }

    public MyStack(int capacity) {
        this.capacity = capacity;
        size = 0;
        array = (E[]) new Object[this.capacity];
    }

    public E push(E item) {
        if (size == capacity) {
            capacity *= 2;
            array = Arrays.copyOf(array, capacity);
        }
        array[size] = item;
        size++;
        return item;
    }

    public E peek() {
        return array[size - 1];
    }

    public E pop() {
        return array[--size];
    }

    public boolean empty() {
        return (size == 0);
    }
}
