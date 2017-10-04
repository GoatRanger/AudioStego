package dji.pilot.groundStation.a;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import com.here.android.mpa.mapping.Map;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataA2PushCommom.DJIA2CtrlMode;
import dji.midware.data.model.P3.DataFlycGetFsAction.FS_ACTION;
import dji.midware.data.model.P3.DataFlycGetIoc;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionCurrentEvent;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.midware.data.model.P3.DataFlycHotPointMissionDownload;
import dji.midware.data.model.P3.DataFlycHotPointMissionSwitch;
import dji.midware.data.model.P3.DataFlycHotPointMissionSwitch.HOTPOINTMISSIONSWITCH;
import dji.midware.data.model.P3.DataFlycHotPointResetCamera;
import dji.midware.data.model.P3.DataFlycHotPointResetParams;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycNavigationSwitch.GS_COMMAND;
import dji.midware.data.model.P3.DataFlycSendGpsInfo;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataFlycStartFollowMeWithInfo;
import dji.midware.data.model.P3.DataFlycStartFollowMeWithInfo.FOLLOWMODE;
import dji.midware.data.model.P3.DataFlycStartFollowMeWithInfo.YAWMODE;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.CAMERA_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.TO_START_POINT_MODE;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.data.model.P3.DataFlycStartIoc.IOCType;
import dji.midware.data.model.P3.DataFlycStartNoeMission;
import dji.midware.data.model.P3.DataFlycStopIoc;
import dji.midware.data.model.P3.DataFlycStopNoeMission;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.ACTION_ON_RC_LOST;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.FINISH_ACTION;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.GIMBAL_PITCH_MODE;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.TRACE_MODE;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.YAW_MODE;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex.ACTION;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex.TURNMODE;
import dji.midware.data.model.P3.DataFlycWayPointMissionPauseOrResume;
import dji.midware.data.model.P3.DataFlycWayPointMissionSwitch;
import dji.midware.data.model.P3.DataFlycWayPointMissionSwitch.CMD;
import dji.midware.data.model.P3.DataFlycWayPointSetIdleSpeed;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.R;
import dji.pilot.fpv.activity.DJIBaseNewPreviewActivity;
import dji.pilot.fpv.activity.DJIPreviewActivity;
import dji.pilot.fpv.activity.DJIPreviewActivityGrape;
import dji.pilot.fpv.activity.DJIPreviewActivityLitchi;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.control.q;
import dji.pilot.fpv.flightmode.DJIFlightModeView;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.pilot.groundStation.stage.DJIGSWayPointWaitUploadMissionView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class a {
    public static final String a = "flight_mode";
    public static final int b = 0;
    public static final int c = 1;
    private static a f = null;
    private static Context g = null;
    private final int A;
    private final int B;
    private final int C;
    private final int D;
    private final int E;
    private final int F;
    private final int G;
    private int H;
    private dji.pilot.groundStation.b.b I;
    private long J;
    private b K;
    private boolean L;
    private boolean M;
    private int N;
    private long O;
    private boolean P;
    private int Q;
    private boolean R;
    private Display S;
    private int T;
    private int U;
    private int V;
    private boolean W;
    private int X;
    private int Y;
    private RcModeChannel Z;
    private long aa;
    private dji.pilot.groundStation.b.a ab;
    private int ac;
    private final Handler ad;
    private final Runnable ae;
    private float af;
    private double ag;
    private double ah;
    private double ai;
    private double aj;
    private double ak;
    private double al;
    private float am;
    private boolean an;
    private ROTATION_DIR ao;
    private boolean ap;
    private DJIWPCollectionItem aq;
    private float ar;
    private FINISH_ACTION as;
    private YAW_MODE at;
    private boolean au;
    private boolean av;
    private boolean aw;
    private int ax;
    private final int ay;
    private int az;
    dji.pilot.groundStation.b.a d;
    dji.pilot.groundStation.b.c e;
    private k h;
    private q i;
    private final Handler j;
    private boolean k;
    private ParamInfo l;
    private boolean m;
    private dji.pilot.fpv.view.DJIErrorPopView.b n;
    private FLYC_STATE o;
    private boolean p;
    private final OnClickListener q;
    private d r;
    private d s;
    private List<DJIWPCollectionItem> t;
    private SharedPreferences u;
    private String v;
    private String w;
    private final int x;
    private final int y;
    private final int z;

    static /* synthetic */ class AnonymousClass21 {
        static final /* synthetic */ int[] a = new int[DJIA2CtrlMode.values().length];

        static {
            c = new int[c$b.values().length];
            try {
                c[c$b.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[c$b.TRACK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                c[c$b.POINT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                c[c$b.SMART.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            b = new int[d.values().length];
            try {
                b[d.COURSE_LOCK.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[d.FOLLOW_ME.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[d.HOME_LOCK.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[d.POI_AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[d.WP_AUTO.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[d.TERRAIN_TRACKING.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[DJIA2CtrlMode.e.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[DJIA2CtrlMode.c.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[DJIA2CtrlMode.f.ordinal()] = 3;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    private static final class a implements Comparator<DJIWPCollectionItem> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((DJIWPCollectionItem) obj, (DJIWPCollectionItem) obj2);
        }

        public int a(DJIWPCollectionItem dJIWPCollectionItem, DJIWPCollectionItem dJIWPCollectionItem2) {
            if (dJIWPCollectionItem.getAutoAddFlag() == dJIWPCollectionItem2.getAutoAddFlag()) {
                if (dJIWPCollectionItem.getCreatedDate() > dJIWPCollectionItem2.getCreatedDate()) {
                    return -1;
                }
                if (dJIWPCollectionItem.getCreatedDate() < dJIWPCollectionItem2.getCreatedDate()) {
                    return 1;
                }
                return 0;
            } else if (dJIWPCollectionItem.getAutoAddFlag() > dJIWPCollectionItem2.getAutoAddFlag()) {
                if (dJIWPCollectionItem2.getAutoAddFlag() != 0) {
                    return 1;
                }
                return -1;
            } else if (dJIWPCollectionItem.getAutoAddFlag() == 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public interface b {
        void onClick(int i);
    }

    public enum c {
        ShowHideTips,
        EnableGS,
        DisableGS,
        EnableChangeModes,
        DisableChangeModes,
        ShowFavoriteTip,
        HideFavoriteTip,
        MainViewShowSettingDialog,
        MainViewHideSettingDialog,
        VisualViewShowGSMainView
    }

    public enum d {
        NONE(0),
        COURSE_LOCK(1),
        HOME_LOCK(2),
        FOLLOW_ME(3),
        POI_AUTO(4),
        WP_AUTO(6),
        WAIT(8),
        TERRAIN_TRACKING(9);
        
        private int i;

        private d(int i) {
            this.i = i;
        }

        public int a() {
            return this.i;
        }

        public boolean a(int i) {
            return this.i == i;
        }

        public static d find(int i) {
            d dVar = NONE;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dVar;
        }
    }

    public void a() {
        a(false);
    }

    public void a(boolean z) {
        int i = this.u.getInt(this.w, 0);
        if (!z) {
            switch (this.r) {
                case COURSE_LOCK:
                    if ((i & 1) != 1) {
                        dji.thirdparty.a.c.a().e(c.ShowHideTips);
                        this.u.edit().putInt(this.w, i | 1).commit();
                        return;
                    }
                    return;
                case FOLLOW_ME:
                    if ((i & 2) != 2) {
                        dji.thirdparty.a.c.a().e(c.ShowHideTips);
                        this.u.edit().putInt(this.w, i | 2).commit();
                        return;
                    }
                    return;
                case HOME_LOCK:
                    if ((i & 4) != 4) {
                        dji.thirdparty.a.c.a().e(c.ShowHideTips);
                        this.u.edit().putInt(this.w, i | 4).commit();
                        return;
                    }
                    return;
                case POI_AUTO:
                    if ((i & 5) != 5) {
                        dji.thirdparty.a.c.a().e(c.ShowHideTips);
                        this.u.edit().putInt(this.w, i | 5).commit();
                        return;
                    }
                    return;
                case WP_AUTO:
                    if ((i & 16) != 16) {
                        dji.thirdparty.a.c.a().e(c.ShowHideTips);
                        this.u.edit().putInt(this.w, i | 16).commit();
                        return;
                    }
                    return;
                default:
                    if ((i & 32) != 32) {
                        dji.thirdparty.a.c.a().e(c.ShowHideTips);
                        this.u.edit().putInt(this.w, i | 32).commit();
                        return;
                    }
                    return;
            }
        } else if ((i & 512) == 0) {
            this.u.edit().putInt(this.w, i | 512).commit();
            dji.thirdparty.a.c.a().e(c.ShowHideTips);
        }
    }

    private a() {
        int i = 0;
        this.h = null;
        this.j = new Handler();
        this.k = false;
        this.m = true;
        this.n = new dji.pilot.fpv.view.DJIErrorPopView.b();
        this.o = FLYC_STATE.OTHER;
        this.p = false;
        this.q = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.d()) {
                    this.a.b(view);
                } else if ((this.a.u.getInt(this.a.w, 0) & 128) != 128) {
                    this.a.S();
                } else if (!this.a.o()) {
                    if (i.getInstance().c() == ProductType.A2) {
                        switch (AnonymousClass21.a[DataA2PushCommom.getInstance().e().ordinal()]) {
                            case 1:
                            case 2:
                                this.a.a((int) R.layout.gs_course_lock_status_view, 12);
                                return;
                            case 3:
                                this.a.a((int) R.layout.gs_home_lock_status_view, 13);
                                return;
                            default:
                                this.a.y();
                                return;
                        }
                    } else if (DataOsdGetPushHome.getInstance().isMultipleModeOpen()) {
                        switch (this.a.r) {
                            case COURSE_LOCK:
                                this.a.a((int) R.layout.gs_course_lock_status_view, 12);
                                return;
                            case FOLLOW_ME:
                                this.a.a((int) R.layout.gs_follow_me_status_view, 14);
                                return;
                            case HOME_LOCK:
                                this.a.a((int) R.layout.gs_home_lock_status_view, 13);
                                return;
                            case POI_AUTO:
                                this.a.a((int) R.layout.gs_point_of_insterest_status_view, 22);
                                return;
                            case WP_AUTO:
                                this.a.a((int) R.layout.gs_way_point_status_view, 21);
                                return;
                            default:
                                this.a.y();
                                return;
                        }
                    } else {
                        this.a.R();
                    }
                }
            }
        };
        this.r = d.NONE;
        this.s = d.NONE;
        this.t = null;
        this.u = null;
        this.v = "gs_settings";
        this.w = "gs_hide_tip_times";
        this.x = 1;
        this.y = 2;
        this.z = 4;
        this.A = 5;
        this.B = 16;
        this.C = 32;
        this.D = 128;
        this.E = 256;
        this.F = 512;
        this.G = 1024;
        this.H = 100;
        this.I = null;
        this.J = 0;
        this.K = null;
        this.L = false;
        this.M = false;
        this.N = 0;
        this.O = -1;
        this.P = false;
        this.Q = -1;
        this.R = false;
        this.S = null;
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = false;
        this.X = 0;
        this.Y = 0;
        this.Z = RcModeChannel.CHANNEL_UNKNOWN;
        this.d = null;
        this.e = null;
        this.aa = 0;
        this.ab = null;
        this.ac = 0;
        this.ad = new Handler();
        this.ae = new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.r != d.FOLLOW_ME) {
                    return;
                }
                if (this.a.ac < 5) {
                    this.a.ac = 0;
                    dji.gs.e.b k = k.k();
                    if (k != null && k.a() && k.b()) {
                        this.a.a(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                            }
                        }, k.b, k.c);
                    }
                    this.a.ad.postDelayed(this.a.ae, 100);
                    return;
                }
                this.a.T();
            }
        };
        this.af = 0.0f;
        this.ag = 0.0d;
        this.ah = 0.0d;
        this.ai = 0.0d;
        this.aj = 0.0d;
        this.ak = 0.0d;
        this.al = 0.0d;
        this.am = 0.0f;
        this.an = false;
        this.ao = ROTATION_DIR.Clockwise;
        this.ap = false;
        this.aq = new DJIWPCollectionItem();
        this.ar = 0.0f;
        this.as = FINISH_ACTION.NO_LIMIT;
        this.at = YAW_MODE.PATH_COURSE;
        this.au = false;
        this.av = false;
        this.aw = false;
        this.ax = 0;
        this.ay = 10;
        this.az = -1;
        this.u = g.getSharedPreferences(this.v, 0);
        this.l = dji.midware.data.manager.P3.d.read("g_config.control.multi_control_mode_enable_0");
        try {
            this.t = com.dji.frame.c.c.c(g.getApplicationContext()).c(DJIWPCollectionItem.class);
            Collections.sort(this.t, new a());
            ArrayList arrayList = new ArrayList();
            for (DJIWPCollectionItem dJIWPCollectionItem : this.t) {
                if (dJIWPCollectionItem.getAutoAddFlag() > 0) {
                    i++;
                    dJIWPCollectionItem.setAutoAddFlag(i);
                    if (i > 3) {
                        arrayList.add(dJIWPCollectionItem);
                    }
                }
                i = i;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a(this.t.indexOf((DJIWPCollectionItem) it.next()));
            }
        } catch (Exception e) {
            this.t = new ArrayList();
        }
        this.j.postDelayed(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
            }
        }, 2000);
    }

    public void b() {
        a(g != null ? g.getApplicationContext() : null);
        a(null);
        a(null);
        if (this.I != null) {
            this.I.dismiss();
        }
        this.I = null;
    }

    public static synchronized a getInstance(Context context) {
        a aVar;
        synchronized (a.class) {
            if (f == null && context != null) {
                g = context;
                f = new a();
            }
            aVar = f;
        }
        return aVar;
    }

    public static void a(Context context) {
        if (context != null) {
            g = context;
        }
    }

    public boolean c() {
        return this.m;
    }

    public boolean d() {
        return this.p;
    }

    public boolean e() {
        if ((this.u.getInt(this.w, 0) & 256) == 256) {
            return true;
        }
        return false;
    }

    public void f() {
        this.u.edit().putInt(this.w, this.u.getInt(this.w, 0) | 256).commit();
    }

    public OnClickListener g() {
        return this.q;
    }

    public List<DJIWPCollectionItem> h() {
        return this.t;
    }

    public void a(DJIWPCollectionItem dJIWPCollectionItem) {
        try {
            if (dJIWPCollectionItem.getCreatedDate() == 0) {
                dJIWPCollectionItem.setCreatedDate(System.currentTimeMillis());
            }
            dJIWPCollectionItem.setDistance(dji.pilot.groundStation.b.a(dJIWPCollectionItem.getPoints()));
            com.dji.frame.c.c.c(g.getApplicationContext()).a(dJIWPCollectionItem);
        } catch (Exception e) {
        }
    }

    public void b(DJIWPCollectionItem dJIWPCollectionItem) {
        try {
            if (dJIWPCollectionItem.getCreatedDate() == 0) {
                dJIWPCollectionItem.setCreatedDate(System.currentTimeMillis());
            }
            dJIWPCollectionItem.setDistance(dji.pilot.groundStation.b.a(dJIWPCollectionItem.getPoints()));
            com.dji.frame.c.c.c(g.getApplicationContext()).e(dJIWPCollectionItem);
        } catch (Exception e) {
        }
    }

    public void a(int i) {
        if (this.t.size() > i) {
            DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) this.t.get(i);
            if (dJIWPCollectionItem != null) {
                com.dji.frame.c.c.c(g.getApplicationContext()).f(dJIWPCollectionItem);
            }
            this.t.remove(i);
        }
    }

    public void onEventMainThread(DataA2PushCommom dataA2PushCommom) {
        if (i.getInstance().c() == ProductType.A2) {
            int i = this.H;
            this.H = i + 1;
            if (i > 100) {
                this.H = 0;
                DataRcGetMaster.getInstance().start(new dji.midware.e.d(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
            }
            if (dataA2PushCommom.f() != 1 || DataRcGetMaster.getInstance().getMode() != MODE.a) {
                l();
            }
        }
    }

    public k i() {
        return this.h;
    }

    public boolean j() {
        return this.m && this.r == d.FOLLOW_ME;
    }

    public dji.pilot.groundStation.b.b k() {
        return this.I;
    }

    public void a(final int i, final int i2) {
        if (!x()) {
            this.j.postDelayed(new Runnable(this) {
                final /* synthetic */ a c;

                public void run() {
                    this.c.a(i, i2);
                }
            }, 1000);
        } else if (this.I == null || !this.I.isShowing()) {
            if (g != null && (g instanceof Activity)) {
                this.I = new dji.pilot.groundStation.b.b(g);
                this.I.setOnDismissListener(new OnDismissListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                        dji.thirdparty.a.c.a().e(DJICustomType.s);
                    }
                });
                this.I.a(i, i2);
                this.L = false;
            }
            dji.thirdparty.a.c.a().e(DJICustomType.r);
        } else if (this.I.c() != i2) {
            this.I.a(i, i2);
            this.L = false;
        }
    }

    public void l() {
        if (this.I != null && this.I.isShowing()) {
            this.I.dismiss();
            this.I = null;
        }
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
            this.e = null;
        }
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
            this.d = null;
        }
    }

    public void m() {
        if (o()) {
            this.az = this.I.c();
        } else {
            this.az = -1;
        }
        y();
    }

    public void n() {
        this.az = -1;
    }

    public void b(boolean z) {
        if (o()) {
            this.az = this.I.c();
        } else {
            this.az = -1;
        }
        DJIFlightModeView.b = z;
        if (dji.pilot.fpv.d.b.p(i.getInstance().c())) {
            a((int) R.layout.fpv_flightmode_view_fullscreen, 102);
        } else {
            a((int) R.layout.fpv_flightmode_view, 100);
        }
    }

    public boolean o() {
        return this.I != null && this.I.isShowing() && this.I.getWindow() != null && this.I.getWindow().getDecorView().getVisibility() == 0;
    }

    public boolean p() {
        return o() || this.L;
    }

    public boolean q() {
        return this.I != null && this.I.isShowing() && (this.I.c() == 0 || this.I.c() == 100);
    }

    public boolean r() {
        return this.I != null && this.I.isShowing() && (this.I.c() == 100 || this.I.c() == 102);
    }

    public void a(k kVar) {
        this.h = kVar;
    }

    public void a(q qVar) {
        this.i = qVar;
    }

    public boolean s() {
        return this.i.c();
    }

    public void c(boolean z) {
        this.k = z;
        if (this.h != null) {
            if (z) {
                a(this.s);
                if (this.s != d.NONE) {
                    l();
                    if (this.q != null) {
                        this.q.onClick(null);
                    }
                    if (this.s == d.WP_AUTO && this.h.l() != null && this.h.l().m().size() > 1) {
                        this.h.l().g();
                        for (int i = 0; i < this.aq.getPoints().size(); i++) {
                            DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) this.aq.getPoints().get(i);
                            this.h.l().g(dji.gs.utils.a.a(new dji.gs.e.b(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng())));
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            this.s = this.r;
            if (this.h.l() != null) {
                this.h.l().g();
                this.h.l().b(null, 0.0d);
            }
            l();
        }
    }

    public boolean t() {
        return this.k;
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        if (this.K != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (dataRcGetPushParams.getCustom1() == 1) {
                if (currentTimeMillis - this.J > 500) {
                    this.J = currentTimeMillis;
                    this.K.onClick(1);
                }
            } else if (dataRcGetPushParams.getCustom2() == 1 && currentTimeMillis - this.J > 100) {
                this.J = currentTimeMillis;
                this.K.onClick(2);
            }
        }
    }

    public void a(b bVar) {
        this.K = bVar;
    }

    public boolean u() {
        return this.K != null;
    }

    public void onEventMainThread(c cVar) {
        if (cVar == c.MainViewHideSettingDialog) {
            if (this.L && this.I != null) {
                this.L = false;
                if (this.I.c() == 22) {
                    DataRcGetControlMode.getInstance().start(new dji.midware.e.d(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                    this.j.postDelayed(new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.I.dismiss();
                            this.a.I = new dji.pilot.groundStation.b.b(a.g);
                            this.a.I.a((int) R.layout.gs_point_of_insterest_status_view, 22);
                        }
                    }, 500);
                } else if (this.I.c() == 23) {
                    this.I.show();
                    if (this.K != null) {
                        this.K.onClick(100);
                    }
                } else {
                    this.I.show();
                }
            }
        } else if (cVar == c.MainViewShowSettingDialog) {
            if (o()) {
                this.L = true;
                this.I.hide();
                return;
            }
            this.L = false;
        } else if (cVar == c.VisualViewShowGSMainView) {
            y();
        }
    }

    public void onEventMainThread(dji.pilot.fpv.control.q.a aVar) {
        if (aVar == dji.pilot.fpv.control.q.a.SWITCH_SHOW) {
            this.M = false;
        } else if (aVar == dji.pilot.fpv.control.q.a.SWITCH_GO) {
            this.M = true;
        }
    }

    public boolean v() {
        return this.M;
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (ServiceManager.getInstance().isConnected()) {
            int flycVersion = dataOsdGetPushCommon.getFlycVersion();
            if (flycVersion >= 11) {
                this.p = true;
                if (!this.m) {
                    this.m = true;
                    dji.thirdparty.a.c.a().e(c.EnableGS);
                }
            } else if (flycVersion >= 7) {
                this.p = false;
                if (!this.m) {
                    this.m = true;
                    dji.thirdparty.a.c.a().e(c.EnableGS);
                }
            } else {
                this.p = false;
                if (this.m) {
                    this.m = false;
                    dji.thirdparty.a.c.a().e(c.DisableGS);
                }
            }
            if (dji.pilot.c.d.b == MODE.a || dji.logic.c.b.getInstance().a(i.getInstance().c())) {
                if (!(dji.pilot.fpv.d.b.q(null) || DataOsdGetPushCommon.getInstance().getModeChannel() == RcModeChannel.CHANNEL_F)) {
                    this.s = d.NONE;
                }
                FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
                if ((flycState == FLYC_STATE.AssitedTakeoff && this.o != FLYC_STATE.AssitedTakeoff) || (flycState == FLYC_STATE.AutoTakeoff && this.o != FLYC_STATE.AutoTakeoff)) {
                    dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                    bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                    bVar.g = f.a;
                    new DataFlycGetParams().setInfos(new String[]{"g_config.fail_safe.protect_action_0"}).start(new dji.midware.e.d(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            FS_ACTION find = FS_ACTION.find(dji.midware.data.manager.P3.d.read("g_config.fail_safe.protect_action_0").value.intValue());
                            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                            bVar.g = f.a;
                            int a;
                            if (find == FS_ACTION.GoHome) {
                                if (this.a.Y > 0) {
                                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                                        a = (int) dji.pilot.groundStation.b.a((float) this.a.Y);
                                        bVar.c = a.g.getString(R.string.return_to_home_attitude) + String.format("%dFT", new Object[]{Integer.valueOf(a)});
                                    } else {
                                        bVar.c = a.g.getString(R.string.return_to_home_attitude) + String.format("%dM", new Object[]{Integer.valueOf(this.a.Y)});
                                    }
                                    dji.thirdparty.a.c.a().e(bVar);
                                }
                            } else if (find == FS_ACTION.Landing) {
                                bVar.c = a.g.getString(R.string.gs_rc_signal_lost_landing);
                                if (this.a.Y > 0) {
                                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                                        a = (int) dji.pilot.groundStation.b.a((float) this.a.Y);
                                        bVar.e = a.g.getString(R.string.gs_low_power_go_home) + String.format("%dFT", new Object[]{Integer.valueOf(a)});
                                    } else {
                                        bVar.e = a.g.getString(R.string.gs_low_power_go_home) + String.format("%dM", new Object[]{Integer.valueOf(this.a.Y)});
                                    }
                                }
                                dji.thirdparty.a.c.a().e(bVar);
                            } else if (find == FS_ACTION.Hover) {
                                bVar.c = a.g.getString(R.string.gs_rc_signal_lost_hover);
                                if (this.a.Y > 0) {
                                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                                        a = (int) dji.pilot.groundStation.b.a((float) this.a.Y);
                                        bVar.e = a.g.getString(R.string.gs_low_power_go_home) + String.format("%dFT", new Object[]{Integer.valueOf(a)});
                                    } else {
                                        bVar.e = a.g.getString(R.string.gs_low_power_go_home) + String.format("%dM", new Object[]{Integer.valueOf(this.a.Y)});
                                    }
                                }
                                dji.thirdparty.a.c.a().e(bVar);
                            }
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                }
                if (d()) {
                    DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
                    if (dji.pilot.fpv.d.b.a(i.getInstance().c(), dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen()), DataOsdGetPushCommon.getInstance().getModeChannel())) {
                        a(d.NONE);
                        if (this.I != null && this.I.isShowing()) {
                            l();
                        }
                        if (!(this.h == null || this.h.l() == null)) {
                            this.h.l().g();
                            this.h.l().b(null, 0.0d);
                        }
                    }
                } else if (c() && i.getInstance().c() != ProductType.A2) {
                    Q();
                    if (dataOsdGetPushCommon.getVoltageWarning() == 2 && DataOsdGetPushCommon.getInstance().getModeChannel() == RcModeChannel.CHANNEL_F) {
                        if (!(this.I == null || this.I.c() == 0 || this.I.c() == 25 || !this.I.isShowing())) {
                            l();
                            y();
                        }
                        if (this.r != d.NONE) {
                            a(d.NONE);
                        } else if (!(this.h == null || this.h.l() == null)) {
                            this.h.l().g();
                            this.h.l().b(null, 0.0d);
                        }
                    }
                    if (DataOsdGetPushCommon.getInstance().getModeChannel() == RcModeChannel.CHANNEL_F) {
                        int groundOrSky = dataOsdGetPushCommon.groundOrSky();
                        if (groundOrSky != 2) {
                            l();
                            if (this.r != d.NONE) {
                                a(d.NONE);
                            } else if (!(this.h == null || this.h.l() == null)) {
                                this.h.l().g();
                                this.h.l().b(null, 0.0d);
                            }
                            this.N = groundOrSky;
                        } else if (dataOsdGetPushCommon.groundOrSky() != this.N) {
                            if (!this.P && x()) {
                                if (this.r != d.NONE) {
                                    a(d.NONE);
                                } else if (!(this.h == null || this.h.l() == null)) {
                                    this.h.l().g();
                                    this.h.l().b(null, 0.0d);
                                }
                                this.N = groundOrSky;
                                if (flycState == FLYC_STATE.AutoTakeoff) {
                                    this.j.postDelayed(new Runnable(this) {
                                        final /* synthetic */ a a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            this.a.R();
                                        }
                                    }, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                                } else {
                                    R();
                                }
                            } else {
                                return;
                            }
                        }
                    }
                    if ((flycState == FLYC_STATE.GoHome || flycState == FLYC_STATE.AutoLanding) && DataOsdGetPushHome.getInstance().isMultipleModeOpen() && DataOsdGetPushCommon.getInstance().getModeChannel() == RcModeChannel.CHANNEL_F) {
                        if (this.r != d.NONE) {
                            a(d.NONE);
                        } else if (!(this.h == null || this.h.l() == null)) {
                            this.h.l().g();
                            this.h.l().b(null, 0.0d);
                        }
                        if (!(this.I == null || this.I.c() == 0 || this.I.c() == 24)) {
                            if (this.I.isShowing()) {
                                l();
                            }
                            y();
                        }
                    }
                }
                if (i.getInstance().c() != ProductType.A2) {
                    if (flycState == FLYC_STATE.Atti_CL || flycState == FLYC_STATE.GPS_CL || flycState == FLYC_STATE.GPS_HomeLock || flycState == FLYC_STATE.GPS_HotPoint || flycState == FLYC_STATE.NaviGo || flycState == FLYC_STATE.NaviMissionFollow || flycState == FLYC_STATE.ClickGo || flycState == FLYC_STATE.TERRAIN_TRACKING) {
                        this.O = -1;
                    } else {
                        if (this.I != null && this.I.isShowing() && (this.I.c() == 12 || this.I.c() == 13 || this.I.c() == 22 || this.I.c() == 14 || this.I.c() == 21)) {
                            l();
                        }
                        if (this.r != d.NONE) {
                            long currentTimeMillis = System.currentTimeMillis();
                            if (this.O <= 0) {
                                this.O = currentTimeMillis;
                            } else if (currentTimeMillis - this.O > 500) {
                                a(d.NONE);
                            }
                        }
                    }
                }
                this.o = flycState;
                return;
            }
            if (this.r != d.NONE) {
                a(d.NONE);
            }
            l();
        }
    }

    public void onEventMainThread(final DataFlycGetPushWayPointMissionInfo dataFlycGetPushWayPointMissionInfo) {
        if (ServiceManager.getInstance().isConnected() && c() && this.k) {
            int missionType = dataFlycGetPushWayPointMissionInfo.getMissionType();
            if (dji.pilot.fpv.flightmode.c.getInstance().a() != c$b.SMART) {
                missionType = 0;
            }
            dji.pilot.fpv.view.DJIErrorPopView.b bVar;
            boolean z;
            dji.pilot.fpv.view.DJIErrorPopView.b bVar2;
            switch (missionType) {
                case 0:
                    if (this.r != d.NONE && dataFlycGetPushWayPointMissionInfo.getCurrentStatus() == 1) {
                        l();
                        a(d.NONE);
                        bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                        bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                        bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                        bVar.b = R.string.gs_follow_me_notify_dialog_title;
                        dji.thirdparty.a.c.a().e(bVar);
                    }
                    if (this.I != null && this.I.isShowing()) {
                        if (this.I.c() == 12 || this.I.c() == 13 || this.I.c() == 22 || this.I.c() == 14 || this.I.c() == 21) {
                            l();
                            a(d.NONE);
                            return;
                        }
                        return;
                    }
                    return;
                case 1:
                    if (!(this.P || this.r == d.WP_AUTO)) {
                        this.P = true;
                        this.j.postDelayed(new Runnable(this) {
                            final /* synthetic */ a a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                FLYC_STATE flycState = DataOsdGetPushCommon.getInstance().getFlycState();
                                if (flycState == FLYC_STATE.NaviGo || flycState == FLYC_STATE.ClickGo) {
                                    if (this.a.r != d.WP_AUTO) {
                                        this.a.a(d.WP_AUTO);
                                        this.a.l();
                                        if (this.a.q != null) {
                                            this.a.q.onClick(null);
                                        }
                                    }
                                    this.a.P = false;
                                    return;
                                }
                                this.a.P = false;
                            }
                        }, 500);
                    }
                    if (this.r == d.WP_AUTO) {
                        if (dataFlycGetPushWayPointMissionInfo.getTargetWayPoint() == 0) {
                            if (this.Q != 0) {
                                this.Q = 0;
                                bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                                bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                                bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                                bVar.b = R.string.gs_way_point_going_to_first_point;
                                dji.thirdparty.a.c.a().e(bVar);
                            }
                        } else if (dataFlycGetPushWayPointMissionInfo.getTargetWayPoint() == 1 && this.Q != 1) {
                            this.Q = 1;
                            bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                            bVar.b = R.string.gs_way_point_mession_begin;
                            dji.thirdparty.a.c.a().e(bVar);
                        }
                        z = this.ap;
                        if (dataFlycGetPushWayPointMissionInfo.getWayPointStatus() == 2) {
                            this.ap = true;
                        } else {
                            this.ap = false;
                        }
                        if (this.ap != z) {
                            bVar2 = new dji.pilot.fpv.view.DJIErrorPopView.b();
                            bVar2.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                            bVar2.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                            bVar2.b = this.ap ? R.string.gs_wp_paused : R.string.gs_wp_resume;
                            dji.thirdparty.a.c.a().e(bVar2);
                            return;
                        }
                        return;
                    }
                    return;
                case 2:
                    if (!(this.r == d.POI_AUTO || this.P)) {
                        this.P = true;
                        this.j.postDelayed(new Runnable(this) {
                            final /* synthetic */ a b;

                            public void run() {
                                if (DataOsdGetPushCommon.getInstance().getFlycState() != FLYC_STATE.GPS_HotPoint) {
                                    this.b.P = false;
                                } else if (this.b.r != d.POI_AUTO) {
                                    final DataFlycHotPointMissionDownload instance = DataFlycHotPointMissionDownload.getInstance();
                                    instance.start(new dji.midware.e.d(this) {
                                        final /* synthetic */ AnonymousClass3 b;

                                        public void onSuccess(Object obj) {
                                            if (instance.getResult() == 0) {
                                                this.b.b.j.post(new Runnable(this) {
                                                    final /* synthetic */ AnonymousClass1 a;

                                                    {
                                                        this.a = r1;
                                                    }

                                                    public void run() {
                                                        this.a.b.b.a((instance.getHotPointLatitude() * 180.0d) / 3.141592653589793d, (instance.getHotPointLongitude() * 180.0d) / 3.141592653589793d);
                                                        this.a.b.b.a(instance.getHotPointAttitude());
                                                        this.a.b.b.b(instance.getHotPointRadius());
                                                        this.a.b.b.ao = instance.getHotPointClockWise();
                                                        this.a.b.b.am = instance.getHotPointAngleSpeed();
                                                        if (this.a.b.b.ao == ROTATION_DIR.Clockwise) {
                                                            this.a.b.b.am = -this.a.b.b.am;
                                                        }
                                                        this.a.b.b.al = (double) (0.01f * ((float) dataFlycGetPushWayPointMissionInfo.getHotPointRadius()));
                                                        this.a.b.b.a(d.POI_AUTO);
                                                        this.a.b.b.l();
                                                        if (this.a.b.b.q != null) {
                                                            this.a.b.b.P = false;
                                                        } else {
                                                            this.a.b.b.P = false;
                                                        }
                                                    }
                                                });
                                            }
                                        }

                                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                                            this.b.b.P = false;
                                        }
                                    });
                                } else {
                                    this.b.P = false;
                                }
                            }
                        }, 500);
                    }
                    if (this.r == d.POI_AUTO) {
                        z = this.an;
                        if (dataFlycGetPushWayPointMissionInfo.getHotPointMissionStatus() == 2) {
                            this.an = true;
                        } else {
                            this.an = false;
                        }
                        if (this.an != z) {
                            bVar2 = new dji.pilot.fpv.view.DJIErrorPopView.b();
                            bVar2.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                            bVar2.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                            bVar2.b = this.an ? R.string.gs_poi_paused : R.string.gs_poi_resume;
                            dji.thirdparty.a.c.a().e(bVar2);
                            return;
                        }
                        return;
                    }
                    return;
                case 3:
                    if (!(this.r == d.FOLLOW_ME || this.P)) {
                        this.P = true;
                        this.j.postDelayed(new Runnable(this) {
                            final /* synthetic */ a a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (DataOsdGetPushCommon.getInstance().getFlycState() != FLYC_STATE.NaviMissionFollow) {
                                    this.a.P = false;
                                    return;
                                }
                                if (this.a.r != d.FOLLOW_ME) {
                                    this.a.a(d.FOLLOW_ME);
                                    this.a.l();
                                    if (this.a.q != null) {
                                        this.a.q.onClick(null);
                                    }
                                }
                                this.a.P = false;
                            }
                        }, 500);
                    }
                    if (dataFlycGetPushWayPointMissionInfo.getFollowMeStatus() == 2 && !this.R) {
                        this.R = true;
                        d(new dji.midware.e.d(this) {
                            final /* synthetic */ a a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                this.a.j.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass5 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.l();
                                        this.a.a.a(d.NONE);
                                        dji.pilot.groundStation.b.a aVar = new dji.pilot.groundStation.b.a(a.g, dji.pilot.fpv.model.b.a(a.g, R.dimen.o6), dji.pilot.fpv.model.b.a(a.g, R.dimen.o6), false);
                                        aVar.d(R.drawable.gs_warning_icon);
                                        aVar.setTitle(R.string.gs_follow_me_notify_dialog_title);
                                        aVar.a((int) R.string.gs_follow_me_notify_dialog_desc);
                                        aVar.c(R.string.gs_follow_me_notify_dialog_ok);
                                        aVar.b();
                                        aVar.show();
                                    }
                                });
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.a.R = false;
                            }
                        });
                        return;
                    }
                    return;
                case 4:
                    if (this.r != d.HOME_LOCK && this.r != d.COURSE_LOCK && !this.P) {
                        this.P = true;
                        this.j.postDelayed(new Runnable(this) {
                            final /* synthetic */ a a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                FLYC_STATE flycState = DataOsdGetPushCommon.getInstance().getFlycState();
                                if (flycState == FLYC_STATE.Atti_CL || flycState == FLYC_STATE.GPS_CL || flycState == FLYC_STATE.GPS_HomeLock) {
                                    if (!(this.a.r == d.HOME_LOCK || this.a.r == d.COURSE_LOCK)) {
                                        if (DataOsdGetPushHome.getInstance().getIOCMode() == DataFlycGetIoc.MODE.CourseLock) {
                                            this.a.a(d.COURSE_LOCK);
                                            this.a.af = (float) (((double) ((((float) DataOsdGetPushHome.getInstance().getCourseLockAngle()) * 180.0f) / 1000.0f)) / 3.141592653589793d);
                                            this.a.l();
                                            if (this.a.q != null) {
                                                this.a.q.onClick(null);
                                            }
                                        } else if (DataOsdGetPushHome.getInstance().getIOCMode() == DataFlycGetIoc.MODE.HomeLock) {
                                            this.a.a(d.HOME_LOCK);
                                            this.a.l();
                                            if (this.a.q != null) {
                                                this.a.q.onClick(null);
                                            }
                                        }
                                    }
                                    this.a.P = false;
                                    return;
                                }
                                this.a.P = false;
                            }
                        }, 500);
                        return;
                    }
                    return;
                case 5:
                    if (this.r != d.TERRAIN_TRACKING && !this.P) {
                        a(d.TERRAIN_TRACKING);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushWayPointMissionCurrentEvent dataFlycGetPushWayPointMissionCurrentEvent) {
        switch (dataFlycGetPushWayPointMissionCurrentEvent.getEventType()) {
            case 1:
                dji.pilot.fpv.view.DJIErrorPopView.b bVar;
                if (this.as == FINISH_ACTION.GOHOME) {
                    bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                    bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                    bVar.b = R.string.gs_way_point_finish_go_home_notice;
                    dji.thirdparty.a.c.a().e(bVar);
                    return;
                }
                bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                bVar.b = R.string.gs_way_point_finish_no_limits_notice;
                dji.thirdparty.a.c.a().e(bVar);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            l();
        }
    }

    private int P() {
        if (this.S == null) {
            this.S = ((WindowManager) g.getSystemService("window")).getDefaultDisplay();
        }
        return this.S.getRotation();
    }

    public void onEventBackgroundThread(float[] fArr) {
        boolean z = false;
        if (this.k) {
            this.U = (int) Math.toDegrees((double) fArr[0]);
            if (this.U - this.T > 2 || this.T - this.U > 2) {
                z = true;
            }
            this.W = z;
            if (this.W) {
                this.T = this.U;
                this.V = P();
                if (this.V == 3) {
                    this.U += 180;
                }
                this.X = this.U + 90;
            }
        }
    }

    public int w() {
        return this.X;
    }

    public void b(int i) {
        this.Y = i;
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        this.Y = dataOsdGetPushHome.getGoHomeHeight();
        if (d()) {
            if (dji.pilot.fpv.d.b.a(i.getInstance().c(), dji.pilot.fpv.d.b.a(dataOsdGetPushHome.isBeginnerMode(), dataOsdGetPushHome.isMultipleModeOpen()), DataOsdGetPushCommon.getInstance().getModeChannel())) {
                a(d.NONE);
                if (this.I != null && this.I.isShowing()) {
                    l();
                }
                if (this.h != null && this.h.l() != null) {
                    this.h.l().g();
                    this.h.l().b(null, 0.0d);
                }
            }
        }
    }

    public boolean x() {
        if (g instanceof DJIPreviewActivity) {
            return ((DJIPreviewActivity) g).g();
        }
        if (g instanceof DJIPreviewActivityLitchi) {
            return ((DJIPreviewActivityLitchi) g).m();
        }
        if (g instanceof DJIPreviewActivityGrape) {
            return ((DJIPreviewActivityGrape) g).f();
        }
        if (g instanceof DJIBaseNewPreviewActivity) {
            return ((DJIBaseNewPreviewActivity) g).E();
        }
        return false;
    }

    private void Q() {
        if (this.k) {
            RcModeChannel modeChannel = DataOsdGetPushCommon.getInstance().getModeChannel();
            if (this.Z != modeChannel) {
                this.Z = modeChannel;
                if (modeChannel != RcModeChannel.CHANNEL_F) {
                    this.P = false;
                    this.L = false;
                    if (this.r != d.NONE) {
                        a(d.NONE);
                    }
                    c(this.r);
                    this.h.l().b(null, 0.0d);
                } else if (!this.P) {
                    if (x() || this.I == null) {
                        if (this.r != d.NONE) {
                            a(d.NONE);
                        }
                        if (DataOsdGetPushCommon.getInstance().groundOrSky() != 2) {
                            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                            bVar.g = f.a;
                            bVar.b = R.string.gs_error_aircraft_not_in_the_air;
                            dji.thirdparty.a.c.a().e(bVar);
                        } else {
                            R();
                        }
                    } else {
                        this.L = true;
                        return;
                    }
                } else {
                    return;
                }
            }
            if (modeChannel != RcModeChannel.CHANNEL_F) {
                l();
                M();
            } else if (x() && this.L) {
                R();
            }
        }
    }

    private void R() {
        if (!ServiceManager.getInstance().isConnected()) {
            return;
        }
        if (DataOsdGetPushHome.getInstance().isMultipleModeOpen() || d()) {
            S();
        } else if (this.d == null || !this.d.isShowing()) {
            this.j.postDelayed(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (!DataOsdGetPushHome.getInstance().isMultipleModeOpen()) {
                        if ((this.a.d == null || !this.a.d.isShowing()) && ServiceManager.getInstance().isConnected() && DataOsdGetPushCommon.getInstance().getModeChannel() == RcModeChannel.CHANNEL_F && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                            this.a.d = new dji.pilot.groundStation.b.a(a.g, dji.pilot.fpv.model.b.a(a.g, R.dimen.o6), dji.pilot.fpv.model.b.a(a.g, R.dimen.o6), false);
                            this.a.d.setTitle("");
                            this.a.d.d(-1);
                            this.a.d.a((int) R.string.gs_enable_mult_flight_mode_dialog_title);
                            this.a.d.b((int) R.string.gs_enable_mult_flight_mode_dialog_left_btn);
                            this.a.d.c(R.string.gs_enable_mult_flight_mode_dialog_right_btn);
                            this.a.d.b(new OnClickListener(this) {
                                final /* synthetic */ AnonymousClass7 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(View view) {
                                    new DataFlycSetParams().a(this.a.a.l.name, Integer.valueOf(1)).start(new dji.midware.e.d(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onSuccess(Object obj) {
                                            int result = DataFlycNavigationSwitch.getInstance().getResult();
                                            if (result == 0) {
                                                this.a.a.a.j.post(new Runnable(this) {
                                                    final /* synthetic */ AnonymousClass1 a;

                                                    {
                                                        this.a = r1;
                                                    }

                                                    public void run() {
                                                        this.a.a.a.a.S();
                                                    }
                                                });
                                            } else {
                                                this.a.a.a.a((int) R.string.gs_open_ground_station_failed, dji.pilot.groundStation.a.a(a.g, result), false);
                                            }
                                        }

                                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                                            this.a.a.a.a((int) R.string.gs_open_ground_station_failed, aVar.toString(), false);
                                        }
                                    });
                                }
                            });
                            this.a.d.show();
                        }
                    }
                }
            }, 1000);
        }
    }

    public void y() {
        this.j.post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                if (dji.pilot.fpv.d.b.p(i.getInstance().c())) {
                    this.a.a((int) R.layout.fpv_flightmode_view_fullscreen, 102);
                } else {
                    this.a.a((int) R.layout.gs_main_view, 0);
                }
            }
        });
    }

    public void a(View view) {
        if (dji.pilot.fpv.d.b.p(i.getInstance().c())) {
            ((DJIStageView) view.getParent()).createStageView(R.layout.fpv_flightmode_view_fullscreen, 102, false);
        } else {
            ((DJIStageView) view.getParent()).createStageView(R.layout.gs_main_view, 0, false);
        }
    }

    private void S() {
        if (!this.P) {
            if (this.e == null || !this.e.isShowing()) {
                final int i = this.u.getInt(this.w, 0);
                if ((i & 128) == 128 || d()) {
                    y();
                    return;
                }
                this.e = new dji.pilot.groundStation.b.c(g);
                this.e.a(false, new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        this.b.u.edit().putInt(this.b.w, i | 128).commit();
                        this.b.y();
                    }
                });
                this.e.show();
            }
        }
    }

    public void a(d dVar) {
        if (ServiceManager.getInstance().isConnected()) {
            this.r = dVar;
            if (dVar != d.NONE) {
                if (this.ab != null && this.ab.isShowing()) {
                    this.ab.dismiss();
                    this.ab = null;
                }
                if (this.Y > 0) {
                    dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                    bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                    bVar.g = f.a;
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        int a = (int) dji.pilot.groundStation.b.a((float) this.Y);
                        bVar.c = g.getString(R.string.return_to_home_attitude) + String.format("%dFT", new Object[]{Integer.valueOf(a)});
                    } else {
                        bVar.c = g.getString(R.string.return_to_home_attitude) + String.format("%dM", new Object[]{Integer.valueOf(this.Y)});
                    }
                    dji.thirdparty.a.c.a().e(bVar);
                }
                dji.thirdparty.a.c.a().e(c.DisableChangeModes);
                b(dVar);
            } else {
                if (!(this.h == null || this.h.l() == null)) {
                    this.h.l().g();
                    this.h.l().b(null, 0.0d);
                }
                dji.thirdparty.a.c.a().e(c.EnableChangeModes);
            }
            dji.thirdparty.a.c.a().e(this.r);
        }
    }

    public d z() {
        return this.r;
    }

    private void b(d dVar) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.aa >= OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
            if (d.FOLLOW_ME == dVar) {
                B();
            }
            if (!d()) {
                this.aa = currentTimeMillis;
                this.n.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                this.n.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                this.n.g = f.a;
                switch (dVar) {
                    case COURSE_LOCK:
                        this.n.b = R.string.gs_course_lock_notify_msg_title;
                        this.n.d = R.string.gs_course_lock_notify_msg_desc;
                        dji.thirdparty.a.c.a().e(this.n);
                        return;
                    case FOLLOW_ME:
                        this.n.b = R.string.gs_follow_me_notify_msg_title;
                        this.n.d = R.string.gs_follow_me_notify_msg_desc;
                        dji.thirdparty.a.c.a().e(this.n);
                        return;
                    case HOME_LOCK:
                        this.n.b = R.string.gs_home_lock_notify_msg_title;
                        this.n.d = R.string.gs_home_lock_notify_msg_desc;
                        dji.thirdparty.a.c.a().e(this.n);
                        return;
                    case POI_AUTO:
                        this.n.b = R.string.gs_point_of_insterest_notify_automatic_fly_msg_title;
                        this.n.d = R.string.gs_point_of_insterest_notify_automatic_fly_msg_desc;
                        dji.thirdparty.a.c.a().e(this.n);
                        return;
                    case WP_AUTO:
                        this.n.b = R.string.gs_way_point_auto_notify_msg_title;
                        this.n.d = R.string.gs_way_point_auto_notify_msg_desc;
                        dji.thirdparty.a.c.a().e(this.n);
                        return;
                    case TERRAIN_TRACKING:
                        this.n.b = R.string.gs_terrain_tracking_auto_notify_msg_title;
                        this.n.d = R.string.gs_terrain_tracking_auto_notify_msg_desc;
                        dji.thirdparty.a.c.a().e(this.n);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void c(d dVar) {
        this.j.post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.h != null && this.a.h.l() != null) {
                    this.a.h.l().g();
                    this.a.h.l().b(null, 0.0d);
                }
            }
        });
    }

    public int A() {
        FLYC_STATE flycState = DataOsdGetPushCommon.getInstance().getFlycState();
        if (flycState == FLYC_STATE.AutoLanding || flycState == FLYC_STATE.GoHome) {
            return -1;
        }
        if (dji.pilot.fpv.d.b.q(i.getInstance().c())) {
            switch (this.r) {
                case COURSE_LOCK:
                    return R.string.ctrl_mode_cl;
                case FOLLOW_ME:
                    return R.string.ctrl_mode_follow_me;
                case HOME_LOCK:
                    return R.string.ctrl_mode_hl;
                case POI_AUTO:
                    return R.string.ctrl_mode_poi;
                case WP_AUTO:
                    return R.string.ctrl_mode_way_point;
                default:
                    return -1;
            }
        }
        switch (this.r) {
            case COURSE_LOCK:
                return R.string.ctrl_mode_fcl;
            case FOLLOW_ME:
                return R.string.ctrl_mode_ffollow_me;
            case HOME_LOCK:
                return R.string.ctrl_mode_fhl;
            case POI_AUTO:
                return R.string.ctrl_mode_fpoi;
            case WP_AUTO:
                return R.string.ctrl_mode_fway_point;
            default:
                return R.string.ctrl_mode_fexit;
        }
    }

    public void a(int i, dji.midware.data.config.P3.a aVar, boolean z) {
        a(i, aVar.toString(), z);
    }

    public void a(int i, int i2, boolean z) {
        if (g != null) {
            a(i, g.getResources().getString(i2), z);
        }
    }

    public void a(final int i, final String str, final boolean z) {
        if ((this.ab == null || !this.ab.isShowing()) && this.r == d.NONE) {
            this.j.post(new Runnable(this) {
                final /* synthetic */ a d;

                public void run() {
                    if (i == R.string.gs_way_point_send_command_failed) {
                        this.d.ab = new dji.pilot.groundStation.b.a(a.g, dji.pilot.fpv.model.b.a(a.g, R.dimen.o6), dji.pilot.fpv.model.b.a(a.g, R.dimen.o5), false);
                        this.d.ab.setTitle(i);
                        this.d.ab.d(R.drawable.gs_exec_command_failed_icon);
                        this.d.ab.a(str);
                        this.d.ab.c(R.string.gs_way_point_upload_failed_retry);
                        this.d.ab.b((int) R.string.gs_wait_dialog_cancel);
                        this.d.ab.b(new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass11 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                this.a.d.a((int) R.layout.gs_way_point_wait_upload_mission_view, 11);
                            }
                        });
                        this.d.ab.a(new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass11 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                this.a.d.h.l().g();
                                this.a.d.y();
                            }
                        });
                        this.d.ab.show();
                        return;
                    }
                    this.d.ab = new dji.pilot.groundStation.b.a(a.g, dji.pilot.fpv.model.b.a(a.g, R.dimen.o6), dji.pilot.fpv.model.b.a(a.g, R.dimen.o5), false);
                    this.d.ab.setTitle(i);
                    this.d.ab.d(R.drawable.gs_exec_command_failed_icon);
                    this.d.ab.a(str);
                    this.d.ab.c(R.string.gs_failed_dialog_ok);
                    this.d.ab.b();
                    this.d.ab.b(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass11 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            if (z) {
                                this.a.d.y();
                            }
                        }
                    });
                    this.d.ab.show();
                }
            });
        }
    }

    public DJIWPCollectionItem c(int i) {
        if (i >= this.t.size() || i < 0) {
            return null;
        }
        return (DJIWPCollectionItem) this.t.get(i);
    }

    public void B() {
        this.ad.post(this.ae);
    }

    private void T() {
        d(new dji.midware.e.d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.j.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass14 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.l();
                        this.a.a.a(d.NONE);
                        this.a.a.y();
                    }
                });
                dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
                bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                bVar.b = R.string.gs_follow_me_device_gps_signal_weak;
                dji.thirdparty.a.c.a().e(bVar);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (d.FOLLOW_ME == this.a.r) {
                    this.a.T();
                    return;
                }
                this.a.j.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass14 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.l();
                        this.a.a.a(d.NONE);
                        this.a.a.y();
                    }
                });
                dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
                bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                bVar.b = R.string.gs_follow_me_device_gps_signal_weak;
                dji.thirdparty.a.c.a().e(bVar);
            }
        });
    }

    public void a(final boolean z, final dji.midware.e.d dVar) {
        DataFlycNavigationSwitch instance = DataFlycNavigationSwitch.getInstance();
        if (z) {
            instance.setCommand(GS_COMMAND.OPEN_GROUND_STATION);
        } else {
            instance.setCommand(GS_COMMAND.CLOSE_GROUND_STATION);
        }
        instance.start(new dji.midware.e.d(this) {
            final /* synthetic */ a c;

            public void onSuccess(Object obj) {
                if (dVar != null) {
                    dVar.onSuccess(obj);
                }
                if (!z) {
                    this.c.j.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass15 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.a(d.NONE);
                        }
                    });
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "openGroundStation timeout", false, true);
                if (dVar != null) {
                    dVar.onFailure(aVar);
                }
            }
        });
    }

    public void a(boolean z, int i, dji.midware.e.d dVar) {
        new DataFlycNavigationSwitch().setCommand(z ? GS_COMMAND.OPEN_GROUND_STATION : GS_COMMAND.CLOSE_GROUND_STATION).setRetryTimes(i).start(dVar);
    }

    public void a(final IOCType iOCType, final dji.midware.e.d dVar) {
        if (i.getInstance().c() == ProductType.A2) {
            DataFlycStartIoc.getInstance().setMode(iOCType).start(dVar);
        } else {
            a(true, new dji.midware.e.d(this) {
                final /* synthetic */ a c;

                public void onSuccess(Object obj) {
                    if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                        DataFlycStartIoc.getInstance().setMode(iOCType).start(dVar);
                    } else if (dVar != null) {
                        dVar.onSuccess(obj);
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (dVar != null) {
                        DJILogHelper.getInstance().LOGD("", "startIoc timeout", false, true);
                        dVar.onFailure(aVar);
                    }
                }
            });
        }
    }

    public void a(dji.midware.e.d dVar) {
        DataFlycStopNoeMission.getInstance().start(dVar);
    }

    public void b(final dji.midware.e.d dVar) {
        a(true, new dji.midware.e.d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                    DataFlycStartNoeMission.getInstance().a(10.0f).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass17 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            if (DataFlycStartNoeMission.getInstance().a() == 0) {
                                if (dVar != null) {
                                    dVar.onSuccess(obj);
                                }
                            } else if (dVar != null) {
                                dVar.onFailure(dji.midware.data.config.P3.a.i);
                            }
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            if (dVar != null) {
                                dVar.onFailure(aVar);
                            }
                        }
                    });
                } else if (dVar != null) {
                    dVar.onFailure(dji.midware.data.config.P3.a.i);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void c(dji.midware.e.d dVar) {
        if (i.getInstance().c() == ProductType.A2) {
            DataFlycStopIoc.getInstance().start(dVar);
        } else {
            a(false, dVar);
        }
    }

    public void a(float f) {
        this.af = f;
    }

    public float C() {
        return this.af;
    }

    public void a(final dji.midware.e.d dVar, final dji.gs.e.b bVar) {
        a(true, new dji.midware.e.d(this) {
            final /* synthetic */ a c;

            public void onSuccess(Object obj) {
                if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                    DataFlycStartFollowMeWithInfo instance = DataFlycStartFollowMeWithInfo.getInstance();
                    instance.setFollowMode(FOLLOWMODE.Relative_mode);
                    instance.setYawMode(YAWMODE.Use_Remote_Controll);
                    instance.setAltitude((short) 0);
                    instance.setHeading((short) 0);
                    instance.setSensitivity(0);
                    if (bVar != null) {
                        this.c.R = false;
                        instance.setUserLatitude(this.c.c(bVar.b + this.c.ag));
                        instance.setUserLongitude(this.c.c(bVar.c + this.c.ah));
                        instance.start(dVar);
                        return;
                    }
                    this.c.a((int) R.string.gs_follow_me_send_command_failed, a.g.getString(R.string.gs_follow_me_can_not_get_user_location), true);
                } else if (dVar != null) {
                    dVar.onSuccess(obj);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (dVar != null) {
                    dVar.onFailure(aVar);
                }
            }
        });
    }

    public void a(dji.midware.e.d dVar, double d, double d2) {
        DataFlycSendGpsInfo instance = DataFlycSendGpsInfo.getInstance();
        instance.setLatitude(c(this.ag + d));
        instance.setLongitude(c(this.ah + d2));
        instance.setAltitude((short) 0);
        instance.setHeading((short) 0);
        instance.start(dVar);
    }

    public void d(dji.midware.e.d dVar) {
        a(false, dVar);
    }

    public void a(double d, double d2) {
        this.ai = d;
        this.aj = d2;
    }

    public void a(double d) {
        this.ak = d;
    }

    public void b(double d) {
        this.al = d;
    }

    public double D() {
        return this.ai;
    }

    public double E() {
        return this.aj;
    }

    public double F() {
        return this.al;
    }

    public double G() {
        return this.ak;
    }

    public float H() {
        return this.am;
    }

    public void b(float f) {
        this.am = f;
    }

    public boolean I() {
        return this.an;
    }

    public void a(dji.midware.e.d dVar, CAMERA_DIR camera_dir, ROTATION_DIR rotation_dir, TO_START_POINT_MODE to_start_point_mode, float f) {
        final ROTATION_DIR rotation_dir2 = rotation_dir;
        final float f2 = f;
        final CAMERA_DIR camera_dir2 = camera_dir;
        final TO_START_POINT_MODE to_start_point_mode2 = to_start_point_mode;
        final dji.midware.e.d dVar2 = dVar;
        a(true, new dji.midware.e.d(this) {
            final /* synthetic */ a f;

            public void onSuccess(Object obj) {
                if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                    this.f.ao = rotation_dir2;
                    if (this.f.ao == ROTATION_DIR.Clockwise) {
                        this.f.am = -f2;
                    } else {
                        this.f.am = f2;
                    }
                    DataFlycStartHotPointMissionWithInfo instance = DataFlycStartHotPointMissionWithInfo.getInstance();
                    instance.setCameraDir(camera_dir2);
                    instance.setRotationDir(this.f.ao);
                    instance.setToStartPointMode(to_start_point_mode2);
                    instance.setVelocity(f2);
                    instance.setAltitude(this.f.ak);
                    instance.setLatitude(this.f.c(this.f.ai));
                    instance.setLongitude(this.f.c(this.f.aj));
                    instance.setRadious(this.f.al);
                    instance.start(dVar2);
                } else if (dVar2 != null) {
                    dVar2.onSuccess(obj);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (dVar2 != null) {
                    dVar2.onFailure(aVar);
                }
            }
        });
    }

    public void a(dji.midware.e.d dVar, boolean z) {
        DataFlycHotPointMissionSwitch instance = DataFlycHotPointMissionSwitch.getInstance();
        if (z) {
            instance.setSwitch(HOTPOINTMISSIONSWITCH.PAUSE);
        } else {
            instance.setSwitch(HOTPOINTMISSIONSWITCH.RESUME);
        }
        instance.start(dVar);
    }

    public void a(dji.midware.e.d dVar, ROTATION_DIR rotation_dir, float f) {
        this.ao = rotation_dir;
        if (this.ao == ROTATION_DIR.Clockwise) {
            this.am = -f;
        } else {
            this.am = f;
        }
        DataFlycHotPointResetParams instance = DataFlycHotPointResetParams.getInstance();
        instance.setRotationDir(this.ao);
        instance.setVelocity(f);
        instance.start(dVar);
    }

    public void e(dji.midware.e.d dVar) {
        DataFlycHotPointResetCamera.getInstance().start(dVar);
    }

    public void f(dji.midware.e.d dVar) {
        a(false, dVar);
    }

    public void c(float f) {
        this.ar = f;
    }

    public float J() {
        return this.ar;
    }

    public void d(boolean z) {
        this.aw = z;
    }

    public boolean K() {
        return this.aw;
    }

    public void a(FINISH_ACTION finish_action) {
        this.as = finish_action;
    }

    public DJIWPCollectionItem L() {
        return this.aq;
    }

    public void M() {
        this.aq = new DJIWPCollectionItem();
    }

    public void c(DJIWPCollectionItem dJIWPCollectionItem) {
        this.aq = dJIWPCollectionItem;
    }

    public void a(YAW_MODE yaw_mode) {
        this.at = yaw_mode;
    }

    public boolean N() {
        return this.ap;
    }

    public synchronized void a(final DJIGSWayPointWaitUploadMissionView dJIGSWayPointWaitUploadMissionView) {
        a(true, new dji.midware.e.d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                    dJIGSWayPointWaitUploadMissionView.show();
                    this.b.av = false;
                    dJIGSWayPointWaitUploadMissionView.setOnCancelListener(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass20 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.b.av = true;
                        }
                    });
                    final DataFlycUploadWayPointMissionMsg instance = DataFlycUploadWayPointMissionMsg.getInstance();
                    final DataFlycUploadWayPointMsgByIndex instance2 = DataFlycUploadWayPointMsgByIndex.getInstance();
                    final DataFlycWayPointMissionSwitch instance3 = DataFlycWayPointMissionSwitch.getInstance();
                    final DJIWPCollectionItem y = this.b.aq;
                    final Semaphore semaphore = new Semaphore(0);
                    instance.setWayPointCount(y.getPoints().size());
                    instance.setCmdSpeed(7.0f);
                    instance.setIdleSpeed(this.b.ar);
                    instance.setFinishAction(this.b.as);
                    instance.setYawMode(this.b.at);
                    instance.setTraceMOde(TRACE_MODE.SMOOTH_PATH);
                    instance.setActionOnRCLost(ACTION_ON_RC_LOST.CONTINUE_WP);
                    instance.seGimbalPitchMode(GIMBAL_PITCH_MODE.PITCH_FREE);
                    instance.setRepeatNum(1);
                    this.b.au = false;
                    instance.start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass20 f;

                        public void onSuccess(Object obj) {
                            int result = instance.getResult();
                            if (result == 0) {
                                this.f.b.ax = 0;
                                int i = 0;
                                while (i < y.getPoints().size()) {
                                    if (this.f.b.av) {
                                        dJIGSWayPointWaitUploadMissionView.dismiss(false, true);
                                        return;
                                    }
                                    DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) y.getPoints().get(i);
                                    instance2.setIndex(i);
                                    instance2.setLatitude(this.f.b.c(dJIWPCollectionItem$WayPoint.getLat()));
                                    instance2.setLongtitude(this.f.b.c(dJIWPCollectionItem$WayPoint.getLng()));
                                    instance2.setAltitude((float) dJIWPCollectionItem$WayPoint.getHeight());
                                    instance2.setDampingDis((float) this.f.b.a(y.getPoints(), i));
                                    instance2.setTgtYaw((short) dJIWPCollectionItem$WayPoint.getCraftYaw());
                                    if (i < y.getPoints().size() - 1) {
                                        int craftYaw = dJIWPCollectionItem$WayPoint.getCraftYaw() - ((DJIWPCollectionItem$WayPoint) y.getPoints().get(i + 1)).getCraftYaw();
                                        if ((craftYaw > 360 || craftYaw < 180) && (craftYaw < -180 || craftYaw > 0)) {
                                            instance2.setTurnMode(TURNMODE.ANTI_CLOSEWISE);
                                        } else {
                                            instance2.setTurnMode(TURNMODE.CLOCKWISE);
                                        }
                                    } else {
                                        instance2.setTurnMode(TURNMODE.CLOCKWISE);
                                    }
                                    instance2.setActionTimeTimit((short) 999);
                                    instance2.setHasAction(false);
                                    instance2.setActionNum(2);
                                    instance2.setActionRepeat(1);
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(ACTION.WP_ACTION_CRAFT_YAW);
                                    arrayList.add(ACTION.WP_ACTION_GIMBAL_YAW);
                                    instance2.setActionList(arrayList);
                                    arrayList = new ArrayList();
                                    arrayList.add(Integer.valueOf(dJIWPCollectionItem$WayPoint.getCraftYaw()));
                                    arrayList.add(Integer.valueOf(dJIWPCollectionItem$WayPoint.getGimbalYaw()));
                                    instance2.setParamsList(arrayList);
                                    instance2.start(new dji.midware.e.d(this) {
                                        final /* synthetic */ AnonymousClass2 b;

                                        public void onSuccess(Object obj) {
                                            if (instance2.getResult() == 0) {
                                                dJIGSWayPointWaitUploadMissionView.setProgress((i * 100) / y.getPoints().size());
                                                this.b.f.b.ax = 0;
                                            } else {
                                                this.b.f.b.au = true;
                                                this.b.f.b.ax = 10;
                                            }
                                            semaphore.release();
                                        }

                                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                                            this.b.f.b.au = true;
                                            semaphore.release();
                                        }
                                    });
                                    try {
                                        semaphore.acquire();
                                    } catch (InterruptedException e) {
                                    }
                                    if (this.f.b.au) {
                                        this.f.b.ax = this.f.b.ax + 1;
                                        if (this.f.b.ax >= 10) {
                                            break;
                                        }
                                        this.f.b.au = false;
                                        i--;
                                    }
                                    i++;
                                }
                                if (this.f.b.av) {
                                    dJIGSWayPointWaitUploadMissionView.dismiss(false, true);
                                    return;
                                } else if (this.f.b.au) {
                                    dJIGSWayPointWaitUploadMissionView.dismiss(false, false);
                                    this.f.b.a((int) R.string.gs_way_point_send_command_failed, "", true);
                                    return;
                                } else {
                                    this.f.b.ax = 0;
                                    do {
                                        instance3.setCMD(CMD.START);
                                        instance3.start(new dji.midware.e.d(this) {
                                            final /* synthetic */ AnonymousClass2 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void onSuccess(Object obj) {
                                                int result = instance3.getResult();
                                                if (result == 0) {
                                                    this.a.f.b.a(d.WP_AUTO);
                                                    this.a.f.b.Q = -1;
                                                    dJIGSWayPointWaitUploadMissionView.dismiss(true, false);
                                                } else {
                                                    dJIGSWayPointWaitUploadMissionView.dismiss(false, false);
                                                    this.a.f.b.a((int) R.string.gs_way_point_send_command_failed, dji.pilot.groundStation.a.a(a.g, result), true);
                                                }
                                                this.a.f.b.au = false;
                                                semaphore.release();
                                            }

                                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                                if (this.a.f.b.ax = this.a.f.b.ax + 1 < 10) {
                                                    this.a.f.b.au = true;
                                                } else {
                                                    this.a.f.b.au = false;
                                                    dJIGSWayPointWaitUploadMissionView.dismiss(false, false);
                                                    this.a.f.b.a((int) R.string.gs_way_point_send_command_failed, aVar.toString(), true);
                                                }
                                                semaphore.release();
                                            }
                                        });
                                        try {
                                            semaphore.acquire();
                                        } catch (InterruptedException e2) {
                                        }
                                    } while (this.f.b.au);
                                    return;
                                }
                            }
                            dJIGSWayPointWaitUploadMissionView.dismiss(false, false);
                            this.f.b.a((int) R.string.gs_way_point_send_command_failed, dji.pilot.groundStation.a.a(a.g, result), true);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            dJIGSWayPointWaitUploadMissionView.dismiss(false, false);
                            this.f.b.a((int) R.string.gs_way_point_send_command_failed, aVar.toString(), true);
                        }
                    });
                    return;
                }
                this.b.a((int) R.string.gs_way_point_send_command_failed, dji.pilot.groundStation.a.a(a.g, DataFlycNavigationSwitch.getInstance().getResult()), true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.a((int) R.string.gs_way_point_send_command_failed, aVar.toString(), true);
            }
        });
    }

    private double c(double d) {
        return (3.141592653589793d * d) / 180.0d;
    }

    private double a(List<DJIWPCollectionItem$WayPoint> list, int i) {
        if (i <= 0 || i >= list.size() - 1) {
            return 2.0d;
        }
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) list.get(i - 1);
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint2 = (DJIWPCollectionItem$WayPoint) list.get(i);
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint3 = (DJIWPCollectionItem$WayPoint) list.get(i + 1);
        double a = dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng(), dJIWPCollectionItem$WayPoint2.getLat(), dJIWPCollectionItem$WayPoint2.getLng());
        double height = dJIWPCollectionItem$WayPoint.getHeight() - dJIWPCollectionItem$WayPoint2.getHeight();
        double sqrt = Math.sqrt((a * a) + (height * height));
        a = dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint2.getLat(), dJIWPCollectionItem$WayPoint2.getLng(), dJIWPCollectionItem$WayPoint3.getLat(), dJIWPCollectionItem$WayPoint3.getLng());
        height = dJIWPCollectionItem$WayPoint2.getHeight() - dJIWPCollectionItem$WayPoint3.getHeight();
        double sqrt2 = Math.sqrt((a * a) + (height * height));
        a = dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng(), dJIWPCollectionItem$WayPoint3.getLat(), dJIWPCollectionItem$WayPoint3.getLng());
        height = dJIWPCollectionItem$WayPoint.getHeight() - dJIWPCollectionItem$WayPoint3.getHeight();
        double sqrt3 = Math.sqrt((a * a) + (height * height));
        return a(2.0d, 40.0d, a(2.0d, 40.0d, (Math.min(sqrt, sqrt2) - 1.0d) / 2.0d) * a(0.2d, 1.0d, Math.sqrt(Math.acos(a((double) Map.MOVE_PRESERVE_ZOOM_LEVEL, 1.0d, (((sqrt * sqrt) + (sqrt2 * sqrt2)) - (sqrt3 * sqrt3)) / ((2.0d * sqrt) * sqrt2))) / 3.141592653589793d)));
    }

    private double a(double d, double d2, double d3) {
        if (d3 > d2) {
            return d2;
        }
        if (d3 < d) {
            return d;
        }
        return d3;
    }

    public void g(dji.midware.e.d dVar) {
        a(false, dVar);
    }

    public void a(dji.midware.e.d dVar, float f) {
        DataFlycWayPointSetIdleSpeed instance = DataFlycWayPointSetIdleSpeed.getInstance();
        instance.a(f);
        instance.start(dVar);
    }

    public void b(dji.midware.e.d dVar, boolean z) {
        DataFlycWayPointMissionPauseOrResume instance = DataFlycWayPointMissionPauseOrResume.getInstance();
        if (z) {
            instance.setCMD(DataFlycWayPointMissionPauseOrResume.CMD.PAUSE);
        } else {
            instance.setCMD(DataFlycWayPointMissionPauseOrResume.CMD.RESUME);
        }
        instance.start(dVar);
    }

    private void b(View view) {
        switch (dji.pilot.fpv.flightmode.c.getInstance().a()) {
            case TRACK:
                a((int) R.layout.visual_general_view, 101);
                return;
            case POINT:
                a((int) R.layout.visual_general_view, 101);
                return;
            case SMART:
                if (o()) {
                    if (this.I.c() != 0 && this.I.c() != 100) {
                        this.az = -2;
                        this.I.hide();
                        dji.thirdparty.a.c.a().e(DJICustomType.s);
                        return;
                    }
                    return;
                } else if (this.I == null || this.az != -2) {
                    switch (this.r) {
                        case COURSE_LOCK:
                            a((int) R.layout.gs_course_lock_status_view, 12);
                            return;
                        case FOLLOW_ME:
                            a((int) R.layout.gs_follow_me_status_view, 14);
                            return;
                        case HOME_LOCK:
                            a((int) R.layout.gs_home_lock_status_view, 13);
                            return;
                        case POI_AUTO:
                            a((int) R.layout.gs_point_of_insterest_status_view, 22);
                            return;
                        case WP_AUTO:
                            a((int) R.layout.gs_way_point_status_view, 21);
                            return;
                        default:
                            switch (this.az) {
                                case 1:
                                    a((int) R.layout.gs_course_lock_view, 1);
                                    return;
                                case 2:
                                    a((int) R.layout.gs_home_lock_view, 2);
                                    return;
                                case 3:
                                    a((int) R.layout.gs_follow_me_view, 3);
                                    return;
                                case 5:
                                    a((int) R.layout.gs_way_point_view, 5);
                                    return;
                                case 7:
                                    a((int) R.layout.gs_way_point_collection_view, 7);
                                    return;
                                case 15:
                                    a((int) R.layout.gs_point_of_insterest_set_hot_point_view, 15);
                                    return;
                                case 17:
                                    a((int) R.layout.gs_point_of_insterest_start_view, 17);
                                    return;
                                case 20:
                                    a((int) R.layout.gs_way_point_auto_fly_setting_view, 20);
                                    return;
                                case 23:
                                    a((int) R.layout.gs_way_point_add_point_small_view, 23);
                                    return;
                                case 27:
                                    a((int) R.layout.gs_way_point_start_confirm, 27);
                                    return;
                                default:
                                    y();
                                    return;
                            }
                    }
                } else {
                    this.I.show();
                    this.az = -1;
                    this.L = false;
                    return;
                }
            default:
                return;
        }
    }
}
