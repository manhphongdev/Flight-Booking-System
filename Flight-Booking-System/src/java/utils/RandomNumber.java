/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Random;

/**
 *
 * @author manhphong
 */
public class RandomNumber {


public static String getRandomNumber() {
    Random rd = new Random();
    long number = Math.abs(rd.nextLong() % 1_000_000_0000L); 
    return String.format("%010d", number); 
}
}
