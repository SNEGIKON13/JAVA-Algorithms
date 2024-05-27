package org.example;

import java.util.HashMap;
import java.util.Map;

public class HybridTST<Value> {
    private static final int R = 256;
    private Node root;

    private class Node {
        char c;
        Node left;
        Node mid;
        Node right;
        Value val;
        Map<Character, Node> nextLevel;

        Node() {
            nextLevel = new HashMap<>();
        }
    }

    public Value get(String key) {
        if (key == null || key.length() == 0) return null;
        Node x = root;
        int d = 0;
        while (d < 2 && x != null) {
            char c = key.charAt(d);
            x = x.nextLevel.get(c);
            d++;
        }
        while (x != null && d < key.length()) {
            char c = key.charAt(d);
            if (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else if (d < key.length() - 1) {
                x = x.mid;
                d++;
            } else return x.val;
        }
        return null;
    }

    public void put(String key, Value val) {
        if (key == null || key.length() == 0) return;
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (d < 2) {
            if (!x.nextLevel.containsKey(c)) {
                x.nextLevel.put(c, new Node());
            }
            x.nextLevel.put(c, put(x.nextLevel.get(c), key, val, d + 1));
        } else {
            if (c < x.c) x.left = put(x.left, key, val, d);
            else if (c > x.c) x.right = put(x.right, key, val, d);
            else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
            else x.val = val;
        }
        return x;
    }

    // Пример теста
    public static void main(String[] args) {
        HybridTST<Integer> tst = new HybridTST<>();
        tst.put("she", 0);
        tst.put("sells", 1);
        tst.put("sea", 2);
        tst.put("shells", 3);

        System.out.println(tst.get("she")); // 0
        System.out.println(tst.get("sells")); // 1
        System.out.println(tst.get("sea")); // 2
        System.out.println(tst.get("shells")); // 3
        System.out.println(tst.get("sh")); // null
    }
}