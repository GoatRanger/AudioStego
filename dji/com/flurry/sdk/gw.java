package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class gw {
    private long a;
    private boolean b;
    private byte[] c;

    public static class a implements jh<gw> {
        public /* synthetic */ Object b(InputStream inputStream) throws IOException {
            return a(inputStream);
        }

        public void a(OutputStream outputStream, gw gwVar) throws IOException {
            if (outputStream != null && gwVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public void close() {
                    }
                };
                anonymousClass1.writeLong(gwVar.a);
                anonymousClass1.writeBoolean(gwVar.b);
                anonymousClass1.writeInt(gwVar.c.length);
                anonymousClass1.write(gwVar.c);
                anonymousClass1.flush();
            }
        }

        public gw a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public void close() {
                }
            };
            gw gwVar = new gw();
            gwVar.a = anonymousClass2.readLong();
            gwVar.b = anonymousClass2.readBoolean();
            gwVar.c = new byte[anonymousClass2.readInt()];
            anonymousClass2.readFully(gwVar.c);
            return gwVar;
        }
    }

    public long a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public byte[] c() {
        return this.c;
    }

    public void a(long j) {
        this.a = j;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }
}
