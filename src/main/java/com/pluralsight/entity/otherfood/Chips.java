package com.pluralsight.entity.otherfood;


import com.pluralsight.entity.Food;
import com.pluralsight.entity.Purchasable;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class Chips extends Food implements Purchasable {


    @Override
    public String toString() {
        return String.format(
                "🍟 **%s Chips**\n" +
                        "   Price: $%.2f\n" +
                        "   Calories: %.0f kcal",
                this.getName(),  this.getPrice(), this.getCalories()
        );
    }
}
