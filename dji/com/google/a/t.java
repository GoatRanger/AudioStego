package com.google.a;

import com.google.a.c.a.a;

public class t {
    private final float a;
    private final float b;

    public t(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final float a() {
        return this.a;
    }

    public final float b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        if (this.a == tVar.a && this.b == tVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(25);
        stringBuilder.append('(');
        stringBuilder.append(this.a);
        stringBuilder.append(',');
        stringBuilder.append(this.b);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public static void a(t[] tVarArr) {
        t tVar;
        t tVar2;
        t tVar3;
        float a = a(tVarArr[0], tVarArr[1]);
        float a2 = a(tVarArr[1], tVarArr[2]);
        float a3 = a(tVarArr[0], tVarArr[2]);
        if (a2 >= a && a2 >= a3) {
            tVar = tVarArr[0];
            tVar2 = tVarArr[1];
            tVar3 = tVarArr[2];
        } else if (a3 < a2 || a3 < a) {
            tVar = tVarArr[2];
            tVar2 = tVarArr[0];
            tVar3 = tVarArr[1];
        } else {
            tVar = tVarArr[1];
            tVar2 = tVarArr[0];
            tVar3 = tVarArr[2];
        }
        if (a(tVar2, tVar, tVar3) >= 0.0f) {
            t tVar4 = tVar3;
            tVar3 = tVar2;
            tVar2 = tVar4;
        }
        tVarArr[0] = tVar3;
        tVarArr[1] = tVar;
        tVarArr[2] = tVar2;
    }

    public static float a(t tVar, t tVar2) {
        return a.a(tVar.a, tVar.b, tVar2.a, tVar2.b);
    }

    private static float a(t tVar, t tVar2, t tVar3) {
        float f = tVar2.a;
        float f2 = tVar2.b;
        return ((tVar3.a - f) * (tVar.b - f2)) - ((tVar.a - f) * (tVar3.b - f2));
    }
}
