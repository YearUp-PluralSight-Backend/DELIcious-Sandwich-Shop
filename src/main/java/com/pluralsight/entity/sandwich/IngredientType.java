package com.pluralsight.entity.sandwich;

import com.pluralsight.exceptions.InvalidIngredientException;

public enum IngredientType {
    BREAD, MEAT, CHEESE, VEGETABLE, SAUCE;

    public static IngredientType fromString(String type) throws InvalidIngredientException {
        try {
            return IngredientType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            String errorMessage = "Invalid ingredient type provided: " + type;
            throw new InvalidIngredientException(errorMessage);
        }
    }
}
