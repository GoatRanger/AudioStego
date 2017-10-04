package dji.pilot2.share.d;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.R;

public class c {
    private PopupWindow a = new PopupWindow();

    public static class a {
        Context a;
        int b = -2;
        int c = -1;
        int d;
        boolean e = false;
        int f;

        public a(Context context) {
            this.a = context;
        }

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a b(int i) {
            this.c = i;
            return this;
        }

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        public a c(int i) {
            this.d = i;
            return this;
        }

        public a d(int i) {
            this.f = i;
            return this;
        }

        public c a() {
            return new c(this.a, this.d, this.f, this.c, this.b, this.e);
        }
    }

    public c(Context context, int i, int i2, int i3, int i4, boolean z) {
        View inflate = LayoutInflater.from(context).inflate(i, null);
        this.a.setContentView(inflate);
        this.a.setHeight(i4);
        this.a.setWidth(i3);
        this.a.setFocusable(z);
        View findViewById = inflate.findViewById(R.id.cgg);
        findViewById.measure(-2, -2);
        int measuredWidth = findViewById.getMeasuredWidth();
        Log.e("Jackson", "" + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + measuredWidth + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + context.getResources().getDisplayMetrics().widthPixels);
        LayoutParams layoutParams = (LayoutParams) findViewById.getLayoutParams();
        layoutParams.setMargins(i2 - (measuredWidth / 2), 0, 0, 0);
        findViewById.setLayoutParams(layoutParams);
    }

    public PopupWindow a() {
        return this.a;
    }
}
