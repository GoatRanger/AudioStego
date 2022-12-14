package com.tencent.android.tpush.service.channel.security;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class b extends FilterOutputStream {
    private static final char[] a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private int b;
    private int c;

    public b(OutputStream outputStream) {
        super(outputStream);
    }

    public void write(int i) {
        if (i < 0) {
            i += 256;
        }
        int i2;
        if (this.b % 3 == 0) {
            i2 = i >> 2;
            this.c = i & 3;
            this.out.write(a[i2]);
        } else if (this.b % 3 == 1) {
            i2 = ((this.c << 4) + (i >> 4)) & 63;
            this.c = i & 15;
            this.out.write(a[i2]);
        } else if (this.b % 3 == 2) {
            this.out.write(a[((this.c << 2) + (i >> 6)) & 63]);
            this.out.write(a[i & 63]);
            this.c = 0;
        }
        this.b++;
        if (this.b % 57 == 0) {
            this.out.write(10);
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            write(bArr[i + i3]);
        }
    }

    public void close() {
        if (this.b % 3 == 1) {
            this.out.write(a[(this.c << 4) & 63]);
            this.out.write(61);
            this.out.write(61);
        } else if (this.b % 3 == 2) {
            this.out.write(a[(this.c << 2) & 63]);
            this.out.write(61);
        }
        super.close();
    }

    public static String a(byte[] bArr) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) (((double) bArr.length) * 1.37d));
        b bVar = new b(byteArrayOutputStream);
        try {
            bVar.write(bArr);
            bVar.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toString("UTF-8");
        } catch (IOException e) {
            return null;
        }
    }
}
