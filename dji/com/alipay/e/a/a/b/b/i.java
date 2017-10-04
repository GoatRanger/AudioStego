package com.alipay.e.a.a.b.b;

import android.annotation.SuppressLint;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class i {
    public static final String a = "SecurityUtils";
    private static String b = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");

    public static String a() {
        String str = new String();
        for (int i = 0; i < b.length() - 1; i += 4) {
            str = str + b.charAt(i);
        }
        return str;
    }

    public static String a(String str) {
        return a(str.getBytes());
    }

    public static String a(String str, String str2) {
        byte[] bArr = null;
        try {
            byte[] b = b(str.getBytes());
            byte[] bytes = str2.getBytes();
            Key secretKeySpec = new SecretKeySpec(b, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            bArr = instance.doFinal(bytes);
        } catch (Exception e) {
        }
        return a(bArr);
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
        }
        return stringBuffer.toString();
    }

    public static String b(String str) {
        return new String(c(str));
    }

    public static String b(String str, String str2) {
        try {
            byte[] b = b(str.getBytes());
            byte[] c = c(str2);
            Key secretKeySpec = new SecretKeySpec(b, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            return new String(instance.doFinal(c));
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint({"TrulyRandom"})
    private static byte[] b(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        Object instance2 = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        h.a(instance2, h.a(new String(a.a("c2VlZA==")), SecureRandom.class, bArr.getClass()), new Object[]{bArr});
        instance.init(128, instance2);
        return instance.generateKey().getEncoded();
    }

    public static byte[] c(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return bArr;
    }
}
