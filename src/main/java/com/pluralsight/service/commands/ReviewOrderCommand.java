package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
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
        order.reviewCart();
    }
}
