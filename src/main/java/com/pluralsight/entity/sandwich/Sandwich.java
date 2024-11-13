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

@EqualsAndHashCode(callSuper = true)
@Data
public class Sandwich extends Food implements Purchasable {

    private static final Logger logger = LogManager.getLogger(Sandwich.class);
    private Bread breadType;
    @NotNull
    private boolean isToast;
    @NotNull
    private List<SandwichIngredient> ingredients;

    public Sandwich() {
    }

    private Sandwich(Builder builder) {
        this.setSize(builder.size);
        this.breadType = builder.breadType;
        this.ingredients = builder.ingredients;
        this.isToast = builder.isToast;
        super.setPrice( builder.totalPrice);
        super.setCalories(builder.totalCalories);
    }

    public static class Builder {
        private final Size size;
        private boolean isToast;
        private Bread breadType;
        private final List<SandwichIngredient> ingredients = new ArrayList<>();
        private double totalPrice;
        private double totalCalories;

        public Builder(int size, boolean isToast) {
            if (size != 8 && size != 12 && size != 4) {
                throw new IllegalArgumentException("Invalid size provided. Valid sizes are 8 or 12.");
            }
            this.size = switch (size) {
                case 4 -> Size.SMALL;
                case 8 -> Size.MEDIUM;
                case 12 -> Size.LARGE;
                default -> throw new IllegalArgumentException("Invalid size provided. Valid sizes are 4, 8, or 12.");
            };
            this.isToast = isToast;
        }

        public Builder addBread(String ingredient) throws InvalidIngredientException {
            if (this.breadType != null) {
                throw new IllegalStateException("Bread type has already been added.");
            }
            this.breadType = createIngredient(ingredient, breadInfoMap, Bread::new);
            this.totalPrice += this.breadType.getPrice();
            this.totalCalories += this.breadType.getCalories();
            return this;
        }

        public Builder addMeat(String ingredient) throws InvalidIngredientException {
            SandwichIngredient meat = createIngredient(ingredient, meatInfoMap, Meat::new);
            ingredients.add(meat);
            this.totalPrice += meat.getPrice();
            this.totalCalories += meat.getCalories();
            return this;
        }

        public Builder addCheese(String ingredient) throws InvalidIngredientException {
            SandwichIngredient cheese = createIngredient(ingredient, cheeseInfoMap, Cheese::new);
            ingredients.add(cheese);
            this.totalPrice += cheese.getPrice();
            this.totalCalories += cheese.getCalories();
            return this;
        }

        public Builder addVegetable(String ingredient) throws InvalidIngredientException {
            SandwichIngredient vegetable = createIngredient(ingredient, vegetableInfoMap, Vegetable::new);
            ingredients.add(vegetable);
            this.totalPrice += vegetable.getPrice();
            this.totalCalories += vegetable.getCalories();
            return this;
        }

        public Builder addSauce(String ingredient) throws InvalidIngredientException {
            SandwichIngredient sauce = createIngredient(ingredient, sauceInfoMap, Sauce::new);
            ingredients.add(sauce);
            this.totalPrice += sauce.getPrice();
            this.totalCalories += sauce.getCalories();
            return this;
        }

        private <T extends SandwichIngredient> T createIngredient(String name, Map<String, IngredientInfo> infoMap, Function<String, T> constructor) throws InvalidIngredientException {
            IngredientInfo info = getIngredientInfo(name, infoMap);
            T ingredient = constructor.apply(name);
            ingredient.setPrice(DefaultValueService.calculatePrice(info, size));
            ingredient.setCalories(DefaultValueService.calculateCalories(info, size));
            this.totalPrice += ingredient.getPrice();
            this.totalCalories += ingredient.getCalories();
            return ingredient;
        }

        private IngredientInfo getIngredientInfo(String name, Map<String, IngredientInfo> infoMap) throws InvalidIngredientException {
            IngredientInfo info = infoMap.get(name.toUpperCase());
            if (info == null) {
                String errorMessage = "The ingredient '%s' is invalid. Please choose a valid ingredient from the list.".formatted(name);
                logger.error(errorMessage);
                throw new InvalidIngredientException(errorMessage);
            }
            return info;
        }

        public Sandwich build() {
            if (ingredients.isEmpty()) {
                throw new IllegalStateException("A sandwich must have at least one ingredient.");
            }
            if (breadType == null) {
                throw new IllegalStateException("A sandwich must have a bread type.");
            }
            return new Sandwich(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sandwichDetails = new StringBuilder();
        sandwichDetails.append(String.format(
                "🥪 **%s Sandwich**\n" +
                        "   Size: %s\n" +
                        "   Price: $%.2f\n" +
                        "   Calories: %.0f kcal\n",
                this.getName(), this.getSize(), this.getPrice(), this.getCalories()
        ));

        sandwichDetails.append("   Ingredients:\n");
        for (SandwichIngredient ingredient : ingredients) {
            sandwichDetails.append(String.format(
                    "   - %s (Price: $%.2f, Calories: %.0f)\n",
                    ingredient.getName(), ingredient.getPrice(), ingredient.getCalories()
            ));
        }

        return sandwichDetails.toString();
    }
}