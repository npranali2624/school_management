package com.example.school_management.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Standard {
    STD_1(1),
    STD_2(2),
    STD_3(3),
    STD_4(4),
    STD_5(5),
    STD_6(6),
    STD_7(7),
    STD_8(8),
    STD_9(9),
    STD_10(10),
    STD_11(11),
    STD_12(12);

    private final int value;

    Standard(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    // 🔥 THIS IS THE KEY PART
    @JsonCreator
    public static Standard fromValue(int value) {
        for (Standard std : Standard.values()) {
            if (std.value == value) {
                return std;
            }
        }
        throw new IllegalArgumentException("Invalid standard: " + value);
    }
}