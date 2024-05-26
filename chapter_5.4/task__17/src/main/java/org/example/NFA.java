package org.example;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    public NFA(String regexp) {
        Stack<Integer> ops = new Stack<Integer>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 1);

        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            if (i < M - 1 && re[i + 1] == '*') {
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i + 1);
            }
        }
    }

    public boolean recognizes(String txt) {
        List<Integer> pc = new LinkedList<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) pc.add(v);
        }

        for (int i = 0; i < txt.length(); i++) {
            List<Integer> match = new LinkedList<>();
            for (int v : pc) {
                if (v < M) {
                    if (re[v] == txt.charAt(i) || re[v] == '.') {
                        match.add(v + 1);
                    }
                }
            }
            pc = new LinkedList<>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) pc.add(v);
            }
        }

        for (int v : pc) {
            if (v == M) return true;
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