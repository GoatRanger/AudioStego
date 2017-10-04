package dji.pilot.a;

import android.util.Base64;
import java.math.BigInteger;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    public static boolean a = true;
    private static byte[] b = new byte[16];

    public static String a(String str, String str2) throws Exception {
        byte[] bArr = new byte[32];
        Key secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(b));
        return new String(instance.doFinal(Base64.decode(str, 8)));
    }

    public static String b(String str, String str2) throws Exception {
        byte[] bytes = str.getBytes("utf-8");
        byte[] bArr = new byte[32];
        Key secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(b));
        return Base64.encodeToString(instance.doFinal(bytes), 8).replace("\n", "");
    }

    public static String c(String str, String str2) {
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
}
