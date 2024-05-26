package org.example;

public class TrieST<Value> {
    private static final int R = 256; // размер алфавита
    private Node root = new Node();

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = root;
        for (int d = 0; d < key.length(); d++) {
            if (x == null) return null;
            char c = key.charAt(d);
            x = x.next[c];
        }
        if (x == null) return null;
        return (Value) x.val;
    }

    public void put(String key, Value val) {
        Node x = root;
        for (int d = 0; d < key.length(); d++) {
            char c = key.charAt(d);
            if (x.next[c] == null) x.next[c] = new Node();
            x = x.next[c];
        }
        x.val = val;
    }

    // Пример теста
    public static void main(String[] args) {
        TrieST<Integer> trie = new TrieST<>();
        trie.put("she", 0);
        trie.put("sells", 1);
        trie.put("sea", 2);
        trie.put("shells", 3);

        System.out.println(trie.get("she")); // 0
        System.out.println(trie.get("sells")); // 1
        System.out.println(trie.get("sea")); // 2
        System.out.println(trie.get("shells")); // 3
        System.out.println(trie.get("sh")); // null
    }
}