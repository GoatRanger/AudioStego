package dji.pilot.fpv.camera.more;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import antistatic.spinnerwheel.a.b;
import dji.pilot.R;
import dji.pilot.usercenter.protocol.d;

public class e<T> extends b {
    private T[] k;
    private final int[] l = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = Integer.MAX_VALUE;
    private int r = -1;
    private boolean s = true;

    public e(Context context, T[] tArr) {
        super(context);
        this.k = tArr;
        this.q = tArr.length;
        this.m = context.getResources().getColor(R.color.gj);
        this.n = context.getResources().getColor(R.color.om);
        this.p = context.getResources().getColor(R.color.j5);
    }

    public void a(T[] tArr) {
        this.k = tArr;
        this.q = tArr.length;
        b();
    }

    public void a(boolean z) {
        if (this.s != z) {
            this.s = z;
            a();
        }
    }

    public void i() {
        this.o = this.f.getResources().getColor(R.color.d9);
    }

    public void a(int i, int i2) {
        if (i != this.l[0] || i2 != this.l[1]) {
            this.l[0] = i;
            this.l[1] = i2;
            a();
        }
    }

    public void g(int i) {
        a.a("curPos:" + i);
        if (this.r != i) {
            this.r = i;
            if (this.q > 0 && i >= this.q) {
                this.r = this.q - 1;
            }
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

    public int k() {
        return this.q;
    }

    public void h(int i) {
        if (this.q != i) {
            this.q = i;
            if (i > 0 && this.r >= i) {
                this.r = i - 1;
            }
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
        a.a("*****index[" + i + "," + this.r + d.H);
        if (this.s) {
            if ((this.l[0] == this.l[1] ? 1 : 0) != 0) {
                if (i == this.l[0]) {
                    textView.setTextColor(this.m);
                    return;
                } else if (this.o == 0 || i != this.r) {
                    textView.setTextColor(this.n);
                    return;
                } else {
                    textView.setTextColor(this.o);
                    return;
                }
            } else if (i < this.l[0] || i > this.l[1]) {
                textView.setTextColor(this.m);
                return;
            } else if (this.o == 0 || i != this.r) {
                textView.setTextColor(this.n);
                return;
            } else {
                textView.setTextColor(this.o);
                return;
            }
        }
        a.a("*****index[" + i + "," + this.r + d.H);
        if (i == this.r) {
            textView.setTextColor(this.n);
        } else {
            textView.setTextColor(this.p);
        }
    }

    private TextView a(View view, int i) {
        return (TextView) view.findViewById(i);
    }

    private View a(int i, ViewGroup viewGroup) {
        return this.g.inflate(i, viewGroup, false);
    }
}
