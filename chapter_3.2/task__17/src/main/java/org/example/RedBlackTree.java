package org.example;

import java.util.Deque;
import java.util.LinkedList;

public class RedBlackTree {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int value;
        Node left;
        Node right;
        boolean color;

        Node(int val, boolean color) {
            this.value = val;
            this.color = color;
        }
    }
    public Node getRoot() {
        return root;
    }
    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(int val) {
        root = put(root, val);
        root.color = BLACK;
    }

    private Node put(Node h, int val) {
        if (h == null) {
            return new Node(val, RED);
        }
        int cmp = val;
        if (cmp < 0) {
            h.left = put(h.left, val);
        } else if (cmp > 0) {
            h.right = put(h.right, val);
        } else {
            h.value = val;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }
    Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    public static void printTree(Node node) {
        System.out.println("Красно-черное бинарное дерево поиска");
        Deque<Node> globalStack = new LinkedList<>();
        globalStack.push(node);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (!isRowEmpty) {
            Deque<Node> localStack = new LinkedList<>();
            isRowEmpty = true;
            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    if (temp.value == -1) {
                        System.out.print("__");
                    } else {
                        System.out.print(temp.value);
                    }
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    isRowEmpty = !(temp.left != null || temp.right != null);
                }
                else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }
}
