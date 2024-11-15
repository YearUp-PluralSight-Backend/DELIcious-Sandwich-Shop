package com.pluralsight.entity;

import com.pluralsight.entity.sandwich.Size;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Abstract class representing a food item.
 */
@Data
public abstract class Food {

    /**
     * Cannot null
     */
    @NotNull
    private String name;

    /**
     * The size of the food item.
     * Must be specified.
     */
    @NotNull(message = "Size must be specified")
    private Size size;

    /**
     * The price of the food item.
     * Must be at least 0 and not exceed 100000.
     */
    @Min(value = 0, message = "Price cannot be lower than 0")
    @Min(value = 100000, message = "I do not have enough supply to make your Order")
    private double price;

    /**
     * The calories of the food item.
     * Must not be empty and must be at least 0.
     */
    @NotEmpty
    @Min(0)
    private double calories;

    /**
     * Default constructor.
     */
    public Food() {
    }

    /**
     * Constructor to create a food item with a specified size.
     *
     * @param size the size of the food item
     */
    public Food(Size size) {
        this.size = size;
    }

}