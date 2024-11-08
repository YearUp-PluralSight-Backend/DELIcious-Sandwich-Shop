package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckoutCommand implements Command {

    private Order order;
    private final Logger logger = LogManager.getLogger(CheckoutCommand.class);

    public CheckoutCommand(Order order) {
        this.order = order;

    }

    /**
     *
     */
    @Override
    public void execute() {
        order.calculateTotalPrice();
        logger.info("total of price is {}", order.getTotalPrice());
    }
}
