package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to checkout an order.
 */
public class CheckoutCommand implements Command {

    private Order order;
    private final Logger logger = LogManager.getLogger(CheckoutCommand.class);

    /**
     * Constructor to create a CheckoutCommand.
     *
     * @param order the order to be checked out
     */
    public CheckoutCommand(Order order) {
        this.order = order;
    }

    /**
     * Executes the command to calculate the total price of the order and log it.
     */
    @Override
    public void execute() {
        order.calculateTotalPrice();
        logger.info("Total price is {}", order.getTotalPrice());
    }
}