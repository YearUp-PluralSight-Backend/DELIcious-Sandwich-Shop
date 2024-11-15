package com.pluralsight.utils;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.entity.sandwich.Size;
import com.pluralsight.entity.sandwich.toppings.SandwichIngredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Utility class providing various helper methods and constants for the application.
 */
public class Utility {

    private static final Scanner input = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Utility.class);
    public static final Consumer<String> print = System.out::print;
    public static final Consumer<String> println = System.out::println;

    /**
     * Displays a loading animation for a specified number of seconds.
     *
     * @param seconds the number of seconds for the loading animation
     */
    public static void loadingAnimation(int seconds) {
        try {
            print.accept("Loading");
            for (int i = 0; i < seconds; i++) {
                print.accept(".");
                Thread.sleep(500);
            }
            println.accept("");
        } catch (InterruptedException e) {
            logger.error("Fail to load the animation!", e);
        }
    }

    /**
     * Pauses the execution for a specified number of seconds.
     *
     * @param seconds the number of seconds to pause
     */
    public static void pauseAnimation(int seconds) {
        try {
            for (int i = 0; i < seconds; i++) {
                Thread.sleep(500);
            }
            println.accept("");
        } catch (InterruptedException e) {
            logger.error("Fail to load the animation!", e);
        }
    }

    /**
     * Prompts the user for input and returns the input as a string.
     *
     * @param prompt the prompt message to display
     * @return the user input as a string
     */
    public static String getInputAndReturnStringWithPrompt(String prompt) {
        print.accept(prompt);
        String value = "";

        while (value.isEmpty() || value.isBlank()) {
            try {
                value = input.nextLine().trim().toUpperCase(Locale.ROOT);
                if (value.isEmpty() || value.isBlank()) {
                    logger.error("Input cannot be empty or blank. Please enter a valid value.");
                }
            } catch (Exception e) {
                logger.error("Input invalid: {}", e.getMessage(), e);
                println.accept("An error occurred while reading input. Please try again.\n->");
            }
        }

        return value;
    }

    /**
     * Prompts the user for input and returns the input as an integer.
     *
     * @param prompt the prompt message to display
     * @return the user input as an integer
     */
    public static int getInputAndReturnIntegerWithPrompt(String prompt) {
        print.accept(prompt);
        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            String value = input.nextLine().trim().toUpperCase(Locale.ROOT);

            if (value.isEmpty() || value.isBlank()) {
                println.accept("Input cannot be empty. Please enter a valid number.");
                continue;
            }

            try {
                option = Integer.parseInt(value);
                validInput = true;
            } catch (NumberFormatException e) {
                logger.error("Invalid input: '{}' is not a valid integer.", value, e);
                println.accept("Invalid input! Please enter a valid integer.\n->");
            }
        }

        return option;
    }

    /**
     * Prompts the user for input and returns the input as a boolean.
     *
     * @param prompt the prompt message to display
     * @return the user input as a boolean
     */
    public static boolean getInputAndReturnBooleanWithPrompt(String prompt) {
        String option = getInputAndReturnStringWithPrompt(prompt).toUpperCase().trim();
        if (option.equalsIgnoreCase("Yes") || option.equalsIgnoreCase("Y")) {
            return true;
        } else if (option.equalsIgnoreCase("No") || option.equalsIgnoreCase("N")) {
            return false;
        } else {
            logger.error("Wrong input!");
        }
        return false;
    }

    /**
     * Checks if the entered drink size is valid.
     *
     * @param size the size to check
     * @return true if the size is valid, false otherwise
     */
    public static boolean validSize(String size) {
        // Check if the drinkSize matches a valid size (SMALL, MEDIUM, LARGE)
        return Arrays.stream(Size.values())
                .map(Size::name)
                .anyMatch(size::equalsIgnoreCase);
    }

    /**
     * Formats the header for the order receipt.
     *
     * @param order the order to format
     * @return the formatted header
     */
    public static String headerFormat(Order order) {
        return new StringBuffer().append("║  Year Up Sandwich Shop\t\t\n")
                .append("║  ").append(order.getShop().getShopAddress().getStreet()).append("\t\t\n")
                .append("║  ").append(order.getShop().getShopAddress().getCity()).append(", ")
                .append(order.getShop().getShopAddress().getState()).append(", ")
                .append(order.getShop().getShopAddress().getZipCode()).append("\t\t\n")
                .append("║  ").append(order.getShop().getPhoneNumber()).append("\t\t\n")
                .append("║  Order Number: ").append(order.getOrderNumber()).append("\t\t\n")
                .append("║  \n").toString();
    }

    /**
     * Formats the receipt for the order.
     *
     * @param order the order to format
     * @return the formatted receipt
     */
    public static String reviewOrder(Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("═══".repeat(20)).append("\n")
                .append("║  Order Summary\n")
                .append("║  ").append(order.getShop().getStaffName()).append("\t\t\t\t\t\t")
                .append(order.getCreateTime().toLocalDate()).append("\t\t\t\t").append(order.getCreateTime().toLocalTime()).append("\n")
                .append("║  ").append("═══".repeat(20)).append("\n")
                .append(String.format("\t%-20s %-10s\t\t%s\n", "Ingredient", "Price", "Calories"))
                .append("║  ").append("═══".repeat(20)).append("\n");

        // Loop through the cart to print items
        for (int i = 1; i <= order.getCart().size(); i++) {
            Object item = order.getCart().get(i - 1);
            if (item instanceof Sandwich) {
                Sandwich sandwich = (Sandwich) item;
                stringBuilder.append("║  ").append(i).append(") ").append(sandwich.getName())
                        .append(" - ").append(sandwich.getSize()).append("\n");

                // Bread details
                appendIngredientDetails(stringBuilder, "   -", sandwich.getBreadType().getName(),
                        sandwich.getBreadType().getPrice(), sandwich.getBreadType().getCalories());

                // Ingredients details
                for (SandwichIngredient ingredient : sandwich.getIngredients()) {
                    appendIngredientDetails(stringBuilder, "   -", ingredient.getName(), ingredient.getPrice(), ingredient.getCalories());
                }

            } else if (item instanceof Drink) {
                Drink drink = (Drink) item;
                stringBuilder.append("║  ").append(i).append(") ").append(drink.getName())
                        .append("\t\t\t").append(String.format("%.2f", drink.getPrice()))
                        .append("\t\t\t").append(drink.getCalories()).append("\n");

            } else if (item instanceof Chips) {
                Chips chips = (Chips) item;
                stringBuilder.append("║  ").append(i).append(") ").append(chips.getName())
                        .append("\t\t").append(String.format("%.2f", chips.getPrice()))
                        .append("\t\t").append(chips.getCalories()).append("\n");
            }
        }
        // Summary (Total Cost and Calories)
        stringBuilder.append("║  ").append("═══".repeat(20)).append("\n")
                .append("║  ").append("Total Cost: ").append(String.format("%.2f", order.getTotalPrice())).append("\n")
                .append("║  ").append("Total Calories: ").append(order.getTotalCalories()).append("\n")
                .append("║  ").append("═══".repeat(20)).append("\n");
        return stringBuilder.toString();
    }

    /**
     * Appends ingredient details to the string builder.
     *
     * @param stringBuilder the string builder to append to
     * @param prefix the prefix for the ingredient details
     * @param name the name of the ingredient
     * @param price the price of the ingredient
     * @param calories the calories of the ingredient
     */
    private static void appendIngredientDetails(StringBuilder stringBuilder, String prefix, String name, double price, double calories) {
        stringBuilder.append("║  ").append(prefix)
                .append(String.format("%-20s", name)) // Align name
                .append(String.format("%.2f", price)) // Format price to 2 decimals
                .append("\t\t").append(String.format("%.1f", calories)) // Format calories to 1 decimal
                .append("\n");
    }

    /**
     * Formats the footer for the order receipt.
     *
     * @return the formatted footer
     */
    public static String footerFormat() {
        return new StringBuffer().append("║  Thank you for choosing Year Up Sandwich Shop!\n")
                .append("║  ").append("═══".repeat(20)).append("\n")
                .append("║  ").append("© 2024 Year Up IP LLC. Subway® is a Registered Trademark of Year Up Sandwich Shop IP LLC\n")
                .append("║  ").append("═══".repeat(20)).append("\n")
                .append("║  ").append("Developed by Yiming Gao\n")
                .append("║  ").append("═══".repeat(20)).append("\n").toString();
    }

    /**
     * Formats the entire receipt for the order.
     *
     * @param order the order to format
     * @return the formatted receipt
     */
    public static String formatReceipt(Order order) {
        return headerFormat(order) + reviewOrder(order) + footerFormat();
    }

    /**
     * Closes the scanner.
     */
    public static void closeScanner() {
        input.close();
    }
}