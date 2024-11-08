package com.pluralsight.gui;

import com.pluralsight.entity.Order;
import com.pluralsight.service.Command;
import com.pluralsight.service.commands.*;
import com.pluralsight.utils.Menu;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing the order screen of the application.
 */
public class OrderScreen {
    private static OrderScreen orderScreen;
    private static final Logger logger = LogManager.getLogger(OrderScreen.class);
    private Order order;

    /**
     * Private constructor to prevent instantiation.
     */
    private OrderScreen() {
        order = new Order();
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
     * Displays the order screen, showing the menu and handling user input.
     */
    public void displayOrderScreen() {
        boolean runningApplication = true;
        while (runningApplication) {
            Utility.print.accept(Menu.ORDER_MENU);
            String optionInput = Utility.getInputAsStringWithPrompt("Choose an option:");

            try {
                int option = Integer.parseInt(optionInput);

                switch (option) {
                    case 1 -> processOrderOption(new AddSandwich(order));
                    case 2 -> processOrderOption(new AddDrinkCommand(order));
                    case 3 -> processOrderOption(new AddChipsCommand(order));
                    case 4 -> processOrderOption(new CheckoutCommand(order));
                    case 0 -> {
                        processOrderOption(new CancelOrder(order));
                        runningApplication = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Processes the given command by executing it.
     *
     * @param command the command to be executed
     */
    private void processOrderOption(Command command) {
        command.execute();
    }
}