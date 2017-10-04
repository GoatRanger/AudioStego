package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.camera.CameraParamRangeManager;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.PhotoState;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b$f;

public class a extends b {
    public static final String a = "Phantom 3 Standard Camera";
    public static final String b = "Phantom 3 Advanced Camera";
    public static final String c = "Phantom 3 Professional Camera";
    public static final String d = "Phantom 3 4K Camera";
    public static final String e = "Phantom 4 Camera";
    public static final String f = "Phantom 4 Professional Camera";
    public static final String g = "Zenmuse X3";
    public static final String h = "Zenmuse X5";
    public static final String i = "Zenmuse X5R";
    public static final String j = "Zenmuse XT";
    public static final String k = "Zenmuse Z3";
    public static final String l = "Mavic Pro";
    public static final String m = "Zenmuse Z30";
    private static final String o = "DJISDKCacheCameraAbstraction";
    protected CameraType n = CameraType.OTHER;
    private CameraParamRangeManager p;

    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        this.p = new CameraParamRangeManager(dji_sdksharedlib_hardware_abstractions_b_f, this.z);
        d();
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    public void e() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        this.p.onDestory();
        super.e();
    }

    protected void c() {
        onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (cameraType != this.n) {
            this.n = cameraType;
        }
        if (dataCameraGetPushStateInfo != null) {
            boolean z = dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Initialzing;
            boolean z2 = dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Invalid || dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Illegal || dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Unknow;
            boolean z3 = dataCameraGetPushStateInfo.getSDCardState() == SDCardState.WriteProtection;
            boolean z4 = dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Illegal;
            boolean z5 = dataCameraGetPushStateInfo.getSDCardState() != SDCardState.Unformat;
            boolean z6 = dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Formating;
            boolean z7 = dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Full;
            boolean z8 = dataCameraGetPushStateInfo.getSDCardState() != SDCardState.Invalid;
            boolean enabledPhoto = dataCameraGetPushStateInfo.getEnabledPhoto();
            boolean z9 = dataCameraGetPushStateInfo.getSDCardState() == SDCardState.Busy;
            boolean sDCardInsertState = dataCameraGetPushStateInfo.getSDCardInsertState();
            int sDCardTotalSize = dataCameraGetPushStateInfo.getSDCardTotalSize();
            int sDCardFreeSize = dataCameraGetPushStateInfo.getSDCardFreeSize();
            long remainedShots = dataCameraGetPushStateInfo.getRemainedShots();
            int remainedTime = dataCameraGetPushStateInfo.getRemainedTime();
            if (!dataCameraGetPushStateInfo.getFastPlayBackEnabled()) {
                a(Integer.valueOf(0), c(dji.sdksharedlib.b.b.z));
            }
            a(Boolean.valueOf(enabledPhoto), c(dji.sdksharedlib.b.b.bf));
            a(Boolean.valueOf(z), c(dji.sdksharedlib.b.b.aI));
            a(Boolean.valueOf(z2), c(dji.sdksharedlib.b.b.aJ));
            a(Boolean.valueOf(z3), c(dji.sdksharedlib.b.b.aK));
            a(Boolean.valueOf(z4), c(dji.sdksharedlib.b.b.aL));
            a(Boolean.valueOf(z5), c(dji.sdksharedlib.b.b.aM));
            a(Boolean.valueOf(z6), c(dji.sdksharedlib.b.b.aN));
            a(Boolean.valueOf(z7), c(dji.sdksharedlib.b.b.aO));
            a(Boolean.valueOf(z8), c(dji.sdksharedlib.b.b.aP));
            a(Boolean.valueOf(sDCardInsertState), c(dji.sdksharedlib.b.b.aQ));
            a(Integer.valueOf(sDCardTotalSize), c(dji.sdksharedlib.b.b.aR));
            a(Integer.valueOf(sDCardFreeSize), c(dji.sdksharedlib.b.b.aS));
            a(Long.valueOf(remainedShots), c(dji.sdksharedlib.b.b.aT));
            a(Integer.valueOf(remainedTime), c(dji.sdksharedlib.b.b.aU));
            a(Boolean.valueOf(z9), c(dji.sdksharedlib.b.b.aV));
            z7 = dataCameraGetPushStateInfo.getIsTimePhotoing();
            PhotoState photoState = dataCameraGetPushStateInfo.getPhotoState();
            z = (PhotoState.NO == photoState || PhotoState.OTHER == photoState) ? false : true;
            z2 = photoState == PhotoState.Multiple;
            z3 = photoState == PhotoState.Single;
            z4 = false;
            if (!(DataCameraGetPushShotParams.getInstance() == null || DataCameraGetPushShotParams.getInstance().getImageFormat() == 1 || !z3)) {
                z4 = true;
            }
            z5 = dataCameraGetPushStateInfo.getRecordState() == RecordType.STARTING || dataCameraGetPushStateInfo.getRecordState() == RecordType.START;
            z8 = dataCameraGetPushStateInfo.getHotState();
            z9 = dataCameraGetPushStateInfo.getSensorState();
            int videoRecordTime = dataCameraGetPushStateInfo.getVideoRecordTime();
            sDCardInsertState = dataCameraGetPushStateInfo.getIsStoring();
            int value = dataCameraGetPushStateInfo.getMode().value();
            switch (value) {
                case 6:
                    value = 2;
                    break;
                case 7:
                    value = 4;
                    break;
            }
            CameraMode find = CameraMode.find(value);
            boolean beInTrackingMode = dataCameraGetPushStateInfo.beInTrackingMode();
            a(Boolean.valueOf(z4), c(dji.sdksharedlib.b.b.aY));
            a(Boolean.valueOf(z7), c(dji.sdksharedlib.b.b.aZ));
            a(Boolean.valueOf(z2), c(dji.sdksharedlib.b.b.ba));
            a(Boolean.valueOf(z3), c(dji.sdksharedlib.b.b.aX));
            a(Boolean.valueOf(z5), c(dji.sdksharedlib.b.b.bc));
            a(Boolean.valueOf(z8), c(dji.sdksharedlib.b.b.bd));
            a(Boolean.valueOf(z9), c(dji.sdksharedlib.b.b.be));
            a(Integer.valueOf(videoRecordTime), c(dji.sdksharedlib.b.b.bg));
            a(Boolean.valueOf(sDCardInsertState), c(dji.sdksharedlib.b.b.bb));
            a(find, c(dji.sdksharedlib.b.b.b));
            a(cameraType, c(dji.sdksharedlib.b.b.bX));
            a(Boolean.valueOf(z), c(dji.sdksharedlib.b.b.bY));
            a(Boolean.valueOf(beInTrackingMode), c(dji.sdksharedlib.b.b.ca));
        }
    }

    protected void a() {
        a(dji.sdksharedlib.b.b.class, getClass());
    }

    protected void d() {
        a(Boolean.valueOf(E()), c(dji.sdksharedlib.b.b.bi));
        a(Boolean.valueOf(F()), c(dji.sdksharedlib.b.b.bj));
        a(Boolean.valueOf(A()), c(dji.sdksharedlib.b.b.bp));
        a(Boolean.valueOf(C()), c(dji.sdksharedlib.b.b.bq));
        a(Boolean.valueOf(G()), c(dji.sdksharedlib.b.b.bs));
        a(Boolean.valueOf(y()), c(dji.sdksharedlib.b.b.bh));
        a(Boolean.valueOf(u()), c(dji.sdksharedlib.b.b.bk));
        a(Boolean.valueOf(v()), c(dji.sdksharedlib.b.b.bl));
        a(Boolean.valueOf(w()), c(dji.sdksharedlib.b.b.bm));
        a(Boolean.valueOf(x()), c(dji.sdksharedlib.b.b.bn));
        a(Boolean.valueOf(z()), c(dji.sdksharedlib.b.b.bo));
        a(Boolean.valueOf(C()), c(dji.sdksharedlib.b.b.bq));
        a(Boolean.valueOf(x()), c(dji.sdksharedlib.b.b.bn));
        a(Boolean.valueOf(z()), c(dji.sdksharedlib.b.b.bo));
        a(Boolean.valueOf(B()), c(dji.sdksharedlib.b.b.br));
        a(M(), c(dji.sdksharedlib.b.b.aH));
        a(Boolean.valueOf(H()), c(dji.sdksharedlib.b.b.bt));
        a(Boolean.valueOf(N()), c(dji.sdksharedlib.b.b.ap));
        a(Boolean.valueOf(b()), c(dji.sdksharedlib.b.b.av));
    }

    protected boolean p() {
        return false;
    }

    protected boolean q() {
        return false;
    }

    protected boolean r() {
        return false;
    }

    protected boolean s() {
        return false;
    }

    protected boolean t() {
        return false;
    }

    protected boolean u() {
        return false;
    }

    protected boolean v() {
        return false;
    }

    protected boolean w() {
        return false;
    }

    protected boolean x() {
        return true;
    }

    protected boolean y() {
        return false;
    }

    protected boolean z() {
        return false;
    }

    protected boolean A() {
        return false;
    }

    protected boolean B() {
        return false;
    }

    protected boolean C() {
        return false;
    }

    protected boolean D() {
        return true;
    }

    protected boolean E() {
        return false;
    }

    protected boolean F() {
        return false;
    }

    protected boolean G() {
        return false;
    }

    public boolean H() {
        return false;
    }

    protected boolean I() {
        return false;
    }

    protected boolean J() {
        return false;
    }

    protected boolean K() {
        return false;
    }

    protected boolean L() {
        return false;
    }

    protected String M() {
        return "";
    }

    protected boolean N() {
        return true;
    }

    protected boolean b() {
        return false;
    }
}
