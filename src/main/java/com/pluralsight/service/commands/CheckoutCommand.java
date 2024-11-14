package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.repository.GenerateReceipt;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to check out an order.
 */
public class CheckoutCommand implements Command {

    private final Order order;
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
        if (order != null) {
            logger.info("Total price is {}", order.calculateTotalPrice());
            logger.info("Total calories is {}", order.getTotalCalories());
            boolean successful = GenerateReceipt.writeReceiptToFile(order);
            GenerateReceipt.printReceiptToConsole(order);
            if (successful) {
                logger.info("Receipt written to file successfully.");
            } else {
                logger.error("Failed to write receipt to file.");
            }
        } else {
            logger.warn("Order is null, cannot proceed with checkout.");
        }
        logger.info("Order checked out successfully.");
    }
}