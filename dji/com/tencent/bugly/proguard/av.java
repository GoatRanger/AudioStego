package com.tencent.bugly.proguard;

import java.util.ArrayList;

public final class av extends m implements Cloneable {
    static ArrayList<au> b;
    public ArrayList<au> a = null;

    public void a(l lVar) {
        lVar.a(this.a, 0);
    }

    public void a(k kVar) {
        if (b == null) {
            b = new ArrayList();
            b.add(new au());
        }
        this.a = (ArrayList) kVar.a(b, 0, true);
    }

    public void a(StringBuilder stringBuilder, int i) {
    }
}
