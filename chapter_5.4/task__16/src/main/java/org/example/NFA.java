package org.example;

import java.util.HashSet;
import java.util.Set;

public class NFA {
    private Set<Integer>[] transitions;
    private Set<Integer> acceptStates;

    public NFA(int stateCount) {
        transitions = new HashSet[stateCount];
        for (int i = 0; i < stateCount; i++) {
            transitions[i] = new HashSet<>();
        }
        acceptStates = new HashSet<>();
    }

    public void addTransition(int from, int to, char symbol) {
        transitions[from].add(to);
    }

    public void addEpsilonTransition(int from, int to) {
        transitions[from].add(to);
    }

    public void addAcceptState(int state) {
        acceptStates.add(state);
    }

    public boolean matches(String input) {
        Set<Integer> currentStates = new HashSet<>();
        currentStates.add(0);

        for (char symbol : input.toCharArray()) {
            Set<Integer> nextStates = new HashSet<>();
            for (int state : currentStates) {
                if (transitions[state].contains(symbol)) {
                    nextStates.add(state);
                }
            }
            currentStates = nextStates;
        }

        for (int state : currentStates) {
            if (acceptStates.contains(state)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        NFA nfa = new NFA(18);

        // Add transitions based on the NFA diagram
        nfa.addTransition(0, 1, 'ε');
        nfa.addTransition(1, 2, 'ε');
        nfa.addTransition(2, 3, 'A');
        nfa.addTransition(3, 4, 'B');
        nfa.addTransition(4, 5, 'ε');
        nfa.addTransition(5, 6, 'ε');
        nfa.addTransition(6, 7, 'C');
        nfa.addTransition(6, 10, 'D');
        nfa.addTransition(6, 13, 'E');
        nfa.addTransition(7, 8, 'ε');
        nfa.addTransition(8, 9, 'ε');
        nfa.addTransition(10, 11, 'ε');
        nfa.addTransition(11, 12, 'ε');
        nfa.addTransition(13, 14, 'ε');
        nfa.addTransition(14, 15, 'F');
        nfa.addTransition(15, 16, 'G');
        nfa.addTransition(16, 17, 'ε');

        // Add accept state
        nfa.addAcceptState(17);

        String testString = "ABCFG";
        boolean result = nfa.matches(testString);
        System.out.println("The string " + testString + " is " + (result ? "accepted" : "rejected") + " by the NFA.");
    }
}