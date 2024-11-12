package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.otherfood.DrinkType;
import com.pluralsight.entity.sandwich.types.Size;
import com.pluralsight.service.Command;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Command to add a drink to an order.
 */
public class AddDrinkCommand implements Command {

    private final Logger logger = LogManager.getLogger(AddDrinkCommand.class);
    private final Order order;
    private final Drink drink;
    private String drinkSize;

    /**
     * Constructor to create an AddDrinkCommand.
     *
     * @param order the order to which the drink will be added
     */
    public AddDrinkCommand(Order order) {
        this.order = order;
        this.drink = new Drink();
        initializeDrink();
    }

    /**
     * Executes the command to add the drink to the order.
     */
    @Override
    public void execute() {
        order.getCart().add(drink);
        String message = String.format("\nChips added to your order (Order Number: %d)\nName: %s\nPrice: $%.2f\nCalories: %.2f",
                order.getOrderNumber(), drink.getName(), drink.getPrice(), drink.getCalories());
        logger.info(message);
    }

    /**
     * Initializes the attributes of the drink including its name, size, price, and calories.
     */
    private void initializeDrink() {
        try {
            // Select drink brand from the menu
            DrinkType drinkBrand = selectDrinkBrand();

            // Select and validate drink size
            Size size = selectAndValidateSize();

            // Set drink attributes
            setDrinkAttributes(drinkBrand, size);

        } catch (IllegalArgumentException e) {
            logger.error("Error initializing drink: ", e);
        }
    }

    /**
     * Prompts the user to select a drink brand and returns the selected DrinkType.
     *
     * @return the selected DrinkType
     */
    private DrinkType selectDrinkBrand() {
        DrinkType drinkBrand = null;
        while (drinkBrand == null) {
            Utility.print.accept(ConstantValue.DRINK_MENU);
            try {
                int option = Utility.getInputAsIntegerWithPrompt("->");
                drinkBrand = DrinkType.getByMenuOption(option);

            } catch (IllegalArgumentException e) {
                logger.error("Error fetching drink brand: ", e);
            }
        }
        return drinkBrand;
    }

    /**
     * Prompts the user to enter and validate the drink size (SMALL, MEDIUM, LARGE).
     *
     * @return the valid Size
     */
    private Size selectAndValidateSize() {
        Size size = null;
        while (size == null) {
            this.drinkSize = Utility.getInputAsStringWithPrompt("Enter the drink size (SMALL, MEDIUM, LARGE): ").toUpperCase();

            if (validSize()) {
                size = Size.valueOf(this.drinkSize);
            } else {
                logger.error("Invalid drink size provided: {}", this.drinkSize);
            }
        }
        return size;
    }

    /**
     * Sets the attributes (name, size, price, and calories) of the drink.
     *
     * @param drinkBrand the selected drink brand
     * @param size the selected size
     */
    private void setDrinkAttributes(DrinkType drinkBrand, Size size) {
        drink.setName(drinkBrand.getDrinkName());
        drink.setSize(size);

        switch (size) {
            case SMALL -> {
                drink.setCalories(150);
                drink.setPrice(2.0);
            }
            case MEDIUM -> {
                drink.setCalories(200);
                drink.setPrice(2.5);
            }
            case LARGE -> {
                drink.setCalories(250);
                drink.setPrice(3.0);
            }
            default -> logger.error("Unexpected size encountered: {}", size);
        }
    }

    /**
     * Checks if the entered drink size is valid.
     *
     * @return true if the size is valid, false otherwise
     */
    private boolean validSize() {
        // Check if the drinkSize matches a valid size (SMALL, MEDIUM, LARGE)
        return Arrays.stream(Size.values())
                .map(Size::name)
                .anyMatch(name -> this.drinkSize.equalsIgnoreCase(name));
    }
}