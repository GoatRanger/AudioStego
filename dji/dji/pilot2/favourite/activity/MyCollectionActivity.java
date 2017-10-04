package dji.pilot2.favourite.activity;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.favourite.b.a;
import dji.pilot2.mine.view.RefreshableView;
import dji.pilot2.mine.view.RefreshableView.b;
import dji.pilot2.nativeexplore.b.g;
import dji.pilot2.nativeexplore.b.h;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.c.d;
import dji.pilot2.nativeexplore.view.e;
import dji.pilot2.utils.k;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCollectionActivity extends DJIActivityNoFullScreen implements OnClickListener {
    public static final String a = "key_force_landscap";
    private View A;
    private View B;
    private RefreshableView C;
    private List<View> D;
    private View E;
    private View F;
    private View G;
    private View H;
    private View I;
    private View J;
    private Dialog K;
    private int L = 0;
    private boolean[] M;
    public boolean b = false;
    private final String[] c = new String[]{"photo", "video", "story"};
    private a d;
    private a t;
    private g u;
    private h[] v;
    private e[] w;
    private i x;
    private ListView y;
    private View z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getIntent().getBooleanExtra("key_force_landscap", false);
        setContentView(R.layout.v2_activity_my_collection);
        c.a().a(this);
        a();
        g();
    }

    protected void onDestroy() {
        c.a().d(this);
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        if (this.b && getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
    }

    private void a() {
        int i = 0;
        this.M = new boolean[]{false, false, false};
        this.w = new e[]{new dji.pilot2.favourite.a.a(this), new dji.pilot2.favourite.a.a(this), new dji.pilot2.nativeexplore.a.e(this)};
        String v = k.v(f.getInstance().i());
        HashMap[] hashMapArr = new HashMap[3];
        for (int i2 = 0; i2 < hashMapArr.length; i2++) {
            hashMapArr[i2] = new HashMap();
            hashMapArr[i2].put("token", f.getInstance().n());
            hashMapArr[i2].put("type", this.c[i2]);
        }
        this.d = new a(this, new String[]{v}, hashMapArr[0], ParamKey.PAGE, n.am);
        this.d.a(0);
        this.t = new a(this, new String[]{v}, hashMapArr[1], ParamKey.PAGE, n.am);
        this.t.a(1);
        this.u = new g(this, new String[]{v}, hashMapArr[2], ParamKey.PAGE, n.am);
        this.u.a(2);
        this.v = new h[]{this.d, this.t, this.u};
        this.x = new i(this) {
            final /* synthetic */ MyCollectionActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                if (i == 0) {
                    this.a.w[i].a(this.a.d.e());
                }
                if (i == 1) {
                    this.a.w[i].a(this.a.t.e());
                }
                if (i == 2) {
                    this.a.w[i].a(this.a.u.e());
                }
                this.a.w[i].a(z2);
                if (i == this.a.L) {
                    this.a.C.finishRefreshing();
                    this.a.w[i].notifyDataSetChanged();
                }
                this.a.M[i] = false;
                this.a.G.setVisibility(4);
                this.a.E.setVisibility(4);
            }

            public void a(int i) {
                this.a.M[i] = true;
                if (i == this.a.L) {
                    this.a.C.finishRefreshing();
                    this.a.G.setVisibility(4);
                    if (this.a.w[i].getCount() <= 1) {
                        this.a.E.setVisibility(0);
                    } else {
                        this.a.K.show();
                    }
                }
            }
        };
        while (i < this.v.length) {
            this.v[i].a(this.x);
            i++;
        }
    }

    private void b() {
        for (h c : this.v) {
            c.c();
        }
    }

    private void f() {
        this.E = findViewById(R.id.d5a);
        this.F = findViewById(R.id.d5b);
        this.G = findViewById(R.id.d5c);
        this.F.setOnClickListener(this);
        ((AnimationDrawable) this.G.getBackground()).start();
        this.G.setVisibility(0);
        this.K = new d(this);
    }

    private void g() {
        this.y = (ListView) findViewById(R.id.cc9);
        this.z = findViewById(R.id.cc3);
        this.A = findViewById(R.id.cc5);
        this.B = findViewById(R.id.cc7);
        this.H = findViewById(R.id.cc4);
        this.I = findViewById(R.id.cc6);
        this.J = findViewById(R.id.cc8);
        this.H.setVisibility(0);
        this.I.setVisibility(4);
        this.J.setVisibility(4);
        this.C = (RefreshableView) findViewById(R.id.c9_);
        this.D = new ArrayList();
        this.D.add(this.z);
        this.D.add(this.A);
        this.D.add(this.B);
        ((View) this.D.get(0)).setSelected(true);
        for (View onClickListener : this.D) {
            onClickListener.setOnClickListener(this);
        }
        this.y.setAdapter(this.w[0]);
        f();
        b();
        this.C.setOnRefreshListener(new b(this) {
            final /* synthetic */ MyCollectionActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.v[this.a.L].c();
            }
        }, this.C.hashCode());
        this.y.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ MyCollectionActivity a;
            private boolean b = false;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.b && i == 0) {
                    this.a.v[this.a.L].d();
                    this.b = false;
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i + i2 != i3 || i3 <= 0) {
                    this.b = false;
                } else {
                    this.b = true;
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cc2:
                finish();
                return;
            case R.id.cc3:
                this.H.setVisibility(0);
                this.I.setVisibility(4);
                this.J.setVisibility(4);
                break;
            case R.id.cc5:
                break;
            case R.id.cc7:
                break;
            case R.id.d5b:
                this.G.setVisibility(0);
                this.E.setVisibility(4);
                this.v[this.L].c();
                return;
            default:
                return;
        }
        if (view.getId() == R.id.cc5) {
            this.H.setVisibility(4);
            this.I.setVisibility(0);
            this.J.setVisibility(4);
        }
        int i = this.L;
        if (view.getId() == R.id.cc7) {
            this.H.setVisibility(4);
            this.I.setVisibility(4);
            this.J.setVisibility(0);
        }
        for (int i2 = 0; i2 < this.D.size(); i2++) {
            if (view == this.D.get(i2)) {
                this.L = i2;
                if (i != this.L) {
                    for (i = 0; i < this.D.size(); i++) {
                        ((View) this.D.get(i)).setSelected(false);
                    }
                    view.setSelected(true);
                    this.w[this.L].a(this.v[this.L].b());
                    this.y.setAdapter(this.w[this.L]);
                    this.C.finishRefreshing();
                    this.G.setVisibility(4);
                    if (this.M[this.L] && this.w[this.L].getCount() <= 1) {
                        this.E.setVisibility(0);
                        return;
                    }
                }
            }
        }
        if (i != this.L) {
            for (i = 0; i < this.D.size(); i++) {
                ((View) this.D.get(i)).setSelected(false);
            }
            view.setSelected(true);
            this.w[this.L].a(this.v[this.L].b());
            this.y.setAdapter(this.w[this.L]);
            this.C.finishRefreshing();
            this.G.setVisibility(4);
            if (this.M[this.L]) {
            }
        }
    }

    public void onEventMainThread(dji.pilot2.nativeexplore.model.a aVar) {
        if (dji.pilot2.nativeexplore.model.a.b.photo.equals(aVar.b)) {
            this.v[0].c();
        } else if (dji.pilot2.nativeexplore.model.a.b.video.equals(aVar.b)) {
            this.v[1].c();
        } else if (dji.pilot2.nativeexplore.model.a.b.guide.equals(aVar.b)) {
            this.v[2].c();
        }
    }
}
