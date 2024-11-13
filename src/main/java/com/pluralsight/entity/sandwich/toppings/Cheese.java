package com.pluralsight.entity.sandwich.toppings;

import com.pluralsight.utils.ConstantValue;

/**
 * Class representing different types of cheese for a sandwich.
 */
public class Cheese extends SandwichIngredient {

    /**
     * Constructor to create a Cheese with a specified name.
     * Initializes the price and calories based on the cheese information.
     *
     * @param name the name of the cheese
     */
    public Cheese(String name) {
        super(name);
    }

}