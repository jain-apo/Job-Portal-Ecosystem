package helpers;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Encryption {
    public static String hash(String input) {
        return BCrypt.withDefaults().hashToString(12, input.toCharArray());
    }

    public static boolean verify(String input, String hashed) {
        return BCrypt.verifyer().verify(input.toCharArray(), hashed).verified;
    }
}
