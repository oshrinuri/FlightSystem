package com.flightsystem.flights.enums;

/**
 * UserRole enumeration.
 * In database: Customer = 1 | Admin = 2 | Airline = 3
 * @author  Oshri Nuri
 * @version 1.3
 */
public enum UserRole {
    CUSTOMER, ADMIN, AIRLINE;
    /* ------------------------------------------------------------------------------------------------------------------- */
    /**
     * A static function to convert an int value to its corresponding enumeration type (UserRole).
     * @param value Value to be converted into UserRole.
     * @return Corresponding UserRole enumeration type.
     */
    public static UserRole getRoleFromIntValue(int value) {
        return switch (value) {
            case 1 -> CUSTOMER;
            case 2 -> ADMIN;
            case 3-> AIRLINE;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}
