package org.example.entity;

public enum EMPLOYEE_TYPE {
    CEO(0),
    COO(1),
    DIRECTOR(2),
    MANAGER(3)
    ;

    int rank;

    EMPLOYEE_TYPE(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
