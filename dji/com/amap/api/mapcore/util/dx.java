package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.h.a;
import com.f.a.a.g;
import dji.setting.ui.flyc.imu.b;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;
import org.msgpack.core.MessagePack.Code;
import org.xeustechnologies.jtar.TarHeader;

public class dx {
    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        a(byteArrayOutputStream, (byte) str.length(), a(str));
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(bArr);
        }
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        int i = 1;
        try {
            byteArrayOutputStream.write(new byte[]{b});
            int i2 = b > (byte) 0 ? 1 : 0;
            if ((b & 255) >= 255) {
                i = 0;
            }
            if ((i & i2) != 0) {
                byteArrayOutputStream.write(bArr);
            } else if ((b & 255) == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (Throwable e) {
            eb.a(e, "Utils", "writeField");
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String b = dr.b(a(str));
        return ((char) ((b.length() % 26) + 65)) + b;
    }

    public static String c(String str) {
        if (str.length() < 2) {
            return "";
        }
        return dr.a(str.substring(1));
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static byte[] a() {
        int i = 0;
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                bArr[i2] = Byte.parseByte(split[i2]);
            }
            split = new StringBuffer(new String(dr.b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split.length];
            while (i < split.length) {
                bArr2[i] = Byte.parseByte(split[i]);
                i++;
            }
            return bArr2;
        } catch (Throwable th) {
            eb.a(th, "Utils", "getIV");
            return new byte[16];
        }
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(a.b);
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        return stringBuilder.toString();
    }

    public static String a(Throwable th) {
        Writer stringWriter;
        Throwable cause;
        Throwable th2;
        String str = null;
        PrintWriter printWriter;
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
                try {
                    th.printStackTrace(printWriter);
                    for (cause = th.getCause(); cause != null; cause = cause.getCause()) {
                        cause.printStackTrace(printWriter);
                    }
                    str = stringWriter.toString();
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable cause2) {
                            cause2.printStackTrace();
                        }
                    }
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Throwable th3) {
                            cause2 = th3;
                            cause2.printStackTrace();
                            return str;
                        }
                    }
                } catch (Throwable th4) {
                    cause2 = th4;
                    try {
                        cause2.printStackTrace();
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable cause22) {
                                cause22.printStackTrace();
                            }
                        }
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Throwable th5) {
                                cause22 = th5;
                                cause22.printStackTrace();
                                return str;
                            }
                        }
                        return str;
                    } catch (Throwable th6) {
                        th2 = th6;
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable cause222) {
                                cause222.printStackTrace();
                            }
                        }
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Throwable cause2222) {
                                cause2222.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable cause22222) {
                printWriter = null;
                th2 = cause22222;
                if (stringWriter != null) {
                    stringWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th2;
            }
        } catch (Throwable cause222222) {
            printWriter = null;
            stringWriter = null;
            th2 = cause222222;
            if (stringWriter != null) {
                stringWriter.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
            throw th2;
        }
        return str;
    }

    public static String b(Map<String, String> map) {
        return d(a((Map) map));
    }

    public static String d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String[] split = str.split(a.b);
            Arrays.sort(split);
            StringBuffer stringBuffer = new StringBuffer();
            for (String append : split) {
                stringBuffer.append(append);
                stringBuffer.append(a.b);
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() > 1) {
                return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
            }
            return str;
        } catch (Throwable th) {
            eb.a(th, "Utils", "sortParams");
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            eb.a(th, "Utils", "gZip");
            return new byte[0];
        }
    }

    public static byte[] c(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        Throwable th;
        String str;
        String str2;
        Throwable th2;
        byte[] bArr2 = null;
        if (!(bArr == null || bArr.length == 0)) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                } catch (Throwable th3) {
                    zipOutputStream = bArr2;
                    th2 = th3;
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th2;
                }
                try {
                    zipOutputStream.putNextEntry(new ZipEntry("log"));
                    zipOutputStream.write(bArr);
                    zipOutputStream.closeEntry();
                    zipOutputStream.finish();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (Throwable th32) {
                            eb.a(th32, "Utils", "zip1");
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th4) {
                            th32 = th4;
                            str = "Utils";
                            str2 = "zip2";
                            eb.a(th32, str, str2);
                            return bArr2;
                        }
                    }
                } catch (Throwable th5) {
                    th32 = th5;
                    eb.a(th32, "Utils", "zip");
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return bArr2;
                }
            } catch (Throwable th322) {
                byteArrayOutputStream = bArr2;
                zipOutputStream = bArr2;
                th2 = th322;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
        return bArr2;
    }

    static PublicKey a(Context context) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException {
        Throwable th;
        Throwable th2;
        PublicKey publicKey = null;
        InputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(new byte[]{TarHeader.LF_NORMAL, (byte) -126, (byte) 2, (byte) -98, TarHeader.LF_NORMAL, (byte) -126, (byte) 2, (byte) 7, Code.FIXSTR_PREFIX, (byte) 3, (byte) 2, (byte) 1, (byte) 2, (byte) 2, (byte) 9, (byte) 0, (byte) -99, (byte) 15, (byte) 119, (byte) 58, (byte) 44, (byte) -19, (byte) -105, Code.FIXEXT16, TarHeader.LF_NORMAL, g.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, g.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 5, (byte) 5, (byte) 0, TarHeader.LF_NORMAL, (byte) 104, TarHeader.LF_LINK, g.STRUCT_END, TarHeader.LF_NORMAL, (byte) 9, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 6, (byte) 19, (byte) 2, (byte) 67, (byte) 78, TarHeader.LF_LINK, (byte) 19, TarHeader.LF_NORMAL, (byte) 17, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 8, g.ZERO_TAG, (byte) 10, (byte) 83, (byte) 111, (byte) 109, (byte) 101, (byte) 45, (byte) 83, (byte) 116, (byte) 97, (byte) 116, (byte) 101, TarHeader.LF_LINK, (byte) 16, TarHeader.LF_NORMAL, (byte) 14, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 7, g.ZERO_TAG, (byte) 7, (byte) 66, (byte) 101, (byte) 105, (byte) 106, (byte) 105, (byte) 110, (byte) 103, TarHeader.LF_LINK, (byte) 17, TarHeader.LF_NORMAL, (byte) 15, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 10, g.ZERO_TAG, (byte) 8, (byte) 65, (byte) 117, (byte) 116, (byte) 111, (byte) 110, (byte) 97, (byte) 118, (byte) 105, TarHeader.LF_LINK, (byte) 31, TarHeader.LF_NORMAL, (byte) 29, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 3, g.ZERO_TAG, (byte) 22, (byte) 99, (byte) 111, (byte) 109, (byte) 46, (byte) 97, (byte) 117, (byte) 116, (byte) 111, (byte) 110, (byte) 97, (byte) 118, (byte) 105, (byte) 46, (byte) 97, (byte) 112, (byte) 105, (byte) 115, (byte) 101, (byte) 114, (byte) 118, (byte) 101, (byte) 114, TarHeader.LF_NORMAL, (byte) 30, (byte) 23, g.SIMPLE_LIST, TarHeader.LF_LINK, TarHeader.LF_CHR, TarHeader.LF_NORMAL, (byte) 56, TarHeader.LF_LINK, TarHeader.LF_DIR, TarHeader.LF_NORMAL, TarHeader.LF_CONTIG, TarHeader.LF_DIR, TarHeader.LF_FIFO, TarHeader.LF_DIR, TarHeader.LF_DIR, (byte) 90, (byte) 23, g.SIMPLE_LIST, TarHeader.LF_SYMLINK, TarHeader.LF_CHR, TarHeader.LF_NORMAL, (byte) 56, TarHeader.LF_LINK, TarHeader.LF_CHR, TarHeader.LF_NORMAL, TarHeader.LF_CONTIG, TarHeader.LF_DIR, TarHeader.LF_FIFO, TarHeader.LF_DIR, TarHeader.LF_DIR, (byte) 90, TarHeader.LF_NORMAL, (byte) 104, TarHeader.LF_LINK, g.STRUCT_END, TarHeader.LF_NORMAL, (byte) 9, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 6, (byte) 19, (byte) 2, (byte) 67, (byte) 78, TarHeader.LF_LINK, (byte) 19, TarHeader.LF_NORMAL, (byte) 17, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 8, g.ZERO_TAG, (byte) 10, (byte) 83, (byte) 111, (byte) 109, (byte) 101, (byte) 45, (byte) 83, (byte) 116, (byte) 97, (byte) 116, (byte) 101, TarHeader.LF_LINK, (byte) 16, TarHeader.LF_NORMAL, (byte) 14, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 7, g.ZERO_TAG, (byte) 7, (byte) 66, (byte) 101, (byte) 105, (byte) 106, (byte) 105, (byte) 110, (byte) 103, TarHeader.LF_LINK, (byte) 17, TarHeader.LF_NORMAL, (byte) 15, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 10, g.ZERO_TAG, (byte) 8, (byte) 65, (byte) 117, (byte) 116, (byte) 111, (byte) 110, (byte) 97, (byte) 118, (byte) 105, TarHeader.LF_LINK, (byte) 31, TarHeader.LF_NORMAL, (byte) 29, (byte) 6, (byte) 3, (byte) 85, (byte) 4, (byte) 3, g.ZERO_TAG, (byte) 22, (byte) 99, (byte) 111, (byte) 109, (byte) 46, (byte) 97, (byte) 117, (byte) 116, (byte) 111, (byte) 110, (byte) 97, (byte) 118, (byte) 105, (byte) 46, (byte) 97, (byte) 112, (byte) 105, (byte) 115, (byte) 101, (byte) 114, (byte) 118, (byte) 101, (byte) 114, TarHeader.LF_NORMAL, (byte) -127, (byte) -97, TarHeader.LF_NORMAL, g.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, g.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 1, (byte) 5, (byte) 0, (byte) 3, (byte) -127, (byte) -115, (byte) 0, TarHeader.LF_NORMAL, (byte) -127, (byte) -119, (byte) 2, (byte) -127, (byte) -127, (byte) 0, (byte) -15, (byte) -27, Byte.MIN_VALUE, Code.EXT16, (byte) 118, Code.BIN16, (byte) 62, (byte) -127, (byte) 79, (byte) 125, Code.ARRAY16, (byte) 121, (byte) 0, (byte) 63, (byte) -125, (byte) -30, (byte) 118, (byte) 5, (byte) -85, (byte) -121, (byte) 91, (byte) 39, (byte) 90, (byte) 123, (byte) 72, (byte) -126, (byte) -83, Code.FIXEXT8, Code.INT64, (byte) -77, Code.FIXEXT4, (byte) -120, (byte) -81, (byte) 23, (byte) -2, (byte) -121, (byte) -29, (byte) 123, (byte) -7, (byte) 22, (byte) -114, (byte) -20, (byte) -25, (byte) 74, (byte) 67, Code.FIXEXT2, (byte) 65, (byte) 124, (byte) -7, g.STRUCT_END, (byte) -72, (byte) 38, (byte) -123, (byte) 16, Code.BIN32, b.a.eZ_, (byte) 32, (byte) 58, Code.MAP32, (byte) 14, g.STRUCT_END, (byte) 36, (byte) 60, g.SIMPLE_LIST, (byte) -121, (byte) 100, (byte) 105, Code.NEGFIXINT_PREFIX, (byte) 123, (byte) -31, (byte) 114, (byte) -101, Code.FIXEXT8, g.ZERO_TAG, (byte) 100, (byte) 33, (byte) -120, (byte) 63, (byte) 126, (byte) -123, TarHeader.LF_NORMAL, TarHeader.LF_CONTIG, b.a.eZ_, (byte) -116, (byte) 28, (byte) -10, (byte) 125, (byte) 59, Code.FIXEXT8, (byte) -95, (byte) -126, (byte) 118, (byte) -70, (byte) 43, (byte) -127, (byte) 9, (byte) 93, (byte) -100, b.a.fa_, (byte) -19, (byte) -114, Code.FIXEXT8, (byte) 85, (byte) -103, Code.STR32, (byte) -116, (byte) 118, (byte) 72, (byte) 86, (byte) 125, Code.FIXEXT2, (byte) -92, (byte) -11, (byte) 63, (byte) 69, Code.STR16, (byte) -10, (byte) -65, (byte) 126, Code.FLOAT64, (byte) -115, (byte) 60, (byte) 62, (byte) -86, (byte) -80, (byte) 1, (byte) 39, (byte) 19, (byte) 2, (byte) 3, (byte) 1, (byte) 0, (byte) 1, (byte) -93, b.a.eZ_, TarHeader.LF_NORMAL, (byte) 78, TarHeader.LF_NORMAL, (byte) 29, (byte) 6, (byte) 3, (byte) 85, (byte) 29, (byte) 14, (byte) 4, (byte) 22, (byte) 4, (byte) 20, (byte) -29, (byte) 63, TarHeader.LF_NORMAL, (byte) -79, (byte) -113, (byte) -13, (byte) 26, (byte) 85, (byte) 22, (byte) -27, (byte) 93, (byte) -5, (byte) 122, (byte) -103, (byte) -109, (byte) 14, (byte) -18, (byte) 6, (byte) -13, (byte) -109, TarHeader.LF_NORMAL, (byte) 31, (byte) 6, (byte) 3, (byte) 85, (byte) 29, (byte) 35, (byte) 4, (byte) 24, TarHeader.LF_NORMAL, (byte) 22, Byte.MIN_VALUE, (byte) 20, (byte) -29, (byte) 63, TarHeader.LF_NORMAL, (byte) -79, (byte) -113, (byte) -13, (byte) 26, (byte) 85, (byte) 22, (byte) -27, (byte) 93, (byte) -5, (byte) 122, (byte) -103, (byte) -109, (byte) 14, (byte) -18, (byte) 6, (byte) -13, (byte) -109, TarHeader.LF_NORMAL, g.ZERO_TAG, (byte) 6, (byte) 3, (byte) 85, (byte) 29, (byte) 19, (byte) 4, (byte) 5, TarHeader.LF_NORMAL, (byte) 3, (byte) 1, (byte) 1, (byte) -1, TarHeader.LF_NORMAL, g.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, g.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 5, (byte) 5, (byte) 0, (byte) 3, (byte) -127, (byte) -127, (byte) 0, Code.NEGFIXINT_PREFIX, (byte) -74, TarHeader.LF_CONTIG, (byte) -125, Code.BIN32, Byte.MIN_VALUE, (byte) 15, Code.FALSE, (byte) 100, Code.BIN8, (byte) 3, (byte) -86, b.a.fa_, (byte) 112, Code.TRUE, Code.EXT16, (byte) -69, (byte) -126, (byte) 8, (byte) 99, (byte) -100, Code.STR16, (byte) -108, Code.EXT16, (byte) -122, (byte) 125, (byte) 19, Code.NIL, Code.TRUE, (byte) 90, (byte) 85, Code.INT16, (byte) -8, (byte) -123, (byte) -103, (byte) 105, (byte) 77, Code.NEGFIXINT_PREFIX, (byte) -65, Code.FALSE, (byte) -28, (byte) 67, (byte) -28, (byte) -78, (byte) 116, Code.UINT64, (byte) 120, (byte) -2, (byte) 33, g.SIMPLE_LIST, (byte) 47, (byte) 46, (byte) -5, Code.FIXARRAY_PREFIX, (byte) 3, (byte) -101, (byte) -125, (byte) -115, (byte) 92, (byte) -124, (byte) 58, b.a.eZ_, (byte) 107, (byte) -67, (byte) 82, (byte) 6, Code.NEVER_USED, (byte) 39, (byte) -90, (byte) -1, (byte) 85, Code.BIN32, (byte) 82, (byte) -115, (byte) 119, g.SIMPLE_LIST, (byte) -4, Code.NEGFIXINT_PREFIX, (byte) 0, (byte) -98, (byte) 100, Code.FIXEXT8, (byte) 94, (byte) -75, (byte) 75, (byte) -103, (byte) 126, (byte) -80, (byte) 85, (byte) 40, (byte) -27, (byte) 60, (byte) 105, (byte) 28, (byte) -27, (byte) -21, (byte) -15, (byte) -98, (byte) 103, (byte) -88, (byte) -109, (byte) 35, (byte) -119, (byte) -27, (byte) -26, (byte) -122, (byte) 113, (byte) 63, (byte) 35, Code.MAP32, (byte) 70, (byte) 23, (byte) 33, (byte) -23, (byte) 66, (byte) 108, (byte) 56, (byte) 112, (byte) 46, (byte) -85, (byte) -123, (byte) -123, (byte) 33, (byte) 118, (byte) 27, (byte) 96, (byte) -7, (byte) -103});
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                KeyFactory instance2 = KeyFactory.getInstance("RSA");
                Certificate generateCertificate = instance.generateCertificate(byteArrayInputStream);
                if (generateCertificate == null || instance2 == null) {
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                            return publicKey;
                        }
                    }
                    return publicKey;
                }
                publicKey = instance2.generatePublic(new X509EncodedKeySpec(generateCertificate.getPublicKey().getEncoded()));
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
                        return publicKey;
                    }
                }
                return publicKey;
            } catch (Throwable th5) {
                th = th5;
                try {
                    th.printStackTrace();
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable th6) {
                            th = th6;
                            th.printStackTrace();
                            return publicKey;
                        }
                    }
                    return publicKey;
                } catch (Throwable th7) {
                    th2 = th7;
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th82) {
            byteArrayInputStream = publicKey;
            th2 = th82;
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            throw th2;
        }
    }

    static String d(byte[] bArr) {
        try {
            return f(bArr);
        } catch (Throwable th) {
            eb.a(th, "Utils", "HexString");
            return null;
        }
    }

    static String e(byte[] bArr) {
        try {
            return f(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String f(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    private static byte[] g(byte[] bArr) throws IOException, Throwable {
        GZIPOutputStream gZIPOutputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = null;
        if (bArr != null) {
            ByteArrayOutputStream byteArrayOutputStream;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream.write(bArr);
                        gZIPOutputStream.finish();
                        bArr2 = byteArrayOutputStream.toByteArray();
                        if (gZIPOutputStream != null) {
                            gZIPOutputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    gZIPOutputStream = null;
                    th = th2;
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th52) {
                byteArrayOutputStream = null;
                th = th52;
                gZIPOutputStream = null;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return bArr2;
    }

    public static String a(long j) {
        String str = null;
        try {
            str = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }
}
