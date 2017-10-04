package dji.pilot.publics.control;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$k extends a$i {
    final /* synthetic */ a c;

    private a$k(a aVar) {
        this.c = aVar;
        super(aVar);
    }

    public void run() {
        this.a.start(new d(this) {
            final /* synthetic */ a$k a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                a.m(this.a.c);
            }

            public void onFailure(a aVar) {
                a.n(this.a.c);
                a.m(this.a.c);
            }
        }, 1000, 1);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
