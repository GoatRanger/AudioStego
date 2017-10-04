package dji.pilot.dji_groundstation.controller;

import android.os.Bundle;
import android.util.Log;
import dji.common.util.LocationUtils;
import dji.gs.e.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycNavigationSwitch.GS_COMMAND;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.data.model.P3.DataFlycStartIoc.IOCType;
import dji.midware.data.model.P3.DataFlycStopIoc;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.ACTION_ON_RC_LOST;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.GIMBAL_PITCH_MODE;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.TRACE_MODE;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex.ACTION;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex.TURNMODE;
import dji.midware.data.model.P3.DataFlycWayPointMissionSwitch;
import dji.midware.data.model.P3.DataFlycWayPointMissionSwitch.CMD;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.a;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem.WayPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.Semaphore;

public class d {
    private static final String a = "GSModeController";
    private static d b = null;
    private g c = null;
    private final int d = 2;
    private a e = a.e;
    private a f = a.e;
    private Stack<c> g = new Stack();
    private ArrayList<h> h = new ArrayList();
    private int i = 0;
    private g j = null;
    private boolean k = false;
    private double l = 0.0d;
    private double m = 0.0d;

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d();
            }
            dVar = b;
        }
        return dVar;
    }

    private d() {
    }

    public void a(h hVar) {
        if (this.h != null && !this.h.contains(hVar)) {
            this.h.add(hVar);
        }
    }

    public void a(g gVar) {
        if (gVar != null) {
            this.c = gVar;
        }
    }

    public a a() {
        return this.f;
    }

    public void a(a aVar) {
        this.e = this.f;
        this.f = aVar;
        if (this.c != null) {
            this.c.a(aVar, 0);
        }
    }

    public c b() {
        if (this.g == null || this.g.isEmpty()) {
            return c.None;
        }
        return (c) this.g.peek();
    }

    public void c() {
        if (this.g != null) {
            this.g.clear();
        }
    }

    public void d() {
        if (this.g != null && !this.g.isEmpty()) {
            this.g.pop();
            if (this.g.isEmpty()) {
                e eVar = new e();
                eVar.s = 1;
                dji.thirdparty.a.c.a().e(eVar);
                a(a.e);
            }
        }
    }

    public int e() {
        if (this.g == null || this.g.isEmpty()) {
            return 0;
        }
        return this.g.size();
    }

    public void a(c cVar) {
        if (this.g == null) {
            this.g = new Stack();
        }
        if (b().a() != cVar.a()) {
            this.g.push(cVar);
        }
        if (cVar != c.None) {
            a(a.f);
        }
        if (cVar.a() == c.WayPoint_Status.a()) {
            dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().g(dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j());
        }
        if (this.c != null) {
            this.c.a(cVar, 0);
        }
    }

    public void b(a aVar) {
        if (this.c != null) {
            this.c.a(aVar);
        }
    }

    public boolean a(a aVar, int i) {
        if (this.c != null) {
            return this.c.b(aVar, i);
        }
        return true;
    }

    public void b(a aVar, int i) {
        if (this.c != null) {
            this.c.a(aVar, i);
        }
    }

    public void a(c cVar, int i) {
        if (this.c != null) {
            this.c.a(cVar, i);
        }
    }

    public void c(a aVar) {
        boolean z = true;
        this.e = this.f;
        Log.i(getClass().getSimpleName(), "into switchFlightMode: " + aVar);
        this.f = aVar;
        if (aVar == a.f) {
            if (DataOsdGetPushCommon.getInstance().getFlycState() == FLYC_STATE.NOVICE) {
                b(aVar, 1);
            } else if (DataOsdGetPushCommon.getInstance().isMotorUp() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                b(this.e);
                if (aVar.a() != a.f.a()) {
                    z = false;
                }
                a(z, 2, new 1(this, aVar));
            } else {
                b(aVar, 2);
                a(g.g, null);
            }
        } else if (DataOsdGetPushCommon.getInstance().getVoltageWarning() == 2) {
            a(8, new Integer(R.string.gsnew_battery_low_warning));
        } else {
            b(this.e);
            c();
            a(false, 2, new 12(this, aVar));
            d(aVar);
            e eVar = new e();
            eVar.s = 9;
            eVar.t = null;
            dji.thirdparty.a.c.a().e(eVar);
        }
    }

    private void d(a aVar) {
        switch (11.a[aVar.ordinal()]) {
            case 1:
                a(g.a, null);
                return;
            case 2:
                a(g.b, null);
                return;
            case 3:
                a(g.d, null);
                return;
            case 4:
                a(g.e, null);
                return;
            case 5:
                a(g.c, null);
                return;
            case 6:
                a(g.f, null);
                return;
            default:
                return;
        }
    }

    private void c(c cVar) {
        switch (11.b[cVar.ordinal()]) {
            case 2:
                this.j = g.h;
                break;
            case 3:
                this.j = g.j;
                break;
            case 4:
                this.j = g.q;
                break;
            case 5:
                this.j = g.r;
                break;
            case 6:
                this.j = g.s;
                break;
            case 7:
                this.j = g.t;
                break;
            case 8:
                this.j = g.u;
                break;
            case 9:
                this.j = g.v;
                break;
            case 10:
                this.j = g.x;
                break;
            case 11:
                this.j = g.C;
                break;
            case 12:
                this.j = g.n;
                break;
            case 13:
                this.j = g.F;
                break;
            case 14:
                this.j = g.G;
                break;
            case 15:
                this.j = g.H;
                break;
            case 16:
                this.j = g.I;
                break;
            case 17:
                this.j = g.J;
                break;
            case 18:
                this.j = g.K;
                break;
            case 20:
                this.j = g.L;
                break;
        }
        a(this.j, null);
    }

    private boolean d(c cVar) {
        if (this.f.a() != a.f.a()) {
            this.e = this.f;
            Log.i(getClass().getSimpleName(), "into isGoingToSwitchANewSmartMode: " + cVar);
            this.f = a.f;
            return true;
        } else if (cVar.a() == b().a()) {
            return false;
        } else {
            if (b().a(cVar)) {
                return false;
            }
            a(cVar);
            return true;
        }
    }

    public void b(c cVar) {
        if (d(cVar)) {
            c();
            if (getInstance().b().a() == c.None.a() && i.getInstance().c().value() == ProductType.A2.value()) {
                a(cVar);
                if (this.c != null) {
                    this.c.a(cVar, 0);
                }
                c(cVar);
                return;
            }
            a(false, 2, new 13(this, cVar));
            return;
        }
        a(cVar);
        if (this.c != null) {
            this.c.a(cVar, 0);
        }
        c(cVar);
    }

    public void a(g gVar) {
        if (this.f.a() == a.f.a()) {
            this.j = gVar;
            switch (11.c[gVar.ordinal()]) {
                case 1:
                    f();
                    break;
                case 2:
                    g();
                    break;
                case 5:
                    i();
                    break;
                case 6:
                    j();
                    break;
                case 7:
                    k();
                    break;
                case 8:
                    l();
                    break;
                case 9:
                    o();
                    break;
                case 10:
                    n();
                    break;
                case 11:
                    p();
                    break;
                case 12:
                    q();
                    break;
                case 13:
                    r();
                    break;
                case 14:
                    s();
                    break;
                case 15:
                    u();
                    break;
                case 16:
                    t();
                    break;
            }
            if (this.c != null) {
                this.c.a(b(), 0);
            }
        }
    }

    public void a(g gVar, Object obj) {
        if (this.h != null) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                h hVar = (h) it.next();
                if (hVar != null) {
                    hVar.a(gVar, obj);
                }
            }
        }
    }

    public void a(int i, Object obj) {
        if (this.c != null) {
            if (i == 4) {
                a(8, Integer.valueOf(R.string.gsnew_gs_error_go_home_height_heigher_than_limit_height));
            } else {
                this.c.a(i, obj);
            }
        }
    }

    public void a(boolean z, int i, dji.midware.e.d dVar) {
        if (dVar != null) {
            if (i.getInstance().c().value() != ProductType.A2.value() || z) {
                c b = b();
                if (z || !(b == c.HomeLock || b == c.HomeLock_Status || b == c.CourseLock || b == c.CourseLock_Status || b == c.Tripod)) {
                    new DataFlycNavigationSwitch().setCommand(z ? GS_COMMAND.OPEN_GROUND_STATION : GS_COMMAND.CLOSE_GROUND_STATION).setRetryTimes(i).start(dVar);
                    return;
                } else {
                    DataFlycStopIoc.getInstance().start(new 14(this, z, i, dVar));
                    return;
                }
            }
            DataFlycStopIoc.getInstance().start(dVar);
        }
    }

    private void f() {
        if (f.a() > 5.0d) {
            dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().a(f.b());
            dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().b(f.c());
            e eVar = new e();
            eVar.s = 21;
            eVar.t = dji.gs.utils.a.a(new b(dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().i(), dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().j()));
            dji.thirdparty.a.c.a().e(eVar);
            a(c.PointOfInsterst_Start);
            a(g.i, null);
            return;
        }
        Object bundle = new Bundle();
        bundle.putInt("contentid", R.string.gsnew_gs_point_of_insterest_height_limits);
        a(g.m, bundle);
    }

    private void g() {
        if (b() != c.PointOfInsterst_Status && dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().h()) {
            a(true, 2, new 15(this));
        }
    }

    private void h() {
        ROTATION_DIR rotation_dir = ROTATION_DIR.Anti_Clockwise;
        dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().b(1.0f);
        rotation_dir = dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().k() > 0.0f ? ROTATION_DIR.Anti_Clockwise : ROTATION_DIR.Clockwise;
        DataFlycNavigationSwitch.getInstance().setCommand(GS_COMMAND.OPEN_GROUND_STATION);
        DataFlycNavigationSwitch.getInstance().start(new 16(this, rotation_dir));
    }

    private void a(int i, int i2, String str, String str2) {
        Object aVar = new dji.pilot.dji_groundstation.a.a();
        aVar.a = i;
        aVar.b = i2;
        aVar.c = str;
        aVar.d = 250;
        aVar.e = 270;
        aVar.k = false;
        aVar.j = R.string.gsnew_gs_failed_dialog_ok;
        aVar.i = str2;
        a(g.M, aVar);
    }

    private void i() {
        dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().l();
    }

    private void j() {
        DataFlycWayPointMissionSwitch.getInstance().setCMD(CMD.START);
        DataFlycWayPointMissionSwitch.getInstance().start(new 17(this));
    }

    private void k() {
        a(c.WayPoint_UploadMission);
        a(g.v, null);
        a(true, 2, new 18(this));
    }

    private void l() {
        this.k = true;
    }

    private void a(int i, String str) {
        Object aVar = new dji.pilot.dji_groundstation.a.a();
        aVar.a = R.string.gsnew_gs_way_point_send_command_failed;
        aVar.c = str;
        aVar.b = i;
        aVar.d = 250;
        aVar.e = 270;
        aVar.k = false;
        aVar.h = R.string.gsnew_gs_wait_dialog_cancel;
        aVar.g = "gs://smartmode/back";
        aVar.j = R.string.gsnew_gs_way_point_upload_failed_retry;
        aVar.i = "gs://smartmode/back";
        if (b().a() != c.WayPoint_UploadMission.a()) {
            aVar.g = "gs://smartmode/waypoint/setreturnhomeheight";
            aVar.i = "gs://smartmode/waypoint/setreturnhomeheight";
        }
        a(g.M, aVar);
    }

    private void m() {
        this.k = false;
        DJIWPCollectionItem j = dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j();
        DataFlycUploadWayPointMissionMsg.getInstance().setWayPointCount(j.getPoints().size());
        DataFlycUploadWayPointMissionMsg.getInstance().setCmdSpeed(10.0f);
        DataFlycUploadWayPointMissionMsg.getInstance().setIdleSpeed(dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().o());
        DataFlycUploadWayPointMissionMsg.getInstance().setFinishAction(dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().n());
        DataFlycUploadWayPointMissionMsg.getInstance().setYawMode(dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().m());
        DataFlycUploadWayPointMissionMsg.getInstance().setTraceMOde(TRACE_MODE.SMOOTH_PATH);
        DataFlycUploadWayPointMissionMsg.getInstance().setActionOnRCLost(ACTION_ON_RC_LOST.CONTINUE_WP);
        DataFlycUploadWayPointMissionMsg.getInstance().seGimbalPitchMode(GIMBAL_PITCH_MODE.PITCH_FREE);
        DataFlycUploadWayPointMissionMsg.getInstance().setRepeatNum(1);
        DataFlycUploadWayPointMissionMsg.getInstance().start(new 19(this, j));
    }

    private void a(WayPoint wayPoint, int i, boolean z) {
        if (wayPoint != null) {
            DataFlycUploadWayPointMsgByIndex.getInstance().setIndex(i);
            DataFlycUploadWayPointMsgByIndex.getInstance().setLatitude(LocationUtils.DegreeToRadian(wayPoint.getLat()));
            DataFlycUploadWayPointMsgByIndex.getInstance().setLongtitude(LocationUtils.DegreeToRadian(wayPoint.getLng()));
            DataFlycUploadWayPointMsgByIndex.getInstance().setAltitude((float) wayPoint.getHeight());
            DataFlycUploadWayPointMsgByIndex.getInstance().setDampingDis((float) dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().e(i));
            DataFlycUploadWayPointMsgByIndex.getInstance().setTgtYaw((short) wayPoint.getCraftYaw());
            DJIWPCollectionItem j = dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j();
            if (z) {
                DataFlycUploadWayPointMsgByIndex.getInstance().setTurnMode(TURNMODE.CLOCKWISE);
            } else {
                int craftYaw = wayPoint.getCraftYaw() - ((WayPoint) j.getPoints().get(i + 1)).getCraftYaw();
                if ((craftYaw > 360 || craftYaw < 180) && (craftYaw < -180 || craftYaw > 0)) {
                    DataFlycUploadWayPointMsgByIndex.getInstance().setTurnMode(TURNMODE.ANTI_CLOSEWISE);
                } else {
                    DataFlycUploadWayPointMsgByIndex.getInstance().setTurnMode(TURNMODE.CLOCKWISE);
                }
            }
            DataFlycUploadWayPointMsgByIndex.getInstance().setHasAction(false);
            DataFlycUploadWayPointMsgByIndex.getInstance().setActionTimeTimit((short) 999);
            DataFlycUploadWayPointMsgByIndex.getInstance().setActionNum(2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(ACTION.WP_ACTION_CRAFT_YAW);
            arrayList.add(ACTION.WP_ACTION_GIMBAL_YAW);
            DataFlycUploadWayPointMsgByIndex.getInstance().setActionList(arrayList);
            arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(wayPoint.getCraftYaw()));
            arrayList.add(Integer.valueOf(wayPoint.getGimbalYaw()));
            DataFlycUploadWayPointMsgByIndex.getInstance().setParamsList(arrayList);
            Semaphore semaphore = new Semaphore(0);
            DataFlycUploadWayPointMsgByIndex.getInstance().start(new 2(this, i, j, semaphore));
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                a(dji.pilot.dji_groundstation.a.c.a(DataFlycNavigationSwitch.getInstance().getResult()), "");
            }
        }
    }

    private void n() {
        dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().r();
    }

    private void o() {
        dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().u();
        if (dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j() == null || dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j().getPoints().size() <= 1) {
            a(8, Integer.valueOf(R.string.gsnew_gs_way_point_collection_item_too_less_point));
        } else {
            b(c.WayPoint_SetReturnHomeHeight);
        }
    }

    private void p() {
        if (dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().x()) {
            dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().A();
            a(g.A, null);
        } else if (dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j() == null || dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j().getPoints().size() <= 1) {
            getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_collection_item_too_less_point));
        } else {
            dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().z();
            a(g.A, null);
        }
    }

    private void q() {
        if (dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().l() == null) {
            a(R.string.gsnew_gs_follow_me_send_command_failed, R.string.gsnew_gs_follow_me_can_not_get_user_location, "");
        } else if (dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().k() > 300.0d) {
            getInstance().a(8, new Integer(R.string.gsnew_gs_follow_me_radius_too_far));
        } else {
            float height = ((float) DataOsdGetPushCommon.getInstance().getHeight()) / 10.0f;
            if (height < 10.0f || height > 120.0f) {
                getInstance().a(8, new Integer(R.string.gsnew_gs_follow_me_aircraft_not_in_height_zone));
                return;
            }
            new DataFlycSetParams().a("g_config.followme_cfg.enable_change_homepoint_0", Integer.valueOf(0)).start(new 3(this, dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().l()));
        }
    }

    private void a(int i, int i2, String str) {
        Object aVar = new dji.pilot.dji_groundstation.a.a();
        aVar.a = i;
        aVar.b = i2;
        aVar.c = str;
        aVar.d = 250;
        aVar.e = 270;
        aVar.k = false;
        aVar.j = R.string.gsnew_gs_failed_dialog_ok;
        aVar.i = "";
        a(g.M, aVar);
    }

    private void r() {
        dji.midware.e.d 4 = new 4(this);
        if (i.getInstance().c().value() == ProductType.A2.value()) {
            DataFlycStartIoc.getInstance().setMode(IOCType.IOCTypeCourseLock).start(4);
        } else {
            a(true, 2, new 5(this, 4));
        }
    }

    private void s() {
        dji.midware.e.d 6 = new 6(this);
        if (i.getInstance().c().value() == ProductType.A2.value()) {
            DataFlycStartIoc.getInstance().setMode(IOCType.IOCTypeHomeLockA2).start(6);
        } else {
            a(true, 2, new 7(this, 6));
        }
    }

    private void t() {
        a(true, 2, new 9(this, new 8(this)));
    }

    private void u() {
        a(true, 2, new 10(this));
    }
}
