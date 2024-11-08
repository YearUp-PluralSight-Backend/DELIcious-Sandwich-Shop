package com.pluralsight.gui;

import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderScreen {

    private  static OrderScreen orderScreen;
    private  static final Logger logger = LogManager.getLogger(OrderScreen.class);

    private OrderScreen() {

    }

    public static OrderScreen getInstance() {
        if (orderScreen == null) {
            orderScreen = new OrderScreen();
            return  orderScreen;
        }
        return orderScreen;
    }

    public void orderScreen() {

        boolean runningApplication = true;
        try {
            while (runningApplication)  {
                String option = Utility.getInputAsStringWithPrompt("");

                switch (option) {


                }


            }
        } finally {
        }
    }

    public void orderScreenMenu() {
        String menu = """
            ==================================================
                       💖 SANDWICH HAVEN - ORDER SCREEN 💖
            ==================================================
                        
                      🥪  BUILD YOUR PERFECT ORDER  🥪
            --------------------------------------------------
            
            Select an option:
            
              🍞 1) Add Sandwich
              🥤 2) Add Drink
              🍟 3) Add Chips
              🛒 4) Checkout
              ❌ 0) Cancel Order - delete the order and return to the home screen
            
            ==================================================
                         ❤️ THANK YOU FOR ORDERING ❤️
            ==================================================
            """;
        System.out.println(menu);
    }
}
