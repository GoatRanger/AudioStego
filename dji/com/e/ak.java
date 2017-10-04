package com.e;

import android.content.Context;
import dji.pilot.usercenter.protocol.d;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class ak extends ao {
    protected Context a;
    protected dc b;

    public ak(Context context, dc dcVar) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        this.b = dcVar;
    }

    private byte[] k() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(dd.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                dg.a(th, "BinaryRequest", "getBinaryHead");
                return toByteArray;
            }
        } catch (Throwable th2) {
            dg.a(th2, "BinaryRequest", "getBinaryHead");
        }
        return null;
    }

    private byte[] l() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] g = g();
            if (g == null || g.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                g = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return g;
                } catch (Throwable th) {
                    dg.a(th, "BinaryRequest", "getRequestRawData");
                    return g;
                }
            }
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            byteArrayOutputStream.write(a(g));
            byteArrayOutputStream.write(g);
            g = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return g;
            } catch (Throwable th2) {
                dg.a(th2, "BinaryRequest", "getRequestRawData");
                return g;
            }
        } catch (Throwable th3) {
            dg.a(th3, "BinaryRequest", "getRequestRawData");
        }
        return new byte[]{(byte) 0};
    }

    private byte[] m() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] h = h();
            if (h == null || h.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                h = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return h;
                } catch (Throwable th) {
                    dg.a(th, "BinaryRequest", "getRequestEncryptData");
                    return h;
                }
            }
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            h = cw.a(this.a, h);
            byteArrayOutputStream.write(a(h));
            byteArrayOutputStream.write(h);
            h = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return h;
            } catch (Throwable th2) {
                dg.a(th2, "BinaryRequest", "getRequestEncryptData");
                return h;
            }
        } catch (Throwable th3) {
            dg.a(th3, "BinaryRequest", "getRequestEncryptData");
        }
        return new byte[]{(byte) 0};
    }

    public Map<String, String> a() {
        String f = cu.f(this.a);
        String a = cw.a();
        String a2 = cw.a(this.a, a, "key=" + f);
        Map<String, String> hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put(d.M, f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    protected byte[] a(byte[] bArr) {
        byte length = (byte) (bArr.length % 256);
        return new byte[]{(byte) (bArr.length / 256), length};
    }

    public final byte[] d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(k());
            byteArrayOutputStream.write(e());
            byteArrayOutputStream.write(l());
            byteArrayOutputStream.write(m());
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                dg.a(th, "BinaryRequest", "getEntityBytes");
                return toByteArray;
            }
        } catch (Throwable th2) {
            dg.a(th2, "BinaryRequest", "getEntityBytes");
        }
        return null;
    }

    public byte[] e() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a = cw.a(this.a, false);
            byte[] a2 = a(a);
            byteArrayOutputStream.write(new byte[]{(byte) 3});
            byteArrayOutputStream.write(a2);
            byteArrayOutputStream.write(a);
            a = dd.a(f());
            if (a == null || a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            } else {
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            }
            a = dd.a(String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.b.b(), this.b.a()}));
            if (a == null || a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            } else {
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            }
            a = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return a;
            } catch (Throwable th) {
                dg.a(th, "BinaryRequest", "getRequestEncryptData");
                return a;
            }
        } catch (Throwable th2) {
            dg.a(th2, "BinaryRequest", "getRequestEncryptData");
        }
        return new byte[]{(byte) 0};
    }

    protected String f() {
        return "2.1";
    }

    public abstract byte[] g();

    public abstract byte[] h();
}
