package com.pluralsight.entity.otherfood;

import com.pluralsight.entity.Food;
import com.pluralsight.entity.Purchasable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a type of food item, Chips, which can be purchased.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Chips extends Food implements Purchasable {

    /**
     * Returns a string representation of the Chips object.
     *
     * @return a string representation of the Chips object
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append("==============================================\n")
                .append("🥤 ").append(this.getName()).append("\t\tChips\n")
                .append("Price: $").append(String.format("%.2f", this.getPrice()))
                .append("\t\tCalories: ").append(this.getCalories()).append(" kcal\n")
                .append("==============================================\n")
                .toString();
    }
}