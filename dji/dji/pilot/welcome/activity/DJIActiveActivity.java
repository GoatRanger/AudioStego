package dji.pilot.welcome.activity;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import dji.midware.data.manager.P3.o;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.library.d;
import dji.pilot2.usercenter.a.b;
import dji.pilot2.usercenter.activate.a;
import dji.publics.DJIUI.DJIOriLayout;
import dji.thirdparty.a.c;

public class DJIActiveActivity extends DJIActivityNoFullScreen {
    private b a;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[o.values().length];

        static {
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_activate_main_view);
        if (dji.logic.c.b.getInstance().b()) {
            a.getInstance().a(a.b.WIFI);
        } else {
            a.getInstance().a(a.b.SDR);
        }
        d.getInstance().f(true);
        this.a = new b(this, (DJIOriLayout) findViewById(R.id.fv));
        c.a().a(this);
        dji.pilot2.usercenter.activate.d.getInstance().a((Context) this);
    }

    public void a() {
        String[] strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        int checkSelfPermission = ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION");
        if (VERSION.SDK_INT < 23 || checkSelfPermission == 0) {
            dji.pilot2.usercenter.activate.d.getInstance().c();
        } else {
            ActivityCompat.requestPermissions(this, strArr, 0);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        dji.pilot2.usercenter.activate.d.getInstance().c();
    }

    protected void onStart() {
        super.onStart();
        if (dji.pilot.fpv.d.b.l(null)) {
            a();
            a.getInstance().a(dji.pilot2.usercenter.activate.d.getInstance().b());
            a.getInstance().a(dji.pilot2.usercenter.activate.d.getInstance().g());
        }
    }

    protected void onStop() {
        d.getInstance().f(false);
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
        this.a.f();
    }

    protected void onDestroy() {
        super.onDestroy();
        DJIGlobalService.b = false;
        this.a.d();
        c.a().d(this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        c.a().e(a.a.BACK_PRESSED);
        return false;
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass1.a[oVar.ordinal()]) {
            case 2:
                if (a.getInstance().f() == a.b.SDR) {
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
