package com.pluralsight.entity.sandwich.toppings;


import lombok.Getter;

public enum Size {
    
    
    SMALL(4),
    MEDIUM(8),
    LARGE(12);

    @Getter
    private final int value;

    Size(int value) {
        this.value = value;
    }


    
}
