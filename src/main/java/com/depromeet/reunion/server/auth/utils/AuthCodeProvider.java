package com.depromeet.reunion.server.auth.utils;

/**
 * This class is used to create a random 6-digit number.
 */
public class AuthCodeProvider {

    public static String createRandomAuthCode() {
        return String.valueOf((int) (Math.random() * 1000000));
    }

}