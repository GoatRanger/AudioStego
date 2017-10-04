package dji.pilot.upgrade;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class c {
    private static final String a = "FirmwareVersionRequest";
    private String[] b = null;
    private HashMap<String, FirmwareVersion> c = null;
    private ArrayList<FirmwareVersion> d = null;
    private b e = null;
    private int f = 0;
    private ArrayList<FirmwareVersion> g;

    public interface b {
        void onResultCallBack(boolean z, ArrayList<FirmwareVersion> arrayList);
    }

    private class a implements d {
        final /* synthetic */ c a;
        private FirmwareVersion b;

        public a(c cVar, FirmwareVersion firmwareVersion) {
            this.a = cVar;
            this.b = firmwareVersion;
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
            if (this.b.deviceType == DeviceType.BATTERY) {
                int firmByte = dataCommonGetVersion.getFirmByte(1);
                if (firmByte == 2 && this.b.firmware.equals("1100")) {
                    this.b.version = Long.MIN_VALUE;
                    this.b.versionStr = null;
                }
                if (firmByte == 1 && this.b.firmware.equals("1101")) {
                    this.b.version = Long.MIN_VALUE;
                    this.b.versionStr = null;
                }
            }
            if (this.b.deviceType == DeviceType.DM368_G && this.b.version == 0) {
                this.b.version = Long.MIN_VALUE;
                this.b.versionStr = null;
            }
            this.a.c.remove(this.b.firmware);
            this.a.b();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            DJILogHelper.getInstance().LOGD("", "fail : " + this.b.firmware, false, true);
            this.b.version = Long.MIN_VALUE;
            this.a.b();
        }
    }

    public c(String[] strArr, b bVar) {
        int i = 0;
        this.b = strArr;
        this.c = new HashMap();
        this.d = new ArrayList();
        int length = strArr.length;
        while (i < length) {
            String str = strArr[i];
            FirmwareVersion firmwareVersion = new FirmwareVersion(str);
            this.c.put(str, firmwareVersion);
            this.d.add(firmwareVersion);
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
            FirmwareVersion firmwareVersion = (FirmwareVersion) this.g.get(0);
            this.g.remove(0);
            d aVar = new a(this, firmwareVersion);
            if (firmwareVersion.deviceType == DeviceType.DM368_G || firmwareVersion.deviceType == DeviceType.FPGA_G || firmwareVersion.deviceType == DeviceType.TRANSFORM_G || firmwareVersion.deviceType == DeviceType.OSD || (firmwareVersion.deviceType == DeviceType.CAMERA && (firmwareVersion.moduleId == 0 || firmwareVersion.moduleId == 1))) {
                new DataCommonGetVersion().setDeviceType(firmwareVersion.deviceType).start(aVar, 500, 5);
            } else {
                new DataCommonGetVersion().setDeviceType(firmwareVersion.deviceType).setDeviceModel(firmwareVersion.moduleId).start(aVar, 500, 5);
            }
        } else if (this.c.size() == 0) {
            a(true);
        } else {
            a(false);
        }
    }

    private void a(boolean z) {
        if (this.e != null) {
            this.e.onResultCallBack(z, this.d);
        }
        this.d = null;
        this.b = null;
        this.e = null;
    }
}
