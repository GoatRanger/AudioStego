package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class hi {
    private static final String b = hi.class.getSimpleName();
    byte[] a;

    public static class a implements jh<hi> {
        public /* synthetic */ Object b(InputStream inputStream) throws IOException {
            return a(inputStream);
        }

        public void a(OutputStream outputStream, hi hiVar) throws IOException {
            if (outputStream != null && hiVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public void close() {
                    }
                };
                int i = 0;
                if (hiVar.a != null) {
                    i = hiVar.a.length;
                }
                anonymousClass1.writeShort(i);
                if (i > 0) {
                    anonymousClass1.write(hiVar.a);
                }
                anonymousClass1.flush();
            }
        }

        public hi a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public void close() {
                }
            };
            hi hiVar = new hi();
            int readUnsignedShort = anonymousClass2.readUnsignedShort();
            if (readUnsignedShort > 0) {
                byte[] bArr = new byte[readUnsignedShort];
                anonymousClass2.readFully(bArr);
                hiVar.a = bArr;
            }
            return hiVar;
        }
    }

    private hi() {
    }

    public hi(byte[] bArr) {
        this.a = bArr;
    }

    public hi(hj hjVar) throws IOException {
        Closeable dataOutputStream;
        Throwable e;
        Closeable closeable = null;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                int i;
                int i2;
                dataOutputStream.writeShort(5);
                dataOutputStream.writeUTF(hjVar.a());
                dataOutputStream.writeLong(hjVar.b());
                dataOutputStream.writeLong(hjVar.c());
                dataOutputStream.writeLong(hjVar.d());
                Map e2 = hjVar.e();
                if (e2 == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(e2.size());
                    for (Entry entry : e2.entrySet()) {
                        dataOutputStream.writeUTF((String) entry.getKey());
                        dataOutputStream.writeUTF((String) entry.getValue());
                        dataOutputStream.writeByte(0);
                    }
                }
                dataOutputStream.writeUTF(hjVar.f());
                dataOutputStream.writeUTF(hjVar.g());
                dataOutputStream.writeByte(hjVar.h());
                dataOutputStream.writeByte(hjVar.i());
                dataOutputStream.writeUTF(hjVar.j());
                if (hjVar.k() == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeDouble(jz.a(hjVar.k().getLatitude(), 3));
                    dataOutputStream.writeDouble(jz.a(hjVar.k().getLongitude(), 3));
                    dataOutputStream.writeFloat(hjVar.k().getAccuracy());
                }
                dataOutputStream.writeInt(hjVar.l());
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(hjVar.m());
                if (hjVar.n() == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeLong(hjVar.n().longValue());
                }
                e2 = hjVar.o();
                if (e2 == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(e2.size());
                    for (Entry entry2 : e2.entrySet()) {
                        dataOutputStream.writeUTF((String) entry2.getKey());
                        dataOutputStream.writeInt(((he) entry2.getValue()).a);
                    }
                }
                List<hf> p = hjVar.p();
                if (p == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(p.size());
                    for (hf e3 : p) {
                        dataOutputStream.write(e3.e());
                    }
                }
                dataOutputStream.writeBoolean(hjVar.q());
                List s = hjVar.s();
                if (s != null) {
                    int i3 = 0;
                    i = 0;
                    for (i2 = 0; i2 < s.size(); i2++) {
                        i3 += ((hd) s.get(i2)).a();
                        if (i3 > 160000) {
                            in.a(5, b, "Error Log size exceeded. No more event details logged.");
                            i2 = i;
                            break;
                        }
                        i++;
                    }
                    i2 = i;
                } else {
                    i2 = 0;
                }
                dataOutputStream.writeInt(hjVar.r());
                dataOutputStream.writeShort(i2);
                for (i = 0; i < i2; i++) {
                    dataOutputStream.write(((hd) s.get(i)).b());
                }
                dataOutputStream.writeInt(-1);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                this.a = byteArrayOutputStream.toByteArray();
                jz.a(dataOutputStream);
            } catch (IOException e4) {
                e = e4;
                closeable = dataOutputStream;
                try {
                    in.a(6, b, "", e);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    dataOutputStream = closeable;
                    jz.a(dataOutputStream);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                jz.a(dataOutputStream);
                throw e;
            }
        } catch (IOException e5) {
            e = e5;
            in.a(6, b, "", e);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            dataOutputStream = null;
            jz.a(dataOutputStream);
            throw e;
        }
    }

    public byte[] a() {
        return this.a;
    }
}
