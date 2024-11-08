package com.pluralsight.gui;

import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeScreen {

    private  static HomeScreen homeScreen;
    private  static final Logger logger = LogManager.getLogger(HomeScreen.class);
    private static final OrderScreen orderScreen = OrderScreen.getInstance();
    private HomeScreen() {


    }

    public static HomeScreen getInstance() {
        if (homeScreen == null) {
            homeScreen = new HomeScreen();
            return  homeScreen;
        }
        return homeScreen;
    }

    public void HomeScreen() {

        boolean runningHomeScreen = true;
        try {
            while (runningHomeScreen)  {
                homeScreenMenu();
                String option = Utility.getInputAsStringWithPrompt("");

                switch (option) {
                    case "1" -> orderScreen.orderScreen();
                    case "0" -> runningHomeScreen = false;

                }
            }
        } finally {
            updateAndPrintReceipt();
        }
    }

    private void updateAndPrintReceipt() {
    }

    public void homeScreenMenu() {

        String menu =
                """
                
                """;
    }
}
