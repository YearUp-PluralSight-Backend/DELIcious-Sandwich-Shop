package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.exceptions.InvalidIngredientException;
import com.pluralsight.service.Command;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.pluralsight.utils.ConstantValue.*;

public class AddSandwichCommand implements Command {

    private final Logger logger = LogManager.getLogger(AddSandwichCommand.class);
    private final Order order;
    private Sandwich sandwich;

    public AddSandwichCommand(Order order) {
        this.order = order;
    }

    private void run() {
        boolean runningApplication = true;
        while (runningApplication) {
            Utility.pauseAnimation(2);
            Utility.print.accept(ConstantValue.SANDWICH_MENU);
            int optionInput = Utility.getInputAndReturnIntegerWithPrompt("->");

            try {
                switch (optionInput) {
                    case 1 -> makeCustomizedSandwich();
                    case 2 -> makeBLT();
                    case 3 -> makePhillyCheeseSteak();
                    case 4 -> makeChickenBaconRanchMelt();
                    case 5 -> makeItalianSub();
                    case 6 -> makeTurkeyAvocadoClub();
                    case 7 -> makeVeggieDelight();
                    case 0 -> {
                        logger.info("Exiting the menu...");
                        runningApplication = false;
                    }
                    default -> logger.error("Invalid option. Please try again.");
                }
            } catch (NumberFormatException | InvalidIngredientException e) {
                logger.error("Please enter a valid number. You entered: {}", optionInput);
            }
        }

        if (sandwich != null) {
            logger.info("Your sandwich has been added to the cart! {}", sandwich);
        }
    }


