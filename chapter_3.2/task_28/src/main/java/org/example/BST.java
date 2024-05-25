package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private Node lastAccessedNode;

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

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (lastAccessedNode != null && lastAccessedNode.key.compareTo(key) == 0) {
            return lastAccessedNode.val;
        }
        Value val = get(root, key);
        if (val != null) {
            lastAccessedNode = getNode(root, key);
        }
        return val;
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    private Node getNode(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return getNode(x.left, key);
        else if (cmp > 0) return getNode(x.right, key);
        else return x;
    }

    public void put(Key key, Value val) {
        if (lastAccessedNode != null && lastAccessedNode.key.compareTo(key) == 0) {
            lastAccessedNode.val = val;
            return;
        }
        root = put(root, key, val);
        lastAccessedNode = getNode(root, key);
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

    public void delete(Key key) {
        if (!contains(key)) throw new NoSuchElementException("Key not found");
        root = delete(root, key);
        lastAccessedNode = null; // Invalidate cache
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void printBST() {
        printBST(root, "", true);
    }

    private void printBST(Node node, String prefix, boolean isTail) {
        if (node == null) return;
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.key);
        printBST(node.left, prefix + (isTail ? "    " : "│   "), false);
        printBST(node.right, prefix + (isTail ? "    " : "│   "), true);
    }

    public void insertKeysToBalancedBST(List<Key> keys) {
        Collections.sort(keys);
        root = insertKeysToBalancedBST(keys, 0, keys.size() - 1);
    }

    private Node insertKeysToBalancedBST(List<Key> keys, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(keys.get(mid), null, end - start + 1);
        node.left = insertKeysToBalancedBST(keys, start, mid - 1);
        node.right = insertKeysToBalancedBST(keys, mid + 1, end);
        return node;
    }
}