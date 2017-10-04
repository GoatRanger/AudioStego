package dji.pilot.dji_groundstation.controller.DataMgr;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.here.android.mpa.mapping.Map;
import dji.gs.e.b;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycSendGpsInfo;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.dji_groundstation.a.f;

public class c extends a {
    private static final String d = "FollowMeDataMgr";
    private static c i = null;
    double b = 0.0d;
    double c = 0.0d;
    private b e = null;
    private double f = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private Handler g = new Handler(Looper.getMainLooper());
    private Runnable h = new Runnable(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.k) {
                e eVar = new e();
                eVar.s = 6;
                eVar.t = new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        if (obj != null) {
                            if (obj instanceof b) {
                                this.a.a.e = (b) obj;
                                if (this.a.a.f <= 0.0d) {
                                    this.a.a.f = f.a(this.a.a.e.b, this.a.a.e.c);
                                }
                            }
                            this.a.a.g.postDelayed(this.a.a.h, 100);
                        }
                    }

                    public void onFailure(a aVar) {
                    }
                };
                dji.thirdparty.a.c.a().e(eVar);
            }
        }
    };
    private Context j = null;
    private boolean k = false;

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (i == null) {
                i = new c();
            }
            cVar = i;
        }
        return cVar;
    }

    public void b(b bVar) {
        if (bVar != null && bVar.a()) {
            this.e = new b(bVar.b, bVar.c, bVar.d, bVar.e);
            if (dji.pilot.dji_groundstation.controller.d.getInstance().b().a(dji.pilot.dji_groundstation.a.d.c.m) && this.f <= 0.0d) {
                this.f = f.a(this.e.b, this.e.c);
            }
        }
    }

    private c() {
    }

    public void a(Context context) {
        this.j = context;
        this.k = false;
    }

    public boolean i() {
        return this.k;
    }

    public void j() {
        this.k = true;
        this.f = Map.MOVE_PRESERVE_ZOOM_LEVEL;
        m();
        if (this.j != null) {
            dji.pilot.dji_groundstation.controller.d.getInstance().c();
            dji.pilot.dji_groundstation.controller.d.getInstance().a(d$a.Normal);
            dji.pilot.dji_groundstation.controller.f.getInstance(this.j).b(false);
        }
    }

    public double k() {
        if (l() == null) {
            return 0.0d;
        }
        double a = f.a(l().b, l().c);
        if (a >= 0.0d) {
            return a;
        }
        return 0.0d;
    }

    public b l() {
        return this.e;
    }

    public void e() {
        super.e();
        this.e = null;
        this.g = null;
        j();
    }

    public void b(final d dVar) {
        if (!this.k) {
            this.b = DataOsdGetPushCommon.getInstance().getLatitude() - this.e.b;
            this.c = DataOsdGetPushCommon.getInstance().getLongitude() - this.e.c;
            if (this.g == null) {
                this.g = new Handler(Looper.getMainLooper());
            }
            this.g.post(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    if (this.b.k || dji.pilot.dji_groundstation.controller.d.getInstance().b().a() != dji.pilot.dji_groundstation.a.d.c.n.a()) {
                        return;
                    }
                    if (this.b.e != null && this.b.e.a() && this.b.e.b()) {
                        this.b.c(dVar);
                        if (!this.b.k) {
                            this.b.g.postDelayed(this, 100);
                            return;
                        }
                        return;
                    }
                    this.b.j();
                }
            });
        }
    }

    private void c(final d dVar) {
        e eVar = new e();
        eVar.s = 24;
        eVar.t = new d(this) {
            final /* synthetic */ c b;

            public void onSuccess(Object obj) {
                if (obj != null && (obj instanceof Float)) {
                    float floatValue = ((Float) obj).floatValue();
                    if (floatValue >= 10000.0f) {
                        dji.pilot.dji_groundstation.controller.d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_follow_me_device_has_no_gps));
                        this.b.j();
                    } else if (floatValue > b.a) {
                        dji.pilot.dji_groundstation.controller.d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_follow_me_can_not_get_user_location));
                        this.b.j();
                    } else if (Math.abs(f.a(this.b.e.b, this.b.e.c) - this.b.f) > 20.0d) {
                        this.b.j();
                    } else if (this.b.e.b != 0.0d && this.b.e.c != 0.0d) {
                        DataFlycSendGpsInfo instance = DataFlycSendGpsInfo.getInstance();
                        instance.setLatitude(f.b(this.b.e.b));
                        instance.setLongitude(f.b(this.b.e.c));
                        instance.setAltitude((short) 0);
                        instance.setHeading((short) 0);
                        instance.start(dVar);
                    }
                }
            }

            public void onFailure(a aVar) {
            }
        };
        dji.thirdparty.a.c.a().e(eVar);
    }

    private void m() {
        dji.pilot.dji_groundstation.controller.d.getInstance().a(false, 2, new d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(a aVar) {
            }
        });
        dji.pilot.dji_groundstation.controller.d.getInstance().a(d$a.Normal);
        dji.pilot.dji_groundstation.controller.d.getInstance().c();
        e eVar = new e();
        eVar.s = 1;
        dji.thirdparty.a.c.a().e(eVar);
    }
}
