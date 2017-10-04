package dji.pilot.set.view.gimbal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import antistatic.spinnerwheel.a.b;

public class a<T> extends b {
    private T[] k;
    private final int[] l = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = Integer.MAX_VALUE;
    private int r = -1;
    private boolean s = true;

    public a(Context context, T[] tArr) {
        super(context);
        this.k = tArr;
        this.q = tArr.length;
        this.m = context.getResources().getColor(17170446);
        this.n = context.getResources().getColor(17170443);
        this.p = context.getResources().getColor(17170443);
        this.o = context.getResources().getColor(17170459);
    }

    public void a(boolean z) {
        if (this.s != z) {
            this.s = z;
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
        }
    }

    private TextView a(View view, int i) {
        return (TextView) view.findViewById(i);
    }

    private View a(int i, ViewGroup viewGroup) {
        return this.g.inflate(i, viewGroup, false);
    }
}
