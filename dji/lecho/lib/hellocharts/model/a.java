package lecho.lib.hellocharts.model;

import android.graphics.Typeface;
import lecho.lib.hellocharts.h.b;

public abstract class a implements f {
    public static final int a = 12;
    protected b b;
    protected b c;
    protected b d;
    protected b e;
    protected int f = -1;
    protected int g = 12;
    protected Typeface h;
    protected boolean i = true;
    protected boolean j = true;
    protected int k = b.a(b.b);

    public a(a aVar) {
        if (aVar.b != null) {
            this.b = new b(aVar.b);
        }
        if (aVar.d != null) {
            this.d = new b(aVar.d);
        }
        if (aVar.c != null) {
            this.c = new b(aVar.c);
        }
        if (aVar.e != null) {
            this.e = new b(aVar.e);
        }
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
    }

    public b a() {
        return this.b;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public b b() {
        return this.c;
    }

    public void b(b bVar) {
        this.c = bVar;
    }

    public b c() {
        return this.d;
    }

    public void c(b bVar) {
        this.d = bVar;
    }

    public b d() {
        return this.e;
    }

    public void d(b bVar) {
        this.e = bVar;
    }

    public int e() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int f() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public Typeface g() {
        return this.h;
    }

    public void a(Typeface typeface) {
        this.h = typeface;
    }

    public boolean h() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public boolean i() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public int j() {
        return this.k;
    }

    public void c(int i) {
        this.k = i;
    }
}
