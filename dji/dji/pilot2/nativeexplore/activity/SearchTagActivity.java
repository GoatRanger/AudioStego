package dji.pilot2.nativeexplore.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.mine.view.RefreshableView;
import dji.pilot2.mine.view.RefreshableView.b;
import dji.pilot2.nativeexplore.a.c;
import dji.pilot2.nativeexplore.b.d;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.model.DeleteArtworkEvent;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowEvent.a;
import dji.pilot2.nativeexplore.model.LikeEvent;
import dji.pilot2.publics.b.a$i;
import java.util.HashMap;
import java.util.Map;

public class SearchTagActivity extends DJIActivityNoFullScreen implements OnClickListener, a$i {
    public static final String a = "search_tag";
    private String A;
    private boolean B = false;
    Dialog b;
    private RefreshableView c;
    private ListView d;
    private TextView t;
    private View u;
    private View v;
    private View w;
    private d x;
    private c y;
    private String z = "";

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_explore_search_tag);
        dji.thirdparty.a.c.a().a(this);
        a();
        b();
        f();
    }

    protected void onDestroy() {
        super.onDestroy();
        dji.thirdparty.a.c.a().d(this);
    }

    protected void onNewIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(a);
        if (!(stringExtra == null || stringExtra.equals(this.A))) {
            setIntent(intent);
            a();
            this.d.setAdapter(this.y);
            this.t.setText(this.A);
            f();
        }
        super.onNewIntent(intent);
    }

    protected void onResume() {
        super.onResume();
        if (!f.getInstance().c()) {
            this.z = "";
            this.x.a("token", this.z);
        } else if (!f.getInstance().n().equals(this.z)) {
            this.z = f.getInstance().n();
            this.x.a("token", this.z);
        }
        this.B = true;
    }

    protected void onPause() {
        super.onPause();
        this.B = false;
    }

    private void a() {
        this.A = getIntent().getStringExtra(a);
        if (this.A == null) {
            this.A = "";
        }
        if (f.getInstance().c()) {
            this.z = f.getInstance().n();
        } else {
            this.z = "";
        }
        this.b = new dji.pilot2.nativeexplore.c.d(this);
        String[] strArr = new String[]{a$i.cl_, a$i.cm_};
        Map hashMap = new HashMap();
        hashMap.put("token", this.z);
        hashMap.put("tag", this.A);
        this.x = new d(this, strArr, hashMap, ParamKey.PAGE, n.am);
        this.x.a(new i(this) {
            final /* synthetic */ SearchTagActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.y.a(this.a.x.b);
                this.a.c.finishRefreshing();
                this.a.u.setVisibility(4);
                this.a.w.setVisibility(4);
                if (z2) {
                    this.a.y.b(z2);
                }
            }

            public void a(int i) {
                this.a.c.finishRefreshing();
                if (this.a.y.getCount() == 1) {
                    this.a.u.setVisibility(0);
                } else if (this.a.B && !this.a.b.isShowing()) {
                    this.a.b.show();
                }
                this.a.w.setVisibility(4);
            }
        });
        this.y = new c(this);
    }

    private void b() {
        this.c = (RefreshableView) findViewById(R.id.c8q);
        this.d = (ListView) findViewById(R.id.c92);
        this.t = (TextView) findViewById(R.id.c70);
        this.u = findViewById(R.id.d5a);
        this.v = findViewById(R.id.d5b);
        this.w = findViewById(R.id.d5c);
        ((AnimationDrawable) this.w.getBackground()).start();
        this.v.setOnClickListener(this);
        this.t.setText(this.A);
        this.d.setAdapter(this.y);
        this.c.setOnRefreshListener(new b(this) {
            final /* synthetic */ SearchTagActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.x.c();
            }
        }, 2);
        this.d.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ SearchTagActivity a;
            private boolean b = false;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.b && i == 0) {
                    this.a.x.d();
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

    private void f() {
        this.w.setVisibility(0);
        this.x.c();
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
        if (this.x.b != null) {
            while (i < this.x.b.size()) {
                ExploreItem exploreItem = (ExploreItem) this.x.b.get(i);
                if (exploreItem != null && exploreItem.userId.equals(aVar2.a)) {
                    exploreItem.isFollowed = z;
                }
                i++;
            }
        }
        this.y.notifyDataSetChanged();
    }

    public void onEventMainThread(LikeEvent likeEvent) {
        boolean z;
        int i = 0;
        String str = likeEvent.id;
        if (likeEvent.action == LikeEvent.a.LIKE) {
            z = true;
        } else {
            z = false;
        }
        if (this.x.b != null) {
            while (i < this.x.b.size()) {
                ExploreItem exploreItem = (ExploreItem) this.x.b.get(i);
                if (!(exploreItem == null || !exploreItem.id.equals(str) || exploreItem.isLiked == z)) {
                    exploreItem.isLiked = z;
                    exploreItem.likeCount = likeEvent.likeCount;
                }
                i++;
            }
        }
        this.y.notifyDataSetChanged();
    }

    public void onEventMainThread(ExploreEvent exploreEvent) {
        switch (exploreEvent) {
            case USER_LOGIN:
                this.x.a();
                this.x.a("token", f.getInstance().n());
                this.x.c();
                return;
            case USER_LOGOUT:
                this.x.a();
                this.x.a("token", "");
                this.x.c();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(DeleteArtworkEvent deleteArtworkEvent) {
        if (this.x.a(deleteArtworkEvent.id)) {
            this.y.a();
            this.y.a(this.x.b);
            this.y.notifyDataSetChanged();
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
                this.w.setVisibility(0);
                this.u.setVisibility(4);
                this.x.c();
                return;
            default:
                return;
        }
    }
}
