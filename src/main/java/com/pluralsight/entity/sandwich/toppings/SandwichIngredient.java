package com.pluralsight.entity.sandwich.toppings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract class representing a sandwich ingredient.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class SandwichIngredient {

    /**
     * The name of the sandwich ingredient.
     */
    private String name;

    /**
     * The price of the sandwich ingredient.
     */
    private double price;

    /**
     * The calories of the sandwich ingredient.
     */
    private double calories;

    /**
     * Constructor for creating a sandwich ingredient with a name.
     *
     * @param name the name of the sandwich ingredient
     */
    public SandwichIngredient(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the SandwichIngredient object.
     *
     * @return a string representation of the SandwichIngredient object
     */
    @Override
    public String toString() {
        return new StringBuilder().append("║   - ")
                .append(String.format("%-10s", getName()))
                .append("\t")
                .append(String.format("%-10.2f", getPrice()))
                .append("\t")
                .append(String.format("%.1f", getCalories()))
                .append("\n").toString();
    }
}