package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Deque;

public class ConcurrentDequeIterator<T> implements Iterator<T> {
    private final Deque<T> Deque;
    private final int counter;
    public ConcurrentDequeIterator(Deque<T> Deque) {
    this.Deque= Deque;
    this.counter = Deque.size();
    }

    @Override
    public boolean hasNext() {

        return Deque.iterator().hasNext();
    }

    @Override
    public T next() {
        checkModification();
        return Deque.iterator().next();
    }

    private void checkModification() {
        if (counter != Deque.size()) {
            throw new ConcurrentModificationException();
        }
    }
}