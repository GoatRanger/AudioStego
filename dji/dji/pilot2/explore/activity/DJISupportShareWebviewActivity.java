package dji.pilot2.explore.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;
import dji.pilot2.main.fragment.DJIPhantomFragment;
import dji.pilot2.mine.e.a;
import dji.pilot2.share.b.b;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;

public class DJISupportShareWebviewActivity extends DJIFragmentActivityNoFullScreen implements o {
    public static final String U = "key_force_landscap";
    protected DJIWebviewFragment T = null;
    public boolean V = false;
    private String W = null;
    private String X = null;
    private String Y = a.a;
    private boolean Z = false;
    private boolean aa = false;
    private boolean ab = false;
    private boolean ac = false;
    private boolean ad = false;
    private boolean ae = false;
    private boolean af = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_secondary_explore);
        DJIOriLayout.setOrientationByDevice(this);
        a();
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.i5) == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(DJIWebviewFragment.o, this.W);
            bundle2.putString(DJIWebviewFragment.p, this.X);
            bundle2.putBoolean(DJIWebviewFragment.r, this.Z);
            bundle2.putBoolean(DJIWebviewFragment.s, this.aa);
            bundle2.putBoolean(DJIWebviewFragment.t, this.ab);
            bundle2.putBoolean(DJIWebviewFragment.u, this.ac);
            bundle2.putBoolean(DJIWebviewFragment.v, this.ad);
            bundle2.putBoolean(DJIWebviewFragment.w, this.ae);
            bundle2.putBoolean(DJIWebviewFragment.x, this.af);
            Fragment a = DJISupportShareWebviewFragment.a(bundle2);
            this.T = (DJIWebviewFragment) a;
            fragmentManager.beginTransaction().add(R.id.i5, a).commit();
        }
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.W = intent.getStringExtra(DJIWebviewFragment.o);
            this.X = intent.getStringExtra(DJIWebviewFragment.p);
            this.Z = intent.getBooleanExtra(DJIWebviewFragment.r, false);
            this.aa = intent.getBooleanExtra(DJIWebviewFragment.s, false);
            this.ab = intent.getBooleanExtra(DJIWebviewFragment.t, false);
            this.ac = intent.getBooleanExtra(DJIWebviewFragment.u, false);
            this.ad = intent.getBooleanExtra(DJIWebviewFragment.v, false);
            this.ae = intent.getBooleanExtra(DJIWebviewFragment.w, false);
            this.af = intent.getBooleanExtra(DJIWebviewFragment.x, false);
            this.V = getIntent().getBooleanExtra("key_force_landscap", false);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.V && getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
    }

    private void b(a.a aVar) {
        if (aVar.b.contains("forum.dji.com")) {
            this.Y = "photo";
        } else if (!"".equals(aVar.e()) && !aVar.a().equals("")) {
            this.Y = "photo";
        } else if (aVar.a().equals("")) {
            this.Y = a.a;
        } else {
            this.Y = "video";
        }
    }

    public void a(a.a aVar) {
        b(aVar);
        b bVar = new b(this);
        if (this.Y.equals("photo")) {
            bVar.a(dji.pilot2.share.e.a.a.a);
        } else if (this.Y.equals("video")) {
            bVar.a(dji.pilot2.share.e.a.a.b);
        } else {
            bVar.a(dji.pilot2.share.e.a.a.c);
        }
        bVar.a(aVar);
        bVar.a(aVar.b());
        bVar.show();
        if (this.af) {
            e.b(o.P);
        }
    }

    public void a(String str) {
        if (this.T != null) {
            this.T.b(str);
        }
    }

    protected void onPause() {
        DJIPhantomFragment.v = false;
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
        e.b((Context) this);
    }

    protected void onStop() {
        e.c((Context) this);
        super.onStop();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.T == null) {
            return super.onKeyDown(i, keyEvent);
        }
        if (!this.T.d()) {
            finish();
        }
        return true;
    }
}
