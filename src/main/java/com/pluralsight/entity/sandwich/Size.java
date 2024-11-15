package com.pluralsight.entity.sandwich;

import lombok.Getter;

import java.util.Arrays;

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

    /**
     * Checks if the entered drink size is valid.
     *
     * @param size the size to check
     * @return true if the size is valid, false otherwise
     */
    public static boolean validSize(String size) {
        // Check if the drinkSize matches a valid size (SMALL, MEDIUM, LARGE)
        return Arrays.stream(Size.values())
                .map(Size::name)
                .anyMatch(size::equalsIgnoreCase);
    }
}