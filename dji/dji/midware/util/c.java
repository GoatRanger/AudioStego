package dji.midware.util;

import android.support.v4.view.MotionEventCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class c {
    private static final int[] a = new int[]{48, 57, 65, 90, 97, 122};

    public static byte a(short s) {
        return (byte) (s & 255);
    }

    public static byte[] b(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & s) >> 8)};
    }

    public static byte[] a(char c) {
        return new byte[]{(byte) c, (byte) (c >> 8)};
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((-16777216 & i) >> 24)};
    }

    public static byte[] b(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8)};
    }

    public static byte c(int i) {
        return (byte) (i & 255);
    }

    public static byte[] a(long j) {
        return new byte[]{(byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 32) & 255)), (byte) ((int) ((j >> 40) & 255)), (byte) ((int) ((j >> 48) & 255)), (byte) ((int) ((j >> 56) & 255))};
    }

    public static byte[] b(long j) {
        return new byte[]{(byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255))};
    }

    public static byte[] a(float f) {
        return a(Float.floatToIntBits(f));
    }

    public static byte[] b(float f) {
        return b(Float.floatToIntBits(f));
    }

    public static byte[] a(double d) {
        return a(Double.doubleToLongBits(d));
    }

    public static byte[] b(double d) {
        return b(Double.doubleToLongBits(d));
    }

    private static byte[] a(String str, String str2) {
        return str.getBytes(Charset.forName(str2));
    }

    public static byte[] a(String str) {
        return a(str, "GBK");
    }

    public static byte[] b(String str) {
        return a(str, "UTF-8");
    }

    public static short a(byte b) {
        return (short) (b & 255);
    }

    public static short b(byte b) {
        return (short) b;
    }

    public static int c(short s) {
        return 65535 & s;
    }

    public static int d(short s) {
        return s;
    }

    public static short a(byte[] bArr) {
        return ByteBuffer.wrap(j(bArr, 2)).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public static int b(byte[] bArr) {
        return ByteBuffer.wrap(j(bArr, 4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static long c(byte[] bArr) {
        return ByteBuffer.wrap(j(bArr, 8)).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    public static float d(byte[] bArr) {
        return ByteBuffer.wrap(j(bArr, 4)).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    public static double e(byte[] bArr) {
        return ByteBuffer.wrap(j(bArr, 8)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }

    public static short a(byte[] bArr, int i) {
        return ByteBuffer.wrap(j(bArr, 2), i, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public static int b(byte[] bArr, int i) {
        return ByteBuffer.wrap(j(bArr, 4), i, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static long c(byte[] bArr, int i) {
        return ByteBuffer.wrap(j(bArr, 8), i, 8).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    public static float d(byte[] bArr, int i) {
        return ByteBuffer.wrap(j(bArr, 4), i, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    public static double e(byte[] bArr, int i) {
        return ByteBuffer.wrap(j(bArr, 8), i, 8).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }

    public static int f(byte[] bArr, int i) {
        Object obj = new byte[2];
        System.arraycopy(bArr, i, obj, 0, 2);
        return ByteBuffer.wrap(j(obj, 4), 0, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static long g(byte[] bArr, int i) {
        Object obj = new byte[4];
        System.arraycopy(bArr, i, obj, 0, 4);
        return ByteBuffer.wrap(j(obj, 8), 0, 8).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    private static byte[] j(byte[] bArr, int i) {
        int length = i - bArr.length;
        if (length > 0) {
            return a(bArr, new byte[length]);
        }
        return bArr;
    }

    private static String b(byte[] bArr, String str) {
        return new String(bArr, Charset.forName(str));
    }

    private static String a(byte[] bArr, int i, int i2, String str) {
        return new String(bArr, i, i2, Charset.forName(str));
    }

    public static String a(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (bArr[i3] == (byte) 0) {
                i2 = i3 - i;
                break;
            }
        }
        return a(bArr, i, i2, "GBK");
    }

    public static String b(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (bArr[i3] == (byte) 0) {
                i2 = i3 - i;
                break;
            }
        }
        return a(bArr, i, i2, "UTF-8");
    }

    public static String c(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        int i3 = i;
        while (i3 < i + i2 && i3 < bArr.length) {
            if (bArr[i3] == (byte) 0) {
                i2 = i3 - i;
                break;
            }
            i3++;
        }
        return new String(bArr, i, i2, Charset.forName("UTF-8"));
    }

    public static String f(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            if (bArr[i] == (byte) 0 || bArr[i] == (byte) -1) {
                bArr = e(bArr, 0, i);
                break;
            }
            i++;
        }
        return b(bArr, "GBK");
    }

    public static String g(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            if (bArr[i] == (byte) 0 || bArr[i] == (byte) -1) {
                bArr = e(bArr, 0, i);
                break;
            }
            i++;
        }
        return b(bArr, "UTF-8");
    }

    public static String h(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = "0" + toHexString;
            }
            str = str + toHexString;
        }
        return str;
    }

    public static String i(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = "0" + toHexString;
            }
            str = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + toHexString;
        }
        return str;
    }

    public static String d(byte[] bArr, int i, int i2) {
        String str = "";
        for (int i3 = i; i3 < i + i2; i3++) {
            String toHexString = Integer.toHexString(bArr[i3] & 255);
            if (toHexString.length() == 1) {
                toHexString = "0" + toHexString;
            }
            str = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + toHexString;
        }
        return str;
    }

    public static String a(byte[] bArr, String str) {
        String str2 = "";
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i] & 255);
            if (toHexString.length() == 1) {
                toHexString = "0" + toHexString;
            }
            if (i == 0) {
                str2 = str2 + toHexString;
            } else {
                str2 = str2 + str + toHexString;
            }
        }
        return str2;
    }

    public static String c(byte b) {
        String toHexString = Integer.toHexString(b & 255);
        if (toHexString.length() == 1) {
            return "0" + toHexString;
        }
        return toHexString;
    }

    public static String d(int i) {
        String toHexString = Integer.toHexString(i & 255);
        if (toHexString.length() == 1) {
            return "0" + toHexString;
        }
        return toHexString;
    }

    public static String j(byte[] bArr) {
        String str = "";
        for (byte d : bArr) {
            str = str + d(d);
        }
        return str;
    }

    public static String d(byte b) {
        String str = "";
        for (int i = 0; i < 8; i++) {
            str = (b % 2) + str;
            b = (byte) (b >> 1);
        }
        return str;
    }

    public static byte[] c(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        int length = trim.length();
        if (length == 0 || length % 2 == 1) {
            return null;
        }
        byte[] bArr = new byte[(length / 2)];
        int i = 0;
        while (i < trim.length()) {
            try {
                bArr[i / 2] = (byte) Integer.decode("0X" + trim.substring(i, i + 2)).intValue();
                i += 2;
            } catch (Exception e) {
                return null;
            }
        }
        return bArr;
    }

    public static byte[] e(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return obj;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public static byte[] a(byte[] bArr, byte b) {
        Object obj = new byte[(bArr.length + 1)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        obj[bArr.length] = b;
        return obj;
    }

    public static byte[] h(byte[] bArr, int i) {
        int length = bArr.length - i;
        Object obj = new byte[length];
        System.arraycopy(bArr, i, obj, 0, length);
        return obj;
    }

    public static byte[] i(byte[] bArr, int i) {
        int length = bArr.length - i;
        Object obj = new byte[length];
        System.arraycopy(bArr, 0, obj, 0, length);
        return obj;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, int i) {
        System.arraycopy(bArr, 0, bArr2, i, bArr.length);
        return bArr2;
    }

    public static byte e(int i) {
        return (byte) (((i / 10) * 16) + (i % 10));
    }

    public static boolean e(byte b) {
        return (a[0] <= b && b <= a[1]) || ((a[2] <= b && b <= a[3]) || (a[4] <= b && b <= a[5]));
    }
}
