package dji.pilot.dji_groundstation.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionCurrentEvent;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.midware.data.model.P3.DataFlycHotPointMissionDownload;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.FINISH_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.b;
import dji.pilot.dji_groundstation.controller.DataMgr.d;
import dji.pilot.dji_groundstation.controller.DataMgr.e;

public class a {
    private static a a = null;
    private static final String b = "g_config.fail_safe.protect_action_0";
    private static Context c = null;
    private Handler d = new Handler(Looper.getMainLooper());
    private boolean e = true;
    private boolean f = false;
    private boolean g = false;
    private int h = -10086;
    private int i = -1;
    private FLYC_STATE j = FLYC_STATE.OTHER;
    private boolean k = o();
    private RcModeChannel l = RcModeChannel.CHANNEL_UNKNOWN;
    private int m = 0;
    private long n = 0;
    private FLYC_STATE o = FLYC_STATE.OTHER;
    private RcModeChannel p = RcModeChannel.CHANNEL_UNKNOWN;

    public static synchronized a getInstance(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null && context != null && (context instanceof Activity)) {
                a = new a(context);
            }
            if (context != null && (context instanceof Activity)) {
                c = context;
            }
            aVar = a;
        }
        return aVar;
    }

    public a(Context context) {
        c = context;
        this.d.postDelayed(new 1(this), 2000);
    }

    public Context a() {
        return c;
    }

    public boolean b() {
        return this.e && d.getInstance().b().ordinal() == c.FollowMe.ordinal();
    }

    public boolean c() {
        return this.f;
    }

    public boolean d() {
        return this.g;
    }

    public void e() {
    }

    public void f() {
    }

    public boolean g() {
        return this.e;
    }

    public void a(int i) {
        this.h = i;
    }

    public void onEventBackgroundThread(DataFlycGetPushWayPointMissionCurrentEvent dataFlycGetPushWayPointMissionCurrentEvent) {
        this.h = dataFlycGetPushWayPointMissionCurrentEvent.getEventType();
        switch (dataFlycGetPushWayPointMissionCurrentEvent.getEventType()) {
            case 0:
                e.getInstance().y();
                return;
            case 1:
                if (e.getInstance().n() == FINISH_ACTION.GOHOME) {
                    d.getInstance().a(8, c.getResources().getString(R.string.gsnew_gs_way_point_finish_go_home_notice));
                } else {
                    d.getInstance().a(8, c.getResources().getString(R.string.gsnew_gs_way_point_finish_no_limits_notice));
                }
                d.getInstance().a(false, 2, new 3(this));
                return;
            case 2:
                int i = -1;
                DataFlycGetPushWayPointMissionInfo instance = DataFlycGetPushWayPointMissionInfo.getInstance();
                if (instance.getMissionType() == 1) {
                    i = instance.getTargetWayPoint();
                }
                DJIWPCollectionItem j = e.getInstance().j();
                if (j != null && j.getPoints() != null && j.getPoints().size() > 0 && r0 == j.getPoints().size() - 1) {
                    this.h = 1;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void l() {
        d.getInstance().a(dji.pilot.dji_groundstation.a.d.a.e);
        d.getInstance().c();
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 1;
        dji.thirdparty.a.c.a().e(eVar);
        m();
    }

    public void onEventMainThread(DataA2PushCommom dataA2PushCommom) {
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.ConnectLose) {
            m();
            d.getInstance().c();
            this.j = FLYC_STATE.OTHER;
            e.getInstance().e();
            dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().e();
            d.getInstance().a(c.None);
            d.getInstance().a(dji.pilot.dji_groundstation.a.d.a.e);
        } else if (pVar != p.ConnectOK) {
        }
    }

    private void m() {
        f.getInstance(c).e();
        f.getInstance(c).b(false);
    }

    public void onEventMainThread(DataFlycGetPushWayPointMissionInfo dataFlycGetPushWayPointMissionInfo) {
        if (ServiceManager.getInstance().isConnected() && g()) {
            switch (dataFlycGetPushWayPointMissionInfo.getMissionType()) {
                case 0:
                    if (dataFlycGetPushWayPointMissionInfo.getCurrentStatus() == 1) {
                        m();
                        d.getInstance().a(8, c.getString(R.string.gsnew_gs_follow_me_notify_dialog_title));
                        return;
                    }
                    return;
                case 1:
                    if (DataOsdGetPushCommon.getInstance().getFlycState() != FLYC_STATE.NaviGo) {
                        return;
                    }
                    if (d.getInstance().b() == c.WayPoint_Status) {
                        if (this.i != dataFlycGetPushWayPointMissionInfo.getWayPointStatus()) {
                            if (dataFlycGetPushWayPointMissionInfo.getTargetWayPoint() == 0) {
                                d.getInstance().a(8, c.getResources().getString(R.string.gsnew_gs_way_point_going_to_first_point));
                            } else if (dataFlycGetPushWayPointMissionInfo.getTargetWayPoint() == 1) {
                                d.getInstance().a(8, c.getResources().getString(R.string.gsnew_gs_way_point_mession_begin));
                            }
                            d.getInstance().a(8, c.getResources().getString(dataFlycGetPushWayPointMissionInfo.getWayPointStatus() == 2 ? R.string.gsnew_gs_wp_paused : R.string.gsnew_gs_wp_resume));
                        }
                        this.i = dataFlycGetPushWayPointMissionInfo.getWayPointStatus();
                        return;
                    } else if (this.h != 1 && this.h != -1 && d.getInstance().b().a() != c.WayPoint_DownloadMission.a()) {
                        d.getInstance().a(c.WayPoint_DownloadMission);
                        f.getInstance(c).b(c);
                        return;
                    } else {
                        return;
                    }
                case 2:
                    if (DataOsdGetPushCommon.getInstance().getFlycState() == FLYC_STATE.GPS_HotPoint) {
                        Object obj = "";
                        if (this.i != dataFlycGetPushWayPointMissionInfo.getHotPointMissionStatus()) {
                            if (dataFlycGetPushWayPointMissionInfo.getHotPointMissionStatus() == 2) {
                                obj = c.getString(R.string.gsnew_gs_poi_paused);
                                d.getInstance().a(false);
                            } else {
                                obj = c.getString(R.string.gsnew_gs_poi_resume);
                                d.getInstance().a(true);
                            }
                            this.i = dataFlycGetPushWayPointMissionInfo.getHotPointMissionStatus();
                        }
                        d.getInstance().a(8, obj);
                        if (d.getInstance().b() != c.PointOfInsterst_Status) {
                            DataFlycHotPointMissionDownload instance = DataFlycHotPointMissionDownload.getInstance();
                            instance.start(new 4(this, instance));
                            return;
                        }
                        return;
                    }
                    return;
                case 3:
                    if (DataOsdGetPushCommon.getInstance().getFlycState() == FLYC_STATE.NaviMissionFollow) {
                        if (!(d.getInstance().b().a() == c.FollowMe_Status.a() || dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().i())) {
                            d.getInstance().a(c.FollowMe_Status);
                            f.getInstance(c).b(c);
                            dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().b(new 5(this));
                        }
                        if (dataFlycGetPushWayPointMissionInfo.getFollowMeStatus() == 2) {
                            d.getInstance().a(false, 2, new 6(this));
                            return;
                        }
                        return;
                    }
                    return;
                case 4:
                    if (d.getInstance().b().ordinal() != c.HomeLock_Status.ordinal() && d.getInstance().b().ordinal() != c.CourseLock.ordinal()) {
                        FLYC_STATE flycState = DataOsdGetPushCommon.getInstance().getFlycState();
                        if ((flycState == FLYC_STATE.GPS_CL || flycState == FLYC_STATE.Atti_CL || flycState == FLYC_STATE.GPS_HomeLock) && this.j.ordinal() != flycState.ordinal()) {
                            if (flycState == FLYC_STATE.GPS_CL || flycState == FLYC_STATE.Atti_CL) {
                                d.getInstance().a(c.CourseLock_Status);
                                f.getInstance(c).b(c);
                                b.getInstance().b(true);
                            } else if (flycState == FLYC_STATE.GPS_HomeLock) {
                                d.getInstance().a(c.HomeLock_Status);
                                f.getInstance(c).b(c);
                            }
                            this.j = flycState;
                            return;
                        }
                        return;
                    }
                    return;
                case 6:
                    if (d.getInstance().b().ordinal() != c.Tripod.ordinal()) {
                        new Handler().postDelayed(new 7(this), 1000);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.a aVar) {
        if (aVar == dji.pilot.fpv.model.n.a.LEFTMENU_OPT_CLICK && d.getInstance().b().a() == c.Tripod.a()) {
            f.getInstance(a()).f();
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        boolean z = true;
        if (ServiceManager.getInstance().isConnected() && a(dataOsdGetPushCommon)) {
            if (!(a(null) || DataOsdGetPushCommon.getInstance().getModeChannel() == RcModeChannel.CHANNEL_F || d.getInstance().b().a() == c.None.a())) {
                d.getInstance().a(false, 2, new 8(this));
            }
            b(dataOsdGetPushCommon);
            if (a(dataOsdGetPushCommon.getModeChannel())) {
                if (this.l != RcModeChannel.CHANNEL_UNKNOWN) {
                    l();
                }
                this.l = dataOsdGetPushCommon.getModeChannel();
            }
            if (dataOsdGetPushCommon.getFlycState() == FLYC_STATE.GoHome || dataOsdGetPushCommon.getFlycState() == FLYC_STATE.AutoLanding || !(this.k == o() || o())) {
                l();
                dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
                eVar.s = 9;
                eVar.t = null;
                dji.thirdparty.a.c.a().e(eVar);
            }
            this.k = o();
            if (d()) {
                DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
                if (instance.isBeginnerMode() || !instance.isMultipleModeOpen()) {
                    z = false;
                }
                if (!a(i.getInstance().c(), z, DataOsdGetPushCommon.getInstance().getModeChannel())) {
                }
            } else if (g() && i.getInstance().c() != ProductType.A2) {
                if (DataOsdGetPushHome.getInstance().isMultipleModeOpen()) {
                    n();
                    if (DataOsdGetPushCommon.getInstance().getModeChannel() == RcModeChannel.CHANNEL_F) {
                        dji.pilot.dji_groundstation.a.e eVar2;
                        if (dataOsdGetPushCommon.getVoltageWarning() == 2) {
                            m();
                            eVar2 = new dji.pilot.dji_groundstation.a.e();
                            eVar2.s = 1;
                            dji.thirdparty.a.c.a().e(eVar2);
                        }
                        if ((dataOsdGetPushCommon.getFlycState() == FLYC_STATE.GoHome || dataOsdGetPushCommon.getFlycState() == FLYC_STATE.AutoLanding) && DataOsdGetPushHome.getInstance().isMultipleModeOpen()) {
                            m();
                            eVar2 = new dji.pilot.dji_groundstation.a.e();
                            eVar2.s = 1;
                            dji.thirdparty.a.c.a().e(eVar2);
                            p();
                        }
                    }
                } else if (d.getInstance().b().a() != c.None.a()) {
                    d.getInstance().a(false, 2, new 9(this));
                    l();
                }
            }
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        this.m = dataOsdGetPushHome.getGoHomeHeight();
        e.getInstance().a(new dji.gs.e.b(dataOsdGetPushHome.getLatitude(), dataOsdGetPushHome.getLongitude()));
        if (d()) {
            boolean z = !dataOsdGetPushHome.isBeginnerMode() && dataOsdGetPushHome.isMultipleModeOpen();
            if (!a(i.getInstance().c(), z, DataOsdGetPushCommon.getInstance().getModeChannel())) {
            }
        }
    }

    private boolean a(RcModeChannel rcModeChannel) {
        return rcModeChannel != this.l;
    }

    public boolean a(ProductType productType, boolean z, RcModeChannel rcModeChannel) {
        boolean z2 = false;
        if (a(productType)) {
            if (!z) {
                if (z) {
                }
                return false;
            } else if (z || rcModeChannel == RcModeChannel.CHANNEL_P) {
                return false;
            } else {
                return true;
            }
        } else if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 11) {
            return true;
        } else {
            if (!(z && rcModeChannel == RcModeChannel.CHANNEL_F)) {
                z2 = true;
            }
            return z2;
        }
    }

    public boolean a(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.A2 || productType == ProductType.Tomato || productType == ProductType.Pomato || b(productType) || dji.pilot.publics.e.a.h();
    }

    public boolean b(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.KumquatX || productType == ProductType.KumquatS;
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        if (d.getInstance().b().a() == c.WayPoint_PageAddNew.a()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (dataRcGetPushParams.getCustom1() == 1) {
                if (currentTimeMillis - this.n > 500) {
                    this.n = currentTimeMillis;
                    e.getInstance().r();
                }
            } else if (dataRcGetPushParams.getCustom2() == 1 && currentTimeMillis - this.n > 100) {
                this.n = currentTimeMillis;
                e.getInstance().s();
            }
        }
    }

    public void h() {
    }

    public void i() {
    }

    public void a(boolean z) {
        this.f = z;
    }

    private boolean a(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon == null) {
            return false;
        }
        if (dji.pilot.c.d.b == MODE.Master || dji.logic.c.b.getInstance().a(i.getInstance().c())) {
            int flycVersion = dataOsdGetPushCommon.getFlycVersion();
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 4;
            this.g = flycVersion >= 11;
            if (flycVersion >= 7) {
                if (!this.e) {
                    this.e = true;
                }
            } else if (this.e) {
                this.e = false;
            }
            eVar.t = Boolean.valueOf(this.e);
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        }
        m();
        return false;
    }

    private void b(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon != null) {
            FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
            if (this.o != flycState && (flycState == FLYC_STATE.AssitedTakeoff || flycState == FLYC_STATE.AutoTakeoff)) {
                new DataFlycGetParams().setInfos(new String[]{b}).start(new 10(this));
            }
            this.o = flycState;
        }
    }

    private void a(String str) {
        if (str != null && !str.trim().isEmpty()) {
            d.getInstance().a(8, (Object) str);
        }
    }

    private void n() {
        RcModeChannel modeChannel = DataOsdGetPushCommon.getInstance().getModeChannel();
        if (this.p != modeChannel) {
            if (modeChannel == RcModeChannel.CHANNEL_F) {
                if (o()) {
                    p();
                } else {
                    d.getInstance().a(8, c.getString(R.string.gsnew_gs_error_aircraft_not_in_the_air));
                }
            } else if (this.p != RcModeChannel.CHANNEL_UNKNOWN) {
                d.getInstance().a(false, 2, new 2(this));
                l();
            }
            this.p = modeChannel;
        }
    }

    private boolean o() {
        return DataOsdGetPushCommon.getInstance().groundOrSky() == 2;
    }

    private void p() {
        if (DataOsdGetPushHome.getInstance().isMultipleModeOpen()) {
            e.a("gs://flightmode/main/", c);
            return;
        }
        if (!f.getInstance(c).a()) {
            f.getInstance(c).c(c);
        }
        Object aVar = new dji.pilot.dji_groundstation.a.a();
        aVar.a = -1;
        aVar.b = R.string.gsnew_gs_enable_mult_flight_mode_dialog_title;
        aVar.d = 250;
        aVar.e = 270;
        aVar.k = false;
        aVar.h = R.string.gsnew_gs_enable_mult_flight_mode_dialog_left_btn;
        aVar.j = R.string.gsnew_gs_enable_mult_flight_mode_dialog_right_btn;
        aVar.i = "gs://flightmode/main/openmultimode";
        d.getInstance().a(g.M, aVar);
    }
}
