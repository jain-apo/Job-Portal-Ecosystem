package helpers;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Encryption {

    private static BCrypt.Verifyer verifyer;
    private static BCrypt.Hasher hasher;

    public static void initialize() {
        hasher = BCrypt.withDefaults();
        verifyer = BCrypt.verifyer();
    }

    public static String hash(String input) {
        return hasher.hashToString(12, input.toCharArray());
    }

    public static boolean verify(String input, String hashed) {
        return verifyer.verify(input.toCharArray(), hashed).verified;
    }
}
