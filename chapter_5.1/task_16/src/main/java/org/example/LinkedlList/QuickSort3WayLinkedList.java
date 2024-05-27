package org.example.LinkedlList;

public class QuickSort3WayLinkedList {
    private Node first;

    public Node getFirst() {
        return first;
    }

    public void addNode(String key) {
        if (first == null) {
            first = new Node(key);
            return;
        }

        Node current = first;
        while (current.next != null) {
            current = current.next;
        }

        Node newNode = new Node(key);
        current.next = newNode;
    }

    public void printList(Node n) {
        while (n != null) {
            System.out.print(n.key + " ");
            n = n.next;
        }
        System.out.println();
    }

    Node partition(Node start, Node end) {
        if (start == end || start == null || end == null) {
            return start;
        }

        Node pivot_prev = start;
        Node curr = start;
        String pivot = end.key;

        while (start != end) {
            int cmp = start.key.compareTo(pivot);
            if (cmp < 0) {
                pivot_prev = curr;
                String temp = curr.key;
                curr.key = start.key;
                start.key = temp;
                curr = curr.next;
            }
            start = start.next;
        }

        String temp = curr.key;
        curr.key = pivot;
        end.key = temp;

        return pivot_prev;
    }

    public void sort(Node start, Node end) {
        if (start == end) {
            return;
        }

        Node pivot_prev = partition(start, end);
        sort(start, pivot_prev);

        if (pivot_prev != null && pivot_prev == start) {
            sort(pivot_prev.next, end);
        } else if (pivot_prev != null && pivot_prev.next != null) {
            sort(pivot_prev.next.next, end);
        }
    }
}
