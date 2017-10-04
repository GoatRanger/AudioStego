package dji.pilot.publics.objects;

import dji.midware.util.c;
import java.security.Key;
import java.security.SignatureException;
import java.util.Locale;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class j {
    private static final String a = "HmacSHA1";

    public static String a(String str, String str2) throws SignatureException {
        try {
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacSHA1");
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            return c.h(instance.doFinal(str.getBytes())).toUpperCase(Locale.ENGLISH);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
    }
}
