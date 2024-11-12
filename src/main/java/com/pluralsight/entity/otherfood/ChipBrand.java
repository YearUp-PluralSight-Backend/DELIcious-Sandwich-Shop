package com.pluralsight.entity.otherfood;


public enum ChipBrand {
    LAYS("Lay's Classic Chips", 1),
    PRINGLES("Pringles Original", 2),
    DORITOS("Doritos Nacho Cheese", 3),
    RUFFLES("Ruffles Sour Cream & Onion", 4);

    private final String brandName;
    private final int menuOption;

    ChipBrand(String brandName, int menuOption) {
        this.brandName = brandName;
        this.menuOption = menuOption;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getMenuOption() {
        return menuOption;
    }

    public static ChipBrand getByMenuOption(int option) {
        for (ChipBrand brand : values()) {
            if (brand.getMenuOption() == option) {
                return brand;
            }
        }
        throw new IllegalArgumentException("Invalid menu option: " + option);
    }

    @Override
    public String toString() {
        return brandName;
    }
}