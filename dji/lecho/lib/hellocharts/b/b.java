package lecho.lib.hellocharts.b;

import lecho.lib.hellocharts.model.Viewport;

public class b extends a {
    public float a(float f) {
        return ((f - this.i.a) * (((float) this.e.width()) / this.i.c())) + ((float) this.e.left);
    }

    public float b(float f) {
        return ((float) this.e.bottom) - ((f - this.i.d) * (((float) this.e.height()) / this.i.d()));
    }

    public Viewport f() {
        return this.i;
    }

    public void c(Viewport viewport) {
        b(viewport);
    }

    public void a(float f, float f2, float f3, float f4) {
        super.a(f, f2, f3, f4);
        this.l.a(this.h);
    }
}
