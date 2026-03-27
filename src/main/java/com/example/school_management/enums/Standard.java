package com.example.school_management.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Standard {

    // ── KG Standards ────────────────────────────
    NURSERY("Nursery"),
    JUNIOR_KG("Junior KG"),
    SENIOR_KG("Senior KG"),

    // ── Numeric Standards ────────────────────────
    STD_1("1"),
    STD_2("2"),
    STD_3("3"),
    STD_4("4"),
    STD_5("5"),
    STD_6("6"),
    STD_7("7"),
    STD_8("8"),
    STD_9("9"),
    STD_10("10"),
    STD_11("11"),
    STD_12("12");

    private final String value;

    Standard(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Standard fromValue(String value) {
        for (Standard std : Standard.values()) {
            if (std.value.equalsIgnoreCase(value)) {
                return std;
            }
        }
        throw new IllegalArgumentException("Invalid standard: " + value);
    }
}