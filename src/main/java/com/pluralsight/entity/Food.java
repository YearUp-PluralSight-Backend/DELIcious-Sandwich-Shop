package com.pluralsight.entity;


import com.pluralsight.entity.sandwich.types.Size;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Food {

    @NotNull(message = "Size must be specified")
    private Size size;

    @Min(value = 0, message = "Price cannot lower than 0")
    @Min(value = 100000, message = "I do not have enough supply to make your Order")
    private double price;

}
