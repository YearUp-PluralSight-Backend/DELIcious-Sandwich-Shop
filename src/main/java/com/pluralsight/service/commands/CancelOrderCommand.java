package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelOrderCommand implements Command {

    private final Logger logger = LogManager.getLogger(CancelOrderCommand.class);

    public CancelOrderCommand(Order order) {
        logger.info("Order is canceled!");
        order = null;
        logger.info("order : {}", order);
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
    }
}
