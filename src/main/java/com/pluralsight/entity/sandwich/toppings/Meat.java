package com.pluralsight.entity.sandwich.toppings;

import com.pluralsight.utils.ConstantValue;

/**
 * Class representing different types of meat for a sandwich.
 */
public class Meat extends SandwichIngredient {

    /**
     * Constructor to create Meat with a specified name.
     * Initializes the price and calories based on the meat information.
     *
     * @param name the name of the meat
     */
    public Meat(String name) {
        super(name);
    }

}