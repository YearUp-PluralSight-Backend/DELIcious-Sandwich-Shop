package com.pluralsight.entity.sandwich;

import com.pluralsight.exceptions.InvalidIngredientException;

/**
 * Enum representing different types of sandwich ingredients.
 */
public enum IngredientType {
    BREAD, MEAT, CHEESE, VEGETABLE, SAUCE;

    /**
     * Converts a string to an IngredientType enum.
     *
     * @param type the string representation of the ingredient type
     * @return the corresponding IngredientType
     * @throws InvalidIngredientException if the provided string does not match any IngredientType
     */
    public static IngredientType fromString(String type) throws InvalidIngredientException {
        try {
            return IngredientType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            String errorMessage = "Invalid ingredient type provided: " + type;
            throw new InvalidIngredientException(errorMessage);
        }
    }
}