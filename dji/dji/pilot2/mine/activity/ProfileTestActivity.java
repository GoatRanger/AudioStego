package dji.pilot2.mine.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dji.frame.c.h;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$m;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.profile.DJIEditProfileActivity;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.main.fragment.DJIMineFragment;
import dji.pilot2.mine.a.b;
import dji.pilot2.mine.b.e;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.LevelInfo;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.MedalInfo;
import dji.pilot2.mine.view.RefreshableView;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.model.CheckFollowResultModel;
import dji.pilot2.nativeexplore.model.DeleteArtworkEvent;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowListModel.AccountModel;
import dji.pilot2.nativeexplore.model.FollowResultModel;
import dji.pilot2.nativeexplore.model.LikeEvent;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.thirdparty.a.c;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileTestActivity extends DJIActivityNoFullScreen implements OnClickListener, c$m, a$i {
    public static final String G = "user_id";
    public static final String H = "user_name";
    public static final String I = "avatar_url";
    public static final String J = "country";
    public static final String K = "tab";
    public static final int L = 0;
    public static final int M = 1;
    public static final int N = 2;
    DisplayImageOptions O;
    e$a P = new e$a(this) {
        final /* synthetic */ ProfileTestActivity a;

        {
            this.a = r1;
        }

        public void a(int i, int i2, int i3, Object obj, Object obj2) {
            switch (i) {
                case d.o /*196656*/:
                    if (this.a.X == null || this.a.X.equals("")) {
                        this.a.X = f.getInstance().i();
                        this.a.T = f.getInstance().i();
                        if (this.a.at != null) {
                            this.a.at.c();
                            this.a.at.d(null);
                        }
                        this.a.n();
                        this.a.p();
                        this.a.o();
                        DJIMineFragment.P.sendEmptyMessage(1);
                        return;
                    }
                    return;
                case d.q /*196688*/:
                    this.a.n();
                    return;
                default:
                    return;
            }
        }

        public void a(int i, int i2, int i3, Object obj) {
        }

        public void a(int i, long j, long j2, int i2, Object obj) {
        }

        public void a(int i, boolean z, int i2, Object obj) {
        }
    };
    private String T;
    private String U;
    private String V;
    private String W;
    private String X;
    private RefreshableView aA;
    private RelativeLayout aB;
    private View aC;
    private LinearLayout aD;
    private ImageView aE;
    private ImageView aF;
    private ImageView aG;
    private ImageView aH;
    private TextView aI;
    private TextView aJ;
    private TextView aK;
    private TextView aL;
    private TextView aM;
    private View aN;
    private ProgressBar aO;
    private View aP;
    private View aQ;
    private ImageView aR;
    private TextView aS;
    private View aT;
    private List<ViewGroup> aU;
    private List<View> aV;
    private ImageView aW;
    private TextView aX;
    private int aY = 0;
    private int aZ = 0;
    private dji.pilot2.mine.b.a at;
    private b au;
    private dji.pilot2.nativeexplore.b.f av;
    private dji.pilot2.nativeexplore.b.f aw;
    private dji.pilot2.mine.a.d ax;
    private dji.pilot2.mine.a.d ay;
    private ExpandableListView az;
    private boolean ba = false;
    private boolean bb = true;
    private boolean bc = true;
    private int bd = 0;
    private int be = 0;
    private int bf = 0;
    private View bg;
    private View bh;
    private boolean bi = true;
    private dji.pilot2.mine.b.a.a bj = new dji.pilot2.mine.b.a.a(this) {
        final /* synthetic */ ProfileTestActivity a;

        {
            this.a = r1;
        }

        public void a() {
            synchronized (this.a.at) {
                this.a.au.a();
                if (this.a.bd == 0) {
                    this.a.au.notifyDataSetChanged();
                }
                this.a.k();
                this.a.aA.finishRefreshing();
                DJILogHelper.getInstance().LOGE("Lyric", "artwork loadding success");
            }
        }

        public void b() {
            DJILogHelper.getInstance().LOGE("Lyric", "artwork loadding failed");
            this.a.aA.finishRefreshing();
            if (this.a.bd != 0) {
            }
        }
    };
    private a bk = new a(this) {
        final /* synthetic */ ProfileTestActivity a;

        {
            this.a = r1;
        }

        public View a() {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_mine_profile_artwork_empty, null);
            TextView textView = (TextView) inflate.findViewById(R.id.ct4);
            if (this.a.T.equals(this.a.X)) {
                textView.setText(R.string.mine_profile_empty_text);
            } else {
                inflate.setVisibility(4);
            }
            return inflate;
        }
    };
    private a bl = new a(this) {
        final /* synthetic */ ProfileTestActivity a;

        {
            this.a = r1;
        }

        public View a() {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_mine_profile_artwork_empty, null);
            TextView textView = (TextView) inflate.findViewById(R.id.ct4);
            ((ImageView) inflate.findViewById(R.id.ct3)).setVisibility(8);
            if (this.a.T.equals(this.a.X)) {
                textView.setText(R.string.v2_explore_host_empty_following);
            } else {
                textView.setText(R.string.v2_explore_other_empty_following);
            }
            inflate.setPadding(0, (int) this.a.getResources().getDimension(R.dimen.fu), 0, 0);
            return inflate;
        }
    };
    private a bm = new a(this) {
        final /* synthetic */ ProfileTestActivity a;

        {
            this.a = r1;
        }

        public View a() {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_mine_profile_artwork_empty, null);
            TextView textView = (TextView) inflate.findViewById(R.id.ct4);
            ((ImageView) inflate.findViewById(R.id.ct3)).setVisibility(8);
            if (this.a.T.equals(this.a.X)) {
                textView.setText(R.string.v2_explore_host_empty_follower);
            } else {
                textView.setText(R.string.v2_explore_other_empty_follower);
            }
            inflate.setPadding(0, (int) this.a.getResources().getDimension(R.dimen.fu), 0, 0);
            return inflate;
        }
    };

    public interface a {
        View a();
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_acitivty_profile_test);
        c.a().a(this);
        g();
        h();
        if (this.T != null && this.T.equals(f.getInstance().i())) {
            f.getInstance().a(this.P);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().d(this);
        if (this.T != null && this.T.equals(f.getInstance().i())) {
            f.getInstance().b(this.P);
        }
        this.at.b(this.bj);
    }

    protected void onResume() {
        super.onResume();
        new Handler(getMainLooper()).postDelayed(new Runnable(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.bf == 0) {
                    this.a.bf = this.a.i() + this.a.bh.getHeight();
                    DJILogHelper.getInstance().LOGI("bob", "getStatusBarHeight() = " + this.a.i() + " mHeadbarView.getHeight()=" + this.a.bg.getHeight());
                    View findViewById = this.a.aD.findViewById(R.id.cys);
                    if (findViewById != null) {
                        findViewById.setLayoutParams(new LayoutParams(-1, this.a.bf));
                        findViewById.invalidate();
                    }
                    this.a.bg.setPadding(0, this.a.i(), 0, 0);
                }
                if (this.a.bi) {
                    this.a.au.a(true);
                    this.a.at.d(new dji.pilot2.mine.b.a.a(this) {
                        final /* synthetic */ AnonymousClass16 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.a.au.a(false);
                        }

                        public void b() {
                            this.a.a.au.a(false);
                        }
                    });
                    this.a.bi = false;
                }
            }
        }, 10);
        n();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    private void b() {
        this.aW.setImageResource(R.drawable.v2_mine_back_pic);
        if (this.aX.getVisibility() == 0) {
            Drawable drawable = getResources().getDrawable(R.drawable.v2_mine_edit_pic);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.aX.setCompoundDrawables(drawable, null, null, null);
            return;
        }
        this.aT.setBackgroundResource(R.drawable.v2_profile_follow_button_background);
        this.aS.setTextColor(getBaseContext().getResources().getColorStateList(R.color.om));
        if (this.ba) {
            this.aR.setImageResource(R.drawable.v2_profile_followed_icon);
        } else {
            this.aR.setImageResource(R.drawable.v2_profile_follow_icon);
        }
    }

    private void f() {
        this.aW.setImageResource(R.drawable.v2_explore_arrow_left_back);
        if (this.aX.getVisibility() == 0) {
            Drawable drawable = getResources().getDrawable(R.drawable.v2_mine_edit_black_pic);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.aX.setCompoundDrawables(drawable, null, null, null);
            return;
        }
        this.aT.setBackgroundResource(R.drawable.v2_profile_follow_button_black_background);
        this.aS.setTextColor(getBaseContext().getResources().getColorStateList(R.color.lv));
        if (this.ba) {
            this.aR.setImageResource(R.drawable.v2_profile_followed_black_icon);
        } else {
            this.aR.setImageResource(R.drawable.v2_profile_follow_black_icon);
        }
    }

    private void g() {
        Intent intent = getIntent();
        this.T = intent.getStringExtra("user_id");
        this.U = intent.getStringExtra("user_name");
        this.V = intent.getStringExtra(I);
        this.W = intent.getStringExtra("country");
        this.be = intent.getIntExtra(K, 0);
        Log.i("DJINotificationExploreFragment", "receive id:" + this.T);
        this.X = f.getInstance().i();
        this.O = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
        if (this.T == null || this.T.equals("") || (this.X != null && this.X.equals(this.T))) {
            this.T = this.X;
            this.U = f.getInstance().m();
            this.at = dji.pilot2.mine.b.a.getInstance();
        } else {
            this.at = new dji.pilot2.mine.b.a(this.T);
        }
        this.at.a(this.bj);
        this.au = new b(this, new ArrayList());
        this.au.a(this.at);
        this.au.a(this.bk);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.au.a(1, 0);
        } else {
            this.au.a(1, 0);
        }
        this.aY = 0;
        this.aZ = 0;
    }

    private void h() {
        this.bg = findViewById(R.id.c4h);
        this.bh = this.bg;
        j();
        k();
        this.aI = (TextView) findViewById(R.id.c4n);
        this.aI.setVisibility(4);
        this.aA = (RefreshableView) findViewById(R.id.c4f);
        this.aA.setOnRefreshListener(new RefreshableView.b(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.bd == 0) {
                    if (!this.a.at.b()) {
                        this.a.at.d(null);
                    }
                } else if (this.a.bd == 1) {
                    this.a.av.c();
                } else if (this.a.bd == 2) {
                    this.a.aw.c();
                }
            }
        }, 0);
        this.az = (ExpandableListView) findViewById(R.id.c4g);
        this.az.addHeaderView(this.aD, null, false);
        this.az.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return true;
            }
        });
        this.az.setAdapter(this.au);
        this.az.setGroupIndicator(null);
        this.az.setDivider(null);
        this.az.setChildDivider(null);
        this.az.setDividerHeight(0);
        for (int i = 0; i < this.au.getGroupCount(); i++) {
            this.az.expandGroup(i);
        }
        this.au.a(new b.b(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void a() {
                for (int i = 0; i < this.a.au.getGroupCount(); i++) {
                    this.a.az.expandGroup(i);
                }
            }
        });
        this.az.setOnScrollListener(new OnScrollListener(this) {
            View a = null;
            boolean b = false;
            final /* synthetic */ ProfileTestActivity c;

            {
                this.c = r2;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.c.bd == 0) {
                    if (this.b && !this.c.at.b() && i == 0) {
                        this.c.at.c(null);
                        this.b = false;
                        return;
                    }
                    DJILogHelper.getInstance().LOGI("ArtworkUpdate", "artwork loading");
                } else if (this.c.bd == 1 && !this.c.bb) {
                    this.c.bb = true;
                    this.c.av.d();
                } else if (this.c.bd == 2 && !this.c.bc) {
                    this.c.bc = true;
                    this.c.aw.d();
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i + i2 == i3 && i3 > 0) {
                    this.b = true;
                }
                int scrollY = this.c.az.getScrollY();
                int height = this.c.aD.getHeight();
                int height2 = this.c.aQ.getHeight();
                int top = this.c.aD.getTop();
                float f = 0.0f;
                if (top != 0) {
                    float j = 1.0f - (((float) (((height + top) - height2) - this.c.bf)) / ((float) ((height - height2) - this.c.bf)));
                    if (((double) j) == 0.0d) {
                        this.c.bg.setBackgroundResource(R.color.j5);
                        this.c.b();
                        f = j;
                    } else if (j >= 1.0f) {
                        this.c.bg.setBackgroundResource(R.color.om);
                        this.c.f();
                        f = j;
                    } else {
                        int i4 = (int) (255.0f * j);
                        if (i4 < FTPCodes.USER_LOGGED_IN) {
                            i4 = FTPCodes.USER_LOGGED_IN;
                        }
                        this.c.bg.setBackgroundColor(Color.rgb(i4, i4, i4));
                        this.c.bg.setAlpha((1.3f * j) + 0.4f);
                        this.c.b();
                        f = j;
                    }
                } else {
                    this.c.bg.setBackgroundResource(R.color.j5);
                    this.c.bg.setAlpha(1.0f);
                    this.c.b();
                }
                DJILogHelper.getInstance().LOGI("bob", "setBackground xx " + scrollY + " hh= " + height + " top=" + top + " trans= " + f + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + height2);
                if (this.a != absListView.getChildAt(0)) {
                    this.a = absListView.getChildAt(0);
                    if (this.a == this.c.aD) {
                        this.c.aI.setText("");
                    } else {
                        this.c.aI.setText(this.c.U);
                    }
                    DJILogHelper.getInstance().LOGI("bob", "setBackground cc " + scrollY + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + height);
                }
            }
        });
        if (this.X != null && !this.X.equals(this.T)) {
            findViewById(R.id.c4k).setVisibility(4);
        }
    }

    private int i() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void j() {
        this.aD = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.v2_profile_user_info_header, null);
        this.aC = this.aD;
        this.aE = (ImageView) this.aD.findViewById(R.id.cyx);
        this.aF = (ImageView) this.aD.findViewById(R.id.cyw);
        this.aG = (ImageView) this.aC.findViewById(R.id.cyn);
        this.aH = (ImageView) this.aC.findViewById(R.id.cyq);
        this.aK = (TextView) this.aD.findViewById(R.id.cyu);
        this.aJ = (TextView) this.aC.findViewById(R.id.c9t);
        this.aL = (TextView) this.aC.findViewById(R.id.c9u);
        this.aM = (TextView) this.aC.findViewById(R.id.c9y);
        this.aN = this.aC.findViewById(R.id.cyp);
        this.aO = (ProgressBar) this.aC.findViewById(R.id.c9w);
        this.aP = this.aC.findViewById(R.id.c9x);
        this.aQ = this.aD.findViewById(R.id.cyt);
        l();
        m();
        p();
        this.aH.setImageResource(R.drawable.v2_medal_pilot_1_black);
        this.aP.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Dialog bVar = new dji.pilot2.mine.c.b(this.a);
                bVar.getWindow().setGravity(21);
                Point point = new Point();
                this.a.getWindowManager().getDefaultDisplay().getSize(point);
                int i = point.x;
                int i2 = point.y;
                if (this.a.aP != null) {
                    int[] iArr = new int[2];
                    this.a.aP.getLocationInWindow(iArr);
                    i2 = iArr[1] - (i2 / 2);
                    bVar.getWindow().getAttributes().x = (i - iArr[0]) + (this.a.aP.getWidth() / 2);
                    bVar.getWindow().getAttributes().y = i2;
                    bVar.show();
                }
            }
        });
        String i = f.getInstance().i();
        String k = e.getInstance().k();
        String j = f.getInstance().j();
        DJILogHelper.getInstance().LOGI("Lyric", "currentEmail: " + j);
        DJILogHelper.getInstance().LOGI("Lyric", "savedUserEmail: " + k);
        if (i != null && i.equals(this.T) && k != null && k.equalsIgnoreCase(j)) {
            i = e.getInstance().c(f.getInstance().j());
            if (!(i == null || i.equals(""))) {
                a(i, false, null);
            }
        }
        o();
        this.aE.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.aE.setImageResource(R.drawable.v2_artwork_grid_enable);
                this.a.aF.setImageResource(R.drawable.v2_artwork_list_disable);
                this.a.au.a(3, 1);
                if (this.a.bd == 0) {
                    this.a.au.notifyDataSetChanged();
                }
            }
        });
        this.aF.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.aE.setImageResource(R.drawable.v2_artwork_grid_disable);
                this.a.aF.setImageResource(R.drawable.v2_artwork_list_enable);
                this.a.au.a(1, 0);
                if (this.a.bd == 0) {
                    this.a.au.notifyDataSetChanged();
                }
            }
        });
        this.aN.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                dji.pilot.fpv.d.e.b(c$m.dJ_);
                Intent intent = new Intent(this.a, MedalActivity.class);
                intent.putExtra("user_id", this.a.T);
                this.a.startActivity(intent);
            }
        });
    }

    private void k() {
        this.aK.setText(getResources().getString(R.string.mine_profile_artwork_count, new Object[]{Integer.valueOf(this.at.a())}));
    }

    private void l() {
        if (this.aT == null || this.aR == null || this.aS == null) {
            View findViewById = findViewById(R.id.c4h);
            this.aT = findViewById.findViewById(R.id.aih);
            this.aR = (ImageView) findViewById.findViewById(R.id.c4l);
            this.aS = (TextView) findViewById.findViewById(R.id.c4m);
            this.aW = (ImageView) findViewById.findViewById(R.id.c4j);
            this.aX = (TextView) findViewById.findViewById(R.id.c4k);
        }
        if (this.T == null || this.T.equals(this.X)) {
            this.aT.setVisibility(8);
        } else {
            this.aT.setVisibility(0);
        }
        a(false);
        String str = "https://www.skypixel.com/api/users/" + this.T + a$i.aa;
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        if (f.getInstance().c()) {
            bVar.a("token", f.getInstance().n());
        } else {
            bVar.a("token", "");
        }
        com.dji.frame.c.c.b(this).a(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (str != null) {
                    CheckFollowResultModel checkFollowResultModel = (CheckFollowResultModel) h.b(str, CheckFollowResultModel.class);
                    if (checkFollowResultModel != null && checkFollowResultModel.status == 0) {
                        this.a.a(checkFollowResultModel.is_follow);
                    }
                }
            }

            public void a(Throwable th, int i, String str) {
            }
        });
        this.aT.setOnClickListener(this);
    }

    private void a(boolean z) {
        this.ba = z;
        this.aT.setSelected(z);
        CharSequence string = getResources().getString(R.string.v2_explore_follow);
        int i = R.drawable.v2_profile_follow_icon;
        if (z) {
            string = getResources().getString(R.string.v2_explore_followed);
            i = R.drawable.v2_profile_followed_icon;
        }
        this.aS.setText(string);
        this.aR.setImageResource(i);
    }

    private void m() {
        int i = 0;
        this.bd = 0;
        this.aU = new ArrayList();
        this.aV = new ArrayList();
        this.aU.add((ViewGroup) this.aC.findViewById(R.id.beb));
        this.aU.add((ViewGroup) this.aC.findViewById(R.id.beh));
        this.aU.add((ViewGroup) this.aC.findViewById(R.id.bek));
        ((ViewGroup) this.aU.get(0)).setSelected(true);
        this.aV.add(this.aC.findViewById(R.id.bec));
        this.aV.add(this.aC.findViewById(R.id.bei));
        this.aV.add(this.aC.findViewById(R.id.bel));
        while (i < this.aU.size()) {
            ((ViewGroup) this.aU.get(i)).setOnClickListener(this);
            i++;
        }
    }

    private void n() {
        String i = f.getInstance().i();
        if (i == null || !i.equals(this.T)) {
            if (this.U == null) {
                this.aJ.setText("");
            } else if (this.U.length() > 15) {
                this.aJ.setText(this.U.substring(0, 15) + "...");
            } else {
                this.aJ.setText(this.U);
            }
            ImageLoader.getInstance().displayImage(this.V, this.aG, this.O);
            return;
        }
        i = f.getInstance().e();
        CharSequence m = f.getInstance().m();
        String str = f.getInstance().h().l;
        if (m == null) {
            this.aJ.setText("");
        } else if (m.length() > 15) {
            this.aJ.setText(m.substring(0, 15) + "...");
        } else {
            this.aJ.setText(m);
        }
        if (i != null && !i.equals("")) {
            File file = new File(i);
            if (file.exists() && file.isFile()) {
                this.aG.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            } else if (str == null || str.equals("")) {
                this.aG.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
            } else {
                ImageLoader.getInstance().displayImage(str, this.aG, this.O);
            }
        } else if (str == null || str.equals("")) {
            this.aG.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
        } else {
            ImageLoader.getInstance().displayImage(str, this.aG, this.O);
        }
    }

    private void o() {
        final String i = f.getInstance().i();
        final String j = f.getInstance().j();
        f.getInstance().n();
        com.dji.frame.c.c.b(this).a(k.t(this.T), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ ProfileTestActivity c;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (i == null || !i.equals(this.c.T)) {
                    this.c.a(str, false, null);
                } else {
                    this.c.a(str, true, j);
                }
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }

    private void a(String str, boolean z, String str2) {
        UserLevelJsonBean userLevelJsonBean = null;
        if (!(str == null || str.equals(""))) {
            userLevelJsonBean = (UserLevelJsonBean) h.b(str, UserLevelJsonBean.class);
        }
        if (userLevelJsonBean != null && userLevelJsonBean.level != null) {
            LevelInfo levelInfo = userLevelJsonBean.level;
            MedalInfo medalInfo = userLevelJsonBean.medal;
            if (levelInfo != null) {
                this.aL.setText("LV" + levelInfo.level);
                this.aM.setText(levelInfo.exp + d.t + levelInfo.nextexp);
                this.aO.setMax(levelInfo.nextexp);
                this.aO.setProgress(levelInfo.exp);
                if (medalInfo == null || medalInfo.level == null || medalInfo.level.level <= 0) {
                    Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), dji.pilot2.mine.b.d.a[0]);
                    Bitmap a = dji.pilot2.utils.a.a(decodeResource);
                    decodeResource.recycle();
                    this.aH.setImageBitmap(a);
                } else if (medalInfo.level.level < 6) {
                    this.aH.setImageResource(dji.pilot2.mine.b.d.a[medalInfo.level.level - 1]);
                }
                if (z) {
                    e.getInstance().a(str2, str);
                }
            }
        }
    }

    private void p() {
        String[] strArr = new String[]{"https://www.skypixel.com/api/users/" + this.T + a$i.ac};
        String[] strArr2 = new String[]{"https://www.skypixel.com/api/users/" + this.T + a$i.ad};
        Map hashMap = new HashMap();
        if (f.getInstance().c()) {
            hashMap.put("token", f.getInstance().n());
        } else {
            hashMap.put("token", "");
        }
        this.av = new dji.pilot2.nativeexplore.b.f(this, strArr, hashMap, ParamKey.PAGE, n.am);
        this.aw = new dji.pilot2.nativeexplore.b.f(this, strArr2, hashMap, ParamKey.PAGE, n.am);
        this.ax = new dji.pilot2.mine.a.d(this, dji.pilot2.mine.a.d.a.FOLLOWER);
        this.ax.a(this.bm);
        this.ay = new dji.pilot2.mine.a.d(this, dji.pilot2.mine.a.d.a.FOLLOWING);
        this.ay.a(this.bl);
        this.av.a(new i(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.ax.a(this.a.av.a);
                this.a.aY = this.a.av.e();
                DJILogHelper.getInstance().LOGD("Lyric", "follower count: " + Integer.toString(this.a.aY));
                if (this.a.bd == 1) {
                    this.a.ax.notifyDataSetChanged();
                    this.a.aA.finishRefreshing();
                }
                this.a.bb = false;
            }

            public void a(int i) {
                if (this.a.bd == 1) {
                    this.a.aA.finishRefreshing();
                }
                this.a.bb = false;
            }
        });
        this.ax.a(new b.b(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.bd == 1) {
                    for (int i = 0; i < this.a.ax.getGroupCount(); i++) {
                        this.a.az.expandGroup(i);
                    }
                }
            }
        });
        this.aw.a(new i(this) {
            final /* synthetic */ ProfileTestActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.ay.a(this.a.aw.a);
                this.a.aZ = this.a.aw.e();
                DJILogHelper.getInstance().LOGD("Lyric", "following count: " + Integer.toString(this.a.aZ));
                if (this.a.bd == 2) {
                    this.a.ay.notifyDataSetChanged();
                    this.a.aA.finishRefreshing();
                }
                this.a.bc = false;
            }

            public void a(int i) {
                if (this.a.bd == 2) {
                    this.a.aA.finishRefreshing();
                }
                this.a.bc = false;
            }
        });
        this.ay.a(new b.b(this) {
            int a = 0;
            final /* synthetic */ ProfileTestActivity b;

            {
                this.b = r2;
            }

            public void a() {
                this.a++;
                if (this.b.bd == 2) {
                    for (int i = 0; i < this.b.ay.getGroupCount(); i++) {
                        this.b.az.expandGroup(i);
                    }
                }
            }
        });
        this.av.c();
        this.aw.c();
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c4j:
                finish();
                return;
            case R.id.c4k:
                Intent intent = new Intent();
                intent.setClass(this, DJIEditProfileActivity.class);
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aih:
                if (f.getInstance().c()) {
                    String str;
                    this.aT.setClickable(false);
                    if (this.ba) {
                        str = "https://www.skypixel.com/api/users/" + this.T + a$i.Y;
                    } else {
                        str = "https://www.skypixel.com/api/users/" + this.T + a$i.S;
                    }
                    dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
                    if (f.getInstance().c()) {
                        bVar.a("token", f.getInstance().n());
                    } else {
                        bVar.a("token", "");
                    }
                    DJILogHelper.getInstance().LOGI("Lyric", "url: " + str);
                    com.dji.frame.c.c.b(this).c(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
                        final /* synthetic */ ProfileTestActivity a;

                        {
                            this.a = r1;
                        }

                        public void a(String str) {
                            FollowResultModel followResultModel = (FollowResultModel) h.b(str, FollowResultModel.class);
                            if (followResultModel != null && followResultModel.status == 0) {
                                this.a.a(!this.a.ba);
                            }
                            c.a().e(new FollowEvent(this.a.ba ? FollowEvent.b.FOLLOW : FollowEvent.b.UNFOLLOW, new dji.pilot2.nativeexplore.model.FollowEvent.a(f.getInstance().i(), f.getInstance().m(), a$e.a + f.getInstance().e(), f.getInstance().h().u), new dji.pilot2.nativeexplore.model.FollowEvent.a(this.a.T, this.a.U, this.a.V, this.a.W)));
                            this.a.aT.setOnClickListener(this.a);
                        }

                        public void a(Throwable th, int i, String str) {
                            DJILogHelper.getInstance().LOGI("Lyric", "failed: " + str);
                            this.a.aT.setOnClickListener(this.a);
                            new dji.pilot2.nativeexplore.c.d(this.a).show();
                        }

                        public void a(boolean z) {
                        }

                        public void a(long j, long j2) {
                        }
                    });
                    return;
                }
                q();
                return;
            case R.id.beb:
            case R.id.beh:
            case R.id.bek:
                Object obj;
                for (int i = 0; i < this.aU.size(); i++) {
                    if (this.aU.get(i) == view) {
                        this.bd = i;
                        ((ViewGroup) this.aU.get(i)).setSelected(true);
                        ((View) this.aV.get(i)).setSelected(true);
                        if (i == 2) {
                            dji.pilot.fpv.d.e.b(c$m.do_);
                        } else if (i == 1) {
                            dji.pilot.fpv.d.e.b(c$m.dr_);
                        }
                    } else {
                        ((ViewGroup) this.aU.get(i)).setSelected(false);
                        ((View) this.aV.get(i)).setSelected(false);
                    }
                }
                if (this.bd == 0) {
                    obj = this.au;
                    this.aQ.setVisibility(0);
                } else if (this.bd == 1) {
                    obj = this.ax;
                    this.aQ.setVisibility(8);
                } else {
                    obj = this.ay;
                    this.aQ.setVisibility(8);
                }
                this.az.setAdapter(obj);
                obj.notifyDataSetChanged();
                this.aA.finishRefreshing();
                if (this.bd == 0) {
                    this.at.a();
                    return;
                } else if (this.bd == 1) {
                    this.ax.getChildrenCount(0);
                    return;
                } else {
                    this.ay.getChildrenCount(0);
                    return;
                }
            default:
                return;
        }
    }

    private void q() {
        Intent intent = new Intent(this, DJIAccountSignActivity.class);
        intent.putExtra(DJIAccountSignActivity.a, 1005);
        startActivity(intent);
    }

    public void onEventMainThread(FollowEvent followEvent) {
        int i = 0;
        dji.pilot2.nativeexplore.model.FollowEvent.a aVar = followEvent.subject;
        dji.pilot2.nativeexplore.model.FollowEvent.a aVar2 = followEvent.object;
        DJILogHelper.getInstance().LOGE("Lyric", "pageUserId: " + this.T);
        DJILogHelper.getInstance().LOGE("Lyric", "subject Id: " + aVar.a);
        DJILogHelper.getInstance().LOGE("Lyric", "object Id: " + aVar2.a);
        DJILogHelper.getInstance().LOGE("Lyric", "Action: " + followEvent.action);
        int i2;
        AccountModel accountModel;
        if (followEvent.action == FollowEvent.b.UNFOLLOW) {
            for (i2 = 0; i2 < this.aw.a.size(); i2++) {
                accountModel = (AccountModel) this.aw.a.get(i2);
                if (accountModel.id.equals(aVar2.a)) {
                    accountModel.is_follow = false;
                }
            }
            for (i2 = 0; i2 < this.av.a.size(); i2++) {
                accountModel = (AccountModel) this.av.a.get(i2);
                if (accountModel.id.equals(aVar2.a)) {
                    accountModel.is_follow = false;
                }
            }
            if (this.T.equals(aVar2.a)) {
                i2 = 0;
                while (i2 < this.av.a.size()) {
                    int i3;
                    accountModel = (AccountModel) this.av.a.get(i2);
                    if (accountModel.id.equals(aVar.a)) {
                        this.av.a.remove(accountModel);
                        this.ax.a(accountModel);
                        this.aY--;
                        i3 = i2;
                    } else {
                        i3 = i2 + 1;
                    }
                    i2 = i3;
                }
            }
            if (this.T.equals(aVar.a)) {
                for (i2 = 0; i2 < this.aw.a.size(); i2++) {
                    accountModel = (AccountModel) this.aw.a.get(i2);
                    if (accountModel.id.equals(aVar2.a)) {
                        accountModel.is_follow = false;
                        this.aZ--;
                    }
                }
            }
            if (this.T.equals(aVar2.a)) {
                for (i2 = 0; i2 < this.at.h.size(); i2++) {
                    ((dji.pilot2.mine.e.a) this.at.h.get(i2)).p.is_follow = false;
                }
                a(false);
            }
        } else {
            if (this.T.equals(aVar.a)) {
                this.aZ++;
            }
            int i4 = 0;
            for (i2 = 0; i2 < this.aw.a.size(); i2++) {
                accountModel = (AccountModel) this.aw.a.get(i2);
                if (accountModel.id.equals(aVar2.a)) {
                    accountModel.is_follow = true;
                    i4 = true;
                }
            }
            int i5 = 0;
            for (i2 = 0; i2 < this.av.a.size(); i2++) {
                accountModel = (AccountModel) this.av.a.get(i2);
                if (accountModel.id.equals(aVar2.a)) {
                    accountModel.is_follow = true;
                    i5 = true;
                }
            }
            if (i4 == 0 && this.T.equals(aVar.a)) {
                accountModel = new AccountModel();
                accountModel.id = aVar2.a;
                accountModel.name = aVar2.b;
                accountModel.avatar = aVar2.c;
                accountModel.country = aVar2.d;
                accountModel.is_follow = true;
                this.aw.a(accountModel);
                this.aZ++;
            }
            if (i5 == 0 && this.T.equals(aVar2.a)) {
                accountModel = new AccountModel();
                accountModel.id = aVar.a;
                accountModel.name = aVar.b;
                accountModel.avatar = aVar.c;
                DJILogHelper.getInstance().LOGI("Lyric", "avatar: " + aVar.c);
                accountModel.country = aVar.d;
                accountModel.is_follow = false;
                this.av.a(accountModel);
                this.aY++;
            }
            if (this.T.equals(aVar2.a)) {
                while (i < this.at.h.size()) {
                    ((dji.pilot2.mine.e.a) this.at.h.get(i)).p.is_follow = true;
                    i++;
                }
                a(true);
            }
        }
        if (this.bd == 1) {
            this.ax.notifyDataSetChanged();
        } else if (this.bd == 2) {
            this.ay.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(DeleteArtworkEvent deleteArtworkEvent) {
        for (int i = 0; i < this.at.h.size(); i++) {
            dji.pilot2.mine.e.a aVar = (dji.pilot2.mine.e.a) this.at.h.get(i);
            if (aVar != null && aVar.p != null && aVar.p.id.equals(deleteArtworkEvent.id)) {
                this.at.h.remove(i);
                this.at.a(this.at.a() - 1);
                k();
                break;
            }
        }
        if (this.T.equals(f.getInstance().i())) {
            this.au.a();
            if (this.bd == 0) {
                this.au.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(ExploreEvent exploreEvent) {
        switch (exploreEvent) {
            case USER_LOGIN:
                this.at.c();
                this.av.a();
                this.aw.a();
                this.at.d(null);
                this.av.c();
                this.aw.c();
                l();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(LikeEvent likeEvent) {
        boolean z;
        int i = 0;
        String str = likeEvent.id;
        if (likeEvent.action == dji.pilot2.nativeexplore.model.LikeEvent.a.LIKE) {
            z = true;
        } else {
            z = false;
        }
        while (i < this.at.h.size()) {
            dji.pilot2.mine.e.a aVar = (dji.pilot2.mine.e.a) this.at.h.get(i);
            if (!(aVar == null || aVar.p == null || !aVar.p.id.equals(str) || aVar.p.is_liked == z)) {
                aVar.p.is_liked = z;
                aVar.p.likes_count = likeEvent.likeCount;
            }
            i++;
        }
        if (this.bd == 0) {
            this.au.notifyDataSetChanged();
        }
    }

    public boolean a() {
        return this.ba;
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b(this);
    }

    protected void onStop() {
        dji.pilot.fpv.d.e.c(this);
        super.onStop();
    }
}
