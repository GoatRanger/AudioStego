package dji.pilot2.nativeexplore.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.mine.view.RefreshableView;
import dji.pilot2.nativeexplore.a.b;
import dji.pilot2.nativeexplore.b.e;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowEvent.a;
import dji.pilot2.nativeexplore.model.FollowListModel.AccountModel;
import dji.pilot2.publics.b.a$i;
import dji.thirdparty.a.c;
import java.util.HashMap;
import java.util.Map;

public class ExploreLikesActivity extends DJIActivityNoFullScreen implements OnClickListener, a$i {
    public static final String a = "work_id";
    public static final String b = "work_type";
    public static final String c = "title";
    public static final int d = 1;
    public static final int t = 2;
    public static final int u = 0;
    private View A;
    private Dialog B;
    private b C;
    private e D;
    private String E;
    private int F;
    private String G;
    private boolean H;
    private RefreshableView v;
    private ListView w;
    private TextView x;
    private View y;
    private View z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_exlore_like_list);
        c.a().a(this);
        a();
        b();
        f();
    }

    protected void onResume() {
        super.onResume();
        this.H = true;
    }

    protected void onPause() {
        super.onPause();
        this.H = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().d(this);
    }

    private void a() {
        Intent intent = getIntent();
        this.E = intent.getStringExtra(a);
        if (this.E == null) {
            this.E = "";
        }
        this.F = intent.getIntExtra(b, 0);
        String str = "";
        if (this.F == 2) {
            str = "photos";
        } else if (this.F == 1) {
            str = "videos";
        }
        this.G = intent.getStringExtra("title");
        if (this.G == null) {
            this.G = "";
        }
        String[] strArr = new String[]{"https://www.skypixel.com/api//" + str + d.t + this.E + a$i.co_};
        Map hashMap = new HashMap();
        if (f.getInstance().c()) {
            hashMap.put("token", f.getInstance().n());
        } else {
            hashMap.put("token", "");
        }
        this.D = new e(this, strArr, hashMap, ParamKey.PAGE, n.am);
        this.D.a(new i(this) {
            final /* synthetic */ ExploreLikesActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.C.a(z2);
                this.a.C.a(this.a.D.a);
                this.a.A.setVisibility(4);
                this.a.v.finishRefreshing();
            }

            public void a(int i) {
                this.a.v.finishRefreshing();
                if (this.a.C.getCount() == 1) {
                    this.a.y.setVisibility(0);
                } else if (this.a.H && !this.a.B.isShowing()) {
                    this.a.B.show();
                }
                this.a.A.setVisibility(4);
            }
        });
        this.C = new b(this);
    }

    private void b() {
        this.v = (RefreshableView) findViewById(R.id.c8q);
        this.w = (ListView) findViewById(R.id.c8r);
        this.w.setAdapter(this.C);
        this.x = (TextView) findViewById(R.id.c8p);
        this.x.setText(this.G);
        this.y = findViewById(R.id.d5a);
        this.z = findViewById(R.id.d5b);
        this.A = findViewById(R.id.d5c);
        this.z.setOnClickListener(this);
        this.B = new dji.pilot2.nativeexplore.c.d(this);
        this.v.setOnRefreshListener(new RefreshableView.b(this) {
            final /* synthetic */ ExploreLikesActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.D.c();
            }
        }, 1000);
        this.w.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ ExploreLikesActivity a;
            private boolean b = false;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.b && i == 0) {
                    this.a.D.d();
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

    private void f() {
        this.A.setVisibility(0);
        this.D.c();
    }

    public void onEventMainThread(FollowEvent followEvent) {
        boolean z;
        int i = 0;
        a aVar = followEvent.subject;
        a aVar2 = followEvent.object;
        if (followEvent.action == FollowEvent.b.FOLLOW) {
            z = true;
        } else {
            z = false;
        }
        if (this.D.a != null) {
            while (i < this.D.a.size()) {
                AccountModel accountModel = (AccountModel) this.D.a.get(i);
                if (accountModel != null && accountModel.id.equals(aVar2.a)) {
                    accountModel.is_follow = z;
                }
                i++;
            }
        }
        this.C.notifyDataSetChanged();
    }

    public void onEventMainThread(ExploreEvent exploreEvent) {
        switch (exploreEvent) {
            case USER_LOGIN:
                this.D.a();
                this.D.a("token", f.getInstance().n());
                this.D.c();
                return;
            case USER_LOGOUT:
                this.D.a();
                this.D.a("token", "");
                this.D.c();
                return;
            default:
                return;
        }
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c6z:
                finish();
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d5b:
                this.A.setVisibility(0);
                this.y.setVisibility(4);
                this.D.c();
                return;
            default:
                return;
        }
    }
}
