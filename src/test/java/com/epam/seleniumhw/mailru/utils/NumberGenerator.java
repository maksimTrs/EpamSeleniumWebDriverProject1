package com.epam.seleniumhw.mailru.utils;

import java.util.Random;

public class NumberGenerator {

    public static String getRandomFirstDomainLvl() {
        StringBuilder init = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            char c = (char) (r.nextInt(26) + 'a');
            init.append(c);
        }
        return new String(init);
    }

    public static int getIntRandomValue() {
        Random rand = new Random();
        int upperbound = 999;
        return rand.nextInt(upperbound);
    }

}
