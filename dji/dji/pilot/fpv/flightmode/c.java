package dji.pilot.fpv.flightmode;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.R;
import dji.pilot.dji_groundstation.controller.g;
import dji.pilot.fpv.model.n.a;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.visual.a.g.d;
import dji.pilot.visual.a.g.e;
import dji.pilot.visual.a.g.f;
import java.util.Arrays;

public class c implements g {
    private static final String i = c.class.getSimpleName();
    private static final String k = "key_disclaimer_smart";
    private static final String l = "key_disclaimer_smode";
    private Handler j;
    private b m;
    private d n;
    private final a[] o;
    private int p;
    private RcModeChannel q;
    private boolean r;
    private FLYC_STATE s;
    private String t;
    private Context u;
    private boolean v;
    private boolean w;

    public static c getInstance() {
        return c.a();
    }

    public b a() {
        return this.m;
    }

    public boolean b() {
        return b.c == this.m || b.d == this.m;
    }

    public void a(b bVar) {
        a(bVar, false);
    }

    private void a(b bVar, boolean z) {
        if (bVar != this.m) {
            this.m = bVar;
            dji.thirdparty.a.c.a().e(bVar);
            if (bVar == b.f || bVar == b.e || bVar == b.g) {
                if ((b() || this.m == b.b) && z) {
                    dji.pilot.visual.a.c.getInstance().g();
                    dji.thirdparty.a.c.a().e(d.b);
                }
            } else if (bVar == b.c) {
                if (z) {
                    dji.pilot.visual.a.c.getInstance().a(f.b);
                    dji.pilot.visual.a.c.getInstance().a(e.b);
                }
            } else if (bVar == b.b) {
                if (z) {
                    dji.pilot.visual.a.c.getInstance().a(f.b);
                    dji.pilot.visual.a.c.getInstance().a(e.c);
                }
            } else if (bVar == b.d && z) {
                dji.pilot.visual.a.c.getInstance().a(f.b);
                dji.pilot.visual.a.c.getInstance().a(e.b);
            }
            if (!b.getInstance().a(null)) {
                return;
            }
            if (bVar == b.e) {
                dji.thirdparty.a.c.a().e(a.LEFTMENU_JS_CLICK_START);
            } else {
                dji.thirdparty.a.c.a().e(a.LEFTMENU_JS_CLICK_STOP);
            }
        }
    }

    public d c() {
        return this.n;
    }

    public void a(d dVar) {
    }

    public boolean b(b bVar) {
        if (bVar == b.f) {
            return this.v;
        }
        return false;
    }

    public boolean d() {
        return this.w;
    }

    public void c(b bVar) {
        if (bVar == b.f) {
            this.v = false;
            dji.pilot.publics.objects.g.a(this.u, k + this.t, false);
        }
    }

