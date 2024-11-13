package com.pluralsight.entity.otherfood;

import com.pluralsight.entity.Food;
import com.pluralsight.entity.Purchasable;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class Drink extends Food implements Purchasable {

    @Override
    public String toString() {
        return String.format(
                """
                        🥤 **%s** (Drink)
                           Size: %s
                           Price: $%.2f
                           Calories: %.0f kcal""",
                this.getName(), this.getSize(), this.getPrice(), this.getCalories()
        );
    }
}
