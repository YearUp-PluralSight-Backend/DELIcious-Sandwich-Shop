package com.pluralsight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a shop with a name, address, phone number, and staff name.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    /**
     * The name of the shop.
     */
    private String shopName;

    /**
     * The address of the shop.
     */
    private Address shopAddress;

    /**
     * The phone number of the shop.
     */
    private String phoneNumber;

    /**
     * The name of the staff member.
     */
    private String staffName;
}