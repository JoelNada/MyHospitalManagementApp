package com.joel.Practice.helper.constants;

import lombok.Getter;

@Getter
public enum BloodType {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String displayValue;

    // Enum constructor (implicitly private)
    BloodType(String displayValue) {
        this.displayValue = displayValue;
    }
}
