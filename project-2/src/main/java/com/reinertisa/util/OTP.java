package com.reinertisa.util;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.KeyGenerator;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class OTP {
    static TimeBasedOneTimePasswordGenerator totp = null;

    static {
        try {
            totp = new TimeBasedOneTimePasswordGenerator();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static Key key;


    public static String getOTP() {
        try {

            final KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());

            // Key length should match the length of the HMAC output (160 bits for SHA-1, 256 bits
            // for SHA-256, and 512 bits for SHA-512).
            keyGenerator.init(512);

            key = keyGenerator.generateKey();

            return "" + totp.generateOneTimePassword(key, Instant.now());
            //TODO Actually manage these exceptions
        } catch (Exception e) {
            return "000000";
        }
    }
}
