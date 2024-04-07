package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Deque;

public class ConcurrentDequeIterator<T> implements Iterator<T> {
    private final Deque<T> deque;
    private final int counter;
    public ConcurrentDequeIterator(Deque<T> deque) {
    this.deque = deque;
    this.counter = this.deque.size();
    }

    @Override
    public boolean hasNext() {

        return deque.iterator().hasNext();
    }

    @Override
    public T next() {
        checkModification();
        return deque.iterator().next();
    }

    private void checkModification() {
        if (counter != deque.size()) {
            throw new ConcurrentModificationException();
        }
    }
}