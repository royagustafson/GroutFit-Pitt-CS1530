package com.GroutFit.Helper;

import org.mindrot.jbcrypt.BCrypt;

public class pHash {

    public static void main(String[] args) {
        String pw = "password";
        String hash = hash(pw);
        System.out.println(verify(pw, hash));
    }

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verify(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

}