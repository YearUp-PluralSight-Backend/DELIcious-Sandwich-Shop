package com.pluralsight.gui;

import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;

public class HomeScreen {

    private  static HomeScreen homeScreen;
    private final Logger logger = LogManager.getLogger(HomeScreen.class);
    private final OrderScreen orderScreen = OrderScreen.getInstance();
    private HomeScreen() {


    }

    public static HomeScreen getInstance() {
        if (homeScreen == null) {
            homeScreen = new HomeScreen();
            return  homeScreen;
        }
        return homeScreen;
    }

    public void run() {

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

//    @CommandLine.Option("")
    public void homeScreenMenu() {

        String menu =
                """
                ============================================
                        WELCOME TO SANDWICH HAVEN
                ============================================
        
                            (  🍞 🥬 🥓  )
                            DELICIOUS SANDWICHES
        
                Please choose an option:
        
                1. New Order
                2. Exit
        
                ============================================
                """;
        System.out.print(menu);
}
}
