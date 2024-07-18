package by.kulevets.demobank.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    private static final int WORKLOAD = 12;

    private PasswordUtil() {
    }

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return(BCrypt.hashpw(password_plaintext, salt));
    }
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }
}
