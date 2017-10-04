package com.nokia.maps;

import android.content.Context;
import android.util.Base64;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

class n {
    private static final String a = n.class.getName();
    private static RSAPublicKey b;
    private static RSAPrivateKey c;

    static {
        try {
            KeyFactory instance = KeyFactory.getInstance("RSA");
            b = (RSAPublicKey) instance.generatePublic(new X509EncodedKeySpec(s.a(Base64.decode("aouVJic0dG/gBYa9O2ANWvt3KeDPUSvViTO43WqakURqOP79hHA/7IetmKjwfa4CCm0KH5VJENPcjKOqVFbnGWbB6EYBFgGjGZqLbSIIzAvdzSsJhrilbcZpFEVqeecKG4M4psSBl2dT9IG7aQa2jeJ4H/qREPOJ20SaO7lPn/RlYkh8T81CYoeatD+UedPexdK3ciwMy4ceFhB2mj8iECYo", 0), 10)));
            c = (RSAPrivateKey) instance.generatePrivate(new PKCS8EncodedKeySpec(s.a(Base64.decode("aoj4YBgzbWVbO/kQwBmKjAN2K2BXUf/WElFp3mgjNB83BseMKL7OAzByS0d5MxOF57vMsc7CmDFrEFxD4V9Ri4Z6u7kWQoA3OrrEY0d1a+9pe49YaTS8SYbKEHfbnJET13H4Wi2CgBtPr3Sb3JzqPhnt6doDZcnWwfVH+4EKtN/eHYNbr2nKuisdF4V16F1nxXuFSPCJpqOD4vAKZROswFpGZiO+djVVZBBxms8ngncMKJFZCOjsI35I6BetL6sujK4D9atPLFpA/BZy3/eLMNhu7J1645frJ2qXGcm8GXwGCOJxq00n5p7gwwuNOTTiZJUhCyRYg9MwtAT2Y4SjlPiimimWc31u4cQ4mkjj5nVTR779DTm3xypuBTUxgDs+ui5PC+/qHJPylMH+EhQedWTOfuvGrG+OtLM7O7tHSgmOU39A9c3emCaCZMLXfGscvJFZcKLaZo+KL79W2mtTI8SM6ZYa7RuxHaGaN3n+5rBVEzIbgVIL4WZJeY5yhQBSl3Sgy/B8bd0u8xryzKElLUQuKafaMSaS7uk8byFtSWQ+1uMy0pxdMWzQirWM7VVrP0UnDipLNFDxYeQ6Y2tcU9It5v2mBzyIno5eeZAoG3SUZd7JM5JSrdZxROnq62vNmXOgqPAHep5Tp45weM6pz3XQRx2C5B50ADEc7y8IreC9MLz9yVqwWE24LTj1DyAmhcUS9AHVFEi/Ftt7JxgRWhFZOCNgBseOdWgk9Qg0W/5CXAmVWbCjNWZRCYwvicYBx7I17DiQxoQHEBfeAvku39K6oOsCUbHLrROZhRjPpEbZQTatr8uiSDrhulvHFhlDFb63j4zdcqmDjw==", 0), 10)));
        } catch (NoSuchAlgorithmException e) {
            bj.c(a, "RSA not supported", new Object[0]);
        } catch (InvalidKeySpecException e2) {
            bj.c(a, "Could not create keys", new Object[0]);
        }
    }

    private n() {
    }

    static boolean a(String str, String str2, Context context) {
        boolean z = false;
        try {
            Signature instance = Signature.getInstance("SHA512withRSA");
            instance.initVerify(b);
            instance.update(str.getBytes(Charset.forName("UTF-8")));
            z = instance.verify(Base64.decode(str2, 0));
        } catch (RuntimeException e) {
            bj.c(a, "Unable to verify signature", new Object[z]);
        } catch (Exception e2) {
            bj.c(a, "Unable to verify signature", new Object[z]);
        }
        return z;
    }

    static String a(String str, Context context) {
        String str2 = "";
        try {
            Signature instance = Signature.getInstance("SHA512withRSA");
            instance.initSign(c);
            instance.update(str.getBytes(Charset.forName("UTF-8")));
            str2 = Base64.encodeToString(instance.sign(), 0);
        } catch (RuntimeException e) {
            bj.c(a, "Unable to create signature", new Object[0]);
        } catch (Exception e2) {
            bj.c(a, "Unable to create signature", new Object[0]);
        }
        return str2;
    }
}
