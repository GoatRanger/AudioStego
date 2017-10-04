package com.google.a.i.a;

import com.google.a.t;

public final class i {
    private final boolean a;

    i(boolean z) {
        this.a = z;
    }

    public boolean a() {
        return this.a;
    }

    public void a(t[] tVarArr) {
        if (this.a && tVarArr != null && tVarArr.length >= 3) {
            t tVar = tVarArr[0];
            tVarArr[0] = tVarArr[2];
            tVarArr[2] = tVar;
        }
    }
}
