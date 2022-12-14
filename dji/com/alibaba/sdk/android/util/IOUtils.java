package com.alibaba.sdk.android.util;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
    private static final String a = IOUtils.class.getSimpleName();

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static byte[] toByteArray(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable e) {
            Log.e(a, null, e);
            return null;
        }
    }

    public static String toString(InputStream inputStream, String str) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    closeQuietly(inputStream);
                    return str2;
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            closeQuietly(inputStream);
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        while (!Thread.interrupted()) {
            int read = inputStream.read(bArr, 0, i);
            if (read == -1) {
                break;
            }
            outputStream.write(bArr, 0, read);
        }
        outputStream.flush();
    }
}
