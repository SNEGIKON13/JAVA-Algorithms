package org.example;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private Node cache;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int size;

        Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public Value get(Key key) {
        if (cache != null && cache.key.compareTo(key) == 0) {
            return cache.val;
        }
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else {
            cache = x;
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        if (cache != null && cache.key.compareTo(key) == 0) {
            cache.val = val;
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else {
            x.val = val;
            cache = x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
}