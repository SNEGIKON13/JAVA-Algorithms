package org.example;

/**
 * Класс для представления красно-черного двоичного дерева поиска.
 * @param <Key> тип ключа
 * @param <Value> тип значения
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;

        /**
         * Конструктор узла.
         * @param key ключ узла
         * @param value значение узла
         * @param color цвет узла
         */
        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    /**
     * Вставляет новый узел в красно-черное дерево.
     * @param key ключ нового узла
     * @param value значение нового узла
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, RED);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }

        return x;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node temp = x.right;
        x.right = temp.left;
        temp.left = x;
        temp.color = x.color;
        x.color = RED;
        return temp;
    }

    private Node rotateRight(Node x) {
        Node temp = x.left;
        x.left = temp.right;
        temp.right = x;
        temp.color = x.color;
        x.color = RED;
        return temp;
    }

    private void flipColors(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    /**
     * Проверяет, является ли данное дерево двоичным поисковым деревом (BST).
     * @return true, если дерево является BST, в противном случае false
     */
    public boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }

        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }

        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }

        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    /**
     * Проверяет, является ли данное дерево 2-3 деревом.
     * @return true, если дерево является 2-3 деревом, в противном случае false
     */
    public boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }

        if (isRed(x.right)) {
            return false;
        }

        if (isRed(x) && isRed(x.left)) {
            return false;
        }

        return is23(x.left) && is23(x.right);
    }

    /**
     * Проверяет, сбалансировано ли данное красно-черное дерево.
     * @return true, если дерево сбалансировано, в противном случае false
     */
    public boolean isBalanced() {
        int blackCount = 0;
        Node x = root;

        while (x != null) {
            if (!isRed(x)) {
                blackCount++;
            }
            x = x.left;
        }

        return isBalanced(root, blackCount);
    }

    private boolean isBalanced(Node x, int blackCount) {
        if (x == null) {
            return blackCount == 0;
        }

        if (!isRed(x)) {
            blackCount--;
        }

        return isBalanced(x.left, blackCount) && isBalanced(x.right, blackCount);
    }

    /**
     * Проверяет, является ли данное дерево красно-черным деревом.
     * @return true, если дерево является красно-черным деревом, в противном случае false
     */
    public boolean isRedBlackBST() {
        return isBST() && is23() && isBalanced();
    }

    /**
     * Основной метод для тестирования функций красно-черного дерева.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Тестирование функций красно-черного дерева
        RedBlackBST<Integer, String> bst = new RedBlackBST<>();

        bst.put(5, "Five");
        bst.put(3, "Three");
        bst.put(7, "Seven");
        bst.put(2, "Two");
        bst.put(4, "Four");
        bst.put(6, "Six");
        bst.put(8, "Eight");

        System.out.println("Is BST: " + bst.isBST());
        System.out.println("Is 2-3: " + bst.is23());

        System.out.println("Is Balanced: " + bst.isBalanced());
        System.out.println("Is Red-Black BST: " + bst.isRedBlackBST());
    }
}
