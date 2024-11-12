package com.pluralsight.entity;

import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.utils.ConstantValue;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Data
public class Order {

    private long orderNumber;
    private List<Food> cart;
    private String createTime;

    public Order() {
        this.orderNumber = generatesOrderNumber();
        createTime = LocalDateTime.now().format(ConstantValue.DATE_TIME_FORMATTER);
        cart = new ArrayList<>();
    }

    public double calculateTotalPrice() {
        return cart.stream().mapToDouble(Food::getPrice).sum();
    }

    private long generatesOrderNumber() {
        return  new Random().nextLong(1, 200001);
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
