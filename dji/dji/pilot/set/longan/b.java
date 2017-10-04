package dji.pilot.set.longan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class b {
    private int a;
    private View b;

    public static class a {
        private View a;

        public a(View view) {
            this.a = view;
        }

        public View a() {
            return this.a;
        }
    }

    public b(int i, int i2, Context context) {
        this.b = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(i, null, false);
        this.a = i2;
    }

    public b(View view, int i) {
        this.b = view;
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public View b() {
        return this.b;
    }
}
