package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelOrderCommand implements Command {

    private final Logger logger = LogManager.getLogger(CancelOrderCommand.class);

    public CancelOrderCommand(Order order) {
        order = new Order();
        logger.info("Cart is clean up! {}", order.getCart());
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        logger.info("Order is canceled!");
    }
}
