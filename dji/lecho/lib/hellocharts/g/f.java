package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.a;

public class f extends a {
    protected List<d> q = new ArrayList();
    protected Viewport r = new Viewport();

    public f(Context context, a aVar) {
        super(context, aVar);
    }

    public void i() {
        for (d i : this.q) {
            i.i();
        }
    }

    public void b() {
        super.b();
        for (d b : this.q) {
            b.b();
        }
        j();
    }

    public void j() {
        if (this.h) {
            int i = 0;
            for (d dVar : this.q) {
                dVar.j();
                if (i == 0) {
                    this.r.a(dVar.e());
                } else {
                    this.r.c(dVar.e());
                }
                i++;
            }
            this.c.b(this.r);
            this.c.a(this.r);
        }
    }

    public void a(Canvas canvas) {
        for (d a : this.q) {
            a.a(canvas);
        }
    }

    public void b(Canvas canvas) {
        for (d b : this.q) {
            b.b(canvas);
        }
    }

    public boolean a(float f, float f2) {
        this.k.a();
        int size = this.q.size() - 1;
        while (size >= 0) {
            d dVar = (d) this.q.get(size);
            if (dVar.a(f, f2)) {
                this.k.a(dVar.h());
                break;
            }
            size--;
        }
        for (size--; size >= 0; size--) {
            ((d) this.q.get(size)).d();
        }
        return c();
    }

    public void d() {
        for (d d : this.q) {
            d.d();
        }
        this.k.a();
    }
}
