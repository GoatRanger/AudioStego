package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class hl {
    private static final String a = hl.class.getSimpleName();
    private boolean b;
    private long c;
    private final List<hi> d = new ArrayList();

    public static class a implements jh<hl> {
        public /* synthetic */ Object b(InputStream inputStream) throws IOException {
            return a(inputStream);
        }

        public void a(OutputStream outputStream, hl hlVar) throws IOException {
            throw new UnsupportedOperationException("Serialization not supported");
        }

        public hl a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass1 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public void close() {
                }
            };
            hl hlVar = new hl();
            anonymousClass1.readUTF();
            anonymousClass1.readUTF();
            hlVar.b = anonymousClass1.readBoolean();
            hlVar.c = anonymousClass1.readLong();
            while (true) {
                int readUnsignedShort = anonymousClass1.readUnsignedShort();
                if (readUnsignedShort == 0) {
                    return hlVar;
                }
                byte[] bArr = new byte[readUnsignedShort];
                anonymousClass1.readFully(bArr);
                hlVar.d.add(0, new hi(bArr));
            }
        }
    }

    public boolean a() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public List<hi> c() {
        return Collections.unmodifiableList(this.d);
    }
}
