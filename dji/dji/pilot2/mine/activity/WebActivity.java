package dji.pilot2.mine.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.publics.object.c$e;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;

public class WebActivity extends DJIFragmentActivityNoFullScreen {
    public static final String s = "WebActivity";
    public static final String t = "title_text";
    public static final String w = "key_force_landscap";
    private boolean A;
    private TextView B;
    private Handler C = new Handler();
    private String a = "about:blank";
    private String r;
    protected DJIWebviewFragment u = null;
    protected boolean v = false;
    public boolean x = false;
    private String y;
    private boolean z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_web);
        this.x = getIntent().getBooleanExtra("key_force_landscap", false);
        if (!this.v) {
            DJIOriLayout.setOrientationByDevice(this);
        }
        a();
        f();
        g();
        if (this.A) {
            this.C.post(new Runnable(this) {
                final /* synthetic */ WebActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.A) {
                        this.a.finish();
                    }
                }
            });
        }
    }

    private void a() {
        Intent intent = getIntent();
        this.a = intent.getStringExtra(DJIWebviewFragment.o);
        if (this.a == null) {
            this.a = "about:blank";
        }
        this.y = intent.getStringExtra("title_text");
        this.r = intent.getStringExtra(DJIWebviewFragment.p);
        this.z = intent.getBooleanExtra(DJIWebviewFragment.r, false);
        this.A = intent.getBooleanExtra(DJIWebviewFragment.q, false);
    }

    protected void onResume() {
        super.onResume();
        if (this.x && getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
    }

    private void f() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment findFragmentById = fragmentManager.findFragmentById(R.id.ck);
        if (findFragmentById == null) {
            Bundle bundle = new Bundle();
            bundle.putString(DJIWebviewFragment.o, this.a);
            bundle.putString(DJIWebviewFragment.p, this.r);
            bundle.putBoolean(DJIWebviewFragment.r, this.z);
            findFragmentById = DJIWebviewFragment.b(bundle);
            fragmentManager.beginTransaction().add(R.id.ck, findFragmentById).commit();
        }
        this.u = (DJIWebviewFragment) findFragmentById;
        this.u.a(new c$e(this) {
            final /* synthetic */ WebActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                if ("...".equals(this.a.B.getText())) {
                    this.a.B.setText(str);
                }
            }
        });
    }

    private void g() {
        this.B = (TextView) findViewById(R.id.d09);
        if (this.y != null) {
            this.B.setText(this.y);
        }
    }

    public void c() {
        super.c();
        if (this.u == null) {
            finish();
        } else if (!this.u.d()) {
            finish();
        }
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.d08:
                c();
                return;
            default:
                return;
        }
    }
}
