package com.dji.videouploadsdk.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class b {
    public static String a(File file) {
        Exception e;
        Throwable th;
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            try {
                e.printStackTrace();
                if (fileInputStream == null) {
                    return null;
                }
                try {
                    fileInputStream.close();
                    return null;
                } catch (IOException e5) {
                    e5.printStackTrace();
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e52) {
                        e52.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }
}
