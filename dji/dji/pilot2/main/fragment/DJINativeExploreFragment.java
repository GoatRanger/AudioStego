package dji.pilot2.main.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.config.OdnpConfigStatic;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.mine.view.RefreshableView;
import dji.pilot2.mine.view.RefreshableView.b;
import dji.pilot2.nativeexplore.activity.ExploreSearchActivity;
import dji.pilot2.nativeexplore.activity.GuideListActivity;
import dji.pilot2.nativeexplore.b.c;
import dji.pilot2.nativeexplore.b.d;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.explorepost.ExplorePostSelectActivity;
import dji.pilot2.nativeexplore.model.DeleteArtworkEvent;
import dji.pilot2.nativeexplore.model.ExploreBannerAdsModel.ExploreActivityModel;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.ExploreItem.ExploreType;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.LikeEvent;
import dji.pilot2.nativeexplore.view.IndicatorBar;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.share.model.UploadEvent;
import dji.pilot2.utils.l;
import dji.pilot2.utils.p;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DJINativeExploreFragment extends Fragment implements OnClickListener, o, a$i {
    private static final int T = 1;
    private static final float bd = 2.0f;
    private static final float be = 2.9985359f;
    private static final float bf = 1.9883721f;
    private static final float bg = 2.9939759f;
    private Handler U;
    private View V;
    private View W;
    private RefreshableView X;
    private View aA;
    private View aB;
    private View aC;
    private View aD;
    private LinearLayout aE;
    private View aF;
    private c aG;
    private List<d> aH;
    private dji.pilot2.nativeexplore.a.a aI;
    private dji.pilot2.nativeexplore.a.c aJ;
    private View aK;
    private View aL;
    private View aM;
    private Dialog aN;
    private volatile int aO = 0;
    private DJIDeviceType aP;
    private int aQ;
    private String aR = "";
    private boolean[] aS = new boolean[]{false, false, false};
    private boolean aT = false;
    private int[] aU = new int[]{0, 0, 0};
    private int[] aV = new int[]{0, 0, 0};
    private boolean aW = false;
    private int aX;
    private int aY;
    private int aZ;
    private DJITextView at;
    private ListView au;
    private List<View> av;
    private View aw;
    private IndicatorBar ax;
    private ViewPager ay;
    private View az;
    private int ba;
    private boolean bb = false;
    private boolean bc = false;

    private static class a extends Handler {
        private WeakReference<DJINativeExploreFragment> a;

        public a(DJINativeExploreFragment dJINativeExploreFragment) {
            this.a = new WeakReference(dJINativeExploreFragment);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    DJINativeExploreFragment dJINativeExploreFragment = (DJINativeExploreFragment) this.a.get();
                    if (dJINativeExploreFragment != null) {
                        if (dJINativeExploreFragment.aI.a() > 1 && dJINativeExploreFragment.aI.getCount() != 0) {
                            try {
                                Method declaredMethod = dJINativeExploreFragment.ay.getClass().getDeclaredMethod("setCurrentItemInternal", new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Integer.TYPE});
                                declaredMethod.setAccessible(true);
                                declaredMethod.invoke(dJINativeExploreFragment.ay, new Object[]{Integer.valueOf((dJINativeExploreFragment.ay.getCurrentItem() + 1) % dJINativeExploreFragment.aI.getCount()), Boolean.valueOf(true), Boolean.valueOf(false), Integer.valueOf(1)});
                            } catch (Exception e) {
                                e.printStackTrace();
                                dJINativeExploreFragment.ay.setCurrentItem((dJINativeExploreFragment.ay.getCurrentItem() + 1) % dJINativeExploreFragment.aI.getCount(), true);
                            }
                        }
                        sendEmptyMessageDelayed(1, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        dji.thirdparty.a.c.a().a(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.bc = true;
        dji.thirdparty.a.c.a().d(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.V = layoutInflater.inflate(R.layout.v2_fragment_native_explore, null);
        a();
        a(this.V);
        b();
        return this.V;
    }

    public void onResume() {
        super.onResume();
        this.bb = dji.pilot2.widget.a.a(getActivity(), 7);
        int i;
        if (!f.getInstance().c()) {
            this.aR = "";
            for (i = 0; i < this.aH.size(); i++) {
                ((d) this.aH.get(i)).a("token", this.aR);
            }
        } else if (!f.getInstance().n().equals(this.aR)) {
            this.aR = f.getInstance().n();
            for (i = 0; i < this.aH.size(); i++) {
                ((d) this.aH.get(i)).a("token", this.aR);
            }
        }
        this.aW = true;
    }

    public void onPause() {
        super.onPause();
        this.aW = false;
    }

    private void a() {
        this.U = new a(this);
        this.aP = DJIOriLayout.getDeviceType();
        this.aQ = f();
        this.av = new ArrayList();
        if (f.getInstance().c()) {
            this.aR = f.getInstance().n();
        } else {
            this.aR = "";
        }
        this.aN = new dji.pilot2.nativeexplore.c.d(getActivity());
        this.aG = new c(getActivity());
        c();
        this.aG.a(new dji.pilot2.nativeexplore.b.c.a(this) {
            final /* synthetic */ DJINativeExploreFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.aI.a(this.a.aG.b);
                this.a.ax.setCount(this.a.aG.b.size());
                this.a.ax.setItemDisatance(0);
                this.a.ax.setIndicatorResource(R.drawable.v2_explore_banner_selected, R.drawable.v2_explore_banner_not_selected);
                this.a.ax.setItemSize(20);
                if (this.a.aG.b.size() > 1) {
                    this.a.ay.setCurrentItem(1000 - (1000 % this.a.aG.b.size()), false);
                }
                this.a.U.sendEmptyMessageDelayed(1, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                if (this.a.aO == 0 && this.a.aJ.getCount() != 0) {
                    this.a.aJ.b(this.a.aG.a);
                    if (this.a.aG.c != null) {
                        this.a.a(this.a.aG.c);
                    }
                    this.a.au.removeHeaderView(this.a.aw);
                    this.a.d();
                    try {
                        this.a.au.addHeaderView(this.a.aw, null, false);
                    } catch (IllegalStateException e) {
                        this.a.au.setAdapter(null);
                        this.a.au.addHeaderView(this.a.aw, null, false);
                        this.a.au.setAdapter(this.a.aJ);
                    }
                }
            }
        });
        this.aH = new ArrayList();
        this.aJ = new dji.pilot2.nativeexplore.a.c(getActivity());
        r7 = new String[3][];
        r7[0] = new String[]{a$i.ce_, a$i.cf_};
        r7[1] = new String[]{a$i.cg_, a$i.ch_};
        r7[2] = new String[]{a$i.ci_};
        for (int i = 0; i < r7.length; i++) {
            Map hashMap = new HashMap();
            hashMap.put("token", this.aR);
            d dVar = new d(getActivity(), r7[i], hashMap, ParamKey.PAGE, n.am);
            dVar.a(i);
            if (i == 0) {
                dVar.a(true);
            } else if (i == 2) {
                dVar.a(2);
                dVar.b(10);
            }
            dVar.a(new i(this) {
                final /* synthetic */ DJINativeExploreFragment a;

                {
                    this.a = r1;
                }

                public void a(int i, boolean z, boolean z2) {
                    if (!this.a.bc) {
                        this.a.aS[i] = false;
                        if (z && i == this.a.aO) {
                            this.a.aJ.a();
                        }
                        if (this.a.aO == i) {
                            this.a.aJ.b(z2);
                            this.a.aJ.a(((d) this.a.aH.get(i)).b);
                            if (this.a.aO == 0 && this.a.aJ.getCount() != 0 && this.a.aG.b()) {
                                this.a.aJ.b(this.a.aG.a);
                                if (this.a.aG.c != null) {
                                    this.a.a(this.a.aG.c);
                                }
                                this.a.au.removeHeaderView(this.a.aw);
                                this.a.d();
                                try {
                                    this.a.au.addHeaderView(this.a.aw, null, false);
                                } catch (IllegalStateException e) {
                                    this.a.au.setAdapter(null);
                                    this.a.au.addHeaderView(this.a.aw, null, false);
                                    this.a.au.setAdapter(this.a.aJ);
                                }
                            }
                            this.a.X.finishRefreshing();
                            this.a.az.setVisibility(4);
                            this.a.e();
                            this.a.aB.setVisibility(4);
                        }
                    }
                }

                public void a(int i) {
                    this.a.aS[i] = true;
                    if (this.a.aO == i) {
                        this.a.X.finishRefreshing();
                        if (this.a.aJ.getCount() == 1) {
                            this.a.az.setVisibility(0);
                        } else if (this.a.aW && !this.a.aN.isShowing()) {
                            this.a.aN.show();
                        }
                        this.a.aB.setVisibility(4);
                    }
                }
            });
            this.aH.add(dVar);
        }
    }

    private void a(View view) {
        this.W = view.findViewById(R.id.coa);
        this.W.setOnClickListener(this);
        this.at = (DJITextView) view.findViewById(R.id.cob);
        this.at.go();
        this.X = (RefreshableView) view.findViewById(R.id.c8q);
        this.au = (ListView) view.findViewById(R.id.c92);
        this.az = view.findViewById(R.id.d5a);
        this.aA = view.findViewById(R.id.d5b);
        this.aB = view.findViewById(R.id.d5c);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.aB.getBackground();
        this.aD = view.findViewById(R.id.co1);
        this.aD.setOnClickListener(this);
        animationDrawable.start();
        this.aC = view.findViewById(R.id.coc);
        this.aI = new dji.pilot2.nativeexplore.a.a(getActivity());
        this.aw = LayoutInflater.from(getActivity()).inflate(R.layout.v2_explore_banner_view, null, false);
        this.ax = (IndicatorBar) this.aw.findViewById(R.id.ckm);
        this.aE = (LinearLayout) this.aw.findViewById(R.id.cko);
        this.aF = this.aw.findViewById(R.id.ckp);
        this.aF.setOnClickListener(this);
        a(null);
        this.au.addHeaderView(this.aw, null, false);
        this.aK = view.findViewById(R.id.co4);
        this.aL = view.findViewById(R.id.co7);
        this.aM = view.findViewById(R.id.co_);
        this.ay = (ViewPager) this.aw.findViewById(R.id.ckl);
        this.ay.setAdapter(this.aI);
        this.ay.addOnPageChangeListener(new OnPageChangeListener(this) {
            int a = 0;
            final /* synthetic */ DJINativeExploreFragment b;

            {
                this.b = r2;
            }

            public void onPageSelected(int i) {
                this.b.ax.setSelectedIndex(i % this.b.aI.a());
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
                if (this.a != i) {
                    this.a = i;
                    if (i == 0) {
                        if (this.b.U.hasMessages(1)) {
                            this.b.U.removeMessages(1);
                        }
                        this.b.U.sendEmptyMessageDelayed(1, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                    } else if (i == 1 && this.b.U.hasMessages(1)) {
                        this.b.U.removeMessages(1);
                    }
                }
            }
        });
        DJILogHelper.getInstance().LOGI("Lyric", "add header");
        this.au.setAdapter(this.aJ);
        this.av.add(view.findViewById(R.id.co2));
        this.av.add(view.findViewById(R.id.co5));
        this.av.add(view.findViewById(R.id.co8));
        if (!f.getInstance().c()) {
            ((View) this.av.get(2)).setVisibility(8);
        }
        for (int i = 0; i < this.av.size(); i++) {
            if (this.av.get(i) != null) {
                if (i == 0) {
                    ((View) this.av.get(i)).setSelected(true);
                    this.aO = 0;
                    a(i);
                }
                ((View) this.av.get(i)).setOnClickListener(this);
            }
        }
        this.X.setOnRefreshListener(new b(this) {
            final /* synthetic */ DJINativeExploreFragment a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.aO == 2 && this.a.aT) {
                    this.a.aT = false;
                    ((d) this.a.aH.get(2)).a();
                    this.a.U.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.a.aO == 2) {
                                this.a.a.aJ.a();
                                this.a.a.aJ.notifyDataSetChanged();
                                ((d) this.a.a.aH.get(this.a.a.aO)).c();
                            }
                        }
                    });
                    return;
                }
                ((d) this.a.aH.get(this.a.aO)).c();
                if (!this.a.aG.b() && !this.a.aG.c()) {
                    this.a.aG.a();
                }
            }
        }, 1);
        this.au.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ DJINativeExploreFragment a;
            private volatile boolean b = false;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.b && i == 0) {
                    if (this.a.aO == 2 && this.a.aT) {
                        this.a.aT = false;
                        ((d) this.a.aH.get(2)).a();
                        this.a.aJ.a();
                        this.a.aJ.notifyDataSetChanged();
                    }
                    if (!((d) this.a.aH.get(this.a.aO)).b()) {
                        ((d) this.a.aH.get(this.a.aO)).d();
                    }
                    this.b = false;
                }
                if (this.a.bb && i == 0 && ((d) this.a.aH.get(this.a.aO)).b.size() > 0) {
                    View a = this.a.c((int) R.id.cuy);
                    if (a != null) {
                        this.a.bb = false;
                        this.a.au.setEnabled(false);
                        this.a.a(a, 7);
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
        this.aA.setOnClickListener(this);
    }

    private void b() {
        for (int i = 0; i < this.aH.size(); i++) {
            ((d) this.aH.get(i)).c();
        }
        this.aG.a();
    }

    private void c() {
        float f;
        float f2;
        if (this.aP == DJIDeviceType.Phone) {
            f = bd;
            f2 = bf;
            this.aX = this.aQ;
        } else {
            f = be;
            f2 = bg;
            this.aX = (this.aQ * 3) / 5;
        }
        DJILogHelper.getInstance().LOGI("Lyric", "banner width: " + this.aX);
        if (this.aG.b == null || this.aG.b.size() <= 0) {
            this.aY = 0;
        } else {
            this.aY = (int) (((float) this.aX) / f);
        }
        this.aZ = this.aX / 2;
        this.ba = (int) (((float) this.aZ) / f2);
    }

    private void d() {
        if (this.U.hasMessages(1)) {
            this.U.removeMessages(1);
        }
        if (this.aG.b()) {
            c();
            LayoutParams layoutParams = this.aE.getLayoutParams();
            if (this.aG.c == null || this.aG.c.size() <= 0) {
                layoutParams.height = 0;
            } else {
                layoutParams.height = this.ba;
                layoutParams.width = this.aZ * 2;
            }
            this.aE.setLayoutParams(layoutParams);
            a(this.aZ, this.ba);
            this.ay.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.aY));
        }
        this.U.sendEmptyMessageDelayed(1, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
    }

    private void b(int i) {
        int i2;
        if (i < 2) {
            dji.pilot2.nativeexplore.a.d.T = true;
        } else {
            dji.pilot2.nativeexplore.a.d.T = false;
        }
        this.aU[this.aO] = this.au.getFirstVisiblePosition();
        View childAt = this.au.getChildAt(0);
        int[] iArr = this.aV;
        int i3 = this.aO;
        if (childAt == null) {
            i2 = 0;
        } else {
            i2 = childAt.getTop() - this.au.getPaddingTop();
        }
        iArr[i3] = i2;
        for (int i4 = 0; i4 < this.av.size(); i4++) {
            if (this.av.get(i4) != null) {
                if (i4 != i) {
                    ((View) this.av.get(i4)).setSelected(false);
                } else {
                    ((View) this.av.get(i4)).setSelected(true);
                }
            }
        }
        this.aO = i;
        if (i == 2) {
            this.aJ.a(true);
        } else {
            this.aJ.a(false);
        }
        if (this.aO == 0 || this.aw == null) {
            this.au.removeHeaderView(this.aw);
            d();
            try {
                this.au.addHeaderView(this.aw, null, false);
            } catch (IllegalStateException e) {
                this.au.setAdapter(null);
                this.au.addHeaderView(this.aw, null, false);
                this.au.setAdapter(this.aJ);
            }
        } else {
            this.au.removeHeaderView(this.aw);
        }
        this.X.finishRefreshing();
        this.aJ.a();
        this.aJ.a(((d) this.aH.get(i)).b);
        if (this.aO == 0 && this.aG.b()) {
            this.aJ.b(this.aG.a);
        }
        this.aJ.b(((d) this.aH.get(i)).b());
        e();
        this.au.setSelectionFromTop(this.aU[this.aO], this.aV[this.aO]);
        this.aB.setVisibility(4);
    }

    private void e() {
        if (this.aO == 2) {
            int i;
            for (int i2 = 0; i2 < ((d) this.aH.get(2)).b.size(); i2++) {
                if (!((ExploreItem) ((d) this.aH.get(2)).b.get(i2)).userId.equals(f.getInstance().i())) {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i != 0) {
                this.aC.setVisibility(8);
                return;
            } else {
                this.aC.setVisibility(0);
                return;
            }
        }
        this.aC.setVisibility(8);
    }

    private int f() {
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            return Math.max(point.x, point.y);
        }
        return Math.min(point.x, point.y);
    }

    private View c(int i) {
        int dimension = (int) getResources().getDimension(R.dimen.c9);
        int dimension2 = (int) getResources().getDimension(R.dimen.o);
        int dimension3 = (int) getResources().getDimension(R.dimen.fi);
        View view = null;
        getActivity().getWindow().getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = Integer.MAX_VALUE;
        int[] iArr = new int[2];
        int i3 = 0;
        while (i3 < this.au.getChildCount()) {
            int abs;
            View view2;
            View childAt = this.au.getChildAt(i3);
            View findViewById = childAt.findViewById(i);
            if (childAt.getTag() != null && (childAt.getTag() instanceof dji.pilot2.nativeexplore.a.d)) {
                dji.pilot2.nativeexplore.a.d dVar = (dji.pilot2.nativeexplore.a.d) childAt.getTag();
                if (!(findViewById == null || dVar.U.type.equals(ExploreType.ads))) {
                    findViewById.getLocationInWindow(iArr);
                    if (iArr[1] > dimension + dimension3 && iArr[1] < ((displayMetrics.heightPixels - findViewById.getHeight()) - dimension2) - dimension3 && Math.abs((displayMetrics.heightPixels / 2) - iArr[1]) < i2) {
                        abs = Math.abs((displayMetrics.heightPixels / 2) - iArr[1]);
                        view2 = findViewById;
                        i3++;
                        view = view2;
                        i2 = abs;
                    }
                }
            }
            abs = i2;
            view2 = view;
            i3++;
            view = view2;
            i2 = abs;
        }
        return view;
    }

    private void a(View view, int i) {
        dji.pilot2.widget.a aVar = new dji.pilot2.widget.a(getActivity(), R.style.hf, i);
        int[] c = p.c(getActivity());
        WindowManager.LayoutParams attributes = aVar.getWindow().getAttributes();
        attributes.width = c[0];
        attributes.height = c[1];
        aVar.getWindow().setAttributes(attributes);
        aVar.a(0.0f);
        c = new int[2];
        view.getLocationInWindow(c);
        Log.i(dji.pilot2.widget.a.a, "location icon x: " + c[0] + ", y: " + c[1] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        int width = view.getWidth() / 2;
        aVar.a(c[0] - width, c[1] - width, width * 2, getResources().getDimensionPixelSize(R.dimen.fj));
        aVar.show();
        if (view.getId() == R.id.cuy) {
            aVar.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ DJINativeExploreFragment a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.a.au.setEnabled(true);
                    View a = this.a.c((int) R.id.cuz);
                    if (a != null) {
                        this.a.a(a, 8);
                    }
                }
            });
        } else if (view.getId() == R.id.cuz) {
            aVar.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ DJINativeExploreFragment a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    dji.pilot2.widget.a.b(this.a.getActivity(), 7);
                }
            });
        }
    }

    public void a(int i) {
        if (i == 0) {
            this.aK.setSelected(true);
            this.aL.setSelected(false);
            this.aM.setSelected(false);
        } else if (i == 1) {
            this.aK.setSelected(false);
            this.aL.setSelected(true);
            this.aM.setSelected(false);
        } else if (i == 2) {
            this.aK.setSelected(false);
            this.aL.setSelected(false);
            this.aM.setSelected(true);
        }
    }

    public void onClick(View view) {
        int i = 0;
        for (int i2 = 0; i2 < this.av.size(); i2++) {
            if (view == this.av.get(i2)) {
                b(i2);
                a(i2);
                if (i2 == 0) {
                    e.b(o.dT_);
                    return;
                } else if (i2 == 1) {
                    e.b(o.dU_);
                    return;
                } else if (i2 == 2) {
                    e.b(o.dV_);
                    return;
                } else {
                    return;
                }
            }
        }
        switch (view.getId()) {
            case R.id.ckp:
                startActivity(new Intent(getActivity(), GuideListActivity.class));
                return;
            case R.id.co1:
                startActivity(new Intent(getActivity(), ExploreSearchActivity.class));
                return;
            case R.id.coa:
                e.b(o.I);
                l.getInstance().b(l.a[1], false);
                dji.pilot2.nativeexplore.c.b.a(getActivity(), new dji.pilot2.nativeexplore.c.b.a(this) {
                    final /* synthetic */ DJINativeExploreFragment a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        Intent intent = new Intent(this.a.getActivity(), ExplorePostSelectActivity.class);
                        intent.putExtra(ExplorePostSelectActivity.a, false);
                        this.a.startActivity(intent);
                    }

                    public void b() {
                        Intent intent = new Intent(this.a.getActivity(), ExplorePostSelectActivity.class);
                        intent.putExtra(ExplorePostSelectActivity.a, true);
                        this.a.startActivity(intent);
                    }

                    public void c() {
                    }
                });
                return;
            case R.id.d5b:
                this.az.setVisibility(4);
                this.aB.setVisibility(0);
                while (i < this.aH.size()) {
                    ((d) this.aH.get(i)).c();
                    i++;
                }
                if (!this.aG.b() && !this.aG.c()) {
                    this.aG.a();
                    return;
                }
                return;
            default:
                b(view);
                return;
        }
    }

    public void onEventMainThread(ExploreEvent exploreEvent) {
        int i;
        int i2;
        switch (exploreEvent) {
            case USER_LOGIN:
                DJILogHelper.getInstance().LOGE("Lyric", "NATIVEEXPLORE USER_LOGIN");
                ((View) this.av.get(2)).setVisibility(0);
                this.aJ.a();
                for (i = 0; i < this.aH.size(); i++) {
                    ((d) this.aH.get(i)).a();
                    ((d) this.aH.get(i)).a("token", f.getInstance().n());
                }
                for (i = 0; i < this.aH.size(); i++) {
                    ((d) this.aH.get(i)).c();
                }
                for (i2 = 0; i2 < this.aU.length; i2++) {
                    this.aU[i2] = 0;
                    this.aV[i2] = 0;
                }
                b(this.aO);
                return;
            case USER_LOGOUT:
                DJILogHelper.getInstance().LOGE("Lyric", "NATIVEEXPLORE USER_LOGOUT");
                ((View) this.av.get(2)).setVisibility(8);
                this.aJ.a();
                for (i = 0; i < this.aH.size(); i++) {
                    ((d) this.aH.get(i)).a();
                    ((d) this.aH.get(i)).a("token", "");
                }
                for (i = 0; i < this.aH.size(); i++) {
                    ((d) this.aH.get(i)).c();
                }
                for (i2 = 0; i2 < this.aU.length; i2++) {
                    this.aU[i2] = 0;
                    this.aV[i2] = 0;
                }
                if (this.aO == 2) {
                    b(0);
                    return;
                } else {
                    b(this.aO);
                    return;
                }
            case USER_MSG:
                DJILogHelper.getInstance().LOGE("zhang", "Has user msg");
                i2 = l.getInstance().a();
                DJILogHelper.getInstance().LOGE("zhang", "count:" + i2);
                if (i2 > 99) {
                    this.at.setText("99+");
                } else {
                    this.at.setText("" + i2);
                }
                this.at.show();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(FollowEvent followEvent) {
        boolean z;
        dji.pilot2.nativeexplore.model.FollowEvent.a aVar = followEvent.subject;
        dji.pilot2.nativeexplore.model.FollowEvent.a aVar2 = followEvent.object;
        this.aT = true;
        if (followEvent.action == FollowEvent.b.FOLLOW) {
            z = true;
        } else {
            z = false;
        }
        for (int i = 0; i < this.aH.size(); i++) {
            if (((d) this.aH.get(i)).b != null) {
                for (int i2 = 0; i2 < ((d) this.aH.get(i)).b.size(); i2++) {
                    ExploreItem exploreItem = (ExploreItem) ((d) this.aH.get(i)).b.get(i2);
                    if (exploreItem != null && exploreItem.userId.equals(aVar2.a)) {
                        exploreItem.isFollowed = z;
                    }
                }
            }
        }
        this.aJ.notifyDataSetChanged();
    }

    public void onEventMainThread(LikeEvent likeEvent) {
        boolean z;
        String str = likeEvent.id;
        if (likeEvent.action == dji.pilot2.nativeexplore.model.LikeEvent.a.LIKE) {
            z = true;
        } else {
            z = false;
        }
        for (int i = 0; i < this.aH.size(); i++) {
            if (((d) this.aH.get(i)).b != null) {
                for (int i2 = 0; i2 < ((d) this.aH.get(i)).b.size(); i2++) {
                    ExploreItem exploreItem = (ExploreItem) ((d) this.aH.get(i)).b.get(i2);
                    if (!(exploreItem == null || !exploreItem.id.equals(str) || exploreItem.isLiked == z)) {
                        exploreItem.isLiked = z;
                        exploreItem.likeCount = likeEvent.likeCount;
                    }
                }
            }
        }
        this.aJ.notifyDataSetChanged();
    }

    public void onEventMainThread(DeleteArtworkEvent deleteArtworkEvent) {
        int i = 0;
        while (i < this.aH.size()) {
            if (((d) this.aH.get(i)).a(deleteArtworkEvent.id) && i == this.aO) {
                this.aJ.a();
                this.aJ.a(((d) this.aH.get(i)).b);
                this.aJ.notifyDataSetChanged();
            }
            i++;
        }
    }

    public void onEventMainThread(UploadEvent uploadEvent) {
        if (uploadEvent.result == dji.pilot2.share.model.UploadEvent.a.UPLOAD_SUCCESS) {
            b(2);
            this.X.showHeaderView();
            ((d) this.aH.get(2)).c();
        }
    }

    private void a(List<ExploreActivityModel> list) {
        if (list != null && list.size() > 0) {
            this.aE.removeAllViews();
            this.aE.addView(this.aF);
            for (ExploreActivityModel exploreActivityModel : list) {
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.v2_explore_activities_item, this.aE, false);
                a(inflate, exploreActivityModel);
                this.aE.addView(inflate);
            }
        }
        if (this.aE.getChildCount() == 1) {
            a(this.aE.getChildAt(0), this.aZ * 2, this.ba);
        }
    }

    private void a(View view, ExploreActivityModel exploreActivityModel) {
        ImageView imageView = (ImageView) view.findViewById(R.id.cki);
        if (imageView != null && exploreActivityModel != null) {
            imageView.setImageBitmap(null);
            if (DJIOriLayout.getDeviceType().equals(DJIDeviceType.Pad)) {
                com.dji.frame.c.c.a(getActivity()).a(imageView, exploreActivityModel.pad_img);
            } else {
                com.dji.frame.c.c.a(getActivity()).a(imageView, exploreActivityModel.mobile_img);
            }
            view.setOnClickListener(this);
            view.setTag(exploreActivityModel);
        }
    }

    private void a(int i, int i2) {
        int i3 = 0;
        int childCount = this.aE.getChildCount();
        if (childCount == 1) {
            a(this.aE.getChildAt(0), i * 2, i2);
            return;
        }
        while (i3 < childCount) {
            a(this.aE.getChildAt(i3), i, i2);
            i3++;
        }
    }

    private void a(View view, int i, int i2) {
        LayoutParams layoutParams = view.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.fz);
        layoutParams.width = i - (dimensionPixelSize * 2);
        layoutParams.height = i2;
        ((MarginLayoutParams) layoutParams).setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        view.setLayoutParams(layoutParams);
    }

    private void b(View view) {
        Object tag = view.getTag();
        if (tag != null && (tag instanceof ExploreActivityModel)) {
            int childCount = this.aE.getChildCount();
            for (int i = 1; i < childCount; i++) {
                if (view == this.aE.getChildAt(i)) {
                    b(view, i);
                    return;
                }
            }
        }
    }

    private void b(View view, int i) {
        ExploreActivityModel exploreActivityModel = (ExploreActivityModel) view.getTag();
        Intent intent = new Intent(getActivity(), DJISupportShareWebviewActivity.class);
        if (!(exploreActivityModel == null || exploreActivityModel.target_url == null)) {
            intent.putExtra(DJIWebviewFragment.o, exploreActivityModel.target_url);
            if (exploreActivityModel.target_url.contains("flightlog")) {
                intent.putExtra(DJIWebviewFragment.x, true);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("index", (i - 1) + "");
            hashMap.put("name", exploreActivityModel.name);
            e.a(o.M, hashMap);
        }
        getActivity().startActivity(intent);
    }
}
