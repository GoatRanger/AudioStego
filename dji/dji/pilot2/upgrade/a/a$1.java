package dji.pilot2.upgrade.a;

import dji.dbox.upgrade.p4.d.a;

class a$1 implements Runnable {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void run() {
        a.getInstance().a(new a.a(this) {
            final /* synthetic */ a$1 a;

            {
                this.a = r1;
            }

            public void a(int i) {
            }

            public void a() {
                a.a(this.a.a, true);
                a.a(this.a.a);
            }

            public void b() {
                a.a(this.a.a, false);
            }
        });
    }
}
