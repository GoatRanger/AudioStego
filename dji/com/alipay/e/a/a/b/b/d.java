package com.alipay.e.a.a.b.b;

import com.alipay.e.a.a.b.a;
import java.security.MessageDigest;

public class d {
    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(bArr);
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] a(String str) {
        byte[] bArr = null;
        try {
            if (!a.a(str)) {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(str.getBytes("UTF-8"));
                bArr = instance.digest();
            }
        } catch (Exception e) {
        }
        return bArr;
    }

    public static String b(String str) {
        String str2 = null;
        try {
            if (!a.a(str)) {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(str.getBytes("UTF-8"));
                byte[] digest = instance.digest();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
                }
                str2 = stringBuilder.toString();
            }
        } catch (Exception e) {
        }
        return str2;
    }

    public static String c(String str) {
        String str2 = null;
        try {
            if (!a.a(str)) {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes("UTF-8"));
                byte[] digest = instance.digest();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < 16; i++) {
                    stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
                }
                str2 = stringBuilder.toString();
            }
        } catch (Exception e) {
        }
        return str2;
    }
}
