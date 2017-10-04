package dji.pilot.e;

import android.databinding.ab;
import android.databinding.ab.b;
import android.databinding.j;
import android.databinding.k;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class a extends ab {
    private static final b q = null;
    private static final SparseIntArray r = new SparseIntArray();
    public final RelativeLayout d;
    public final DJIStateImageView e;
    public final RelativeLayout f;
    public final DJIStateImageView g;
    public final DJIRelativeLayout h;
    public final RelativeLayout i;
    public final DJIStateImageView j;
    public final DJIStateTextView k;
    public final RelativeLayout l;
    public final DJIStateImageView m;
    public final DJIStateImageView n;
    public final RelativeLayout o;
    public final DJIStateImageView p;
    private final RelativeLayout s;
    private long t = -1;

    static {
        r.put(R.id.gx, 1);
        r.put(R.id.gy, 2);
        r.put(R.id.gz, 3);
        r.put(R.id.h0, 4);
        r.put(R.id.h1, 5);
        r.put(R.id.h2, 6);
        r.put(R.id.h3, 7);
        r.put(R.id.h4, 8);
        r.put(R.id.h5, 9);
        r.put(R.id.h6, 10);
        r.put(R.id.h7, 11);
        r.put(R.id.h8, 12);
        r.put(R.id.h9, 13);
    }

    public a(j jVar, View view) {
        super(jVar, view, 0);
        Object[] a = a(jVar, view, 14, q, r);
        this.s = (RelativeLayout) a[0];
        this.s.setTag(null);
        this.d = (RelativeLayout) a[4];
        this.e = (DJIStateImageView) a[5];
        this.f = (RelativeLayout) a[10];
        this.g = (DJIStateImageView) a[11];
        this.h = (DJIRelativeLayout) a[1];
        this.i = (RelativeLayout) a[12];
        this.j = (DJIStateImageView) a[13];
        this.k = (DJIStateTextView) a[3];
        this.l = (RelativeLayout) a[6];
        this.m = (DJIStateImageView) a[7];
        this.n = (DJIStateImageView) a[2];
        this.o = (RelativeLayout) a[8];
        this.p = (DJIStateImageView) a[9];
        a(view);
        f();
    }

    public void f() {
        synchronized (this) {
            this.t = 1;
        }
        j();
    }

    public boolean g() {
        synchronized (this) {
            if (this.t != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean a(int i, Object obj) {
        return false;
    }

    protected boolean a(int i, Object obj, int i2) {
        return false;
    }

    protected void e() {
        synchronized (this) {
            long j = this.t;
            this.t = 0;
        }
    }

    public static a a(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return a(layoutInflater, viewGroup, z, k.a());
    }

    public static a a(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, j jVar) {
        return (a) k.a(layoutInflater, R.layout.activity_language_setting, viewGroup, z, jVar);
    }

    public static a a(LayoutInflater layoutInflater) {
        return a(layoutInflater, k.a());
    }

    public static a a(LayoutInflater layoutInflater, j jVar) {
        return a(layoutInflater.inflate(R.layout.activity_language_setting, null, false), jVar);
    }

    public static a c(View view) {
        return a(view, k.a());
    }

    public static a a(View view, j jVar) {
        if ("layout/activity_language_setting_0".equals(view.getTag())) {
            return new a(jVar, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