    public void e() {
        this.w = false;
        dji.pilot.publics.objects.g.a(this.u, l + this.t, false);
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.ConnectLose) {
            this.p = 0;
            this.q = RcModeChannel.CHANNEL_UNKNOWN;
            this.r = false;
            this.s = FLYC_STATE.OTHER;
            a(b.e, true);
        } else if (pVar == p.ConnectOK) {
            a(b.e, true);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar != o.ConnectLose) {
        }
    }

    public void onEventMainThread(dji.setting.ui.rc.RcMasterSlaveView.c cVar) {
        if (cVar != null) {
            a(cVar.a, this.p, this.r, this.q);
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (this.p != flycVersion) {
            this.p = flycVersion;
            a(dji.pilot.c.d.b, this.p, this.r, this.q);
        }
        if (dji.pilot.fpv.d.b.j()) {
            RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
            if (this.q != modeChannel) {
                a(dji.pilot.c.d.b, this.p, this.r, modeChannel);
            }
            FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
            if (flycState != this.s) {
                this.s = flycState;
                h();
            }
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if (dji.pilot.fpv.d.b.j()) {
            boolean a = dji.pilot.fpv.d.b.a(dataOsdGetPushHome.isBeginnerMode(), dataOsdGetPushHome.isMultipleModeOpen());
            if (this.r != a) {
                a(dji.pilot.c.d.b, this.p, a, this.q);
                h();
            }
        }
    }

    private void a(MODE mode, int i, boolean z, RcModeChannel rcModeChannel) {
        this.q = rcModeChannel;
        this.r = z;
        this.p = i;
        if ((mode != null && mode == MODE.Slave) || dji.pilot.fpv.d.b.a(i.getInstance().c(), z, rcModeChannel)) {
            a(b.e, true);
        }
    }

    private boolean f() {
        return (dji.pilot.c.d.b != null && dji.pilot.c.d.b == MODE.Slave) || dji.pilot.fpv.d.b.a(i.getInstance().c(), this.r, this.q);
    }

    private a[] g() {
        FLYC_STATE flyc_state = this.s;
        a[] aVarArr = this.o;
        aVarArr[0] = b.a;
        aVarArr[1] = d.a;
        if (FLYC_STATE.TRACK_HEADLOCK == flyc_state) {
            aVarArr[0] = b.c;
        } else if (FLYC_STATE.NaviSubMode_Tracking == flyc_state) {
            if (DataEyeGetPushException.getInstance().isMovingObjectDetectEnable()) {
                aVarArr[0] = b.d;
            } else {
                aVarArr[0] = b.c;
            }
        } else if (FLYC_STATE.NaviSubMode_Pointing == flyc_state) {
            aVarArr[0] = b.b;
        } else if (FLYC_STATE.TRIPOD_GPS != flyc_state) {
            if (FLYC_STATE.GPS_CL == flyc_state) {
                aVarArr[0] = b.f;
                aVarArr[1] = d.e;
            } else if (FLYC_STATE.GPS_HomeLock == flyc_state) {
                aVarArr[0] = b.f;
                aVarArr[1] = d.f;
            } else if (FLYC_STATE.GPS_HotPoint == flyc_state) {
                aVarArr[0] = b.f;
                aVarArr[1] = d.b;
            } else if (FLYC_STATE.NaviGo == flyc_state) {
                aVarArr[0] = b.f;
                aVarArr[1] = d.c;
            } else if (FLYC_STATE.NaviMissionFollow == flyc_state) {
                aVarArr[0] = b.f;
                aVarArr[1] = d.d;
            } else if (FLYC_STATE.TERRAIN_TRACKING == flyc_state) {
                aVarArr[0] = b.f;
                aVarArr[1] = d.h;
            }
        }
        if (f()) {
            aVarArr[0] = b.e;
            aVarArr[1] = d.a;
        }
        DJILogHelper.getInstance().LOGD(i, "CheckMode-" + this.s + com.alipay.sdk.j.i.b + Arrays.toString(aVarArr), false, true);
        return aVarArr;
    }

    private void h() {
        a[] g = g();
        b bVar = (b) g[0];
        d dVar = (d) g[1];
        if (b.a != bVar) {
            if (bVar != this.m) {
                a(bVar, true);
            } else if (b.f == bVar && this.n != dVar) {
            }
        }
    }

    public void onEventMainThread(d dVar) {
        if (d.c != dVar) {
        }
    }

    public void onEventMainThread(f fVar) {
    }

    private d a(dji.pilot.groundStation.a.a.d dVar) {
        d dVar2 = d.a;
        if (dVar == dji.pilot.groundStation.a.a.d.e) {
            return d.b;
        }
        if (dVar == dji.pilot.groundStation.a.a.d.f) {
            return d.c;
        }
        if (dVar == dji.pilot.groundStation.a.a.d.d) {
            return d.d;
        }
        if (dVar == dji.pilot.groundStation.a.a.d.b) {
            return d.e;
        }
        if (dVar == dji.pilot.groundStation.a.a.d.c) {
            return d.f;
        }
        if (dVar == dji.pilot.groundStation.a.a.d.h) {
            return d.h;
        }
        return dVar2;
    }

    public void onEventMainThread(dji.pilot.groundStation.a.a.d dVar) {
        if (!dji.pilot.fpv.d.b.j()) {
            return;
        }
        if (dVar != dji.pilot.groundStation.a.a.d.a) {
            a(b.f);
            a(a(dVar));
        } else if (this.m == b.f) {
            a(d.a);
        }
    }

    private void i() {
        dji.pilot.dji_groundstation.a.d.a a = dji.pilot.dji_groundstation.controller.d.getInstance().a();
        dji.pilot.dji_groundstation.a.d.c b = dji.pilot.dji_groundstation.controller.d.getInstance().b();
        if (a.a() != dji.pilot.dji_groundstation.a.d.a.f.a()) {
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 16;
            switch (2.a[a.ordinal()]) {
                case 1:
                    eVar.t = new Integer(R.drawable.mini_point);
                    break;
                case 2:
                    eVar.t = new Integer(R.drawable.mini_track);
                    break;
                case 3:
                    eVar.t = new Integer(R.drawable.mini_normal);
                    break;
                case 4:
                    eVar.t = new Integer(R.drawable.mini_selfie);
                    break;
            }
            dji.thirdparty.a.c.a().e(eVar);
            return;
        }
        dji.pilot.dji_groundstation.a.e eVar2 = new dji.pilot.dji_groundstation.a.e();
        eVar2.s = 16;
        if (b.a(dji.pilot.dji_groundstation.a.d.c.CourseLock)) {
            eVar2.t = new Integer(R.drawable.mini_course_lock);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.HomeLock)) {
            eVar2.t = new Integer(R.drawable.mini_home_lock);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.FollowMe)) {
            eVar2.t = new Integer(R.drawable.mini_followme);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.PointOfInterest)) {
            eVar2.t = new Integer(R.drawable.mini_point_of_interest);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.WayPoint)) {
            eVar2.t = new Integer(R.drawable.mini_waypoint);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.TerrainTracking)) {
            eVar2.t = new Integer(R.drawable.mini_terrain_tracking);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.Tripod)) {
            eVar2.t = new Integer(R.drawable.mini_tripod);
        } else {
            eVar2.t = new Integer(R.drawable.mini_normal);
        }
        dji.thirdparty.a.c.a().e(eVar2);
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar == p.ConnectOK) {
            i();
        }
    }

    private c() {
        this.m = b.e;
        this.n = d.a;
        this.o = new a[2];
        this.p = 0;
        this.q = RcModeChannel.CHANNEL_UNKNOWN;
        this.r = false;
        this.s = FLYC_STATE.OTHER;
        this.t = "";
        this.u = null;
        this.v = true;
        this.w = true;
        this.u = DJIApplication.a();
        this.j = new Handler(Looper.getMainLooper());
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        dji.pilot.dji_groundstation.controller.d.getInstance().a((g) this);
        this.t = dji.pilot.publics.e.d.a(this.u);
        this.v = dji.pilot.publics.objects.g.b(this.u, k + this.t, this.v);
        this.w = dji.pilot.publics.objects.g.b(this.u, l + this.t, this.w);
    }

    private void a(int i) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.a = DJIErrorPopView.d.WARNING;
        bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.AUTODISAPPEAR;
        bVar.b = i;
        dji.thirdparty.a.c.a().e(bVar);
    }

    private void a(String str) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.a = DJIErrorPopView.d.WARNING;
        bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.AUTODISAPPEAR;
        bVar.c = str;
        dji.thirdparty.a.c.a().e(bVar);
    }

    public void a(dji.pilot.dji_groundstation.a.d.a aVar) {
        if (b() || b.b == this.m) {
            dji.pilot.visual.a.c.getInstance().g();
        }
    }

    public boolean b(dji.pilot.dji_groundstation.a.d.a aVar, int i) {
        b bVar = (b) g()[0];
        if (bVar == b(aVar) || bVar == b.a) {
            return true;
        }
        return false;
    }

    public void a(dji.pilot.dji_groundstation.a.d.a aVar, int i) {
        b b = b(aVar);
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 16;
        if (b.ordinal() == b.c.ordinal() || b.ordinal() == b.d.ordinal() || b.ordinal() == b.b.ordinal()) {
            CameraOrientation cameraOrientation = (CameraOrientation) dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.bW);
            if (cameraOrientation != null && cameraOrientation.equals(CameraOrientation.Portrait)) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.a = DJIErrorPopView.d.WARNING;
                bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.AUTODISAPPEAR;
                bVar.b = R.string.gs_hint_no_mission_in_rotation_mode;
                dji.thirdparty.a.c.a().e(bVar);
                dji.pilot.dji_groundstation.controller.d.getInstance().a(dji.pilot.dji_groundstation.a.d.a.e);
                return;
            }
        }
        if (b.ordinal() == b.e.ordinal()) {
            if (i == 0) {
                a(b);
                dji.thirdparty.a.c.a().e(d.b);
                eVar.t = new Integer(R.drawable.mini_normal);
                dji.thirdparty.a.c.a().e(eVar);
                return;
            }
            a((int) R.string.fpv_flight_mode_switch_failed);
        } else if (b.ordinal() == b.b.ordinal()) {
            if (i != 0) {
                a((int) R.string.fpv_flight_mode_switch_failed);
            } else if (DataOsdGetPushCommon.getInstance().isMotorUp() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                a(b);
                dji.pilot.visual.a.c.getInstance().a(f.b);
                dji.pilot.visual.a.c.getInstance().a(e.c);
                dji.thirdparty.a.c.a().e(d.a);
                eVar.t = new Integer(R.drawable.mini_point);
                dji.thirdparty.a.c.a().e(eVar);
            } else {
                a((int) R.string.gs_not_take_off_warning_title);
            }
        } else if (b.ordinal() == b.c.ordinal()) {
            if (i != 0) {
                a((int) R.string.fpv_flight_mode_switch_failed);
            } else if (DataOsdGetPushCommon.getInstance().isMotorUp() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                a(b);
                dji.pilot.visual.a.c.getInstance().a(f.b);
                dji.pilot.visual.a.c.getInstance().a(e.b);
                dji.thirdparty.a.c.a().e(d.a);
                eVar.t = new Integer(R.drawable.mini_track);
                dji.thirdparty.a.c.a().e(eVar);
            } else {
                a((int) R.string.gs_not_take_off_warning_title);
            }
        } else if (b.ordinal() == b.d.ordinal()) {
            if (i == 0) {
                this.j.post(new 1(this, b, eVar));
            } else {
                a((int) R.string.fpv_flight_mode_switch_failed);
            }
        } else if (b.ordinal() != b.f.ordinal()) {
        } else {
            if (i == 0) {
                a(b);
                dji.pilot.visual.a.c.getInstance().g();
                dji.thirdparty.a.c.a().e(d.b);
            } else if (i == 1) {
                a((int) R.string.fpv_flight_mode_novice_tip);
            } else if (i == 2) {
                a((int) R.string.gs_not_take_off_warning_title);
            } else {
                a((int) R.string.fpv_flight_mode_switch_failed);
            }
        }
    }

    public void a(dji.pilot.dji_groundstation.a.d.c cVar, int i) {
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 16;
        if (cVar.a(dji.pilot.dji_groundstation.a.d.c.CourseLock)) {
            eVar.t = new Integer(R.drawable.mini_course_lock);
        } else if (cVar.a(dji.pilot.dji_groundstation.a.d.c.HomeLock)) {
            eVar.t = new Integer(R.drawable.mini_home_lock);
        } else if (cVar.a(dji.pilot.dji_groundstation.a.d.c.FollowMe)) {
            eVar.t = new Integer(R.drawable.mini_followme);
        } else if (cVar.a(dji.pilot.dji_groundstation.a.d.c.PointOfInterest)) {
            eVar.t = new Integer(R.drawable.mini_point_of_interest);
        } else if (cVar.a(dji.pilot.dji_groundstation.a.d.c.WayPoint)) {
            eVar.t = new Integer(R.drawable.mini_waypoint);
        } else if (cVar.a(dji.pilot.dji_groundstation.a.d.c.TerrainTracking)) {
            eVar.t = new Integer(R.drawable.mini_terrain_tracking);
        } else if (cVar.a(dji.pilot.dji_groundstation.a.d.c.Tripod)) {
            eVar.t = new Integer(R.drawable.mini_tripod);
        } else {
            eVar.t = new Integer(R.drawable.mini_normal);
        }
        dji.thirdparty.a.c.a().e(eVar);
        dji.pilot.visual.a.c.getInstance().g();
        dji.thirdparty.a.c.a().e(d.b);
        if (cVar.a(dji.pilot.dji_groundstation.a.d.c.Tripod)) {
            dji.thirdparty.a.c.a().e(e.a);
        } else {
            dji.thirdparty.a.c.a().e(e.b);
        }
    }

    public void a(int i, Object obj) {
        switch (i) {
            case 3:
                a((int) R.string.gs_get_limit_height_failed);
                return;
            case 4:
                a((int) R.string.gs_error_go_home_height_heigher_than_limit_height);
                return;
            case 5:
                a((int) R.string.gs_return_to_home_attitude_invalidate_parameter);
                return;
            case 6:
                if (obj != null && (obj instanceof dji.midware.data.config.P3.a)) {
                    a(((dji.midware.data.config.P3.a) obj).toString());
                    return;
                }
                return;
            case 7:
            case 8:
                if (obj != null && (obj instanceof String)) {
                    a((String) obj);
                    return;
                } else if (obj != null && (obj instanceof Integer)) {
                    a(((Integer) obj).intValue());
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private dji.pilot.dji_groundstation.a.d.a d(b bVar) {
        switch (2.b[bVar.ordinal()]) {
            case 1:
                return dji.pilot.dji_groundstation.a.d.a.a;
            case 2:
                return dji.pilot.dji_groundstation.a.d.a.b;
            case 3:
                return dji.pilot.dji_groundstation.a.d.a.c;
            case 4:
                return dji.pilot.dji_groundstation.a.d.a.h;
            case 5:
                return dji.pilot.dji_groundstation.a.d.a.e;
            case 6:
                return dji.pilot.dji_groundstation.a.d.a.f;
            case 7:
                return dji.pilot.dji_groundstation.a.d.a.g;
            default:
                return dji.pilot.dji_groundstation.a.d.a.e;
        }
    }

    private dji.pilot.dji_groundstation.a.d.c b(d dVar) {
        switch (2.c[dVar.ordinal()]) {
            case 1:
                return dji.pilot.dji_groundstation.a.d.c.None;
            case 2:
                return dji.pilot.dji_groundstation.a.d.c.PointOfInterest;
            case 3:
                return dji.pilot.dji_groundstation.a.d.c.WayPoint;
            case 4:
                return dji.pilot.dji_groundstation.a.d.c.FollowMe;
            case 5:
                return dji.pilot.dji_groundstation.a.d.c.CourseLock;
            case 6:
                return dji.pilot.dji_groundstation.a.d.c.HomeLock;
            case 7:
                return dji.pilot.dji_groundstation.a.d.c.Pano;
            case 8:
                return dji.pilot.dji_groundstation.a.d.c.TerrainTracking;
            default:
                return dji.pilot.dji_groundstation.a.d.c.None;
        }
    }

    private b b(dji.pilot.dji_groundstation.a.d.a aVar) {
        if (aVar.a() == dji.pilot.dji_groundstation.a.d.a.g.a()) {
            return b.g;
        }
        if (aVar.a() == dji.pilot.dji_groundstation.a.d.a.a.a()) {
            return b.a;
        }
        if (aVar.a() == dji.pilot.dji_groundstation.a.d.a.e.a()) {
            return b.e;
        }
        if (aVar.a() == dji.pilot.dji_groundstation.a.d.a.b.a()) {
            return b.b;
        }
        if (aVar.a() == dji.pilot.dji_groundstation.a.d.a.f.a()) {
            return b.f;
        }
        if (aVar.a() == dji.pilot.dji_groundstation.a.d.a.c.a()) {
            return b.c;
        }
        if (aVar.a() == dji.pilot.dji_groundstation.a.d.a.d.a()) {
            return b.d;
        }
        return b.a;
    }

    private d a(dji.pilot.dji_groundstation.a.d.c cVar) {
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.CourseLock.a()) {
            return d.e;
        }
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.FollowMe.a()) {
            return d.d;
        }
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.HomeLock.a()) {
            return d.f;
        }
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.None.a()) {
            return d.a;
        }
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.Pano.a()) {
            return d.g;
        }
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.PointOfInterest.a()) {
            return d.b;
        }
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.WayPoint.a()) {
            return d.c;
        }
        if (cVar.a() == dji.pilot.dji_groundstation.a.d.c.TerrainTracking.a()) {
            return d.h;
        }
        return d.a;
    }

    private Context j() {
        dji.pilot.dji_groundstation.controller.a instance = dji.pilot.dji_groundstation.controller.a.getInstance(this.u);
        if (instance != null) {
            return instance.a();
        }
        return null;
    }
}
