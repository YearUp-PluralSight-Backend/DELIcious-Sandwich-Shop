package com.pluralsight.utils;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Utility class containing constant values used throughout the application.
 */
public class ConstantValue {

    /**
     * Formatter for date and time in the pattern "yyyyMMdd-HHmmss".
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss", Locale.ENGLISH);

    /**
     * Record representing ingredient information with prices for different sizes and calorie count.
     */
    public record IngredientInfo(double price4Inch, double price8Inch, double price12Inch, int calories) {
    }

    /**
     * Map containing information about different types of cheese.
     */
    public static final Map<String, IngredientInfo> cheeseInfoMap = new HashMap<>();

    /**
     * Map containing information about different types of meat.
     */
    public static final Map<String, IngredientInfo> meatInfoMap = new HashMap<>();

    /**
     * Map containing information about different types of vegetables.
     */
    public static final Map<String, IngredientInfo> vegetableInfoMap = new HashMap<>();

    /**
     * Map containing information about different types of sauces.
     */
    public static final Map<String, IngredientInfo> sauceInfoMap = new HashMap<>();

    /**
     * Map containing information about different types of bread.
     */
    public static final Map<String, IngredientInfo> breadInfoMap = new HashMap<>();

    static {
        // Initialize sauce information
        sauceInfoMap.put("MAYO", new IngredientInfo(0, 0, 0, 40));
        sauceInfoMap.put("MUSTARD", new IngredientInfo(0, 0, 0, 30));
        sauceInfoMap.put("KETCHUP", new IngredientInfo(0, 0, 0, 20));
        sauceInfoMap.put("RANCH", new IngredientInfo(0, 0, 0, 50));
        sauceInfoMap.put("THOUSAND_ISLANDS", new IngredientInfo(0, 0, 0, 60));
        sauceInfoMap.put("VINAIGRETTE", new IngredientInfo(0, 0, 0, 25));

        // Initialize vegetable information
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

        // Initialize bread information
        breadInfoMap.put("WHITE", new IngredientInfo(5.50, 7.00, 8.50, 200));
        breadInfoMap.put("WHEAT", new IngredientInfo(5.50, 7.00, 8.50, 300));
        breadInfoMap.put("RYE", new IngredientInfo(5.50, 7.00, 8.50, 200));
        breadInfoMap.put("WRAP", new IngredientInfo(5.50, 7.00, 8.50, 250));

        // Initialize meat information
        meatInfoMap.put("STEAK", new IngredientInfo(1.00, 2.00, 3.00, 120));
        meatInfoMap.put("HAM", new IngredientInfo(1.00, 2.00, 3.00, 90));
        meatInfoMap.put("SALAMI", new IngredientInfo(1.00, 2.00, 3.00, 110));
        meatInfoMap.put("ROAST BEEF", new IngredientInfo(1.00, 2.00, 3.00, 130));
        meatInfoMap.put("CHICKEN", new IngredientInfo(1.00, 2.00, 3.00, 80));
        meatInfoMap.put("PEPPERONI", new IngredientInfo(1.00, 2.00, 3.00, 100));
        meatInfoMap.put("BACON", new IngredientInfo(1.00, 2.00, 3.00, 100));
        meatInfoMap.put("EXTRA_MEAT", new IngredientInfo(0.50, 1.00, 1.50, 50));

        // Initialize cheese information
        cheeseInfoMap.put("AMERICAN", new IngredientInfo(0.75, 1.50, 2.25, 50));
        cheeseInfoMap.put("PROVOLONE", new IngredientInfo(0.75, 1.50, 2.25, 60));
        cheeseInfoMap.put("CHEDDAR", new IngredientInfo(0.75, 1.50, 2.25, 70));
        cheeseInfoMap.put("SWISS", new IngredientInfo(0.75, 1.50, 2.25, 80));
        cheeseInfoMap.put("EXTRA_CHEESE", new IngredientInfo(0.30, 0.60, 0.90, 20));
    }

    /**
     * Menu for ordering items.
     */
    public static final String ORDER_MENU =
            """
                    ╔═════════════════════════════════╗
                    ║  ORDER SCREEN                   ║
                    ╠═════════════════════════════════╣
                    ║  Select an option:              ║
                    ║═════════════════════════════════║
                    ║  1) Add Chips                   ║
                    ║  2) Add Drink                   ║
                    ║  3) Add Sandwich                ║
                    ║  4) Checkout                    ║
                    ║  5) Review                      ║
                    ║  0) Cancel Order/ Home Screen   ║
                    ╚═════════════════════════════════╝
                    """;

