package com.dji.videouploadsdk.a;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class c extends FilterOutputStream {
    private a a;
    private int b = 0;

    public interface a {
        void a(int i);

        boolean a();
    }

    public c(OutputStream outputStream) {
        super(outputStream);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            if (this.a != null) {
                if (this.a.a()) {
                    throw new IOException();
                }
                int i4 = (int) ((((double) (i3 - i)) * 100.0d) / ((double) i2));
                if (this.b != i4) {
                    this.b = i4;
                    this.a.a(i4);
                }
            }
            super.write(bArr[i3]);
        }
    }
}
