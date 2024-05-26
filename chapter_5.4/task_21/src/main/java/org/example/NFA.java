package org.example;

import java.util.*;

public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    public NFA(String regexp) {
        Deque<Integer> ops = new LinkedList<>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 2); // Увеличиваем размер графа на 2 для учета начального и конечного состояний

        int lp = 0;
        if (re[0] == '^') {
            G.addEdge(0, 1); // Начальное состояние
            lp = 1;
        }

        for (int i = lp; i < M; i++) {
            int current = i + 1;
            if (re[i] == '(' || re[i] == '|') {
                ops.push(current);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or - 1] == '|') {
                    int leftParen = ops.pop();
                    G.addEdge(leftParen - 1, or);
                    G.addEdge(or, current);
                } else {
                    G.addEdge(or - 1, current);
                }
            }

            if (i < M - 1 && re[i + 1] == '*') {
                G.addEdge(current - 1, current);
                G.addEdge(current, current - 1);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(current - 1, current);
            }
        }

        G.addEdge(M + 1, M + 2); // Конечное состояние
    }

    public boolean recognizes(String txt) {
        List<Integer> pc = new LinkedList<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }

        for (int i = 0; i < txt.length(); i++) {
            List<Integer> match = new LinkedList<>();
            for (int v : pc) {
                if (v < M && (re[v] == txt.charAt(i) || re[v] == '.')) {
                    match.add(v + 1);
                }
            }
            pc.clear();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }

        for (int v : pc) {
            if (v == M) {
                return true;
            }
        }
        return false;
    }

    private class Digraph {
        private final int V;
        private List<Integer>[] adj;

        public Digraph(int V) {
            this.V = V;
            List<Integer>[] adj = new List[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }

        public int V() {
            return V;
        }
    }

    private class DirectedDFS {
        private boolean[] marked;

        public DirectedDFS(Digraph G, int s) {
            marked = new boolean[G.V()];
            dfs(G, s);
        }

        public DirectedDFS(Digraph G, Iterable<Integer> sources) {
            marked = new boolean[G.V()];
            for (int s : sources) {
                if (!marked[s]) dfs(G, s);
            }
        }

        private void dfs(Digraph G, int v) {
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) dfs(G, w);
            }
        }

        public boolean marked(int v) {
            return marked[v];
        }
    }
}