    /**
     * Menu for the home screen.
     */
    public static final String HOME_MENU =
            """
                    ╔═════════════════════════════════╗
                    ║  Home SCREEN                    ║
                    ╠═════════════════════════════════╣
                    ║  1) New Order                   ║
                    ║  2) Exit                        ║
                    ╚═════════════════════════════════╝
                    """;

    /**
     * Menu for selecting chips.
     */
    public static final String CHIPS_MENU =
            """
                    ╔══════════════════════════════════════════════════╗
                    ║  Chips Menu                                      ║
                    ╠══════════════════════════════════════════════════╣
                    ║  1) Lays                                         ║
                    ║  2) Pringles                                     ║
                    ║  3) Doritos                                      ║
                    ║  4) Ruffles                                      ║
                    ╚══════════════════════════════════════════════════╝
                    """;

    /**
     * Menu for selecting drinks.
     */
    public static final String DRINK_MENU =
            """
                    ╔══════════════════════════════════════════════════╗
                    ║  Chips Menu                                      ║
                    ╠══════════════════════════════════════════════════╣
                    ║  1) Coca-Cola                                    ║
                    ║  2) Pepsi                                        ║
                    ║  3) Sprite                                       ║
                    ║  4) Fanta                                        ║
                    ║  5) Water                                        ║
                    ╚══════════════════════════════════════════════════╝
                    """;

    /**
     * Menu for selecting bread types.
     */
    public static final String BREAD_MENU =
            """
                    ╔═════════════════════════════════════════════════════════════════════════════╗
                    ║ Bread Menu                                                                  ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ Bread Types         | 4" (Small)       | 8" (Medium)       | 12" (Large)    ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 1. WHITE            | $5.50, 200 cal   | $7.00, 250 cal    | $8.50, 300 cal ║
                    ║ 2. WHEAT            | $5.50, 300 cal   | $7.00, 350 cal    | $8.50, 400 cal ║
                    ║ 3. RYE              | $5.50, 200 cal   | $7.00, 250 cal    | $8.50, 300 cal ║
                    ║ 4. WRAP             | $5.50, 250 cal   | $7.00, 300 cal    | $8.50, 350 cal ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    """;

    /**
     * Menu for selecting meat types.
     */
    public static final String MEAT_MENU =
            """
                    ╔═════════════════════════════════════════════════════════════════════════════╗
                    ║ Protein Menu                                                                ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ Meat Toppings      | 4" (Small)       | 8" (Medium)       | 12" (Large)     ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 1. STEAK           | $1.00, 120 cal   | $2.00, 170 cal    | $3.00, 220 cal  ║
                    ║ 2. HAM             | $1.00, 90 cal    | $2.00, 140 cal    | $3.00, 190 cal  ║
                    ║ 3. SALAMI          | $1.00, 110 cal   | $2.00, 160 cal    | $3.00, 210 cal  ║
                    ║ 4. ROAST BEEF      | $1.00, 130 cal   | $2.00, 180 cal    | $3.00, 230 cal  ║
                    ║ 5. CHICKEN         | $1.00, 80 cal    | $2.00, 130 cal    | $3.00, 180 cal  ║
                    ║ 6. BACON           | $1.00, 100 cal   | $2.00, 150 cal    | $3.00, 200 cal  ║
                    ║ 7. EXTRA_MEAT      | $0.50, 50 cal    | $1.00, 100 cal    | $1.50, 150 cal  ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    """;

    /**
     * Menu for selecting cheese types.
     */
    public static final String CHEESE_MENU =
            """
                    ╔═════════════════════════════════════════════════════════════════════════════╗
                    ║ Cheese Menu                                                                 ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ Cheese Toppings   | 4" (Small)       | 8" (Medium)       | 12" (Large)      ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 1. AMERICAN        | $0.75, 50 cal    | $1.50, 100 cal    | $2.25, 150 cal  ║
                    ║ 2. PROVOLONE       | $0.75, 60 cal    | $1.50, 110 cal    | $2.25, 160 cal  ║
                    ║ 3. CHEDDAR         | $0.75, 70 cal    | $1.50, 120 cal    | $2.25, 170 cal  ║
                    ║ 4. SWISS           | $0.75, 80 cal    | $1.50, 130 cal    | $2.25, 180 cal  ║
                    ║ 5. EXTRA_CHEESE    | $0.30, 20 cal    | $0.60, 40 cal    | $0.90, 60 cal    ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    """;

