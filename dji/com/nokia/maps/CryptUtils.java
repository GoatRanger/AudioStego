package com.nokia.maps;

import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class CryptUtils extends BaseNativeObject {
    private static volatile CryptUtils a;

    public native byte[] PKCS5_PBKDF2_HMAC_SHA1(byte[] bArr, byte[] bArr2, int i, int i2);

    public native String x509_NAME_HASH(byte[] bArr);

    @OnlineNative
    public static CryptUtils getInstance() {
        if (a == null) {
            synchronized (CryptUtils.class) {
                if (a == null) {
                    a = new CryptUtils();
                }
            }
        }
        return a;
    }

    private CryptUtils() {
    }
}
