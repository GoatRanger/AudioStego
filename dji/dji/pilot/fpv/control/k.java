package dji.pilot.fpv.control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.location.GpsStatus;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.google.android.gms.maps.GoogleMap;
import com.here.odnp.config.OdnpConfigStatic;
import dji.gs.map.control.AmapControll;
import dji.gs.map.control.GmapControll;
import dji.gs.map.views.AmapView;
import dji.gs.map.views.GmapView;
import dji.gs.map.views.HmapView;
import dji.gs.views.EventView;
import dji.gs.views.PaintView;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.DJIFlightLimitAreaModel;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.DJIFlyForbidController.DataSwitchEvent;
import dji.midware.data.forbid.DJIFlyForbidController.GeoStatusEvent;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCommonGetPushAppGpsConfig;
import dji.midware.data.model.P3.DataCommonSetAppGpsCyclic;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.pilot.R;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem$WayPoint;
import dji.pilot.flightrecord.DJIRecordService;
import dji.pilot.flyforbid.a$a;
import dji.pilot.fpv.d.c.g;
import dji.pilot.publics.c.h$a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class k implements OnClickListener, dji.gs.b.b.a, dji.gs.d.a, dji.gs.d.e, g {
    public static final String a = "DJILastAirPoint";
    private static dji.gs.e.b ai = new dji.gs.e.b(0.0d, 0.0d);
    public static dji.gs.e.b b;
    public static dji.gs.e.b c;
    private PaintView N;
    private dji.pilot.fpv.view.e O;
    private dji.gs.e.b P = new dji.gs.e.b(22.531946958202116d, 113.93412908363342d);
    private dji.gs.e.b Q = new dji.gs.e.b(22.530546958202116d, 113.93412908363342d);
    private boolean R = false;
    private DJIImageView S;
    private DJIImageView T;
    private dji.pilot.fpv.b.a U;
    private long V;
    private boolean W = false;
    private Bundle X;
    private volatile boolean Y = false;
    private boolean Z = false;
    private double aA = 0.0d;
    private double aB = 0.0d;
    private double aC = 0.0d;
    private boolean aD = false;
    private boolean aE = true;
    private boolean aF = false;
    private int aG = 0;
    private boolean aa = false;
    private d ab = null;
    private IntentFilter ac = null;
    private int ad = 0;
    private boolean ae = false;
    private int af;
    private int ag;
    private dji.gs.e.b ah = new dji.gs.e.b(0.0d, 0.0d);
    private float aj = 0.0f;
    private DJIRelativeLayout ak;
    private DJIImageView al;
    private DJIImageView am;
    private e an = new e();
    private boolean ao = false;
    private float ap = 0.0f;
    private boolean aq = false;
    private int ar;
    private int as;
    private boolean at = true;
    private boolean au = false;
    private Handler av = new Handler(new Callback(this) {
        final /* synthetic */ k a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            boolean z = true;
            if (this.a.l != null) {
                dji.gs.c.e b;
                float g;
                switch (message.what) {
                    case 0:
                        this.a.l.a(k.b, true);
                        if (this.a.m != null) {
                            this.a.m.a(this.a);
                            break;
                        }
                        break;
                    case 1:
                        this.a.l.a(this.a.ah);
                        this.a.l.a(DataOsdGetPushCommon.getInstance().getFlycState() != FLYC_STATE.GoHome, false);
                        if (this.a.n == c.PLANE_CENTER || this.a.n == c.PLANE_YAW_CENTER) {
                        }
                        if (this.a.at) {
                            this.a.l.a(this.a.ah, false);
                            break;
                        }
                        break;
                    case 2:
                        b = this.a.l;
                        g = this.a.aj;
                        if (this.a.n != c.PLANE_YAW_CENTER) {
                            z = false;
                        }
                        b.a(g, z);
                        break;
                    case 3:
                        b = this.a.l;
                        dji.gs.e.b bVar = k.b;
                        if (message.arg1 != 1) {
                            z = false;
                        }
                        b.b(bVar, z);
                        if (this.a.aq) {
                            this.a.A();
                            break;
                        }
                        break;
                    case 4:
                        dji.gs.e.b bVar2 = (dji.gs.e.b) message.obj;
                        if (bVar2 != null) {
                            if (message.arg1 != 1) {
                                this.a.l.d(bVar2);
                                break;
                            }
                            this.a.l.e(bVar2);
                            break;
                        }
                        break;
                    case 5:
                        b = this.a.l;
                        g = this.a.ap;
                        float g2 = this.a.aj;
                        if (this.a.n != c.PLANE_YAW_CENTER) {
                            z = false;
                        }
                        b.a(g, g2, z);
                        break;
                    case 100:
                        boolean z2;
                        if (this.a.ax) {
                            this.a.ah = new dji.gs.e.b(this.a.ah.b, this.a.ah.c + 1.0E-4d);
                        } else {
                            this.a.ah = new dji.gs.e.b(this.a.ah.b + 1.0E-4d, this.a.ah.c);
                        }
                        k kVar = this.a;
                        if (this.a.ax) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        kVar.ax = z2;
                        this.a.av.sendEmptyMessage(1);
                        this.a.av.sendEmptyMessage(2);
                        this.a.aw = this.a.aw + 1;
                        DJILogHelper.getInstance().LOGD("", "testnum=" + this.a.aw);
                        if (this.a.aw <= 10) {
                            this.a.av.sendEmptyMessageDelayed(100, 10);
                            break;
                        }
                        break;
                    case 200:
                        if (this.a.ax) {
                            this.a.P = new dji.gs.e.b(this.a.P.b, this.a.P.c + 1.0E-4d);
                        } else {
                            this.a.P = new dji.gs.e.b(this.a.P.b + 1.0E-4d, this.a.P.c);
                        }
                        k kVar2 = this.a;
                        if (this.a.ax) {
                            z = false;
                        }
                        kVar2.ax = z;
                        this.a.l.e(this.a.P);
                        this.a.av.sendEmptyMessageDelayed(200, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                        break;
                    case 300:
                        this.a.s();
                        break;
                    case 400:
                        if (DJIRecordService.a != null) {
                            this.a.an.a = this.a.U.D();
                            new Thread(this.a.an).start();
                            break;
                        }
                        break;
                    case 500:
                        this.a.q();
                        break;
                    case 600:
                        this.a.l.D();
                        break;
                    default:
                        break;
                }
            }
            return false;
        }
    });
    private int aw;
    private boolean ax;
    private boolean ay = false;
    private double az = 0.0d;
    DataCommonSetAppGpsCyclic d = new DataCommonSetAppGpsCyclic();
    private Context e;
    private RelativeLayout f;
    private EventView g;
    private dji.gs.c.b h;
    private dji.gs.views.a i;
    private DJIImageView j;
    private ImageView k;
    private dji.gs.c.e l;
    private dji.gs.b.b m;
    private c n = c.LOCK;
    private DJIRelativeLayout o;
    private DJIImageView p;
    private DJIImageView q;
    private DJIImageView r;
    private DJIImageView s;
    private dji.pilot.fpv.view.b t;
    private DJIRelativeLayout u;

    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] b = new int[dji.gs.views.EventView.b.values().length];
        static final /* synthetic */ int[] c = new int[o.values().length];
        static final /* synthetic */ int[] d = new int[DJICustomType.values().length];

        static {
            f = new int[a.values().length];
            try {
                f[a.UPDATE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            e = new int[b.values().length];
            try {
                e[b.AIR.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                e[b.HOME.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                d[DJICustomType.e.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                c[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                c[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[dji.gs.views.EventView.b.a.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            a = new int[c.values().length];
            try {
                a[c.LOCK.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[c.PLANE_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[c.PLANE_YAW_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public enum a {
        UPDATE
    }

    public enum b {
        AIR,
        HOME
    }

    public enum c {
        LOCK,
        PLANE_CENTER,
        PLANE_YAW_CENTER
    }

    private final class d extends BroadcastReceiver {
        final /* synthetic */ k a;

        private d(k kVar) {
            this.a = kVar;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.location.PROVIDERS_CHANGED".equals(intent.getAction())) {
                this.a.s();
            }
        }
    }

    private class e implements Runnable {
        public Bitmap a;
        final /* synthetic */ k b;

        private e(k kVar) {
            this.b = kVar;
        }

        public void run() {
            if (DJIRecordService.a != null) {
                DJIRecordService.a.a(this.a);
            }
        }
    }

    public k(Context context) {
        this.e = context;
    }

    private void b(Bundle bundle) {
        Object amapView;
        if (DJIGenSettingDataManager.getInstance().r()) {
            amapView = new AmapView(this.e);
            amapView.setLayoutParams(new LayoutParams(-1, -1));
            amapView.setClickable(true);
            amapView.setVisibility(0);
            this.g.addView(amapView);
            try {
                MapsInitializer.initialize(this.e);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            amapView.onCreate(bundle);
            AMap map = amapView.getMap();
            UiSettings uiSettings = map.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setCompassEnabled(false);
            uiSettings.setScaleControlsEnabled(true);
            this.l = new AmapControll(this.e, map, this.f);
            this.h = amapView;
            this.l.a(this.e);
        } else if (dji.a.a.getInstance().a()) {
            Object hmapView = new HmapView(this.e);
            hmapView.setLayoutParams(new LayoutParams(-1, -1));
            hmapView.setClickable(true);
            hmapView.setVisibility(0);
            this.g.addView(hmapView);
            hmapView.onCreate(bundle);
            this.l = new dji.gs.map.control.a(this.e, hmapView, this.f);
            this.h = hmapView;
            this.l.a(this.e);
        } else {
            amapView = new GmapView(this.e);
            amapView.setLayoutParams(new LayoutParams(-1, -1));
            amapView.setClickable(true);
            amapView.setVisibility(0);
            this.g.addView(amapView);
            try {
                com.google.android.gms.maps.MapsInitializer.initialize(this.e);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            amapView.onCreate(bundle);
            GoogleMap map2 = amapView.getMap();
            com.google.android.gms.maps.UiSettings uiSettings2 = map2.getUiSettings();
            uiSettings2.setZoomControlsEnabled(false);
            uiSettings2.setCompassEnabled(false);
            this.l = new GmapControll(this.e, map2, this.f);
            this.h = amapView;
            this.l.a(this.e);
        }
        D();
    }

    public void a(Bundle bundle, RelativeLayout relativeLayout) {
        this.X = bundle;
        this.f = relativeLayout;
        this.g = (EventView) relativeLayout.findViewById(R.id.ak7);
        this.o = (DJIRelativeLayout) relativeLayout.findViewById(R.id.ak9);
        this.ak = (DJIRelativeLayout) relativeLayout.findViewById(R.id.a2k);
        this.q = (DJIImageView) relativeLayout.findViewById(R.id.a2l);
        this.r = (DJIImageView) relativeLayout.findViewById(R.id.ak_);
        this.s = (DJIImageView) relativeLayout.findViewById(R.id.aka);
        this.k = (DJIImageView) this.o.findViewById(R.id.ak5);
        this.p = (DJIImageView) this.o.findViewById(R.id.ak6);
        this.al = (DJIImageView) this.ak.findViewById(R.id.ak5);
        this.am = (DJIImageView) this.ak.findViewById(R.id.ak6);
        this.u = (DJIRelativeLayout) relativeLayout.findViewById(R.id.akc);
        this.j = (DJIImageView) relativeLayout.findViewById(R.id.ak8);
        this.T = (DJIImageView) relativeLayout.findViewById(R.id.a2m);
        this.S = (DJIImageView) relativeLayout.findViewById(R.id.akb);
        this.j.hide();
        this.o.hide();
        this.r.hide();
        this.s.hide();
        this.u.hide();
        this.S.hide();
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.al.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.m = new dji.gs.b.b(this.e);
        this.m.a(this.n != c.PLANE_CENTER);
        this.m.a();
    }

    private void c(Bundle bundle) {
        b(bundle);
        B();
        this.l.a(new dji.gs.d.b(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void onClick() {
                dji.thirdparty.a.c.a().e(dji.gs.views.EventView.a.a);
            }
        });
        this.l.a(new dji.gs.d.c(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void a(Bitmap bitmap) {
            }
        });
        this.N = new PaintView(this.e, this.l);
        this.g.addView(this.N);
        this.av.sendEmptyMessageDelayed(0, 1000);
        this.V = System.currentTimeMillis();
        if (ai != null) {
            ai.b = 0.0d;
            ai.c = 0.0d;
        }
        dji.thirdparty.a.c.a().a(this);
        if (this.ay) {
            this.l.e(this.Q);
            this.ah = this.Q;
            this.l.a(this.ah);
        }
        this.l.a(this);
        h();
        t();
        onEventBackgroundThread(DataOsdGetPushHome.getInstance());
        this.U = (dji.pilot.fpv.b.a) this.e;
        if (dji.pilot.publics.objects.g.b(this.e, DJIFlyForbidController.KEY_FLY_FORBID_DATA_SOURCE, DJIFlyForbidController.DJI_DATA_SOURCE).equals(DJIFlyForbidController.DJI_DATA_SOURCE)) {
            onEventMainThread(DataSwitchEvent.DJI);
        } else {
            onEventMainThread(DataSwitchEvent.AIRMAP);
        }
    }

    private void r() {
    }

    private void c(dji.gs.e.b bVar) {
        if (bVar != null && bVar.a() && !this.Y && !ServiceManager.getInstance().isRemoteOK()) {
            try {
                dji.thirdparty.a.c.a().e(new a$a(bVar.b, bVar.c));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.Y = true;
        }
    }

    public void b(dji.gs.e.b bVar) {
        try {
            this.l.h(null);
            this.l.h(bVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        if (this.l == null) {
            c(this.X);
        }
        if (this.h != null) {
            this.h.onResume();
        }
        if (!this.Z) {
            this.Z = true;
            this.av.sendEmptyMessageDelayed(300, 1000);
        }
        try {
            dji.gs.e.b a = dji.gs.e.b.a(dji.pilot.publics.objects.g.b(this.e, a, ""));
            if (a != null) {
                this.l.b(a);
                c(a);
            }
            if (b == null) {
                a = this.l.C();
                if (a == null || !a.a()) {
                    if (this.ay) {
                        b = this.P;
                        this.l.b(b, true);
                        C();
                    }
                    onEventMainThread(a.UPDATE);
                }
                c = a;
                if (c.a()) {
                    c(c);
                    this.l.f(c);
                    if (!this.W) {
                        this.l.a(c, true);
                        this.W = true;
                    }
                    onEventMainThread(a.UPDATE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
        if (this.h != null) {
            this.h.onPause();
        }
    }

    public void d() {
        if (this.h != null) {
            this.h.onLowMemory();
        }
    }

    public void e() {
        u();
        this.ab = null;
        if (this.ah != null) {
            dji.pilot.publics.objects.g.a(this.e, a, this.ah.toString());
        }
        this.av.removeCallbacksAndMessages(null);
        this.av = null;
        if (this.N != null) {
            this.N.onDestroy();
            this.N = null;
        }
        this.m.b();
        this.g.destroy();
        this.g = null;
        dji.gs.views.b.a();
        this.m.c();
        this.m = null;
        dji.thirdparty.a.c.a().d(this);
        if (this.l != null) {
            this.l.D();
            this.l.a();
            this.l = null;
        }
        if (this.h != null) {
            this.h.onDestroy();
            this.h = null;
        }
        b = null;
    }

    public void f() {
        this.av.sendEmptyMessageDelayed(400, 2000);
    }

    private void s() {
        this.l.a(1000, 0.0f, this);
    }

    private void t() {
        if (!this.aa) {
            this.aa = true;
            if (this.ab == null) {
                this.ab = new d();
                this.ac = new IntentFilter();
                this.ac.addAction("android.location.PROVIDERS_CHANGED");
            }
            this.e.registerReceiver(this.ab, this.ac);
        }
    }

    private void u() {
        if (this.aa) {
            this.e.unregisterReceiver(this.ab);
        }
    }

    public void a(Bundle bundle) {
        this.h.onSaveInstanceState(bundle);
    }

    private void v() {
        if (this.i == null) {
            this.i = new dji.gs.views.a(this.e, this.l, this.j);
        }
        this.i.b();
    }

    private void w() {
        if (this.t == null) {
            this.t = new dji.pilot.fpv.view.b(this.e, this.r);
        }
        this.t.a();
    }

    private void x() {
        if (this.O == null) {
            this.O = new dji.pilot.fpv.view.e(this.e, this.l, this.s);
        }
        this.O.a();
    }

    public void g() {
        if (!EventView.b) {
            boolean z;
            switch (this.n) {
                case LOCK:
                    this.n = c.PLANE_CENTER;
                    this.p.hide();
                    this.am.hide();
                    break;
                default:
                    this.n = c.LOCK;
                    this.k.setImageResource(R.drawable.gs_compass_button);
                    this.p.show();
                    this.al.setImageResource(R.drawable.gs_compass_indicator);
                    this.am.show();
                    b(0.0f);
                    break;
            }
            dji.gs.b.b bVar = this.m;
            if (this.n != c.PLANE_CENTER) {
                z = true;
            } else {
                z = false;
            }
            bVar.a(z);
        }
    }

    public void b(float f) {
        this.l.a(f);
        if (this.n == c.PLANE_YAW_CENTER) {
            this.l.b(this.aj - f);
        } else {
            this.l.b(f);
            this.av.sendEmptyMessage(2);
            this.av.sendEmptyMessage(5);
        }
        if (this.k.isShown()) {
            this.k.setRotation(-f);
        }
        if (this.al.isShown()) {
            this.al.setRotation(-f);
        }
    }

    public void a(boolean z) {
        if (z) {
            this.l.i(this.ah.a() ? this.ah : b);
        } else if (ai.a()) {
            this.l.s();
        } else {
            this.l.i(b);
        }
    }

    public void h() {
        if (!DJIGenSettingDataManager.getInstance().t()) {
            this.l.v();
        }
        this.l.d(DJIGenSettingDataManager.getInstance().t());
    }

    public void i() {
        if (DJIGenSettingDataManager.getInstance().t() && this.l != null) {
            this.l.v();
        }
    }

    public void a(ArrayList<DJIFlightLimitAreaModel> arrayList) {
        this.l.a(arrayList);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ak5) {
            dji.pilot.fpv.d.e.a("GroundStation_RightControlView_Button_CompassLock");
            g();
        } else if (id == R.id.ak8) {
            dji.pilot.fpv.d.e.a("GroundStation_RightControlView_Button_ShowMapType");
            v();
        } else if (id == R.id.a2l) {
            z();
        } else if (id == R.id.ak_) {
            dji.pilot.fpv.d.e.a("GroundStation_RightControlView_Button_ShowMapLocation");
            w();
        } else if (id == R.id.akc) {
            dji.pilot.fpv.d.e.a("GroundStation_Button_ClearDrawLine");
            y();
        } else if (id == R.id.a2m || id == R.id.akb) {
            i();
            dji.pilot.fpv.d.e.a("GroundStation_Button_ClearAircraftLine");
        } else if (id == R.id.aka) {
            x();
        }
    }

    private void y() {
        DJILogHelper.getInstance().LOGD("", "clearRoute");
        this.l.x();
        this.ao = false;
        this.u.go();
    }

    private void z() {
        this.ae = !this.ae;
        if (this.ae) {
            this.q.setImageResource(R.drawable.gs_map_widget_zoom_out);
            A();
            return;
        }
        this.q.setImageResource(R.drawable.gs_map_widget_zoom_in);
        this.l.w();
    }

    private void A() {
        if (this.af == 0) {
            this.af = q.a;
            this.ag = q.b;
        }
        this.l.b(this.af / 2, this.ag / 2);
        this.aq = false;
    }

    public void a(float f) {
        if (!EventView.b) {
            b(90.0f + f);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        dji.gs.e.b bVar = new dji.gs.e.b(dji.pilot.groundStation.a.b.getInstance().b(), dji.pilot.groundStation.a.b.getInstance().a());
        if (bVar.a() && !this.ah.a(bVar) && dji.pilot.fpv.d.b.a(dataOsdGetPushCommon.getDroneType(), dataOsdGetPushCommon.getFlycVersion(), dataOsdGetPushCommon.getGpsNum(), dataOsdGetPushCommon.getGpsLevel())) {
            if (this.ah.a()) {
                c(this.ah);
            }
            this.ah = bVar;
            this.av.sendEmptyMessage(1);
        }
        float yaw = ((float) dataOsdGetPushCommon.getYaw()) * 0.1f;
        if (yaw != this.aj) {
            this.aj = yaw;
            this.av.sendEmptyMessage(2);
        }
    }

    public static dji.gs.e.b j() {
        return ai;
    }

    public void onEventMainThread(dji.gs.views.EventView.b bVar) {
        switch (AnonymousClass6.b[bVar.ordinal()]) {
            case 1:
                this.ao = true;
                this.u.show();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(dji.pilot.dji_groundstation.a.e eVar) {
        int i = 1;
        if (eVar.s == 7) {
            if (eVar.t instanceof dji.midware.e.d) {
                ((dji.midware.e.d) eVar.t).onSuccess(l().f());
            }
        } else if (eVar.s == 6) {
            if (eVar.t instanceof dji.midware.e.d) {
                ((dji.midware.e.d) eVar.t).onSuccess(k());
            }
        } else if (eVar.s == 5) {
            if (eVar.t instanceof DJIWPCollectionItem$WayPoint) {
                DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) eVar.t;
                if (dJIWPCollectionItem$WayPoint != null) {
                    l().g(dji.gs.utils.a.a(new dji.gs.e.b(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng())));
                }
            }
        } else if (eVar.s == 2) {
            if (eVar.t instanceof DJIWPCollectionItem) {
                if (l() != null) {
                    l().g();
                    l().b(null, 0.0d);
                }
                DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) eVar.t;
                if (dJIWPCollectionItem != null && dJIWPCollectionItem.getPoints().size() >= 1) {
                    dji.gs.e.b a = dji.gs.utils.a.a(new dji.gs.e.b(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).getLat(), ((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).getLng()));
                    dji.gs.e.b bVar = new dji.gs.e.b(a.b, a.c);
                    l().g(a);
                    while (i < dJIWPCollectionItem.getPoints().size()) {
                        dji.gs.e.b a2 = dji.gs.utils.a.a(new dji.gs.e.b(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(i)).getLat(), ((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(i)).getLng()));
                        l().g(a2);
                        if (a.b > a2.b) {
                            a.b = a2.b;
                        }
                        if (a.c > a2.c) {
                            a.c = a2.c;
                        }
                        if (bVar.b < a2.b) {
                            bVar.b = a2.b;
                        }
                        if (bVar.c < a2.c) {
                            bVar.c = a2.c;
                        }
                        a(a, bVar);
                        i++;
                    }
                }
            }
        } else if (eVar.s == 1) {
            if (l() != null) {
                l().g();
                l().b(null, 0.0d);
            }
        } else if (eVar.s == 3) {
            if (k() == null || l().F() >= 10000.0f) {
                dji.pilot.dji_groundstation.controller.d.getInstance().a(8, this.e.getResources().getString(R.string.gs_follow_me_device_has_no_gps));
            } else if (l().F() > dji.gs.e.b.a) {
                dji.pilot.dji_groundstation.controller.d.getInstance().a(8, this.e.getResources().getString(R.string.gs_follow_me_can_not_get_user_location));
            } else {
                dji.pilot.dji_groundstation.controller.e.a(this.e);
            }
        } else if (eVar.s == 24) {
            if (eVar.t instanceof dji.midware.e.d) {
                ((dji.midware.e.d) eVar.t).onSuccess(new Float(l().F()));
            }
        } else if (eVar.s == 21) {
            if (eVar.t instanceof dji.gs.e.b) {
                l().g();
                l().b((dji.gs.e.b) eVar.t, 0.0d);
            }
        } else if (eVar.s == 22 && (eVar.t instanceof Integer)) {
            l().d(((Integer) eVar.t).intValue());
        }
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        float yawAngle = ((((float) dataGimbalGetPushParams.getYawAngle()) * 0.1f) + this.aj) + 180.0f;
        if (yawAngle != this.ap) {
            this.ap = yawAngle;
            this.av.sendEmptyMessage(5);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if (dataOsdGetPushHome.isHomeRecord()) {
            dji.gs.e.b bVar = new dji.gs.e.b(dataOsdGetPushHome.getLatitude(), dataOsdGetPushHome.getLongitude());
            if (bVar.a() && !ai.a(bVar) && dji.gs.utils.c.a(bVar, ai) > 1.0f) {
                DJILogHelper.getInstance().LOGD("", "===Start[" + this.V + "]now[" + System.currentTimeMillis() + dji.pilot.usercenter.protocol.d.H, false, true);
                if (System.currentTimeMillis() - this.V >= 10000) {
                    if (ai.a()) {
                        dji.thirdparty.a.c.a().e(h$a.UPDATE);
                    } else {
                        dji.thirdparty.a.c.a().e(h$a.RECORD);
                    }
                    dji.pilot.fpv.view.DJIErrorPopView.b bVar2 = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar2.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                    bVar2.b = R.string.gs_set_home;
                    dji.thirdparty.a.c.a().e(bVar2);
                }
                if (ai.a()) {
                    this.av.sendMessage(this.av.obtainMessage(4, 1, 0, bVar));
                } else {
                    this.av.sendMessage(this.av.obtainMessage(4, 0, 0, bVar));
                }
                ai = bVar;
                c(ai);
            }
        }
    }

    public void onEventBackgroundThread(DataRcGetPushGpsInfo dataRcGetPushGpsInfo) {
        int i = 1;
        if (!this.R) {
            dji.gs.e.b bVar = new dji.gs.e.b(dataRcGetPushGpsInfo.getLatitude(), dataRcGetPushGpsInfo.getLongitude(), dataRcGetPushGpsInfo.getGpsStatus() ? 1.0f : 10000.0f);
            if (bVar.a() && !bVar.a(b)) {
                if (b == null) {
                    this.aq = true;
                }
                b = bVar;
                dji.thirdparty.a.c.a().e(b);
                Handler handler = this.av;
                Handler handler2 = this.av;
                if (!DataRcGetPushGpsInfo.getInstance().getGpsStatus()) {
                    i = 0;
                }
                handler.sendMessage(handler2.obtainMessage(3, i, 0));
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass6.c[oVar.ordinal()]) {
        }
    }

    public void onEventMainThread(DJICustomType dJICustomType) {
        switch (AnonymousClass6.d[dJICustomType.ordinal()]) {
            case 1:
                i();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(b bVar) {
        switch (bVar) {
            case AIR:
                a(true);
                return;
            case HOME:
                a(false);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(a aVar) {
        boolean z = true;
        switch (aVar) {
            case UPDATE:
                if (dji.midware.data.manager.P3.d.read("g_config.advanced_function.radius_limit_enabled_0").value.intValue() != 1) {
                    z = false;
                }
                this.l.a(z, dji.midware.data.manager.P3.d.read("g_config.flying_limit.max_radius_0").value.intValue());
                return;
            default:
                return;
        }
    }

    public void a(int i, int i2) {
        this.ar = i;
        this.as = i2;
        B();
    }

    private void B() {
        if (this.l != null) {
            this.l.a(this.ar, this.as);
            dji.gs.e.b bVar = (this.ah == null || !this.ah.a()) ? b : this.ah;
            if (bVar == null) {
                bVar = c;
            }
            this.l.a(bVar, false);
        }
    }

    public void b(boolean z) {
        this.at = z;
        if (z) {
            this.o.hide();
            this.j.hide();
            this.r.hide();
            this.s.hide();
            this.u.hide();
            this.S.hide();
            this.ak.show();
            return;
        }
        this.o.show();
        this.j.show();
        this.r.show();
        if (dji.pilot.c.a.E && this.au) {
            this.s.show();
        }
        this.S.show();
        if (this.ao) {
            this.u.show();
        }
        this.ak.hide();
    }

    public void onEventMainThread(GeoStatusEvent geoStatusEvent) {
        if (dji.pilot.c.a.E) {
            if (geoStatusEvent == GeoStatusEvent.OPENED) {
                this.au = true;
            } else {
                this.au = false;
            }
            if (!this.at) {
                if (geoStatusEvent == GeoStatusEvent.OPENED) {
                    this.s.show();
                } else {
                    this.s.hide();
                }
            }
        }
    }

    public void onEventMainThread(DataSwitchEvent dataSwitchEvent) {
        if (dji.pilot.c.a.E) {
            if (dataSwitchEvent == DataSwitchEvent.AIRMAP) {
                this.au = true;
            } else {
                this.au = false;
            }
            if (!this.at) {
                if (dataSwitchEvent == DataSwitchEvent.AIRMAP) {
                    this.s.show();
                } else {
                    this.s.hide();
                }
            }
        }
    }

    private void C() {
        dji.a.a instance = dji.a.a.getInstance();
        if (instance.a("gps") || instance.a("network")) {
            dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().b(b);
        } else {
            dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().b(null);
        }
    }

    public static dji.gs.e.b k() {
        dji.a.a instance = dji.a.a.getInstance();
        if (!dji.pilot.fpv.d.b.k(i.getInstance().c()) || instance.a("gps") || instance.a("network")) {
            return b;
        }
        return null;
    }

    public void a(dji.gs.e.b bVar) {
        int i = 1;
        if (bVar != null && bVar.a()) {
            c = bVar;
            dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().b(bVar);
            if (dji.pilot.fpv.d.b.k(i.getInstance().c())) {
                if (b == null) {
                    b = bVar;
                    if (!this.W) {
                        this.l.i(c);
                        this.W = true;
                    }
                } else {
                    b = bVar;
                }
                dji.thirdparty.a.c.a().e(b);
                if (this.av != null) {
                    Handler handler = this.av;
                    Handler handler2 = this.av;
                    if (!b.b()) {
                        i = 0;
                    }
                    handler.sendMessage(handler2.obtainMessage(3, i, 0));
                }
            } else if (b == null) {
                this.l.b(c, c.b());
                if (!this.W) {
                    this.l.i(c);
                    this.W = true;
                }
            }
        }
    }

    public dji.gs.d.e.a a() {
        return null;
    }

    public dji.gs.c.e l() {
        return this.l;
    }

    private void a(String str, Bitmap bitmap) {
        if (bitmap != null) {
            try {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, (bitmap.getHeight() * 300) / bitmap.getWidth(), true);
                OutputStream fileOutputStream = new FileOutputStream(str);
                createScaledBitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                createScaledBitmap.recycle();
                try {
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (FileNotFoundException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void a(dji.gs.e.b bVar, dji.gs.e.b bVar2) {
        if (this.h != null) {
            if (bVar.b >= bVar2.b) {
                bVar2.b = bVar.b;
                bVar = bVar2;
            }
            if (this.az != bVar.b || this.aA != bVar2.b || this.aB != bVar.c || this.aC != bVar2.c) {
                this.aE = false;
                this.l.a(150, new dji.gs.e.b[]{bVar, bVar2});
            }
        }
    }

    public c m() {
        return this.n;
    }

    public boolean n() {
        return this.aD;
    }

    public void o() {
        this.aD = false;
    }

    private void D() {
        if (DJIGenSettingDataManager.getInstance().r()) {
            ((AmapView) this.h).getMap().setOnCameraChangeListener(new OnCameraChangeListener(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void onCameraChange(CameraPosition cameraPosition) {
                }

                public void onCameraChangeFinish(CameraPosition cameraPosition) {
                    if (this.a.aE) {
                        this.a.aD = true;
                    }
                    this.a.aE = true;
                    this.a.l.c(cameraPosition.zoom);
                }
            });
        } else if (!dji.a.a.getInstance().a()) {
            ((GmapView) this.h).getMap().setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void onCameraChange(com.google.android.gms.maps.model.CameraPosition cameraPosition) {
                    if (this.a.aE) {
                        this.a.aD = true;
                    }
                    this.a.aE = true;
                    this.a.l.c(cameraPosition.zoom);
                }
            });
        }
    }

    public void onEventBackgroundThread(DataCommonGetPushAppGpsConfig dataCommonGetPushAppGpsConfig) {
        if (this.aF != dataCommonGetPushAppGpsConfig.isStart() || this.aG != dataCommonGetPushAppGpsConfig.getPushInterval()) {
            DJILogHelper.getInstance().LOGD("", "是否请求推送：" + dataCommonGetPushAppGpsConfig.isStart() + " 推送间隔：" + dataCommonGetPushAppGpsConfig.getPushInterval() + "ms", false, true);
            boolean a = dji.a.a.getInstance().a("gps");
            this.aF = dataCommonGetPushAppGpsConfig.isStart();
            this.aG = dataCommonGetPushAppGpsConfig.getPushInterval();
            DJILogHelper.getInstance().LOGD("", "是否开启定位" + a, false, true);
            dataCommonGetPushAppGpsConfig.setResponseCode(a);
            dataCommonGetPushAppGpsConfig.start(null);
            if (a) {
                p();
            }
        }
    }

    public void p() {
        if (this.av.hasMessages(500)) {
            this.av.removeMessages(500);
        }
        if (this.aF) {
            this.av.sendEmptyMessageDelayed(500, (long) this.aG);
        }
    }

    public void q() {
        double d;
        double d2 = 0.0d;
        int i = 0;
        float f = 0.0f;
        if (c == null) {
            d = 0.0d;
        } else {
            GpsStatus a = dji.a.a.getInstance().a(null);
            Iterator it = a.getSatellites().iterator();
            int i2 = 0;
            while (it.hasNext() && i2 <= a.getMaxSatellites()) {
                i2++;
                it.next();
            }
            d = c.b;
            d2 = c.c;
            f = c.d;
            DJILogHelper.getInstance().LOGD("", "count:" + i2 + " lat: " + c.b + " long: " + c.c + " alt: " + c.d + " interval:" + this.aG, false, true);
            i = i2;
        }
        this.d.a(i, d, d2, f).start(null);
        this.av.sendEmptyMessageDelayed(500, (long) this.aG);
    }
}
