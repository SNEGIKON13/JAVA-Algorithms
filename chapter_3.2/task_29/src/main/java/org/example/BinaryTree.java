package org.example;

class Node {

    int value;
    Node left;
    Node right;
    int count;

    Node(int value) {
        this.value = value;
        left = right = null;
        count = 1;
    }
}

public class BinaryTree {
    public static boolean isBinaryTree(Node node) {
        if (node == null) return true;
        int leftCount = countNodes(node.left);
        int rightCount = countNodes(node.right);
        int totalCount = leftCount + rightCount + 1;
        if (node.count != totalCount) return false;
        return isBinaryTree(node.left) && isBinaryTree(node.right);
    }

    private static int countNodes(Node node) {
        if (node == null) return 0;
        return countNodes(node.left) + countNodes(node.right) + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.count = 3;
        root.right.count = 3;
        root.left.left.count = 1;
        root.left.right.count = 1;
        root.right.left.count = 1;
        root.right.right.count = 1;
        root.count = 7;

        System.out.println(isBinaryTree(root));

        root.right.count = 2;
        System.out.println(isBinaryTree(root));
    }
}