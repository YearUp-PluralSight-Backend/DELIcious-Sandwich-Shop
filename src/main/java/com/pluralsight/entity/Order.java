package com.pluralsight.entity;

import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.sandwich.Sandwich;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class Order {

    private Long orderNumber;
    private List<Food> cart;
    private LocalDateTime createTime;

    public Order() {
        cart = new ArrayList<>();

    }

    public double calculateTotalPrice() {
        return cart.stream().mapToDouble(Food::getPrice).sum();
    }

    public long getNumberOfChips() {
        return cart.stream().filter(food -> food instanceof Chips).count();
    }

    public long getNumberOfDrinks() {
        return cart.stream().filter(food -> food instanceof Drink).count();
    }

    public long getNumberOfSandwiches() {
        return cart.stream().filter(food -> food instanceof Sandwich).count();
    }

    public long getNumberOfItems() {
        return cart.size();
    }




}
