package com.pluralsight.entity.sandwich;

import com.pluralsight.entity.Food;
import com.pluralsight.entity.Purchasable;
import com.pluralsight.entity.sandwich.toppings.*;
import com.pluralsight.exceptions.InvalidIngredientException;
import com.pluralsight.utils.DefaultValueService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.pluralsight.utils.ConstantValue.*;

/**
 * Represents a Sandwich which is a type of Food and can be purchased.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Sandwich extends Food implements Purchasable {

    private static final Logger logger = LogManager.getLogger(Sandwich.class);
    private Bread breadType;
    @NotNull
    private boolean isToast;
    @NotNull
    private List<SandwichIngredient> ingredients;

    /**
     * Constructs a Sandwich using the provided Builder.
     *
     * @param builder the Builder used to construct the Sandwich
     */
    private Sandwich(Builder builder) {
        this.setSize(builder.size);
        this.breadType = builder.breadType;
        this.ingredients = builder.ingredients;
        this.isToast = builder.isToast;
        super.setPrice(builder.totalPrice);
        super.setCalories(builder.totalCalories);
    }

    /**
     * Builder class for constructing a Sandwich.
     */
    public static class Builder {
        private final Size size;
        private final boolean isToast;
        private Bread breadType;
        private final List<SandwichIngredient> ingredients = new ArrayList<>();
        private double totalPrice;
        private double totalCalories;

        /**
         * Constructs a Builder with the specified size and toasting preference.
         *
         * @param size the size of the sandwich (4, 8, or 12)
         * @param isToast whether the sandwich is toasted
         */
        public Builder(int size, boolean isToast) {
            this.size = switch (size) {
                case 4 -> Size.SMALL;
                case 8 -> Size.MEDIUM;
                case 12 -> Size.LARGE;
                default -> throw new IllegalArgumentException("Invalid size provided. Valid sizes are 4, 8, or 12.");
            };
            this.isToast = isToast;
        }

        /**
         * Adds a bread type to the sandwich.
         *
         * @param ingredient the bread type
         * @return the Builder instance
         * @throws InvalidIngredientException if the bread type is invalid
         */
        public Builder addBread(String ingredient) throws InvalidIngredientException {
            if (this.breadType != null) {
                throw new IllegalStateException("Bread type has already been added.");
            }
            this.breadType = createIngredient(ingredient, breadInfoMap, Bread::new);
            return this;
        }

        /**
         * Adds a meat ingredient to the sandwich.
         *
         * @param ingredient the meat type
         * @return the Builder instance
         * @throws InvalidIngredientException if the meat type is invalid
         */
        public Builder addMeat(String ingredient) throws InvalidIngredientException {
            ingredients.add(createIngredient(ingredient, meatInfoMap, Meat::new));
            return this;
        }

        /**
         * Adds a cheese ingredient to the sandwich.
         *
         * @param ingredient the cheese type
         * @return the Builder instance
         * @throws InvalidIngredientException if the cheese type is invalid
         */
        public Builder addCheese(String ingredient) throws InvalidIngredientException {
            ingredients.add(createIngredient(ingredient, cheeseInfoMap, Cheese::new));
            return this;
        }

        /**
         * Adds a vegetable ingredient to the sandwich.
         *
         * @param ingredient the vegetable type
         * @return the Builder instance
         * @throws InvalidIngredientException if the vegetable type is invalid
         */
        public Builder addVegetable(String ingredient) throws InvalidIngredientException {
            ingredients.add(createIngredient(ingredient, vegetableInfoMap, Vegetable::new));
            return this;
        }

        /**
         * Adds a sauce ingredient to the sandwich.
         *
         * @param ingredient the sauce type
         * @return the Builder instance
         * @throws InvalidIngredientException if the sauce type is invalid
         */
        public Builder addSauce(String ingredient) throws InvalidIngredientException {
            ingredients.add(createIngredient(ingredient, sauceInfoMap, Sauce::new));
            return this;
        }

        /**
         * Creates an ingredient and calculates its price and calories.
         *
         * @param name the name of the ingredient
         * @param infoMap the map containing ingredient information
         * @param constructor the constructor function for the ingredient
         * @param <T> the type of the ingredient
         * @return the created ingredient
         * @throws InvalidIngredientException if the ingredient is invalid
         */
        private <T extends SandwichIngredient> T createIngredient(String name, Map<String, IngredientInfo> infoMap, Function<String, T> constructor) throws InvalidIngredientException {
            IngredientInfo info = getIngredientInfo(name, infoMap);
            T ingredient = constructor.apply(name);
            ingredient.setPrice(DefaultValueService.calculatePrice(info, size));
            ingredient.setCalories(DefaultValueService.calculateCalories(info, size));
            this.totalPrice += ingredient.getPrice();
            this.totalCalories += ingredient.getCalories();
            return ingredient;
        }

        /**
         * Retrieves ingredient information from the map.
         *
         * @param name the name of the ingredient
         * @param infoMap the map containing ingredient information
         * @return the ingredient information
         * @throws InvalidIngredientException if the ingredient is invalid
         */
        private IngredientInfo getIngredientInfo(String name, Map<String, IngredientInfo> infoMap) throws InvalidIngredientException {
            IngredientInfo info = infoMap.get(name.toUpperCase());
            if (info == null) {
                String errorMessage = "The ingredient '%s' is invalid. Please choose a valid ingredient from the list.".formatted(name);
                logger.error(errorMessage);
                throw new InvalidIngredientException(errorMessage);
            }
            return info;
        }

        /**
         * Builds the Sandwich.
         *
         * @return the constructed Sandwich
         * @throws IllegalStateException if the sandwich does not have at least one ingredient and a bread type
         */
        public Sandwich build() {
            if (ingredients.isEmpty() || breadType == null) {
                throw new IllegalStateException("A sandwich must have at least one ingredient and a bread type.");
            }
            return new Sandwich(this);
        }
    }

    /**
     * Returns a string representation of the Sandwich.
     *
     * @return a string representation of the Sandwich
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("===================================================\n")
                .append("SANDWICH\n")
                .append("Sandwich Name: ").append(this.getName()).append("\n")
                .append("Size: ").append(this.getSize()).append("\t\t\t").append("Toast: ").append(this.isToast).append("\n")
                .append("===================================================\n")
                .append(String.format("\t%-19s %-10s\t%s\n", "Ingredient", "Price", "Calories"));

        if (breadType != null) {
            stringBuilder.append(String.format("   -%-20s %.2f\t\t%.1f\n",
                    breadType.getName(), breadType.getPrice(), breadType.getCalories()));
        }
        for (SandwichIngredient ingredient : ingredients) {
            stringBuilder.append("   -")
                    .append(String.format("%-20s %.2f\t\t%.1f\n",
                            ingredient.getName(), ingredient.getPrice(), ingredient.getCalories()));
        }

        stringBuilder.append("===================================================\n")
                .append("Total Price: ").append(this.getPrice()).append("\n")
                .append("Total Calories: ").append(this.getCalories()).append("\n")
                .append("===================================================\n");

        return stringBuilder.toString();

    }

    /**
     * Calculates the total calories of the Sandwich.
     *
     * @return the total calories of the Sandwich
     */
    @Override
    public double getCalories() {
        return ingredients.stream().mapToDouble(SandwichIngredient::getCalories).sum() + breadType.getCalories();
    }

    /**
     * Calculates the total price of the Sandwich.
     *
     * @return the total price of the Sandwich
     */
    @Override
    public double getPrice() {
        return ingredients.stream().mapToDouble(SandwichIngredient::getPrice).sum() + breadType.getPrice();
    }



}