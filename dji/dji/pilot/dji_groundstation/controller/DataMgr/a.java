package dji.pilot.dji_groundstation.controller.DataMgr;

import dji.gs.e.b;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;

public class a {
    private static final String b = "CommonDataMgr";
    protected b a;
    private int c;
    private int d;
    private int e;
    private ParamInfo f;
    private ParamInfo g;
    private boolean h;
    private boolean i;

    public a() {
        this.c = -1;
        this.d = 0;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.a = null;
        this.h = false;
        this.i = false;
        this.a = new b(DataOsdGetPushHome.getInstance().getLatitude(), DataOsdGetPushHome.getInstance().getLongitude());
    }

    public int a() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(b bVar) {
        this.a = new b(bVar.b, bVar.c);
    }

    public boolean b() {
        return this.i;
    }

    public boolean c() {
        return this.h;
    }

    public void d() {
        this.h = false;
    }

    public void e() {
    }

    public void a(final d dVar) {
        this.g = dji.midware.data.manager.P3.d.read("g_config.flying_limit.max_height_0");
        new DataFlycGetParams().setInfos(new String[]{this.g.name}).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                this.b.g = dji.midware.data.manager.P3.d.read(this.b.g.name);
                this.b.c = this.b.g.value.intValue();
                this.b.i = true;
                if (dVar != null) {
                    dVar.onSuccess(obj);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dji.pilot.dji_groundstation.controller.d.getInstance().a(3, aVar);
                this.b.i = false;
                if (dVar != null) {
                    dVar.onFailure(aVar);
                }
            }
        });
    }

    public void b(int i) {
        this.c = i;
    }

    public int f() {
        return this.c;
    }

    public int g() {
        return this.d;
    }

    public boolean h() {
        return this.d > 0 && (this.f == null || this.f.isCorrect(Integer.valueOf(this.d)));
    }

    public void c(final int i) {
        this.h = false;
        this.f = dji.midware.data.manager.P3.d.read("g_config.go_home.fixed_go_home_altitude_0");
        if (i > this.c) {
            dji.pilot.dji_groundstation.controller.d.getInstance().a(4, null);
        } else if (i == DataOsdGetPushHome.getInstance().getGoHomeHeight()) {
            this.h = true;
            this.d = i;
        } else if (i <= 0 || !(this.f == null || this.f.isCorrect(Integer.valueOf(i)))) {
            this.d = -1;
            dji.pilot.dji_groundstation.controller.d.getInstance().a(5, null);
        } else {
            new DataFlycSetParams().a(this.f.name, Integer.valueOf(i)).start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    this.b.d = i;
                    this.b.h = true;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.h = false;
                    dji.pilot.dji_groundstation.controller.d.getInstance().a(6, aVar);
                }
            });
        }
    }
}
