package antistatic.spinnerwheel.a;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class b extends a {
    public static final int a = -1;
    protected static final int b = 0;
    public static final int c = -15724528;
    public static final int d = -9437072;
    public static final int e = 24;
    protected Context f;
    protected LayoutInflater g;
    protected int h;
    protected int i;
    protected int j;
    private Typeface k;
    private int l;
    private int m;

    protected abstract CharSequence f(int i);

    protected b(Context context) {
        this(context, -1);
    }

    protected b(Context context, int i) {
        this(context, i, 0);
    }

    protected b(Context context, int i, int i2) {
        this.l = c;
        this.m = 24;
        this.f = context;
        this.h = i;
        this.i = i2;
        this.g = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int c() {
        return this.l;
    }

    public void a(int i) {
        this.l = i;
    }

    public void a(Typeface typeface) {
        this.k = typeface;
    }

    public int d() {
        return this.m;
    }

    public void b(int i) {
        this.m = i;
    }

    public int e() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    public int f() {
        return this.i;
    }

    public void d(int i) {
        this.i = i;
    }

    public int g() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
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
        a(a);
        return view;
    }

    public View a(View view, ViewGroup viewGroup) {
        View a;
        if (view == null) {
            a = a(this.j, viewGroup);
        } else {
            a = view;
        }
        if (a instanceof TextView) {
            a((TextView) a);
        }
        return a;
    }

    protected void a(TextView textView) {
        if (this.h == -1) {
            textView.setTextColor(this.l);
            textView.setGravity(17);
            textView.setTextSize((float) this.m);
            textView.setLines(1);
        }
        if (this.k != null) {
            textView.setTypeface(this.k);
        } else {
            textView.setTypeface(Typeface.SANS_SERIF, 1);
        }
    }

    private TextView a(View view, int i) {
        if (i == 0) {
            try {
                if (view instanceof TextView) {
                    return (TextView) view;
                }
            } catch (Throwable e) {
                Log.e("AbstractWheelAdapter", "You must supply a resource ID for a TextView");
                throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e);
            }
        }
        if (i != 0) {
            return (TextView) view.findViewById(i);
        }
        return null;
    }

    private View a(int i, ViewGroup viewGroup) {
        switch (i) {
            case -1:
                return new TextView(this.f);
            case 0:
                return null;
            default:
                return this.g.inflate(i, viewGroup, false);
        }
    }
}
