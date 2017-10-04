package dji.pilot2.upgrade.ble;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.b.a.c;
import dji.midware.b.a.e;
import dji.midware.b.b;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.reflect.FpvReflect;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.upgrade.ble.a.a;
import java.util.ArrayList;
import java.util.List;

public class BleDevicesActivity extends DJIActivityNoFullScreen implements OnClickListener, a {
    private static final String C = "BleDevicesActivity";
    private static final int D = 20;
    public static List<b> z;
    ObjectAnimator A;
    String B;
    private Runnable E = new Runnable(this) {
        final /* synthetic */ BleDevicesActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d.setVisibility(0);
        }
    };
    private Runnable F = new Runnable(this) {
        final /* synthetic */ BleDevicesActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d.setVisibility(8);
        }
    };
    ImageView a;
    ImageView b;
    TextView c;
    TextView d;
    ViewGroup t;
    ViewGroup u;
    ListView v;
    a w;
    e x;
    List<b> y;

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_home_ble_devices_activity);
        if (!dji.midware.b.c.getInstance().c()) {
            dji.midware.b.c.getInstance().b();
        }
        b();
    }

    private void b() {
        this.y = new ArrayList();
        findViewById(R.id.cpe).setOnClickListener(this);
        this.t = (ViewGroup) findViewById(R.id.b_s);
        this.c = (TextView) findViewById(R.id.cpf);
        this.d = (TextView) findViewById(R.id.cpg);
        this.b = (ImageView) findViewById(R.id.b_u);
        this.a = (ImageView) findViewById(R.id.b_t);
        this.v = (ListView) findViewById(R.id.b_w);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.w = new a(this);
        this.w.a(this.y);
        this.w.a((a) this);
        this.v.setAdapter(this.w);
        ObjectAnimator objectAnimator = new ObjectAnimator();
        this.A = ObjectAnimator.ofFloat(this.b, "Rotation", new float[]{0.0f, 60.0f, 120.0f, 180.0f, 240.0f, 300.0f, 360.0f}).setDuration(1000);
        this.A.setInterpolator(new AccelerateDecelerateInterpolator());
        this.A.setRepeatCount(20);
        this.A.setRepeatMode(1);
        h();
        dji.thirdparty.a.c.a().a(this);
        dji.publics.b.b.a.getInstance().a("createtime", System.currentTimeMillis() + "", false).a("action", "4", false).a("pageid", "1", false).a("device_ver", "", false).a("device_type", i.getInstance().c()._name(), true);
    }

    protected void onResume() {
        super.onResume();
        f();
    }

    protected void onDestroy() {
        super.onDestroy();
        dji.midware.b.c.getInstance().f().p();
        dji.thirdparty.a.c.a().d(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cpe) {
            finish();
        } else if (id == R.id.b_t) {
            finish();
        } else if (id == R.id.b_u) {
            a();
        } else if (id == R.id.cpf) {
            g();
        }
    }

    private void f() {
        List list = BleBannerView.a;
        if (list == null) {
            return;
        }
        if (list.isEmpty()) {
            a();
            return;
        }
        this.w.a(list);
        this.w.notifyDataSetChanged();
    }

    private void g() {
        Intent intent = new Intent();
        Class lpClass = FpvReflect.getLpClass();
        intent.addFlags(536870912);
        intent.setClass(this, lpClass);
        startActivityForResult(intent, 0);
        overridePendingTransition(17432576, 0);
        dji.publics.b.b.a.getInstance().a("createtime", System.currentTimeMillis() + "", false).a("action", "9", false).a("pageid", "1", false).a("device_ver", "", false).a("device_type", i.getInstance().c()._name(), true);
    }

    public void a() {
        if (!this.A.isRunning()) {
            this.A.start();
            if (!this.w.a().isEmpty()) {
                this.w.a().clear();
                i();
            }
            dji.midware.b.c.getInstance().f().a(this.x);
            dji.midware.b.c.getInstance().b(20);
            dji.midware.b.c.getInstance().f().f();
        }
    }

    private void h() {
        this.x = new e(this) {
            final /* synthetic */ BleDevicesActivity a;

            {
                this.a = r1;
            }

            public synchronized void onScanResultUpdate(ArrayList<b> arrayList) {
                this.a.w.a().clear();
                List arrayList2 = new ArrayList();
                arrayList2.addAll(arrayList);
                this.a.w.a(arrayList2);
                this.a.i();
                if (arrayList.isEmpty()) {
                    this.a.runOnUiThread(this.a.E);
                } else {
                    this.a.runOnUiThread(this.a.F);
                }
            }
        };
    }

    private void i() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ BleDevicesActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.w.notifyDataSetChanged();
            }
        });
    }

    public void onEventMainThread(c cVar) {
        b a;
        switch (AnonymousClass5.a[cVar.ordinal()]) {
            case 2:
                DJILogHelper.getInstance().LOGD(C, "ble test" + c.b, false, true);
                a = this.w.a(this.B);
                if (a != null) {
                    a.f = c.b;
                } else {
                    String h = dji.midware.b.c.getInstance().f().h();
                    for (b a2 : this.y) {
                        if (!a2.a.isEmpty() && !h.isEmpty() && a2.a.equals(h)) {
                            a2.f = c.b;
                        }
                    }
                }
                i();
                finish();
                return;
            case 3:
                DJILogHelper.getInstance().LOGD(C, "ble test" + c.c, false, true);
                a2 = this.w.a(this.B);
                if (a2 != null) {
                    a2.f = c.c;
                    i();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View view, View view2, int i, int i2, String str) {
        if (this.A != null && this.A.isRunning()) {
            this.A.cancel();
            dji.midware.b.c.getInstance().d();
        }
        if (i2 == R.id.b_y) {
            this.B = str;
            b bVar = (b) this.w.getItem(i);
            dji.midware.b.c.getInstance().a(bVar.a);
            bVar.f = c.d;
            DJILogHelper.getInstance().LOGD(C, "ble testitem btn clicked  name:" + bVar.b + "  address:" + bVar.a, false, true);
            this.w.notifyDataSetChanged();
            dji.publics.b.b.a.getInstance().a("createtime", System.currentTimeMillis() + "", false).a("action", "5", false).a("pageid", "1", false).a("device_ver", "", false).a("device_type", i.getInstance().c()._name(), true);
        }
    }
}
