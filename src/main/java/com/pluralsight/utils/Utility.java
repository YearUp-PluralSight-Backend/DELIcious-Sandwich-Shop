package com.pluralsight.utils;

import com.pluralsight.entity.sandwich.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

public class Utility {

    private static final Scanner input = new Scanner(System.in);
    private  static final Logger logger = LogManager.getLogger(Utility.class);
    public static final Consumer<String> print = System.out::print;
    public static final Consumer<String> println = System.out::println;

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
                println.accept("An error occurred while reading input. Please try again.");
            }
        }

        return value;
    }

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
                println.accept("Invalid input! Please enter a valid integer.");
            }
        }

        return option;
    }

    public static boolean getInputAndReturnBooleanWithPrompt(String prompt) {
        String option = getInputAndReturnStringWithPrompt(prompt).toUpperCase().trim();
        if (option.equalsIgnoreCase("Yes")) {
            return true;
        } else if (option.equalsIgnoreCase("No")) {
            return false;
        } else {
            logger.error("Wrong input!");
        }
        return false;
    }

    /**
     * Checks if the entered drink size is valid.
     *
     * @return true if the size is valid, false otherwise
     */
    public static boolean validSize(String size) {
        // Check if the drinkSize matches a valid size (SMALL, MEDIUM, LARGE)
        return Arrays.stream(Size.values())
                .map(Size::name)
                .anyMatch(size::equalsIgnoreCase);
    }
}
