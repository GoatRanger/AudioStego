package dji.common.util;

import android.support.v4.view.MotionEventCompat;
import com.alipay.e.a.a.b.b.a;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class BytesUtil {
    public static byte getUnsignedBytes(short s) {
        return (byte) (s & 255);
    }

    public static byte[] getBytes(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & s) >> 8)};
    }

    public static byte[] getBytes(char c) {
        return new byte[]{(byte) c, (byte) (c >> 8)};
    }

    public static byte[] getBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((-16777216 & i) >> 24)};
    }

    public static byte[] getUnsignedBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8)};
    }

    public static byte getByte(int i) {
        return (byte) (i & 255);
    }

    public static byte[] getBytes(long j) {
        return new byte[]{(byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 32) & 255)), (byte) ((int) ((j >> 40) & 255)), (byte) ((int) ((j >> 48) & 255)), (byte) ((int) ((j >> 56) & 255))};
    }

    public static byte[] getUnsignedBytes(long j) {
        return new byte[]{(byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255))};
    }

    public static byte[] getBytes(float f) {
        return getBytes(Float.floatToIntBits(f));
    }

    public static byte[] getUnsignedBytes(float f) {
        return getUnsignedBytes(Float.floatToIntBits(f));
    }

    public static byte[] getBytes(double d) {
        return getBytes(Double.doubleToLongBits(d));
    }

    public static byte[] getUnsignedBytes(double d) {
        return getUnsignedBytes(Double.doubleToLongBits(d));
    }

    public static byte[] getBytes(String str, String str2) {
        return str.getBytes(Charset.forName(str2));
    }

    public static byte[] getBytes(String str) {
        return getBytes(str, "GBK");
    }

    public static short getInt(byte b) {
        return (short) (b & 255);
    }

    public static short getSignedInt(byte b) {
        return (short) b;
    }

    public static int getInt(short s) {
        return 65535 & s;
    }

    public static int getSignedInt(short s) {
        return s;
    }

    public static short getShort(byte[] bArr) {
        return ByteBuffer.wrap(fillBytes(bArr, 2)).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public static int getInt(byte[] bArr) {
        return ByteBuffer.wrap(fillBytes(bArr, 4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static long getLong(byte[] bArr) {
        return ByteBuffer.wrap(fillBytes(bArr, 8)).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    public static float getFloat(byte[] bArr) {
        return ByteBuffer.wrap(fillBytes(bArr, 4)).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    public static double getDouble(byte[] bArr) {
        return ByteBuffer.wrap(fillBytes(bArr, 8)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }

    private static byte[] fillBytes(byte[] bArr, int i) {
        int length = i - bArr.length;
        if (length > 0) {
            return arrayComb(bArr, new byte[length]);
        }
        return bArr;
    }

    public static String getString(byte[] bArr, String str) {
        return new String(bArr, Charset.forName(str));
    }

    public static String getString(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] == (byte) 0) {
                bArr = readBytes(bArr, 0, i);
                break;
            }
        }
        return getString(bArr, a.b);
    }

    public static String byte2hex(byte[] bArr) {
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

    public static String byte2hex(byte b) {
        String toHexString = Integer.toHexString(b & 255);
        if (toHexString.length() == 1) {
            return "0" + toHexString;
        }
        return toHexString;
    }

    public static String getBinaryStrFromByteArr(byte[] bArr) {
        String str = "";
        for (byte binaryStrFromByte : bArr) {
            str = str + getBinaryStrFromByte(binaryStrFromByte);
        }
        return str;
    }

    public static String getBinaryStrFromByte(byte b) {
        String str = "";
        for (int i = 0; i < 8; i++) {
            str = (b % 2) + str;
            b = (byte) (b >> 1);
        }
        return str;
    }

    public static byte[] readBytes(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return bArr2;
    }

    public static byte[] arrayComb(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public static byte[] arrayApend(byte[] bArr, byte b) {
        Object obj = new byte[(bArr.length + 1)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        obj[bArr.length] = b;
        return obj;
    }

    public static byte[] arrayRemove(byte[] bArr, int i) {
        int length = bArr.length - i;
        Object obj = new byte[length];
        System.arraycopy(bArr, i, obj, 0, length);
        return obj;
    }

    public static byte[] arrayPop(byte[] bArr, int i) {
        int length = bArr.length - i;
        Object obj = new byte[length];
        System.arraycopy(bArr, 0, obj, 0, length);
        return obj;
    }

    public static byte[] arraycopy(byte[] bArr, byte[] bArr2) {
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
