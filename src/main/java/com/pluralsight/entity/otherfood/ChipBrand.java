package com.pluralsight.entity.otherfood;

import lombok.Getter;

/**
 * Enum representing different brands of chips.
 */
@Getter
public enum ChipBrand {
    LAYS("Lays", 1),
    PRINGLES("Pringles", 2),
    DORITOS("Doritos", 3),
    RUFFLES("Ruffles", 4);

    private final String brandName;
    private final int menuOption;

    /**
     * Constructor for ChipBrand enum.
     *
     * @param brandName the name of the chip brand
     * @param menuOption the menu option associated with the chip brand
     */
    ChipBrand(String brandName, int menuOption) {
        this.brandName = brandName;
        this.menuOption = menuOption;
    }

    /**
     * Retrieves the ChipBrand corresponding to the given menu option.
     *
     * @param option the menu option
     * @return the ChipBrand corresponding to the menu option
     * @throws IllegalArgumentException if the menu option is invalid
     */
    public static ChipBrand getByMenuOption(int option) {
        for (ChipBrand brand : values()) {
            if (brand.getMenuOption() == option) {
                return brand;
            }
        }
        throw new IllegalArgumentException("Invalid menu option: " + option);
    }

    /**
     * Returns the string representation of the chip brand.
     *
     * @return the brand name
     */
    @Override
    public String toString() {
        return brandName;
    }
}