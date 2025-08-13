package com.simpleexchange.common.enums;

public enum UserRole {
    ADMIN(0),
    EMPLOYEE(1);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
