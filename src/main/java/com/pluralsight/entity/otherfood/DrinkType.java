package com.pluralsight.entity.otherfood;

import lombok.Getter;

/**
 * Enum representing different types of drinks.
 */
@Getter
public enum DrinkType {
    COKE("Coca-Cola", 1),
    PEPSI("Pepsi", 2),
    SPRITE("Sprite", 3),
    FANTA("Fanta", 4),
    WATER("Water", 5);

    private final String drinkName;
    private final int menuOption;

    /**
     * Constructor for DrinkType enum.
     *
     * @param drinkName the name of the drink
     * @param menuOption the menu option associated with the drink
     */
    DrinkType(String drinkName, int menuOption) {
        this.drinkName = drinkName;
        this.menuOption = menuOption;
    }

    /**
     * Retrieves the DrinkType corresponding to the given menu option.
     *
     * @param option the menu option
     * @return the DrinkType corresponding to the menu option
     * @throws IllegalArgumentException if the menu option is invalid
     */
    public static DrinkType getByMenuOption(int option) {
        for (DrinkType drink : values()) {
            if (drink.getMenuOption() == option) {
                return drink;
            }
        }
        throw new IllegalArgumentException("Invalid menu option: " + option);
    }

    /**
     * Returns the string representation of the drink type.
     *
     * @return the drink name
     */
    @Override
    public String toString() {
        return drinkName;
    }
}