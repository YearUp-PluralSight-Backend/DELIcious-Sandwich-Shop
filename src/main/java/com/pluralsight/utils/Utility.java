package com.pluralsight.utils;

import java.util.Scanner;

public class Utility {


    private static final Scanner input = new Scanner(System.in);

    public static String getInputAsStringWithPrompt(String prompt) {
        System.out.println(prompt);
        String value = null;
        try {
            value = "";
            do {
                value = input.nextLine().trim();

            } while (value.isEmpty() && value.isBlank());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return value;
    }

}
