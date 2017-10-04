package com.google.a.i.c;

import com.google.a.i.a.h;
import com.google.a.i.a.j;

public final class f {
    public static final int a = 8;
    private h b;
    private com.google.a.i.a.f c;
    private j d;
    private int e = -1;
    private b f;

    public h a() {
        return this.b;
    }

    public com.google.a.i.a.f b() {
        return this.c;
    }

    public j c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public b e() {
        return this.f;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append("<<\n");
        stringBuilder.append(" mode: ");
        stringBuilder.append(this.b);
        stringBuilder.append("\n ecLevel: ");
        stringBuilder.append(this.c);
        stringBuilder.append("\n version: ");
        stringBuilder.append(this.d);
        stringBuilder.append("\n maskPattern: ");
        stringBuilder.append(this.e);
        if (this.f == null) {
            stringBuilder.append("\n matrix: null\n");
        } else {
            stringBuilder.append("\n matrix:\n");
            stringBuilder.append(this.f);
        }
        stringBuilder.append(">>\n");
        return stringBuilder.toString();
    }

    public void a(h hVar) {
        this.b = hVar;
    }

    public void a(com.google.a.i.a.f fVar) {
        this.c = fVar;
    }

    public void a(j jVar) {
        this.d = jVar;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public static boolean b(int i) {
        return i >= 0 && i < 8;
    }
}
