package com.pluralsight.service.commands;

import com.pluralsight.entity.Food;
import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to add chips to an order.
 */
public class AddChipsCommand implements Command {

    private Order order;
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
        order.getCart().add(chips);
        logger.info("Chips added to your order: {}", order);
    }
}