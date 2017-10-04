package dji.pilot2.upgrade.ble;

import dji.log.DJILogHelper;
import dji.logic.f.d;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.model.P3.DataBaseSetting;
import dji.pilot.publics.control.p3cupgrade.b.j;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class b {
    private static final String c = "DJIHandheldUpgradeHelper";
    DataBaseSetting a;
    boolean b;
    private Timer d;

    private static class a {
        public static final b a = new b();

        private a() {
        }
    }

    public static b getInstance() {
        return a.a;
    }

    private b() {
        this.a = new DataBaseSetting();
        if (this.d == null) {
            this.d = new Timer("hg300 gimbal upgrade control");
        }
        c.a().a(this);
    }

    public void onEventBackgroundThread(j jVar) {
        if (d.c(null)) {
            switch (jVar) {
                case UPGRADING:
                    if (!this.b) {
                        this.b = true;
                        if (this.d == null) {
                            this.d = new Timer("hg300 gimbal upgrade control");
                        }
                        this.d.schedule(new TimerTask(this) {
                            final /* synthetic */ b a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a(p.j).a(16).a(DeviceType.OFDM).a(new byte[]{(byte) 1}).a(600, 3).start(new dji.midware.e.d(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onSuccess(Object obj) {
                                        DJILogHelper.getInstance().LOGD(b.c, "gimbalOffSettersuccess", false, true);
                                    }

                                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                                        DJILogHelper.getInstance().LOGD(b.c, "gimbalOffSetteronFailure", false, true);
                                    }
                                });
                            }
                        }, 1000, 8000);
                        return;
                    }
                    return;
                case UPGRADE_FAILS:
                    this.b = false;
                    if (this.a != null) {
                        if (this.d != null) {
                            this.d.cancel();
                            this.d = null;
                        }
                        this.a.a(p.j).a(16).a(DeviceType.OFDM).a(600, 3).a(new byte[]{(byte) 3}).start(null);
                        return;
                    }
                    return;
                case UPGRADE_SUCCESS:
                    this.b = false;
                    if (this.a != null) {
                        if (this.d != null) {
                            this.d.cancel();
                            this.d = null;
                        }
                        this.a.a(p.j).a(16).a(DeviceType.OFDM).a(600, 3).a(new byte[]{(byte) 2}).start(null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void a(ArrayList<dji.midware.b.b> arrayList) {
        dji.midware.b.c instance = dji.midware.b.c.getInstance();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            dji.midware.b.b bVar = (dji.midware.b.b) it.next();
            if (instance.f().i().equals(bVar.a)) {
                instance.a(bVar.a);
                return;
            }
        }
    }
}
