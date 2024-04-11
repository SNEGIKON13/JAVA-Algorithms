package org.example;

import java.util.Arrays;

public record CheckStructure(int number, int id) implements
        Comparable<CheckStructure> {

    public static boolean check(final CheckStructure[] structures) {
        Arrays.sort(structures);
        for (int i = 0; i < structures.length - 1; i++) {
            if (structures[i].number() == structures[i + 1].number()
                    && structures[i].id() > structures[i + 1].id()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(final CheckStructure o) {
        return Integer.compare(this.number, o.number);
    }
}
