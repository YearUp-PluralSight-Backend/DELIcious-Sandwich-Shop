package com.pluralsight.entity.sandwich.toppings;

import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;

/**
 * Class representing a sauce ingredient for a sandwich.
 */
public class Sauce extends SandwichIngredient {

    /**
     * Constructor to create a Sauce with a specified name.
     * Initializes the calories based on the sauce information.
     *
     * @param name the name of the sauce
     */
    public Sauce(String name) {
        super(name);
    }

}