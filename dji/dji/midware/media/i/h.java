package dji.midware.media.i;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams.MODE;
import dji.midware.data.model.P3.DataCameraSingleChoice;
import dji.midware.data.model.P3.DataCameraVideoControl;
import dji.midware.data.model.P3.DataCameraVideoControl.ControlType;
import dji.midware.e.d;
import dji.midware.media.i.g.b;
import dji.thirdparty.a.c;

public class h implements g {
    private static final String a = "DJIMediaPlayerKumquat";
    private MODE b;
    private int c;
    private int d;
    private boolean e;
    private boolean f;
    private boolean g;
    private Context h;
    private a i;
    private DataCameraVideoControl j;

    public interface a {
        void a();

        void a(int i, int i2);

        void b();

        void c();
    }

    public h() {
        this.b = MODE.OTHER;
        this.c = -1;
        this.d = -1;
        this.e = true;
        this.f = false;
        this.g = false;
        this.j = DataCameraVideoControl.getInstance();
        this.b = DataCameraGetPushPlayBackParams.getInstance().getMode();
        c.a().a(this);
    }

    public void k() {
        c.a().d(this);
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public void a(String str) {
    }

    public void a() {
    }

    public void b() {
    }

    public void a(int i, final int i2) {
        if (this.b != MODE.SingleOver) {
            m();
            return;
        }
        DJILogHelper.getInstance().LOGD(a, "restart", false, true);
        DataCameraSingleChoice.getInstance().setKey(i).start(new d(this) {
            final /* synthetic */ h b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(h.a, "restart success " + i2, false, true);
                if (i2 > 0) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DJILogHelper.getInstance().LOGD(h.a, "restart seek to " + i2, false, true);
                    this.b.a((long) i2);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(h.a, "restart fail", false, true);
            }
        });
    }

    public void c() {
        if (this.b == MODE.SinglePlay) {
            m();
        } else {
            this.j.setControlType(ControlType.Start).start(new d(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Start Success", false, true);
                    this.a.e = true;
                    this.a.f = false;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Start Fail", false, true);
                }
            });
        }
    }

    public void d() {
        if (this.b == MODE.SingleOver) {
            m();
        } else {
            this.j.setControlType(ControlType.Stop).start(new d(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Stop Success", false, true);
                    this.a.e = false;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Stop Fail", false, true);
                }
            });
        }
    }

    public void e() {
        if (this.b == MODE.SinglePause || this.b == MODE.SingleOver) {
            m();
        } else {
            this.j.setControlType(ControlType.Pause).start(new d(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Pause Success", false, true);
                    this.a.e = false;
                    this.a.f = true;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Pause Fail", false, true);
                }
            });
        }
    }

    public int f() {
        return this.c;
    }

    public int g() {
        return this.d;
    }

    public void a(final long j) {
        this.j.setControlType(ControlType.StepTo).setProgress((int) j).start(new d(this) {
            final /* synthetic */ h b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Seek To " + j + "ms Success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(h.a, "MediaPlayer Seek To " + j + "ms Fail", false, true);
            }
        });
    }

    public boolean h() {
        return this.e;
    }

    public boolean i() {
        return this.f;
    }

    public boolean l() {
        return this.g;
    }

    public void j() {
    }

    public void a(int i) {
    }

    public void a(dji.midware.media.i.g.a aVar) {
    }

    public void a(b bVar) {
    }

    public void m() {
        DJILogHelper.getInstance().LOGD(a, "当前状态和操作状态不符，当前状态为：" + this.b.name(), false, true);
    }

    public void onEventBackgroundThread(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        if (this.i != null) {
            if (this.b != dataCameraGetPushPlayBackParams.getMode()) {
                this.b = dataCameraGetPushPlayBackParams.getMode();
                if (this.b == MODE.SinglePlay) {
                    this.i.a();
                    this.e = true;
                    this.f = false;
                    this.g = false;
                } else if (this.b == MODE.SinglePause) {
                    this.i.b();
                    this.e = false;
                    this.f = true;
                    this.g = false;
                } else if (this.b == MODE.SingleOver) {
                    this.i.c();
                    this.e = false;
                    this.f = false;
                    this.g = true;
                }
            }
            if (this.b != MODE.SingleOver) {
                this.d = dataCameraGetPushPlayBackParams.getTotalTimeForWM();
            }
            if (this.b == MODE.SinglePlay) {
                this.c = dataCameraGetPushPlayBackParams.getCurrentForWM();
                this.i.a(this.c, this.d);
            }
        }
    }

    public void a(Object obj) {
    }
}
