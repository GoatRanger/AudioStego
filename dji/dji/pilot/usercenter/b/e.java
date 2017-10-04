package dji.pilot.usercenter.b;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.RemoteException;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.google.android.gms.maps.GoogleMap;
import com.here.odnp.posclient.pos.IPositioningSession;
import dji.gs.map.control.AmapControll;
import dji.gs.map.control.GmapControll;
import dji.gs.map.views.AmapView;
import dji.gs.map.views.GmapView;
import dji.gs.map.views.HmapView;
import dji.gs.views.EventView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.model.DJIGeocoderResult;
import dji.pilot.fpv.model.DJIGeocoderResult.FirstLevel;
import dji.pilot.fpv.model.DJIGeocoderResult.SecondLevel;
import dji.pilot.fpv.model.f;
import dji.pilot.fpv.model.h;
import dji.pilot.fpv.model.i;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIFpvGimbalPitchView;
import dji.pilot.fpv.view.DJISmartBatteryView;
import dji.pilot.fpv.view.DJIStickCirclePgbView;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class e implements OnClickListener, dji.gs.b.b.a, dji.gs.d.e {
    private static final int A = 2;
    private static final int[] B = new int[]{R.id.c1k, R.id.c1l};
    private static final int C = 1024;
    private static final int D = 364;
    private static final int E = 1684;
    protected static int a = 0;
    protected static int b = 0;
    protected static final String c = "99：99";
    public static dji.gs.e.b d = null;
    public static boolean e = false;
    private static final long g = 100;
    private static ImageView m = null;
    private static final int y = 0;
    private static final int z = 1;
    private DJISmartBatteryView F = null;
    private DJITextView G = null;
    private DJITextView H = null;
    private DJITextView I = null;
    private DJITextView J = null;
    private DJITextView K = null;
    private DJITextView L = null;
    private DJITextView M = null;
    private DJIGenSettingDataManager N = DJIGenSettingDataManager.getInstance();
    private boolean O;
    private DJITextView P = null;
    private float Q = 0.0f;
    private float R = 0.0f;
    private float S = 0.0f;
    private float T = 0.0f;
    private int U = 0;
    private int V = 0;
    private int W = 0;
    private int X = 0;
    private FLYC_STATE Y = FLYC_STATE.OTHER;
    private boolean Z = false;
    private boolean aA = false;
    private Handler aB = new Handler(new Callback(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.j();
                    this.a.w();
                    break;
                case 1:
                    this.a.o.a(this.a);
                    break;
                case 10:
                    this.a.i();
                    break;
                case 11:
                    this.a.c(message.arg1);
                    break;
                case 12:
                    this.a.au.b();
                    break;
                case 13:
                    long currentTimeMillis = System.currentTimeMillis() - this.a.an;
                    if (currentTimeMillis >= 1000) {
                        this.a.aB.sendEmptyMessage(12);
                        break;
                    }
                    this.a.aB.sendEmptyMessageDelayed(12, 1000 - currentTimeMillis);
                    break;
                case 100:
                    if (this.a.ag != null) {
                        this.a.n.a(new dji.gs.e.b(this.a.ag.E, this.a.ag.D), true);
                        break;
                    }
                    break;
            }
            return false;
        }
    });
    private DJIImageView aa = null;
    private DJITextView ab = null;
    private DJIFpvGimbalPitchView ac;
    private DecimalFormat ad = new DecimalFormat("###,###,###,###");
    private volatile boolean ae = false;
    private List<h> af;
    private f ag;
    private h ah;
    private volatile boolean ai = false;
    private int aj;
    private int ak;
    private boolean al = false;
    private Thread am;
    private long an = 0;
    private dji.gs.e.b ao = new dji.gs.e.b(0.0d, 0.0d);
    private dji.gs.e.b ap = new dji.gs.e.b(0.0d, 0.0d);
    private float aq = 0.0f;
    private float ar = 0.0f;
    private int as = 0;
    private h at;
    private b au;
    private long av;
    private int aw = 1000;
    private int ax = 1;
    private float ay;
    private dji.gs.e.b az;
    public final c[] f = new c[2];
    private Context h;
    private RelativeLayout i;
    private dji.gs.c.b j;
    private dji.gs.views.a k;
    private DJIImageView l;
    private dji.gs.c.e n;
    private dji.gs.b.b o;
    private a p = a.LOCK;
    private DJIRelativeLayout q;
    private DJIImageView r;
    private DJIImageView s;
    private dji.pilot.fpv.view.b t;
    private EventView u;
    private DJIErrorPopView v;
    private String w = "";
    private String x = "";

    public enum a {
        LOCK,
        PLANE_CENTER,
        PLANE_YAW_CENTER
    }

    public interface b {
        void a();

        void a(int i, f fVar, long j, float f);

        void a(f fVar);

        void a(boolean z);

        void b();

        void b(f fVar);

        void b(boolean z);
    }

    public static final class c {
        public View a = null;
        public DJITextView b = null;
        public DJITextView c = null;
        public DJITextView d = null;
        public DJITextView e = null;
        public DJIStickCirclePgbView f = null;

        public void a() {
            this.a.setVisibility(0);
        }

        public void b() {
            this.a.setVisibility(4);
        }
    }

    public e(Context context) {
        this.h = context;
    }

    private void b(Bundle bundle) {
        if (DJIGenSettingDataManager.getInstance().r()) {
            e = false;
            Object amapView = new AmapView(this.h);
            amapView.setLayoutParams(new LayoutParams(-1, -1));
            amapView.setClickable(true);
            amapView.setVisibility(0);
            this.u.addView(amapView);
            try {
                MapsInitializer.initialize(this.h);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            amapView.onCreate(bundle);
            AMap map = amapView.getMap();
            UiSettings uiSettings = map.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setCompassEnabled(false);
            uiSettings.setScaleControlsEnabled(true);
            this.n = new AmapControll(this.h, map, this.i);
            this.j = amapView;
            return;
        }
        e = true;
        if (dji.a.a.getInstance().a()) {
            Object hmapView = new HmapView(this.h);
            hmapView.setLayoutParams(new LayoutParams(-1, -1));
            hmapView.setClickable(true);
            hmapView.setVisibility(0);
            this.u.addView(hmapView);
            hmapView.onCreate(bundle);
            this.n = new dji.gs.map.control.a(this.h, hmapView, this.i);
            this.j = hmapView;
            this.n.a(this.h);
            return;
        }
        amapView = new GmapView(this.h);
        amapView.setLayoutParams(new LayoutParams(-1, -1));
        amapView.setClickable(true);
        amapView.setVisibility(0);
        this.u.addView(amapView);
        try {
            com.google.android.gms.maps.MapsInitializer.initialize(this.h);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        amapView.onCreate(bundle);
        GoogleMap map2 = amapView.getMap();
        com.google.android.gms.maps.UiSettings uiSettings2 = map2.getUiSettings();
        uiSettings2.setZoomControlsEnabled(false);
        uiSettings2.setCompassEnabled(false);
        this.n = new GmapControll(this.h, map2, this.i);
        this.j = amapView;
    }

    public void a(Bundle bundle, RelativeLayout relativeLayout) {
        boolean z = false;
        this.i = relativeLayout;
        boolean z2 = (this.N.v() == 1 || this.N.v() == 2) ? false : true;
        this.O = z2;
        this.u = (EventView) relativeLayout.findViewById(R.id.ak7);
        this.q = (DJIRelativeLayout) relativeLayout.findViewById(R.id.ak9);
        this.s = (DJIImageView) relativeLayout.findViewById(R.id.ak_);
        m = (DJIImageView) this.q.findViewById(R.id.ak5);
        this.r = (DJIImageView) this.q.findViewById(R.id.ak6);
        this.l = (DJIImageView) relativeLayout.findViewById(R.id.ak8);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.l.getLayoutParams();
            marginLayoutParams.topMargin = com.dji.frame.c.e.b(this.h, 47.0f);
            this.l.setLayoutParams(marginLayoutParams);
        }
        this.v = (DJIErrorPopView) relativeLayout.findViewById(R.id.c1a);
        this.v.dispatchOnCreate();
        this.s.setOnClickListener(this);
        m.setOnClickListener(this);
        this.l.setOnClickListener(this);
        b(bundle);
        this.n.a(new dji.gs.d.b(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick() {
                dji.thirdparty.a.c.a().e(dji.gs.views.EventView.a.a);
            }
        });
        this.o = new dji.gs.b.b(this.h);
        dji.gs.b.b bVar = this.o;
        if (this.p != a.PLANE_CENTER) {
            z = true;
        }
        bVar.a(z);
        this.n.a(this);
        this.o.a();
        this.aB.sendEmptyMessageDelayed(100, 400);
        this.aB.sendEmptyMessageDelayed(1, 1000);
        dji.thirdparty.a.c.a().a(this);
        o();
        p();
        q();
        this.P = (DJITextView) relativeLayout.findViewById(R.id.d41);
    }

    private void a(dji.gs.e.b bVar) {
        if (bVar != null && bVar.a() && !this.ae) {
            try {
                this.n.h(bVar);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.ae = true;
        }
    }

    public void b() {
        this.j.onResume();
        if (d == null) {
            Location e = dji.a.a.getInstance().e();
            if (e != null) {
                d = new dji.gs.e.b(e.getLatitude(), e.getLongitude());
            }
        }
    }

    public void c() {
        this.j.onPause();
    }

    public void d() {
        this.j.onLowMemory();
    }

    public void e() {
        DJILogHelper.getInstance().LOGD("gs record player", "onDestroy");
        this.v.dispatchOnDestroy();
        this.ac.dispatchOnDestroy();
        this.aB.removeCallbacksAndMessages(null);
        this.aB = null;
        if (this.am != null) {
            this.am.interrupt();
        }
        dji.thirdparty.a.c.a().d(this);
        this.o.b();
        dji.gs.views.b.a();
        this.o.c();
        this.o = null;
        this.n.a();
        this.n = null;
        this.j.onDestroy();
        this.j = null;
    }

    public void a(Bundle bundle) {
        this.j.onSaveInstanceState(bundle);
    }

    private void o() {
        for (int i = 0; i < 2; i++) {
            c cVar = new c();
            cVar.a = this.i.findViewById(B[i]);
            cVar.b = (DJITextView) cVar.a.findViewById(R.id.th);
            cVar.c = (DJITextView) cVar.a.findViewById(R.id.tl);
            cVar.d = (DJITextView) cVar.a.findViewById(R.id.ti);
            cVar.e = (DJITextView) cVar.a.findViewById(R.id.tk);
            cVar.f = (DJIStickCirclePgbView) cVar.a.findViewById(R.id.tj);
            cVar.b.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
            cVar.c.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
            cVar.d.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
            cVar.e.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
            cVar.f.setProgress(0, 0);
            this.f[i] = cVar;
        }
    }

    private void p() {
        this.F = (DJISmartBatteryView) this.i.findViewById(R.id.c11);
        this.G = (DJITextView) this.i.findViewById(R.id.d3z);
        this.H = (DJITextView) this.i.findViewById(R.id.c13);
        this.I = (DJITextView) this.i.findViewById(R.id.c14);
        this.J = (DJITextView) this.i.findViewById(R.id.c15);
        this.K = (DJITextView) this.i.findViewById(R.id.c16);
        this.L = (DJITextView) this.i.findViewById(R.id.c17);
        this.M = (DJITextView) this.i.findViewById(R.id.c18);
        this.aa = (DJIImageView) this.i.findViewById(R.id.d3u);
        this.ab = (DJITextView) this.i.findViewById(R.id.d3v);
        this.F.setMax(100);
        this.F.setProgress(0);
        this.F.setSecondaryProgress(0);
        this.F.setThirdProgress(0);
        this.F.setLowWarning(0);
        this.F.setSeriousWarning(0);
        this.F.setGoHomeBattery(0);
        this.G.setText("--/--");
    }

    private void q() {
        this.ac = (DJIFpvGimbalPitchView) this.i.findViewById(R.id.c1_);
        this.ac.dispatchOnCreate();
    }

    private int f(int i) {
        if (i == 0) {
            return 0;
        }
        int i2;
        if (i >= 1024) {
            i2 = ((i - 1024) * 100) / 660;
        } else {
            i2 = 0 - (((1024 - i) * 100) / 660);
        }
        if (i2 > 100) {
            return 100;
        }
        if (i2 < -100) {
            return -100;
        }
        return i2;
    }

    private void a(int i, int i2, int i3) {
        int f = f(i2);
        int f2 = f(i3);
        this.f[i].f.setProgress(f2, f);
        if (f >= 0) {
            this.f[i].b.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(f)}));
            this.f[i].c.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
        } else {
            this.f[i].b.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
            this.f[i].c.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(-f)}));
        }
        if (f2 >= 0) {
            this.f[i].d.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
            this.f[i].e.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(f2)}));
            return;
        }
        this.f[i].d.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(-f2)}));
        this.f[i].e.setText(this.h.getString(R.string.battery_percent, new Object[]{Integer.valueOf(0)}));
    }

    public void a(dji.gs.d.c cVar) {
        this.n.a(cVar);
    }

    public void f() {
        this.n.A();
    }

    private void r() {
        if (this.k == null) {
            this.k = new dji.gs.views.a(this.h, this.n, this.l);
        }
        this.k.b();
    }

    private void s() {
        if (this.t == null) {
            this.t = new dji.pilot.fpv.view.b(this.h, this.s);
        }
        this.t.a();
    }

    public void g() {
        if (!EventView.b) {
            boolean z;
            switch (this.p) {
                case LOCK:
                    this.p = a.PLANE_CENTER;
                    this.r.hide();
                    break;
                default:
                    this.p = a.LOCK;
                    m.setImageResource(R.drawable.gs_compass_button);
                    this.r.show();
                    b(0.0f);
                    break;
            }
            dji.gs.b.b bVar = this.o;
            if (this.p != a.PLANE_CENTER) {
                z = true;
            } else {
                z = false;
            }
            bVar.a(z);
        }
    }

    public void b(float f) {
        this.n.a(f);
        if (this.p == a.PLANE_YAW_CENTER) {
            this.n.b(this.aq - f);
        } else {
            this.n.b(f);
            this.aB.sendEmptyMessage(2);
            this.aB.sendEmptyMessage(5);
        }
        if (m.isShown()) {
            m.setRotation(-f);
        }
    }

    public void a(boolean z) {
        if (z) {
            this.n.i(this.ao.a() ? this.ao : d);
        } else if (this.ap.a()) {
            this.n.s();
        } else {
            this.n.i(d);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ak5) {
            g();
        } else if (id == R.id.ak8) {
            r();
        } else if (id == R.id.ak_) {
            s();
        }
    }

    public void onEventMainThread(dji.pilot.fpv.control.k.b bVar) {
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

    public void a(float f) {
        b(90.0f + f);
    }

    public void h() {
        if (this.am != null && this.al) {
            this.am.interrupt();
        }
    }

    public void a(int i) {
        this.al = true;
        this.ak = i;
        this.aj = d.getInstance().q();
        this.ag = d.getInstance().b(i);
        x();
        if (this.ag != null) {
            if (this.ag.A != (byte) 2) {
                this.ag.A = (byte) 2;
                d.getInstance().b(this.ag);
            }
            this.au.a();
            this.an = System.currentTimeMillis();
            this.am = new Thread(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.af = i.f(this.a.h, this.a.ag);
                    if (this.a.af != null && this.a.af.size() > 0) {
                        this.a.ai = true;
                        this.a.ah = (h) this.a.af.get(0);
                        DJILogHelper.getInstance().LOGD("", "recordlist size=" + this.a.af.size());
                        if (!(this.a.aB == null || Thread.interrupted())) {
                            this.a.aB.sendEmptyMessage(0);
                        }
                    }
                    if (!(this.a.aB == null || Thread.interrupted())) {
                        this.a.aB.sendEmptyMessage(13);
                    }
                    this.a.al = false;
                }
            });
            this.am.start();
            a(new dji.gs.e.b(this.ag.E, this.ag.D));
            t();
        }
    }

    private void t() {
        DJILogHelper.getInstance().LOGD("", "updateInfoModel address " + this.ag.w);
        if (f.a(this.ag.v)) {
            DJIGeocoderResult.get(this.h, this.ag.E, this.ag.D, new com.dji.frame.b.c(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                    if (obj != null) {
                        DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) obj;
                        if (dJIGeocoderResult.status.equals("OK")) {
                            FirstLevel streetAdress = DJIGeocoderResult.getStreetAdress(dJIGeocoderResult);
                            DJILogHelper.getInstance().LOGD("", "updateInfoModel firstLevel " + streetAdress);
                            if (streetAdress != null) {
                                Iterator it = streetAdress.address_components.iterator();
                                while (it.hasNext()) {
                                    SecondLevel secondLevel = (SecondLevel) it.next();
                                    if (secondLevel.types.contains("administrative_area_level_1")) {
                                        this.a.ag.w = secondLevel.long_name;
                                    } else if (secondLevel.types.contains("administrative_area_level_2") || secondLevel.types.contains(dji.pilot.usercenter.protocol.c.W)) {
                                        this.a.ag.v = secondLevel.long_name;
                                    } else if (secondLevel.types.contains("sublocality")) {
                                        this.a.ag.u = secondLevel.long_name;
                                    } else if (secondLevel.types.contains("route")) {
                                        this.a.ag.t = secondLevel.long_name;
                                    }
                                }
                                DJILogHelper.getInstance().LOGD("", "updateInfoModel area " + this.a.ag.w);
                                DJILogHelper.getInstance().LOGD("", "updateInfoModel city " + this.a.ag.v);
                                DJILogHelper.getInstance().LOGD("", "updateInfoModel street " + this.a.ag.u);
                                DJILogHelper.getInstance().LOGD("", "updateInfoModel substreet " + this.a.ag.t);
                                this.a.ag.a(this.a.ag.a() == 1 ? 1 : 2);
                                i.b(this.a.h, this.a.ag);
                                d.getInstance().a(this.a.ag, false);
                            }
                        }
                    }
                }
            });
        }
    }

    public void a(b bVar) {
        this.au = bVar;
    }

    public void b(int i) {
        this.ax = i;
    }

    public void i() {
        if (this.ai && !this.aA) {
            dji.pilot.fpv.view.DJIErrorPopView.b bVar;
            String[] split;
            if (this.at == null) {
                this.n.v();
                DJILogHelper.getInstance().LOGD("", "recordlist start");
                this.at = (h) this.af.get(this.as);
            }
            int size = (this.as * this.aw) / this.af.size();
            long j = IPositioningSession.NotSet;
            if (this.at.a != null && this.at.a.isGetted()) {
                long flyTime = (long) ((this.at.a.getFlyTime() - this.ah.a.getFlyTime()) * 100);
                dji.gs.e.b bVar2 = new dji.gs.e.b(this.at.a.getLatitude(), this.at.a.getLongitude());
                if (bVar2.a() && this.az != null && this.az.a()) {
                    this.ay = (float) (dji.gs.utils.a.a(bVar2.b, bVar2.c, this.az.b, this.az.c) + ((double) this.ay));
                }
                this.az = bVar2;
                j = flyTime;
            }
            this.au.a(size, this.ag, j, this.ay);
            a(this.at);
            if (!this.at.h.equals("")) {
                bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = d.a;
                bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                bVar.g = DJIErrorPopView.f.a;
                split = this.at.h.split("__");
                if (split.length <= 1) {
                    bVar.c = split[0];
                } else if (dji.pilot.publics.e.d.a(split[0])) {
                    bVar.c = split[1];
                } else {
                    bVar.c = split[0];
                    bVar.e = split[1];
                }
                if (this.w.equals("") || !this.w.equals(this.at.h)) {
                    this.w = this.at.h;
                    this.v.onEventMainThread(bVar);
                }
            }
            if (!this.at.i.equals("")) {
                bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = d.b;
                bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
                bVar.g = DJIErrorPopView.f.a;
                split = this.at.i.split("__");
                if (split.length <= 1) {
                    bVar.c = split[0];
                } else if (dji.pilot.publics.e.d.a(split[0])) {
                    bVar.c = split[1];
                } else {
                    bVar.c = split[0];
                    bVar.e = split[1];
                }
                if (this.x.equals("") || !this.x.equals(this.at.i)) {
                    this.x = this.at.i;
                    this.v.onEventMainThread(bVar);
                }
            }
            if (this.as < this.af.size() - 1) {
                List list = this.af;
                int i = this.as;
                this.as = i + 1;
                long j2 = g / ((long) this.ax);
                this.at = (h) list.get(i);
                this.aB.sendEmptyMessageDelayed(10, j2);
                return;
            }
            u();
            this.au.b(this.ag);
            v();
            DJILogHelper.getInstance().LOGD("", "recordlist end");
        }
    }

    public void j() {
        this.n.v();
        this.n.z();
        if (this.ai) {
            this.av = 0;
            long currentTimeMillis = System.currentTimeMillis();
            DJILogHelper.getInstance().LOGD("", "recordlist progress initall start");
            if (this.ah.a == null || !this.ah.a.isGetted()) {
                this.av = this.ah.k.e() + ((long) this.ag.G);
            } else {
                this.av = ((long) (this.ah.a.getFlyTime() * 100)) + ((long) this.ag.G);
            }
            ArrayList arrayList = new ArrayList(this.af.size());
            ArrayList arrayList2 = new ArrayList(this.af.size());
            for (int i = 0; i < this.af.size(); i++) {
                this.at = (h) this.af.get(i);
                dji.gs.e.b bVar = new dji.gs.e.b(this.at.a.getLatitude(), this.at.a.getLongitude());
                if (bVar.a()) {
                    if (this.at.k.a() == (byte) 1) {
                        arrayList2.add(bVar);
                    }
                    arrayList.add(bVar);
                }
                if (i == this.af.size() - 1) {
                    a(this.at);
                    this.n.d(arrayList2);
                    this.n.c(arrayList);
                }
            }
            this.au.a(this.ag);
            DJILogHelper.getInstance().LOGD("", "recordlist progress initall end " + (System.currentTimeMillis() - currentTimeMillis));
            v();
        }
    }

    public void c(int i) {
        if (this.ai) {
            boolean hasMessages = this.aB.hasMessages(10);
            if (hasMessages) {
                this.aB.removeMessages(10);
            }
            this.n.v();
            v();
            int size = (int) ((((float) (this.af.size() * i)) * 1.0f) / ((float) this.aw));
            ArrayList arrayList = new ArrayList(size);
            for (int i2 = 0; i2 < size; i2++) {
                this.as = i2;
                this.at = (h) this.af.get(i2);
                if (this.at.a != null) {
                    dji.gs.e.b bVar = new dji.gs.e.b(this.at.a.getLatitude(), this.at.a.getLongitude());
                    if (bVar.a()) {
                        if (this.az != null && this.az.a()) {
                            this.ay = (float) (dji.gs.utils.a.a(bVar.b, bVar.c, this.az.b, this.az.c) + ((double) this.ay));
                        }
                        arrayList.add(bVar);
                    }
                    this.az = bVar;
                }
            }
            if (this.at == null) {
                this.as = size;
                if (hasMessages) {
                    this.aB.sendEmptyMessage(10);
                    return;
                }
                return;
            }
            this.n.b(arrayList);
            a(this.at);
            int i3 = i;
            this.au.a(i3, this.ag, (long) ((this.at.a.getFlyTime() - this.ah.a.getFlyTime()) * 100), this.ay);
            this.as = size;
            if (hasMessages) {
                this.aB.sendEmptyMessage(10);
            }
        }
    }

    public void d(int i) {
        if (this.ai) {
            this.n.v();
            v();
            long currentTimeMillis = System.currentTimeMillis();
            DJILogHelper.getInstance().LOGD("", "recordlist progress start");
            this.av = (long) ((((float) (this.ag.G * i)) * 1.0f) / ((float) this.aw));
            ArrayList arrayList = new ArrayList(this.af.size());
            for (int i2 = 0; i2 < this.af.size(); i2++) {
                this.as = i2;
                this.at = (h) this.af.get(i2);
                dji.gs.e.b bVar = new dji.gs.e.b(this.at.a.getLatitude(), this.at.a.getLongitude());
                if (bVar.a()) {
                    if (this.az != null && this.az.a()) {
                        this.ay = (float) (dji.gs.utils.a.a(bVar.b, bVar.c, this.az.b, this.az.c) + ((double) this.ay));
                    }
                    arrayList.add(bVar);
                }
                this.az = bVar;
                if (this.av < this.at.k.e() - this.ah.k.e()) {
                    this.n.b(arrayList);
                    a(this.at);
                    break;
                }
            }
            this.au.a(i, this.ag, this.av, this.ay);
            DJILogHelper.getInstance().LOGD("", "recordlist progress end " + (System.currentTimeMillis() - currentTimeMillis));
            if (i == this.aw) {
                v();
            }
        }
    }

    public void k() {
        this.aA = true;
    }

    public void l() {
        this.aA = false;
        i();
    }

    private void u() {
        if (Math.abs(this.ay - this.ag.F) > 50.0f) {
            this.ag.F = this.ay;
            this.ag.a(2);
            i.b(this.h, this.ag);
        }
    }

    private void v() {
        this.at = null;
        this.as = 0;
        this.av = 0;
        this.ay = 0.0f;
        this.az = null;
    }

    private void a(h hVar) {
        int i = 0;
        if (hVar != null) {
            dji.gs.e.b bVar;
            float yaw;
            boolean a;
            int gpsNum;
            if (hVar.a != null) {
                bVar = new dji.gs.e.b(hVar.a.getLatitude(), hVar.a.getLongitude());
                if (bVar.a() && !this.ao.a(bVar)) {
                    this.ao = bVar;
                    this.n.c(this.ao);
                }
                yaw = ((float) hVar.a.getYaw()) * 0.1f;
                if (yaw != this.aq) {
                    this.aq = yaw;
                    this.n.a(this.aq, this.p == a.PLANE_YAW_CENTER);
                }
                if (this.ap != null) {
                    this.T = dji.pilot.fpv.d.b.a(this.ap.b, this.ap.c, hVar.a.getLatitude(), hVar.a.getLongitude());
                } else {
                    this.T = 0.0f;
                }
                this.Q = Math.abs(((float) hVar.a.getZSpeed()) * 0.1f);
                this.R = (float) (Math.sqrt((double) ((hVar.a.getXSpeed() * hVar.a.getXSpeed()) + (hVar.a.getYSpeed() * hVar.a.getYSpeed()))) * 0.10000000149011612d);
                this.S = ((float) hVar.a.getHeight()) * 0.1f;
                if (this.O) {
                    this.I.setText(this.i.getResources().getString(R.string.flight_record_distance_ft_format, new Object[]{this.ad.format((double) this.N.b(this.T))}));
                    this.H.setText(this.i.getResources().getString(R.string.flight_record_distance_ft_format, new Object[]{this.ad.format((double) this.N.b(this.S))}));
                    this.K.setText(this.i.getResources().getString(R.string.flight_record_hspeed_imperial, new Object[]{Float.valueOf(this.N.c(this.R))}));
                    this.J.setText(this.i.getResources().getString(R.string.flight_record_vspeed_imperial, new Object[]{Float.valueOf(this.N.c(this.Q))}));
                } else if (this.N.v() == 2) {
                    this.I.setText(this.i.getResources().getString(R.string.flight_record_distance_m_format, new Object[]{this.ad.format((double) this.T)}));
                    this.H.setText(this.i.getResources().getString(R.string.flight_record_distance_m_format, new Object[]{this.ad.format((double) this.S)}));
                    this.K.setText(this.i.getResources().getString(R.string.flight_record_hspeed_kilometric, new Object[]{Float.valueOf(this.N.c(this.R))}));
                    this.J.setText(this.i.getResources().getString(R.string.flight_record_vspeed_kilometric, new Object[]{Float.valueOf(this.N.c(this.Q))}));
                } else {
                    this.I.setText(this.i.getResources().getString(R.string.flight_record_distance_m_format, new Object[]{this.ad.format((double) this.T)}));
                    this.H.setText(this.i.getResources().getString(R.string.flight_record_distance_m_format, new Object[]{this.ad.format((double) this.S)}));
                    this.K.setText(this.i.getResources().getString(R.string.flight_record_hspeed_metric, new Object[]{Float.valueOf(this.R)}));
                    this.J.setText(this.i.getResources().getString(R.string.flight_record_vspeed_metric, new Object[]{Float.valueOf(this.Q)}));
                }
                if (hVar.a.getZSpeed() < 0) {
                    this.J.setCompoundDrawablesWithIntrinsicBounds(R.drawable.flight_record_speed_up, 0, 0, 0);
                } else if (hVar.a.getZSpeed() > 0) {
                    this.J.setCompoundDrawablesWithIntrinsicBounds(R.drawable.flight_record_speed_down, 0, 0, 0);
                } else {
                    this.J.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                FLYC_STATE flycState = hVar.a.getFlycState();
                hVar.a.isVisionUsed();
                a = (hVar.b == null || !hVar.b.isGetted()) ? this.Z : dji.pilot.fpv.d.b.a(hVar.b.isBeginnerMode(), hVar.b.isMultipleModeOpen());
                this.Z = a;
                a(flycState, hVar.a.isVisionUsed(), ProductType.find(this.ag.Q), hVar.a.getDroneType(), hVar.a.getModeChannelByFR(), this.Z);
                gpsNum = hVar.a.getGpsNum();
                if (hVar.a.getFlycVersion() < 6 || dji.pilot.publics.e.a.a(hVar.a.getDroneType())) {
                    this.aa.setImageLevel(dji.pilot.fpv.d.b.c(gpsNum));
                } else {
                    this.aa.setImageLevel(hVar.a.getGpsLevel());
                }
                this.ab.setText("" + gpsNum);
                this.P.show();
                if (dji.pilot.fpv.d.b.a(hVar.a.getDroneType(), hVar.a.getFlycVersion(), hVar.a.getGpsNum(), hVar.a.getGpsLevel())) {
                    this.P.setText(String.format("%f", new Object[]{Double.valueOf(hVar.a.getLatitude())}) + "," + String.format("%f", new Object[]{Double.valueOf(hVar.a.getLongitude())}));
                } else {
                    this.P.setText(R.string.gs_way_point_add_point_gps_weak);
                }
            }
            if (hVar.c != null) {
                yaw = ((((float) hVar.c.getYawAngle()) * 0.1f) + this.aq) + 180.0f;
                if (yaw != this.ar) {
                    this.ar = yaw;
                    dji.gs.c.e eVar = this.n;
                    float f = this.ar;
                    float f2 = this.aq;
                    if (this.p == a.PLANE_YAW_CENTER) {
                        a = true;
                    } else {
                        a = false;
                    }
                    eVar.a(f, f2, a);
                }
                if (!(hVar.c.getPitch() == 0 || hVar.c.getPitch() == this.X)) {
                    this.X = hVar.c.getPitch();
                    this.ac.onEventMainThread(hVar.c);
                }
            }
            if (hVar.b != null) {
                bVar = new dji.gs.e.b(hVar.b.getLatitude(), hVar.b.getLongitude());
                if (bVar.a() && !this.ap.a(bVar)) {
                    if (this.ap.a()) {
                        this.n.e(bVar);
                    } else {
                        this.n.d(bVar);
                    }
                    this.ap = bVar;
                }
            }
            if (hVar.d != null && hVar.d.isGetted()) {
                a(0, hVar.d.getThrottle(), hVar.d.getRudder());
                a(1, hVar.d.getElevator(), hVar.d.getAileron());
            }
            if (hVar.g != null && hVar.g.isGetted()) {
                int batteryPercent = hVar.g.getBatteryPercent();
                this.F.setProgress(batteryPercent);
                gpsNum = this.U != batteryPercent ? 1 : 0;
                this.U = batteryPercent;
                this.L.setText(this.U + "%");
                batteryPercent = hVar.a.isGetted() ? hVar.a.getVoltageWarning() : this.W;
                int goHomeBattery = hVar.g.isGetted() ? hVar.g.getGoHomeBattery() : this.V;
                if (!(gpsNum == 0 && goHomeBattery == this.V && batteryPercent == this.W)) {
                    this.V = goHomeBattery;
                    this.W = batteryPercent;
                    if (this.U < goHomeBattery || batteryPercent == 1 || batteryPercent == 2) {
                        this.L.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_electric_low, 0, 0, 0);
                        this.L.setTextColor(this.h.getResources().getColor(R.color.a6));
                    } else {
                        this.L.setTextColor(this.h.getResources().getColor(R.color.a3));
                        this.L.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_electric_btn_normal, 0, 0, 0);
                    }
                }
                if (hVar.g.getGoHomeBattery() != 0) {
                    this.F.setGoHomeBattery(hVar.g.getGoHomeBattery());
                }
                if (hVar.g.getLowWarning() != 0) {
                    if (hVar.g.getLowWarning() > this.U) {
                        this.F.setSecondaryProgress(this.U);
                    } else if (hVar.g.getLowWarning() <= hVar.g.getGoHomeBattery()) {
                        this.F.setSecondaryProgress(hVar.g.getLowWarning());
                    } else if (hVar.g.getGoHomeBattery() > this.U) {
                        this.F.setSecondaryProgress(this.U);
                    } else {
                        this.F.setSecondaryProgress(hVar.g.getGoHomeBattery());
                    }
                }
                if (hVar.g.getSeriousLowWarning() != 0) {
                    if (hVar.g.getSeriousLowWarning() > this.U) {
                        this.F.setThirdProgress(this.U);
                    } else {
                        this.F.setThirdProgress(hVar.g.getSeriousLowWarning());
                    }
                }
                if (hVar.g.getLowWarning() != 0) {
                    this.F.setLowWarning(hVar.g.getLowWarning());
                }
                if (hVar.g.getSeriousLowWarning() != 0) {
                    this.F.setSeriousWarning(hVar.g.getSeriousLowWarning());
                }
                gpsNum = hVar.g.getUsefulTime();
                batteryPercent = this.U;
                if (gpsNum != 0) {
                    this.G.setText(e(gpsNum));
                }
                if (a == 0) {
                    a = ((int) (this.G.getPaint().measureText(c) + dji.pilot.visual.a.d.c)) + com.dji.frame.c.e.b(this.h, 2.0f);
                }
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.G.getLayoutParams();
                goHomeBattery = this.F.getWidth();
                batteryPercent = ((batteryPercent * goHomeBattery) / 100) - (a / 2);
                if (batteryPercent >= 0) {
                    i = batteryPercent > goHomeBattery - a ? goHomeBattery - a : batteryPercent;
                }
                if (i != marginLayoutParams.leftMargin || marginLayoutParams.width != a) {
                    marginLayoutParams.leftMargin = i;
                    marginLayoutParams.width = a;
                    this.G.setLayoutParams(marginLayoutParams);
                }
            }
        }
    }

    protected String e(int i) {
        int[] e = dji.pilot.fpv.d.b.e(i);
        return String.format(Locale.US, "%1$02d:%2$02d", new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])});
    }

    protected void a(FLYC_STATE flyc_state, boolean z, ProductType productType, DroneType droneType, RcModeChannel rcModeChannel, boolean z2) {
        this.M.setText(dji.pilot.fpv.d.b.a(flyc_state, z, productType, droneType, rcModeChannel, z2, true)[0]);
    }

    private void w() {
        this.n.b(DJIBaseActivity.screenWidth / 4, DJIBaseActivity.screenHeight / 4);
    }

    private SpannableString a(String str, int i, int i2, int i3) {
        int color = this.i.getResources().getColor(R.color.d6);
        SpannableString spannableString = new SpannableString(str);
        if (i3 == 0) {
            color = this.i.getResources().getDimensionPixelSize(R.dimen.rq);
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, color), i, str.length() - i2, 17);
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, color), i, str.length() - i2, 17);
        } else if (1 == i3) {
            int dimensionPixelSize = this.i.getResources().getDimensionPixelSize(R.dimen.rl);
            int dimensionPixelSize2 = this.i.getResources().getDimensionPixelSize(R.dimen.rp);
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, dimensionPixelSize), i, str.length() - i2, 17);
            spannableString.setSpan(new dji.pilot.publics.d.a.b(DJITextView.NBOLD, 0, dimensionPixelSize2, color), str.length() - i2, str.length(), 17);
        }
        return spannableString;
    }

    public dji.gs.d.e.a a() {
        return null;
    }

    public void m() {
        if (!this.al) {
            int i = this.ak + 1;
            if (this.aj > i) {
                this.ae = false;
                a(i);
            }
        }
    }

    public void n() {
        if (!this.al) {
            int i = this.ak - 1;
            if (i >= 0) {
                this.ae = false;
                a(i);
            }
        }
    }

    private void x() {
        boolean z;
        boolean z2 = true;
        b bVar = this.au;
        if (this.ak > 0) {
            z = true;
        } else {
            z = false;
        }
        bVar.a(z);
        b bVar2 = this.au;
        if (this.ak >= this.aj - 1) {
            z2 = false;
        }
        bVar2.b(z2);
    }
}
