package dji.pilot2.nativeexplore.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.nativeexplore.a.b;
import dji.pilot2.nativeexplore.b.d;
import dji.pilot2.nativeexplore.b.e;
import dji.pilot2.nativeexplore.b.h;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowEvent.a;
import dji.pilot2.nativeexplore.model.FollowListModel.AccountModel;
import dji.pilot2.nativeexplore.model.LikeEvent;
import dji.pilot2.publics.b.a$i;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExploreSearchActivity extends DJIActivityNoFullScreen implements a$i {
    private View A;
    private View B;
    private View C;
    private h[] D;
    private BaseAdapter[] E;
    private List<HashMap<String, String>> F;
    private String G;
    private int H;
    private boolean[] I;
    private int[] J;
    private int[] K;
    private EditText a;
    private View b;
    private View c;
    private View d;
    private View t;
    private View u;
    private View v;
    private View w;
    private TextView x;
    private List<View> y;
    private ListView z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_explore_search);
        a();
        b();
        c.a().a(this);
    }

    protected void onDestroy() {
        c.a().d(this);
        super.onDestroy();
    }

    private void a() {
        this.H = 0;
        this.J = new int[]{0, 0, 0};
        this.K = new int[]{0, 0, 0};
        this.I = new boolean[]{false, false, false};
        this.E = new BaseAdapter[3];
        this.D = new h[3];
        this.F = new ArrayList();
        int i = 0;
        while (i < 3) {
            if (i == 0 || i == 1) {
                this.E[i] = new dji.pilot2.nativeexplore.a.c(this);
            } else {
                this.E[i] = new b(this);
            }
            this.F.add(new HashMap());
            if (i == 0) {
                ((HashMap) this.F.get(i)).put("type", "photo");
            } else if (i == 1) {
                ((HashMap) this.F.get(i)).put("type", "video");
            } else {
                ((HashMap) this.F.get(i)).put("type", "account");
            }
            ((HashMap) this.F.get(i)).put("keyword", "");
            if (f.getInstance().c()) {
                ((HashMap) this.F.get(i)).put("token", f.getInstance().n());
            }
            if (i == 0 || i == 1) {
                this.D[i] = new d(this, new String[]{a$i.ag}, (Map) this.F.get(i), ParamKey.PAGE, n.am);
            } else {
                this.D[i] = new dji.pilot2.nativeexplore.b.f(this, new String[]{a$i.ag}, (Map) this.F.get(i), ParamKey.PAGE, n.am);
            }
            this.D[i].a(i);
            this.D[i].a(new i(this) {
                final /* synthetic */ ExploreSearchActivity a;

                {
                    this.a = r1;
                }

                public void a(int i, boolean z, boolean z2) {
                    this.a.I[i] = false;
                    if (i == 0 || i == 1) {
                        ((dji.pilot2.nativeexplore.a.c) this.a.E[i]).a(((d) this.a.D[i]).b);
                    } else {
                        ((b) this.a.E[i]).a(((dji.pilot2.nativeexplore.b.f) this.a.D[i]).a);
                    }
                    this.a.a(this.a.getResources().getString(R.string.v2_explore_search_empty_notice, new Object[]{this.a.G}));
                    if (this.a.H == i) {
                        this.a.E[this.a.H].notifyDataSetChanged();
                        if (this.a.E[this.a.H].getCount() <= 1) {
                            this.a.x.setVisibility(0);
                        } else {
                            this.a.x.setVisibility(4);
                        }
                    }
                    this.a.A.setVisibility(4);
                }

                public void a(int i) {
                    this.a.I[i] = true;
                    if (this.a.E[i].getCount() <= 1) {
                        this.a.A.setVisibility(0);
                    } else {
                        this.a.A.setVisibility(4);
                    }
                    this.a.x.setVisibility(4);
                }
            });
            i++;
        }
    }

    private void b() {
        this.b = findViewById(R.id.c8s);
        this.a = (EditText) findViewById(R.id.c8t);
        this.c = findViewById(R.id.c8u);
        this.u = findViewById(R.id.c8v);
        this.v = findViewById(R.id.c8x);
        this.w = findViewById(R.id.c8z);
        this.d = findViewById(R.id.c8w);
        this.t = findViewById(R.id.c8y);
        this.z = (ListView) findViewById(R.id.c90);
        this.x = (TextView) findViewById(R.id.c91);
        this.A = findViewById(R.id.d5a);
        this.B = findViewById(R.id.d5b);
        this.B.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ExploreSearchActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b(this.a.G);
            }
        });
        this.C = findViewById(R.id.d5c);
        ((AnimationDrawable) this.C.getBackground()).start();
        this.y = new ArrayList();
        this.y.add(this.c);
        this.y.add(this.d);
        this.y.add(this.t);
        this.c.setSelected(true);
        this.z.setAdapter(this.E[0]);
        this.z.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ ExploreSearchActivity a;
            private volatile boolean b = false;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.b && i == 0) {
                    this.b = false;
                    if (!this.a.D[this.a.H].b()) {
                        this.a.D[this.a.H].d();
                    }
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
        this.a.setFocusable(true);
        this.a.requestFocus();
        this.a.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ ExploreSearchActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 66) {
                    return false;
                }
                this.a.G = this.a.a.getText().toString();
                if (!this.a.G.equals("")) {
                    this.a.b(this.a.G);
                }
                return true;
            }
        });
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ ExploreSearchActivity a;

            {
                this.a = r1;
            }

            public void run() {
                ((InputMethodManager) this.a.a.getContext().getSystemService("input_method")).showSoftInput(this.a.a, 0);
            }
        }, 500);
    }

    private void a(String str) {
        this.x.setText("");
        Pattern compile = Pattern.compile("\\[.*\\]");
        String[] split = compile.split(str, -1);
        this.x.append(split[0]);
        Matcher matcher = compile.matcher(str);
        if (matcher.find()) {
            CharSequence spannableString = new SpannableString(str.substring(matcher.start() + 1, matcher.end() - 1));
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.kk)), 0, (matcher.end() - matcher.start()) - 2, 33);
            this.x.append(spannableString);
        }
        this.x.append(split[1]);
    }

    private void b(String str) {
        int i = 0;
        int i2 = 0;
        while (i2 < this.E.length) {
            if (i2 == 0 || i2 == 1) {
                ((dji.pilot2.nativeexplore.a.c) this.E[i2]).a();
            } else {
                ((b) this.E[i2]).a();
            }
            if (this.H == i2) {
                this.E[i2].notifyDataSetChanged();
            }
            i2++;
        }
        while (i < 3) {
            this.D[i].a();
            this.D[i].a("keyword", this.G);
            this.D[i].c();
            i++;
        }
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }

    private void a(int i) {
        int i2 = 0;
        this.J[i] = this.z.getFirstVisiblePosition();
        View childAt = this.z.getChildAt(0);
        int[] iArr = this.K;
        if (childAt != null) {
            i2 = childAt.getTop() - this.z.getPaddingTop();
        }
        iArr[i] = i2;
    }

    private void b(int i) {
        this.z.setSelectionFromTop(this.J[i], this.K[i]);
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c6z:
                finish();
                return;
            case R.id.c8s:
                this.G = this.a.getText().toString();
                b(this.G);
                return;
            case R.id.c8u:
                this.u.setVisibility(0);
                this.v.setVisibility(8);
                this.w.setVisibility(8);
                break;
            case R.id.c8w:
                break;
            case R.id.c8y:
                break;
            default:
                return;
        }
        if (view.getId() == R.id.c8w) {
            this.v.setVisibility(0);
            this.u.setVisibility(8);
            this.w.setVisibility(8);
        }
        a(this.H);
        if (view.getId() == R.id.c8y) {
            this.v.setVisibility(8);
            this.u.setVisibility(8);
            this.w.setVisibility(0);
        }
        int i = 0;
        while (i < 3 && i < this.y.size()) {
            ((View) this.y.get(i)).setSelected(false);
            if (this.y.get(i) == view) {
                this.H = i;
                this.z.setAdapter(this.E[i]);
                ((View) this.y.get(i)).setSelected(true);
            }
            i++;
        }
        if (this.I[this.H]) {
            if (this.E[this.H].getCount() <= 1) {
                this.A.setVisibility(0);
            }
            this.x.setVisibility(4);
        } else {
            if (this.E[this.H].getCount() > 1 || this.G == null || this.G.equals("")) {
                this.x.setVisibility(4);
            } else {
                this.x.setVisibility(0);
            }
            this.A.setVisibility(4);
        }
        b(this.H);
    }

    public void onEventMainThread(FollowEvent followEvent) {
        int i = 0;
        a aVar = followEvent.subject;
        a aVar2 = followEvent.object;
        boolean z = followEvent.action == FollowEvent.b.FOLLOW;
        for (int i2 = 0; i2 < 2; i2++) {
            if (this.D[i2] instanceof d) {
                d dVar = (d) this.D[i2];
                if (dVar.b != null) {
                    for (int i3 = 0; i3 < dVar.b.size(); i3++) {
                        ExploreItem exploreItem = (ExploreItem) dVar.b.get(i3);
                        if (exploreItem != null && exploreItem.userId.equals(aVar2.a)) {
                            exploreItem.isFollowed = z;
                        }
                    }
                }
                if (this.E[i2] instanceof dji.pilot2.nativeexplore.a.c) {
                    ((dji.pilot2.nativeexplore.a.c) this.E[i2]).a();
                    ((dji.pilot2.nativeexplore.a.c) this.E[i2]).a(dVar.b);
                }
            }
        }
        if (this.D[2] instanceof e) {
            e eVar = (e) this.D[2];
            if (eVar.a != null) {
                while (i < eVar.a.size()) {
                    AccountModel accountModel = (AccountModel) eVar.a.get(i);
                    if (accountModel != null && accountModel.id.equals(aVar2.a)) {
                        accountModel.is_follow = z;
                    }
                    i++;
                }
            }
            if (this.E[2] instanceof b) {
                ((b) this.E[2]).a();
                ((b) this.E[2]).a(eVar.a);
            }
        }
        this.E[this.H].notifyDataSetChanged();
    }

    public void onEventMainThread(LikeEvent likeEvent) {
        String str = likeEvent.id;
        boolean z = likeEvent.action == LikeEvent.a.LIKE;
        for (int i = 0; i < 2; i++) {
            if (this.D[i] instanceof d) {
                d dVar = (d) this.D[i];
                if (dVar.b != null) {
                    for (int i2 = 0; i2 < dVar.b.size(); i2++) {
                        ExploreItem exploreItem = (ExploreItem) dVar.b.get(i2);
                        if (!(exploreItem == null || !exploreItem.id.equals(str) || exploreItem.isLiked == z)) {
                            exploreItem.isLiked = z;
                            exploreItem.likeCount = likeEvent.likeCount;
                        }
                    }
                }
                if (this.E[i] instanceof dji.pilot2.nativeexplore.a.c) {
                    ((dji.pilot2.nativeexplore.a.c) this.E[i]).a();
                    ((dji.pilot2.nativeexplore.a.c) this.E[i]).a(dVar.b);
                }
            }
        }
        if (this.H == 0 || this.H == 1) {
            this.E[this.H].notifyDataSetChanged();
        }
    }

    public void onEventMainThread(ExploreEvent exploreEvent) {
        int i;
        switch (exploreEvent) {
            case USER_LOGIN:
                for (i = 0; i < 3; i++) {
                    if (this.E[i] instanceof dji.pilot2.nativeexplore.a.c) {
                        ((dji.pilot2.nativeexplore.a.c) this.E[i]).a();
                    } else if (this.E[i] instanceof b) {
                        ((b) this.E[i]).a();
                    }
                    this.D[i].a();
                    this.D[i].a("token", f.getInstance().n());
                    this.D[i].c();
                }
                return;
            case USER_LOGOUT:
                for (i = 0; i < 3; i++) {
                    if (this.E[i] instanceof dji.pilot2.nativeexplore.a.c) {
                        ((dji.pilot2.nativeexplore.a.c) this.E[i]).a();
                    } else if (this.E[i] instanceof b) {
                        ((b) this.E[i]).a();
                    }
                    this.D[i].a();
                    this.D[i].a("token", "");
                    this.D[i].c();
                }
                return;
            default:
                return;
        }
    }
}
