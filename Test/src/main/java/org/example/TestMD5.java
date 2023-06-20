package org.example;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class TestMD5 {
    public static void main(String[] args) {
        run1();
        run();

    }
    public static void run(){
        String res = DigestUtils.md5DigestAsHex("password".getBytes());
        System.out.println(res);
        System.out.println("pbkdf2_sha256$390000$cxW5a64MB5N2uAcH0uPZuz$ld+N77MW8G4+6VAsYA+Bc8ec3v5wCT1BlvTiqO1QsTw=");

    }
    public static String makePassword(String password, String algorithm) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        int iterations = 390000;
        byte[] hash = generateHash(password.toCharArray(), salt, algorithm, iterations);

        return algorithm + "$" + iterations + "$" + Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hash);
    }

    public static boolean checkPassword(String password, String encoded) throws NoSuchAlgorithmException {
        String[] parts = encoded.split("\\$");
        String algorithm = parts[0];
        int iterations = Integer.parseInt(parts[1]);
        byte[] salt = Base64.getDecoder().decode(parts[2]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[3]);

        byte[] actualHash = generateHash(password.toCharArray(), salt, algorithm, iterations);

        return MessageDigest.isEqual(expectedHash, actualHash);
    }

    private static byte[] generateHash(char[] password, byte[] salt, String algorithm, int iterations) throws NoSuchAlgorithmException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 256);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            return skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new NoSuchAlgorithmException("Error while hashing password: " + e.getMessage());
        } finally {
            spec.clearPassword();
        }
    }

    public static void run1() {
        String password = "password";
        String algorithm = "PBKDF2WithHmacSHA256";

        try {
            String hashedPassword = makePassword(password, algorithm);
            System.out.println(hashedPassword);

            boolean isValid = checkPassword(password, hashedPassword);
            System.out.println(isValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
