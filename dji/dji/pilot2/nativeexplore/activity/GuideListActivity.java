package dji.pilot2.nativeexplore.activity;

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
import dji.pilot2.mine.view.RefreshableView;
import dji.pilot2.mine.view.RefreshableView.b;
import dji.pilot2.nativeexplore.a.e;
import dji.pilot2.nativeexplore.b.g;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.c.d;
import dji.pilot2.publics.b.a$i;
import java.util.HashMap;
import java.util.Map;

public class GuideListActivity extends DJIActivityNoFullScreen implements OnClickListener, a$i {
    private ListView a;
    private RefreshableView b;
    private g c;
    private e d;
    private View t;
    private View u;
    private View v;
    private Dialog w;
    private i x;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_guide_list);
        a();
        f();
        g();
    }

    private void a() {
        Map hashMap = new HashMap();
        hashMap.put("token", f.getInstance().n());
        this.c = new g(this, new String[]{a$i.am}, hashMap, ParamKey.PAGE, n.am);
        this.d = new e(this);
        this.x = new i(this) {
            final /* synthetic */ GuideListActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.d.a(this.a.c.e());
                this.a.b.finishRefreshing();
                if (z2) {
                    this.a.d.a(z2);
                }
                this.a.d.notifyDataSetChanged();
                this.a.v.setVisibility(4);
                this.a.t.setVisibility(4);
            }

            public void a(int i) {
                this.a.b.finishRefreshing();
                this.a.v.setVisibility(4);
                if (this.a.d.getCount() <= 1) {
                    this.a.t.setVisibility(0);
                } else {
                    this.a.w.show();
                }
            }
        };
        this.c.a(this.x);
    }

    private void b() {
        this.t = findViewById(R.id.d5a);
        this.u = findViewById(R.id.d5b);
        this.v = findViewById(R.id.d5c);
        this.u.setOnClickListener(this);
        ((AnimationDrawable) this.v.getBackground()).start();
        this.v.setVisibility(0);
        this.w = new d(this);
    }

    private void f() {
        this.a = (ListView) findViewById(R.id.c9a);
        this.b = (RefreshableView) findViewById(R.id.c9_);
        this.a.setAdapter(this.d);
        this.a.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ GuideListActivity a;
            private boolean b = false;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.b && i == 0) {
                    this.a.c.d();
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
        this.b.setOnRefreshListener(new b(this) {
            final /* synthetic */ GuideListActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.c.c();
            }
        }, this.b.hashCode());
        b();
    }

    private void g() {
        this.c.c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c99:
                finish();
                return;
            case R.id.d5b:
                this.v.setVisibility(0);
                this.t.setVisibility(4);
                this.c.c();
                return;
            default:
                return;
        }
    }
}
