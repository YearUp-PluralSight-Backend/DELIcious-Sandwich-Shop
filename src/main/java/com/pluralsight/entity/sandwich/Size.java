package com.pluralsight.entity.sandwich;

import lombok.Getter;

/**
 * Enum representing different sizes of a sandwich.
 */
public enum Size {

    /**
     * Small size with a value of 4.
     */
    SMALL(4),

    /**
     * Medium size with a value of 8.
     */
    MEDIUM(8),

    /**
     * Large size with a value of 12.
     */
    LARGE(12);

    @Getter
    private final int value;

    /**
     * Constructor for the Size enum.
     *
     * @param value the value associated with the size
     */
    Size(int value) {
        this.value = value;
    }
}