    /**
     * Menu for selecting vegetable types.
     */
    public static final String VEGETABLE_MENU =
            """
                    ╔═════════════════════════════════════════════════════════════════════════════╗
                    ║ Vegetable Menu                                                              ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ Regular Toppings         | Included, Calories                               ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 1. LETTUCE               | Included, 5 cal                                  ║
                    ║ 2. PEPPERS               | Included, 10 cal                                 ║
                    ║ 3. ONIONS                | Included, 15 cal                                 ║
                    ║ 4. TOMATOES              | Included, 10 cal                                 ║
                    ║ 5. JALAPENOS             | Included, 5 cal                                  ║
                    ║ 6. CUCUMBERS             | Included, 5 cal                                  ║
                    ║ 7. PICKLES               | Included, 5 cal                                  ║
                    ║ 8. GUACAMOLE             | Included, 40 cal                                 ║
                    ║ 9. MUSHROOMS             | Included, 10 cal                                 ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    """;

    /**
     * Menu for selecting sauce types.
     */
    public static final String SAUCE_MENU =
            """
                   ╔═════════════════════════════════════════════════════════════════════════════╗
                   ║  Sauce Menu                                                                 ║
                   ╠═════════════════════════════════════════════════════════════════════════════╣
                   ║  Sauce Options              | Included    | Calories                        ║
                   ╠═════════════════════════════════════════════════════════════════════════════╣
                   ║  1. MAYO                    | Included    | 40 cal                          ║
                   ║  2. MUSTARD                 | Included    | 30 cal                          ║
                   ║  3. KETCHUP                 | Included    | 20 cal                          ║
                   ║  4. RANCH                   | Included    | 50 cal                          ║
                   ║  5. THOUSAND ISLANDS        | Included    | 60 cal                          ║
                   ║  6. VINAIGRETTE             | Included    | 25 cal                          ║
                   ╚═════════════════════════════════════════════════════════════════════════════╝
                    """;

    /**
     * Menu for selecting sandwich options.
     */
    public static final String SANDWICH_MENU =
            """  
                    ╔═════════════════════════════════════════════════════════════════════════════╗
                    ║ Sandwich Menu                                                               ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ Sandwich Option                  |    Price  |   Calories                   ║
                    ║-----------------------------------------------------------------------------║
                    ║ 1. Customized Sandwich           |    ?         |    ?                      ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 2.  BLT                          |    $10.5     |    522.0 cal              ║
                    ║     - 8" white bread, crispy bacon, cheddar cheese, fresh lettuce,          ║
                    ║      ripe tomato, and creamy ranch dressing. Served toasted to perfection.  ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 3. Philly Cheese Steak           |    $10.5     |    546.0 cal              ║
                    ║     - 8" Thinly sliced steak, melted provolone, grilled onions,             ║
                    ║       and bell peppers on a toasted hoagie roll. A hearty classic!          ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 4. Chicken Bacon Ranch Melt      |    $16.75     |   840.0 cal              ║
                    ║     - 12" Grilled chicken, crispy bacon, cheddar cheese,                    ║
                    ║       and creamy ranch dressing on your choice of bread. Savor every bite!  ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 5. Italian Sub                   |    $19.75     |   931.0 cal              ║
                    ║     - 12" A robust blend of ham, salami, pepperoni, provolone, lettuce,     ║
                    ║       tomatoes, and onions on an Italian sub roll with Italian dressing.    ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 6.  Turkey Avocado Club          |    $16.75     |    819.0 cal             ║
                    ║     - 12" Sliced turkey, fresh avocado, bacon, Swiss cheese, lettuce,       ║
                    ║        and tomato with a hint of mayo on multigrain bread.                  ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 7.  Veggie Delight               |    $8.5      |    378.0 cal              ║
                    ║     - A fresh assortment of lettuce, cucumbers, tomatoes, peppers, onions,  ║
                    ║       and avocado with your choice of cheese and dressing on wheat bread.   ║
                    ╠═════════════════════════════════════════════════════════════════════════════╣
                    ║ 0. Exit                                                                     ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    """;
}