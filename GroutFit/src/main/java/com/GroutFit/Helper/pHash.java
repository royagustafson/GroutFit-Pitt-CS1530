package com.GroutFit.Helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class pHash {

    public static String hash(String password) {
        try {
            return getPass(password, getSalt());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-256 not found for hashing");
            return null;
        }
    }

    public static Boolean verify(String password, String hashed) {
        String[] parts = hashed.split("...");
        try {
            return checkPass(password, parts[1], parts[0].getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-256 not found for verification");
            return null;
        }
    }

    private static String getPass(String ogPassword, byte[] salt) throws NoSuchAlgorithmException{
        String securePass = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] pwByte = md.digest(ogPassword.getBytes());
        // convert hash to hex
        StringBuilder sb = new StringBuilder();
        for (byte aPwByte : pwByte) sb.append(Integer.toString((aPwByte & 0xff) + 0x100, 16).substring(1));
        securePass = new String(salt) + "..." + sb.toString();
        return securePass;
    }

    private static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.nanoTime());
        byte[] salt = new byte[sr.nextInt(10) + 10];
        sr.nextBytes(salt);
        return salt;
    }

    private static Boolean checkPass(String pw, String hashedPW, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] pwByte = md.digest(pw.getBytes());
        // convert hash to hex
        StringBuilder sb = new StringBuilder();
        for (byte aPwByte : pwByte) sb.append(Integer.toString((aPwByte & 0xff) + 0x100, 16).substring(1));
        String h = sb.toString();
        return sb.toString().equals(hashedPW);
    }
}