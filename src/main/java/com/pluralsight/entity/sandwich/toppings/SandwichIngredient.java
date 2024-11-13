package com.pluralsight.entity.sandwich.toppings;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class SandwichIngredient {

    private String name;
    private double price;
    private double calories;

    public SandwichIngredient(String name) {
        this.name = name;
    }
}
