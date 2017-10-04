package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class hb extends ix {
    protected long a;

    public static class a implements jh<hb> {
        public /* synthetic */ Object b(InputStream inputStream) throws IOException {
            return a(inputStream);
        }

        public void a(OutputStream outputStream, hb hbVar) throws IOException {
            if (outputStream != null && hbVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public void close() {
                    }
                };
                anonymousClass1.writeLong(hbVar.f());
                anonymousClass1.writeBoolean(hbVar.g());
                anonymousClass1.writeInt(hbVar.h());
                anonymousClass1.writeUTF(hbVar.i());
                anonymousClass1.writeUTF(hbVar.j());
                anonymousClass1.writeLong(hbVar.a);
                anonymousClass1.flush();
            }
        }

        public hb a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public void close() {
                }
            };
            hb hbVar = new hb();
            hbVar.a(anonymousClass2.readLong());
            hbVar.a(anonymousClass2.readBoolean());
            hbVar.a(anonymousClass2.readInt());
            hbVar.b(anonymousClass2.readUTF());
            hbVar.c(anonymousClass2.readUTF());
            hbVar.a = anonymousClass2.readLong();
            return hbVar;
        }
    }

    public static class b implements jh<hb> {
        public /* synthetic */ Object b(InputStream inputStream) throws IOException {
            return a(inputStream);
        }

        public void a(OutputStream outputStream, hb hbVar) throws IOException {
            throw new UnsupportedOperationException("Serialization not supported");
        }

        public hb a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass1 = new DataInputStream(this, inputStream) {
                final /* synthetic */ b a;

                public void close() {
                }
            };
            hb hbVar = new hb();
            hbVar.a(anonymousClass1.readLong());
            hbVar.a(anonymousClass1.readBoolean());
            hbVar.a(anonymousClass1.readInt());
            hbVar.a = anonymousClass1.readLong();
            hbVar.a(anonymousClass1.readUTF());
            return hbVar;
        }
    }

    private hb() {
    }

    public hb(long j, String str, String str2, long j2) {
        a(str2);
        a(j2);
        this.a = j;
    }

    public long a() {
        return this.a;
    }
}
