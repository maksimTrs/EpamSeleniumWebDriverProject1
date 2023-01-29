package com.epam.seleniumhw.mailru.utils;

import org.bouncycastle.util.encoders.Base64;

import java.nio.charset.StandardCharsets;

public class SecretPasswordHandler {

    private static  String PASS_BASE64 = "aG9rYWdlbWF4MDIwNTkw";

    private static String returnPass(String pass) {
        if (pass != null && pass.equals(PASS_BASE64)) {
            System.out.println("*** Password validated without errors ***");
            return new String(Base64.decode(pass.getBytes(StandardCharsets.UTF_8)));
        }
        throw new IllegalArgumentException("***** ERROR: Need to add correct password! *****");
    }

    public static String handlingPassword(String pass) {
        System.out.println("*** Handle Base64 password ***");
        return returnPass(pass);
    }

}
