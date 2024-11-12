package com.pluralsight.service.commands;

import com.pluralsight.entity.Food;
import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.service.Command;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to add chips to an order.
 */
public class AddChipsCommand implements Command {

    private final Order order;
    private final Logger logger = LogManager.getLogger(AddChipsCommand.class);

    /**
     * Constructor to create an AddChipsCommand.
     *
     * @param order the order to which chips will be added
     */
    public AddChipsCommand(Order order) {
        this.order = order;
    }

    /**
     * Executes the command to add chips to the order.
     */
    @Override
    public void execute() {
        Food chips = new Chips();
        chips.setPrice(1.5);
        chips.setCalories(120);
        String message = String.format("Chips added to your order (Order Number: %d)\nPrice: $%.2f\nCalories: %.2f",
                order.getOrderNumber(), chips.getPrice(), chips.getCalories());
        logger.info(message);
        printChipsAddedToOrder(chips, message);
    }

    /**
     * Adds the chips to the order and prints a message.
     *
     * @param chips the chips to be added
     * @param message the message to be printed
     */
    private void printChipsAddedToOrder(Food chips, String message) {
        order.getCart().add(chips);
        Utility.print.accept(message);
    }
}