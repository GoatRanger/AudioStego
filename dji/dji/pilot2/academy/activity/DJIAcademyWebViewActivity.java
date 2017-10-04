package dji.pilot2.academy.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.publics.object.c$e;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;

public class DJIAcademyWebViewActivity extends DJIFragmentActivityNoFullScreen {
    public static final String a = "DJIAcademyWebViewActivity";
    public static final String r = "title_text";
    private String s = "about:blank";
    private String t;
    private String u;
    private boolean v;
    private TextView w;
    private DJIWebviewFragment x = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_web);
        DJIOriLayout.setOrientationByDevice(this);
        a();
        f();
        g();
    }

    private void a() {
        Intent intent = getIntent();
        this.s = intent.getStringExtra(DJIWebviewFragment.o);
        if (this.s == null) {
            this.s = "about:blank";
        }
        this.u = intent.getStringExtra("title_text");
        this.t = intent.getStringExtra(DJIWebviewFragment.p);
        this.v = intent.getBooleanExtra(DJIWebviewFragment.r, false);
    }

    private void f() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment findFragmentById = fragmentManager.findFragmentById(R.id.ck);
        if (findFragmentById == null) {
            Bundle bundle = new Bundle();
            bundle.putString(DJIWebviewFragment.o, this.s);
            bundle.putString(DJIWebviewFragment.p, this.t);
            bundle.putBoolean(DJIWebviewFragment.r, this.v);
            findFragmentById = DJIWebviewFragment.b(bundle);
            fragmentManager.beginTransaction().add(R.id.ck, findFragmentById).commit();
        }
        this.x = (DJIWebviewFragment) findFragmentById;
        this.x.a(new c$e(this) {
            final /* synthetic */ DJIAcademyWebViewActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                if ("...".equals(this.a.w.getText())) {
                    this.a.w.setText(str);
                }
            }
        });
    }

    private void g() {
        this.w = (TextView) findViewById(R.id.d09);
        if (this.u != null) {
            this.w.setText(this.u);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    public void c() {
        super.c();
        if (this.x == null) {
            finish();
        } else if (!this.x.d()) {
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
