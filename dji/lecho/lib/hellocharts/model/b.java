package lecho.lib.hellocharts.model;

import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.c.a;
import lecho.lib.hellocharts.c.f;

public class b {
    public static final int a = 12;
    public static final int b = 3;
    private int c = 12;
    private int d = 3;
    private List<c> e = new ArrayList();
    private String f;
    private boolean g = true;
    private boolean h = false;
    private boolean i = false;
    private int j = -3355444;
    private int k = lecho.lib.hellocharts.h.b.b;
    private Typeface l;
    private a m = new f();
    private boolean n = true;
    private boolean o = false;

    public b(List<c> list) {
        b((List) list);
    }

    public b(b bVar) {
        this.f = bVar.f;
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = bVar.k;
        this.c = bVar.c;
        this.d = bVar.d;
        this.l = bVar.l;
        this.m = bVar.m;
        this.n = bVar.n;
        for (c cVar : bVar.e) {
            this.e.add(new c(cVar));
        }
    }

    public static b a(float f, float f2, float f3) {
        List arrayList = new ArrayList();
        while (f <= f2) {
            arrayList.add(new c(f));
            f += f3;
        }
        return new b(arrayList);
    }

    public static b a(List<Float> list) {
        List arrayList = new ArrayList();
        int i = 0;
        for (Float floatValue : list) {
            arrayList.add(new c(floatValue.floatValue()));
            i++;
        }
        return new b(arrayList);
    }

    public static b a(List<Float> list, List<String> list2) {
        if (list.size() != list2.size()) {
            throw new IllegalArgumentException("Values and labels lists must have the same size!");
        }
        List arrayList = new ArrayList();
        int i = 0;
        for (Float floatValue : list) {
            arrayList.add(new c(floatValue.floatValue()).a((String) list2.get(i)));
            i++;
        }
        return new b(arrayList);
    }

    public List<c> a() {
        return this.e;
    }

    public b b(List<c> list) {
        if (list == null) {
            this.e = new ArrayList();
        } else {
            this.e = list;
        }
        this.g = false;
        return this;
    }

    public String b() {
        return this.f;
    }

    public b a(String str) {
        this.f = str;
        return this;
    }

    public boolean c() {
        return this.g;
    }

    public b a(boolean z) {
        this.g = z;
        return this;
    }

    public boolean d() {
        return this.h;
    }

    public b b(boolean z) {
        this.h = z;
        return this;
    }

    public int e() {
        return this.j;
    }

    public b a(int i) {
        this.j = i;
        return this;
    }

    public boolean f() {
        return this.i;
    }

    public b c(boolean z) {
        this.i = z;
        return this;
    }

    public int g() {
        return this.k;
    }

    public b b(int i) {
        this.k = i;
        return this;
    }

    public int h() {
        return this.c;
    }

    public b c(int i) {
        this.c = i;
        return this;
    }

    public int i() {
        return this.d;
    }

    public b d(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 32) {
            i = 32;
        }
        this.d = i;
        return this;
    }

    public Typeface j() {
        return this.l;
    }

    public b a(Typeface typeface) {
        this.l = typeface;
        return this;
    }

    public a k() {
        return this.m;
    }

    public b a(a aVar) {
        if (aVar == null) {
            this.m = new f();
        } else {
            this.m = aVar;
        }
        return this;
    }

    public b d(boolean z) {
        this.n = z;
        return this;
    }

    public boolean l() {
        return this.n;
    }

    public boolean m() {
        return this.o;
    }

    public b e(boolean z) {
        this.o = z;
        return this;
    }
}
