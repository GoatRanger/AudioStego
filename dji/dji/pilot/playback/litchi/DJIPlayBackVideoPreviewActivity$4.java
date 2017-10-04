package dji.pilot.playback.litchi;

import dji.midware.media.i.h.a;

class DJIPlayBackVideoPreviewActivity$4 implements a {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$4(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void a() {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$4 a;

            {
                this.a = r1;
            }

            public void run() {
                DJIPlayBackVideoPreviewActivity.k(this.a.a);
            }
        });
    }

    public void a(final int i, final int i2) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$4 c;

            public void run() {
                if (i2 != 0) {
                    if (DJIPlayBackVideoPreviewActivity.s(this.c.a) && Math.abs(DJIPlayBackVideoPreviewActivity.t(this.c.a) - ((long) i)) < ((long) DJIPlayBackVideoPreviewActivity.b())) {
                        DJIPlayBackVideoPreviewActivity.c(this.c.a, false);
                        DJIPlayBackVideoPreviewActivity.a(this.c.a, 0);
                        DJIPlayBackVideoPreviewActivity.u(this.c.a).hide();
                    }
                    if (!DJIPlayBackVideoPreviewActivity.s(this.c.a)) {
                        DJIPlayBackVideoPreviewActivity.o(this.c.a).setProgress((int) ((((float) (i * 1000)) * 1.0f) / ((float) i2)));
                    }
                    DJIPlayBackVideoPreviewActivity.b(this.c.a, i / 1000);
                    DJIPlayBackVideoPreviewActivity.c(this.c.a, i2 / 1000);
                    DJIPlayBackVideoPreviewActivity.a(this.c.a, DJIPlayBackVideoPreviewActivity.v(this.c.a), DJIPlayBackVideoPreviewActivity.w(this.c.a));
                }
            }
        });
    }

    public void b() {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$4 a;

            {
                this.a = r1;
            }

            public void run() {
                DJIPlayBackVideoPreviewActivity.k(this.a.a);
            }
        });
    }

    public void c() {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$4 a;

            {
                this.a = r1;
            }

            public void run() {
                DJIPlayBackVideoPreviewActivity.k(this.a.a);
                DJIPlayBackVideoPreviewActivity.o(this.a.a).setProgress(DJIPlayBackVideoPreviewActivity.o(this.a.a).getMax());
                DJIPlayBackVideoPreviewActivity.a(this.a.a, DJIPlayBackVideoPreviewActivity.w(this.a.a), DJIPlayBackVideoPreviewActivity.w(this.a.a));
            }
        });
    }
}
