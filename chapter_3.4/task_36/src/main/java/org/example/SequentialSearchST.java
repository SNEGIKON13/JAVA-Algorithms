package org.example;

public class SequentialSearchST<K, V> {
    private Node first;
    int size = 0;
    private class Node{
        K key;
        V val;
        Node next;
        public Node(K key, V val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public V get(K key){
        for(Node node = first; node != null; node = node.next){
            if(key.equals(node.key)){
                return node.val;
            }
        }
        return null;
    }

    public void put(K key, V val){
        for(Node node = first; node != null; node = node.next){
            if(key.equals(node.key)){
                node.val = val;
                return;
            }
        }
        ++size;
        first = new Node(key, val, first);
    }

    public int size(){
        return size;
    }
}
