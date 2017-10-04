package com.f.a.a;

import java.io.UnsupportedEncodingException;

public class a {
    public static final byte[] a = new byte[0];
    private static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte b) {
        char[] cArr = new char[2];
        cArr[1] = b[b & 15];
        cArr[0] = b[((byte) (b >>> 4)) & 15];
        return new String(cArr);
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            cArr[(i * 2) + 1] = b[b & 15];
            cArr[(i * 2) + 0] = b[((byte) (b >>> 4)) & 15];
        }
        return new String(cArr);
    }

    public static byte a(String str) {
        if (str == null || str.length() != 1) {
            return (byte) 0;
        }
        return a(str.charAt(0));
    }

    public static byte a(char c) {
        if (c >= '0' && c <= '9') {
            return (byte) (c - 48);
        }
        if (c >= 'a' && c <= 'f') {
            return (byte) ((c - 97) + 10);
        }
        if (c < 'A' || c > 'F') {
            return (byte) 0;
        }
        return (byte) ((c - 65) + 10);
    }

    public static byte[] b(String str) {
        if (str == null || str.equals("")) {
            return a;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((a(str.charAt(i * 2)) * 16) + a(str.charAt((i * 2) + 1)));
        }
        return bArr;
    }

    public static void a(String[] strArr) {
        try {
            System.out.println(a("Hello WebSocket World?".getBytes("gbk")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
