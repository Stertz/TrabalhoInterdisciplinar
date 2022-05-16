/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.NoSuchProviderException;

/**
 *
 * @author lucas
 */
public class HashSal {
    public static void criarHash () throws NoSuchAlgorithmException, NoSuchProviderException {
        String passwordToHash = "password";
      
        String securePassword = getPasswordDigest(passwordToHash);
    }
    
//    public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return toHextString(salt);        
//    }

     private static String toHextString (byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte i : bytes) {
            sb.append(String.format("%02x", i));
        }
        return sb.toString();
    }

    public static String getPasswordDigest(String passwordToHash) {
        String salt = "uMaStrinGquaLquEr65";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            return toHextString(bytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Should not get here");
            return "";
        }
    }
}