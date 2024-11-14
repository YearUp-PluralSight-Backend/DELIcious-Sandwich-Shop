package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelOrderCommand implements Command {

    private final Logger logger = LogManager.getLogger(CancelOrderCommand.class);
    private Order order;

    /**
     * Constructor to create a CancelOrderCommand.
     *
     * @param order the order to be canceled
     */
    public CancelOrderCommand(Order order) {
        this.order = order;
        logger.info("Order is initialized for cancellation: {}", order);
    }

    /**
     * Executes the command to cancel the order.
     */
    @Override
    public void execute() {
        if (order != null) {
            order = null;
            logger.info("Order has been canceled.");
        } else {
            logger.warn("No order to cancel.");
        }
    }
}