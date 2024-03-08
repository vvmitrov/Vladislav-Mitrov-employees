package org.employees.model;

import java.util.Objects;

public class EmployeePair {
    private int first;
    private int second;

    public EmployeePair(int first, int second) {
        this.first = Math.min(first, second);
        this.second = Math.max(first, second);
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair pair = (EmployeePair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
