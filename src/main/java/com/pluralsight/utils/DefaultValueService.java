package com.pluralsight.utils;


import com.pluralsight.entity.sandwich.Size;
import com.pluralsight.exceptions.InvalidIngredientException;

public class DefaultValueService {
    public static double calculatePrice(ConstantValue.IngredientInfo info, Size size) throws InvalidIngredientException {
        return switch (size.getValue()) {
            case 4 -> info.price4Inch();
            case 8 -> info.price8Inch();
            case 12 -> info.price12Inch();
            default -> throw new InvalidIngredientException("Invalid sandwich size");
        };
    }

    public static double calculateCalories(ConstantValue.IngredientInfo info, Size size) throws InvalidIngredientException {
        return switch (size.getValue()) {
            case 4 -> info.calories();
            case 8 -> info.calories() * 1.2;
            case 12 -> info.calories() * 1.4;
            default -> throw new InvalidIngredientException("Invalid sandwich size");
        };
    }
}
