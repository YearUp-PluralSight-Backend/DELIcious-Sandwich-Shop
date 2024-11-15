package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewOrderCommand implements Command {

    private final Logger logger = LogManager.getLogger(ReviewOrderCommand.class);
    private final Order order;

    public ReviewOrderCommand(Order order) {
        this.order = order;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        if (!order.getCart().isEmpty()) {
            order.setTotalPrice(order.calculateTotalPrice());
            logger.info("Reviewing order: {}", order);
            Utility.println.accept(Utility.reviewOrder(order));

        } else {
            Utility.println.accept("Your cart is empty.");
            logger.warn("Order is null, cannot review cart.");
        }
        Utility.randomLoadingAnimation(1);
    }
}