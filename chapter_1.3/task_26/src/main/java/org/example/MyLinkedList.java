package org.example;

import java.util.Iterator;
import java.util.LinkedList;

public class MyLinkedList<T> extends LinkedList<T> {
    public void removeKey(T key) {
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (key.equals(iterator.next())) {
                iterator.remove();
            }
        }
    }
}