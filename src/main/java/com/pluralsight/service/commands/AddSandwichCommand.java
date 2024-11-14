package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.exceptions.InvalidIngredientException;
import com.pluralsight.service.Command;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
                    case 2 -> makePresetSandwich("BLT", 8, true, "WHITE", "BACON", "CHEDDAR", "LETTUCE", "TOMATOES", "RANCH");
                    case 3 -> makePresetSandwich("Philly Cheese Steak", 8, true, "WHITE", "STEAK", "PROVOLONE", "ONIONS", "PEPPERS", "RANCH");
                    case 4 -> makePresetSandwich("Chicken Bacon Ranch Melt", 12, true, "WHEAT", "CHICKEN", "BACON", "CHEDDAR", "RANCH");
                    case 5 -> makePresetSandwich("Italian Sub", 12, false, "WRAP", "SALAMI", "HAM", "PEPPERONI", "PROVOLONE", "LETTUCE", "TOMATOES", "ONIONS", "VINAIGRETTE");
                    case 6 -> makePresetSandwich("Turkey Avocado Club", 12, false, "RYE", "ROAST BEEF", "BACON", "SWISS", "LETTUCE", "TOMATOES", "AVOCADO", "MAYO");
                    case 7 -> makePresetSandwich("Veggie Delight", 12, false, "RYE", "LETTUCE", "TOMATOES", "CUCUMBERS", "PEPPERS", "ONIONS", "VINAIGRETTE");
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

    private void makeCustomizedSandwich() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(chooseSize(), chooseToasting());
        boolean breadAdded = false;
        boolean addMoreIngredients = true;

        while (addMoreIngredients) {
            String ingredientType = Utility.getInputAndReturnStringWithPrompt("Choose ingredient type (Bread, Meat, Cheese, Vegetable, Sauce): ");

            if (ingredientType.equalsIgnoreCase("BREAD") && breadAdded) {
                logger.info("You can only add one bread type. Choose another ingredient.");
                continue;
            }

            switch (ingredientType.toUpperCase()) {
                case "BREAD" -> {
                    Utility.print.accept(ConstantValue.BREAD_MENU);
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the ingredient (1. White, 2. Wheat, 3. Rye, 4. Wrap):");
                    builder.addBread(switch (ingredient) {
                        case 1 -> "WHITE";
                        case 2 -> "WHEAT";
                        case 3 -> "RYE";
                        case 4 -> "WRAP";
                        default -> throw new InvalidIngredientException("Invalid bread choice: " + ingredient);
                    });
                    breadAdded = true;
                }
                case "MEAT" -> {
                    Utility.print.accept(ConstantValue.MEAT_MENU);
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the meat option (1. Steak, 2. Ham, 3. Salami, 4. Roast Beef, 5. Chicken, 6. Bacon):");
                    builder.addMeat(switch (ingredient) {
                        case 1 -> "STEAK";
                        case 2 -> "HAM";
                        case 3 -> "SALAMI";
                        case 4 -> "ROAST BEEF";
                        case 5 -> "CHICKEN";
                        case 6 -> "BACON";
                        default -> throw new InvalidIngredientException("Invalid meat choice: " + ingredient);
                    });
                }
                case "CHEESE" -> {
                    Utility.print.accept(ConstantValue.CHEESE_MENU);
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the cheese option (1. American, 2. Provolone, 3. Cheddar, 4. Swiss):");
                    builder.addCheese(switch (ingredient) {
                        case 1 -> "AMERICAN";
                        case 2 -> "PROVOLONE";
                        case 3 -> "CHEDDAR";
                        case 4 -> "SWISS";
                        default -> throw new InvalidIngredientException("Invalid cheese choice: " + ingredient);
                    });
                }
                case "VEGETABLE" -> {
                    Utility.print.accept(ConstantValue.VEGETABLE_MENU);
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the vegetable option (1. Lettuce, 2. Peppers, 3. Onions, 4. Tomatoes, 5. Jalapenos, 6. Cucumbers, 7. Pickles, 8. Guacamole, 9. Mushrooms):");
                    builder.addVegetable(switch (ingredient) {
                        case 1 -> "LETTUCE";
                        case 2 -> "PEPPERS";
                        case 3 -> "ONIONS";
                        case 4 -> "TOMATOES";
                        case 5 -> "JALAPENOS";
                        case 6 -> "CUCUMBERS";
                        case 7 -> "PICKLES";
                        case 8 -> "GUACAMOLE";
                        case 9 -> "MUSHROOMS";
                        default -> throw new InvalidIngredientException("Invalid vegetable choice: " + ingredient);
                    });
                }
                case "SAUCE" -> {
                    Utility.print.accept(ConstantValue.SAUCE_MENU);
                    int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the sauce option (1. Mayo, 2. Mustard, 3. Ketchup, 4. Ranch, 5. Thousand Islands, 6. Vinaigrette):");
                    builder.addSauce(switch (ingredient) {
                        case 1 -> "MAYO";
                        case 2 -> "MUSTARD";
                        case 3 -> "KETCHUP";
                        case 4 -> "RANCH";
                        case 5 -> "THOUSAND ISLANDS";
                        case 6 -> "VINAIGRETTE";
                        default -> throw new InvalidIngredientException("Invalid sauce choice: " + ingredient);
                    });
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

    private void makePresetSandwich(String name, int size, boolean toasted, String... ingredients) throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(size, toasted);
        for (String ingredient : ingredients) {
            if (ConstantValue.breadInfoMap.containsKey(ingredient)) {
                builder.addBread(ingredient);
            } else if (ConstantValue.meatInfoMap.containsKey(ingredient)) {
                builder.addMeat(ingredient);
            } else if (ConstantValue.cheeseInfoMap.containsKey(ingredient)) {
                builder.addCheese(ingredient);
            } else if (ConstantValue.vegetableInfoMap.containsKey(ingredient)) {
                builder.addVegetable(ingredient);
            } else if (ConstantValue.sauceInfoMap.containsKey(ingredient)) {
                builder.addSauce(ingredient);
            } else {
                throw new InvalidIngredientException("Invalid ingredient: " + ingredient);
            }
        }
        this.sandwich = builder.build();
        sandwich.setName(name);
        order.getCart().add(sandwich);
        logger.info("You have added {} to your cart.", sandwich.getName());
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