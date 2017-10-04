package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ja {
    private static final String b = ja.class.getSimpleName();
    String a;
    private byte[] c;

    public static class a implements jh<ja> {
        public /* synthetic */ Object b(InputStream inputStream) throws IOException {
            return a(inputStream);
        }

        public void a(OutputStream outputStream, ja jaVar) throws IOException {
            if (outputStream != null && jaVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public void close() {
                    }
                };
                anonymousClass1.writeShort(jaVar.c.length);
                anonymousClass1.write(jaVar.c);
                anonymousClass1.writeShort(0);
                anonymousClass1.flush();
            }
        }

        public ja a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public void close() {
                }
            };
            ja jaVar = new ja();
            short readShort = anonymousClass2.readShort();
            if (readShort == (short) 0) {
                return null;
            }
            jaVar.c = new byte[readShort];
            anonymousClass2.readFully(jaVar.c);
            if (anonymousClass2.readUnsignedShort() == 0) {
            }
            return jaVar;
        }
    }

    private ja() {
        this.a = null;
        this.c = null;
    }

    public ja(byte[] bArr) {
        this.a = null;
        this.c = null;
        this.a = UUID.randomUUID().toString();
        this.c = bArr;
    }

    public String a() {
        return this.a;
    }

    public byte[] b() {
        return this.c;
    }

    public static String a(String str) {
        return ".yflurrydatasenderblock." + str;
    }
}
