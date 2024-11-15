package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.exceptions.InvalidIngredientException;
import com.pluralsight.service.Command;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to add a sandwich to an order.
 */
public class AddSandwichCommand implements Command {

    private final Logger logger = LogManager.getLogger(AddSandwichCommand.class);
    private final Order order;
    private Sandwich sandwich;

    /**
     * Constructor for AddSandwichCommand.
     *
     * @param order the order to which the sandwich will be added
     */
    public AddSandwichCommand(Order order) {
        this.order = order;
    }

    /**
     * Runs the command to add a sandwich to the order.
     */
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
                Utility.println.accept("Please enter a valid number. (1-7, 0 to exit)");
                logger.error("Please enter a valid number. You entered: {}", optionInput);
            }
        }
    }

    /**
     * Creates a customized sandwich based on user input.
     *
     * @throws InvalidIngredientException if an invalid ingredient is chosen
     */
    private void makeCustomizedSandwich() throws InvalidIngredientException {
        Sandwich.Builder builder = new Sandwich.Builder(chooseSize(), chooseToasting());
        boolean breadAdded = false;
        boolean addMoreIngredients = true;

        while (addMoreIngredients) {
            int ingredientType = Utility.getInputAndReturnIntegerWithPrompt("Choose ingredient type (1. Bread, 2. Meat, 3. Cheese, 4. Vegetable, 5. Sauce): ");

            switch (ingredientType) {
                case 1 -> {
                    if (breadAdded) {
                        logger.info("You can only add one bread type. Choose another ingredient.");
                        Utility.println.accept("You can only add one bread type. Choose another ingredient.");
                        continue;
                    }
                    boolean validBread = false;
                    while (!validBread) {
                        try {
                            Utility.print.accept(ConstantValue.BREAD_MENU);
                            int ingredient = Utility.getInputAndReturnIntegerWithPrompt("->");
                            builder.addBread(switch (ingredient) {
                                case 1 -> "WHITE";
                                case 2 -> "WHEAT";
                                case 3 -> "RYE";
                                case 4 -> "WRAP";
                                default -> throw new InvalidIngredientException("Invalid bread choice: " + ingredient);
                            });
                            breadAdded = true;
                            validBread = true;
                        } catch (InvalidIngredientException e) {
                            Utility.println.accept("Invalid bread choice. Please try again.");
                            logger.warn("Invalid bread choice. Please try again.");
                        }
                    }
                }
                case 2 -> {
                    boolean validMeat = false;
                    while (!validMeat) {
                        try {
                            Utility.print.accept(ConstantValue.MEAT_MENU);
                            int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the meat option:");
                            builder.addMeat(switch (ingredient) {
                                case 1 -> "STEAK";
                                case 2 -> "HAM";
                                case 3 -> "SALAMI";
                                case 4 -> "ROAST BEEF";
                                case 5 -> "CHICKEN";
                                case 6 -> "BACON";
                                default -> throw new InvalidIngredientException("Invalid meat choice: " + ingredient);
                            });
                            validMeat = true;
                        } catch (InvalidIngredientException e) {
                            Utility.println.accept("Invalid meat choice. Please try again.");
                            logger.warn("Invalid meat choice. Please try again.");
                        }
                    }
                }
                case 3 -> {
                    boolean validCheese = false;
                    while (!validCheese) {
                        try {
                            Utility.print.accept(ConstantValue.CHEESE_MENU);
                            int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the cheese option:");
                            builder.addCheese(switch (ingredient) {
                                case 1 -> "AMERICAN";
                                case 2 -> "PROVOLONE";
                                case 3 -> "CHEDDAR";
                                case 4 -> "SWISS";
                                default -> throw new InvalidIngredientException("Invalid cheese choice: " + ingredient);
                            });
                            validCheese = true;
                        } catch (InvalidIngredientException e) {
                            Utility.println.accept("Invalid cheese choice. Please try again.");
                            logger.warn("Invalid cheese choice. Please try again.");
                        }
                    }
                }
                case 4 -> {
                    boolean validVegetable = false;
                    while (!validVegetable) {
                        try {
                            Utility.print.accept(ConstantValue.VEGETABLE_MENU);
                            int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the vegetable option:");
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
                            validVegetable = true;
                        } catch (InvalidIngredientException e) {
                            Utility.println.accept("Invalid vegetable choice. Please try again.");
                            logger.warn("Invalid vegetable choice. Please try again.");
                        }
                    }
                }
                case 5 -> {
                    boolean validSauce = false;
                    while (!validSauce) {
                        try {
                            Utility.print.accept(ConstantValue.SAUCE_MENU);
                            int ingredient = Utility.getInputAndReturnIntegerWithPrompt("Enter the sauce option:");
                            builder.addSauce(switch (ingredient) {
                                case 1 -> "MAYO";
                                case 2 -> "MUSTARD";
                                case 3 -> "KETCHUP";
                                case 4 -> "RANCH";
                                case 5 -> "THOUSAND ISLANDS";
                                case 6 -> "VINAIGRETTE";
                                default -> throw new InvalidIngredientException("Invalid sauce choice: " + ingredient);
                            });
                            validSauce = true;
                        } catch (InvalidIngredientException e) {
                            Utility.println.accept("Invalid sauce choice. Please try again.");
                            logger.warn("Invalid sauce choice. Please try again.");
                        }
                    }
                }
                default -> logger.error("Unknown ingredient type: {}", ingredientType);
            }

            addMoreIngredients = Utility.getInputAndReturnBooleanWithPrompt("Would you like to add another ingredient? (Y/N or Yes/No)");
        }

        this.sandwich = builder.build();
        sandwich.setName("Custom Sandwich");
        logger.info("Your sandwich has been added to the cart! {}", sandwich.getName());
        order.getCart().add(sandwich);
        Utility.println.accept("Your sandwich has been added to the cart!");
        Utility.println.accept(sandwich.toString());

    }

    /**
     * Creates a preset sandwich with predefined ingredients.
     *
     * @param name the name of the sandwich
     * @param size the size of the sandwich
     * @param toasted whether the sandwich is toasted
     * @param ingredients the ingredients of the sandwich
     * @throws InvalidIngredientException if an invalid ingredient is chosen
     */
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
        logger.info("Your sandwich has been added to the cart! {}", sandwich.getName());
        Utility.println.accept("Your sandwich has been added to the cart!");
        Utility.println.accept(sandwich.toString());
    }

    /**
     * Prompts the user to choose the size of the sandwich.
     *
     * @return the chosen size
     */
    private int chooseSize() {
        int size = 8;
        boolean validSize = false;
        while (!validSize) {
            try {
                size = Utility.getInputAndReturnIntegerWithPrompt("Choose sandwich size: 4 (Small), 8 (Medium), or 12 (Large)");
                if (size == 4 || size == 8 || size == 12) {
                    validSize = true;
                } else {
                    Utility.println.accept("Invalid size choice. Please choose 4, 8, or 12.");
                    logger.warn("Invalid size choice. Please choose 4, 8, or 12.");
                }
            } catch (Exception e) {
                logger.warn("Invalid size choice.\n->");
            }
        }

        return size;
    }

    /**
     * Prompts the user to choose whether the sandwich should be toasted.
     *
     * @return true if the sandwich should be toasted, false otherwise
     */
    private boolean chooseToasting() {
        return Utility.getInputAndReturnBooleanWithPrompt("Would you like the sandwich toasted?");
    }

    /**
     * Executes the command to add a sandwich to the order.
     */
    @Override
    public void execute() {
        run();
    }
}