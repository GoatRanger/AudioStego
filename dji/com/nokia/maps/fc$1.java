package com.nokia.maps;

class fc$1 implements Runnable {
    final /* synthetic */ fc$a a;
    final /* synthetic */ Runnable b;
    final /* synthetic */ fc c;

    fc$1(fc fcVar, fc$a com_nokia_maps_fc_a, Runnable runnable) {
        this.c = fcVar;
        this.a = com_nokia_maps_fc_a;
        this.b = runnable;
    }

    public void run() {
        this.c.a(this.a);
        if (this.b != null) {
            this.b.run();
        }
    }
}
