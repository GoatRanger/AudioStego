package dji.midware.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class f {
    private static final String a = "HmacSHA1";

    public static String a(Context context) {
        String str = "";
        try {
            return c.a(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray()))).getEncoded()), ":").toUpperCase(Locale.ENGLISH);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return str;
        } catch (CertificateException e2) {
            e2.printStackTrace();
            return str;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return str;
        }
    }

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
