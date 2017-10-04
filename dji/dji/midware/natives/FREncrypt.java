package dji.midware.natives;

import android.util.Log;
import com.dji.frame.c.a;
import dji.midware.util.f;
import java.security.SignatureException;

public class FREncrypt {
    public static native long decryptFRData(byte[] bArr, byte[] bArr2, int i, int i2, long j);

    public static native long encryptFRData(byte[] bArr, byte[] bArr2, int i, int i2, long j);

    public static native String getVerifyStr(byte[] bArr);

    public static native void test();

    public static native void verifySign();

    static {
        try {
            System.loadLibrary("FREncrypt");
            Log.d("FREncrypt", "load lib suc");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            Log.d("FREncrypt", "Couldn't load lib");
        }
    }

    public static void loadLibrary() {
    }

    public static String getMD5(byte[] bArr) {
        return a.a(bArr);
    }

    public static String calSHA1(String str, String str2) {
        try {
            return f.a(str, str2);
        } catch (SignatureException e) {
            e.printStackTrace();
            return "";
        }
    }
}
