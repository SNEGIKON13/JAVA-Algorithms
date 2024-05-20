package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для представления красно-черного двоичного дерева поиска.
 * @param <Key> тип ключа
 * @param <Value> тип значения
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * Внутренний класс для представления узла красно-черного дерева.
     */
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;
        private int size;

        /**
         * Конструктор узла.
         * @param key ключ узла
         * @param value значение узла
         * @param color цвет узла
         * @param size размер поддерева с корнем в данном узле
         */
        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
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

    private Node put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value, RED, 1);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = 1 + size(node.left) + size(node.right);
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = 1 + size(node.left) + size(node.right);
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.color == RED;
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return node.size;
    }

    /**
     * Вычисляет процент красных узлов в дереве.
     * @return процент красных узлов
     */
    public double calculateRedNodePercentage() {
        int totalNodes = size(root);
        int redNodes = countRedNodes(root);
        return (double) redNodes / totalNodes * 100;
    }

    private int countRedNodes(Node node) {
        if (node == null)
            return 0;
        int count = countRedNodes(node.left) + countRedNodes(node.right);
        if (node.color == RED)
            count++;
        return count;
    }

    /**
     * Возвращает размер дерева.
     * @return размер дерева
     */
    public int size() {
        return size(root);
    }

    /**
     * Отображает красно-черное дерево графически.
     */
    public void draw() {
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.key.toString();
                    line.add(aa);
                    if (aa.length() > widest)
                        widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null)
                        nn++;
                    if (n.right != null)
                        nn++;
                }
            }

            if (widest % 2 == 1)
                widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // Рисуем линии ветвей
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null)
                            c = (line.get(j) != null) ? '┴' : '┘';
                        else
                            c = (line.get(j) != null) ? '└' : ' ';
                    }
                    System.out.print(c);

                    // Рисуем пробелы
                    if (line.get(j) == null)
                        for (int k = 0; k < perpiece - 1; k++)
                            System.out.print(" ");
                    else {
                        for (int k = 0; k < hpw; k++)
                            System.out.print(j % 2 == 0 ? " " : "─");

                        // Рисуем узел
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++)
                            System.out.print(j % 2 == 0 ? "─" : " ");

                        System.out.print(line.get(j));

                        for (int k = 0; k < hpw; k++)
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        System.out.print(j % 2 == 0 ? "┐" : "┌");

                        for (int k = 0; k < hpw; k++)
                            System.out.print(j % 2 == 0 ? " " : "─");
                    }
                }
                System.out.println();
            }

            // Рисуем последнюю строку (только ключи)
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null)
                    f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // Рисуем пробелы
                for (int k = 0; k < gap1; k++)
                    System.out.print(" ");

                // Рисуем ключ
                System.out.print(f);

                // Рисуем пробелы
                for (int k = 0; k < gap2; k++)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Основной метод для тестирования функций красно-черного дерева.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Тестирование функций красно-черного дерева
        RedBlackBST<Integer, String> bst = new RedBlackBST<>();

        bst.put(5, "E");
        bst.put(3, "C");
        bst.put(8, "H");
        bst.put(2, "B");
        bst.put(4, "D");
        bst.put(7, "G");
        bst.put(9, "I");

        // Добавляем красные узлы
        bst.root.left.color = RedBlackBST.RED;
        bst.root.right.color = RedBlackBST.RED;

        bst.draw();

        double redNodePercentage = bst.calculateRedNodePercentage();
        System.out.println("Percentage of red nodes: " + redNodePercentage + "%");
    }
}
