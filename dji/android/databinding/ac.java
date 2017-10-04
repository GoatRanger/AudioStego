package android.databinding;

import android.view.View;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;

public class ac {
    private ViewStub a;
    private ab b;
    private View c;
    private OnInflateListener d;
    private ab e;
    private OnInflateListener f = new OnInflateListener(this) {
        final /* synthetic */ ac a;

        {
            this.a = r1;
        }

        public void onInflate(ViewStub viewStub, View view) {
            this.a.c = view;
            this.a.b = k.a(this.a.e.c, view, viewStub.getLayoutResource());
            this.a.a = null;
            if (this.a.d != null) {
                this.a.d.onInflate(viewStub, view);
                this.a.d = null;
            }
            this.a.e.f();
            this.a.e.d();
        }
    };

    public ac(ViewStub viewStub) {
        this.a = viewStub;
        this.a.setOnInflateListener(this.f);
    }

    public void a(ab abVar) {
        this.e = abVar;
    }

    public boolean a() {
        return this.c != null;
    }

    public View b() {
        return this.c;
    }

    public ab c() {
        return this.b;
    }

    public ViewStub d() {
        return this.a;
    }

    public void a(OnInflateListener onInflateListener) {
        if (this.a != null) {
            this.d = onInflateListener;
        }
    }
}
