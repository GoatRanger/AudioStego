package dji.pilot2.main.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dji.frame.c.h;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.DJIFlyForbidController.GeoStatusEvent;
import dji.pilot.R;
import dji.pilot.fpv.d.c$c;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.c.e;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.coupon.couponmain.DJICouponMainActivity;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.explore.fragment.DJINotificationExploreFragment;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;
import dji.pilot2.favourite.activity.MyCollectionActivity;
import dji.pilot2.mine.activity.ContactDjiActivity;
import dji.pilot2.mine.activity.DraftActivity;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.mine.activity.RepairWebviewActivity;
import dji.pilot2.mine.activity.SettingsActivity;
import dji.pilot2.mine.activity.UnlockNFZActivity;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.mine.b.d;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.LevelInfo;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.MedalInfo;
import dji.pilot2.mine.view.MineButton;
import dji.pilot2.nativeexplore.b.f;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.model.DeleteArtworkEvent;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.LikeEvent;
import dji.pilot2.publics.b.a$h;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.utils.k;
import dji.pilot2.utils.l;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.f.b;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DJIMineFragment extends Fragment implements OnClickListener, e, c$m, a$h, a$i {
    public static final String G = "DJIMineFragment";
    public static final String H = "storenew";
    public static final String I = "couponnew";
    public static final int J = 1;
    public static final int K = 2;
    public static final int L = 3;
    public static final int M = 4;
    public static a P;
    private static boolean bA = false;
    private static boolean bB = false;
    private static boolean bC = false;
    private static boolean bD = false;
    private static boolean bE = false;
    private static boolean by = false;
    private static boolean bz = false;
    f N;
    f O;
    private View aA;
    private DJIStateImageView aB;
    private View aC;
    private MineButton aD;
    private MineButton aE;
    private MineButton aF;
    private MineButton aG;
    private MineButton aH;
    private MineButton aI;
    private MineButton aJ;
    private MineButton aK;
    private MineButton aL;
    private View aM;
    private MineButton aN;
    private MineButton aO;
    private MineButton aP;
    private MineButton aQ;
    private View aR;
    private View aS;
    private ImageView aT;
    private TextView aU;
    private List<ImageView> aV;
    private int[] aW;
    private TextView aX;
    private TextView aY;
    private TextView aZ;
    private View at;
    private View au;
    private View av;
    private View aw;
    private View ax;
    private View ay;
    private View az;
    private int[][] bF = new int[][]{d.a, d.b, d.c, d.d};
    private int[][] bG = new int[][]{d.e, d.f, d.g, d.h};
    private TextView ba;
    private TextView bb;
    private View bc;
    private View bd;
    private View be;
    private View bf;
    private View bg;
    private View bh;
    private DJIRelativeLayout bi;
    private LinearLayout bj;
    private int bk;
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private boolean bp = true;
    private boolean bq = true;
    private boolean br = true;
    private boolean bs = true;
    private boolean bt;
    private dji.pilot2.mine.b.a.a bu;
    private e$a bv;
    private l bw;
    private boolean bx = false;

    public static class a extends Handler {
        final WeakReference<DJIMineFragment> a;

        public a(DJIMineFragment dJIMineFragment) {
            this.a = new WeakReference(dJIMineFragment);
        }

        public void handleMessage(Message message) {
            DJIMineFragment dJIMineFragment = (DJIMineFragment) this.a.get();
            if (dJIMineFragment != null) {
                switch (message.what) {
                    case 1:
                        if (dji.pilot.usercenter.b.f.getInstance().c()) {
                            dJIMineFragment.bp = dJIMineFragment.bq = dJIMineFragment.br = dJIMineFragment.bs = true;
                            dJIMineFragment.a(true);
                            dJIMineFragment.c();
                            return;
                        }
                        dJIMineFragment.bp = dJIMineFragment.bq = dJIMineFragment.br = dJIMineFragment.bs = false;
                        dJIMineFragment.d();
                        dJIMineFragment.v();
                        dji.pilot2.mine.b.a.getInstance().c();
                        return;
                    case 2:
                        if (dJIMineFragment != null) {
                            dJIMineFragment.n();
                            return;
                        }
                        return;
                    case 3:
                        Bundle data = message.getData();
                        if (data.getBoolean("BBS", false)) {
                            DJIMineFragment.by = true;
                        }
                        if (data.getBoolean("Store", false)) {
                            DJIMineFragment.bz = true;
                        }
                        if (data.getBoolean("Exp", false)) {
                            DJIMineFragment.bA = true;
                        }
                        if (data.getBoolean("DDS", false)) {
                            DJIMineFragment.bC = true;
                        }
                        if (data.getBoolean("COUPON", false)) {
                            DJIMineFragment.bD = true;
                        }
                        if (data.getBoolean("MESSAGE", false)) {
                            DJIMineFragment.bE = true;
                        }
                        if ((!DJIMineFragment.bB && !DJIMineFragment.by && !DJIMineFragment.bz && !DJIMineFragment.bA && !DJIMineFragment.bC && !DJIMineFragment.bD && !DJIMineFragment.bE) || !dji.pilot.usercenter.b.f.getInstance().c()) {
                            return;
                        }
                        return;
                    case 4:
                        dJIMineFragment.a(false);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_main_mine_fragment, null);
        P = new a(this);
        o();
        a(inflate);
        c.a().a(this);
        dji.pilot2.mine.b.a.getInstance().a(this.bu);
        return inflate;
    }

    public void onDestroy() {
        c.a().d(this);
        dji.pilot2.mine.b.a.getInstance().b(this.bu);
        super.onDestroy();
    }

    public void onResume() {
        if (this.bt || this.bx != dji.pilot.usercenter.b.f.getInstance().c()) {
            this.bt = false;
            this.bx = dji.pilot.usercenter.b.f.getInstance().c();
            if (dji.pilot.usercenter.b.f.getInstance().c()) {
                c();
                a();
                a(false);
            } else {
                d();
            }
        }
        if (dji.pilot.usercenter.b.f.getInstance().c()) {
            b();
            n();
            l.getInstance().c(getActivity(), this.aN);
            l.getInstance().d(getActivity(), this.aF);
            if (this.br) {
                u();
            }
            if (this.bp) {
                q();
            }
            if (this.bq) {
                r();
                s();
            }
            if (this.bs) {
                t();
            }
        }
        dji.pilot.usercenter.b.f.getInstance().a(this.bv);
        if (g.b(getActivity(), DJIFlyForbidController.KEY_CUR_USE_GEO_SYSTEM, false)) {
            onEventMainThread(GeoStatusEvent.OPENED);
        } else {
            onEventMainThread(GeoStatusEvent.CLOSED);
        }
        super.onResume();
    }

    public void onPause() {
        dji.pilot.usercenter.b.f.getInstance().b(this.bv);
        super.onPause();
    }

    private void n() {
        int b = dji.pilot2.mine.b.c.getInstance().b();
        int l = dji.pilot2.mine.b.e.getInstance().l();
        if (b <= 0) {
            this.aD.setNotificationText("");
            this.aD.setRedPointVisiblity(false);
            bB = false;
        } else if (l > 0) {
            this.aD.setNotificationText("" + l);
            bB = true;
        } else {
            this.aD.setNotificationText("");
            bB = false;
        }
        P.removeMessages(3);
        P.sendEmptyMessage(3);
    }

    private void o() {
        this.bk = 0;
        this.bl = 0;
        this.bm = 0;
        this.bn = 0;
        this.bo = 0;
        this.bp = true;
        this.bq = true;
        this.br = true;
        this.bs = true;
        this.aV = new ArrayList();
        this.aW = new int[4];
        this.bw = l.getInstance();
        this.bw.b(getActivity());
        this.bu = new dji.pilot2.mine.b.a.a(this) {
            final /* synthetic */ DJIMineFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.bp = false;
                this.a.bk = dji.pilot2.mine.b.a.getInstance().a();
                this.a.aX.setText(Integer.toString(this.a.bk));
            }

            public void b() {
                DJILogHelper.getInstance().LOGI("Lyric", "MineFragment LoadArtwork failed");
            }
        };
        this.bv = new e$a(this) {
            final /* synthetic */ DJIMineFragment a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                switch (i) {
                    case dji.pilot.usercenter.protocol.d.o /*196656*/:
                        this.a.a(true);
                        return;
                    case dji.pilot.usercenter.protocol.d.q /*196688*/:
                        this.a.a(true);
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
    }

    private void a(View view) {
        this.at = view.findViewById(R.id.ct5);
        this.au = view.findViewById(R.id.ctg);
        this.bi = (DJIRelativeLayout) view.findViewById(R.id.hm);
        this.aT = (ImageView) view.findViewById(R.id.ct8);
        this.bj = (LinearLayout) view.findViewById(R.id.crs);
        if (!dji.pilot.usercenter.b.f.getInstance().c()) {
            this.bj.setVisibility(8);
        }
        this.ay = view.findViewById(R.id.cte);
        if (this.ay != null) {
            this.ay.setVisibility(8);
        }
        this.bg = view.findViewById(R.id.crr);
        this.bh = view.findViewById(R.id.crz);
        this.bh.setVisibility(8);
        this.aA = view.findViewById(R.id.crq);
        this.az = view.findViewById(R.id.cth);
        this.aw = view.findViewById(R.id.ctj);
        this.ax = view.findViewById(R.id.cti);
        this.aC = view.findViewById(R.id.ct6);
        this.aD = (MineButton) view.findViewById(R.id.cru);
        this.aF = (MineButton) view.findViewById(R.id.bor);
        this.aN = (MineButton) view.findViewById(R.id.crt);
        this.aR = view.findViewById(R.id.csw);
        this.aG = (MineButton) view.findViewById(R.id.ho);
        this.aS = view.findViewById(R.id.ct0);
        this.aI = (MineButton) view.findViewById(R.id.hp);
        this.aJ = (MineButton) view.findViewById(R.id.hr);
        this.aK = (MineButton) view.findViewById(R.id.hs);
        this.aL = (MineButton) view.findViewById(R.id.crw);
        this.aM = view.findViewById(R.id.crx);
        this.aL.setVisibility(8);
        this.aM.setVisibility(8);
        this.av = view.findViewById(R.id.csx);
        if (g.b(getActivity(), H, 0) != 0) {
            this.av.setVisibility(8);
        }
        this.aO = (MineButton) view.findViewById(R.id.bos);
        this.aP = (MineButton) view.findViewById(R.id.bot);
        this.aQ = (MineButton) view.findViewById(R.id.cry);
        this.aV.add((ImageView) view.findViewById(R.id.ct_));
        this.aV.add((ImageView) view.findViewById(R.id.cta));
        this.aV.add((ImageView) view.findViewById(R.id.ctb));
        this.aV.add((ImageView) view.findViewById(R.id.ctc));
        this.aX = (TextView) view.findViewById(R.id.bec);
        this.aY = (TextView) view.findViewById(R.id.bef);
        this.aZ = (TextView) view.findViewById(R.id.bei);
        this.ba = (TextView) view.findViewById(R.id.bel);
        this.bb = (TextView) view.findViewById(R.id.bea);
        this.bc = view.findViewById(R.id.beb);
        this.bd = view.findViewById(R.id.bee);
        this.be = view.findViewById(R.id.beh);
        this.bf = view.findViewById(R.id.bek);
        this.aB = (DJIStateImageView) view.findViewById(R.id.ct9);
        this.bc.setOnClickListener(this);
        this.be.setOnClickListener(this);
        this.bf.setOnClickListener(this);
        this.bd.setOnClickListener(this);
        this.aD.setButtonIcon(getResources().getDrawable(R.drawable.v2_mine_draft_icon));
        this.aF.setButtonIcon(getResources().getDrawable(R.drawable.v2_mine_message_icon));
        this.aN.setButtonIcon(getResources().getDrawable(R.drawable.v2_mine_gift_icon));
        this.aG.setButtonIcon(getResources().getDrawable(R.drawable.v2_icon_my_earning));
        this.aJ.setButtonIcon(getResources().getDrawable(R.drawable.v2_icon_contact_dji));
        this.aK.setButtonIcon(getResources().getDrawable(R.drawable.v2_icon_feedback));
        this.aI.setButtonIcon(getResources().getDrawable(R.drawable.v2_icon_repair));
        this.aL.setButtonIcon(getResources().getDrawable(R.drawable.v2_icon_nfz_list));
        this.aD.setButtonText(getResources().getString(R.string.mine_upload_mission));
        this.aF.setButtonText(getResources().getString(R.string.v2_mine_message));
        this.aN.setButtonText(getResources().getString(R.string.v2_mine_coupon));
        this.aG.setButtonText(getResources().getString(R.string.mine_dji_dds));
        this.aJ.setButtonText(getResources().getString(R.string.mine_contact_dji));
        this.aK.setButtonText(getResources().getString(R.string.mine_feedback));
        this.aL.setButtonText(getResources().getString(R.string.mine_unlock_list));
        this.aI.setButtonText(getResources().getString(R.string.v2_6_mine_repair));
        this.aO.setButtonIcon(getResources().getDrawable(R.drawable.v2_mine_collect_icon));
        this.aP.setButtonIcon(getResources().getDrawable(R.drawable.v2_mine_more_icon));
        this.aQ.setButtonIcon(getResources().getDrawable(R.drawable.v2_mine_setting_icon));
        this.aO.setButtonText(getResources().getString(R.string.v2_mine_collection));
        this.aP.setButtonText(getResources().getString(R.string.v2_mine_More));
        this.aQ.setButtonText(getResources().getString(R.string.v2_mine_Setting));
        this.aU = (TextView) view.findViewById(R.id.c9t);
        this.aA.setOnClickListener(this);
        this.az.setOnClickListener(this);
        this.aC.setOnClickListener(this);
        this.aw.setOnClickListener(this);
        this.ax.setOnClickListener(this);
        this.aD.setOnClickListener(this);
        this.aF.setOnClickListener(this);
        this.aN.setOnClickListener(this);
        this.aR.setOnClickListener(this);
        this.aG.setOnClickListener(this);
        this.aS.setOnClickListener(this);
        this.aI.setOnClickListener(this);
        this.aJ.setOnClickListener(this);
        this.aK.setOnClickListener(this);
        this.aL.setOnClickListener(this);
        this.aP.setOnClickListener(this);
        this.aO.setOnClickListener(this);
        this.aQ.setOnClickListener(this);
        p();
        if (!"CN".equals(getResources().getConfiguration().locale.getCountry())) {
            this.aI.setVisibility(8);
            view.findViewById(R.id.hq).setVisibility(8);
        }
        this.bb.setText(getResources().getString(R.string.v2_mine_flight_level, new Object[]{"0"}));
        this.aw.measure(-2, -2);
        int measuredWidth = this.aw.getMeasuredWidth();
        if (measuredWidth != 0 && getResources().getDisplayMetrics().widthPixels > measuredWidth * 2) {
            LayoutParams layoutParams = this.ax.getLayoutParams();
            layoutParams.width = measuredWidth;
            this.ax.setLayoutParams(layoutParams);
        }
    }

    private void p() {
        for (int i = 0; i < 4; i++) {
            this.aW[i] = 0;
            ((ImageView) this.aV.get(i)).setImageResource(this.bG[i][0]);
        }
    }

    public void a() {
    }

    public void b() {
        CharSequence m = dji.pilot.usercenter.b.f.getInstance().m();
        if (m == null) {
            this.aU.setText("");
        } else if (m.length() > 15) {
            this.aU.setText(m.substring(0, 15) + "...");
        } else {
            this.aU.setText(m);
        }
    }

    public void a(boolean z) {
        b();
        String e = dji.pilot.usercenter.b.f.getInstance().e();
        String str = dji.pilot.usercenter.b.f.getInstance().h().l;
        String str2 = dji.pilot.usercenter.b.f.getInstance().f().u;
        Log.i("zhangchen", "userContryCode:" + str2);
        if (str2 != null) {
            int a = dji.pilot2.utils.c.a(getActivity(), str2);
            if (a == 0) {
                this.aB.setVisibility(4);
            } else {
                this.aB.setVisibility(0);
                this.aB.setImageResource(a);
            }
        }
        if (e != null && !e.equals("")) {
            File file = new File(e);
            if (file.exists() && file.isFile()) {
                this.aT.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            } else if (str.equals("")) {
                this.aT.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
            } else {
                ImageLoader.getInstance().displayImage(str, this.aT);
            }
        } else if (str.equals("")) {
            this.aT.setImageDrawable(getResources().getDrawable(R.drawable.v2_avatar_default));
        } else {
            ImageLoader.getInstance().displayImage(str, this.aT);
        }
        if (z) {
            q();
            u();
            r();
            s();
            t();
        }
    }

    private void q() {
        dji.pilot2.mine.b.a.getInstance().a(dji.pilot.usercenter.b.f.getInstance().i(), this.bu);
    }

    public void c() {
        this.au.setVisibility(8);
        this.at.setVisibility(0);
        this.bj.setVisibility(0);
        this.at.requestLayout();
    }

    public void d() {
        this.at.setVisibility(8);
        this.au.setVisibility(0);
        this.bj.setVisibility(8);
        this.au.requestLayout();
    }

    private void r() {
        String[] strArr = new String[]{"https://www.skypixel.com/api/users/" + dji.pilot.usercenter.b.f.getInstance().i() + a$i.ac};
        Map hashMap = new HashMap();
        hashMap.put("token", dji.pilot.usercenter.b.f.getInstance().n());
        this.N = new f(getActivity(), strArr, hashMap, ParamKey.PAGE, n.am);
        this.N.b(1);
        this.N.a(new i(this) {
            final /* synthetic */ DJIMineFragment a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.bl = this.a.N.e();
                this.a.aZ.setText(Integer.toString(this.a.bl));
            }

            public void a(int i) {
            }
        });
        this.N.c();
    }

    private void s() {
        String[] strArr = new String[]{"https://www.skypixel.com/api/users/" + dji.pilot.usercenter.b.f.getInstance().i() + a$i.ad};
        Map hashMap = new HashMap();
        hashMap.put("token", dji.pilot.usercenter.b.f.getInstance().n());
        this.O = new f(getActivity(), strArr, hashMap, ParamKey.PAGE, n.am);
        this.O.b(1);
        this.O.a(new i(this) {
            final /* synthetic */ DJIMineFragment a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z, boolean z2) {
                this.a.bq = false;
                this.a.bm = this.a.O.e();
                this.a.ba.setText(Integer.toString(this.a.bm));
            }

            public void a(int i) {
            }
        });
        this.O.c();
    }

    private void t() {
        String str = "https://www.skypixel.com/api/users/" + dji.pilot.usercenter.b.f.getInstance().i() + a$i.al;
        Map hashMap = new HashMap();
        hashMap.put("token", dji.pilot.usercenter.b.f.getInstance().n());
        hashMap.put(n.am, "1");
        com.dji.frame.c.c.b(getActivity()).a(str, new b(hashMap), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIMineFragment a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt("photo_count");
                    int i2 = jSONObject.getInt("video_count");
                    this.a.bn = jSONObject.getInt("story_count") + (i + i2);
                    this.a.aY.setText("" + this.a.bn);
                    this.a.bs = false;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }

    private void u() {
        final String i = dji.pilot.usercenter.b.f.getInstance().i();
        String o = dji.pilot.usercenter.b.f.getInstance().o();
        final String j = dji.pilot.usercenter.b.f.getInstance().j();
        dji.pilot.usercenter.b.f.getInstance().n();
        com.dji.frame.c.c.b(getActivity()).a(k.t(o), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIMineFragment c;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                this.c.br = false;
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

    private void a(String str, boolean z, String str2) {
        UserLevelJsonBean userLevelJsonBean = null;
        if (!(str == null || str.equals(""))) {
            userLevelJsonBean = (UserLevelJsonBean) h.b(str, UserLevelJsonBean.class);
        }
        if (userLevelJsonBean != null && userLevelJsonBean.level != null) {
            LevelInfo levelInfo = userLevelJsonBean.level;
            MedalInfo medalInfo = userLevelJsonBean.medal;
            if (levelInfo != null) {
                this.bo = levelInfo.level;
                this.bb.setText(getString(R.string.v2_mine_flight_level, new Object[]{Integer.valueOf(this.bo)}));
                if (z) {
                    dji.pilot2.mine.b.e.getInstance().a(str2, str);
                }
            }
            if (medalInfo != null && medalInfo.director != null && medalInfo.photographer != null && medalInfo.level != null && medalInfo.share != null) {
                int[] iArr = new int[]{medalInfo.level.level, medalInfo.director.level, medalInfo.photographer.level, medalInfo.share.level};
                for (int i = 0; i < 4; i++) {
                    this.aW[i] = iArr[i];
                    int[] iArr2 = this.bF[i];
                    int[] iArr3 = this.bG[i];
                    if (this.aW[i] <= 0) {
                        ((ImageView) this.aV.get(i)).setImageResource(iArr3[0]);
                    } else {
                        int length;
                        int i2 = this.aW[i] - 1;
                        if (i2 >= iArr2.length) {
                            length = iArr2.length - 1;
                        } else {
                            length = i2;
                        }
                        ((ImageView) this.aV.get(i)).setImageResource(iArr2[length]);
                    }
                }
            }
        }
    }

    private void v() {
        this.bk = 0;
        this.bl = 0;
        this.bm = 0;
        this.bn = 0;
        this.bo = 0;
        this.aX.setText("0");
        this.aZ.setText("0");
        this.ba.setText("0");
        this.aY.setText("0");
        this.bb.setText(getString(R.string.v2_mine_flight_level, new Object[]{"0"}));
        p();
    }

    private void w() {
        startActivity(new Intent(getActivity(), DJIAccountSignActivity.class));
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ho:
                if (dji.pilot.usercenter.b.f.getInstance().c()) {
                    dji.pilot.fpv.d.e.b(c$m.dA_);
                    intent = new Intent(getActivity(), DJISupportShareWebviewActivity.class);
                    this.aG.setRedPointVisiblity(false);
                    P.sendEmptyMessage(3);
                    intent.putExtra(DJIWebviewFragment.o, k.o());
                    intent.putExtra(DJIWebviewFragment.s, true);
                    startActivity(intent);
                    return;
                }
                w();
                return;
            case R.id.hp:
                if (!dji.pilot.usercenter.b.f.getInstance().c() || dji.pilot.usercenter.b.f.getInstance().o() == null) {
                    w();
                    return;
                }
                String u = k.u(dji.pilot.usercenter.b.f.getInstance().o());
                Intent intent2 = new Intent(getActivity(), RepairWebviewActivity.class);
                intent2.putExtra(DJISupportShareWebviewFragment.o, u);
                startActivity(intent2);
                dji.pilot.fpv.d.e.b(c$m.eF_);
                return;
            case R.id.hr:
                dji.pilot.fpv.d.e.b(c$m.dF_);
                startActivity(new Intent(getActivity(), ContactDjiActivity.class));
                return;
            case R.id.hs:
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra(DJIWebviewFragment.o, k.q());
                intent.putExtra(DJIWebviewFragment.s, false);
                intent.putExtra("title_text", getActivity().getResources().getString(R.string.mine_feedback));
                startActivity(intent);
                return;
            case R.id.beb:
                dji.pilot.fpv.d.e.b(c$m.du_);
                bA = false;
                this.ay.setVisibility(8);
                P.sendEmptyMessage(3);
                intent = new Intent(getActivity(), ProfileTestActivity.class);
                intent.putExtra("user_id", dji.pilot.usercenter.b.f.getInstance().i());
                intent.putExtra("country", dji.pilot.usercenter.b.f.getInstance().h().u);
                intent.putExtra(ProfileTestActivity.K, 0);
                startActivity(intent);
                return;
            case R.id.bee:
                startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                return;
            case R.id.beh:
                dji.pilot.fpv.d.e.b(c$m.du_);
                bA = false;
                this.ay.setVisibility(8);
                P.sendEmptyMessage(3);
                intent = new Intent(getActivity(), ProfileTestActivity.class);
                intent.putExtra("user_id", dji.pilot.usercenter.b.f.getInstance().i());
                intent.putExtra("country", dji.pilot.usercenter.b.f.getInstance().h().u);
                intent.putExtra(ProfileTestActivity.K, 1);
                startActivity(intent);
                return;
            case R.id.bek:
                dji.pilot.fpv.d.e.b(c$m.du_);
                bA = false;
                this.ay.setVisibility(8);
                P.sendEmptyMessage(3);
                intent = new Intent(getActivity(), ProfileTestActivity.class);
                intent.putExtra("user_id", dji.pilot.usercenter.b.f.getInstance().i());
                intent.putExtra("country", dji.pilot.usercenter.b.f.getInstance().h().u);
                intent.putExtra(ProfileTestActivity.K, 2);
                startActivity(intent);
                return;
            case R.id.bor:
                bE = false;
                P.sendEmptyMessage(3);
                startActivity(new Intent(getActivity(), DJINotificationExploreFragment.class));
                return;
            case R.id.bos:
                startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                return;
            case R.id.bot:
                this.bi.show();
                this.bg.setVisibility(8);
                this.bh.setVisibility(0);
                return;
            case R.id.crq:
                this.bi.go();
                this.bg.setVisibility(0);
                this.bh.setVisibility(8);
                return;
            case R.id.crt:
                bD = false;
                g.a(getActivity(), I, 1);
                P.sendEmptyMessage(3);
                startActivity(new Intent(getActivity(), DJICouponMainActivity.class));
                dji.pilot.fpv.d.e.b(c$c.a);
                return;
            case R.id.cru:
                if (dji.pilot.usercenter.b.f.getInstance().c()) {
                    startActivity(new Intent(getActivity(), DraftActivity.class));
                    return;
                } else {
                    w();
                    return;
                }
            case R.id.crv:
                if (dji.pilot.usercenter.b.f.getInstance().c()) {
                    dji.pilot.fpv.d.e.b(c$m.eE_);
                    by = false;
                    P.sendEmptyMessage(3);
                    intent = new Intent(getActivity(), DJISupportShareWebviewActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, k.p(dji.pilot.usercenter.b.f.getInstance().n()));
                    intent.putExtra(DJIWebviewFragment.r, true);
                    startActivity(intent);
                    return;
                }
                w();
                return;
            case R.id.crw:
                if (dji.pilot.usercenter.b.f.getInstance().c()) {
                    startActivity(new Intent(getActivity(), UnlockNFZActivity.class));
                    return;
                } else {
                    w();
                    return;
                }
            case R.id.cry:
                dji.pilot.fpv.d.e.b(c$m.dB_);
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return;
            case R.id.csw:
                if (dji.pilot.usercenter.b.f.getInstance().c()) {
                    g.a(getActivity(), H, 1);
                    this.av.setVisibility(8);
                    dji.pilot.fpv.d.e.b(c$m.dz_);
                    bz = false;
                    P.sendEmptyMessage(3);
                    intent = new Intent(getActivity(), DJISupportShareWebviewActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, k.n());
                    intent.putExtra(DJIWebviewFragment.r, true);
                    startActivity(intent);
                    return;
                }
                w();
                return;
            case R.id.ct0:
                if (dji.pilot.usercenter.b.f.getInstance().c()) {
                    dji.pilot.fpv.d.e.b(c$m.eE_);
                    by = false;
                    P.sendEmptyMessage(3);
                    intent = new Intent(getActivity(), DJISupportShareWebviewActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, k.p(dji.pilot.usercenter.b.f.getInstance().n()));
                    intent.putExtra(DJIWebviewFragment.r, true);
                    startActivity(intent);
                    return;
                }
                w();
                return;
            case R.id.ct6:
                dji.pilot.fpv.d.e.b(c$m.du_);
                bA = false;
                this.ay.setVisibility(8);
                P.sendEmptyMessage(3);
                intent = new Intent(getActivity(), ProfileTestActivity.class);
                intent.putExtra("user_id", dji.pilot.usercenter.b.f.getInstance().i());
                intent.putExtra("country", dji.pilot.usercenter.b.f.getInstance().h().u);
                startActivity(intent);
                return;
            case R.id.cth:
            case R.id.cti:
                w();
                return;
            case R.id.ctj:
                intent = new Intent(getActivity(), DJIAccountSignActivity.class);
                intent.putExtra(DJIAccountSignActivity.b, false);
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    public boolean e() {
        return this.bh.getVisibility() == 0;
    }

    public void f() {
        this.bg.setVisibility(0);
        this.bh.setVisibility(8);
        this.bi.go();
    }

    public void onEventMainThread(dji.pilot2.account.profile.DJIEditProfileFragment.a aVar) {
        DJILogHelper.getInstance().LOGI("bob", "DJIMineFragment onEventMainThread AVATAR_CHANGED");
        a(false);
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
        while (i < dji.pilot2.mine.b.a.getInstance().h.size()) {
            dji.pilot2.mine.e.a aVar = (dji.pilot2.mine.e.a) dji.pilot2.mine.b.a.getInstance().h.get(i);
            if (!(aVar.p == null || !str.equals(aVar.p.id) || aVar.p.is_liked == z)) {
                aVar.p.is_liked = z;
                aVar.p.likes_count = likeEvent.likeCount;
            }
            i++;
        }
    }

    public void onStart() {
        super.onStart();
        dji.pilot.usercenter.b.f.getInstance().b(getActivity());
    }

    public void onEventMainThread(DeleteArtworkEvent deleteArtworkEvent) {
        this.bp = true;
    }

    public void onEventMainThread(FollowEvent followEvent) {
        this.bq = true;
    }

    public void onEventMainThread(dji.pilot2.nativeexplore.model.a aVar) {
        this.bs = true;
        if (dji.pilot2.nativeexplore.model.a.a.favorite.equals(aVar.a)) {
            this.bn++;
        } else {
            this.bn--;
        }
        this.aY.setText("" + this.bn);
    }

    public void onEventMainThread(GeoStatusEvent geoStatusEvent) {
        if (!dji.pilot.c.a.E) {
            return;
        }
        if (geoStatusEvent == GeoStatusEvent.OPENED) {
            this.aL.setVisibility(0);
            this.aM.setVisibility(0);
            return;
        }
        this.aL.setVisibility(8);
        this.aM.setVisibility(8);
    }
}
