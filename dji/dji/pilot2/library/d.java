package dji.pilot2.library;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$c;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.e.c;

public class d {
    private static boolean A = false;
    private static boolean B = false;
    private static d C = null;
    public static boolean a = false;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 8;
    public static final int k = 9;
    public static int l = 0;
    public static int m = 1;
    public static int n = 2;
    public static int o = 3;
    public static int p = 4;
    public static int q = 5;
    public static int r = 6;
    public static int s = 7;
    public static int t = 8;
    private static volatile boolean v = false;
    private static boolean w = false;
    private static boolean x = false;
    private static boolean y = false;
    private static boolean z = false;
    private MODE D;
    DJIGenSettingDataManager$c u = null;

    private d() {
        k();
    }

    public static d getInstance() {
        if (C == null) {
            C = new d();
        }
        return C;
    }

    public boolean a() {
        if ((v && !b.e(null)) || w || A || x || z || B || MixAlbumSyncManager.isInThread) {
            return false;
        }
        this.D = DataCameraGetPushStateInfo.getInstance().getMode();
        if ((this.D == c.b() || b.c(null) || (this.D == MODE.RECORD && DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.NO)) && j()) {
            return true;
        }
        return false;
    }

    public boolean b() {
        return true;
    }

    public boolean c() {
        if (j()) {
            return true;
        }
        return false;
    }

    public boolean d() {
        if (!A && j()) {
            return true;
        }
        return false;
    }

    public boolean e() {
        if ((v && !b.e(null)) || w || x || B || !j()) {
            return false;
        }
        return true;
    }

    public boolean f() {
        ProductType c = i.getInstance().c();
        if ((v && !b.e(c)) || w || A || x || z || B || !j()) {
            return false;
        }
        return true;
    }

    private boolean j() {
        ProductType c = i.getInstance().c();
        if (!ServiceManager.getInstance().isRemoteOK() && !b.h(c)) {
            return false;
        }
        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
        if (sDCardState == SDCardState.None || sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal || DataCameraGetPushStateInfo.getInstance().getRecordState() != RecordType.NO) {
            return false;
        }
        if (c == ProductType.litchiS || c == ProductType.litchiC || c == ProductType.P34K || c.a(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            return true;
        }
        if (c.c()) {
            return true;
        }
        return false;
    }

    public int g() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            ProductType c = i.getInstance().c();
            SDCardState sDCardState;
            if (c == ProductType.litchiS || c == ProductType.litchiC || c == ProductType.Grape2 || c == ProductType.A2 || b.h(c) || c == ProductType.P34K || c.a(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                if (DataCameraGetPushStateInfo.getInstance().getRecordState() != RecordType.NO) {
                    return 6;
                }
                sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
                if (sDCardState == SDCardState.None) {
                    return 7;
                }
                if (sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal) {
                    return 8;
                }
                return DataOsdGetPushPowerStatus.getInstance().getPowerStatus() == 1 ? 9 : 0;
            } else if (!c.f()) {
                return 2;
            } else {
                if (c.g()) {
                    return 3;
                }
                if (c.e()) {
                    return 4;
                }
                if (c.d()) {
                    return 5;
                }
                if (DataCameraGetPushStateInfo.getInstance().getRecordState() != RecordType.NO) {
                    return 6;
                }
                sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
                if (sDCardState == SDCardState.None) {
                    return 7;
                }
                if (sDCardState == SDCardState.Invalid) {
                    return 8;
                }
                return 0;
            }
        } else if (ServiceManager.getInstance().isConnected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void a(boolean z) {
        A = z;
    }

    public void b(boolean z) {
        v = z;
    }

    public void c(boolean z) {
        w = z;
    }

    public void d(boolean z) {
        y = z;
    }

    public void e(boolean z) {
        z = z;
    }

    public void f(boolean z) {
        x = z;
    }

    public void g(boolean z) {
        B = z;
    }

    public boolean h() {
        return y;
    }

    public boolean i() {
        return A;
    }

    private void k() {
        this.u = new DJIGenSettingDataManager$c(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i != 15) {
                }
            }

            public void b(int i) {
            }

            public void a(int i, boolean z) {
            }

            public void a(int i, boolean z, int i2, a aVar) {
            }
        };
        DJIGenSettingDataManager.getInstance().a(this.u);
    }
}
