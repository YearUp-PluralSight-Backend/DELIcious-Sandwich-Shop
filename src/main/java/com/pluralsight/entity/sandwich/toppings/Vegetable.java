package com.pluralsight.entity.sandwich.toppings;

import com.pluralsight.utils.ConstantValue;

/**
 * Class representing a vegetable ingredient for a sandwich.
 */
public class Vegetable extends SandwichIngredient {

    /**
     * Constructor to create a Vegetable with a specified name.
     * Initializes the price and calories based on the vegetable information.
     *
     * @param name the name of the vegetable
     */
    public Vegetable(String name) {
        super(name);
    }

}