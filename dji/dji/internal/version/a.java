package dji.internal.version;

import android.os.Handler;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class a {
    private static final String a = "FirmwareVersionRequest";
    private static final int g = 3;
    private String[] b = null;
    private HashMap<String, DJIDeviceVersion> c = null;
    private ArrayList<DJIDeviceVersion> d = null;
    private b e = null;
    private int f = 0;
    private Handler h;
    private ArrayList<DJIDeviceVersion> i;

    public interface b {
        void a(boolean z, ArrayList<DJIDeviceVersion> arrayList);
    }

    private class a implements d {
        final /* synthetic */ a a;
        private DJIDeviceVersion b;

        public a(a aVar, DJIDeviceVersion dJIDeviceVersion) {
            this.a = aVar;
            this.b = dJIDeviceVersion;
        }

        public void onSuccess(Object obj) {
            DataCommonGetVersion dataCommonGetVersion = (DataCommonGetVersion) obj;
            if (this.b.deviceType == DeviceType.FLYC && this.b.moduleId == 5) {
                this.b.setVersion(dataCommonGetVersion.getLoader("."));
            } else if (this.b.deviceType == DeviceType.CAMERA && this.b.moduleId == 1) {
                this.b.setVersion(dataCommonGetVersion.getLoader("."));
            } else {
                this.b.setVersion(dataCommonGetVersion.getFirmVer("."));
            }
            if ((this.b.deviceType == DeviceType.DM368_G || this.b.deviceType == DeviceType.FPGA_G || this.b.deviceType == DeviceType.TRANSFORM_G || this.b.deviceType == DeviceType.OSD) && this.b.moduleId != dataCommonGetVersion.getLoaderByte(2)) {
                this.b.version = Long.MIN_VALUE;
                this.b.versionStr = null;
            }
            if (this.b.deviceType == DeviceType.DM368_G && this.b.version == 0) {
                this.b.version = Long.MIN_VALUE;
                this.b.versionStr = null;
            }
            this.a.c.remove(this.b.firmware);
            this.a.b();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.a.a("fail : " + this.b.firmware);
            this.b.version = Long.MIN_VALUE;
            this.a.b();
        }
    }

    public a(String[] strArr, b bVar) {
        int i = 0;
        this.b = strArr;
        this.c = new HashMap();
        this.d = new ArrayList();
        int length = strArr.length;
        while (i < length) {
            String str = strArr[i];
            DJIDeviceVersion dJIDeviceVersion = new DJIDeviceVersion(str);
            this.c.put(str, dJIDeviceVersion);
            this.d.add(dJIDeviceVersion);
            i++;
        }
        this.e = bVar;
        this.f = 3;
        this.h = new Handler(dji.midware.util.b.b());
        a();
    }

    private void a() {
        this.i = new ArrayList();
        for (Entry value : this.c.entrySet()) {
            this.i.add(value.getValue());
        }
        b();
    }

    private void b() {
        if (this.i.size() > 0) {
            DJIDeviceVersion dJIDeviceVersion = (DJIDeviceVersion) this.i.get(0);
            this.i.remove(0);
            d aVar = new a(this, dJIDeviceVersion);
            if (dJIDeviceVersion.deviceType == DeviceType.DM368_G || dJIDeviceVersion.deviceType == DeviceType.FPGA_G || dJIDeviceVersion.deviceType == DeviceType.TRANSFORM_G || dJIDeviceVersion.deviceType == DeviceType.OSD || dJIDeviceVersion.deviceType == DeviceType.CAMERA) {
                new DataCommonGetVersion().setDeviceType(dJIDeviceVersion.deviceType).start(aVar);
            } else {
                new DataCommonGetVersion().setDeviceType(dJIDeviceVersion.deviceType).setDeviceModel(dJIDeviceVersion.moduleId).start(aVar);
            }
        } else if (this.c.size() == 0) {
            a(true);
        } else {
            a("checkIsFinish retry time : " + this.f);
            this.f--;
            if (this.f > 0) {
                this.h.postDelayed(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a();
                    }
                }, (long) ((3 - this.f) * 1500));
            } else {
                a(false);
            }
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

    private void a(String str) {
        DJILogHelper.getInstance().LOGD(a, str);
    }
}
