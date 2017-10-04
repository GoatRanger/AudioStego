package dji.pilot.dji_groundstation.controller;

import dji.thirdparty.a.c;

class a$1 implements Runnable {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void run() {
        if (!c.a().c(a.j())) {
            c.a().a(a.j());
        }
    }
}
