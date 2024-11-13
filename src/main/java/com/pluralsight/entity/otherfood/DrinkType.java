package com.pluralsight.entity.otherfood;

public enum DrinkType {
    COKE("Coca-Cola", 1),
    PEPSI("Pepsi", 2),
    SPRITE("Sprite", 3),
    FANTA("Fanta", 4),
    WATER("Water", 5);

    private final String drinkName;
    private final int menuOption;

    DrinkType(String drinkName, int menuOption) {
        this.drinkName = drinkName;
        this.menuOption = menuOption;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public int getMenuOption() {
        return menuOption;
    }

    public static DrinkType getByMenuOption(int option) {
        for (DrinkType drink : values()) {
            if (drink.getMenuOption() == option) {
                return drink;
            }
        }
        throw new IllegalArgumentException("Invalid menu option: " + option);
    }

    @Override
    public String toString() {
        return drinkName;
    }
}