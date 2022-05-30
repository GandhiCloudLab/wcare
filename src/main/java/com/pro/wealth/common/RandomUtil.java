package com.pro.wealth.common;

import java.util.Random;

public class RandomUtil {

    public static int getRandomInRange(int start, int end){
        Random r = new Random();
        return start + r.nextInt(end - start + 1);
    }
}
