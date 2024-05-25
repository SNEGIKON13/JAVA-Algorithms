package org.example;

import java.util.ArrayList;
import java.util.List;

class Node {
    int key;
    Node left, right;

    Node(int key) {
        this.key = key;
        left = right = null;
    }
}

public class BST {
    Node root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    public List<Integer> keys(int low, int high) {
        List<Integer> result = new ArrayList<>();
        keysRec(root, low, high, result);
        return result;
    }

    private void keysRec(Node node, int low, int high, List<Integer> result) {
        if (node == null)
            return;

        if (low < node.key)
            keysRec(node.left, low, high, result);

        if (low <= node.key && high >= node.key)
            result.add(node.key);

        if (high > node.key)
            keysRec(node.right, low, high, result);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int key : keys) {
            bst.insert(key);
        }

        List<Integer> result = bst.keys(10, 30);
        System.out.println(result);  // Output: [10, 15, 20, 25, 30]
    }
}