    private void makeBLT() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(8, true);
        builder.addBread("WHITE")
                .addMeat("BACON")
                .addCheese("CHEDDAR")
                .addVegetable("LETTUCE")
                .addVegetable("TOMATOES")
                .addSauce("RANCH");
        this.sandwich = builder.build();
        sandwich.setName("BLT");
        order.getCart().add(sandwich);
        logger.info("You have added {} to your cart.",sandwich.getName());

    }
    private void makePhillyCheeseSteak() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(8, true);
        builder.addBread("WHITE")
                .addMeat("STEAK")
                .addCheese("PROVOLONE")
                .addVegetable("ONIONS")
                .addVegetable("PEPPERS")
                .addSauce("RANCH");
        this.sandwich = builder.build();
        sandwich.setName("Philly Cheese Steak");
        order.getCart().add(sandwich);
        logger.info("You have added {} to your cart.",sandwich.getName());

    }

    private void makeChickenBaconRanchMelt() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(12, true);
        builder.addBread("WHEAT")
                .addMeat("CHICKEN")
                .addMeat("BACON")
                .addCheese("CHEDDAR")
                .addSauce("RANCH");
        this.sandwich = builder.build();
        sandwich.setName("Chicken Bacon Ranch Melt");
        order.getCart().add(sandwich);
        logger.info("You have added {} to your cart.",sandwich.getName());

    }

    private void makeItalianSub() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(12, false);
        builder.addBread("WRAP")
                .addMeat("SALAMI")
                .addMeat("HAM")
                .addMeat("PEPPERONI")
                .addCheese("PROVOLONE")
                .addVegetable("LETTUCE")
                .addVegetable("TOMATOES")
                .addVegetable("ONIONS")
                .addSauce("VINAIGRETTE");
        this.sandwich = builder.build();
        sandwich.setName("Italian Sub");
        order.getCart().add(sandwich);
        logger.info("You have added {} to your cart.",sandwich.getName());

    }

    private void makeTurkeyAvocadoClub() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(12, false);
        builder.addBread("RYE")
                .addMeat("ROAST BEEF")
                .addMeat("BACON")
                .addCheese("SWISS")
                .addVegetable("LETTUCE")
                .addVegetable("TOMATOES")
                .addVegetable("AVOCADO")
                .addSauce("MAYO");
        this.sandwich = builder.build();
        sandwich.setName("Turkey Avocado Club");
        order.getCart().add(sandwich);
        logger.info("You have added {} to your cart.",sandwich.getName());

    }

    private void makeVeggieDelight() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(12, false);
        builder.addBread("RYE")
                .addVegetable("LETTUCE")
                .addVegetable("TOMATOES")
                .addVegetable("CUCUMBERS")
                .addVegetable("PEPPERS")
                .addVegetable("ONIONS")
                .addSauce("VINAIGRETTE");
        this.sandwich = builder.build();
        sandwich.setName("Veggie Delight");
        order.getCart().add(sandwich);
        logger.info("You have added {} to your cart.",sandwich.getName());
    }
    private void makeCustomizedSandwich() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(chooseSize(), chooseToasting());
        boolean breadAdded = false; // Track if bread has been added
        boolean addMoreIngredients = true;

        while (addMoreIngredients) {
            String ingredientType = Utility.getInputAndReturnStringWithPrompt("Choose ingredient type (Bread, Meat, Cheese, Vegetable, Sauce): ");

            if (ingredientType.equalsIgnoreCase("BREAD") && breadAdded) {
                logger.info("You can only add one bread type. Choose another ingredient.");
                continue;
            }

            switch (ingredientType.toUpperCase()) {
                case "BREAD" -> {
                    Utility.print.accept(BREAD_MENU); // Show the bread menu
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the ingredient (1. White, 2. Wheat, 3. Rye, 4. Wrap):");
                    switch (ingredient) {
                        case 1 -> builder.addBread("WHITE");
                        case 2 -> builder.addBread("WHEAT");
                        case 3 -> builder.addBread("RYE");
                        case 4 -> builder.addBread("WRAP");
                        default -> logger.error("Invalid bread choice: {}", ingredient);
                    }
                    breadAdded = true;
                }
                case "MEAT" -> {
                    Utility.print.accept(MEAT_MENU); // Show the meat menu
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the meat option (1. Steak, 2. Ham, 3. Salami, 4. Roast Beef, 5. Chicken, 6. Bacon):");
                    switch (ingredient) {
                        case 1 -> builder.addMeat("STEAK");
                        case 2 -> builder.addMeat("HAM");
                        case 3 -> builder.addMeat("SALAMI");
                        case 4 -> builder.addMeat("ROAST BEEF");
                        case 5 -> builder.addMeat("CHICKEN");
                        case 6 -> builder.addMeat("BACON");
                        default -> logger.error("Invalid meat choice: {}", ingredient);
                    }
                }
                case "CHEESE" -> {
                    Utility.print.accept(CHEESE_MENU); // Show the cheese menu
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the cheese option (1. American, 2. Provolone, 3. Cheddar, 4. Swiss):");
                    switch (ingredient) {
                        case 1 -> builder.addCheese("AMERICAN");
                        case 2 -> builder.addCheese("PROVOLONE");
                        case 3 -> builder.addCheese("CHEDDAR");
                        case 4 -> builder.addCheese("SWISS");
                        default -> logger.error("Invalid cheese choice: {}", ingredient);
                    }
                }
                case "VEGETABLE" -> {
                    Utility.print.accept(VEGETABLE_MENU); // Show the vegetable menu
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the vegetable option (1. Lettuce, 2. Peppers, 3. Onions, 4. Tomatoes, 5. Jalapenos, 6. Cucumbers, 7. Pickles, 8. Guacamole, 9. Mushrooms):");
                    switch (ingredient) {
                        case 1 -> builder.addVegetable("LETTUCE");
                        case 2 -> builder.addVegetable("PEPPERS");
                        case 3 -> builder.addVegetable("ONIONS");
                        case 4 -> builder.addVegetable("TOMATOES");
                        case 5 -> builder.addVegetable("JALAPENOS");
                        case 6 -> builder.addVegetable("CUCUMBERS");
                        case 7 -> builder.addVegetable("PICKLES");
                        case 8 -> builder.addVegetable("GUACAMOLE");
                        case 9 -> builder.addVegetable("MUSHROOMS");
                        default -> logger.error("Invalid vegetable choice: {}", ingredient);
                    }
                }
                case "SAUCE" -> {
                    Utility.print.accept(SAUCE_MENU); // Show the sauce menu
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the sauce option (1. Mayo, 2. Mustard, 3. Ketchup, 4. Ranch, 5. Thousand Islands, 6. Vinaigrette):");
                    switch (ingredient) {
                        case 1 -> builder.addSauce("MAYO");
                        case 2 -> builder.addSauce("MUSTARD");
                        case 3 -> builder.addSauce("KETCHUP");
                        case 4 -> builder.addSauce("RANCH");
                        case 5 -> builder.addSauce("THOUSAND ISLANDS");
                        case 6 -> builder.addSauce("VINAIGRETTE");
                        default -> logger.error("Invalid sauce choice: {}", ingredient);
                    }
                }
                default -> logger.error("Unknown ingredient type: {}", ingredientType);
            }

            addMoreIngredients = Utility.getInputAndReturnBooleanWithPrompt("Would you like to add another ingredient?");
        }

        this.sandwich = builder.build();
        sandwich.setName("Custom Sandwich");
        order.getCart().add(sandwich);
        logger.info("Custom sandwich added to order: {}", sandwich);
    }

    private int chooseSize() {
        return Utility.getInputAndReturnIntegerWithPrompt("Choose sandwich size: 4 (Small), 8 (Medium), or 12 (Large)");
    }

    private boolean chooseToasting() {
        return Utility.getInputAndReturnBooleanWithPrompt("Would you like the sandwich toasted?");
    }

    @Override
    public void execute() {
        run();
    }
}