package com.alipay.e.a.a.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class a {
    private static a a = new a();

    private a() {
    }

    private static byte[] b(Context context, String str) {
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(64)) {
                if (packageInfo.packageName.equals(str)) {
                    return packageInfo.signatures[0].toByteArray();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static a getInstance() {
        return a;
    }

    public byte[] a(Context context, String str) {
        try {
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(b(context, str)))).getPublicKey().getEncoded();
        } catch (Exception e) {
            return null;
        }
    }
}
