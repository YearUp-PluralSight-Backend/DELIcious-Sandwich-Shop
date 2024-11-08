package com.pluralsight.gui;

import com.pluralsight.utils.Menu;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;

/**
 * Class representing the home screen of the application.
 */
public class HomeScreen {

    private static HomeScreen homeScreen;
    private final Logger logger = LogManager.getLogger(HomeScreen.class);
    private final OrderScreen orderScreen = OrderScreen.getInstance();

    /**
     * Private constructor to prevent instantiation.
     */
    private HomeScreen() {
    }

    /**
     * Returns the singleton instance of HomeScreen.
     *
     * @return the singleton instance of HomeScreen
     */
    public static synchronized HomeScreen getInstance() {
        if (homeScreen == null) {
            homeScreen = new HomeScreen();
            return homeScreen;
        }
        return homeScreen;
    }

    /**
     * Runs the home screen, displaying the menu and handling user input.
     */
    public void run() {
        boolean runningHomeScreen = true;
        try {
            while (runningHomeScreen) {
                Utility.print.accept(Menu.HOME_MENU);
                String option = Utility.getInputAsStringWithPrompt("->  ");

                switch (option) {
                    case "1" -> orderScreen.displayOrderScreen();
                    case "0" -> runningHomeScreen = false;
                }
            }
        } finally {
            updateAndPrintReceipt();
        }
    }

    /**
     * Updates and prints the receipt.
     */
    private void updateAndPrintReceipt() {
    }
}