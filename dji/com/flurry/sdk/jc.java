package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class jc {
    private String a;

    public static class a implements jh<jc> {
        public /* synthetic */ Object b(InputStream inputStream) throws IOException {
            return a(inputStream);
        }

        public void a(OutputStream outputStream, jc jcVar) throws IOException {
            if (outputStream != null && jcVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public void close() {
                    }
                };
                anonymousClass1.writeUTF(jcVar.a);
                anonymousClass1.flush();
            }
        }

        public jc a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public void close() {
                }
            };
            jc jcVar = new jc();
            jcVar.a = anonymousClass2.readUTF();
            return jcVar;
        }
    }

    private jc() {
    }

    public jc(String str) {
        this.a = str;
    }

    public String a() {
        return this.a;
    }
}
