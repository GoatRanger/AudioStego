package dji.pilot.fpv.control;

import android.widget.Toast;
import dji.c.b.a;

class b$3 implements a {
    final /* synthetic */ b a;

    b$3(b bVar) {
        this.a = bVar;
    }

    public void b() {
        this.a.d.hide();
    }

    public void a() {
        this.a.d.show();
    }

    public void c() {
        this.a.d.hide();
        Toast.makeText(this.a.q, "record voice error", 0).show();
    }

    public void a(int i) {
        this.a.a(i);
    }
}
