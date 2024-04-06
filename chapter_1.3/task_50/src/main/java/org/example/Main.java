package org.example;

import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> Deque = new LinkedList<>();
        Deque.push(1);
        Deque.push(2);
        Deque.push(3);
        ConcurrentDequeIterator<Integer> iterator = new ConcurrentDequeIterator<Integer>(Deque);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            Deque.pop();
        }
    }
}