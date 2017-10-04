package com.dji.a.f;

import java.security.Key;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class d {
    public static String a(byte[] bArr, String str) {
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), "HmacMD5");
            Mac instance = Mac.getInstance("HmacMD5");
            instance.init(secretKeySpec);
            return a(instance.doFinal(bArr));
        } catch (Exception e) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            int i = b & 255;
            int i2 = i >>> 4;
            int i3 = i & 15;
            if (i2 < 10) {
                i = 48;
            } else {
                i = 87;
            }
            stringBuffer.append((char) (i + i2));
            if (i3 < 10) {
                i = 48;
            } else {
                i = 87;
            }
            stringBuffer.append((char) (i + i3));
        }
        return stringBuffer.toString();
    }

    public static String a() {
        return UUID.randomUUID().toString().replace("-", "d");
    }
}
