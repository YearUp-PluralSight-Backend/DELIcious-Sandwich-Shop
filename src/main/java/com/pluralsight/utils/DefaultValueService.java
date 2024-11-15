package com.pluralsight.utils;

import com.pluralsight.entity.sandwich.Size;
import com.pluralsight.exceptions.InvalidIngredientException;

/**
 * Service class for calculating default values such as price and calories for ingredients.
 */
public class DefaultValueService {

    /**
     * Calculates the price of an ingredient based on its size.
     *
     * @param info the ingredient information containing prices for different sizes
     * @param size the size of the sandwich
     * @return the price of the ingredient for the given size
     * @throws InvalidIngredientException if the size is invalid
     */
    public static double calculatePrice(ConstantValue.IngredientInfo info, Size size) throws InvalidIngredientException {
        return switch (size.getValue()) {
            case 4 -> info.price4Inch();
            case 8 -> info.price8Inch();
            case 12 -> info.price12Inch();
            default -> throw new InvalidIngredientException("Invalid sandwich size");
        };
    }

    /**
     * Calculates the calories of an ingredient based on its size.
     *
     * @param info the ingredient information containing calorie count
     * @param size the size of the sandwich
     * @return the calories of the ingredient for the given size
     * @throws InvalidIngredientException if the size is invalid
     */
    public static double calculateCalories(ConstantValue.IngredientInfo info, Size size) throws InvalidIngredientException {
        return switch (size.getValue()) {
            case 4 -> info.calories();
            case 8 -> info.calories() * 1.2;
            case 12 -> info.calories() * 1.4;
            default -> throw new InvalidIngredientException("Invalid sandwich size");
        };
    }
}