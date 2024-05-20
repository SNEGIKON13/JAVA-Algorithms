package org.example;

import java.util.Optional;

public class RedBlackBST<K extends Comparable<K>, V> {
    private Node root;
    Node lastAccessedNode = null;    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int size;
        private boolean color;
        public Node(K key, V val, int size, boolean color){
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;
        }
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root).get();
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Optional<Node> deleteMin(Node node) {
        Optional<Node> optNode = Optional.ofNullable(node.left);
        if (optNode.isEmpty()){
            return optNode;
        }
        if (!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left).get();
        return Optional.ofNullable(balance(node));
    }

    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
             node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }     
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    public boolean isEmpty(){
        return isEmpty(root);
    }

    private boolean isEmpty(Node node){
        return node == null;
    }

    private boolean isRed(Node node){
        Optional<Node> optNode = Optional.ofNullable(node);
        if(optNode.isPresent()){
            return node.color == RED;
        }
        else{
            return false;
        }
    }

    private Node rotateLeft(Node oldRoot){
        Node newRoot = oldRoot.right;
        oldRoot.right = newRoot.left;
        newRoot.left = oldRoot;
        newRoot.color = oldRoot.color;
        oldRoot.color = RED;
        newRoot.size = oldRoot.size;
        oldRoot.size = 1 + size(oldRoot.left) + size(oldRoot.right);
        return newRoot;
    } 

    private Node rotateRight(Node oldRoot){
        Node newRoot = oldRoot.left;
        oldRoot.left = newRoot.right;
        newRoot.right = oldRoot;
        newRoot.color = oldRoot.color;
        oldRoot.color = RED;
        newRoot.size = oldRoot.size;
        oldRoot.size = 1 + size(oldRoot.left) + size(oldRoot.right);
        return newRoot;
    }

    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        Optional<Node> optNode = Optional.ofNullable(node);
        if(optNode.isPresent()){
            return node.size;
        }
        else{
            return 0;
        }
    }

    public V get(K key){
        Optional<Node> optNode = Optional.ofNullable(lastAccessedNode);
        if(optNode.isPresent()){
            if(optNode.get().key == key){
                return optNode.get().val;
            }
        }
        else{
            optNode = get(root, key);
            if(optNode.isPresent()){
                return optNode.get().val;
            }
        }
        return null;
    }

    private Optional<Node> get(Node node, K key){
        Optional<Node> optNode = Optional.ofNullable(node);
        if(optNode.isEmpty()){
            return optNode;
        }
        while(key != node.key){
            if(key.compareTo(node.key) > 0){
                node = node.right;
            }
            else if(key.compareTo(node.key) < 0){
                node = node.left;
            }
            optNode = Optional.ofNullable(node);
            if(optNode.isEmpty()){
                return optNode;
            }
        }
        lastAccessedNode = node;
        return optNode;
    }

    public void put(K key, V val){
        Optional<Node> optNode = Optional.ofNullable(lastAccessedNode);
        if(optNode.isPresent() && optNode.get().key == key){
                lastAccessedNode.val = val;
        }
        else{
            root = put(root, key, val);
            root.color = BLACK;
        }
    }

    private Node put(Node node, K key, V val){
        Optional<Node> optNode = Optional.ofNullable(node);
        if(optNode.isEmpty()){ 
            lastAccessedNode = new Node(key, val, 1, RED); 
            return lastAccessedNode;
        }
        int compare = key.compareTo(node.key);
        if(compare < 0){
            node.left = put(node.left, key, val);
        }
        else if(compare > 0){
            node.right = put(node.right, key, val);
        }
        else{
            node.val = val;
        }

        if(isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public K min(){
        if(Optional.ofNullable(root).isEmpty()){
            return null;
        }
        return min(root).get().key;
    }

    private Optional<Node> min(Node node){
        Optional<Node> optNode = Optional.ofNullable(node);
        if(optNode.isEmpty()){
            return optNode;
        }
        while(node.left != null){
            node = node.left;
        }
        lastAccessedNode = node;
        optNode = Optional.of(node);
        return optNode;
    }

    public K max(){
        if(Optional.ofNullable(root).isEmpty()){
            return null;
        }
        return max(root).get().key;
    }

    private Optional<Node> max(Node node){
        Optional<Node> optNode = Optional.ofNullable(node);
        if(optNode.isEmpty()){
            return optNode;
        }
        while(node.right != null){
            node = node.right;
        }
        lastAccessedNode = node;
        optNode = Optional.of(node);
        return optNode;
    }
}