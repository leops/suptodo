package com.supinfo.todo;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by l3ops on 19/05/2015.
 */
public class Utils {
    public static String sha1(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return Base64.encode(md.digest(value.getBytes()));
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
