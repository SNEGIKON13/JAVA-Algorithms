package org.example;

public class IdealBalancedBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int size;

        Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, 1);
        } else {
            root = put(root, key, val);
        }
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void buildIdealBST(Key[] keys, Value[] vals) {
        root = buildIdealBST(keys, vals, 0, keys.length - 1);
    }

    private Node buildIdealBST(Key[] keys, Value[] vals, int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        Node node = new Node(keys[mid], vals[mid], 1);
        node.left = buildIdealBST(keys, vals, lo, mid - 1);
        node.right = buildIdealBST(keys, vals, mid + 1, hi);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public static void main(String[] args) {
        IdealBalancedBST<Integer, String> bst = new IdealBalancedBST<>();
        Integer[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] vals = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        bst.buildIdealBST(keys, vals);

        for (int i = 1; i <= 10; i++) {
            System.out.println("Key: " + i + ", Value: " + bst.get(i));
        }
    }
}