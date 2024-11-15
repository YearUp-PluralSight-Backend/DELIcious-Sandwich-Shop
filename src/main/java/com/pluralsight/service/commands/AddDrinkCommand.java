package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.otherfood.DrinkType;
import com.pluralsight.entity.sandwich.Size;
import com.pluralsight.service.Command;
import com.pluralsight.utils.ConstantValue;
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
        Utility.println.accept( drink.getName()+ " added to your order");
        Utility.println.accept(drink.toString());
        logger.info("{} added to your order (Order Number: {})",
                drink.getName(),order.getOrderNumber());
        order.setTotalCalories(order.getTotalCalories());
        order.setTotalPrice(order.getTotalPrice());
    }

    /**
     * Initializes the attributes of the drink including its name, size, price, and calories.
     */
    private void initializeDrink() {
        DrinkType drinkBrand = selectDrinkBrand();
        Size size = selectAndValidateSize();
        setDrinkAttributes(drinkBrand, size);
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
                int option = Utility.getInputAndReturnIntegerWithPrompt("->");
                drinkBrand = DrinkType.getByMenuOption(option);
            } catch (IllegalArgumentException e) {
                Utility.println.accept("Invalid option. Please try again.");
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
            String drinkSize = Utility.getInputAndReturnStringWithPrompt("Enter the drink size (SMALL, MEDIUM, LARGE): ").toUpperCase();
            if (Utility.validSize(drinkSize)) {
                size = Size.valueOf(drinkSize);
            } else {
                logger.error("Invalid drink size provided: {}", drinkSize);
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
                drink.setCalories(100);
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
            default -> {
                Utility.println.accept("Unexpected size encountered: " + size);
                logger.error("Unexpected size encountered: {}", size);
            }
        }

        if (drinkBrand == DrinkType.WATER) {
            drink.setCalories(0);
        }
    }


}