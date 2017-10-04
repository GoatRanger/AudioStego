package com.nokia.maps;

import com.nokia.maps.dd.c;

class do$4 implements Runnable {
    final /* synthetic */ do a;

    do$4(do doVar) {
        this.a = doVar;
    }

    public void run() {
        boolean z;
        if (do.a(this.a) != null) {
            do.a(this.a).onCompleted(this.a.g, this.a.f);
        }
        p b = do.b(this.a);
        c cVar = this.a.i;
        if (this.a.g != null) {
            z = true;
        } else {
            z = false;
        }
        b.a(cVar, false, z);
    }
}
