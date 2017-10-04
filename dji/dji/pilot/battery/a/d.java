package dji.pilot.battery.a;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetParams;

public class d {
    public static final String[] a = new String[]{"g_config.voltage.level_1_protect_0", "g_config.voltage.level_2_protect_0", "g_config.voltage.battery_cell_0"};
    private static d b = null;
    private float c = -1.0f;
    private float d = -1.0f;

    public static d getInstance() {
        if (b == null) {
            b = new d();
        }
        return b;
    }

    private d() {
        a();
    }

    public void a() {
        new DataFlycGetParams().setInfos(a).start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                int intValue = dji.midware.data.manager.P3.d.read(d.a[2]).value.intValue();
                this.a.c = ((float) (dji.midware.data.manager.P3.d.read(d.a[0]).value.intValue() * intValue)) / 1000.0f;
                this.a.d = ((float) (intValue * dji.midware.data.manager.P3.d.read(d.a[1]).value.intValue())) / 1000.0f;
                DJILogHelper.getInstance().LOGD("pm820", "***1: " + this.a.c + " 2: " + this.a.d, false, true);
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("pm820", "***on nosmart battery voltage get fail!", false, true);
            }
        });
    }

    public void b() {
        this.c = -1.0f;
        this.d = -1.0f;
    }

    public float c() {
        return this.c;
    }

    public float d() {
        return this.d;
    }
}
