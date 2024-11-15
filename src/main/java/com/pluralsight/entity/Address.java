package com.pluralsight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an address with street, city, state, and zip code.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    /**
     * The street of the address.
     */
    private String street;

    /**
     * The city of the address.
     */
    private String city;

    /**
     * The state of the address.
     */
    private String state;

    /**
     * The zip code of the address.
     */
    private String zipCode;
}