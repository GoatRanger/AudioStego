package dji.pilot.fpv.camera.control;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler.Callback;
import android.os.Message;
import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraExposureMode;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRawParams.DiskStatus;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataCameraSetPhotoMode;
import dji.midware.data.model.P3.DataNewSpecialControl;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.media.DJIVideoDecoder;
import dji.pilot.R;
import dji.pilot.fpv.camera.control.b.b;
import dji.pilot.fpv.camera.more.a$a;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c.g;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.newfpv.f.l;
import dji.pilot.publics.objects.k;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.c.d;
import java.util.HashMap;
import java.util.Locale;

public class a implements b, dji.pilot.fpv.camera.control.b.a, g, dji.pilot.publics.objects.k.a, d {
    private static final String d = a.class.getSimpleName();
    private static final boolean e = true;
    private static final int f = 4096;
    private c N = null;
    private c O = null;
    private c P = null;
    private c Q = null;
    private c R = null;
    private c S = null;
    private c T = null;
    private c U = null;
    private c V = null;
    private c W = null;
    private c X = null;
    private c Y = null;
    private c Z = null;
    private final Runnable aa = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            if (CameraMode.Playback == this.a.n) {
                this.a.e(false);
            }
        }
    };
    private Callback ab = new Callback(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 4096:
                    DJIVideoDecoder e = ServiceManager.getInstance().e();
                    if (e != null) {
                        dji.pilot.fpv.camera.a.a.a(a.d, "fixDelayStream end");
                        e.setConnectLosedelay(2000);
                        break;
                    }
                    break;
            }
            return true;
        }
    };
    private b g = null;
    private Context h = null;
    private k i = null;
    private final dji.pilot.fpv.camera.more.a j = dji.pilot.fpv.camera.more.a.getInstance();
    private TYPE k = TYPE.b;
    private int l = 0;
    private CameraType m = CameraType.OTHER;
    private CameraMode n = CameraMode.Unknown;
    private CameraMode o = CameraMode.ShootPhoto;
    private boolean p = false;
    private SDCardState q = SDCardState.OTHER;
    private int r = -1;
    private boolean s = false;
    private c t = null;
    private c u = null;

    private void k() {
        this.t = dji.sdksharedlib.a.b.k(i.o);
        this.u = dji.sdksharedlib.a.b.k(i.p);
        this.N = dji.sdksharedlib.a.b.k(i.u);
        this.O = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.d.ck);
        this.P = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bX);
        this.Q = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.b);
        this.R = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.m);
        this.S = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bg);
        this.T = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bb);
        this.U = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.aZ);
        this.V = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bY);
        this.W = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bc);
        this.X = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bx);
        this.Y = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.d);
        this.Z = dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.ca);
    }

    public void a(b bVar, int i) {
        this.g = bVar;
        this.h = bVar.getSelf().getContext();
        this.l = dji.pilot.publics.objects.g.b(this.h, b.c, 0);
        k();
        dji.pilot.fpv.control.b.a = com.dji.frame.c.d.a(this.h, "RECORD_VOICE/");
        com.dji.frame.c.d.a(dji.pilot.fpv.control.b.a);
        this.i = new k(this, this.ab);
        if (ServiceManager.getInstance().isRemoteOK()) {
            this.j.m();
        }
        x();
        if (dji.pilot.publics.e.c.a()) {
            dji.pilot.fpv.control.b.p = false;
        }
    }

    public void a() {
        boolean z;
        dji.midware.e.d anonymousClass1;
        boolean z2 = true;
        final CameraMode cameraMode = CameraMode.ShootPhoto == this.n ? CameraMode.RecordVideo : CameraMode.ShootPhoto;
        MODE a = a(cameraMode);
        if (CameraType.DJICameraTypeFC6310 == this.m) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            z2 = false;
        }
        if (z2) {
            this.g.setSwitchViewEnable(false);
            this.g.setPhotoViewEnable(false);
            this.g.setPIVViewEnable(false);
            this.g.setVideoViewEnable(false);
            this.g.setPlayBackViewEnable(false);
            anonymousClass1 = new dji.midware.e.d(this) {
                final /* synthetic */ a c;

                public void onSuccess(Object obj) {
                    this.c.i.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.a(false, z, cameraMode);
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.c.a(false, z, this.c.n);
                }
            };
        } else {
            anonymousClass1 = null;
        }
        if (z) {
            DataNewSpecialControl.getInstance().a(a).start(anonymousClass1);
        } else {
            DataCameraSetMode.getInstance().a(a).start(anonymousClass1);
        }
        CameraExposureMode cameraExposureMode = (CameraExposureMode) dji.sdksharedlib.a.a.a(this.R);
        if (cameraExposureMode != null && CameraExposureMode.Cine == cameraExposureMode) {
            new DataCameraSetExposureMode().a(ExposureMode.b.a()).start(null);
        }
    }

    private void a(final boolean z, final boolean z2, final CameraMode cameraMode) {
        this.i.post(new Runnable(this) {
            final /* synthetic */ a d;

            public void run() {
                this.d.g.setSwitchViewEnable(true);
                if (!z2) {
                    if (CameraMode.RecordVideo == cameraMode) {
                        this.d.g.showVideoView();
                    } else {
                        this.d.g.showPhotoView();
                    }
                }
                if (z) {
                    dji.thirdparty.a.c.a().e(l.CENTER);
                }
                this.d.q();
                this.d.d(false);
            }
        });
    }

    private void l() {
        if (dji.pilot.publics.e.a.c(dji.midware.data.manager.P3.i.getInstance().c())) {
            int i = this.l;
            this.l = i + 1;
            if (i < 3) {
                dji.pilot.publics.objects.g.a(this.h, b.c, this.l);
                DJIErrorPopView.b.b(DJIErrorPopView.d.a, R.string.camera_focus_tip, 0, DJIErrorPopView.c.a, f.a);
            }
        }
    }

    public void a(int i) {
        if (CameraType.DJICameraTypeFC550Raw == this.m && dji.pilot.fpv.d.b.t()) {
            dji.pilot.fpv.camera.a.a.a(d, "caseX5RException");
            return;
        }
        boolean a;
        e.a("FPV_RightBarView_CameraControllView_Button_TakePhoto");
        HashMap hashMap = new HashMap();
        hashMap.put(dji.pilot.fpv.d.d.dI, dji.midware.data.manager.P3.i.getInstance().c().toString());
        e.a(g.H, hashMap);
        if (CameraType.DJICameraTypeFC220 == this.m || CameraType.DJICameraTypeFC220S == this.m) {
            a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.Z), false);
            dji.pilot.fpv.camera.a.a.a(d, "Camera Tracking mode-" + a + com.alipay.sdk.j.i.b);
            if (a) {
                l();
                DataSpecialControl.getInstance().setPhotoType(TYPE.b, 1, 0).start(20);
                return;
            }
        }
        a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.U), false);
        if (TYPE.g == this.k && a) {
            this.g.stopAnimVideo();
            DataSpecialControl.getInstance().setPhotoType(TYPE.a).start(20);
            return;
        }
        l();
        if (TYPE.g == this.k) {
            DataSpecialControl.getInstance().setPhotoType(this.k, 255, this.j.n()).start(20);
        } else {
            DataSpecialControl.getInstance().setPhotoType(this.k, this.j.n(), 0).start(20);
        }
        dji.pilot.fpv.camera.a.a.a(null, "TakePhoto-" + this.k + com.alipay.sdk.j.i.b + this.j.n());
    }

    public void b() {
        DataSpecialControl.getInstance().setPhotoType(TYPE.b, 1, 0).start(20);
    }

    public void c() {
        boolean z = false;
        e.a("FPV_RightBarView_CameraControllView_Button_TakeVideo");
        e.c(g.I);
        if (dji.midware.data.manager.P3.i.getInstance().c() == ProductType.OrangeRAW && dji.pilot.fpv.d.b.t()) {
            dji.pilot.fpv.camera.a.a.a(d, "caseX5RException");
            return;
        }
        final boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.W), false);
        if (!a) {
            l();
        }
        if (DataCameraGetPushRawParams.getInstance().isGetted() && this.m == CameraType.DJICameraTypeFC550Raw && DataCameraGetPushRawParams.getInstance().getDiskStatus() == DiskStatus.FULL && !a) {
            dji.pilot.publics.widget.b.a(this.h, (int) R.string.fpv_record_ssd_full_tittle, (int) R.string.fpv_record_ssd_full_tip, (int) R.string.app_cancel, null, (int) R.string.app_isee, new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataSpecialControl.getInstance().setRecordType(!a).start(20);
                    dialogInterface.dismiss();
                }
            }).show();
            return;
        }
        DataSpecialControl instance = DataSpecialControl.getInstance();
        if (!a) {
            z = true;
        }
        instance.setRecordType(z).start(20);
    }

    public void d() {
        if (ServiceManager.getInstance().isConnected()) {
            e.a("FPV_RightBarView_CameraControllView_Button_PlayBack");
            dji.pilot.fpv.control.b.p = true;
            dji.pilot.c.d.a = a(this.o);
            DataSpecialControl.getInstance().setPlayBackType(true).start(20);
        }
    }

    public void e() {
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.CAMERACTRL_MENU);
    }

    public boolean b(int i) {
        return CameraMode.RecordVideo == this.n;
    }

    public boolean c(int i) {
        if (CameraType.DJICameraTypeTau336 == this.m || CameraType.DJICameraTypeTau640 == this.m) {
            return true;
        }
        if (CameraType.DJICameraTypeFC6310 != this.m) {
            return false;
        }
        CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = (CameraVideoResolutionAndFrameRate) dji.sdksharedlib.a.a.a(this.Y);
        if (cameraVideoResolutionAndFrameRate != null && cameraVideoResolutionAndFrameRate.getResolution() == CameraVideoResolution.Resolution_4096x2160 && cameraVideoResolutionAndFrameRate.getFrameRate() == CameraVideoFrameRate.FrameRate_60fps) {
            return false;
        }
        return true;
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (this.t.equals(cVar)) {
            a(this.t, s.cQ);
        } else if (this.u.equals(cVar)) {
            a(this.u, s.cP);
        } else if (this.N.equals(cVar)) {
            a(this.N, s.cO);
        } else if (this.P.equals(cVar)) {
            a(false);
        } else if (this.R.equals(cVar)) {
            w();
        } else if (this.Q.equals(cVar)) {
            f(false);
        } else if (this.S.equals(cVar)) {
            u();
        } else if (this.T.equals(cVar)) {
            t();
        } else if (this.U.equals(cVar)) {
            s();
        } else if (this.V.equals(cVar)) {
            d(true);
        } else if (this.W.equals(cVar)) {
            r();
        } else if (this.X.equals(cVar)) {
            o();
        } else if (this.O.equals(cVar)) {
            b(false);
        } else if (this.Z.equals(cVar)) {
            m();
        }
    }

    private void m() {
        if (dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.Z), false)) {
            DataCameraSetPhotoMode.getInstance().a(TYPE.b).a(0).c(255).b(0).d(0).start(null);
            this.g.updatePhotoView(R.drawable.wm220_btn_cam_capture_nor, 0);
            boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.U), false);
            if (TYPE.g == this.k && a) {
                this.g.stopAnimVideo();
                DataSpecialControl.getInstance().setPhotoType(TYPE.a).start(20);
                return;
            }
            return;
        }
        this.j.m();
        x();
    }

    private void a(boolean z) {
        CameraType cameraType = (CameraType) dji.sdksharedlib.a.a.a(this.P);
        if (cameraType != this.m || z) {
            this.m = cameraType;
            if (!this.p) {
                return;
            }
            if (c(0)) {
                this.g.showPIVView();
            } else {
                this.g.hidePIVView();
            }
        }
    }

    private void n() {
        f(true);
        w();
        t();
        s();
        d(true);
        r();
        u();
        o();
    }

    public void onEventMainThread(o oVar) {
        b(true);
    }

    private void b(boolean z) {
        dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.O), false);
        boolean isRemoteOK = ServiceManager.getInstance().isRemoteOK();
        dji.pilot.fpv.camera.a.a.a(d, "Connection-" + isRemoteOK);
        if (this.s != isRemoteOK || z) {
            this.s = isRemoteOK;
            this.g.setSelfEnable(isRemoteOK);
            if (isRemoteOK) {
                this.g.setSettingViewEnable(true);
                d(false);
                q();
                n();
                return;
            }
            this.g.setSwitchViewEnable(false);
            this.g.setPhotoViewEnable(false);
            this.g.setPIVViewEnable(false);
            this.g.setVideoViewEnable(false);
            this.g.setPlayBackViewEnable(false);
            this.g.setSettingViewEnable(false);
        }
    }

    private void o() {
    }

    private void p() {
        this.g.setSettingViewEnable(this.s);
    }

    private void a(boolean z, boolean z2, boolean z3) {
        b bVar = this.g;
        boolean z4 = (!this.s || z || z2 || z3) ? false : true;
        bVar.setSwitchViewEnable(z4);
    }

    private void c(boolean z) {
        boolean z2 = false;
        dji.pilot.fpv.camera.a.a.a(null, "conn-" + this.s + com.alipay.sdk.j.i.b + this.q + com.alipay.sdk.j.i.b + z);
        b bVar = this.g;
        if (!z && a(this.s, this.q, false)) {
            z2 = true;
        }
        bVar.setPIVViewEnable(z2);
    }

    private void q() {
        this.g.setVideoViewEnable(a(this.s, this.q, false));
    }

    private void a(boolean z, boolean z2) {
        boolean z3 = false;
        b bVar = this.g;
        if ((z2 || !z) && a(this.s, this.q, false)) {
            z3 = true;
        }
        bVar.setPhotoViewEnable(z3);
    }

    private void b(boolean z, boolean z2, boolean z3) {
        boolean z4 = true;
        b bVar = this.g;
        if (z || z2 || z3 || !a(this.s, this.q, true)) {
            z4 = false;
        }
        bVar.setPlayBackViewEnable(z4);
    }

    private void r() {
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.W), false);
        boolean a2 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.V), false);
        boolean a3 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.U), false);
        dji.pilot.fpv.camera.a.a.a(d, "isRecording-" + a + ";isShootingPhoto-" + a2 + ";isShootingIntervalPhoto-" + a3 + com.alipay.sdk.j.i.b);
        a(a, a2, a3);
        b(a, a2, a3);
        if (this.p != a) {
            if (a) {
                this.g.showRecordingView();
                this.g.startAnimVideo();
                A();
                if (c(0)) {
                    this.g.showPIVView();
                } else {
                    this.g.hidePIVView();
                }
            } else {
                if (c(0)) {
                    this.g.hidePIVView();
                }
                this.g.stopAnimVideo();
                this.g.hideRecordingView();
                B();
            }
            this.p = a;
        }
    }

    private void d(boolean z) {
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.W), false);
        boolean a2 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.V), false);
        boolean a3 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.U), false);
        dji.pilot.fpv.camera.a.a.a(d, "isRecording-" + a + ";isShootingPhoto-" + a2 + ";isShootingIntervalPhoto-" + a3 + com.alipay.sdk.j.i.b);
        a(a, a2, a3);
        c(a2);
        a(a2, a3);
        b(a, a2, a3);
        if (a2 && z) {
            z();
            this.g.startTakePhoto(this.k, this.j.n());
        }
    }

    private void s() {
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.W), false);
        boolean a2 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.V), false);
        boolean a3 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.U), false);
        dji.pilot.fpv.camera.a.a.a(d, "isRecording-" + a + ";isShootingPhoto-" + a2 + ";isShootingIntervalPhoto-" + a3 + com.alipay.sdk.j.i.b);
        a(a, a2, a3);
        a(a2, a3);
        b(a, a2, a3);
        if (a3) {
            this.g.updatePhotoView(R.drawable.wm220_btn_cam_pano_nor, 0);
        } else {
            x();
        }
    }

    private void t() {
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.T), false);
        dji.pilot.fpv.camera.a.a.a(d, "isStoring-" + a);
        if (a) {
            dji.pilot.fpv.control.b.u = true;
            this.g.showStoringView();
            return;
        }
        dji.thirdparty.a.c.a().e(l.CENTER);
        this.g.hideStoringView();
    }

    private void u() {
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.S));
        dji.pilot.fpv.camera.a.a.a(d, "recordTime-" + a);
        if (a < 0) {
            a = 0;
        }
        this.g.updateVideoTime(d(a));
        if (a >= 1765) {
            DataSpecialControl.getInstance().setRecordType(false, 0, 0).start(20);
        }
    }

    private void a(CameraMode cameraMode, CameraMode cameraMode2, boolean z) {
        dji.pilot.fpv.camera.a.a.a(d, "CameraMode-" + cameraMode);
        if (CameraMode.RecordVideo == cameraMode) {
            this.o = cameraMode;
            this.g.showVideoView();
            if (CameraMode.Playback == cameraMode2) {
                v();
            }
        } else if (CameraMode.ShootPhoto == cameraMode) {
            this.o = cameraMode;
            this.g.showPhotoView();
            if (CameraMode.Playback == cameraMode2) {
                v();
            }
        } else if (CameraMode.Playback == cameraMode) {
            e(z);
        }
    }

    private void e(boolean z) {
        if (z) {
            this.i.postDelayed(this.aa, 700);
            return;
        }
        dji.pilot.fpv.control.b.p = true;
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.CAMERACTRL_ENTER_PB);
    }

    private void v() {
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.CAMERACTRL_EXIT_PB);
    }

    private void f(boolean z) {
        CameraMode cameraMode = (CameraMode) dji.sdksharedlib.a.a.a(this.Q);
        if (cameraMode != this.n) {
            a(cameraMode, this.n, z);
            this.n = cameraMode;
            dji.thirdparty.a.c.a().e(l.CENTER);
        }
    }

    private void w() {
        if (CameraType.OTHER == this.m) {
            this.m = (CameraType) dji.sdksharedlib.a.a.a(this.P);
        }
        if (!dji.pilot.fpv.d.b.j(this.m)) {
            int i;
            CameraExposureMode cameraExposureMode = (CameraExposureMode) dji.sdksharedlib.a.a.a(this.R);
            dji.pilot.fpv.camera.a.a.a(d, "exposuremode-" + cameraExposureMode);
            if (cameraExposureMode == null) {
                i = R.drawable.wm220_ic_cam_setting_nor;
            } else if (CameraExposureMode.Manual == cameraExposureMode) {
                i = R.drawable.wm220_ic_cam_setting_mmode;
            } else if (CameraExposureMode.ShutterPriority == cameraExposureMode) {
                i = R.drawable.wm220_ic_cam_setting_smode;
            } else if (CameraExposureMode.AperturePriority == cameraExposureMode) {
                i = R.drawable.wm220_ic_cam_setting_amode;
            } else if (CameraExposureMode.Program == cameraExposureMode) {
                i = R.drawable.wm220_ic_cam_setting_pmode;
            } else {
                i = CameraExposureMode.Cine == cameraExposureMode ? R.drawable.wm220_ic_cam_setting_smode : R.drawable.wm220_ic_cam_setting_nor;
            }
            this.g.updateSettingView(i, 0);
        }
    }

    private void a(c cVar, String str) {
        Boolean bool = (Boolean) dji.sdksharedlib.a.a.a(cVar);
        if (bool != null && bool.booleanValue()) {
            e.c(str);
        }
    }

    private void a(DataCameraGetPushStateInfo dataCameraGetPushStateInfo, boolean z) {
        SDCardState sDCardState = dataCameraGetPushStateInfo.getSDCardState(true);
        if (this.q != sDCardState) {
            this.q = sDCardState;
            d(false);
            q();
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        boolean z = true;
        int focusStatus = dataCameraGetPushShotInfo.getFocusStatus();
        if (this.r != focusStatus) {
            this.r = focusStatus;
            if (1 == focusStatus) {
                z = false;
            }
            if (z) {
                d(false);
                q();
                p();
                return;
            }
            this.g.setSwitchViewEnable(false);
            this.g.setPhotoViewEnable(false);
            this.g.setPIVViewEnable(false);
            this.g.setVideoViewEnable(false);
            this.g.setPlayBackViewEnable(false);
            this.g.setSettingViewEnable(false);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a(dataCameraGetPushStateInfo, false);
    }

    public void onEventMainThread(dji.midware.media.j.g.e eVar) {
        if (eVar == dji.midware.media.j.g.e.a) {
            DJIErrorPopView.b bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.b;
            bVar.b = R.string.videocache_nospace;
            dji.thirdparty.a.c.a().e(bVar);
        }
    }

    public void onEventMainThread(dji.pilot.newfpv.f.b bVar) {
        if (dji.pilot.newfpv.f.b.CAMERA_CONTROL_SHOW == bVar) {
            this.g.handleVisibilityEvent(true);
        } else if (dji.pilot.newfpv.f.b.CAMERA_CONTROL_HIDE == bVar) {
            this.g.handleVisibilityEvent(false);
        }
    }

    public void onEventMainThread(dji.midware.usb.P3.a.c cVar) {
        if (dji.pilot.publics.e.a.d(null)) {
            if (cVar == dji.midware.usb.P3.a.c.a) {
                this.g.setPlayBackViewEnable(false);
            } else if (cVar == dji.midware.usb.P3.a.c.b) {
                this.g.setPlayBackViewEnable(true);
            }
            this.m = (CameraType) dji.sdksharedlib.a.a.a(this.P);
            if (this.m != CameraType.DJICameraTypeFC350 && this.m != CameraType.DJICameraTypeFC550 && this.m != CameraType.DJICameraTypeFC550Raw && this.m != CameraType.DJICameraTypeCV600) {
                this.g.setPlayBackViewEnable(false);
            }
        }
    }

    public void onEventMainThread(a$a dji_pilot_fpv_camera_more_a_a) {
        if (dji_pilot_fpv_camera_more_a_a == a$a.PHOTOTYPE_CHANGED) {
            x();
        }
    }

    private void x() {
        int i = R.drawable.wm220_btn_cam_capture_nor;
        this.k = this.j.l();
        int n = this.j.n();
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.Z), false);
        if (!(TYPE.b == this.k || a)) {
            if (TYPE.c == this.k) {
                i = R.drawable.wm220_capture_hdr;
            } else if (TYPE.d == this.k) {
                i = R.drawable.wm220_capture_pano;
            } else if (TYPE.g == this.k || TYPE.a == this.k) {
                i = R.drawable.wm220_capture_time;
            } else if (TYPE.e == this.k) {
                i = 14 == n ? R.drawable.wm220_capture_mode_continuous_14 : 10 == n ? R.drawable.wm220_capture_mode_continuous_10 : 7 == n ? R.drawable.wm220_capture_mode_continuous_7 : 5 == n ? R.drawable.wm220_capture_mode_continuous_5 : R.drawable.wm220_capture_mode_continuous_3;
            } else if (TYPE.f == this.k) {
                i = 7 == n ? R.drawable.wm220_capture_aeb_continuous_7 : 5 == n ? R.drawable.wm220_capture_aeb_continuous_5 : R.drawable.wm220_capture_aeb_continuous_3;
            }
        }
        this.g.updatePhotoView(i, 0);
    }

    private String d(int i) {
        int[] e = dji.pilot.fpv.d.b.e(i);
        return String.format(Locale.US, "%1$02d:%2$02d", new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])});
    }

    private MODE a(CameraMode cameraMode) {
        if (CameraMode.RecordVideo == cameraMode) {
            return MODE.RECORD;
        }
        return MODE.TAKEPHOTO;
    }

    private boolean a(boolean z, SDCardState sDCardState, boolean z2) {
        boolean z3;
        if (SDCardState.Normal == sDCardState || SDCardState.ToFormat == sDCardState || SDCardState.Slow == sDCardState) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z4;
        if (SDCardState.Full == sDCardState) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z) {
            if (z3) {
                return true;
            }
            if (z2 && r3) {
                return true;
            }
        }
        return false;
    }

    private boolean y() {
        CameraType cameraType = this.m;
        return CameraType.DJICameraTypeFC300S == cameraType || CameraType.DJICameraTypeFC260 == cameraType || CameraType.DJICameraTypeFC300XW == cameraType || CameraType.DJICameraTypeFC220 == cameraType || CameraType.DJICameraTypeFC220S == cameraType;
    }

    private void z() {
        if (!y()) {
            return;
        }
        if (this.k == TYPE.c || this.k == TYPE.f || this.k == TYPE.e || this.k == TYPE.b) {
            DJIVideoDecoder e = ServiceManager.getInstance().e();
            if (e != null) {
                dji.pilot.fpv.camera.a.a.a(d, "fixDelayStream start");
                e.setConnectLosedelay(26000);
                this.i.removeMessages(4096);
                this.i.sendEmptyMessageDelayed(4096, 24000);
            }
        }
    }

    private void A() {
        if (!dji.pilot2.simulator.d.h() && dji.pilot.c.a.p && DJIGenSettingDataManager.getInstance().C()) {
            dji.midware.media.j.g.a(dji.pilot.c.a.q);
            dji.midware.media.j.g.a(DJIGenSettingDataManager.getInstance().E());
            dji.thirdparty.a.c.a().e(dji.midware.media.j.g.b.a);
        }
    }

    private void B() {
        if (!dji.pilot2.simulator.d.h() && dji.pilot.c.a.p && DJIGenSettingDataManager.getInstance().C()) {
            dji.thirdparty.a.c.a().e(dji.midware.media.j.g.b.b);
        }
    }

    public boolean isFinished() {
        return false;
    }

    public void f() {
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
            onEventMainThread(dji.midware.usb.P3.a.getInstance().b());
            if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                a(DataCameraGetPushStateInfo.getInstance(), true);
            }
            if (DataCameraGetPushShotInfo.getInstance().isGetted()) {
                onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            }
            dji.sdksharedlib.a.a.f(this, new String[]{i.o, i.p, i.u});
            dji.sdksharedlib.a.a.b(this, new String[]{dji.sdksharedlib.b.b.bX, dji.sdksharedlib.b.b.b, dji.sdksharedlib.b.b.m, dji.sdksharedlib.b.b.bg, dji.sdksharedlib.b.b.bb, dji.sdksharedlib.b.b.aZ, dji.sdksharedlib.b.b.bY, dji.sdksharedlib.b.b.bc, dji.sdksharedlib.b.b.bx, dji.sdksharedlib.b.d.ck, dji.sdksharedlib.b.b.ca});
            b(true);
            n();
        }
    }

    public void g() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.midware.media.j.g.b();
            dji.thirdparty.a.c.a().d(this);
            dji.sdksharedlib.a.a.a(this);
            if (this.n == CameraMode.ShootPhoto || this.n == CameraMode.RecordVideo) {
                dji.pilot.c.d.a = a(this.n);
            }
            DataSpecialControl.getInstance().stop();
            dji.midware.media.j.g.b();
        }
    }

    public void h() {
    }

    public void i() {
    }
}
