package org.xeustechnologies.jtar;

public class Octal {
    public static long parseOctal(byte[] bArr, int i, int i2) {
        long j = 0;
        Object obj = 1;
        int i3 = i + i2;
        while (i < i3 && bArr[i] != (byte) 0) {
            if (bArr[i] == (byte) 32 || bArr[i] == TarHeader.LF_NORMAL) {
                if (obj == null) {
                    if (bArr[i] == (byte) 32) {
                        break;
                    }
                }
                continue;
                i++;
            }
            obj = null;
            j = (j << 3) + ((long) (bArr[i] - 48));
            i++;
        }
        return j;
    }

    public static int getOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        bArr[i + i3] = (byte) 0;
        i3--;
        bArr[i + i3] = (byte) 32;
        i3--;
        if (j == 0) {
            bArr[i + i3] = TarHeader.LF_NORMAL;
            i3--;
        } else {
            while (i3 >= 0 && j > 0) {
                bArr[i + i3] = (byte) (((byte) ((int) (7 & j))) + 48);
                j >>= 3;
                i3--;
            }
        }
        while (i3 >= 0) {
            bArr[i + i3] = (byte) 32;
            i3--;
        }
        return i + i2;
    }

    public static int getCheckSumOctalBytes(long j, byte[] bArr, int i, int i2) {
        getOctalBytes(j, bArr, i, i2);
        bArr[(i + i2) - 1] = (byte) 32;
        bArr[(i + i2) - 2] = (byte) 0;
        return i + i2;
    }

    public static int getLongOctalBytes(long j, byte[] bArr, int i, int i2) {
        Object obj = new byte[(i2 + 1)];
        getOctalBytes(j, obj, 0, i2 + 1);
        System.arraycopy(obj, 0, bArr, i, i2);
        return i + i2;
    }
}
