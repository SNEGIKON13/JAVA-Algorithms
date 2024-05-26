package org.example;

import java.util.*;

public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    public NFA(String regexp) {
        Deque<Integer> ops = new LinkedList<Integer>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 1);

        for (int i = 0; i < M; i++) {
            int lp = i;
            char currentChar = re[i];
            boolean isNextStar = i < M - 1 && re[i + 1] == '*';
            boolean isNextPlus = i < M - 1 && re[i + 1] == '+';

            if (currentChar == '(' || currentChar == '|') {
                ops.push(i);
            } else if (currentChar == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            if (isNextStar) {
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }

            if (isNextPlus) {
                G.addEdge(i + 1, lp); // Замыкание "+"
            }

            if (currentChar == '(' || currentChar == '*' || currentChar == '+' || currentChar == ')') {
                G.addEdge(i, i + 1);
            }
        }
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

            pc = new LinkedList<>();
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
            adj = (List<Integer>[]) new List[V];
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