package com.pluralsight.console_gui;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import com.pluralsight.service.commands.*;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing the order screen of the application.
 */
public class OrderScreen {
    private static OrderScreen orderScreen;
    private static final Logger logger = LogManager.getLogger(OrderScreen.class);

    /**
     * Private constructor to prevent instantiation.
     */
    private OrderScreen() {
    }

    /**
     * Returns the singleton instance of OrderScreen.
     *
     * @return the singleton instance of OrderScreen
     */
    public static synchronized OrderScreen getInstance() {
        if (orderScreen == null) {
            orderScreen = new OrderScreen();
        }
        return orderScreen;
    }

    /**
     * Creates a new order object.
     *
     * @return a new Order instance
     */
    private Order newOrder() {
        return new Order();
    }

    /**
     * Displays the order screen, showing the menu and handling user input.
     */
    public void displayOrderScreen() {
        boolean runningApplication = true;
        Order order = newOrder();
        Utility.println.accept("New order created. Order Number(" +  order.getOrderNumber() + ")");
        while (runningApplication) {
            Utility.pauseAnimation(2);
            Utility.print.accept(ConstantValue.ORDER_MENU);
            String optionInput = Utility.getInputAndReturnStringWithPrompt("->");
            try {
                int option = Integer.parseInt(optionInput);
                switch (option) {
                    case 1 -> processOrderOption(new AddChipsCommand(order));
                    case 2 -> processOrderOption(new AddDrinkCommand(order));
                    case 3 -> processOrderOption(new AddSandwichCommand(order));
                    case 4 -> processOrderOption(new CheckoutCommand(order));
                    case 5 -> processOrderOption(new ReviewOrderCommand(order));
                    case 0 -> {
                        processOrderOption(new CancelOrderCommand(order));
                        runningApplication = false;
                    }
                    default -> {
                        Utility.print.accept("Please enter a valid number.");
                        logger.error("Invalid option. Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                Utility.print.accept("Please enter a valid number.");
                logger.error("Please enter a valid number.{}", optionInput);
            }
        }
    }

    /**
     * Processes the given command by executing it.
     *
     * @param command the command to be executed
     */
    private void processOrderOption(Command command) {
        logger.info("Executing command: {}", command.getClass());
        command.execute();
        Utility.randomLoadingAnimation(2);

    }
}