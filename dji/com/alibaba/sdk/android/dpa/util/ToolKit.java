package com.alibaba.sdk.android.dpa.util;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.util.Base64;
import com.alipay.e.a.a.b.b.c;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ToolKit {
    private static final int BUFFER_SIZE = 4096;
    private static Pattern hostPattern = Pattern.compile("^(?=.{1,255}$)[0-9A-Za-z](?:(?:[0-9A-Za-z]|-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|-){0,61}[0-9A-Za-z])?)*\\.?$");

    public static String currentTimeInGMTFormat() {
        return date2GMTFormat(new Date());
    }

    public static String date2GMTFormat(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat simpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    public static String epoch2GMTFormat(long j) {
        return date2GMTFormat(new Date(1000 * j));
    }

    public static Date gmtFormat2Date(String str) throws ParseException {
        return string2DateInSpecifyFormat("E, dd MMM yyyy HH:mm:ss Z", str);
    }

    public static Date string2DateInSpecifyFormat(String str, String str2) throws ParseException {
        return new SimpleDateFormat(str, Locale.ENGLISH).parse(str2);
    }

    public static String getHmacSha1Signature(String str, String str2) throws NoSuchAlgorithmException, InvalidKeyException {
        Key secretKeySpec = new SecretKeySpec(str2.getBytes(), c.a);
        Mac instance = Mac.getInstance(c.a);
        instance.init(secretKeySpec);
        return new String(Base64.encode(instance.doFinal(str.getBytes()), 0)).trim();
    }

    public static String getBase64Md5FromFile(String str) throws IOException, DigestException, NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        byte[] bArr = new byte[4096];
        InputStream fileInputStream = new FileInputStream(new File(str));
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                instance.update(bArr, 0, read);
            } else {
                fileInputStream.close();
                return new String(Base64.encode(instance.digest(), 0)).trim();
            }
        }
    }

    public static String readFullyToString(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append(new String(bArr, 0, read));
        }
    }

    public static int readFullyToBuffer(byte[] bArr, int i, InputStream inputStream) throws IOException {
        int i2 = 0;
        int min = Math.min(bArr.length, i);
        while (min > 0) {
            int read = inputStream.read(bArr, i2, min);
            if (read == -1) {
                break;
            }
            i2 += read;
            min -= read;
        }
        return i2;
    }

    public static byte[] calMd5sum(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
        return instance.digest();
    }

    public static String calMd5sumString(byte[] bArr) throws NoSuchAlgorithmException {
        return getMd5StrFromBytes(calMd5sum(bArr));
    }

    public static String getMd5StrFromBytes(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuilder.toString();
    }

    public static boolean isEmptyOrNullString(String str) {
        return str == null || str.equals("");
    }

    public static byte[] readFullyToByteArray(InputStream inputStream) throws IOException {
        if (inputStream instanceof ByteArrayInputStream) {
            int available = inputStream.available();
            byte[] bArr = new byte[available];
            inputStream.read(bArr, 0, available);
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr2, 0, 4096);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr2, 0, read);
        }
    }

    public static int readFullyToLocalFile(InputStream inputStream, String str) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(new File(str));
        byte[] bArr = new byte[4096];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                fileOutputStream.write(bArr, 0, read);
                i += read;
            } else {
                fileOutputStream.close();
                return i;
            }
        }
    }

    public static void checkNotNullPointer(Object obj, String str) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void checkNotNullArg(Object obj, String str) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void checkNotEmptyStringArg(String str, String str2) throws IllegalArgumentException {
        if (isEmptyOrNullString(str)) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void checkFileReadable(String str) throws IllegalArgumentException {
        File file = new File(str);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not exist. ");
        } else if (file.isDirectory()) {
            throw new IllegalArgumentException("File is directory. ");
        } else if (!file.canRead()) {
            throw new IllegalArgumentException("File can not read. ");
        }
    }

    public static void checkFileWritable(String str) throws IllegalArgumentException {
        File file = new File(str);
        if (!file.exists() || (!file.isDirectory() && file.canWrite())) {
            file = file.getParentFile();
            if (!file.exists()) {
                throw new IllegalArgumentException(file + " not exist. ");
            } else if (!file.canWrite()) {
                throw new IllegalArgumentException(str + " can not write. ");
            } else {
                return;
            }
        }
        throw new IllegalArgumentException(str + " can not write. ");
    }

    public static String trimAndJoin(List<String> list, String str) {
        if (list.isEmpty()) {
            return "";
        }
        String str2 = null;
        for (String str3 : list) {
            String str32;
            if (str2 == null) {
                str32 = str32.trim();
            } else {
                str32 = str2 + str + str32.trim();
            }
            str2 = str32;
        }
        return str2;
    }

    public static void validateHostName(String str) {
        if (str == null || !hostPattern.matcher(str).matches()) {
            throw new IllegalArgumentException("The endpoint hostName is invalid! Must be like \"oss-cn-hangzhou.aliyuncs.com\" format");
        }
    }

    public static boolean detectIfProxyExist(Context context) {
        String property;
        int parseInt;
        if ((VERSION.SDK_INT >= 14 ? 1 : null) != null) {
            property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property2 == null) {
                property2 = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            }
            parseInt = Integer.parseInt(property2);
        } else {
            property = Proxy.getHost(context);
            parseInt = Proxy.getPort(context);
        }
        if (property == null || r0 == -1) {
            return false;
        }
        return true;
    }
}
