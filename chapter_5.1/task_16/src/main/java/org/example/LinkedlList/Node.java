package org.example.LinkedlList;

public class Node {
    String key;
    Node next;

    public Node getNext(){
        return next;
    }

    Node(String key) {
        this.key = key;
        this.next = null;
    }
}
