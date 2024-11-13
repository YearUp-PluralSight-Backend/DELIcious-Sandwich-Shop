package com.pluralsight.utils;

import com.pluralsight.entity.otherfood.ChipBrand;
import com.pluralsight.entity.otherfood.DrinkType;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ConstantValue {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss", Locale.ENGLISH);
    public record IngredientInfo(double price4Inch, double price8Inch, double price12Inch, int calories) {}

    public static final Map<String, IngredientInfo> cheeseInfoMap = new HashMap<>();
    public static final Map<String, IngredientInfo> meatInfoMap = new HashMap<>();
    public static final Map<String, IngredientInfo> vegetableInfoMap = new HashMap<>();
    public static final Map<String, IngredientInfo> sauceInfoMap = new HashMap<>();
    public static final Map<String, IngredientInfo> breadInfoMap = new HashMap<>();
    static {
        sauceInfoMap.put("MAYO", new IngredientInfo(0, 0, 0, 40));
        sauceInfoMap.put("MUSTARD", new IngredientInfo(0, 0, 0, 30));
        sauceInfoMap.put("KETCHUP", new IngredientInfo(0, 0, 0, 20));
        sauceInfoMap.put("RANCH", new IngredientInfo(0, 0, 0, 50));
        sauceInfoMap.put("THOUSAND_ISLANDS", new IngredientInfo(0, 0, 0, 60));
        sauceInfoMap.put("VINAIGRETTE", new IngredientInfo(0, 0, 0, 25));

        vegetableInfoMap.put("LETTUCE", new IngredientInfo(0, 0, 0, 5));
        vegetableInfoMap.put("PEPPERS", new IngredientInfo(0, 0, 0, 10));
        vegetableInfoMap.put("ONIONS", new IngredientInfo(0, 0, 0, 15));
        vegetableInfoMap.put("TOMATOES", new IngredientInfo(0, 0, 0, 10));
        vegetableInfoMap.put("JALAPENOS", new IngredientInfo(0, 0, 0, 5));
        vegetableInfoMap.put("CUCUMBERS", new IngredientInfo(0, 0, 0, 5));
        vegetableInfoMap.put("PICKLES", new IngredientInfo(0, 0, 0, 5));
        vegetableInfoMap.put("GUACAMOLE", new IngredientInfo(0, 0, 0, 40));
        vegetableInfoMap.put("MUSHROOMS", new IngredientInfo(0, 0, 0, 10));
        vegetableInfoMap.put("AVOCADO", new IngredientInfo(0, 0, 0, 20));


        breadInfoMap.put("WHITE", new IngredientInfo(5.50, 7.00, 8.50, 200));
        breadInfoMap.put("WHEAT", new IngredientInfo(5.50, 7.00, 8.50, 300));
        breadInfoMap.put("RYE", new IngredientInfo(5.50, 7.00, 8.50, 200));
        breadInfoMap.put("WRAP", new IngredientInfo(5.50, 7.00, 8.50, 250));

        meatInfoMap.put("STEAK", new IngredientInfo(1.00, 2.00, 3.00, 120));
        meatInfoMap.put("HAM", new IngredientInfo(1.00, 2.00, 3.00, 90));
        meatInfoMap.put("SALAMI", new IngredientInfo(1.00, 2.00, 3.00, 110));
        meatInfoMap.put("ROAST BEEF", new IngredientInfo(1.00, 2.00, 3.00, 130));
        meatInfoMap.put("CHICKEN", new IngredientInfo(1.00, 2.00, 3.00, 80));
        meatInfoMap.put("PEPPERONI", new IngredientInfo(1.00, 2.00, 3.00, 100));
        meatInfoMap.put("BACON", new IngredientInfo(1.00, 2.00, 3.00, 100));

        meatInfoMap.put("EXTRA_MEAT", new IngredientInfo(0.50, 1.00, 1.50, 50));

        cheeseInfoMap.put("AMERICAN", new IngredientInfo(0.75, 1.50, 2.25, 50));
        cheeseInfoMap.put("PROVOLONE", new IngredientInfo(0.75, 1.50, 2.25, 60));
        cheeseInfoMap.put("CHEDDAR", new IngredientInfo(0.75, 1.50, 2.25, 70));
        cheeseInfoMap.put("SWISS", new IngredientInfo(0.75, 1.50, 2.25, 80));
        cheeseInfoMap.put("EXTRA_CHEESE", new IngredientInfo(0.30, 0.60, 0.90, 20));
    }

    public static final String ORDER_MENU =
            """
                     ======================================================================
                                        💖 CHIPS GALORE - ORDER SCREEN 💖
                     ======================================================================
                    
                    
                                        🍟  BUILD YOUR PERFECT CHIPS ORDER  🍟
                     ----------------------------------------------------------------------
                    
                     Select an option:
                    
                       🍞 1) Add Chips - Choose your favorite flavor
                       🥤 2) Add Drink - Pair your chips with a refreshing drink
                       🍔 3) Add Sandwich - Complete your meal with a sandwich
                       🛒 4) Checkout - Checkout your order
                          5) Review - Review your order
                       ❌ 0) Cancel Order - delete the order and return to the home screen
                    
                     ======================================================================
                                  ❤️ THANK YOU FOR ORDERING ❤️
                     ======================================================================
                    """;

    public static final String HOME_MENU =
            """
                     ======================================================================
                            WELCOME TO SANDWICH HAVEN
                     ======================================================================
                    
                                (  🍞 🥬 🥓  )
                                DELICIOUS SANDWICHES
                    
                     Please choose an option:
                    
                     1. New Order
                     2. Exit
                    
                     ======================================================================
                    """;

    public static final String CHIPS_MENU =
            """
                     ======================================================================
                                        💖 CHIPS GALORE - ORDER SCREEN 💖
                     ======================================================================
                    
                                        🍟  CHOOSE YOUR FAVORITE CHIPS  🍟
                     ----------------------------------------------------------------------
                    
                     Select a brand:
                    
                       🥔 1) %s
                       🥔 2) %s
                       🥔 3) %s
                       🥔 4) %s
                    
                     ======================================================================
                                  ❤️ THANK YOU FOR ORDERING ❤️
                     ======================================================================
                    """.formatted(ChipBrand.LAYS.getBrandName(), ChipBrand.PRINGLES.getBrandName(), ChipBrand.DORITOS.getBrandName(), ChipBrand.RUFFLES.getBrandName());

    public static final String DRINK_MENU =
            """
                     ======================================================================
                                    💖 DRINK SELECTION - ORDER SCREEN 💖
                     ======================================================================
                    
                                      🥤  CHOOSE YOUR FAVORITE DRINK  🥤
                     --------------------------------------------------
                    
                     Select a drink:
                    
                      🍹 1) %s
                      🍹 2) %s
                      🍹 3) %s
                      🍹 4) %s
                      🍹 5) %s
                    
                    ======================================================================
                                        ❤️ THANK YOU FOR ORDERING ❤️
                    ======================================================================
                    """
                    .formatted(DrinkType.COKE.getDrinkName(), DrinkType.PEPSI.getDrinkName(), DrinkType.SPRITE.getDrinkName(), DrinkType.FANTA.getDrinkName(), DrinkType.WATER.getDrinkName());

    public static final String BREAD_MENU =
            """
                     ========================================================================
                                      🥪 SANDWICH DELIGHTS - ORDER MENU 🥪
                     ========================================================================
                    
                                      🥖 BUILD YOUR PERFECT SANDWICH ORDER 🥖
                     ------------------------------------------------------------------------
                    
                     Choose Your Bread Type & Size:
                    
                     Bread Types         | 4" (Small)       | 8" (Medium)       | 12" (Large)
                     ------------------------------------------------------------------------
                     1. WHITE            | $5.50, 200 cal   | $7.00, 250 cal    | $8.50, 300 cal
                     2. WHEAT            | $5.50, 300 cal   | $7.00, 350 cal    | $8.50, 400 cal
                     3. RYE              | $5.50, 200 cal   | $7.00, 250 cal    | $8.50, 300 cal
                     4. WRAP             | $5.50, 250 cal   | $7.00, 300 cal    | $8.50, 350 cal
                     ------------------------------------------------------------------------
                    
                          🎉 Select a bread and size to start building your sandwich! 🎉
                    
                     ========================================================================
                                            ❤️ THANK YOU FOR ORDERING ❤️
                     ========================================================================
                    """;

    public static final String MEAT_MENU =
            """
                     ========================================================================
                                            🥩 MEAT TOPPINGS MENU 🥩
                     ========================================================================
                    
                                            🍖 ADD PROTEIN TO YOUR SANDWICH 🍖
                     ------------------------------------------------------------------------
                    
                     Meat Toppings      | 4" (Small)       | 8" (Medium)       | 12" (Large)
                     ------------------------------------------------------------------------
                     1. STEAK           | $1.00, 120 cal   | $2.00, 170 cal    | $3.00, 220 cal
                     2. HAM             | $1.00, 90 cal    | $2.00, 140 cal    | $3.00, 190 cal
                     3. SALAMI          | $1.00, 110 cal   | $2.00, 160 cal    | $3.00, 210 cal
                     4. ROAST BEEF      | $1.00, 130 cal   | $2.00, 180 cal    | $3.00, 230 cal
                     5. CHICKEN         | $1.00, 80 cal    | $2.00, 130 cal    | $3.00, 180 cal
                     6. BACON           | $1.00, 100 cal   | $2.00, 150 cal    | $3.00, 200 cal
                     ------------------------------------------------------------------------
                    
                     🥓 Pick your favorite meats and customize your sandwich! 🥓
                    
                     ========================================================================
                                   ❤️ THANK YOU FOR ORDERING ❤️
                     ========================================================================
                    """;

    public static final String CHEESE_MENU =
            """
                     ========================================================================
                                            🧀 CHEESE TOPPINGS MENU 🧀
                     ========================================================================
                    
                                            🧀 ADD FLAVOR TO YOUR SANDWICH 🧀
                     ------------------------------------------------------------------------
                    
                     Cheese Toppings    | 4" (Small)       | 8" (Medium)       | 12" (Large)
                     ------------------------------------------------------------------------
                     1. AMERICAN        | $0.75, 50 cal    | $1.50, 100 cal    | $2.25, 150 cal
                     2. PROVOLONE       | $0.75, 60 cal    | $1.50, 110 cal    | $2.25, 160 cal
                     3. CHEDDAR         | $0.75, 70 cal    | $1.50, 120 cal    | $2.25, 170 cal
                     4. SWISS           | $0.75, 80 cal    | $1.50, 130 cal    | $2.25, 180 cal
                     ------------------------------------------------------------------------
                    
                     🧀 Choose your favorite cheese and make your sandwich delicious! 🧀
                    
                     ========================================================================
                                   ❤️ THANK YOU FOR ORDERING ❤️
                     ========================================================================
                    """;

    public static final String VEGETABLE_MENU =
            """
                     ========================================================================
                                           🥬 REGULAR TOPPINGS MENU 🥬
                     ========================================================================
                    
                                           🥗 ADD FRESHNESS TO YOUR SANDWICH 🥗
                     ------------------------------------------------------------------------
                    
                     Regular Toppings         | Included, Calories
                     ------------------------------------------------------------------------
                     1. LETTUCE               | Included, 5 cal
                     2. PEPPERS               | Included, 10 cal
                     3. ONIONS                | Included, 15 cal
                     4. TOMATOES              | Included, 10 cal
                     5. JALAPENOS             | Included, 5 cal
                     6. CUCUMBERS             | Included, 5 cal
                     7. PICKLES               | Included, 5 cal
                     8. GUACAMOLE             | Included, 40 cal
                     9. MUSHROOMS             | Included, 10 cal
                     ------------------------------------------------------------------------
                    
                     🥕 Add as many regular toppings as you like—they're all included! 🥕
                    
                     ========================================================================
                                   ❤️ THANK YOU FOR ORDERING ❤️
                     ========================================================================
                    """;

    public static final String SAUCE_MENU =
            """
                     ========================================================================
                                           🥫 SAUCE OPTIONS MENU 🥫
                     ========================================================================
                    
                                           🌶️ SAUCE UP YOUR SANDWICH 🌶️
                     ------------------------------------------------------------------------
                    
                     Sauce Options              | Included    | Calories
                     ------------------------------------------------------------------------
                     1. MAYO                    | Included    | 40 cal
                     2. MUSTARD                 | Included    | 30 cal
                     3. KETCHUP                 | Included    | 20 cal
                     4. RANCH                   | Included    | 50 cal
                     5. THOUSAND ISLANDS        | Included    | 60 cal
                     6. VINAIGRETTE             | Included    | 25 cal
                     ------------------------------------------------------------------------
                    
                     🍯 Enjoy any of these delicious sauces at no extra cost! 🍯
                    
                     ========================================================================
                                   ❤️ THANK YOU FOR ORDERING ❤️
                     ========================================================================
                    """;

    public static final String SANDWICH_MENU =
            """  
                 --------------------------------------------------------------------------------------------
                 🥇 **Signature Sandwich**                   |   💲 Price  |   🔥 Calories
                 --------------------------------------------------------------------------------------------
                 1. Customized Sandwich                      |    ?         |    ?
                 
                 2. 🥓 **BLT**                               |    $7.49     |   🔥 500 cal
                 🍞 8" white bread, crispy bacon, cheddar cheese, fresh lettuce, ripe tomato, 
                 and creamy ranch dressing. Served toasted to perfection.
                 
                 3. 🥩 **Philly Cheese Steak**               |    $8.99     |   🔥 650 cal
                 🔪 Thinly sliced steak, melted provolone, grilled onions, 
                 and bell peppers on a toasted hoagie roll. A hearty classic!
                 
                 4. 🍗 **Chicken Bacon Ranch Melt**          |    $8.49     |   🔥 700 cal
                 🥓 Grilled chicken, crispy bacon, cheddar cheese, 
                 and creamy ranch dressing on your choice of bread. Savor every bite!
        
                 5. 🇮🇹 **Italian Sub**                       |    $7.99     |   🔥 600 cal
                 🥖 A robust blend of ham, salami, pepperoni, provolone, lettuce, tomatoes, 
                 and onions on an Italian sub roll with Italian dressing.
        
                 6. 🥑 **Turkey Avocado Club**               |    $8.49     |   🔥 550 cal
                 🦃 Sliced turkey, fresh avocado, bacon, Swiss cheese, lettuce, 
                 and tomato with a hint of mayo on multigrain bread.
        
                 7. 🥬 **Veggie Delight**                    |    $6.99      |   🔥 450 cal
                 🥒 A fresh assortment of lettuce, cucumbers, tomatoes, peppers, onions, 
                 and avocado with your choice of cheese and dressing on wheat bread.
                 
                 0. Exit
                 --------------------------------------------------------------------------------------------
                 """;
}
