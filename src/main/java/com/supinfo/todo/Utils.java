package com.supinfo.todo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by l3ops on 19/05/2015.
 */
public class Utils {
    public static String sha1(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1"); // Crypte les mots de passe en SHA-1 pour la s�curit�
            return Base64.getEncoder().encodeToString(md.digest(value.getBytes())); // Mot de passe stock� en base 64 pl�tot q'en base 2 pour prendre moins de place
        } catch(NoSuchAlgorithmException e) { // Si le SHA-1 n'est pas disponible
            e.printStackTrace(); // M�thode pour l'afficher
            return null;
        }
    }
}
