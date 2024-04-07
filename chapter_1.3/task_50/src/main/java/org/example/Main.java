package org.example;

import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);
        deque.push(2);
        deque.push(3);
        ConcurrentDequeIterator<Integer> iterator = new ConcurrentDequeIterator<Integer>(deque);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            deque.pop();
        }
    }
}