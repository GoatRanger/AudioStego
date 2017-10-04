package dji.pilot2.scan.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.a.e;
import com.google.a.r;
import dji.pilot.R;
import dji.pilot2.scan.a.d;
import dji.pilot2.scan.view.ViewfinderView;
import dji.pilot2.utils.WiFiStateReceiver;
import dji.pilot2.utils.WiFiStateReceiver.a;
import java.util.Collection;
import java.util.Map;

public final class CaptureActivity extends Activity implements Callback, a {
    private static final String a = CaptureActivity.class.getSimpleName();
    private static final String b = "scan_ssid_string";
    private static final int c = 1025;
    private static final int d = 1026;
    private static final int e = 1027;
    private static final int f = 9;
    private SurfaceView g;
    private d h;
    private b i;
    private ViewfinderView j;
    private boolean k;
    private e l;
    private Collection<com.google.a.a> m;
    private Map<e, ?> n;
    private String o;
    private d p;
    private a q;
    private ImageButton r;
    private WiFiStateReceiver s = null;
    private String t;
    private String u;
    private boolean v = false;

    public ViewfinderView a() {
        return this.j;
    }

    public Handler b() {
        return this.i;
    }

    public d c() {
        return this.h;
    }

    public void d() {
        this.j.drawViewfinder();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.v2_pilot_scan);
        this.k = false;
        this.p = new d(this);
        this.q = new a(this);
        this.r = (ImageButton) findViewById(R.id.cxr);
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CaptureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.s = new WiFiStateReceiver();
        this.s.b(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("supplicantError");
        registerReceiver(this.s, intentFilter);
    }

    protected void onResume() {
        super.onResume();
        this.h = new d(getApplication());
        this.j = (ViewfinderView) findViewById(R.id.cxn);
        this.j.setCameraManager(this.h);
        this.i = null;
        this.g = (SurfaceView) findViewById(R.id.cxm);
        SurfaceHolder holder = this.g.getHolder();
        if (this.k) {
            a(holder);
        } else {
            holder.addCallback(this);
        }
        this.q.a();
        this.p.c();
        this.l = e.NONE;
        this.m = null;
        this.o = null;
    }

    protected void onPause() {
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        this.p.b();
        this.q.close();
        this.h.b();
        if (!this.k) {
            ((SurfaceView) findViewById(R.id.cxm)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    protected void onDestroy() {
        this.p.d();
        this.s.a(this);
        unregisterReceiver(this.s);
        super.onDestroy();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!this.k) {
            this.k = true;
            a(surfaceHolder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.k = false;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void a(r rVar, Bitmap bitmap, float f) {
        this.p.a();
        if (bitmap != null) {
            this.q.b();
            String[] split = rVar.a().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            String str = "";
            str = "";
            try {
                str = split[1].split(":")[1];
                String str2 = split[2].split(":")[1];
                this.t = str;
                this.u = str2;
                Intent intent = getIntent();
                f();
                dji.pilot2.utils.r rVar2 = new dji.pilot2.utils.r(this);
                rVar2.a();
                if (rVar2.b(this.t)) {
                    rVar2.b(rVar2.a(this.t, this.u, 3));
                    this.v = true;
                    return;
                }
                setResult(1026, intent);
                finish();
            } catch (Exception e) {
                g();
            }
        }
    }

    private void f() {
        findViewById(R.id.cxo).setVisibility(0);
        ((ImageView) findViewById(R.id.cxp)).setImageResource(R.drawable.scan_confirm_icon);
        ((TextView) findViewById(R.id.cxq)).setText(R.string.v2_phantom_fragment_scan_confirmed);
        this.j.refresh();
        a(1000);
    }

    private void g() {
        findViewById(R.id.cxo).setVisibility(0);
        ((ImageView) findViewById(R.id.cxp)).setImageResource(R.drawable.scan_not_detected);
        ((TextView) findViewById(R.id.cxq)).setText(R.string.v2_phantom_fragment_scan_failed);
        this.j.refresh();
        a(1000);
    }

    private void a(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (!this.h.a()) {
            try {
                this.h.a(surfaceHolder);
                if (this.i == null) {
                    this.i = new b(this, this.m, this.n, this.o, this.h);
                }
            } catch (Throwable e) {
                Log.w(a, e);
                h();
            } catch (Throwable e2) {
                Log.w(a, "Unexpected error initializing camera", e2);
                h();
            }
        }
    }

    private void h() {
        Builder builder = new Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Error");
        builder.setPositiveButton("OK", new c(this));
        builder.setOnCancelListener(new c(this));
        builder.show();
    }

    public void e() {
        g();
    }

    public void a(String str) {
        if (str.substring(1, str.length() - 1).equals(this.t) && this.v) {
            f();
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ CaptureActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.setResult(1025, this.a.getIntent());
                    this.a.finish();
                }
            }, 1500);
        } else if (this.v) {
            g();
        }
    }

    public void a(long j) {
        if (this.i != null) {
            this.i.sendEmptyMessageDelayed(R.id.q, j);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.h.a(motionEvent.getX(), motionEvent.getY(), this.g.getWidth(), this.g.getHeight());
        }
        return super.onTouchEvent(motionEvent);
    }
}
