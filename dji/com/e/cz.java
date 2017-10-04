package com.e;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class cz {
    public static String a(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        String str2 = null;
        FileInputStream fileInputStream2 = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.isFile() && file.exists()) {
                    byte[] bArr = new byte[2048];
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    fileInputStream = new FileInputStream(file);
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            instance.update(bArr, 0, read);
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    str2 = dd.d(instance.digest());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th4) {
                            dg.a(th4, "MD5", "getMd5FromFile");
                        }
                    }
                } else if (str2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable th42) {
                        dg.a(th42, "MD5", "getMd5FromFile");
                    }
                }
            } else if (str2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Throwable th422) {
                    dg.a(th422, "MD5", "getMd5FromFile");
                }
            }
        } catch (Throwable th4222) {
            fileInputStream = str2;
            th2 = th4222;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th2;
        }
        return str2;
    }

    public static String a(byte[] bArr) {
        return dd.d(b(bArr));
    }

    public static byte[] a(byte[] bArr, String str) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable th) {
            dg.a(th, "MD5", "getMd5Bytes1");
        }
        return bArr2;
    }

    public static String b(String str) {
        return str == null ? null : dd.d(d(str));
    }

    private static byte[] b(byte[] bArr) {
        return a(bArr, "MD5");
    }

    public static String c(String str) {
        return dd.e(e(str));
    }

    public static byte[] d(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            dg.a(th, "MD5", "getMd5Bytes");
            return new byte[0];
        }
    }

    private static byte[] e(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] f(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(dd.a(str));
        return instance.digest();
    }
}
