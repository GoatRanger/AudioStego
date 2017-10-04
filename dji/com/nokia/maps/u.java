package com.nokia.maps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.nokia.maps.ar.a;

public class u extends y {
    protected e a = null;
    protected i b = null;
    protected Context c = null;
    a d = new a(this) {
        final /* synthetic */ u a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.onPause();
            return false;
        }
    };
    a e = new a(this) {
        final /* synthetic */ u a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.onResume();
            return false;
        }
    };
    a f = new a(this) {
        final /* synthetic */ u a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.b();
            return false;
        }
    };
    private int i;
    private int j;

    public u(Context context) {
        super(context);
        this.c = context.getApplicationContext();
    }

    public u(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context.getApplicationContext();
    }

    e a() {
        if (this.a == null) {
            this.a = new e(this.c);
            if (this.i > 0 || this.j > 0) {
                this.a.a(this.i, this.j);
            }
        }
        return this.a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.a != null) {
            return this.a.a(motionEvent);
        }
        return false;
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void b() {
        this.b = null;
        this.a = null;
        setOnTouchListener(null);
    }

    e getARGesture() {
        return this.a;
    }

    void a(int i, int i2) {
        this.i = i;
        this.j = i2;
    }

    void setPanEnabled(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }

    boolean c() {
        if (this.a != null) {
            return this.a.a();
        }
        return false;
    }

    void setPinchEnabled(boolean z) {
        if (this.a != null) {
            this.a.b(z);
        }
    }

    boolean d() {
        if (this.a != null) {
            return this.a.b();
        }
        return false;
    }
}
