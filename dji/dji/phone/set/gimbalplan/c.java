package dji.phone.set.gimbalplan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import antistatic.spinnerwheel.a.b;
import dji.pilot.fpv.R;

public class c<T> extends b {
    private T[] k;
    private final int[] l = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = Integer.MAX_VALUE;
    private int r = -1;
    private boolean s = true;

    public c(Context context, T[] tArr) {
        super(context);
        this.k = tArr;
        this.q = tArr.length;
        this.m = context.getResources().getColor(R.color.red);
        this.n = context.getResources().getColor(R.color.white);
        this.p = context.getResources().getColor(R.color.white);
        this.o = context.getResources().getColor(R.color.longan_blue_selected);
    }

    public void a(boolean z) {
        if (this.s != z) {
            this.s = z;
            a();
        }
    }

    public void a(T[] tArr) {
        this.k = tArr;
        this.q = tArr.length;
        b();
    }

    public void g(int i) {
        this.n = this.f.getResources().getColor(i);
    }

    public void i() {
        this.o = this.f.getResources().getColor(R.color.blue);
    }

    public void h(int i) {
        this.o = i;
    }

    public void a(int i, int i2) {
        if (i != this.l[0] || i2 != this.l[1]) {
            this.l[0] = i;
            this.l[1] = i2;
            a();
        }
    }

    public void i(int i) {
        if (this.r != i) {
            this.r = i;
            a();
        }
    }

    public void j() {
        if (this.l[0] != Integer.MIN_VALUE || this.l[1] != Integer.MAX_VALUE) {
            this.l[0] = Integer.MIN_VALUE;
            this.l[1] = Integer.MAX_VALUE;
            a();
        }
    }

    public void j(int i) {
        if (this.q != i) {
            this.q = i;
            a();
        }
    }

    public int h() {
        if (this.q != -1) {
            return this.q;
        }
        return this.k.length;
    }

    protected CharSequence f(int i) {
        if (i < 0 || i >= h()) {
            return null;
        }
        Object obj = this.k[i];
        if (obj instanceof CharSequence) {
            return (CharSequence) obj;
        }
        return obj.toString();
    }

    public View a(View view, ViewGroup viewGroup) {
        return null;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= h()) {
            return null;
        }
        if (view == null) {
            view = a(this.h, viewGroup);
        }
        TextView a = a(view, this.i);
        if (a == null) {
            return view;
        }
        CharSequence f = f(i);
        if (f == null) {
            f = "";
        }
        a.setText(f);
        a(a, i);
        return view;
    }

    private void a(TextView textView, int i) {
        if (!this.s) {
            textView.setTextColor(this.p);
        } else if (i < this.l[0] || i > this.l[1]) {
            textView.setTextColor(this.m);
        } else if (this.o == 0 || i != this.r) {
            textView.setTextColor(this.n);
        } else {
            textView.setTextColor(this.o);
            textView.setText(textView.getText() + "°/s");
        }
    }

    private TextView a(View view, int i) {
        return (TextView) view.findViewById(i);
    }

    private View a(int i, ViewGroup viewGroup) {
        return this.g.inflate(i, viewGroup, false);
    }
}
