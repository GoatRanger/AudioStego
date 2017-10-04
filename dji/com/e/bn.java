package com.e;

import android.support.v4.media.TransportMediator;
import android.text.TextUtils;
import dji.pilot.usercenter.mode.n;
import java.util.zip.CRC32;

public class bn {
    public String A = null;
    public String B = null;
    public String C = null;
    public String D = null;
    public String E = null;
    public String F = null;
    public byte[] G = null;
    public String a = "1";
    public short b = (short) 0;
    public String c = null;
    public String d = null;
    public String e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = null;
    public String u = null;
    public String v = null;
    public String w = null;
    public String x = null;
    public String y = null;
    public String z = null;

    private String a(String str, int i) {
        String[] split = this.B.split("\\*")[i].split(",");
        return str.equals("lac") ? split[0] : str.equals("cellid") ? split[1] : str.equals("signal") ? split[2] : null;
    }

    public static void a(byte[] bArr, int i) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] b(java.lang.String r8) {
        /*
        r7 = this;
        r6 = 2;
        r4 = 6;
        r2 = 0;
        r0 = ":";
        r0 = r8.split(r0);
        r1 = new byte[r4];
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        r3 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r3 == r4) goto L_0x001e;
    L_0x0010:
        r0 = 6;
        r0 = new java.lang.String[r0];	 Catch:{ Throwable -> 0x0043 }
        r3 = r2;
    L_0x0014:
        r4 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r3 >= r4) goto L_0x001e;
    L_0x0017:
        r4 = "0";
        r0[r3] = r4;	 Catch:{ Throwable -> 0x0043 }
        r3 = r3 + 1;
        goto L_0x0014;
    L_0x001e:
        r3 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r2 >= r3) goto L_0x0041;
    L_0x0021:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r3 = r3.length();	 Catch:{ Throwable -> 0x0043 }
        if (r3 <= r6) goto L_0x0033;
    L_0x0029:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r4 = 0;
        r5 = 2;
        r3 = r3.substring(r4, r5);	 Catch:{ Throwable -> 0x0043 }
        r0[r2] = r3;	 Catch:{ Throwable -> 0x0043 }
    L_0x0033:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r4 = 16;
        r3 = java.lang.Integer.parseInt(r3, r4);	 Catch:{ Throwable -> 0x0043 }
        r3 = (byte) r3;	 Catch:{ Throwable -> 0x0043 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0043 }
        r2 = r2 + 1;
        goto L_0x001e;
    L_0x0041:
        r0 = r1;
    L_0x0042:
        return r0;
    L_0x0043:
        r0 = move-exception;
        r1 = "Req";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "getMacBa ";
        r2 = r2.append(r3);
        r2 = r2.append(r8);
        r2 = r2.toString();
        com.e.bc.a(r0, r1, r2);
        r0 = "00:00:00:00:00:00";
        r0 = r7.b(r0);
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bn.b(java.lang.String):byte[]");
    }

    public String a(String str) {
        if (!this.A.contains(str + ">")) {
            return "0";
        }
        int indexOf = this.A.indexOf(str + ">");
        return this.A.substring((indexOf + str.length()) + 1, this.A.indexOf("</" + str));
    }

    public byte[] a() {
        Object e;
        Object e2;
        b();
        int i = 3072;
        if (this.G != null) {
            i = 3072 + (this.G.length + 1);
        }
        Object obj = new byte[i];
        obj[0] = Byte.parseByte(this.a);
        Object b = br.b(this.b);
        System.arraycopy(b, 0, obj, 1, b.length);
        int length = b.length + 1;
        try {
            b = this.c.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th) {
            bc.a(th, "Req", "buildV4Dot2");
            obj[length] = null;
            length++;
        }
        try {
            b = this.d.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th2) {
            bc.a(th2, "Req", "buildV4Dot21");
            obj[length] = null;
            length++;
        }
        try {
            b = this.o.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th22) {
            bc.a(th22, "Req", "buildV4Dot22");
            obj[length] = null;
            length++;
        }
        try {
            b = this.e.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th222) {
            bc.a(th222, "Req", "buildV4Dot23");
            obj[length] = null;
            length++;
        }
        try {
            b = this.f.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th2222) {
            bc.a(th2222, "Req", "buildV4Dot24");
            obj[length] = null;
            length++;
        }
        try {
            b = this.g.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th22222) {
            bc.a(th22222, "Req", "buildV4Dot25");
            obj[length] = null;
            length++;
        }
        try {
            b = this.u.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th222222) {
            bc.a(th222222, "Req", "buildV4Dot26");
            obj[length] = null;
            length++;
        }
        try {
            b = this.h.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th2222222) {
            bc.a(th2222222, "Req", "buildV4Dot27");
            obj[length] = null;
            length++;
        }
        try {
            b = this.p.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th22222222) {
            bc.a(th22222222, "Req", "buildV4Dot28");
            obj[length] = null;
            length++;
        }
        try {
            b = this.q.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th222222222) {
            bc.a(th222222222, "Req", "buildV4Dot29");
            obj[length] = null;
            length++;
        }
        try {
            if (TextUtils.isEmpty(this.t)) {
                obj[length] = (byte) 0;
                i = length + 1;
            } else {
                b = b(this.t);
                obj[length] = (byte) b.length;
                length++;
                System.arraycopy(b, 0, obj, length, b.length);
                i = b.length + length;
            }
            length = i;
        } catch (Throwable th2222222222) {
            bc.a(th2222222222, "Req", "buildV4Dot219");
            obj[length] = null;
            length++;
        }
        try {
            b = this.v.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th22222222222) {
            bc.a(th22222222222, "Req", "buildV4Dot211");
            obj[length] = null;
            length++;
        }
        try {
            b = this.w.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        } catch (Throwable th222222222222) {
            bc.a(th222222222222, "Req", "buildV4Dot212");
            obj[length] = null;
            length++;
        }
        try {
            b = this.x.getBytes("GBK");
            obj[length] = (byte) b.length;
            length++;
            System.arraycopy(b, 0, obj, length, b.length);
            i = b.length + length;
        } catch (Throwable th2222222222222) {
            bc.a(th2222222222222, "Req", "buildV4Dot213");
            obj[length] = null;
            i = length + 1;
        }
        obj[i] = Byte.parseByte(this.y);
        i++;
        obj[i] = Byte.parseByte(this.j);
        i++;
        obj[i] = Byte.parseByte(this.z);
        i++;
        if (this.z.equals("1")) {
            e = br.e(a("mcc"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.e(a("mnc"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.e(a("lac"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.f(a("cellid"));
            System.arraycopy(e, 0, obj, i, e.length);
            length = e.length + i;
            i = Integer.parseInt(a("signal"));
            if (i > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i = 0;
            }
            obj[length] = (byte) i;
            i = length + 1;
            if (this.B.length() == 0) {
                obj[i] = null;
                i++;
            } else {
                int length2 = this.B.split("\\*").length;
                obj[i] = (byte) length2;
                i++;
                length = 0;
                while (length < length2) {
                    e2 = br.e(a("lac", length));
                    System.arraycopy(e2, 0, obj, i, e2.length);
                    i += e2.length;
                    e2 = br.f(a("cellid", length));
                    System.arraycopy(e2, 0, obj, i, e2.length);
                    int length3 = e2.length + i;
                    i = Integer.parseInt(a("signal", length));
                    if (i > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                        i = 0;
                    }
                    obj[length3] = (byte) i;
                    length++;
                    i = length3 + 1;
                }
            }
        } else if (this.z.equals("2")) {
            e = br.e(a("mcc"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.e(a("sid"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.e(a("nid"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.e(a("bid"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.f(a("lon"));
            System.arraycopy(e, 0, obj, i, e.length);
            i += e.length;
            e = br.f(a(n.x));
            System.arraycopy(e, 0, obj, i, e.length);
            length = e.length + i;
            i = Integer.parseInt(a("signal"));
            if (i > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i = 0;
            }
            obj[length] = (byte) i;
            i = length + 1;
            obj[i] = null;
            i++;
        }
        if (this.C.length() == 0) {
            obj[i] = null;
            i++;
        } else {
            String[] split;
            obj[i] = 1;
            length = i + 1;
            try {
                split = this.C.split(",");
                b = b(split[0]);
                System.arraycopy(b, 0, obj, length, b.length);
                length += b.length;
                b = split[2].getBytes("GBK");
                obj[length] = (byte) b.length;
                length++;
                System.arraycopy(b, 0, obj, length, b.length);
                length += b.length;
            } catch (Throwable th22222222222222) {
                bc.a(th22222222222222, "Req", "buildV4Dot216");
                b = b("00:00:00:00:00:00");
                System.arraycopy(b, 0, obj, length, b.length);
                i = b.length + length;
                obj[i] = null;
                i++;
                obj[i] = Byte.parseByte("0");
                i++;
            }
            i = Integer.parseInt(split[1]);
            if (i > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i = 0;
            }
            obj[length] = Byte.parseByte(String.valueOf(i));
            i = length + 1;
        }
        String[] split2 = this.D.split("\\*");
        if (TextUtils.isEmpty(this.D) || split2.length == 0) {
            obj[i] = null;
            length = i + 1;
        } else {
            obj[i] = (byte) split2.length;
            length = i + 1;
            for (String split3 : split2) {
                String[] split4 = split3.split(",");
                try {
                    b = b(split4[0]);
                } catch (Throwable th222222222222222) {
                    bc.a(th222222222222222, "Req", "buildV4Dot2110");
                    b = b("00:00:00:00:00:00");
                }
                System.arraycopy(b, 0, obj, length, b.length);
                length += b.length;
                try {
                    b = split4[2].getBytes("GBK");
                    obj[length] = (byte) b.length;
                    length++;
                    System.arraycopy(b, 0, obj, length, b.length);
                    i = b.length + length;
                } catch (Throwable th2222222222222222) {
                    bc.a(th2222222222222222, "Req", "buildV4Dot217");
                    obj[length] = null;
                    i = length + 1;
                }
                length = Integer.parseInt(split4[1]);
                if (length > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                    length = 0;
                }
                obj[i] = Byte.parseByte(String.valueOf(length));
                length = i + 1;
            }
            b = br.b(Integer.parseInt(this.E));
            System.arraycopy(b, 0, obj, length, b.length);
            length += b.length;
        }
        try {
            b = this.F.getBytes("GBK");
            if (b.length > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                b = null;
            }
            if (b == null) {
                obj[length] = (byte) 0;
                i = length + 1;
            } else {
                obj[length] = (byte) b.length;
                length++;
                System.arraycopy(b, 0, obj, length, b.length);
                i = b.length + length;
            }
        } catch (Throwable th22222222222222222) {
            bc.a(th22222222222222222, "Req", "buildV4Dot218");
            obj[length] = null;
            i = length + 1;
        }
        length = this.G != null ? this.G.length : 0;
        e2 = br.b(length);
        System.arraycopy(e2, 0, obj, i, e2.length);
        i += e2.length;
        if (length > 0) {
            System.arraycopy(this.G, 0, obj, i, this.G.length);
            i += this.G.length;
        }
        e = new byte[i];
        System.arraycopy(obj, 0, e, 0, i);
        CRC32 crc32 = new CRC32();
        crc32.update(e);
        e2 = br.a(crc32.getValue());
        byte[] bArr = new byte[(e2.length + i)];
        System.arraycopy(e, 0, bArr, 0, i);
        System.arraycopy(e2, 0, bArr, i, e2.length);
        i += e2.length;
        a(bArr, 0);
        return bArr;
    }

    public void b() {
        if (TextUtils.isEmpty(this.a)) {
            this.a = "";
        }
        if (TextUtils.isEmpty(this.c)) {
            this.c = "";
        }
        if (TextUtils.isEmpty(this.d)) {
            this.d = "";
        }
        if (TextUtils.isEmpty(this.e)) {
            this.e = "";
        }
        if (TextUtils.isEmpty(this.f)) {
            this.f = "";
        }
        if (TextUtils.isEmpty(this.g)) {
            this.g = "";
        }
        if (TextUtils.isEmpty(this.h)) {
            this.h = "";
        }
        if (TextUtils.isEmpty(this.i)) {
            this.i = "";
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "0";
        } else if (!(this.j.equals("1") || this.j.equals("2"))) {
            this.j = "0";
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "0";
        } else if (!(this.k.equals("0") || this.k.equals("1"))) {
            this.k = "0";
        }
        if (TextUtils.isEmpty(this.l)) {
            this.l = "";
        }
        if (TextUtils.isEmpty(this.m)) {
            this.m = "";
        }
        if (TextUtils.isEmpty(this.n)) {
            this.n = "";
        }
        if (TextUtils.isEmpty(this.o)) {
            this.o = "";
        }
        if (TextUtils.isEmpty(this.p)) {
            this.p = "";
        }
        if (TextUtils.isEmpty(this.q)) {
            this.q = "";
        }
        if (TextUtils.isEmpty(this.r)) {
            this.r = "";
        }
        if (TextUtils.isEmpty(this.s)) {
            this.s = "";
        }
        if (TextUtils.isEmpty(this.t)) {
            this.t = "";
        }
        if (TextUtils.isEmpty(this.u)) {
            this.u = "";
        }
        if (TextUtils.isEmpty(this.v)) {
            this.v = "";
        }
        if (TextUtils.isEmpty(this.w)) {
            this.w = "";
        }
        if (TextUtils.isEmpty(this.x)) {
            this.x = "";
        }
        if (TextUtils.isEmpty(this.y)) {
            this.y = "0";
        } else if (!(this.y.equals("1") || this.y.equals("2"))) {
            this.y = "0";
        }
        if (TextUtils.isEmpty(this.z)) {
            this.z = "0";
        } else if (!(this.z.equals("1") || this.z.equals("2"))) {
            this.z = "0";
        }
        if (TextUtils.isEmpty(this.A)) {
            this.A = "";
        }
        if (TextUtils.isEmpty(this.B)) {
            this.B = "";
        }
        if (TextUtils.isEmpty(this.C)) {
            this.C = "";
        }
        if (TextUtils.isEmpty(this.D)) {
            this.D = "";
        }
        if (TextUtils.isEmpty(this.E)) {
            this.E = "";
        }
        if (TextUtils.isEmpty(this.F)) {
            this.F = "";
        }
        if (this.G == null) {
            this.G = new byte[0];
        }
    }
}
