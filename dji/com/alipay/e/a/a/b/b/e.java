package com.alipay.e.a.a.b.b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class e {
    private static final f a = new f();

    public static int a(String str, OutputStream outputStream) {
        return a.a(str, outputStream);
    }

    public static int a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        return a.a(bArr, i, i2, outputStream);
    }

    public static int a(byte[] bArr, OutputStream outputStream) {
        return a.a(bArr, 0, bArr.length, outputStream);
    }

    public static byte[] a(String str) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a.a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("exception decoding Hex string: " + e);
        }
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a.a(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("exception encoding Hex string: " + e);
        }
    }

    public static byte[] b(byte[] bArr) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a.b(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("exception decoding Hex string: " + e);
        }
    }
}
