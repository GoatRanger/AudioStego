package com.autonavi.amap.mapcore;

import java.io.ByteArrayOutputStream;

public class Convert {
    public static int getInt(byte[] bArr, int i) {
        return ((((bArr[i + 3] & 255) << 24) + ((bArr[i + 2] & 255) << 16)) + ((bArr[i + 1] & 255) << 8)) + ((bArr[i + 0] & 255) << 0);
    }

    public static short getShort(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & 255) << 8) + ((bArr[i + 0] & 255) << 0));
    }

    public static int getUShort(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) + ((bArr[i + 0] & 255) << 0);
    }

    public static boolean getBit(byte b, int i) {
        if ((((b << (32 - i)) >>> (32 - i)) >>> (i - 1)) > 0) {
            return true;
        }
        return false;
    }

    public static byte[] copyString(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return obj;
    }

    public static byte[] covertBytes(byte b) {
        return new byte[]{b};
    }

    public static byte[] convertInt(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static int getNum(byte b, int i, int i2) {
        return ((b << ((32 - i2) - 1)) >>> ((32 - i2) - 1)) >>> i;
    }

    public static int getNum(short s, int i, int i2) {
        return ((s << (32 - i2)) >>> (32 - i2)) >>> (i - 1);
    }

    public static byte[] convertShort(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    public static void writeInt(byte[] bArr, int i, int i2) {
        System.arraycopy(convertInt(i2), 0, bArr, i, 4);
    }

    public static void writeShort(byte[] bArr, int i, short s) {
        System.arraycopy(convertShort(s), 0, bArr, i, 2);
    }

    public static void moveArray(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        Object obj = new byte[i3];
        System.arraycopy(bArr, i, obj, 0, i3);
        System.arraycopy(obj, 0, bArr2, i2, i3);
    }

    public static String getString(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static byte[] getSubBytes(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return obj;
    }

    public static byte[] get1BString(String str) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(new byte[]{(byte) str.getBytes("UTF-8").length});
            byteArrayOutputStream.write(r1);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[1];
        }
    }

    public static byte[] get2BString(String str) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = str.getBytes("UTF-8");
            byteArrayOutputStream.write(convertShort(bytes.length));
            byteArrayOutputStream.write(bytes);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[1];
        }
    }

    public static double convertDouble(byte[] bArr, int i) {
        long j = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j += ((long) (bArr[i2 + i] & 255)) << (i2 * 8);
        }
        return Double.longBitsToDouble(j);
    }

    public static byte[] getDouble(double d) {
        byte[] bArr = new byte[8];
        String toHexString = Long.toHexString(Double.doubleToLongBits(d));
        for (int i = 0; i < 8; i++) {
            bArr[7 - i] = (byte) Integer.parseInt(toHexString.substring(i * 2, (i * 2) + 2), 16);
        }
        return bArr;
    }

    public static void convert1bString(byte[] bArr, int i, b bVar) {
        try {
            bVar.a = bArr[i];
            bVar.b = new String(bArr, i + 1, bVar.a, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            bVar.a = 0;
            bVar.b = "";
        }
    }

    public static void convert2bString(byte[] bArr, int i, b bVar) {
        try {
            bVar.a = getShort(bArr, i);
            bVar.b = new String(bArr, i + 2, bVar.a, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            bVar.a = 0;
            bVar.b = "";
        }
    }

    public static final String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(toHexString.toUpperCase());
        }
        return stringBuffer.toString();
    }
}
