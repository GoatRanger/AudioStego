package dji.pilot.set.view.a;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class c {
    private static final String a = "FirmwareVersionRequest";
    private String[] b = null;
    private HashMap<String, b> c = null;
    private ArrayList<b> d = null;
    private b e = null;
    private int f = 0;
    private ArrayList<b> g;

    private class a implements d {
        final /* synthetic */ c a;
        private b b;

        public a(c cVar, b bVar) {
            this.a = cVar;
            this.b = bVar;
        }

        public void onSuccess(Object obj) {
            DataCommonGetVersion dataCommonGetVersion = (DataCommonGetVersion) obj;
            if (this.b.b == DeviceType.FLYC && this.b.c == 5) {
                this.b.b(dataCommonGetVersion.getLoader("."));
            } else if (this.b.b == DeviceType.CAMERA && this.b.c == 1) {
                this.b.b(dataCommonGetVersion.getLoader("."));
            } else {
                this.b.b(dataCommonGetVersion.getFirmVer("."));
            }
            if ((this.b.b == DeviceType.DM368_G || this.b.b == DeviceType.FPGA_G || this.b.b == DeviceType.TRANSFORM_G || this.b.b == DeviceType.OSD) && this.b.c != dataCommonGetVersion.getLoaderByte(2)) {
                this.b.d = Long.MIN_VALUE;
                this.b.e = null;
            }
            if (this.b.b == DeviceType.DM368_G && this.b.d == 0) {
                this.b.d = Long.MIN_VALUE;
                this.b.e = null;
            }
            this.a.c.remove(this.b.a);
            this.a.b();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.b.d = Long.MIN_VALUE;
            this.a.b();
        }
    }

    public interface b {
        void a(boolean z, ArrayList<b> arrayList);
    }

    public c(String[] strArr, b bVar) {
        int i = 0;
        this.b = strArr;
        this.c = new HashMap();
        this.d = new ArrayList();
        int length = strArr.length;
        while (i < length) {
            String str = strArr[i];
            b bVar2 = new b(str);
            this.c.put(str, bVar2);
            this.d.add(bVar2);
            i++;
        }
        this.e = bVar;
        this.f = 3;
        a();
    }

    private void a() {
        this.g = new ArrayList();
        for (Entry value : this.c.entrySet()) {
            this.g.add(value.getValue());
        }
        b();
    }

    private void b() {
        if (this.g.size() > 0) {
            b bVar = (b) this.g.get(0);
            this.g.remove(0);
            d aVar = new a(this, bVar);
            if (bVar.b == DeviceType.DM368_G || bVar.b == DeviceType.FPGA_G || bVar.b == DeviceType.TRANSFORM_G || bVar.b == DeviceType.OSD || bVar.b == DeviceType.CAMERA) {
                new DataCommonGetVersion().setDeviceType(bVar.b).start(aVar);
            } else {
                new DataCommonGetVersion().setDeviceType(bVar.b).setDeviceModel(bVar.c).start(aVar);
            }
        } else if (this.c.size() == 0) {
            a(true);
        } else if (this.f > 0) {
            a();
            this.f--;
        } else {
            a(false);
        }
    }

    private void a(boolean z) {
        if (this.e != null) {
            this.e.a(z, this.d);
        }
        this.d = null;
        this.b = null;
        this.e = null;
    }
}
