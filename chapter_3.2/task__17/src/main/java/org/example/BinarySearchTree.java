package org.example;

import java.util.Deque;
import java.util.LinkedList;

public class BinarySearchTree {
    BinarySearchTree()
    {

    }
    class Node {
        Node left = null;
        Node right = null;
        int key;
        public Node(int key) {
            this.key = key;
        }
    }
    Node insert(Node node, int key) {
        if (node == null)
        {
            return new Node(key);
        }
        else if (key == node.key)
        {
            System.out.println("Узел с ключом " + key + " уже существует");
            return node;
        }
        else if (key < node.key) {
            node.left = insert(node.left, key);
        }
        else {
            node.right = insert(node.right, key);
        }
        return node;
    }
    public static void printTree(Node node) {
        System.out.println("\nБинарное дерево поиска");
        Deque<Node> globalStack = new LinkedList<>();
        globalStack.push(node);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (!isRowEmpty) { Deque<Node> localStack = new LinkedList<>();
            isRowEmpty = true;
            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (!globalStack.isEmpty())
            {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.key);
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    isRowEmpty = false;
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
    Node getParent() {
        return parent;
    }
    void setParent(Node node) {
        parent = node;
    }
    private Node parent;
}
