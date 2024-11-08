package com.pluralsight.utils;

import com.pluralsight.gui.HomeScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.function.Consumer;

public class Utility {

    private static final Scanner input = new Scanner(System.in);
    private  static final Logger logger = LogManager.getLogger(Utility.class);


    public static Consumer<String> print = System.out::print;
    public static String getInputAsStringWithPrompt(String prompt) {
        System.out.println(prompt);
        String value = null;
        try {
            value = "";
            do {
                value = input.nextLine().trim();

            } while (value.isEmpty() && value.isBlank());
        } catch (Exception e) {
            logger.error("Input Invalid!!!!!{e}", e);
        }

        return value;
    }

}
