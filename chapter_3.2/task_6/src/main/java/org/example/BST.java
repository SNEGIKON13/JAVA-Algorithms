package org.example;

public class BST {
    private class Node {
        private int value;
        private Node left;
        private Node right;
        private int nodeSize;
        public Node(int value) {
            this.value = value;
            this.nodeSize = 1;
        }
    }
    private Node root;

    public int recursiveHeight() {
        return recursiveHeight(root);
    }
    private int recursiveHeight(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = recursiveHeight(node.left);
        int rightHeight = recursiveHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
    public int sizeBasedHeight() {
        return sizeBasedHeight(root);
    }
    private int sizeBasedHeight(Node node) {
        if (node == null) {
            return -1;
        }

        return node.nodeSize - 1;
    }
    public void insert(int value) {
        root = insertNode(root, value);
    }

    private Node insertNode(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertNode(node.left, value);
        } else if (value > node.value) {
            node.right = insertNode(node.right, value);
        }

        node.nodeSize = 1 + size(node.left) + size(node.right);

        return node;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        return node.nodeSize;
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int level) {
        if (node != null) {
            printTree(node.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(node.value);
            printTree(node.left, level + 1);
        }
    }
    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);
        bst.printTree();
        int recursiveHeight = bst.recursiveHeight();
        int sizeBasedHeight = bst.sizeBasedHeight();

        System.out.println("Recursive Height: " + recursiveHeight);
        System.out.println("Size-based Height: " + sizeBasedHeight);
    }
}
