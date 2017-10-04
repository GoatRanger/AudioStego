package com.f.a.a;

import com.alipay.sdk.j.i;
import dji.pilot.usercenter.protocol.d;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class c {
    private StringBuilder a;
    private int b = 0;

    private void a(String str) {
        for (int i = 0; i < this.b; i++) {
            this.a.append('\t');
        }
        if (str != null) {
            this.a.append(str).append(": ");
        }
    }

    public c(StringBuilder stringBuilder, int i) {
        this.a = stringBuilder;
        this.b = i;
    }

    public c(StringBuilder stringBuilder) {
        this.a = stringBuilder;
    }

    public c a(boolean z, String str) {
        a(str);
        this.a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    public c a(boolean z, boolean z2) {
        this.a.append(z ? 'T' : 'F');
        if (z2) {
            this.a.append("|");
        }
        return this;
    }

    public c a(byte b, String str) {
        a(str);
        this.a.append(b).append('\n');
        return this;
    }

    public c a(byte b, boolean z) {
        this.a.append(b);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(char c, String str) {
        a(str);
        this.a.append(c).append('\n');
        return this;
    }

    public c a(char c, boolean z) {
        this.a.append(c);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(short s, String str) {
        a(str);
        this.a.append(s).append('\n');
        return this;
    }

    public c a(short s, boolean z) {
        this.a.append(s);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(int i, String str) {
        a(str);
        this.a.append(i).append('\n');
        return this;
    }

    public c a(int i, boolean z) {
        this.a.append(i);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(long j, String str) {
        a(str);
        this.a.append(j).append('\n');
        return this;
    }

    public c a(long j, boolean z) {
        this.a.append(j);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(float f, String str) {
        a(str);
        this.a.append(f).append('\n');
        return this;
    }

    public c a(float f, boolean z) {
        this.a.append(f);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(double d, String str) {
        a(str);
        this.a.append(d).append('\n');
        return this;
    }

    public c a(double d, boolean z) {
        this.a.append(d);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(String str, String str2) {
        a(str2);
        if (str == null) {
            this.a.append("null").append('\n');
        } else {
            this.a.append(str).append('\n');
        }
        return this;
    }

    public c a(String str, boolean z) {
        if (str == null) {
            this.a.append("null");
        } else {
            this.a.append(str);
        }
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(byte[] bArr, String str) {
        a(str);
        if (bArr == null) {
            this.a.append("null").append('\n');
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []").append('\n');
        } else {
            this.a.append(bArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (byte a : bArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public c a(byte[] bArr, boolean z) {
        if (bArr != null && bArr.length != 0) {
            this.a.append(a.a(bArr));
            if (z) {
                this.a.append("|");
            }
        } else if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(char[] cArr, String str) {
        a(str);
        if (cArr == null) {
            this.a.append("null").append('\n');
        } else if (cArr.length == 0) {
            this.a.append(cArr.length).append(", []").append('\n');
        } else {
            this.a.append(cArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (char a : cArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public c a(char[] cArr, boolean z) {
        if (cArr != null && cArr.length != 0) {
            this.a.append(new String(cArr));
            if (z) {
                this.a.append("|");
            }
        } else if (z) {
            this.a.append("|");
        }
        return this;
    }

    public c a(short[] sArr, String str) {
        a(str);
        if (sArr == null) {
            this.a.append("null").append('\n');
        } else if (sArr.length == 0) {
            this.a.append(sArr.length).append(", []").append('\n');
        } else {
            this.a.append(sArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (short a : sArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public c a(short[] sArr, boolean z) {
        if (sArr == null || sArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
        } else {
            this.a.append(d.G);
            c cVar = new c(this.a, this.b + 1);
            for (int i = 0; i < sArr.length; i++) {
                short s = sArr[i];
                if (i != 0) {
                    this.a.append("|");
                }
                cVar.a(s, false);
            }
            this.a.append(d.H);
            if (z) {
                this.a.append("|");
            }
        }
        return this;
    }

    public c a(int[] iArr, String str) {
        a(str);
        if (iArr == null) {
            this.a.append("null").append('\n');
        } else if (iArr.length == 0) {
            this.a.append(iArr.length).append(", []").append('\n');
        } else {
            this.a.append(iArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (int a : iArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public c a(int[] iArr, boolean z) {
        if (iArr == null || iArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
        } else {
            this.a.append(d.G);
            c cVar = new c(this.a, this.b + 1);
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                if (i != 0) {
                    this.a.append("|");
                }
                cVar.a(i2, false);
            }
            this.a.append(d.H);
            if (z) {
                this.a.append("|");
            }
        }
        return this;
    }

    public c a(long[] jArr, String str) {
        a(str);
        if (jArr == null) {
            this.a.append("null").append('\n');
        } else if (jArr.length == 0) {
            this.a.append(jArr.length).append(", []").append('\n');
        } else {
            this.a.append(jArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (long a : jArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public c a(long[] jArr, boolean z) {
        if (jArr == null || jArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
        } else {
            this.a.append(d.G);
            c cVar = new c(this.a, this.b + 1);
            for (int i = 0; i < jArr.length; i++) {
                long j = jArr[i];
                if (i != 0) {
                    this.a.append("|");
                }
                cVar.a(j, false);
            }
            this.a.append(d.H);
            if (z) {
                this.a.append("|");
            }
        }
        return this;
    }

    public c a(float[] fArr, String str) {
        a(str);
        if (fArr == null) {
            this.a.append("null").append('\n');
        } else if (fArr.length == 0) {
            this.a.append(fArr.length).append(", []").append('\n');
        } else {
            this.a.append(fArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (float a : fArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public c a(float[] fArr, boolean z) {
        if (fArr == null || fArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
        } else {
            this.a.append(d.G);
            c cVar = new c(this.a, this.b + 1);
            for (int i = 0; i < fArr.length; i++) {
                float f = fArr[i];
                if (i != 0) {
                    this.a.append("|");
                }
                cVar.a(f, false);
            }
            this.a.append(d.H);
            if (z) {
                this.a.append("|");
            }
        }
        return this;
    }

    public c a(double[] dArr, String str) {
        a(str);
        if (dArr == null) {
            this.a.append("null").append('\n');
        } else if (dArr.length == 0) {
            this.a.append(dArr.length).append(", []").append('\n');
        } else {
            this.a.append(dArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (double a : dArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public c a(double[] dArr, boolean z) {
        if (dArr == null || dArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
        } else {
            this.a.append(d.G);
            c cVar = new c(this.a, this.b + 1);
            for (int i = 0; i < dArr.length; i++) {
                double d = dArr[i];
                if (i != 0) {
                    this.a.append("|");
                }
                cVar.a(d, false);
            }
            this.a.append(d.G);
            if (z) {
                this.a.append("|");
            }
        }
        return this;
    }

    public <K, V> c a(Map<K, V> map, String str) {
        a(str);
        if (map == null) {
            this.a.append("null").append('\n');
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}").append('\n');
        } else {
            this.a.append(map.size()).append(", {").append('\n');
            c cVar = new c(this.a, this.b + 1);
            c cVar2 = new c(this.a, this.b + 2);
            for (Entry entry : map.entrySet()) {
                cVar.a('(', null);
                cVar2.a(entry.getKey(), null);
                cVar2.a(entry.getValue(), null);
                cVar.a(')', null);
            }
            a('}', null);
        }
        return this;
    }

    public <K, V> c a(Map<K, V> map, boolean z) {
        if (map == null || map.isEmpty()) {
            this.a.append("{}");
            if (z) {
                this.a.append("|");
            }
        } else {
            this.a.append("{");
            c cVar = new c(this.a, this.b + 2);
            boolean z2 = true;
            for (Entry entry : map.entrySet()) {
                if (!z2) {
                    this.a.append(",");
                }
                cVar.a(entry.getKey(), true);
                cVar.a(entry.getValue(), false);
                z2 = false;
            }
            this.a.append(i.d);
            if (z) {
                this.a.append("|");
            }
        }
        return this;
    }

    public <T> c a(T[] tArr, String str) {
        a(str);
        if (tArr == null) {
            this.a.append("null").append('\n');
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []").append('\n');
        } else {
            this.a.append(tArr.length).append(", [").append('\n');
            c cVar = new c(this.a, this.b + 1);
            for (Object a : tArr) {
                cVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public <T> c a(T[] tArr, boolean z) {
        if (tArr == null || tArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
        } else {
            this.a.append(d.G);
            c cVar = new c(this.a, this.b + 1);
            for (int i = 0; i < tArr.length; i++) {
                Object obj = tArr[i];
                if (i != 0) {
                    this.a.append("|");
                }
                cVar.a(obj, false);
            }
            this.a.append(d.H);
            if (z) {
                this.a.append("|");
            }
        }
        return this;
    }

    public <T> c a(Collection<T> collection, String str) {
        if (collection != null) {
            return a(collection.toArray(), str);
        }
        a(str);
        this.a.append("null").append('\t');
        return this;
    }

    public <T> c a(Collection<T> collection, boolean z) {
        if (collection != null) {
            return a(collection.toArray(), z);
        }
        this.a.append("[]");
        if (!z) {
            return this;
        }
        this.a.append("|");
        return this;
    }

    public <T> c a(T t, String str) {
        if (t == null) {
            this.a.append("null").append('\n');
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((List) t, str);
        } else if (t instanceof g) {
            a((g) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((Object) (boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new d("write object error: unsupport type.");
        }
        return this;
    }

    public <T> c a(T t, boolean z) {
        if (t == null) {
            this.a.append("null").append('\n');
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), z);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), z);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), z);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), z);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), z);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), z);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), z);
        } else if (t instanceof String) {
            a((String) t, z);
        } else if (t instanceof Map) {
            a((Map) t, z);
        } else if (t instanceof List) {
            a((List) t, z);
        } else if (t instanceof g) {
            a((g) t, z);
        } else if (t instanceof byte[]) {
            a((byte[]) t, z);
        } else if (t instanceof boolean[]) {
            a((Object) (boolean[]) t, z);
        } else if (t instanceof short[]) {
            a((short[]) t, z);
        } else if (t instanceof int[]) {
            a((int[]) t, z);
        } else if (t instanceof long[]) {
            a((long[]) t, z);
        } else if (t instanceof float[]) {
            a((float[]) t, z);
        } else if (t instanceof double[]) {
            a((double[]) t, z);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, z);
        } else {
            throw new d("write object error: unsupport type.");
        }
        return this;
    }

    public c a(g gVar, String str) {
        a('{', str);
        if (gVar == null) {
            this.a.append('\t').append("null");
        } else {
            gVar.display(this.a, this.b + 1);
        }
        a('}', null);
        return this;
    }

    public c a(g gVar, boolean z) {
        this.a.append("{");
        if (gVar == null) {
            this.a.append('\t').append("null");
        } else {
            gVar.displaySimple(this.a, this.b + 1);
        }
        this.a.append(i.d);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public static void a(String[] strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1.2d);
        System.out.println(stringBuilder.toString());
    }
}
