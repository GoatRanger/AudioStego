package dji.pilot2.share.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import com.facebook.share.model.ShareLinkContent;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.nativeexplore.b.d;
import dji.pilot2.nativeexplore.view.IndicatorBar;
import dji.pilot2.publics.b.a$l;
import dji.pilot2.share.a.a;
import dji.pilot2.share.mode.ContestsModel;
import dji.pilot2.share.mode.ContestsModel.Event;
import dji.pilot2.share.mode.b;
import dji.pilot2.utils.k;
import dji.pilot2.utils.p;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DJIShareLaterActivity extends DJIActivityNoFullScreen implements OnClickListener, o {
    public static final int T = 16;
    public static final int U = 32;
    public static final String V = "intent_share_type";
    public static final String W = "intent_share_work_id";
    public static final String X = "intent_share_filepath";
    public static final String Y = "intent_share_title";
    public static final String Z = "intent_share_desc";
    public static final String aa = "intent_share_thumbnailpath";
    public static final String ab = "intent_share_thumbnailurlpath";
    private static final int ac = 1;
    private String ad;
    private String ae;
    private String af;
    private String ag;
    private String ah;
    private String ai;
    private String aj;
    private View ak;
    private IndicatorBar al;
    private ViewPager am;
    private a an;
    private Handler ao;
    private LinearLayout ap;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_share_later);
        Intent intent = getIntent();
        this.ae = intent.getStringExtra(W);
        this.af = intent.getStringExtra(X);
        this.ag = intent.getStringExtra(Y);
        this.ah = intent.getStringExtra(Z);
        this.ai = intent.getStringExtra(aa);
        this.aj = intent.getStringExtra(ab);
        if (intent.getIntExtra(V, 0) == 1) {
            this.ad = "photo";
        } else {
            this.ad = "video";
        }
        this.ao = new Handler(this, getMainLooper()) {
            final /* synthetic */ DJIShareLaterActivity a;

            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        if (this.a.an.a() > 1 && this.a.an.getCount() != 0) {
                            try {
                                Method declaredMethod = this.a.am.getClass().getDeclaredMethod("setCurrentItemInternal", new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Integer.TYPE});
                                declaredMethod.setAccessible(true);
                                declaredMethod.invoke(this.a.am, new Object[]{Integer.valueOf((this.a.am.getCurrentItem() + 1) % this.a.an.getCount()), Boolean.valueOf(true), Boolean.valueOf(false), Integer.valueOf(1)});
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.a.am.setCurrentItem((this.a.am.getCurrentItem() + 1) % this.a.an.getCount(), true);
                            }
                        }
                        sendEmptyMessageDelayed(1, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                        return;
                    default:
                        return;
                }
            }
        };
        this.ak = LayoutInflater.from(this).inflate(R.layout.v2_share_contest_banner_view, null, false);
        this.al = (IndicatorBar) this.ak.findViewById(R.id.ckm);
        this.am = (ViewPager) this.ak.findViewById(R.id.ckl);
        ((ViewGroup) findViewById(R.id.cgf)).addView(this.ak);
        this.am.addOnPageChangeListener(new OnPageChangeListener(this) {
            int a = 0;
            final /* synthetic */ DJIShareLaterActivity b;

            {
                this.b = r2;
            }

            public void onPageSelected(int i) {
                this.b.al.setSelectedIndex(i % this.b.an.a());
                Log.i("rxq", "pos:" + i + " count:" + this.b.an.a());
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
                if (this.a != i) {
                    this.a = i;
                    if (i == 0) {
                        if (this.b.ao.hasMessages(1)) {
                            this.b.ao.removeMessages(1);
                        }
                        this.b.ao.sendEmptyMessageDelayed(1, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                    } else if (i == 1 && this.b.ao.hasMessages(1)) {
                        this.b.ao.removeMessages(1);
                    }
                }
            }
        });
        findViewById(R.id.cg_).setOnClickListener(this);
        this.ap = (LinearLayout) findViewById(R.id.cge);
        a();
        b();
    }

    private void a() {
        b[] bVarArr;
        if (getResources().getConfiguration().locale.getCountry().equalsIgnoreCase("CN")) {
            bVarArr = b.g;
        } else {
            bVarArr = b.h;
        }
        this.ap.setWeightSum(12.0f);
        int i = 0;
        View view = null;
        while (i < bVarArr.length) {
            LayoutInflater from = LayoutInflater.from(this);
            if (i % 3 == 0) {
                view = new LinearLayout(this);
                view.setOrientation(0);
                view.setLayoutParams(new LayoutParams(-1, -2, 3.0f));
                view.setWeightSum((float) 3);
                this.ap.addView(view);
            }
            View view2 = view;
            View inflate = from.inflate(R.layout.v2_sharelater_sharebutton, null);
            ((ImageView) inflate.findViewById(R.id.cjd)).setImageResource(bVarArr[i].b);
            inflate.setTag(bVarArr[i]);
            inflate.setOnClickListener(this);
            inflate.setLayoutParams(new LayoutParams(0, -2, 1.0f));
            view2.addView(inflate);
            i++;
            view = view2;
        }
    }

    private void a(dji.pilot2.share.e.a.b bVar) {
        String str = this.ad;
        String str2 = this.ai;
        if (str2 == null && this.ad == "photo") {
            str2 = this.af;
        }
        String str3 = "";
        str = k.b(str, this.ae, null);
        dji.pilot2.mine.e.a.a aVar = new dji.pilot2.mine.e.a.a();
        aVar.c = this.ag;
        aVar.d = this.ah;
        aVar.f = str2;
        if (f.getInstance().c()) {
            aVar.b = str + "?account_id=" + f.getInstance().i();
        } else {
            aVar.b = str;
        }
        DJILogHelper.getInstance().LOGI("bob", "shareUrl = " + aVar.b + " mTitle=" + this.ag + " mDescription=" + aVar.d + " shareInfo.mLocalThumbnailPath= " + aVar.f);
        a(aVar, bVar);
    }

    private void a(dji.pilot2.mine.e.a.a aVar, dji.pilot2.share.e.a.b bVar) {
        dji.pilot2.share.d.b bVar2 = new dji.pilot2.share.d.b(this);
        bVar2.a(aVar);
        bVar2.a(aVar.b());
        if (bVar == dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM) {
            if (p.b(this, "com.instagram.android")) {
                dji.pilot2.share.e.a.a aVar2 = dji.pilot2.share.e.a.a.CONTENT_IMG;
                if (this.ad == "photo") {
                    aVar2 = dji.pilot2.share.e.a.a.CONTENT_IMG;
                } else {
                    aVar2 = dji.pilot2.share.e.a.a.CONTENT_VIDEO;
                }
                dji.pilot2.share.f.b.a((Context) this, this.af, aVar2, dji.pilot2.share.b.b.a.EDIT_UPLOAD);
                return;
            }
            Toast.makeText(this, R.string.v2_share_instagram_tip, 1).show();
        } else if (bVar == dji.pilot2.share.e.a.b.PLATFORM_TYPE_FACKBOOK) {
            a(aVar);
        } else if (this.ad == "photo") {
            bVar2.b(bVar);
        } else {
            bVar2.a(bVar);
        }
    }

    private void a(dji.pilot2.mine.e.a.a aVar) {
        if (com.facebook.share.widget.f.a(ShareLinkContent.class)) {
            new com.facebook.share.widget.f(this).b(((ShareLinkContent.a) new ShareLinkContent.a().b(aVar.c).a(aVar.d + aVar.b()).a(Uri.parse(aVar.b()))).b());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cg_:
                setResult(32);
                e.b(o.cG_);
                finish();
                return;
            default:
                b bVar = (b) view.getTag();
                if (bVar.d != dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM || p.b(this, "com.instagram.android")) {
                    DJILogHelper.getInstance().LOGI("bob", "share info mTitle=" + this.ag + " mShareDesc=" + this.ah);
                    a(bVar.d);
                    return;
                }
                Toast.makeText(this, R.string.v2_share_instagram_tip, 1).show();
                return;
        }
    }

    private void b() {
        String str = a$l.g + this.ad + com.alipay.sdk.h.a.b;
        Log.i("rxq", "get contests url: " + str);
        c.b(this).a(str, new dji.thirdparty.afinal.f.b(new HashMap()), new d.a(this) {
            final /* synthetic */ DJIShareLaterActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                Log.i("rxq", "get contests start: ");
            }

            public void a(long j, long j2) {
                Log.i("rxq", "get contests loading: ");
            }

            public void a(String str) {
                Log.i("rxq", "get contests suc: " + str);
                if (str != null) {
                    ContestsModel contestsModel = (ContestsModel) h.b(str, ContestsModel.class);
                    if (contestsModel != null && contestsModel.status == 0 && contestsModel.events != null) {
                        this.a.an = new a(this.a, contestsModel.events);
                        this.a.am.setAdapter(this.a.an);
                        this.a.an.a(new a.a(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void a(Event event, View view) {
                                if (event != null && this.a.a.ae != null && this.a.a.ad != null && view != null) {
                                    this.a.a.a(this.a.a.ae, event.id, this.a.a.ad, view);
                                    Log.i("rxq", "begin post join");
                                }
                            }
                        });
                        this.a.al.setCount(contestsModel.events.size());
                        this.a.al.setItemDisatance(0);
                        this.a.al.setIndicatorResource(R.drawable.v2_explore_banner_selected, R.drawable.v2_explore_banner_not_selected);
                        this.a.al.setItemSize(20);
                        if (contestsModel.events.size() > 1) {
                            this.a.am.setCurrentItem(1000 - (1000 % contestsModel.events.size()), false);
                        } else {
                            this.a.al.setVisibility(4);
                        }
                        this.a.ao.sendEmptyMessageDelayed(1, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                    }
                }
            }

            public void a(Throwable th, int i, String str) {
                Log.i("rxq", "get contests fail, strMsg: " + str);
            }
        });
    }

    private void a(String str, int i, String str2, final View view) {
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("target_type", str2);
        bVar.a("target_id", str);
        bVar.a(n.J, String.valueOf(i));
        Log.i("rxq", this.ae + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i);
        c.b(this).b(a$l.i, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIShareLaterActivity b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Log.i("rxq", "post suc:" + str);
                view.setSelected(true);
                view.setClickable(false);
                ((TextView) view.findViewById(R.id.czi)).setText(this.b.getString(R.string.v2_share_contest_banner_item_been_submited));
            }

            public void a(Throwable th, int i, String str) {
                Log.i("rxq", "post fail:" + str);
                Toast.makeText(this.b, this.b.getString(R.string.v2_share_contest_join_fail), 0).show();
            }
        });
    }

    public void onBackPressed() {
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.ao != null && this.ao.hasMessages(1)) {
            this.ao.removeMessages(1);
        }
    }
}
