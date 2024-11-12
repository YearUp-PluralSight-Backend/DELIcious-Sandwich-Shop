package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.sandwich.types.Size;
import com.pluralsight.service.Command;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to add a drink to an order.
 */
public class AddDrinkCommand implements Command {

    private final Logger logger = LogManager.getLogger(AddDrinkCommand.class);
    private final Order order;
    private final Drink drink;
    private double basePrice;
    private final String drinkSize;

    /**
     * Constructor to create an AddDrinkCommand.
     *
     * @param order the order to which the drink will be added
     */
    public AddDrinkCommand(Order order) {
        logger.info("Executing AddCommand ----");
        this.order = order;
        this.drinkSize = Utility.getInputAsStringWithPrompt("Enter the drink size (SMALL, MEDIUM, LARGE): ").toUpperCase();
        this.drink = new Drink();

        switch (drinkSize) {
            case "SMALL" -> this.basePrice = 2.0;
            case "MEDIUM" -> this.basePrice = 2.5;
            case "LARGE" -> this.basePrice = 3.0;
            default -> {
                String errorMessage = "Invalid drink size: " + drinkSize;
                Utility.print.accept(errorMessage);
                logger.error(errorMessage);
            }
        }
    }

    /**
     * Executes the command to add the drink to the order.
     */
    @Override
    public void execute() {
        drink.setSize(Size.valueOf(drinkSize));
        drink.setPrice(basePrice);
        switch (Size.valueOf(drinkSize)) {
            case SMALL -> drink.setCalories(150);
            case MEDIUM -> drink.setCalories(200);
            case LARGE -> drink.setCalories(250);
        }

        order.getCart().add(drink);
        String message = "Drink added to your order: {%s}, ${%s}".formatted(drinkSize, basePrice);
        Utility.print.accept(message);
        logger.info(message);
    }
}