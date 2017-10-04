package dji.thirdparty.g.a;

import dji.thirdparty.g.e;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class l {
    public byte[] a(byte[] bArr, int i) throws e, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            if (i2 >= bArr.length) {
                throw new e("Tiff: Unpack bits source exhausted: " + i2 + ", done + " + i3 + ", expected + " + i);
            }
            int i4 = i2 + 1;
            byte b = bArr[i2];
            int i5;
            if (b >= (byte) 0 && b <= Byte.MAX_VALUE) {
                int i6 = b + 1;
                i3 += i6;
                i2 = i4;
                i4 = 0;
                while (i4 < i6) {
                    i5 = i2 + 1;
                    byteArrayOutputStream.write(bArr[i2]);
                    i4++;
                    i2 = i5;
                }
                i4 = i3;
            } else if (b >= (byte) -127 && b <= (byte) -1) {
                i2 = i4 + 1;
                byte b2 = bArr[i4];
                i5 = (-b) + 1;
                i4 = i3 + i5;
                for (i3 = 0; i3 < i5; i3++) {
                    byteArrayOutputStream.write(b2);
                }
            } else if (b == Byte.MIN_VALUE) {
                throw new e("Packbits: " + b);
            } else {
                i2 = i4;
                i4 = i3;
            }
            i3 = i4;
        }
        return byteArrayOutputStream.toByteArray();
    }

    private int b(byte[] bArr, int i) {
        if (i >= bArr.length) {
            return -1;
        }
        byte b = bArr[i];
        int i2 = i + 1;
        while (i2 < bArr.length) {
            byte b2 = bArr[i2];
            if (b2 == b) {
                return i2 - 1;
            }
            i2++;
            b = b2;
        }
        return -1;
    }

    private int c(byte[] bArr, int i) {
        byte b = bArr[i];
        int i2 = i + 1;
        while (i2 < bArr.length && bArr[i2] == b) {
            i2++;
        }
        return i2 - i;
    }

    public byte[] a(byte[] bArr) throws IOException {
        k kVar = new k(bArr.length * 2);
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            i++;
            int b = b(bArr, i2);
            int min;
            if (b == i2) {
                min = Math.min(c(bArr, b), 128);
                kVar.write(-(min - 1));
                kVar.write(bArr[i2]);
                i2 += min;
            } else {
                int c;
                min = b - i2;
                if (b > 0) {
                    c = c(bArr, b);
                    if (c < 3) {
                        int i3 = (i2 + min) + c;
                        c = b(bArr, i3);
                        if (c != i3) {
                            min = c - i2;
                            if (c < 0) {
                                min = bArr.length - i2;
                            }
                            b = Math.min(min, 128);
                            kVar.write(b - 1);
                            min = 0;
                            while (min < b) {
                                kVar.write(bArr[i2]);
                                min++;
                                i2++;
                            }
                        }
                    }
                }
                c = b;
                if (c < 0) {
                    min = bArr.length - i2;
                }
                b = Math.min(min, 128);
                kVar.write(b - 1);
                min = 0;
                while (min < b) {
                    kVar.write(bArr[i2]);
                    min++;
                    i2++;
                }
            }
        }
        return kVar.a();
    }
}
