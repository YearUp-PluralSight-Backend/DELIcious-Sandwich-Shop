package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddSandwichCommand implements Command {

    private final Logger logger = LogManager.getLogger(AddSandwichCommand.class);


    public AddSandwichCommand(Order order) {
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {

    }
}
