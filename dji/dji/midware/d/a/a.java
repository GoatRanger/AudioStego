package dji.midware.d.a;

import java.math.BigInteger;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    private static final String a = "AES";
    private static final String b = "AES/CBC/PKCS5Padding";
    private static byte[] c = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};

    private static byte[] b(String str) throws Exception {
        return str.getBytes();
    }

    private static Key a(byte[] bArr) throws Exception {
        return new SecretKeySpec(bArr, a);
    }

    public static byte[] a(byte[] bArr, String str) throws Exception {
        Key a = a(b(str));
        Cipher instance = Cipher.getInstance(b, "BC");
        instance.init(1, a, new IvParameterSpec(c));
        return instance.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr, String str) throws Exception {
        Key a = a(b(str));
        Cipher instance = Cipher.getInstance(b, "BC");
        instance.init(2, a, new IvParameterSpec(c));
        return instance.doFinal(bArr);
    }

    public static boolean a() {
        try {
            if (Cipher.getMaxAllowedKeyLength(a) >= 256) {
                return true;
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String a(String str, String str2) {
        try {
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacSha256");
            Mac instance = Mac.getInstance(secretKeySpec.getAlgorithm());
            instance.init(secretKeySpec);
            return String.format("%0" + (instance.doFinal(str.getBytes()).length * 2) + "X", new Object[]{new BigInteger(1, r0)});
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 32; i++) {
            if (i + 1 <= str.length()) {
                bArr[i] = (byte) str.charAt(i);
            } else {
                bArr[i] = (byte) 0;
            }
        }
        return bArr;
    }
}
