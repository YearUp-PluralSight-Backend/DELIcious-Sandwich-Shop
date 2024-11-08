package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AddChipsCommand implements Command {


    private Order order;
    private final Logger logger = LogManager.getLogger(AddChipsCommand.class);

    public AddChipsCommand(Order order) {
        this.order = order;
    }

    /**
     *
     */
    @Override
    public void execute() {
        Chips chips = new Chips();
        chips.setPrice(1.5);
        order.getCart().add(chips);
        logger.info("Chips added to your order: {}", order);
    }
}
