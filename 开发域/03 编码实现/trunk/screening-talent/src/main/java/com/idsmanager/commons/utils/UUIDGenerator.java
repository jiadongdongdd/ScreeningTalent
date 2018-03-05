package com.idsmanager.commons.utils;

import java.util.UUID;

public abstract class UUIDGenerator {

    private UUIDGenerator() {
    }

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * Always return a random positive number
     */
    public static long generateNumber() {
        long number;
        while (true) {
            number = UUID.randomUUID().getMostSignificantBits();
            if (number > 0) {
                return number;
            }
        }
    }

}
