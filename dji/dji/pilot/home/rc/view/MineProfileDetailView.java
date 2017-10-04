package dji.pilot.home.rc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.mine.b.a.a;
import dji.pilot2.mine.b.e;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.LevelInfo;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.MedalInfo;
import dji.pilot2.nativeexplore.b.f;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.publics.b.a$h;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.HashMap;
import java.util.Map;

public class MineProfileDetailView extends DJIRelativeLayout implements a$h, a$i {
    private Context G;
    private DJIRelativeLayout H;
    private DJIStateTextView I;
    private DJIRelativeLayout J;
    private DJIStateTextView K;
    private DJIStateTextView L;
    private DJIRelativeLayout M;
    private DJIStateTextView N;
    private DJIStateTextView O;
    private DJIRelativeLayout P;
    private f aA;
    private int aB;
    private int aC;
    private int aD;
    private int aE;
    private int aF;
    private boolean aG;
    private boolean aH;
    private boolean aI;
    private boolean aJ;
    private boolean aK;
    private DJIStateTextView at;
    private DJIStateTextView au;
    private DJIRelativeLayout av;
    private DJIStateTextView aw;
    private DJIStateTextView ax;
    private a ay;
    private f az;

    public MineProfileDetailView(Context context) {
        this(context, null);
    }

    public MineProfileDetailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MineProfileDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.aG = true;
        this.aH = true;
        this.aI = true;
        this.aJ = true;
        a();
        this.G = context;
    }

    private void a() {
        dji.setting.a.a.a((View) this, (int) R.layout.mine_num_datail_layout);
        if (!isInEditMode()) {
            c();
            b();
        }
    }

    private void b() {
        this.aB = 0;
        this.aC = 0;
        this.aD = 0;
        this.aE = 0;
        this.aF = 0;
        this.aG = true;
        this.aH = true;
        this.aI = true;
        this.aJ = true;
        this.ay = new a(this) {
            final /* synthetic */ MineProfileDetailView a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.aG = false;
                this.a.aB = dji.pilot2.mine.b.a.getInstance().a();
                this.a.K.setText(Integer.toString(this.a.aB));
            }

            public void b() {
                DJILogHelper.getInstance().LOGI("Lyric", "MineFragment LoadArtwork failed");
            }
        };
        dji.pilot2.mine.b.a.getInstance().a(this.ay);
        d();
        e();
        f();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.pilot2.mine.b.a.getInstance().b(this.ay);
    }

    private void c() {
        this.H = (DJIRelativeLayout) findViewById(R.id.be_);
        this.I = (DJIStateTextView) findViewById(R.id.bea);
        this.J = (DJIRelativeLayout) findViewById(R.id.beb);
        this.K = (DJIStateTextView) findViewById(R.id.bec);
        this.L = (DJIStateTextView) findViewById(R.id.bed);
        this.M = (DJIRelativeLayout) findViewById(R.id.bee);
        this.N = (DJIStateTextView) findViewById(R.id.bef);
        this.O = (DJIStateTextView) findViewById(R.id.beg);
        this.P = (DJIRelativeLayout) findViewById(R.id.beh);
        this.at = (DJIStateTextView) findViewById(R.id.bei);
        this.au = (DJIStateTextView) findViewById(R.id.bej);
        this.av = (DJIRelativeLayout) findViewById(R.id.bek);
        this.aw = (DJIStateTextView) findViewById(R.id.bel);
        this.ax = (DJIStateTextView) findViewById(R.id.bem);
    }

    private void d() {
        final String i = dji.pilot.usercenter.b.f.getInstance().i();
        String o = dji.pilot.usercenter.b.f.getInstance().o();
        final String j = dji.pilot.usercenter.b.f.getInstance().j();
        c.b(this.G).a(k.t(o), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ MineProfileDetailView c;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                this.c.aI = false;
                if (!(i == null || i.equals(""))) {
                    this.c.a(str, true, j);
                }
                DJILogHelper.getInstance().LOGI("bob", "updateUserLevel suc " + str);
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGI("bob", "updateUserLevel fail errorNo=" + i + " strMsg=" + str);
            }
        });
    }

    private void e() {
        String[] strArr = new String[]{"https://www.skypixel.com/api/users/" + dji.pilot.usercenter.b.f.getInstance().i() + a$i.ac};
        Map hashMap = new HashMap();
        hashMap.put("token", dji.pilot.usercenter.b.f.getInstance().n());
        this.az = new f(this.G, strArr, hashMap, ParamKey.PAGE, n.am);
        this.az.b(1);
        this.az.a(new i(this) {
            final /* synthetic */ MineProfileDetailView a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.aC = this.a.az.e();
                this.a.at.setText(Integer.toString(this.a.aC));
            }

            public void a(int i) {
            }
        });
        this.az.c();
    }

    private void f() {
        String[] strArr = new String[]{"https://www.skypixel.com/api/users/" + dji.pilot.usercenter.b.f.getInstance().i() + a$i.ad};
        Map hashMap = new HashMap();
        hashMap.put("token", dji.pilot.usercenter.b.f.getInstance().n());
        this.aA = new f(this.G, strArr, hashMap, ParamKey.PAGE, n.am);
        this.aA.b(1);
        this.aA.a(new i(this) {
            final /* synthetic */ MineProfileDetailView a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.aH = false;
                this.a.aD = this.a.aA.e();
                this.a.aw.setText(Integer.toString(this.a.aD));
            }

            public void a(int i) {
            }
        });
        this.aA.c();
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
                this.aF = levelInfo.level;
                this.I.setText(this.G.getString(R.string.v2_mine_flight_level, new Object[]{Integer.valueOf(this.aF)}));
                if (z) {
                    e.getInstance().a(str2, str);
                }
            }
        }
    }
}
