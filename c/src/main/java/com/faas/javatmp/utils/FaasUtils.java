package com.faas.javatmp.utils;

/**
 * @author LBW
 */
public class FaasUtils {
    public static int getRandomNumber(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }
}
