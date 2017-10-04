package dji.pilot.fpv.control;

import dji.midware.data.model.P3.DataCameraGetMode.MODE;

class b$8 implements Runnable {
    final /* synthetic */ b a;

    b$8(b bVar) {
        this.a = bVar;
    }

    public void run() {
        if (b.n(this.a) == MODE.PLAYBACK && b.a(this.a) != null) {
            this.a.b.sendEmptyMessage(2);
        }
    }
}
