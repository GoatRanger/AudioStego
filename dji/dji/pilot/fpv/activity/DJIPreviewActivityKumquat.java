package dji.pilot.fpv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.R;
import dji.pilot.dji_groundstation.controller.e;
import dji.pilot.fpv.control.h;
import dji.pilot.fpv.model.n.a;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.newfpv.f.k;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.widget.FpvPopWarnView;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.f;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.c.d;
import dji.setting.ui.rc.Rc5DButtonView;
import dji.thirdparty.afinal.a.b.c;
import java.util.Timer;

public class DJIPreviewActivityKumquat extends DJIBaseNewPreviewActivity {
    private static final int aa = 0;
    private static final int ab = 270;
    private static final double af = 0.3d;
    private static final float ag = 2.0f;
    private static final float ah = 1.0f;
    private static final float ai = 0.1f;
    private static final int aj = 500;
    @c(a = 2131362922)
    protected RelativeLayout Y;
    private h ac;
    private CameraOrientation ad;
    private d ae = new 2(this);
    private Timer ak;
    private Timer al;
    private d am = new 3(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        H();
        K();
        L();
        I();
    }

    public void a() {
        super.a();
        View inflate = View.inflate(this, R.layout.fpv_preview_surface, null);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth, screenHeight);
        layoutParams.addRule(13, -1);
        inflate.setLayoutParams(layoutParams);
        ((DJIRelativeLayout) findViewById(R.id.a1v)).addView(inflate, 0);
        showG04RecommendDialog();
    }

    protected void onDestroy() {
        super.onDestroy();
        N();
        DJISDKCache.getInstance().stopListening(this.ae);
        J();
    }

    private void I() {
        this.ac = new h(this, new 1(this));
    }

    private void J() {
        if (this.ac != null) {
            this.ac.a();
            this.ac = null;
        }
    }

    protected void H() {
        this.m.setTutorialView(this.Y, this.e);
        if (b.getInstance().a(null) && dji.pilot.fpv.flightmode.c.getInstance().a() == dji.pilot.fpv.flightmode.c.b.e) {
            dji.thirdparty.a.c.a().e(a.LEFTMENU_JS_CLICK_START);
        }
    }

    public void onEventMainThread(ProductType productType) {
        switch (6.a[productType.ordinal()]) {
            case 1:
            case 2:
                return;
            default:
                finishThis();
                return;
        }
    }

    public void onEventMainThread(a aVar) {
        super.onEventMainThread(aVar);
        if (aVar == a.LEFTMENU_JS_CLICK_START) {
            this.m.show();
            this.m.showJoyStick();
            d(true);
            this.h.hideView();
            this.i.hideView();
            this.j.hideView();
            this.r.e();
            this.k.hide();
            this.l.setVisibility(0);
            this.r.a(true);
            this.l.setAlpha(1.0f);
        } else if (aVar == a.LEFTMENU_JS_CLICK_STOP) {
            this.m.hide();
            this.m.hideJoyStick();
            if (this.r.c()) {
                h();
                j();
                k();
                A();
            }
            this.r.d();
            this.k.show();
            this.l.setAlpha(dji.pilot.visual.a.d.c);
        }
    }

    protected void q() {
        super.q();
        if ((this.m.isJoystickViewEnable() && !this.g.isFocusKumquat()) || (b.getInstance().a(null) && !this.g.isFocusKumquat())) {
            this.i.hideView();
        }
    }

    private void K() {
        DJISDKCache.getInstance().startListeningForUpdates(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bW), this.ae, false);
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bW));
        if (availableValue != null && availableValue.e() != null) {
            dji.thirdparty.a.c.a().e((CameraOrientation) availableValue.e());
        }
    }

    private void L() {
        P();
        M();
        dji.sdksharedlib.a.a.f(this.am, i.aj, i.ak, i.am, i.an, i.al);
    }

    private void M() {
        DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(f.aa), Double.valueOf(af), null);
    }

    private void N() {
        DJISDKCache.getInstance().stopListening(this.am);
        O();
    }

    private void O() {
        if (this.ak != null) {
            this.ak.cancel();
            this.ak.purge();
            this.ak = null;
        }
        if (this.al != null) {
            this.al.cancel();
            this.al.purge();
            this.al = null;
        }
    }

    private void P() {
        int i = 0;
        if (dji.midware.util.i.b(getBaseContext(), Rc5DButtonView.f[0], -1) == -1) {
            while (i < Rc5DButtonView.f.length) {
                dji.midware.util.i.a(getBaseContext(), Rc5DButtonView.f[i], Rc5DButtonView.getDefaultSelectIndex(i));
                i++;
            }
            dji.midware.util.i.a(getBaseContext(), Rc5DButtonView.f[4], Rc5DButtonView.a.g.ordinal());
        }
    }

    private void a(String str) {
        int b = b(str);
        if (b == Rc5DButtonView.a.b.ordinal() && this.ak != null) {
            this.ak.cancel();
            this.ak.purge();
            this.ak = null;
        }
        if (b == Rc5DButtonView.a.c.ordinal() && this.al != null) {
            this.al.cancel();
            this.al.purge();
            this.al = null;
        }
    }

    private int b(String str) {
        int c = c(str);
        return dji.midware.util.i.b(getBaseContext(), Rc5DButtonView.f[c], c);
    }

    private int c(String str) {
        if (str.equals(i.aj)) {
            return 0;
        }
        if (str.equals(i.ak)) {
            return 1;
        }
        if (str.equals(i.am)) {
            return 2;
        }
        if (str.equals(i.an)) {
            return 3;
        }
        if (str.equals(i.al)) {
            return 4;
        }
        return -1;
    }

    private void c(int i) {
        if (i == Rc5DButtonView.a.a.ordinal()) {
            DataSpecialControl.getInstance().resetGimbal().start(20);
        } else if (i == Rc5DButtonView.a.b.ordinal()) {
            if (dji.pilot.fpv.d.b.r()) {
                if (this.ak == null) {
                    this.ak = new Timer();
                    this.ak.schedule(new 4(this), 0, 500);
                }
            } else if (dji.pilot.fpv.d.b.s()) {
                FpvPopWarnView.getInstance(this).pop(R.drawable.gs_warning_icon, R.string.unsupport_dzoom_4k, FpvPopWarnView.a.a);
            }
        } else if (i == Rc5DButtonView.a.c.ordinal()) {
            if (dji.pilot.fpv.d.b.r()) {
                if (this.al == null) {
                    this.al = new Timer();
                    this.al.schedule(new 5(this), 0, 500);
                }
            } else if (dji.pilot.fpv.d.b.s()) {
                FpvPopWarnView.getInstance(this).pop(R.drawable.gs_warning_icon, R.string.unsupport_dzoom_4k, FpvPopWarnView.a.a);
            }
        } else if (i == Rc5DButtonView.a.d.ordinal()) {
            Object obj;
            CameraOrientation cameraOrientation = this.ad;
            CameraOrientation cameraOrientation2 = CameraOrientation.Landscape;
            cameraOrientation = (CameraOrientation) dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.bW);
            if (cameraOrientation != null) {
                CameraOrientation cameraOrientation3 = this.ad;
                if (cameraOrientation.equals(CameraOrientation.Landscape)) {
                    cameraOrientation = this.ad;
                    obj = CameraOrientation.Portrait;
                    DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bW), obj, null);
                }
            }
            cameraOrientation = cameraOrientation2;
            DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bW), obj, null);
        } else if (i == Rc5DButtonView.a.e.ordinal()) {
            if (this.g.getSwitchMode().equals(DJISwitchModeView.a.b)) {
                this.g.switchMode(DJISwitchModeView.a.a);
            } else {
                this.g.switchMode(DJISwitchModeView.a.b);
            }
        } else if (i == Rc5DButtonView.a.f.ordinal()) {
            DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.x), Boolean.valueOf(!Boolean.valueOf(dji.sdksharedlib.a.a.b(dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.x))).booleanValue()), null);
        } else if (i == Rc5DButtonView.a.g.ordinal() && DataOsdGetPushCommon.getInstance().getModeChannel() != RcModeChannel.CHANNEL_S) {
            if (dji.pilot.dji_groundstation.controller.f.getInstance(getBaseContext()).c()) {
                e.a("gs://flightmode/dismiss", this);
            } else {
                e.a("gs://flightmode/main", this);
            }
        }
    }

    public void onEventMainThread(CameraOrientation cameraOrientation) {
        switch (6.b[cameraOrientation.ordinal()]) {
            case 1:
                f();
                return;
            case 2:
                f();
                return;
            default:
                return;
        }
    }

    private boolean Q() {
        if (this.ad == null) {
            this.ad = (CameraOrientation) dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.bW);
        }
        if (this.ad != null) {
            CameraOrientation cameraOrientation = this.ad;
            CameraOrientation cameraOrientation2 = this.ad;
            if (cameraOrientation == CameraOrientation.Portrait) {
                return true;
            }
        }
        return false;
    }

    public void onEventMainThread(k kVar) {
        DJILogHelper.getInstance().LOGE("KUMQUAT_FOCUS", "onEventMainThread KumquatStartFocus=" + kVar);
        if (kVar == k.c) {
            this.i.hideView();
            if (this.m.isJoystickViewEnable()) {
                this.m.show();
                this.l.setAlpha(1.0f);
            }
        } else if (kVar == k.a) {
            this.i.showView();
            this.m.hide();
            this.l.setAlpha(dji.pilot.visual.a.d.c);
        }
    }
}
