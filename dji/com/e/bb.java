package com.e;

import com.f.a.a.g;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class bb {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final byte[] b = new byte[]{(byte) 0, (byte) 1, (byte) 1, (byte) 2, (byte) 3, (byte) 5, (byte) 8, g.SIMPLE_LIST, (byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 2, (byte) 1};
    private static final IvParameterSpec c = new IvParameterSpec(b);

    public static String a(String str) {
        String str2 = null;
        if (str != null) {
            try {
                if (str.length() != 0) {
                    str2 = a("MD5", a("SHA1", str) + str);
                }
            } catch (Throwable th) {
                bc.a(th, "Encrypt", "generatorKey");
            }
        }
        return str2;
    }

    public static String a(String str, String str2) {
        String str3 = null;
        if (str2 != null) {
            try {
                str3 = b(cz.a(str2.getBytes("UTF-8"), str));
            } catch (Throwable th) {
                bc.a(th, "Encrypt", "encode");
            }
        }
        return str3;
    }

    public static String a(byte[] bArr) {
        try {
            Object obj = new byte[16];
            Object obj2 = new byte[(bArr.length - 16)];
            System.arraycopy(bArr, 0, obj, 0, 16);
            System.arraycopy(bArr, 16, obj2, 0, bArr.length - 16);
            Key secretKeySpec = new SecretKeySpec(obj, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(dd.a()));
            return new String(instance.doFinal(obj2), "UTF-8");
        } catch (Throwable th) {
            bc.a(th, "Encrypt", "decryptRsponse");
            return null;
        }
    }

    private static byte[] a() {
        return dd.a();
    }

    public static synchronized byte[] a(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        int i = 0;
        synchronized (bb.class) {
            Key generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(cy.b(str)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePrivate);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i2 = 0;
            while (length - i > 0) {
                doFinal = length - i > 245 ? instance.doFinal(bArr, i, 245) : instance.doFinal(bArr, i, length - i);
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i = i2 + 1;
                int i3 = i;
                i *= 245;
                i2 = i3;
            }
            doFinal = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        return doFinal;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, new SecretKeySpec(bArr, "AES"), c);
        return instance.doFinal(bArr2);
    }

    private static String b(byte[] bArr) {
        int length = bArr.length;
        StringBuilder stringBuilder = new StringBuilder(length * 2);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(a[(bArr[i] >> 4) & 15]);
            stringBuilder.append(a[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }

    private static SecretKeySpec b(String str) {
        byte[] bArr = null;
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(str);
        while (stringBuffer.length() < 16) {
            stringBuffer.append("0");
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            bArr = stringBuffer.toString().getBytes("UTF-8");
        } catch (Throwable th) {
            bc.a(th, "Encrypt", "createKey");
        }
        return new SecretKeySpec(bArr, "AES");
    }

    public static synchronized byte[] b(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        int i = 0;
        synchronized (bb.class) {
            Key generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(cy.b(str)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, generatePrivate);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i2 = 0;
            while (length - i > 0) {
                doFinal = length - i > 256 ? instance.doFinal(bArr, i, 256) : instance.doFinal(bArr, i, length - i);
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i = i2 + 1;
                int i3 = i;
                i *= 256;
                i2 = i3;
            }
            doFinal = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        return doFinal;
    }

    public static byte[] c(byte[] bArr, String str) {
        Cipher instance;
        try {
            Key b = b(str);
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a());
            instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, b, ivParameterSpec);
        } catch (Throwable e) {
            bc.a(e, "Encrypt", "aesEncrypt1");
        } catch (Throwable e2) {
            bc.a(e2, "Encrypt", "aesEncrypt");
            return null;
        }
        return instance.doFinal(bArr);
    }

    public static byte[] d(byte[] bArr, String str) {
        Cipher instance;
        try {
            Key b = b(str);
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a());
            instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, b, ivParameterSpec);
        } catch (Throwable e) {
            bc.a(e, "Encrypt", "aesDecrypt1");
        } catch (Throwable e2) {
            bc.a(e2, "Encrypt", "aesDecrypt");
            return null;
        }
        return instance.doFinal(bArr);
    }
}
